-- Most runs in test between INDIA and RSA
SELECT ID, Batsman, Span, Mats, Inns, Runs, Balls, NO, ROUND(CAST(Runs AS FLOAT)/(Inns - NO), 2) AS Avg, 
ROUND(CAST(Runs AS FLOAT)/Balls * 100, 2) AS SR, Fours as 4s, Sixes as 6s, FIFTIES as 50s, HUNDREDS as 100s, HS
FROM (
	SELECT batsman_id AS ID, known_as AS Batsman,
	count(DISTINCT bs.match_id) AS Mats,
	concat(min(year(start_date_raw)), "-", max(year(start_date_raw))) as Span,
	SUM(runs) AS Runs,
	-- IF(SUM(balls IS NULL), NULL, SUM(balls)) as Balls2,
    IF(SUM(balls IS NULL AND runs is NOT NULL), NULL, SUM(balls)) as Balls,
	COUNT(*) AS Inns,
	SUM(CASE WHEN is_out = 1 THEN 0 ELSE 1 END) AS NO,
	SUM(fours) as Fours, SUM(sixes) AS Sixes, SUM(CASE WHEN runs >= 50 AND runs < 100 THEN 1 ELSE 0 END) AS FIFTIES,
	SUM(CASE WHEN bs.runs >= 100 THEN 1 ELSE 0 END) AS HUNDREDS,
    max(runs) AS HS 
	FROM batting_scorecard bs 
	INNER JOIN match_summary ms 
	ON bs.match_id = ms.match_id AND ms.international_class_id = 1 
	-- AND runs IS NOT NULL AND batted_type = "yes" 
    AND team1id IN (3, 6) AND team2id IN (3, 6) 
    INNER JOIN squad_summary ss
    ON bs.match_id = ss.match_id AND bs.batsman_id = ss.object_id
	GROUP BY batsman_id ORDER BY Runs DESC
) AS Final;

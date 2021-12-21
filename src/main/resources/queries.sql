/*  Delete contents from all tables */
DELETE FROM `cricketdb`.`batting_scorecard`;
DELETE FROM `cricketdb`.`bowling_scorecard`;
DELETE FROM `cricketdb`.`debuts`;
DELETE FROM `cricketdb`.`fow_summary`;
DELETE FROM `cricketdb`.`match_innings`;
DELETE FROM `cricketdb`.`official_summary`;
DELETE FROM `cricketdb`.`partnership_summary`;
DELETE FROM `cricketdb`.`players_of_the_match`;
DELETE FROM `cricketdb`.`players_of_the_series`;
DELETE FROM `cricketdb`.`replacement_summary`;
DELETE FROM `cricketdb`.`series`;
DELETE FROM `cricketdb`.`squad_summary`;
DELETE FROM `cricketdb`.`match_summary`;

/* DROP TABLES */
DROP TABLE `cricketdb`.`batting_scorecard`, `cricketdb`.`bowling_scorecard`, `cricketdb`.`debuts`, `cricketdb`.`fow_summary`, `cricketdb`.`match_innings`, `cricketdb`.`match_summary`, `cricketdb`.`official_summary`, `cricketdb`.`partnership_summary`, `cricketdb`.`players_of_the_match`, `cricketdb`.`players_of_the_series`, `cricketdb`.`replacement_summary`, `cricketdb`.`series`, `cricketdb`.`squad_summary`;

-- Most runs in test
SELECT ID, Span, Inns, Runs, Balls, NO, ROUND(CAST(Runs AS FLOAT)/(Inns - NO), 2) AS Avg, 
ROUND(CAST(Runs AS FLOAT)/Balls * 100, 2) AS SR, Fours as 4s, Sixes as 6s, FIFTIES as 50s, HUNDREDS as 100s 
FROM (
	SELECT batsman_id AS ID, 
	concat(min(year(start_date_raw)), "-", max(year(start_date_raw))) as Span,
	SUM(runs) AS Runs,
	IF(SUM(balls is NULL), NULL, SUM(balls)) as Balls,
	COUNT(*) AS Inns,
	SUM(CASE 
		WHEN is_out = 1 THEN 0 ELSE 1 END) AS NO,
	SUM(fours) as Fours, SUM(sixes) AS Sixes, SUM(CASE WHEN runs >= 50 AND runs < 100 THEN 1 ELSE 0 END) AS FIFTIES, 
	SUM(CASE WHEN bs.runs >= 100 THEN 1 ELSE 0 END) AS HUNDREDS 
	FROM batting_scorecard bs 
	INNER JOIN match_summary ms 
	ON bs.match_id = ms.match_id 
	WHERE bs.match_id IN (SELECT match_id FROM results_summary WHERE class_id = 1) 
	AND runs IS NOT NULL AND batted_type = "yes"
	GROUP BY batsman_id ORDER BY Runs DESC
) AS Final;

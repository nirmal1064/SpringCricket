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
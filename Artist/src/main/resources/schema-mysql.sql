DROP TABLE IF EXISTS `Artist` ;

CREATE TABLE IF NOT EXISTS `Artist` (
  `Artistid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `catchphrase` VARCHAR(105) NOT NULL,
  `description` VARCHAR(205)  NOT NULL,
  PRIMARY KEY (`Artistid`));

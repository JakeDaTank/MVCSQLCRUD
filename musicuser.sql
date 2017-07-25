-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema musicuserdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `musicuserdb` ;

-- -----------------------------------------------------
-- Schema musicuserdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `musicuserdb` DEFAULT CHARACTER SET utf8 ;
USE `musicuserdb` ;

-- -----------------------------------------------------
-- Table `Artist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Artist` ;

CREATE TABLE IF NOT EXISTS `Artist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(25) NULL,
  `last_name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Album`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Album` ;

CREATE TABLE IF NOT EXISTS `Album` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Song`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Song` ;

CREATE TABLE IF NOT EXISTS `Song` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `album_id` INT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id`
    FOREIGN KEY (`album_id`)
    REFERENCES `Album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `id_idx` ON `Song` (`album_id` ASC);


-- -----------------------------------------------------
-- Table `Album_has_Artist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Album_has_Artist` ;

CREATE TABLE IF NOT EXISTS `Album_has_Artist` (
  `Album_id` INT NOT NULL,
  `Artist_id` INT NOT NULL,
  PRIMARY KEY (`Album_id`, `Artist_id`),
  CONSTRAINT `fk_Album_has_Artist_Album1`
    FOREIGN KEY (`Album_id`)
    REFERENCES `Album` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Album_has_Artist_Artist1`
    FOREIGN KEY (`Artist_id`)
    REFERENCES `Artist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Album_has_Artist_Artist1_idx` ON `Album_has_Artist` (`Artist_id` ASC);

CREATE INDEX `fk_Album_has_Artist_Album1_idx` ON `Album_has_Artist` (`Album_id` ASC);

SET SQL_MODE = '';
GRANT USAGE ON *.* TO musicuser@localhost;
 DROP USER musicuser@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'musicuser'@'localhost' IDENTIFIED BY 'musicuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'musicuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `Artist`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicuserdb`;
INSERT INTO `Artist` (`id`, `first_name`, `last_name`) VALUES (1, 'Don', 'Henley');
INSERT INTO `Artist` (`id`, `first_name`, `last_name`) VALUES (2, 'Joe', 'Walsh');
INSERT INTO `Artist` (`id`, `first_name`, `last_name`) VALUES (3, 'Timothy', ' Schmit');
INSERT INTO `Artist` (`id`, `first_name`, `last_name`) VALUES (4, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Album`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicuserdb`;
INSERT INTO `Album` (`id`, `title`) VALUES (1, 'Hotel California');
INSERT INTO `Album` (`id`, `title`) VALUES (2, 'Hell Freezez Over');
INSERT INTO `Album` (`id`, `title`) VALUES (3, 'Timothy B. Schmit');

COMMIT;


-- -----------------------------------------------------
-- Data for table `Song`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicuserdb`;
INSERT INTO `Song` (`id`, `title`, `album_id`) VALUES (1, 'Hotel California', 1);
INSERT INTO `Song` (`id`, `title`, `album_id`) VALUES (2, 'New Kid In Town', 1);
INSERT INTO `Song` (`id`, `title`, `album_id`) VALUES (3, 'Life In The Fast Lane', 1);
INSERT INTO `Song` (`id`, `title`, `album_id`) VALUES (4, 'Wasted Time', 1);
INSERT INTO `Song` (`id`, `title`, `album_id`) VALUES (5, 'Wasted Time (Reprise)', 1);
INSERT INTO `Song` (`id`, `title`, `album_id`) VALUES (6, 'Victim Of Love', 1);
INSERT INTO `Song` (`id`, `title`, `album_id`) VALUES (7, 'Pretty Maids All In A Row', 1);
INSERT INTO `Song` (`id`, `title`, `album_id`) VALUES (8, 'Try And Love Again', 1);
INSERT INTO `Song` (`id`, `title`, `album_id`) VALUES (9, 'The Last Resort', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `Album_has_Artist`
-- -----------------------------------------------------
START TRANSACTION;
USE `musicuserdb`;
INSERT INTO `Album_has_Artist` (`Album_id`, `Artist_id`) VALUES (1, 1);
INSERT INTO `Album_has_Artist` (`Album_id`, `Artist_id`) VALUES (1, 2);
INSERT INTO `Album_has_Artist` (`Album_id`, `Artist_id`) VALUES (1, 3);
INSERT INTO `Album_has_Artist` (`Album_id`, `Artist_id`) VALUES (2, 1);
INSERT INTO `Album_has_Artist` (`Album_id`, `Artist_id`) VALUES (2, 2);
INSERT INTO `Album_has_Artist` (`Album_id`, `Artist_id`) VALUES (2, 3);
INSERT INTO `Album_has_Artist` (`Album_id`, `Artist_id`) VALUES (3, 1);
INSERT INTO `Album_has_Artist` (`Album_id`, `Artist_id`) VALUES (3, 2);
INSERT INTO `Album_has_Artist` (`Album_id`, `Artist_id`) VALUES (3, 3);

COMMIT;


CREATE SCHEMA IF NOT EXISTS `nhn_academy_43` DEFAULT CHARACTER SET utf8;
USE `nhn_academy_43`;


drop table if exists `nhn_academy_43`.`comments`;
drop table if exists `nhn_academy_43`.`task_tag`;
drop table if exists `nhn_academy_43`.`tags`;
drop table if exists `nhn_academy_43`.`tasks`;
drop table if exists `nhn_academy_43`.`mile_stones`;
drop table if exists `nhn_academy_43`.`members`;
drop table if exists `nhn_academy_43`.`role`;
drop table if exists `nhn_academy_43`.`projects`;
drop table if exists `nhn_academy_43`.`project_status`;



-- -----------------------------------------------------
-- Table `mini_dooray_task`.`project_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_43`.`project_status`
(
    `project_status_id`   INT         NOT NULL AUTO_INCREMENT,
    `project_status_name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`project_status_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mini_dooray_task`.`projects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_43`.`projects`
(
    `project_id`        BIGINT NOT NULL AUTO_INCREMENT,
    `account_id`        BIGINT NOT NULL,
    `project_status_id` INT    NOT NULL,
    PRIMARY KEY (`project_id`),
    INDEX `fk_project_project_status_idx` (`project_status_id` ASC) VISIBLE,
    CONSTRAINT `fk_project_project_status`
        FOREIGN KEY (`project_status_id`)
            REFERENCES `nhn_academy_43`.`project_status` (`project_status_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mini_dooray_task`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_43`.`role`
(
    `role_id`   INT         NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`role_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mini_dooray_task`.`members`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_43`.`members`
(
    `account_id`   BIGINT NOT NULL,
    `project_id`   BIGINT NOT NULL,
    `role_role_id` INT    NOT NULL,
    PRIMARY KEY (`account_id`, `project_id`),
    INDEX `fk_member_project1_idx` (`project_id` ASC) VISIBLE,
    INDEX `fk_member_role1_idx` (`role_role_id` ASC) VISIBLE,
    CONSTRAINT `fk_member_project1`
        FOREIGN KEY (`project_id`)
            REFERENCES `nhn_academy_43`.`projects` (`project_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_member_role1`
        FOREIGN KEY (`role_role_id`)
            REFERENCES `nhn_academy_43`.`role` (`role_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mini_dooray_task`.`mile_stones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_43`.`mile_stones`
(
    `mile_stone_id`         BIGINT      NOT NULL AUTO_INCREMENT,
    `project_id`            BIGINT      NOT NULL,
    `mile_stone_name`       VARCHAR(45) NOT NULL,
    `mile_stone_start_date` DATE        NULL,
    `mile_stone_end_date`   DATE        NULL,
    PRIMARY KEY (`mile_stone_id`),
    INDEX `fk_mile_stone_project1_idx` (`project_id` ASC) VISIBLE,
    CONSTRAINT `fk_mile_stone_project1`
        FOREIGN KEY (`project_id`)
            REFERENCES `nhn_academy_43`.`projects` (`project_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mini_dooray_task`.`tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_43`.`tasks`
(
    `task_id`       BIGINT       NOT NULL AUTO_INCREMENT,
    `project_id`    BIGINT       NOT NULL,
    `mile_stone_id` BIGINT       NOT NULL,
    `task_name`     VARCHAR(45)  NOT NULL,
    `task_content`  VARCHAR(200) NOT NULL,
    PRIMARY KEY (`task_id`),
    INDEX `fk_task_project1_idx` (`project_id` ASC) VISIBLE,
    INDEX `fk_task_mile_stone1_idx` (`mile_stone_id` ASC) VISIBLE,
    CONSTRAINT `fk_task_project1`
        FOREIGN KEY (`project_id`)
            REFERENCES `nhn_academy_43`.`projects` (`project_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_task_mile_stone1`
        FOREIGN KEY (`mile_stone_id`)
            REFERENCES `nhn_academy_43`.`mile_stones` (`mile_stone_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mini_dooray_task`.`tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_43`.`tags`
(
    `tag_id`     BIGINT      NOT NULL AUTO_INCREMENT,
    `project_id` BIGINT      NOT NULL,
    `tag_name`   VARCHAR(45) NOT NULL,
    PRIMARY KEY (`tag_id`),
    INDEX `fk_tag_project1_idx` (`project_id` ASC) VISIBLE,
    CONSTRAINT `fk_tag_project1`
        FOREIGN KEY (`project_id`)
            REFERENCES `nhn_academy_43`.`projects` (`project_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mini_dooray_task`.`task_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_43`.`task_tag`
(
    `task_id` BIGINT NOT NULL,
    `tag_id`  BIGINT NOT NULL,
    PRIMARY KEY (`task_id`, `tag_id`),
    INDEX `fk_task_tag_tag1_idx` (`tag_id` ASC) VISIBLE,
    CONSTRAINT `fk_task_tag_task1`
        FOREIGN KEY (`task_id`)
            REFERENCES `nhn_academy_43`.`tasks` (`task_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_task_tag_tag1`
        FOREIGN KEY (`tag_id`)
            REFERENCES `nhn_academy_43`.`tags` (`tag_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mini_dooray_task`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_43`.`comments`
(
    `comment_id`      VARCHAR(45) NOT NULL,
    `task_id`         BIGINT      NOT NULL,
    `account_id`      BIGINT      NOT NULL,
    `comment_content` VARCHAR(45) NULL,
    PRIMARY KEY (`comment_id`),
    INDEX `fk_comment_task1_idx` (`task_id` ASC) VISIBLE,
    CONSTRAINT `fk_comment_task1`
        FOREIGN KEY (`task_id`)
            REFERENCES `nhn_academy_43`.`tasks` (`task_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;



ALTER TABLE `nhn_academy_43`.`project_status`
    AUTO_INCREMENT = 1;
INSERT INTO project_status (`project_status_name`) value ('ACTIVE'), ('INACTIVE'), ('COMPLETE');
delete from project_status;
select * from project_status;
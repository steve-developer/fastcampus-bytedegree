-- -----------------------------------------------------
-- Schema fastcampus
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fastcampus` DEFAULT CHARACTER SET utf8 ;
USE `fastcampus` ;

-- -----------------------------------------------------
-- Table `fastcampus`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fastcampus`.`course` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL,
  `status` VARCHAR(50) NULL,
  `teacher_name` VARCHAR(50) NULL,
  `teacher_phone_number` VARCHAR(15) NULL,
  `teacher_email` VARCHAR(100) NULL,
  `amount` DECIMAL(17,4) NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '강좌';


-- -----------------------------------------------------
-- Table `fastcampus`.`course_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fastcampus`.`course_detail` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `course_id` BIGINT(20) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '강좌 상세 목록';


-- -----------------------------------------------------
-- Table `fastcampus`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fastcampus`.`student` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NULL,
  `phone_number` VARCHAR(15) NOT NULL,
  `registered_at` DATETIME NULL,
  `unregistered_at` DATETIME NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '학생';


-- -----------------------------------------------------
-- Table `fastcampus`.`apply_course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fastcampus`.`apply_course` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `student_id` BIGINT(20) NOT NULL,
  `course_id` BIGINT(20) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `progress_rate` FLOAT NULL,
  `is_complete` tinyint NULL,
  `expire_at` DATETIME NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '수강강좌';


-- -----------------------------------------------------
-- Table `fastcampus`.`apply_course_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fastcampus`.`apply_course_detail` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `apply_course_id` BIGINT(20) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL,
  `updated_by` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '수강목록';



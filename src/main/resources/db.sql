-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Employees
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Employees
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS Employees DEFAULT CHARACTER SET utf8 ;
USE Employees ;

-- -----------------------------------------------------
-- Table Employees.tblDepartments
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Employees.tblDepartments (
                                                        dpID INT NOT NULL AUTO_INCREMENT,
                                                        dpName VARCHAR(45) NOT NULL,
    PRIMARY KEY (dpID),
    UNIQUE INDEX dpID_UNIQUE (dpID ASC) VISIBLE,
    UNIQUE INDEX dpName_UNIQUE (dpName ASC) VISIBLE)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Employees.tblEmployees
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Employees.tblEmployees (
                                                      empID INT NOT NULL AUTO_INCREMENT,
                                                      empName VARCHAR(45) NOT NULL,
    empActive BIT(1) NOT NULL,
    emp_dpID INT NOT NULL,
    PRIMARY KEY (empID, emp_dpID),
    UNIQUE INDEX empID_UNIQUE (empID ASC) VISIBLE,
    INDEX fk_tblEmployees_tblDepartments_idx (emp_dpID ASC) VISIBLE,
    CONSTRAINT fk_tblEmployees_tblDepartments
    FOREIGN KEY (emp_dpID)
    REFERENCES Employees.tblDepartments (dpID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
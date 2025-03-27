-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema biblioteca_v2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema biblioteca_v2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `biblioteca_v2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `biblioteca_v2` ;

-- -----------------------------------------------------
-- Table `biblioteca_v2`.`autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_v2`.`autor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cidade` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `telefone` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_v2`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_v2`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_categoria` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_v2`.`emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_v2`.`emprestimo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_devolucao` DATE NOT NULL,
  `data_emprestimo` DATE NOT NULL,
  `esta_emprestado` BIT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_v2`.`leitor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_v2`.`leitor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `idade` INT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `sobrenome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_v2`.`emprestimo_para_leitor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_v2`.`emprestimo_para_leitor` (
  `emprestimo_id` INT NOT NULL,
  `leitor_id` INT NOT NULL,
  INDEX `fk_leitor_para_emprestimo_id` (`leitor_id` ASC) VISIBLE,
  INDEX `fk_emprestimo_para_leitor_id` (`emprestimo_id` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_para_leitor_id`
    FOREIGN KEY (`emprestimo_id`)
    REFERENCES `biblioteca_v2`.`emprestimo` (`id`),
  CONSTRAINT `fk_leitor_para_emprestimo_id`
    FOREIGN KEY (`leitor_id`)
    REFERENCES `biblioteca_v2`.`leitor` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_v2`.`livro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_v2`.`livro` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ano_lancamento` INT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `autor_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_autor_id` (`autor_id` ASC) VISIBLE,
  CONSTRAINT `fk_autor_id`
    FOREIGN KEY (`autor_id`)
    REFERENCES `biblioteca_v2`.`autor` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_v2`.`leitor_com_livro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_v2`.`leitor_com_livro` (
  `leitor_id` INT NOT NULL,
  `livro_id` INT NOT NULL,
  INDEX `fk_livro_id` (`livro_id` ASC) VISIBLE,
  INDEX `fk_leitor_id` (`leitor_id` ASC) VISIBLE,
  CONSTRAINT `fk_leitor_id`
    FOREIGN KEY (`leitor_id`)
    REFERENCES `biblioteca_v2`.`leitor` (`id`),
  CONSTRAINT `fk_livro_id`
    FOREIGN KEY (`livro_id`)
    REFERENCES `biblioteca_v2`.`livro` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_v2`.`livro_tem_categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_v2`.`livro_tem_categoria` (
  `livro_id` INT NOT NULL,
  `categoria_id` INT NOT NULL,
  INDEX `fk_categoria_para_livro` (`categoria_id` ASC) VISIBLE,
  INDEX `fk_livro_para_categoria_id` (`livro_id` ASC) VISIBLE,
  CONSTRAINT `fk_categoria_para_livro`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `biblioteca_v2`.`categoria` (`id`),
  CONSTRAINT `fk_livro_para_categoria_id`
    FOREIGN KEY (`livro_id`)
    REFERENCES `biblioteca_v2`.`livro` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `biblioteca_v2`.`livro_tem_emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biblioteca_v2`.`livro_tem_emprestimo` (
  `livro_id` INT NOT NULL,
  `emprestimo_id` INT NOT NULL,
  INDEX `fk_emprestimo_para_livro_id` (`emprestimo_id` ASC) VISIBLE,
  INDEX `fk_livro_para_emprestimo_id` (`livro_id` ASC) VISIBLE,
  CONSTRAINT `fk_emprestimo_para_livro_id`
    FOREIGN KEY (`emprestimo_id`)
    REFERENCES `biblioteca_v2`.`emprestimo` (`id`),
  CONSTRAINT `fk_livro_para_emprestimo_id`
    FOREIGN KEY (`livro_id`)
    REFERENCES `biblioteca_v2`.`livro` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

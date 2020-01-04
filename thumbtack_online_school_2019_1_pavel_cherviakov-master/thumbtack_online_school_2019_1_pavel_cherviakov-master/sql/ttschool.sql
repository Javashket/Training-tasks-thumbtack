DROP DATABASE IF EXISTS ttschool;
CREATE DATABASE `ttschool`;
USE `ttschool`;


CREATE TABLE school
(
    id   int      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name char(50) NOT NULL,
    year char(50),
    UNIQUE KEY school (name, year)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8;

CREATE TABLE groupp
(
    id        int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name      char(50),
    room      char(50),
    school_id int NOT NULL,
    FOREIGN KEY (school_id) REFERENCES school (id) ON DELETE CASCADE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8;

CREATE TABLE trainee
(
    id        int      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname char(50) NOT NULL,
    lastname  char(50) NOT NULL,
    rating    int      NOT NULL,
    group_id  int,
    FOREIGN KEY (group_id) REFERENCES groupp (id) ON DELETE SET NULL
) ENGINE = INNODB
  DEFAULT CHARSET = utf8;

CREATE TABLE subject
(
    id       int      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name     char(50) NOT NULL,
    group_id int,
    FOREIGN KEY (group_id) REFERENCES groupp (id) ON DELETE SET NULL
) ENGINE = INNODB
  DEFAULT CHARSET = utf8;
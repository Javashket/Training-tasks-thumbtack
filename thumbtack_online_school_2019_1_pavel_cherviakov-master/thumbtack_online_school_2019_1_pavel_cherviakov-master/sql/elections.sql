DROP DATABASE IF EXISTS elections;
CREATE DATABASE `elections`;
USE `elections`;

CREATE TABLE voter
(
    id         int      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    firstname  char(30) NOT NULL,
    lastname   char(30) NOT NULL,
    patronymic char(30),
    street     char(30) NOT NULL,
    house      char(30) NOT NULL,
    apartment  char(30),
    login      char(30) NOT NULL,
    password   char(30) NOT NULL,
    token      char(50),
    UNIQUE KEY voter (firstname,lastname,patronymic,street,house,apartment)
)
 ENGINE = INNODB
  DEFAULT CHARSET = utf8;

CREATE TABLE offer
(
    id       int      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    author_token char(50) ,
    average_rating double NOT NULL,
    content  char(50) NOT NULL
)
    ENGINE = INNODB
    DEFAULT CHARSET = utf8;

CREATE TABLE rating
(
    id       int      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    token_evaluating_voter char(50)      NOT NULL,
    rating  int NOT NULL,
    offer_id int      NOT NULL,
    UNIQUE KEY voter (id),
    FOREIGN KEY (offer_id) REFERENCES offer (id) ON DELETE CASCADE
)
    ENGINE = INNODB
    DEFAULT CHARSET = utf8;

CREATE TABLE mayor_candidate
(
    id       int      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    token_voter char(50)      NOT NULL,
    consentOnNomination  BOOLEAN NOT NULL
)
    ENGINE = INNODB
    DEFAULT CHARSET = utf8;

CREATE TABLE mayor_candidate_offer
(
    offer_id      int      NOT NULL,
    mayor_candidate_id      int      NOT NULL,
    FOREIGN KEY (offer_id) REFERENCES offer (id) ON DELETE CASCADE,
    FOREIGN KEY (mayor_candidate_id) REFERENCES mayor_candidate (id) ON DELETE CASCADE
)
    ENGINE = INNODB
    DEFAULT CHARSET = utf8;

CREATE TABLE vote
(
    id       int      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    voter_id int     NOT NULL,
    vote  BOOLEAN NOT NULL,
    mayor_candidate_id int NOT NULL ,
    FOREIGN KEY (voter_id) REFERENCES voter (id) ON DELETE CASCADE,
    FOREIGN KEY (mayor_candidate_id) REFERENCES mayor_candidate (id) ON DELETE CASCADE
)
    ENGINE = INNODB
    DEFAULT CHARSET = utf8;
-- liquibase formatted sql
-- changeset max:1 context:p1 splitStatement:false

CREATE TABLE USERS (
    ID                  BIGINT          NOT NULL AUTO_INCREMENT,
    NAME                VARCHAR(50),
    EMAIL               VARCHAR(50),
    PRIMARY KEY (ID)
);
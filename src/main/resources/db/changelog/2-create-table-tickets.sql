-- liquibase formatted sql
-- changeset max:2 context:p1 splitStatement:false

CREATE TABLE TICKETS (
    ID                  BIGINT          NOT NULL AUTO_INCREMENT,
    EVENT_NAME          VARCHAR(50),
    START_TIME          DATETIME,
    END_TIME            DATETIME,
    USER_NAME           VARCHAR(50),
    EMAIL               VARCHAR(50),
    PRIMARY KEY (ID)
);
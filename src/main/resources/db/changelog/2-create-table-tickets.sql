-- liquibase formatted sql
-- changeset max:2 context:p1 splitStatement:false

CREATE TABLE TICKET (
    ID                  BIGINT          NOT NULL AUTO_INCREMENT,
    EVENT_ID            BIGINT,
    EVENT_NAME          VARCHAR(50),
    START_DATE          DATETIME,
    END_DATE            DATETIME,
    USER_ID             BIGINT,
    USER_NAME           VARCHAR(50),
    EMAIL               VARCHAR(50),
    PRIMARY KEY (ID)
);
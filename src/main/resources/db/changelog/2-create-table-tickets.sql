-- liquibase formatted sql
-- changeset max:2 context:p1 splitStatement:false

CREATE TABLE TICKET (
    ID                  BIGINT          NOT NULL AUTO_INCREMENT,
    EVENT_ID            BIGINT,
    EVENT_NAME          VARCHAR(50),
    EVENT_DATE          DATE,
    USER_ID             BIGINT,
    USER_NAME           VARCHAR(50),
    EMAIL               VARCHAR(50),
    PRIMARY KEY (ID)
);
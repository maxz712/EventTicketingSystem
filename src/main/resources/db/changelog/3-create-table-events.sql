-- liquibase formatted sql
-- changeset max:3 context:p1 splitStatement:false

CREATE TABLE EVENT (
    ID                  BIGINT          NOT NULL AUTO_INCREMENT,
    NAME                VARCHAR(50),
    CREATE_DATE         DATE,
    EVENT_DATE          DATE,
    CURRENT_CAPACITY    BIGINT,
    MAX_CAPACITY        BIGINT,
    PRIMARY KEY (ID)
);
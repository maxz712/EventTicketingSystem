-- liquibase formatted sql
-- changeset max:3 context:p1 splitStatement:false

CREATE TABLE EVENT (
    ID                  BIGINT          NOT NULL AUTO_INCREMENT,
    NAME                VARCHAR(50),
    CREATE_TIME         DATETIME,
    START_TIME          DATETIME,
    END_TIME            DATETIME,
    CURRENT_CAPACITY    BIGINT,
    MAX_CAPACITY        BIGINT,
    PRIMARY KEY (ID)
);
package com.eventticketingsystem.constants;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueryConstants {

    public static final String SELECT_USER_BY_EMAIL =
            """
                SELECT
                    *
                FROM
                    USER
                WHERE
                    EMAIL = :email
            """;

    public static final String SELECT_UNEXPIRED_EVENTS =
            """
                SELECT
                    *
                FROM
                    EVENT
                WHERE
                    END_TIME > UTC_TIMESTAMP();
            """;

    public static final String SELECT_EVENT_BY_NAME =
            """
                SELECT
                    *
                FROM
                    EVENT
                WHERE
                        NAME = :name
                    AND END_TIME > UTC_TIMESTAMP();
            """;
}

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
                    USERS
                WHERE
                    EMAIL = :email
            """;

    public static final String SELECT_UNEXPIRED_EVENTS =
            """
                SELECT
                    *
                FROM
                    EVENTS
                WHERE
                    END_TIME > UTC_TIMESTAMP();
            """;

    public static final String SELECT_EVENT_BY_NAME =
            """
                SELECT
                    *
                FROM
                    EVENTS
                WHERE
                        NAME = :name
                    AND END_TIME > UTC_TIMESTAMP();
            """;

    public static final String SELECT_AVAILABLE_EVENT_BY_NAME =
            """
                SELECT
                    *
                FROM
                    EVENTS
                WHERE
                        NAME = :name
                    AND END_TIME > UTC_TIMESTAMP()
                    AND CURRENT_CAPACITY > 0;
            """;

    public static final String SELECT_UNEXPIRED_TICKET_BY_EMAIL =
            """
                SELECT
                    *
                FROM
                    TICKETS
                WHERE
                        EMAIL = :email
                    AND END_TIME > UTC_TIMESTAMP();
            """;
}

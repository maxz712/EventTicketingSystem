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
}

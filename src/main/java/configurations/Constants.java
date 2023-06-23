package configurations;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String DEFAULT_APP_FILE_NAME = "application.properties";

    public static final String CONNECTION_URL = "com.h2database.url";
    public static final String CONNECTION_USERNAME = "com.h2database.username";
    public static final String CONNECTION_PASSWORD = "com.h2database.password";
    public static final String CONNECTION_AUTOCOMMIT = "com.h2database.autocommit";
    public static final String TRANSACTION_ISOLATION = "com.h2database.isolation.level";
    public static final String FLYWAY_CONNECTION_URL = "org.flywaydb.url";
    public static final String FLYWAY_LOGGER = "org.flywaydb.logger";
    public static final String FLYWAY_USER = "org.flywaydb.user";
    public static final String FLYWAY_PASSWORD = "org.flywaydb.password";
    public static final String BASE_PACKAGE = "hibernate.entity.package";
}

package configurations.hibernate;

import configurations.Environment;
import entity.ClientEntity;
import entity.PlanetEntity;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class HibernateConfiguration {
    public static Configuration setup(Environment environment, Class<?>... classesToRegister) {
        Configuration configuration = new Configuration();
        String driverProperty = environment.getProperty(AvailableSettings.DRIVER);
        String urlProperty = environment.getProperty(AvailableSettings.URL);
        String showSqlProperty = environment.getProperty(AvailableSettings.SHOW_SQL);
        Optional<String> dialectProperty = environment.getPropertyOptional(AvailableSettings.DIALECT);
        Optional<String> userProperty = environment.getPropertyOptional(AvailableSettings.USER);
        Optional<String> passwordProperty = environment.getPropertyOptional(AvailableSettings.PASS);

        configuration.setProperty(AvailableSettings.DRIVER, driverProperty);
        configuration.setProperty(AvailableSettings.URL, urlProperty);
        configuration.setProperty(AvailableSettings.SHOW_SQL, showSqlProperty);

        dialectProperty.ifPresent(dialect -> configuration.setProperty(AvailableSettings.DIALECT, dialect));
        userProperty.ifPresent(userName -> configuration.setProperty(AvailableSettings.USER, userName));
        passwordProperty.ifPresent(password -> configuration.setProperty(AvailableSettings.PASS, password));

        configuration.addAnnotatedClass(ClientEntity.class);
        configuration.addAnnotatedClass(PlanetEntity.class);

        return configuration;
    }
}

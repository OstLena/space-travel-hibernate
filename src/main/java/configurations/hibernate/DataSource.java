package configurations.hibernate;

import configurations.Environment;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;

public class DataSource {
    private final Configuration configuration;
    private final SessionFactory sessionFactory;

    public DataSource(Environment environment, Class<?>... entities) {
        this.configuration = HibernateConfiguration.setup(environment, entities);
        this.sessionFactory = createSessionFactory();
    }

    public Session openSession() {
        return this.sessionFactory.openSession();
    }

    private SessionFactory createSessionFactory() {
        final ClassLoaderService classLoaderService = new ClassLoaderServiceImpl(getClass().getClassLoader());
        return configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .addService(ClassLoaderService.class, classLoaderService)
                        .build()
        );
    }
}

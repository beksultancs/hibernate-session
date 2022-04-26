package peaksoft.java5.configurations;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import peaksoft.java5.models.Client;

import java.util.Properties;

/**
 * @author Beksultan
 */
public class DatabaseConnection {

    public static SessionFactory createSessionFactory() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5678/postgres");
//        properties.put(Environment.USER, "postgres");
//        properties.put(Environment.PASS, "postgres");

        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL, "true");

        Configuration configuration = new Configuration();
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(Client.class);

        return configuration.buildSessionFactory();
    }

    public static EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("peaksoft");
    }
}

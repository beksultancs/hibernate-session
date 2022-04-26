package peaksoft.java5.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.java5.configurations.DatabaseConnection;
import peaksoft.java5.models.Client;

import java.util.List;
import java.util.Optional;

/**
 * @author Beksultan
 */
public class ClientRepository implements AutoCloseable {

    private SessionFactory sessionFactory = DatabaseConnection.createSessionFactory();
    private EntityManagerFactory entityManagerFactory = DatabaseConnection.createEntityManagerFactory();

    public void save(Client newClient) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(newClient);

        entityManager.getTransaction().commit();

        entityManager.close();
    }

    public Optional<Client> findByEmail(String email) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Client client = session.createQuery("select c from Client c where c.email = :email", Client.class)
                .setParameter("email", email)
                .getSingleResultOrNull();

        session.getTransaction().commit();

        session.close();

        return Optional.ofNullable(client);
    }

    public Boolean existsByEmail(String email) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select case when count(c) > 0 " +
                "then true " +
                "else false end " +
                "from Client c where c.email = :email", Boolean.class);

        query.setParameter("email", email);

        Boolean singleResult = (Boolean) query.getSingleResult();

        entityManager.getTransaction().commit();

        entityManager.close();

        return singleResult;
    }

    public List<Client> findAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Client> clients = entityManager.createQuery("select c from Client c", Client.class).getResultList();

        entityManager.getTransaction().commit();

        entityManager.close();

        return clients;
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
        entityManagerFactory.close();
    }
}

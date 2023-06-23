package repository;

import configurations.hibernate.DataSource;
import entity.ClientEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ClientRepository {
    private final DataSource datasource;

    public ClientRepository(DataSource datasource) {
        this.datasource = datasource;
    }

    public List<ClientEntity> findAll() {
        return dbCall(session -> session
                .createQuery("select c from ClientEntity c", ClientEntity.class)
                .getResultList());
    }

    public ClientEntity findById(Long id) {
        return dbCall(session -> {
            String queryString = "select c from ClientEntity c where c.id=:id";
            Query<ClientEntity> query = session.createQuery(queryString, ClientEntity.class);
            query.setParameter("id", id);
            ClientEntity result;
            try {
                result = query.getSingleResult();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("No result found for query");
                result = null;
            }
            return result;
        });
    }

    public ClientEntity save(ClientEntity entity) {
        dbVoidCall(session -> persist(entity, session));
        return entity;
    }

    public int deleteById(Long id) {
        return dbCall(session -> {
            String queryString = "delete from ClientEntity c where c.id=:id";
            MutationQuery mutationQuery = session.createMutationQuery(queryString);
            mutationQuery.setParameter("id", id);
            return mutationQuery.executeUpdate();
        });
    }

    public ClientEntity update(ClientEntity entity) {
        dbVoidCall(session -> persist(entity, session));
        return entity;
    }


    private ClientEntity persist(ClientEntity entity, Session session) {
        if (entity.getName().length() < 3 || entity.getName().length() > 200) {
            throw new RuntimeException("Illegal name " + entity.getName());
        }
        ClientEntity saved = session.merge(entity);
        entity.setId(saved.getId());
        return entity;
    }

    private <R> R dbCall(Function<Session, R> function) {
        try (Session session = datasource.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void dbVoidCall(Consumer<Session> function) {
        try (Session session = datasource.openSession()) {
            Transaction transaction = session.beginTransaction();
            function.accept(session);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

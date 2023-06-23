package repository;

import configurations.hibernate.DataSource;
import entity.PlanetEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class PlanetRepository {
    private final DataSource datasource;

    public PlanetRepository(DataSource datasource) {
        this.datasource = datasource;
    }

    public List<PlanetEntity> findAll() {
        return dbCall(session -> session
                .createQuery("select p from PlanetEntity p", PlanetEntity.class)
                .getResultList());
    }

    public PlanetEntity findById(String id) {
        return dbCall(session -> {
            String queryString = "select p from PlanetEntity p where p.id=:id";
            Query<PlanetEntity> query = session.createQuery(queryString, PlanetEntity.class);
            query.setParameter("id", id);
            PlanetEntity result;
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

    public PlanetEntity save(PlanetEntity entity) {
        dbVoidCall(session -> persist(entity, session));
        return entity;
    }

    public int deleteById(String id) {
        return dbCall(session -> {
            String queryString = "delete from PlanetEntity p where p.id=:id";
            MutationQuery mutationQuery = session.createMutationQuery(queryString);
            mutationQuery.setParameter("id", id);
            return mutationQuery.executeUpdate();
        });
    }

    public PlanetEntity update(PlanetEntity entity) {
        dbVoidCall(session -> persist(entity, session));
        return entity;
    }

    private PlanetEntity persist(PlanetEntity entity, Session session) {
        if (entity.getName().length() < 1 || entity.getName().length() > 500) {
            throw new RuntimeException("Illegal name " + entity.getName());
        }

        if (entity.getId().length() < 1 || !entity.getId().matches("\\p{Lu}")) {
            throw new RuntimeException("Illegal id " + entity.getId());
        }
        PlanetEntity saved = session.merge(entity);
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

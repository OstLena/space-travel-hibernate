package repository;

import configurations.hibernate.DataSource;
import entity.TicketEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class TicketRepository {
    private final DataSource datasource;

    public TicketRepository(DataSource datasource) {
        this.datasource = datasource;
    }

    public List<TicketEntity> findAll() {
        return dbCall(session -> session
                .createQuery("select t from TicketEntity t", TicketEntity.class)
                .getResultList());
    }

    public TicketEntity findById(Long id) {
        return dbCall(session -> {
            String queryString = "select t from TicketEntity t where t.id=:id";
            Query<TicketEntity> query = session.createQuery(queryString, TicketEntity.class);
            query.setParameter("id", id);
            TicketEntity result;
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

    public TicketEntity save(TicketEntity entity) {
        dbVoidCall(session -> persist(entity, session));
        return entity;
    }

    public int deleteById(Long id) {
        return dbCall(session -> {
            String queryString = "delete from TicketEntity t where t.id=:id";
            MutationQuery mutationQuery = session.createMutationQuery(queryString);
            mutationQuery.setParameter("id", id);
            return mutationQuery.executeUpdate();
        });
    }

    public TicketEntity update(TicketEntity entity) {
        dbVoidCall(session -> persist(entity, session));
        return entity;
    }


    private TicketEntity persist(TicketEntity entity, Session session) {
        TicketEntity saved = session.merge(entity);
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

package customer.project.dao;

import customer.project.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private final SessionFactory sessionFactory;

    public CustomerDaoImpl(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    @Override
    public Customer create(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            return customer;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert customer with id: "
                    + customer.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Customer> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Customer.class, id));
        } catch (Exception e) {
            throw new RuntimeException("Can't get a customer by id: " + id, e);
        }
    }

    @Override
    public List<Customer> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Customer> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Customer.class);
            criteriaQuery.from(Customer.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all customers", e);
        }
    }

    @Override
    public Customer update(Customer customer) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            return customer;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Could not update customer with id "
                    + customer.getId() + ". ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean delete(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("UPDATE customer SET is_active = true WHERE id =:id"
                                    + " and is_active = false")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Could not remove customer by id "
                    + id + ". ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

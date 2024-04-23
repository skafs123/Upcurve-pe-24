package com.targetindia.dao;

import com.targetindia.model.Customer;
import com.targetindia.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import org.hibernate.HibernateException;

import java.util.List;

public class JpaCustomerDao implements CustomerDao {

    @Override
    public String save(Customer customer) throws DaoException {
        try (EntityManager em = JpaUtil.createEntityManager()) {
            var tx = em.getTransaction();
            tx.begin();
            try {
                em.persist(customer);
                tx.commit();
                return customer.getId();
            } catch (Exception e) {
                tx.rollback();
                throw new DaoException(e);
            }
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Customer findById(String id) throws DaoException {
        try (EntityManager em = JpaUtil.createEntityManager()) {
            return em.find(Customer.class, id);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Customer customer) throws DaoException {
        try (EntityManager em = JpaUtil.createEntityManager()) {
            var tx = em.getTransaction();
            tx.begin();
            try {
                // at this time, `customer` is a detached/new entity
                em.merge(customer);
                // 1. a select statement is executed to fetch data of `customer.id`
                // 2. check if the retrieved data is any different from represented by `customer`
                // 3. if yes, then during commit, a sql update is fired
                // 4. else, nothing happens
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw new DaoException(e);
            }
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(String id) throws DaoException {

        try (EntityManager em = JpaUtil.createEntityManager()) {

            var customer = em.find(Customer.class, id);
            if (customer == null) {
                throw new DaoException("customer not found for given id");
            }
            var tx = em.getTransaction();
            tx.begin();
            try {
                em.remove(customer);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw new DaoException(e);
            }
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Customer> findAll() throws DaoException {
        try (EntityManager em = JpaUtil.createEntityManager()) {
            String jpql = "from Customer";
            var qry = em.createQuery(jpql, Customer.class);
            return qry.getResultList();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Customer> findAllByCity(String city) throws DaoException {
        try (EntityManager em = JpaUtil.createEntityManager()) {
            String jpql = "from Customer where city=?1";
            var qry = em.createQuery(jpql, Customer.class);
            qry.setParameter(1, city);
            return qry.getResultList();
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Customer findByEmail(String email) throws DaoException {
        try (EntityManager em = JpaUtil.createEntityManager()) {
            String jpql = "from Customer where email=?1";
            var qry = em.createQuery(jpql, Customer.class);
            qry.setParameter(1, email);
           return  qry.getResultList().get(0);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Customer findByPhone(String phone) throws DaoException {
        try (EntityManager em = JpaUtil.createEntityManager()) {
            String jpql = "from Customer where phone=?1";
            var qry = em.createQuery(jpql, Customer.class);
            qry.setParameter(1, phone);
            return  qry.getResultList().get(0);
        } catch (HibernateException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Customer> findByAgeGroup(Integer fromAge, Integer toAge) throws DaoException {
        return null;
    }
}

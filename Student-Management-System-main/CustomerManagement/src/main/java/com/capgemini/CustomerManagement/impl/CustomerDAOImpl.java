package com.capgemini.CustomerManagement.impl;

import com.capgemini.CustomerManagement.dao.CustomerDAO;
import com.capgemini.CustomerManagement.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CustomerDAOImpl implements CustomerDAO {

    private EntityManagerFactory emf;

    public CustomerDAOImpl() {
        // Initialize EntityManagerFactory with persistence unit name
        this.emf = Persistence.createEntityManagerFactory("PU02");
    }

    @Override
    public String saveCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(customer);
            transaction.commit();
            return "Customer saved successfully with ID: " + customer.getId();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return "Error saving customer: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public String updateCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Customer existingCustomer = em.find(Customer.class, customer.getId());
            if (existingCustomer != null) {
                em.merge(customer);
                transaction.commit();
                return "Customer updated successfully with ID: " + customer.getId();
            } else {
                transaction.rollback();
                return "Customer not found with ID: " + customer.getId();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return "Error updating customer: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public String deleteCustomerById(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                em.remove(customer);
                transaction.commit();
                return "Customer deleted successfully with ID: " + id;
            } else {
                transaction.rollback();
                return "Customer not found with ID: " + id;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return "Error deleting customer: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        } catch (Exception e) {
            System.err.println("Error fetching customer: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error fetching all customers: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            // JPQL Query to fetch customer by email
            TypedQuery<Customer> query = em.createQuery(
                    "SELECT c FROM Customer c WHERE c.email = :email", Customer.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error fetching customer by email: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    // Method to close EntityManagerFactory when done
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}

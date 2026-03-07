package com.capgemini.CustomerManagement.impl;

import com.capgemini.CustomerManagement.dao.OrderDAO;
import com.capgemini.CustomerManagement.entity.Order;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OrderDAOImpl implements OrderDAO {

    private EntityManagerFactory emf;

    public OrderDAOImpl() {
        // Initialize EntityManagerFactory with persistence unit name
        this.emf = Persistence.createEntityManagerFactory("PU02");
    }

    @Override
    public String saveOrder(Order order) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(order);
            transaction.commit();
            return "Order saved successfully with ID: " + order.getId();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return "Error saving order: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public String updateOrder(Order order) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Order existingOrder = em.find(Order.class, order.getId());
            if (existingOrder != null) {
                em.merge(order);
                transaction.commit();
                return "Order updated successfully with ID: " + order.getId();
            } else {
                transaction.rollback();
                return "Order not found with ID: " + order.getId();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return "Error updating order: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public String deleteOrderById(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Order order = em.find(Order.class, id);
            if (order != null) {
                em.remove(order);
                transaction.commit();
                return "Order deleted successfully with ID: " + id;
            } else {
                transaction.rollback();
                return "Order not found with ID: " + id;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return "Error deleting order: " + e.getMessage();
        } finally {
            em.close();
        }
    }

    @Override
    public Order getOrderById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Order order = em.find(Order.class, id);
            return order;
        } catch (Exception e) {
            System.err.println("Error fetching order: " + e.getMessage());
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

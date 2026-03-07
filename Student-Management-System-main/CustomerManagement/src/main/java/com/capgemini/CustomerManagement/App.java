package com.capgemini.CustomerManagement;

import com.capgemini.CustomerManagement.entity.Customer;
import com.capgemini.CustomerManagement.entity.Order;
import com.capgemini.CustomerManagement.impl.CustomerDAOImpl;
import com.capgemini.CustomerManagement.impl.OrderDAOImpl;
import java.time.LocalDate;
import java.util.List;

/**
 * Customer Management Application - Demonstrating Hibernate One-to-One Mapping
 */
public class App {
    public static void main(String[] args) {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        OrderDAOImpl orderDAO = new OrderDAOImpl();

        System.out.println("===== Customer Management System =====\n");

        // 1. INSERT: Create and save a new customer with order
        System.out.println("1. INSERT Operation - Saving new customer with order:");
        Customer customer1 = new Customer("John Doe", "john.doe@email.com", "Male", 1234567890L, LocalDate.now());
        Order order1 = new Order("ORD001", "Laptop", 1, 45000.00, LocalDate.now());
        customer1.setOrder(order1);
        order1.setCustomer(customer1);

        String saveResult = customerDAO.saveCustomer(customer1);
        System.out.println(saveResult);
        System.out.println();

        // 2. FETCH BY ID: Retrieve customer by ID
        System.out.println("2. FETCH BY ID Operation:");
        Customer fetchedCustomer = customerDAO.getCustomerById(customer1.getId());
        if (fetchedCustomer != null) {
            System.out.println("Fetched Customer: " + fetchedCustomer);
            if (fetchedCustomer.getOrder() != null) {
                System.out.println("Associated Order: " + fetchedCustomer.getOrder());
            }
        }
        System.out.println();

        // 3. UPDATE: Update customer details
        System.out.println("3. UPDATE Operation - Updating customer details:");
        customer1.setCustomerName("John Updated");
        customer1.setPhone(9876543210L);
        String updateResult = customerDAO.updateCustomer(customer1);
        System.out.println(updateResult);
        System.out.println();

        // 4. FETCH BY EMAIL: Retrieve customer by email (JPQL Query)
        System.out.println("4. FETCH BY EMAIL Operation (JPQL Query):");
        Customer customerByEmail = customerDAO.getCustomerByEmail("john.doe@email.com");
        if (customerByEmail != null) {
            System.out.println("Customer found by email: " + customerByEmail);
        } else {
            System.out.println("Customer not found with the given email.");
        }
        System.out.println();

        // 5. INSERT: Create another customer
        System.out.println("5. INSERT Operation - Saving another customer:");
        Customer customer2 = new Customer("Jane Smith", "jane.smith@email.com", "Female", 9988776655L, LocalDate.now());
        Order order2 = new Order("ORD002", "Smartphone", 2, 25000.00, LocalDate.now());
        customer2.setOrder(order2);
        order2.setCustomer(customer2);

        String saveResult2 = customerDAO.saveCustomer(customer2);
        System.out.println(saveResult2);
        System.out.println();

        // 6. FETCH ALL: Retrieve all customers
        System.out.println("6. FETCH ALL Operation - Getting all customers:");
        List<Customer> allCustomers = customerDAO.getAllCustomers();
        if (allCustomers != null && !allCustomers.isEmpty()) {
            System.out.println("Total customers: " + allCustomers.size());
            for (Customer c : allCustomers) {
                System.out.println(c);
            }
        }
        System.out.println();

        // 7. UPDATE ORDER: Update order details
        System.out.println("7. UPDATE ORDER Operation:");
        Order orderToUpdate = orderDAO.getOrderById(order1.getId());
        if (orderToUpdate != null) {
            orderToUpdate.setQuantity(2);
            orderToUpdate.setPrice(90000.00);
            String orderUpdateResult = orderDAO.updateOrder(orderToUpdate);
            System.out.println(orderUpdateResult);

            // Fetch updated order
            Order updatedOrder = orderDAO.getOrderById(order1.getId());
            System.out.println("Updated Order: " + updatedOrder);
        }
        System.out.println();

        // 8. DELETE: Delete customer by ID (will cascade delete order due to
        // CascadeType.ALL)
        System.out.println("8. DELETE Operation - Deleting customer by ID:");
        String deleteResult = customerDAO.deleteCustomerById(customer1.getId());
        System.out.println(deleteResult);
        System.out.println();

        // Verify deletion
        System.out.println("9. Verifying deletion:");
        Customer deletedCustomer = customerDAO.getCustomerById(customer1.getId());
        if (deletedCustomer == null) {
            System.out.println("Customer successfully deleted!");
        }

        // Close resources
        customerDAO.close();
        orderDAO.close();

        System.out.println("\n===== All operations completed successfully! =====");
    }
}

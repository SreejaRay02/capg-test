package com.capgemini.CustomerManagement.dao;

import com.capgemini.CustomerManagement.entity.Customer;
import java.util.List;

public interface CustomerDAO {

    String saveCustomer(Customer customer);

    String updateCustomer(Customer customer);

    String deleteCustomerById(int id);

    Customer getCustomerById(int id);

    List<Customer> getAllCustomers();

    Customer getCustomerByEmail(String email);
}

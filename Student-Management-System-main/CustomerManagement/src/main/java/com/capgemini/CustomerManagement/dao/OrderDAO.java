package com.capgemini.CustomerManagement.dao;

import com.capgemini.CustomerManagement.entity.Order;

public interface OrderDAO {

    String saveOrder(Order order);

    String updateOrder(Order order);

    String deleteOrderById(int id);

    Order getOrderById(int id);
}

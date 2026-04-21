package com.hotel.service;

import com.hotel.entity.Order;
import java.util.List;

public interface OrderService {

    boolean addOrder(Order order);

    boolean updateOrder(Order order);

    boolean deleteOrder(String roomId);

    Order getOrderById(String roomId);

    List<Order> getAllOrders();

    List<Order> getOrdersByPage(int pageNum, int pageSize);

    int getOrderCount();

    List<Order> getOrdersByUserName(String userName);

    boolean checkOut(String roomId);
}

package com.hotel.service;

import com.hotel.entity.Order;

import java.util.List;

/**
 * 订单服务接口
 *
 * @author hotel-management
 */
public interface OrderService {

    List<Order> findAll();

    Order findByRoomId(String roomId);

    boolean addOrder(Order order);

    boolean updateOrder(Order order);

    boolean deleteOrder(String roomId);
}

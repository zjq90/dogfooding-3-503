package com.hotel.service.impl;

import com.hotel.entity.Order;
import com.hotel.mapper.OrderMapper;
import com.hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单服务实现类
 *
 * @author hotel-management
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findAll() {
        return orderMapper.selectList(null);
    }

    @Override
    public Order findByRoomId(String roomId) {
        return orderMapper.selectByRoomId(roomId);
    }

    @Override
    public boolean addOrder(Order order) {
        return orderMapper.insert(order) > 0;
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderMapper.updateByRoomId(order) > 0;
    }

    @Override
    public boolean deleteOrder(String roomId) {
        return orderMapper.deleteByRoomId(roomId) > 0;
    }
}

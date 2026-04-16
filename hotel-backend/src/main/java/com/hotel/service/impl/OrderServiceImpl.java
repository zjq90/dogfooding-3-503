package com.hotel.service.impl;

import com.hotel.entity.Order;
import com.hotel.mapper.OrderMapper;
import com.hotel.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    public boolean addOrder(Order order) {
        return orderMapper.insert(order) > 0;
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderMapper.updateByPrimaryKey(order) > 0;
    }

    @Override
    public boolean deleteOrder(String roomId) {
        return orderMapper.deleteByPrimaryKey(roomId) > 0;
    }

    @Override
    public Order getOrderById(String roomId) {
        return orderMapper.selectByPrimaryKey(roomId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.selectAll();
    }

    @Override
    public List<Order> getOrdersByPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return orderMapper.selectByPage(offset, pageSize);
    }

    @Override
    public int getOrderCount() {
        return orderMapper.countAll();
    }

    @Override
    public List<Order> getOrdersByUserName(String userName) {
        return orderMapper.selectByUserName(userName);
    }

    @Override
    public boolean checkOut(String roomId) {
        return orderMapper.checkOut(roomId) > 0;
    }
}

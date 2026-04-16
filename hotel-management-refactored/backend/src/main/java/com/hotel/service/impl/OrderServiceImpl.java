package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.common.BusinessException;
import com.hotel.entity.Order;
import com.hotel.mapper.OrderMapper;
import com.hotel.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单服务实现类
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public List<Order> getOrdersByUserName(String userName) {
        return baseMapper.selectByUserName(userName);
    }

    @Override
    public List<Order> getActiveOrders() {
        return baseMapper.selectActiveOrders();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean checkout(String roomId, BigDecimal payment) {
        Order order = getById(roomId);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        if (order.getEndTime() != null) {
            throw new BusinessException("该房间已退房");
        }

        LocalDateTime endTime = LocalDateTime.now();
        BigDecimal change = payment.subtract(order.getPrepay());

        int result = baseMapper.checkout(roomId, endTime, payment, change);
        if (result > 0) {
            log.info("房间{}退房成功", roomId);
            return true;
        }
        return false;
    }
}

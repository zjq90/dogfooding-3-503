package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.entity.Order;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单服务接口
 *
 * @author hotel
 * @since 1.0.0
 */
public interface OrderService extends IService<Order> {

    /**
     * 根据用户名查询订单
     *
     * @param userName 用户名
     * @return 订单列表
     */
    List<Order> getOrdersByUserName(String userName);

    /**
     * 获取在住订单列表
     *
     * @return 在住订单列表
     */
    List<Order> getActiveOrders();

    /**
     * 退房结算
     *
     * @param roomId  房间ID
     * @param payment 实付款
     * @return true-成功，false-失败
     */
    boolean checkout(String roomId, BigDecimal payment);
}

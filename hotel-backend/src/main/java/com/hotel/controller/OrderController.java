package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Order;
import com.hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单控制器
 *
 * @author hotel-management
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public Result<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        return Result.success(orders);
    }

    @GetMapping("/detail/{roomId}")
    public Result<Order> getOrderByRoomId(@PathVariable String roomId) {
        Order order = orderService.findByRoomId(roomId);
        if (order != null) {
            return Result.success(order);
        }
        return Result.error("订单不存在");
    }

    @PostMapping("/add")
    public Result<String> addOrder(@RequestBody Order order) {
        if (orderService.addOrder(order)) {
            return Result.success("入住成功");
        }
        return Result.error("入住失败");
    }

    @PutMapping("/update")
    public Result<String> updateOrder(@RequestBody Order order) {
        if (orderService.updateOrder(order)) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    @DeleteMapping("/delete/{roomId}")
    public Result<String> deleteOrder(@PathVariable String roomId) {
        if (orderService.deleteOrder(roomId)) {
            return Result.success("退房成功");
        }
        return Result.error("退房失败");
    }
}

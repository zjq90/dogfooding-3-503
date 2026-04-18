package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Order;
import com.hotel.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 订单控制器
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 获取所有订单
     */
    @GetMapping("/list")
    public Result<List<Order>> list() {
        List<Order> list = orderService.list();
        return Result.success(list);
    }

    /**
     * 根据用户名获取订单
     */
    @GetMapping("/user/{userName}")
    public Result<List<Order>> getByUserName(@PathVariable String userName) {
        List<Order> list = orderService.getOrdersByUserName(userName);
        return Result.success(list);
    }

    /**
     * 获取在住订单
     */
    @GetMapping("/active")
    public Result<List<Order>> getActiveOrders() {
        List<Order> list = orderService.getActiveOrders();
        return Result.success(list);
    }

    /**
     * 根据房间ID获取订单
     */
    @GetMapping("/{roomId}")
    public Result<Order> getByRoomId(@PathVariable String roomId) {
        Order order = orderService.getById(roomId);
        if (order == null) {
            return Result.error("订单不存在");
        }
        return Result.success(order);
    }

    /**
     * 添加入住订单
     */
    @PostMapping
    public Result<Void> save(@Valid @RequestBody Order order) {
        order.setStartTime(LocalDateTime.now());
        order.setSerNum(UUID.randomUUID().toString().replace("-", ""));
        boolean success = orderService.save(order);
        if (success) {
            return Result.success("入住登记成功");
        } else {
            return Result.error("入住登记失败");
        }
    }

    /**
     * 退房结算
     */
    @PostMapping("/checkout/{roomId}")
    public Result<Void> checkout(@PathVariable String roomId,
                                  @RequestParam BigDecimal payment) {
        boolean success = orderService.checkout(roomId, payment);
        if (success) {
            return Result.success("退房结算成功");
        } else {
            return Result.error("退房结算失败");
        }
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/{roomId}")
    public Result<Void> delete(@PathVariable String roomId) {
        boolean success = orderService.removeById(roomId);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}

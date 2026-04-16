package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Order;
import com.hotel.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getOrderList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<Order> list = orderService.getOrdersByPage(pageNum, pageSize);
        int total = orderService.getOrderCount();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("total", total);
        return Result.success(data);
    }

    @GetMapping("/user/{userName}")
    public Result<List<Order>> getOrdersByUserName(@PathVariable String userName) {
        List<Order> list = orderService.getOrdersByUserName(userName);
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Order> getOrderById(@PathVariable String id) {
        Order order = orderService.getOrderById(id);
        return Result.success(order);
    }

    @PostMapping("/add")
    public Result<String> addOrder(@RequestBody Order order) {
        boolean success = orderService.addOrder(order);
        return success ? Result.success("入住成功") : Result.error("入住失败");
    }

    @PutMapping("/update")
    public Result<String> updateOrder(@RequestBody Order order) {
        boolean success = orderService.updateOrder(order);
        return success ? Result.success("更新成功") : Result.error("更新失败");
    }

    @PostMapping("/checkout/{roomId}")
    public Result<String> checkOut(@PathVariable String roomId) {
        boolean success = orderService.checkOut(roomId);
        return success ? Result.success("退房成功") : Result.error("退房失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteOrder(@PathVariable String id) {
        boolean success = orderService.deleteOrder(id);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }
}

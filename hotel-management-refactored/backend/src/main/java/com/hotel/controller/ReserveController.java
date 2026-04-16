package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Reserve;
import com.hotel.service.ReserveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 预订控制器
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/reserve")
@RequiredArgsConstructor
public class ReserveController {

    private final ReserveService reserveService;

    /**
     * 获取所有预订
     */
    @GetMapping("/list")
    public Result<List<Reserve>> list() {
        List<Reserve> list = reserveService.list();
        return Result.success(list);
    }

    /**
     * 根据用户名获取预订
     */
    @GetMapping("/user/{userName}")
    public Result<List<Reserve>> getByUserName(@PathVariable String userName) {
        List<Reserve> list = reserveService.getReservesByUserName(userName);
        return Result.success(list);
    }

    /**
     * 根据房间ID获取预订
     */
    @GetMapping("/{roomId}")
    public Result<Reserve> getByRoomId(@PathVariable String roomId) {
        Reserve reserve = reserveService.getById(roomId);
        if (reserve == null) {
            return Result.error("预订不存在");
        }
        return Result.success(reserve);
    }

    /**
     * 预订房间
     */
    @PostMapping
    public Result<Void> reserve(@Valid @RequestBody Reserve reserve) {
        boolean success = reserveService.reserveRoom(reserve);
        if (success) {
            return Result.success("预订成功");
        } else {
            return Result.error("预订失败");
        }
    }

    /**
     * 取消预订
     */
    @DeleteMapping("/{roomId}")
    public Result<Void> cancel(@PathVariable String roomId) {
        boolean success = reserveService.removeById(roomId);
        if (success) {
            return Result.success("取消预订成功");
        } else {
            return Result.error("取消预订失败");
        }
    }
}

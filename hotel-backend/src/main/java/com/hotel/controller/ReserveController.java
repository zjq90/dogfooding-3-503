package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Reserve;
import com.hotel.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 预定控制器
 *
 * @author hotel-management
 */
@RestController
@RequestMapping("/api/reserve")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @GetMapping("/list")
    public Result<List<Reserve>> getAllReserves() {
        List<Reserve> reserves = reserveService.findAll();
        return Result.success(reserves);
    }

    @PostMapping("/add")
    public Result<String> addReserve(@RequestBody Reserve reserve) {
        if (reserveService.isReserved(reserve.getRoomId())) {
            return Result.error("该房间已被预定或入住");
        }
        if (reserveService.addReserve(reserve)) {
            return Result.success("预定成功");
        }
        return Result.error("预定失败");
    }

    @DeleteMapping("/delete/{roomId}")
    public Result<String> deleteReserve(@PathVariable String roomId) {
        if (reserveService.deleteReserve(roomId)) {
            return Result.success("取消预定成功");
        }
        return Result.error("取消预定失败");
    }

    @GetMapping("/check/{roomId}")
    public Result<Boolean> checkReserved(@PathVariable String roomId) {
        return Result.success(reserveService.isReserved(roomId));
    }
}

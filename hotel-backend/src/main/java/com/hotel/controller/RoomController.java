package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 房间控制器
 *
 * @author hotel-management
 */
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/list")
    public Result<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.findAll();
        return Result.success(rooms);
    }

    @GetMapping("/detail/{roomId}")
    public Result<Room> getRoomDetail(@PathVariable String roomId) {
        Room room = roomService.findByRoomId(roomId);
        if (room != null) {
            return Result.success(room);
        }
        return Result.error("房间不存在");
    }

    @GetMapping("/type/{roomType}")
    public Result<List<Room>> getRoomsByType(@PathVariable String roomType) {
        List<Room> rooms = roomService.findByRoomType(roomType);
        return Result.success(rooms);
    }

    @GetMapping("/price")
    public Result<List<Room>> getRoomsByPrice(@RequestParam BigDecimal startPrice, @RequestParam BigDecimal endPrice) {
        List<Room> rooms = roomService.findByPriceRange(startPrice, endPrice);
        return Result.success(rooms);
    }

    @GetMapping("/available")
    public Result<List<Room>> getAvailableRooms() {
        List<Room> rooms = roomService.findAvailableRooms();
        return Result.success(rooms);
    }

    @PostMapping("/add")
    public Result<String> addRoom(@RequestBody Room room) {
        if (roomService.existsByRoomId(room.getRoomId())) {
            return Result.error("房间号已存在");
        }
        if (roomService.addRoom(room)) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    @PutMapping("/update")
    public Result<String> updateRoom(@RequestBody Room room) {
        if (roomService.updateRoom(room)) {
            return Result.success("更新成功");
        }
        return Result.error("更新失败");
    }

    @DeleteMapping("/delete/{roomId}")
    public Result<String> deleteRoom(@PathVariable String roomId) {
        if (roomService.deleteRoom(roomId)) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}

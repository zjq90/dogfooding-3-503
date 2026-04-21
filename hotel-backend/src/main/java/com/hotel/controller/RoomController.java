package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getRoomList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<Room> list = roomService.getRoomsByPage(pageNum, pageSize);
        int total = roomService.getRoomCount();
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        data.put("total", total);
        return Result.success(data);
    }

    @GetMapping("/all")
    public Result<List<Room>> getAllRooms() {
        List<Room> list = roomService.getAllRooms();
        return Result.success(list);
    }

    @GetMapping("/available")
    public Result<List<Room>> getAvailableRooms() {
        List<Room> list = roomService.getAvailableRooms();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Room> getRoomById(@PathVariable String id) {
        Room room = roomService.getRoomById(id);
        return Result.success(room);
    }

    @GetMapping("/type/{type}")
    public Result<List<Room>> getRoomsByType(@PathVariable String type) {
        List<Room> list = roomService.getRoomsByType(type);
        return Result.success(list);
    }

    @PostMapping("/add")
    public Result<String> addRoom(@RequestBody Room room) {
        boolean success = roomService.addRoom(room);
        return success ? Result.success("添加成功") : Result.error("添加失败");
    }

    @PutMapping("/update")
    public Result<String> updateRoom(@RequestBody Room room) {
        boolean success = roomService.updateRoom(room);
        return success ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteRoom(@PathVariable String id) {
        boolean success = roomService.deleteRoom(id);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }
}

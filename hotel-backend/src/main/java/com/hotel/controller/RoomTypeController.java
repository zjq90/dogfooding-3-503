package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.RoomType;
import com.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 房间类型控制器
 *
 * @author hotel-management
 */
@RestController
@RequestMapping("/api/roomType")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/list")
    public Result<List<RoomType>> getAllRoomTypes() {
        List<RoomType> roomTypes = roomTypeService.findAll();
        return Result.success(roomTypes);
    }

    @PostMapping("/add")
    public Result<String> addRoomType(@RequestBody RoomType roomType) {
        if (roomTypeService.addRoomType(roomType)) {
            return Result.success("添加成功");
        }
        return Result.error("添加失败");
    }

    @DeleteMapping("/delete/{typeId}")
    public Result<String> deleteRoomType(@PathVariable Integer typeId) {
        if (roomTypeService.deleteRoomType(typeId)) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }
}

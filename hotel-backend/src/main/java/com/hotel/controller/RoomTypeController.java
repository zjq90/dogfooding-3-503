package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.RoomType;
import com.hotel.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-type")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    @GetMapping("/list")
    public Result<List<RoomType>> getRoomTypeList() {
        List<RoomType> list = roomTypeService.getAllRoomTypes();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<RoomType> getRoomTypeById(@PathVariable Long id) {
        RoomType roomType = roomTypeService.getRoomTypeById(id);
        return Result.success(roomType);
    }

    @PostMapping("/add")
    public Result<String> addRoomType(@RequestBody RoomType roomType) {
        boolean success = roomTypeService.addRoomType(roomType);
        return success ? Result.success("添加成功") : Result.error("添加失败");
    }

    @PutMapping("/update")
    public Result<String> updateRoomType(@RequestBody RoomType roomType) {
        boolean success = roomTypeService.updateRoomType(roomType);
        return success ? Result.success("更新成功") : Result.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteRoomType(@PathVariable Long id) {
        boolean success = roomTypeService.deleteRoomType(id);
        return success ? Result.success("删除成功") : Result.error("删除失败");
    }
}

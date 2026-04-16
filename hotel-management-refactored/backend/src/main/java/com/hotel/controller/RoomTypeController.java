package com.hotel.controller;

import com.hotel.common.Result;
import com.hotel.entity.RoomType;
import com.hotel.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 房间类型控制器
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/room-type")
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    /**
     * 获取所有房间类型
     */
    @GetMapping("/list")
    public Result<List<RoomType>> list() {
        List<RoomType> list = roomTypeService.list();
        return Result.success(list);
    }

    /**
     * 根据ID获取房间类型
     */
    @GetMapping("/{typeId}")
    public Result<RoomType> getById(@PathVariable Integer typeId) {
        RoomType roomType = roomTypeService.getById(typeId);
        if (roomType == null) {
            return Result.error("房间类型不存在");
        }
        return Result.success(roomType);
    }

    /**
     * 添加房间类型
     */
    @PostMapping
    public Result<Void> save(@Valid @RequestBody RoomType roomType) {
        boolean success = roomTypeService.save(roomType);
        if (success) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    /**
     * 更新房间类型
     */
    @PutMapping("/{typeId}")
    public Result<Void> update(@PathVariable Integer typeId, @Valid @RequestBody RoomType roomType) {
        roomType.setTypeId(typeId);
        boolean success = roomTypeService.updateById(roomType);
        if (success) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    /**
     * 删除房间类型
     */
    @DeleteMapping("/{typeId}")
    public Result<Void> delete(@PathVariable Integer typeId) {
        boolean success = roomTypeService.removeById(typeId);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}

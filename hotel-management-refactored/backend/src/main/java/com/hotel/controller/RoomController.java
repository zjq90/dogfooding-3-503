package com.hotel.controller;

import com.hotel.common.PageQuery;
import com.hotel.common.PageResult;
import com.hotel.common.Result;
import com.hotel.entity.Room;
import com.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 房间控制器
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    /**
     * 获取所有房间
     */
    @GetMapping("/list")
    public Result<List<Room>> list() {
        List<Room> list = roomService.list();
        return Result.success(list);
    }

    /**
     * 分页查询房间
     */
    @GetMapping("/page")
    public Result<PageResult<Room>> page(PageQuery query) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Room> mpPage =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(query.getCurrent(), query.getSize());
        com.baomidou.mybatisplus.core.metadata.IPage<Room> page = roomService.page(mpPage);
        PageResult<Room> result = new PageResult<>(
                page.getTotal(),
                page.getRecords(),
                page.getCurrent(),
                page.getSize()
        );
        return Result.success(result);
    }

    /**
     * 根据ID获取房间
     */
    @GetMapping("/{roomId}")
    public Result<Room> getById(@PathVariable String roomId) {
        Room room = roomService.getById(roomId);
        if (room == null) {
            return Result.error("房间不存在");
        }
        return Result.success(room);
    }

    /**
     * 根据类型获取房间
     */
    @GetMapping("/type/{roomType}")
    public Result<List<Room>> getByType(@PathVariable String roomType) {
        List<Room> list = roomService.getRoomsByType(roomType);
        return Result.success(list);
    }

    /**
     * 获取可预订房间
     */
    @GetMapping("/available")
    public Result<List<Room>> getAvailable() {
        List<Room> list = roomService.getAvailableRooms();
        return Result.success(list);
    }

    /**
     * 添加房间
     */
    @PostMapping
    public Result<Void> save(@Valid @RequestBody Room room) {
        boolean success = roomService.save(room);
        if (success) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    /**
     * 更新房间
     */
    @PutMapping("/{roomId}")
    public Result<Void> update(@PathVariable String roomId, @Valid @RequestBody Room room) {
        room.setRoomId(roomId);
        boolean success = roomService.updateById(room);
        if (success) {
            return Result.success("更新成功");
        } else {
            return Result.error("更新失败");
        }
    }

    /**
     * 删除房间
     */
    @DeleteMapping("/{roomId}")
    public Result<Void> delete(@PathVariable String roomId) {
        boolean success = roomService.removeById(roomId);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }
    }
}

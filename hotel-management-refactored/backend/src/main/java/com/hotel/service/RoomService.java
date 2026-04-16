package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.entity.Room;

import java.util.List;

/**
 * 房间服务接口
 *
 * @author hotel
 * @since 1.0.0
 */
public interface RoomService extends IService<Room> {

    /**
     * 根据房间类型查询房间
     *
     * @param roomType 房间类型
     * @return 房间列表
     */
    List<Room> getRoomsByType(String roomType);

    /**
     * 获取可预订房间列表
     *
     * @return 可预订房间列表
     */
    List<Room> getAvailableRooms();
}

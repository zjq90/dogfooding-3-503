package com.hotel.service;

import com.hotel.entity.RoomType;

import java.util.List;

/**
 * 房间类型服务接口
 *
 * @author hotel-management
 */
public interface RoomTypeService {

    List<RoomType> findAll();

    boolean addRoomType(RoomType roomType);

    boolean deleteRoomType(Integer typeId);
}

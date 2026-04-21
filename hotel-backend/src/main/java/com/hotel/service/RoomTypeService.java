package com.hotel.service;

import com.hotel.entity.RoomType;
import java.util.List;

public interface RoomTypeService {

    boolean addRoomType(RoomType roomType);

    boolean updateRoomType(RoomType roomType);

    boolean deleteRoomType(Long typeId);

    RoomType getRoomTypeById(Long typeId);

    List<RoomType> getAllRoomTypes();
}

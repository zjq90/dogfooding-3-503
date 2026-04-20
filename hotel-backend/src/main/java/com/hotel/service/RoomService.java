package com.hotel.service;

import com.hotel.entity.Room;

import java.math.BigDecimal;
import java.util.List;

/**
 * 房间服务接口
 *
 * @author hotel-management
 */
public interface RoomService {

    List<Room> findAll();

    Room findByRoomId(String roomId);

    List<Room> findByRoomType(String roomType);

    List<Room> findByPriceRange(BigDecimal startPrice, BigDecimal endPrice);

    boolean existsByRoomId(String roomId);

    boolean addRoom(Room room);

    boolean updateRoom(Room room);

    boolean deleteRoom(String roomId);

    List<Room> findAvailableRooms();
}

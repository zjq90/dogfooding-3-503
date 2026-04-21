package com.hotel.service;

import com.hotel.entity.Room;
import java.util.List;

public interface RoomService {

    boolean addRoom(Room room);

    boolean updateRoom(Room room);

    boolean deleteRoom(String roomId);

    Room getRoomById(String roomId);

    List<Room> getAllRooms();

    List<Room> getRoomsByPage(int pageNum, int pageSize);

    int getRoomCount();

    List<Room> getRoomsByType(String roomType);

    List<Room> getAvailableRooms();
}

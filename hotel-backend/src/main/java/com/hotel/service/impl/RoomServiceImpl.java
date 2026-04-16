package com.hotel.service.impl;

import com.hotel.entity.Room;
import com.hotel.mapper.RoomMapper;
import com.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;

    @Override
    public boolean addRoom(Room room) {
        return roomMapper.insert(room) > 0;
    }

    @Override
    public boolean updateRoom(Room room) {
        return roomMapper.updateByPrimaryKey(room) > 0;
    }

    @Override
    public boolean deleteRoom(String roomId) {
        return roomMapper.deleteByPrimaryKey(roomId) > 0;
    }

    @Override
    public Room getRoomById(String roomId) {
        return roomMapper.selectByPrimaryKey(roomId);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomMapper.selectAll();
    }

    @Override
    public List<Room> getRoomsByPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return roomMapper.selectByPage(offset, pageSize);
    }

    @Override
    public int getRoomCount() {
        return roomMapper.countAll();
    }

    @Override
    public List<Room> getRoomsByType(String roomType) {
        return roomMapper.selectByType(roomType);
    }

    @Override
    public List<Room> getAvailableRooms() {
        return roomMapper.selectAvailableRooms();
    }
}

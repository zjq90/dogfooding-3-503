package com.hotel.service.impl;

import com.hotel.entity.Room;
import com.hotel.mapper.RoomMapper;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 房间服务实现类
 *
 * @author hotel-management
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<Room> findAll() {
        return roomMapper.selectList(null);
    }

    @Override
    public Room findByRoomId(String roomId) {
        return roomMapper.selectById(roomId);
    }

    @Override
    public List<Room> findByRoomType(String roomType) {
        return roomMapper.selectByRoomType(roomType);
    }

    @Override
    public List<Room> findByPriceRange(BigDecimal startPrice, BigDecimal endPrice) {
        return roomMapper.selectByPriceRange(startPrice, endPrice);
    }

    @Override
    public boolean existsByRoomId(String roomId) {
        return roomMapper.existsByRoomId(roomId) > 0;
    }

    @Override
    public boolean addRoom(Room room) {
        return roomMapper.insert(room) > 0;
    }

    @Override
    public boolean updateRoom(Room room) {
        return roomMapper.updateById(room) > 0;
    }

    @Override
    public boolean deleteRoom(String roomId) {
        return roomMapper.deleteById(roomId) > 0;
    }

    @Override
    public List<Room> findAvailableRooms() {
        return roomMapper.selectAvailableRooms();
    }
}

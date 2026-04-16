package com.hotel.service.impl;

import com.hotel.entity.RoomType;
import com.hotel.mapper.RoomTypeMapper;
import com.hotel.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeMapper roomTypeMapper;

    @Override
    public boolean addRoomType(RoomType roomType) {
        return roomTypeMapper.insert(roomType) > 0;
    }

    @Override
    public boolean updateRoomType(RoomType roomType) {
        return roomTypeMapper.updateByPrimaryKey(roomType) > 0;
    }

    @Override
    public boolean deleteRoomType(Long typeId) {
        return roomTypeMapper.deleteByPrimaryKey(typeId) > 0;
    }

    @Override
    public RoomType getRoomTypeById(Long typeId) {
        return roomTypeMapper.selectByPrimaryKey(typeId);
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        return roomTypeMapper.selectAll();
    }
}

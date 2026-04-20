package com.hotel.service.impl;

import com.hotel.entity.RoomType;
import com.hotel.mapper.RoomTypeMapper;
import com.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 房间类型服务实现类
 *
 * @author hotel-management
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Override
    public List<RoomType> findAll() {
        return roomTypeMapper.selectList(null);
    }

    @Override
    public boolean addRoomType(RoomType roomType) {
        return roomTypeMapper.insert(roomType) > 0;
    }

    @Override
    public boolean deleteRoomType(Integer typeId) {
        return roomTypeMapper.deleteById(typeId) > 0;
    }
}

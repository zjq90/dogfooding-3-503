package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.entity.Room;
import com.hotel.mapper.RoomMapper;
import com.hotel.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 房间服务实现类
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Override
    public List<Room> getRoomsByType(String roomType) {
        return baseMapper.selectByRoomType(roomType);
    }

    @Override
    public List<Room> getAvailableRooms() {
        return baseMapper.selectAvailableRooms();
    }
}

package com.hotel.service.impl;

import com.hotel.entity.Reserve;
import com.hotel.mapper.OrderMapper;
import com.hotel.mapper.ReserveMapper;
import com.hotel.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 预定服务实现类
 *
 * @author hotel-management
 */
@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveMapper reserveMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Reserve> findAll() {
        return reserveMapper.selectList(null);
    }

    @Override
    public boolean addReserve(Reserve reserve) {
        return reserveMapper.insert(reserve) > 0;
    }

    @Override
    public boolean deleteReserve(String roomId) {
        return reserveMapper.deleteByRoomId(roomId) > 0;
    }

    @Override
    public boolean isReserved(String roomId) {
        boolean isBooked = reserveMapper.countByRoomId(roomId) > 0;
        boolean hasOrder = orderMapper.selectByRoomId(roomId) != null;
        return isBooked || hasOrder;
    }
}

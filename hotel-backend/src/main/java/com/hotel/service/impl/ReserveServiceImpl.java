package com.hotel.service.impl;

import com.hotel.entity.Reserve;
import com.hotel.mapper.ReserveMapper;
import com.hotel.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveServiceImpl implements ReserveService {

    private final ReserveMapper reserveMapper;

    @Override
    public boolean addReserve(Reserve reserve) {
        return reserveMapper.insert(reserve) > 0;
    }

    @Override
    public boolean updateReserve(Reserve reserve) {
        return reserveMapper.updateByPrimaryKey(reserve) > 0;
    }

    @Override
    public boolean deleteReserve(String roomId) {
        return reserveMapper.deleteByPrimaryKey(roomId) > 0;
    }

    @Override
    public Reserve getReserveById(String roomId) {
        return reserveMapper.selectByPrimaryKey(roomId);
    }

    @Override
    public List<Reserve> getAllReserves() {
        return reserveMapper.selectAll();
    }

    @Override
    public List<Reserve> getReservesByPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return reserveMapper.selectByPage(offset, pageSize);
    }

    @Override
    public int getReserveCount() {
        return reserveMapper.countAll();
    }

    @Override
    public List<Reserve> getReservesByUserName(String userName) {
        return reserveMapper.selectByUserName(userName);
    }
}

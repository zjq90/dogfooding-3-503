package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.common.BusinessException;
import com.hotel.entity.Reserve;
import com.hotel.mapper.ReserveMapper;
import com.hotel.service.ReserveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 预订服务实现类
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@Service
public class ReserveServiceImpl extends ServiceImpl<ReserveMapper, Reserve> implements ReserveService {

    @Override
    public List<Reserve> getReservesByUserName(String userName) {
        return baseMapper.selectByUserName(userName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean reserveRoom(Reserve reserve) {
        // 检查房间是否已被预订
        Reserve existing = baseMapper.selectByRoomId(reserve.getRoomId());
        if (existing != null) {
            throw new BusinessException("该房间已被预订");
        }

        reserve.setPresetTime(LocalDateTime.now());
        boolean success = save(reserve);
        if (success) {
            log.info("房间{}预订成功，预订人：{}", reserve.getRoomId(), reserve.getUserName());
        }
        return success;
    }
}

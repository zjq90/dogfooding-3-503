package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Reserve;
import org.apache.ibatis.annotations.Param;

/**
 * 预定数据访问层
 *
 * @author hotel-management
 */
public interface ReserveMapper extends BaseMapper<Reserve> {

    int deleteByRoomId(@Param("roomId") String roomId);

    int countByRoomId(@Param("roomId") String roomId);
}

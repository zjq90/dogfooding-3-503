package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单数据访问层
 *
 * @author hotel-management
 */
public interface OrderMapper extends BaseMapper<Order> {

    Order selectByRoomId(@Param("roomId") String roomId);

    int updateByRoomId(Order order);

    int deleteByRoomId(@Param("roomId") String roomId);
}

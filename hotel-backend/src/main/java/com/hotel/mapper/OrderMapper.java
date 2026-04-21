package com.hotel.mapper;

import com.hotel.entity.Order;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface OrderMapper {

    int insert(Order order);

    int updateByPrimaryKey(Order order);

    int deleteByPrimaryKey(String roomId);

    Order selectByPrimaryKey(String roomId);

    List<Order> selectAll();

    List<Order> selectByPage(@Param("offset") int offset, @Param("limit") int limit);

    int countAll();

    List<Order> selectByUserName(String userName);

    int checkOut(@Param("roomId") String roomId);
}

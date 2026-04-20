package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 房间数据访问层
 *
 * @author hotel-management
 */
public interface RoomMapper extends BaseMapper<Room> {

    List<Room> selectByRoomType(@Param("roomType") String roomType);

    List<Room> selectByPriceRange(@Param("startPrice") BigDecimal startPrice, @Param("endPrice") BigDecimal endPrice);

    int existsByRoomId(@Param("roomId") String roomId);

    List<Room> selectAvailableRooms();
}

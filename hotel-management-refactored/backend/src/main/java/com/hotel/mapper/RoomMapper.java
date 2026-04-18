package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 房间数据访问层
 *
 * @author hotel
 * @since 1.0.0
 */
@Mapper
public interface RoomMapper extends BaseMapper<Room> {

    /**
     * 根据房间类型查询房间列表
     *
     * @param roomType 房间类型
     * @return 房间列表
     */
    @Select("SELECT * FROM t_room WHERE room_type = #{roomType}")
    List<Room> selectByRoomType(@Param("roomType") String roomType);

    /**
     * 查询所有可预订房间（不在预订表和订单表中的房间）
     *
     * @return 可预订房间列表
     */
    @Select("SELECT * FROM t_room WHERE room_id NOT IN (SELECT room_id FROM t_book_a_room) " +
            "AND room_id NOT IN (SELECT room_id FROM t_order WHERE end_time IS NULL)")
    List<Room> selectAvailableRooms();
}

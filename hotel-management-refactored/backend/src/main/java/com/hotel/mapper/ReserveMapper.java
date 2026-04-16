package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Reserve;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 预订数据访问层
 *
 * @author hotel
 * @since 1.0.0
 */
@Mapper
public interface ReserveMapper extends BaseMapper<Reserve> {

    /**
     * 根据用户名查询预订
     *
     * @param userName 用户名
     * @return 预订列表
     */
    @Select("SELECT * FROM t_book_a_room WHERE user_name = #{userName}")
    List<Reserve> selectByUserName(@Param("userName") String userName);

    /**
     * 根据房间ID查询预订
     *
     * @param roomId 房间ID
     * @return 预订信息
     */
    @Select("SELECT * FROM t_book_a_room WHERE room_id = #{roomId}")
    Reserve selectByRoomId(@Param("roomId") String roomId);
}

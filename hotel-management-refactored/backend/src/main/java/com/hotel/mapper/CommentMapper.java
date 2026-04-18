package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 评论数据访问层
 *
 * @author hotel
 * @since 1.0.0
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据房间ID查询评论
     *
     * @param roomId 房间ID
     * @return 评论列表
     */
    @Select("SELECT * FROM t_comment WHERE room_id = #{roomId} ORDER BY release_time DESC")
    List<Comment> selectByRoomId(@Param("roomId") String roomId);

    /**
     * 根据用户名查询评论
     *
     * @param userName 用户名
     * @return 评论列表
     */
    @Select("SELECT * FROM t_comment WHERE user_name = #{userName} ORDER BY release_time DESC")
    List<Comment> selectByUserName(@Param("userName") String userName);
}

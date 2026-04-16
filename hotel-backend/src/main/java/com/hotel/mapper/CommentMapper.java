package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论数据访问层
 *
 * @author hotel-management
 */
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> selectByRoomId(@Param("roomId") String roomId);
}

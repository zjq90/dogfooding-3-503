package com.hotel.mapper;

import com.hotel.entity.Comment;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CommentMapper {

    int insert(Comment comment);

    int deleteByPrimaryKey(Long serNum);

    Comment selectByPrimaryKey(Long serNum);

    List<Comment> selectAll();

    List<Comment> selectByPage(@Param("offset") int offset, @Param("limit") int limit);

    int countAll();

    List<Comment> selectByRoomId(String roomId);

    List<Comment> selectByUserName(String userName);
}

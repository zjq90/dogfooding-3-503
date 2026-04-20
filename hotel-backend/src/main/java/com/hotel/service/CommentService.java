package com.hotel.service;

import com.hotel.entity.Comment;

import java.util.List;

/**
 * 评论服务接口
 *
 * @author hotel-management
 */
public interface CommentService {

    List<Comment> findByRoomId(String roomId);

    boolean addComment(Comment comment);

    boolean updateComment(Comment comment);

    boolean deleteComment(Integer serNum);
}

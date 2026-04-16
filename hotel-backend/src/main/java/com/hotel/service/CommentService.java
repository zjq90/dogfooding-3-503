package com.hotel.service;

import com.hotel.entity.Comment;
import java.util.List;

public interface CommentService {

    boolean addComment(Comment comment);

    boolean deleteComment(Long serNum);

    Comment getCommentById(Long serNum);

    List<Comment> getAllComments();

    List<Comment> getCommentsByPage(int pageNum, int pageSize);

    int getCommentCount();

    List<Comment> getCommentsByRoomId(String roomId);

    List<Comment> getCommentsByUserName(String userName);
}

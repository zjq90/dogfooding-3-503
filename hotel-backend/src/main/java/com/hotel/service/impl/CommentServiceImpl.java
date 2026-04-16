package com.hotel.service.impl;

import com.hotel.entity.Comment;
import com.hotel.mapper.CommentMapper;
import com.hotel.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.insert(comment) > 0;
    }

    @Override
    public boolean deleteComment(Long serNum) {
        return commentMapper.deleteByPrimaryKey(serNum) > 0;
    }

    @Override
    public Comment getCommentById(Long serNum) {
        return commentMapper.selectByPrimaryKey(serNum);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentMapper.selectAll();
    }

    @Override
    public List<Comment> getCommentsByPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        return commentMapper.selectByPage(offset, pageSize);
    }

    @Override
    public int getCommentCount() {
        return commentMapper.countAll();
    }

    @Override
    public List<Comment> getCommentsByRoomId(String roomId) {
        return commentMapper.selectByRoomId(roomId);
    }

    @Override
    public List<Comment> getCommentsByUserName(String userName) {
        return commentMapper.selectByUserName(userName);
    }
}

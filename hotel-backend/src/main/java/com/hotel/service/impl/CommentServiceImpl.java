package com.hotel.service.impl;

import com.hotel.entity.Comment;
import com.hotel.mapper.CommentMapper;
import com.hotel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论服务实现类
 *
 * @author hotel-management
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> findByRoomId(String roomId) {
        return commentMapper.selectByRoomId(roomId);
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.insert(comment) > 0;
    }

    @Override
    public boolean updateComment(Comment comment) {
        return commentMapper.updateById(comment) > 0;
    }

    @Override
    public boolean deleteComment(Integer serNum) {
        return commentMapper.deleteById(serNum) > 0;
    }
}

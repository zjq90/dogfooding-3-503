package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.entity.Comment;
import com.hotel.mapper.CommentMapper;
import com.hotel.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 评论服务实现类
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public List<Comment> getCommentsByRoomId(String roomId) {
        return baseMapper.selectByRoomId(roomId);
    }

    @Override
    public List<Comment> getCommentsByUserName(String userName) {
        return baseMapper.selectByUserName(userName);
    }

    @Override
    public boolean save(Comment comment) {
        comment.setReleaseTime(LocalDateTime.now());
        boolean success = super.save(comment);
        if (success) {
            log.info("用户{}评论房间{}成功", comment.getUserName(), comment.getRoomId());
        }
        return success;
    }
}

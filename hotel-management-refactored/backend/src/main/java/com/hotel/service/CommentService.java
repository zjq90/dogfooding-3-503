package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.entity.Comment;

import java.util.List;

/**
 * 评论服务接口
 *
 * @author hotel
 * @since 1.0.0
 */
public interface CommentService extends IService<Comment> {

    /**
     * 根据房间ID查询评论
     *
     * @param roomId 房间ID
     * @return 评论列表
     */
    List<Comment> getCommentsByRoomId(String roomId);

    /**
     * 根据用户名查询评论
     *
     * @param userName 用户名
     * @return 评论列表
     */
    List<Comment> getCommentsByUserName(String userName);
}

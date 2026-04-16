package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.entity.Message;

import java.util.List;

/**
 * 留言服务接口
 *
 * @author hotel
 * @since 1.0.0
 */
public interface MessageService extends IService<Message> {

    /**
     * 根据用户名查询留言
     *
     * @param userName 用户名
     * @return 留言列表
     */
    List<Message> getMessagesByUserName(String userName);
}

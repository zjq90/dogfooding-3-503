package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.entity.Message;
import com.hotel.mapper.MessageMapper;
import com.hotel.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 留言服务实现类
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Override
    public List<Message> getMessagesByUserName(String userName) {
        return baseMapper.selectByUserName(userName);
    }

    @Override
    public boolean save(Message message) {
        message.setReleaseTime(LocalDateTime.now());
        boolean success = super.save(message);
        if (success) {
            log.info("用户{}留言成功", message.getUserName());
        }
        return success;
    }
}

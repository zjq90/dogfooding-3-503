package com.hotel.service.impl;

import com.hotel.entity.Notice;
import com.hotel.mapper.NoticeMapper;
import com.hotel.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告服务实现类
 *
 * @author hotel-management
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> findAll() {
        return noticeMapper.selectList(null);
    }

    @Override
    public boolean addNotice(Notice notice) {
        return noticeMapper.insert(notice) > 0;
    }

    @Override
    public boolean deleteNotice(Integer serNum) {
        return noticeMapper.deleteById(serNum) > 0;
    }
}

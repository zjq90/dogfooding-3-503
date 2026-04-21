package com.hotel.service.impl;

import com.hotel.entity.Notice;
import com.hotel.mapper.NoticeMapper;
import com.hotel.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;

    @Override
    public boolean addNotice(Notice notice) {
        return noticeMapper.insert(notice) > 0;
    }

    @Override
    public boolean updateNotice(Notice notice) {
        return noticeMapper.updateByPrimaryKey(notice) > 0;
    }

    @Override
    public boolean deleteNotice(Long serNum) {
        return noticeMapper.deleteByPrimaryKey(serNum) > 0;
    }

    @Override
    public Notice getNoticeById(Long serNum) {
        return noticeMapper.selectByPrimaryKey(serNum);
    }

    @Override
    public List<Notice> getAllNotices() {
        return noticeMapper.selectAll();
    }

    @Override
    public List<Notice> getLatestNotices() {
        return noticeMapper.selectLatest();
    }
}

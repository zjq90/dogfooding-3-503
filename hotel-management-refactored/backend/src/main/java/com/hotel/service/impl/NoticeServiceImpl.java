package com.hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.entity.Notice;
import com.hotel.mapper.NoticeMapper;
import com.hotel.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公告服务实现类
 *
 * @author hotel
 * @since 1.0.0
 */
@Slf4j
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public List<Notice> getLatestNotices(int limit) {
        return baseMapper.selectLatest(limit);
    }

    @Override
    public boolean save(Notice notice) {
        notice.setReleaseTime(LocalDateTime.now());
        boolean success = super.save(notice);
        if (success) {
            log.info("管理员{}发布公告成功", notice.getAdminId());
        }
        return success;
    }
}

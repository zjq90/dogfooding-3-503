package com.hotel.service;

import com.hotel.entity.Notice;
import java.util.List;

public interface NoticeService {

    boolean addNotice(Notice notice);

    boolean updateNotice(Notice notice);

    boolean deleteNotice(Long serNum);

    Notice getNoticeById(Long serNum);

    List<Notice> getAllNotices();

    List<Notice> getLatestNotices();
}

package com.hotel.service;

import com.hotel.entity.Notice;

import java.util.List;

/**
 * 公告服务接口
 *
 * @author hotel-management
 */
public interface NoticeService {

    List<Notice> findAll();

    boolean addNotice(Notice notice);

    boolean deleteNotice(Integer serNum);
}

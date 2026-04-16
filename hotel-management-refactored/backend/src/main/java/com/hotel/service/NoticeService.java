package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.entity.Notice;

import java.util.List;

/**
 * 公告服务接口
 *
 * @author hotel
 * @since 1.0.0
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 获取最新公告
     *
     * @param limit 数量限制
     * @return 公告列表
     */
    List<Notice> getLatestNotices(int limit);
}

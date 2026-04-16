package com.hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.entity.Reserve;

import java.util.List;

/**
 * 预订服务接口
 *
 * @author hotel
 * @since 1.0.0
 */
public interface ReserveService extends IService<Reserve> {

    /**
     * 根据用户名查询预订
     *
     * @param userName 用户名
     * @return 预订列表
     */
    List<Reserve> getReservesByUserName(String userName);

    /**
     * 预订房间
     *
     * @param reserve 预订信息
     * @return true-成功，false-失败
     */
    boolean reserveRoom(Reserve reserve);
}

package com.hotel.service;

import com.hotel.entity.Reserve;

import java.util.List;

/**
 * 预定服务接口
 *
 * @author hotel-management
 */
public interface ReserveService {

    List<Reserve> findAll();

    boolean addReserve(Reserve reserve);

    boolean deleteReserve(String roomId);

    boolean isReserved(String roomId);
}

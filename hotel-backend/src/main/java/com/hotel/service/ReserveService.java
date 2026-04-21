package com.hotel.service;

import com.hotel.entity.Reserve;
import java.util.List;

public interface ReserveService {

    boolean addReserve(Reserve reserve);

    boolean updateReserve(Reserve reserve);

    boolean deleteReserve(String roomId);

    Reserve getReserveById(String roomId);

    List<Reserve> getAllReserves();

    List<Reserve> getReservesByPage(int pageNum, int pageSize);

    int getReserveCount();

    List<Reserve> getReservesByUserName(String userName);
}

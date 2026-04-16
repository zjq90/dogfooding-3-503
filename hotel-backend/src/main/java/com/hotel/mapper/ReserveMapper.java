package com.hotel.mapper;

import com.hotel.entity.Reserve;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ReserveMapper {

    int insert(Reserve reserve);

    int updateByPrimaryKey(Reserve reserve);

    int deleteByPrimaryKey(String roomId);

    Reserve selectByPrimaryKey(String roomId);

    List<Reserve> selectAll();

    List<Reserve> selectByPage(@Param("offset") int offset, @Param("limit") int limit);

    int countAll();

    List<Reserve> selectByUserName(String userName);
}

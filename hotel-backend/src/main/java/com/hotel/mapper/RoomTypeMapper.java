package com.hotel.mapper;

import com.hotel.entity.RoomType;
import java.util.List;

public interface RoomTypeMapper {

    int insert(RoomType roomType);

    int updateByPrimaryKey(RoomType roomType);

    int deleteByPrimaryKey(Long typeId);

    RoomType selectByPrimaryKey(Long typeId);

    List<RoomType> selectAll();
}

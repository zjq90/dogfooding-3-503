package com.hotel.mapper;

import com.hotel.entity.Room;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface RoomMapper {

    int insert(Room room);

    int updateByPrimaryKey(Room room);

    int deleteByPrimaryKey(String roomId);

    Room selectByPrimaryKey(String roomId);

    List<Room> selectAll();

    List<Room> selectByPage(@Param("offset") int offset, @Param("limit") int limit);

    int countAll();

    List<Room> selectByType(String roomType);

    List<Room> selectAvailableRooms();
}

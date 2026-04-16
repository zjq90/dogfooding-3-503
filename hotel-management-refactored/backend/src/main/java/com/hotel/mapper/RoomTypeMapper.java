package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.RoomType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 房间类型数据访问层
 *
 * @author hotel
 * @since 1.0.0
 */
@Mapper
public interface RoomTypeMapper extends BaseMapper<RoomType> {
}

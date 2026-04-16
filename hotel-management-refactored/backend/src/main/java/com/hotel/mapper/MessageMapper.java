package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 留言数据访问层
 *
 * @author hotel
 * @since 1.0.0
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 根据用户名查询留言
     *
     * @param userName 用户名
     * @return 留言列表
     */
    @Select("SELECT * FROM t_liuyan WHERE user_name = #{userName} ORDER BY release_time DESC")
    List<Message> selectByUserName(@Param("userName") String userName);
}

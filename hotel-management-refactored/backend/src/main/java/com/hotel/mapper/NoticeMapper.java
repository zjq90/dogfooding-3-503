package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 公告数据访问层
 *
 * @author hotel
 * @since 1.0.0
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    /**
     * 查询最新的N条公告
     *
     * @param limit 数量限制
     * @return 公告列表
     */
    @Select("SELECT * FROM t_notice ORDER BY release_time DESC LIMIT #{limit}")
    List<Notice> selectLatest(int limit);
}

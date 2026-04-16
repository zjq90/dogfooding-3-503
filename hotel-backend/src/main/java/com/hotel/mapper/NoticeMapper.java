package com.hotel.mapper;

import com.hotel.entity.Notice;
import java.util.List;

public interface NoticeMapper {

    int insert(Notice notice);

    int updateByPrimaryKey(Notice notice);

    int deleteByPrimaryKey(Long serNum);

    Notice selectByPrimaryKey(Long serNum);

    List<Notice> selectAll();

    List<Notice> selectLatest();
}

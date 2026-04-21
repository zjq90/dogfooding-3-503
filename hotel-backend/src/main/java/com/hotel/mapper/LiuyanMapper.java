package com.hotel.mapper;

import com.hotel.entity.Liuyan;
import java.util.List;

public interface LiuyanMapper {

    int insert(Liuyan liuyan);

    int deleteByPrimaryKey(Long serNum);

    Liuyan selectByPrimaryKey(Long serNum);

    List<Liuyan> selectAll();
}

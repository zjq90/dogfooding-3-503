package com.hotel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 留言实体类
 *
 * @author hotel-management
 */
@Data
@TableName("t_liuyan")
public class Liuyan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ser_num", type = IdType.AUTO)
    private Long serNum;

    private String userName;

    private String liuyan;

    private Date releaseTime;
}

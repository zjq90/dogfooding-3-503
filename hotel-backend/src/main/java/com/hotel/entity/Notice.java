package com.hotel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告实体类
 *
 * @author hotel-management
 */
@Data
@TableName("t_notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ser_num", type = IdType.AUTO)
    private Integer serNum;

    private Integer adminId;

    private String notice;

    private Date releaseTime;
}

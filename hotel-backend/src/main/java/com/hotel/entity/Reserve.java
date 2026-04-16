package com.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 预定实体类
 *
 * @author hotel-management
 */
@Data
@TableName("t_book_a_room")
public class Reserve implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("room_id")
    private String roomId;

    private Integer countDate;

    private String userName;

    private String phone;

    private Date presetTime;
}

package com.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体类
 *
 * @author hotel-management
 */
@Data
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("room_id")
    private String roomId;

    private String userName;

    @TableField("ID")
    private String id;

    private Date startTime;

    private Date endTime;

    private BigDecimal prepay;

    private BigDecimal payment;

    @TableField("`change`")
    private BigDecimal change;

    private String serNum;
}

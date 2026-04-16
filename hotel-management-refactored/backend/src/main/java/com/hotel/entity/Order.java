package com.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体类（入住订单）
 *
 * @author hotel
 * @since 1.0.0
 */
@Data
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房间ID
     */
    @TableId
    @NotBlank(message = "房间ID不能为空")
    private String roomId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 身份证号
     */
    private String id;

    /**
     * 入住时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 退房时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 预付款
     */
    @NotNull(message = "预付款不能为空")
    @Positive(message = "预付款必须大于0")
    private BigDecimal prepay;

    /**
     * 实付款
     */
    private BigDecimal payment;

    /**
     * 找零
     */
    private BigDecimal change;

    /**
     * 订单序列号
     */
    private String serNum;
}

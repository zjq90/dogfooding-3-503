package com.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 房间实体类
 *
 * @author hotel-management
 */
@Data
@TableName("t_room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("room_id")
    private String roomId;

    private String roomType;

    private Integer area;

    private String introduce;

    private String photoUrl;

    private BigDecimal price;
}

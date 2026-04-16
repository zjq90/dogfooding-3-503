package com.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 房间实体类
 *
 * @author hotel
 * @since 1.0.0
 */
@Data
@TableName("t_room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房间ID
     */
    @TableId
    @NotBlank(message = "房间ID不能为空")
    private String roomId;

    /**
     * 房间类型
     */
    @NotBlank(message = "房间类型不能为空")
    private String roomType;

    /**
     * 房间面积
     */
    @NotNull(message = "房间面积不能为空")
    @Positive(message = "房间面积必须大于0")
    private Integer area;

    /**
     * 房间介绍
     */
    private String introduce;

    /**
     * 房间图片URL
     */
    private String photoUrl;

    /**
     * 房间价格
     */
    @NotNull(message = "房间价格不能为空")
    @Positive(message = "房间价格必须大于0")
    private BigDecimal price;
}

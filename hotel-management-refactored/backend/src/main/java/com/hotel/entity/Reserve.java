package com.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 预订实体类
 *
 * @author hotel
 * @since 1.0.0
 */
@Data
@TableName("t_book_a_room")
public class Reserve implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 房间ID
     */
    @TableId
    @NotBlank(message = "房间ID不能为空")
    private String roomId;

    /**
     * 预订天数
     */
    @NotNull(message = "预订天数不能为空")
    @Positive(message = "预订天数必须大于0")
    private Integer countDate;

    /**
     * 预订人姓名
     */
    @NotBlank(message = "预订人姓名不能为空")
    private String userName;

    /**
     * 预订人电话
     */
    @NotBlank(message = "预订人电话不能为空")
    private String phone;

    /**
     * 预订时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime presetTime;
}

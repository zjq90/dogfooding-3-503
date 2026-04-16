package com.hotel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 房间类型实体类
 *
 * @author hotel
 * @since 1.0.0
 */
@Data
@TableName("t_room_type")
public class RoomType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型ID
     */
    @TableId(type = IdType.AUTO)
    private Integer typeId;

    /**
     * 类型名称
     */
    @NotBlank(message = "类型名称不能为空")
    private String type;
}

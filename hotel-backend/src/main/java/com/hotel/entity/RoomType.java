package com.hotel.entity;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class RoomType implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long typeId;

    @NotBlank(message = "房型名称不能为空")
    private String type;
}

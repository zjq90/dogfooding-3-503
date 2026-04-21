package com.hotel.entity;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "房间号不能为空")
    private String roomId;

    @NotBlank(message = "房间类型不能为空")
    private String roomType;

    @NotNull(message = "房间面积不能为空")
    private Integer area;

    private String introduce;

    private String photoUrl;

    @NotNull(message = "房间价格不能为空")
    private BigDecimal price;
}

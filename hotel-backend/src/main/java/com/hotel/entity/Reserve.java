package com.hotel.entity;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class Reserve implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "房间号不能为空")
    private String roomId;

    @NotNull(message = "预订天数不能为空")
    private Integer countDate;

    private String userName;

    private String phone;

    private Date presetTime;
}

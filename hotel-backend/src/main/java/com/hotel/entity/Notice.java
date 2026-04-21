package com.hotel.entity;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long serNum;

    @NotNull(message = "管理员ID不能为空")
    private Long adminId;

    @NotBlank(message = "公告内容不能为空")
    private String notice;

    private Date releaseTime;
}

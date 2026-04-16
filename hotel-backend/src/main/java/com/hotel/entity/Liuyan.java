package com.hotel.entity;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class Liuyan implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long serNum;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "留言内容不能为空")
    private String liuyan;

    private Date releaseTime;
}

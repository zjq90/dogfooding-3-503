package com.hotel.entity;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long serNum;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "房间号不能为空")
    private String roomId;

    @NotBlank(message = "评论内容不能为空")
    private String content;

    private Date releaseTime;
}

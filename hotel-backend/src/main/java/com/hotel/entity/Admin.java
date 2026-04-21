package com.hotel.entity;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long adminId;

    @NotBlank(message = "管理员账号不能为空")
    private String adminName;

    @NotBlank(message = "管理员密码不能为空")
    private String adminPwd;
}

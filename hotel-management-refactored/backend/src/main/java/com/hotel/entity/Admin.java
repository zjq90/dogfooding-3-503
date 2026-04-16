package com.hotel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 管理员实体类
 *
 * @author hotel
 * @since 1.0.0
 */
@Data
@TableName("t_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员ID
     */
    @TableId(type = IdType.AUTO)
    private Integer adminId;

    /**
     * 管理员名称
     */
    @NotBlank(message = "管理员名称不能为空")
    private String adminName;

    /**
     * 管理员密码
     */
    @NotBlank(message = "密码不能为空")
    private String adminPwd;
}

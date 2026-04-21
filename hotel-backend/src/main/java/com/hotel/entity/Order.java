package com.hotel.entity;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "房间号不能为空")
    private String roomId;

    private String userName;

    private String idCard;

    private Date startTime;

    private Date endTime;

    private BigDecimal prepay;

    private BigDecimal payment;

    private BigDecimal change;

    private String serNum;
}

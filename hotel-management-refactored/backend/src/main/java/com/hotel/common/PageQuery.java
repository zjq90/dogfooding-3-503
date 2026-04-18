package com.hotel.common;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 分页查询参数
 *
 * @author hotel
 * @since 1.0.0
 */
@Data
public class PageQuery {

    /**
     * 当前页码（默认第1页）
     */
    @Min(value = 1, message = "页码不能小于1")
    private long current = 1L;

    /**
     * 每页大小（默认10条）
     */
    @Min(value = 1, message = "每页大小不能小于1")
    private long size = 10L;
}

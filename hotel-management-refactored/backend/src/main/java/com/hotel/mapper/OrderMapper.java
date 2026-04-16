package com.hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单数据访问层
 *
 * @author hotel
 * @since 1.0.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 根据用户名查询订单
     *
     * @param userName 用户名
     * @return 订单列表
     */
    @Select("SELECT * FROM t_order WHERE user_name = #{userName}")
    List<Order> selectByUserName(@Param("userName") String userName);

    /**
     * 查询所有在住订单（未退房）
     *
     * @return 在住订单列表
     */
    @Select("SELECT * FROM t_order WHERE end_time IS NULL")
    List<Order> selectActiveOrders();

    /**
     * 退房操作
     *
     * @param roomId   房间ID
     * @param endTime  退房时间
     * @param payment  实付款
     * @param change   找零
     * @return 影响行数
     */
    @Update("UPDATE t_order SET end_time = #{endTime}, payment = #{payment}, `change` = #{change} " +
            "WHERE room_id = #{roomId}")
    int checkout(@Param("roomId") String roomId,
                 @Param("endTime") LocalDateTime endTime,
                 @Param("payment") java.math.BigDecimal payment,
                 @Param("change") java.math.BigDecimal change);
}

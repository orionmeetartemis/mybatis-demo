package com.zpc.mybatis.dao;

import com.zpc.mybatis.pojo.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    /** 1 - 1
     * 根据订单号查询订单用户的信息
     * @param number
     * @return
     */
    Order queryOrderWithUserByOrderNumber(@Param("number") String number);

    /** 1 - n
     * 根据订单号查询订单用户的信息及订单详情
     * @param number
     * @return
     */
    Order queryOrderWithUserAndDetailByOrderNumber(@Param("number") String number);

    /** n - n
     * 根据订单号查询订单用户的信息及订单详情及订单详情对应的商品信息
     * @param number
     * @return
     */
    Order queryOrderWithUserAndDetailItemByOrderNumber(@Param("number") String number);
}

package com.iL.hotel.api.dao;

import com.iL.hotel.pojo.OrderEntity;

import java.util.List;

public interface OrderDAO {
    public List<OrderEntity> getOrderByOrderId(Integer OrderId);
    public void addDays(Integer orderId,Integer days);
}

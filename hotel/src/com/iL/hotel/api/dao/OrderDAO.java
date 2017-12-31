package com.iL.hotel.api.dao;

import com.iL.hotel.dao.HibernateBaseDao;
import com.iL.hotel.pojo.OrderEntity;

import java.util.List;

public interface OrderDAO extends HibernateBaseDao<OrderEntity,Integer> {
    public List<OrderEntity> getOrderByOrderId(Integer OrderId);
    public void addDays(Integer orderId,Integer days);
}

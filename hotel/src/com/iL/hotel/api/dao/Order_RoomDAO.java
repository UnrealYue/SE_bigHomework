package com.iL.hotel.api.dao;

import com.iL.hotel.dao.HibernateBaseDao;
import com.iL.hotel.pojo.OrderEntity;
import com.iL.hotel.pojo.OrderRoomEntity;
import com.iL.hotel.pojo.RoomEntity;

import java.sql.Timestamp;
import java.util.List;

public interface Order_RoomDAO extends HibernateBaseDao<OrderRoomEntity,Integer> {

    public List<OrderRoomEntity> getCheckedOrderInfo();
    public void checkOut(Integer ids, Timestamp DATE, Integer method,String note);

}

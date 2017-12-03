package com.iL.hotel.api.service;

import com.iL.hotel.pojo.CheckedOrderInfoForShow;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface CheckedOrderService {
    public List<CheckedOrderInfoForShow> getCheckedOrderInfo();

    public void checkOut(Integer OrderId, Timestamp date, Integer payMethod,String note);
    public void addOrderDays(Integer orderId,Integer days);
}

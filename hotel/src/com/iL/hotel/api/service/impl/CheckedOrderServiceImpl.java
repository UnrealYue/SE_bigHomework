package com.iL.hotel.api.service.impl;

import com.iL.hotel.api.dao.OrderDAO;
import com.iL.hotel.api.dao.Order_RoomDAO;
import com.iL.hotel.api.dao.impl.OrderDAOImpl;
import com.iL.hotel.api.dao.impl.Order_RoomDAOImpl;
import com.iL.hotel.api.service.CheckedOrderService;
import com.iL.hotel.pojo.CheckedOrderInfoForShow;
import com.iL.hotel.pojo.OrderEntity;
import com.iL.hotel.pojo.OrderRoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CheckedOrderServiceImpl implements CheckedOrderService {
    @Autowired
    Order_RoomDAO order_roomDAO;
    @Autowired
    OrderDAO orderDAO;
    @Override

    public List<CheckedOrderInfoForShow> getCheckedOrderInfo() {
        List<CheckedOrderInfoForShow> checkedOrderInfoForShowList=new ArrayList<CheckedOrderInfoForShow>() ;
        for (OrderRoomEntity orderRoomEntity: order_roomDAO.getCheckedOrderInfo()){
            checkedOrderInfoForShowList.add(new CheckedOrderInfoForShow(orderRoomEntity.getRoomByRoomId(),orderRoomEntity,orderRoomEntity.getOrderByOrderId(),orderRoomEntity.getOrderByOrderId().getGuestByGuestId()));
        }
        return  checkedOrderInfoForShowList;
    }


    @Override
    @Transactional
    public void checkOut(Integer OrderId, Timestamp date, Integer payMethod,String note) {
        order_roomDAO.checkOut(OrderId,date,payMethod,note);
    }
    public void addOrderDays(Integer orderId,Integer days){
        orderDAO.addDays(orderId,days);
    }



}

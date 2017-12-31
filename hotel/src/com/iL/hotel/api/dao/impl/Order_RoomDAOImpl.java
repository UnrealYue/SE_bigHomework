package com.iL.hotel.api.dao.impl;

import com.iL.hotel.api.dao.Order_RoomDAO;
import com.iL.hotel.common.utils.HqlMapParameterSetter;
import com.iL.hotel.dao.impl.HibernateBaseDaoImpl;
import com.iL.hotel.pojo.OrderEntity;
import com.iL.hotel.pojo.OrderRoomEntity;
import com.iL.hotel.pojo.RoomEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import static com.iL.hotel.common.ConstUtil.*;
@Repository
@Transactional
public class Order_RoomDAOImpl  extends HibernateBaseDaoImpl<OrderRoomEntity,Integer> implements Order_RoomDAO{
    static Configuration configuration = new Configuration().configure();
    static SessionFactory factory = configuration.buildSessionFactory();

    @Override
    public List<OrderRoomEntity> getCheckedOrderInfo() {
        HqlMapParameterSetter parameterSetter=new HqlMapParameterSetter();
        parameterSetter.add("a",(byte)1);
        parameterSetter.add("b",(byte)0);
        parameterSetter.add("c",(byte)0);

        List<OrderRoomEntity> orderRoomEntities=(List<OrderRoomEntity>)this.findAll(GET_CHECKEDOR,parameterSetter.getParamMap());
        for(OrderRoomEntity orderRoomEntity:orderRoomEntities){
            orderRoomEntity.getOrderByOrderId().getGuestByGuestId().getGuestId();
        }
        return orderRoomEntities;
    }
    @Override
    public void checkOut(Integer ids, Timestamp DATE, Integer method,String note) {
        HqlMapParameterSetter parameterSetter=new HqlMapParameterSetter();
        parameterSetter.add("orderID",ids);
        List<OrderRoomEntity> orderRoomEntities=(List<OrderRoomEntity>)this.findAll(GET_OR_BYORDERID,parameterSetter.getParamMap());
        for(OrderRoomEntity orderRoomEntity:orderRoomEntities){
            orderRoomEntity.getOrderByOrderId().setIsFinished((byte)1);
            orderRoomEntity.getOrderByOrderId().setCheckoutMethod((byte)method.intValue());
            orderRoomEntity.getOrderByOrderId().setNotes(note);
            orderRoomEntity.setCheckoutTime(DATE);
            orderRoomEntity.getRoomByRoomId().setRoomState((byte)0);
            update(orderRoomEntity);
        }
    }
}

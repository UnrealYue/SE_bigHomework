package com.iL.hotel.api.dao.impl;

import com.iL.hotel.api.dao.Order_RoomDAO;
import com.iL.hotel.pojo.OrderEntity;
import com.iL.hotel.pojo.OrderRoomEntity;
import com.iL.hotel.pojo.RoomEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import static com.iL.hotel.common.ConstUtil.*;
public class Order_RoomDAOImpl   implements Order_RoomDAO{
    static Configuration configuration = new Configuration().configure();
    static SessionFactory factory = configuration.buildSessionFactory();

    @Override
    public List<OrderRoomEntity> getCheckedOrderInfo() {
        Session session = factory.openSession();
        String hql=getCheckedOR;
        Query query = session.createQuery(hql);
        List<OrderRoomEntity> orderRoomEntities= (List<OrderRoomEntity>)query.list();
        for(OrderRoomEntity orderRoomEntity:orderRoomEntities){
            orderRoomEntity.getOrderByOrderId().getGuestByGuestId().getGuestId();

        }
        return orderRoomEntities;
    }
    @Override
    public void checkOut(Integer ids, Timestamp DATE, Integer method,String note) {
        Session session = factory.openSession();
        String hql = getORByOrderId;
        List<OrderRoomEntity> orderRoomEntities =(List<OrderRoomEntity>)session.createQuery(hql).setParameter(0,ids).list();
        session.beginTransaction();
        for(OrderRoomEntity orderRoomEntity:orderRoomEntities){
            orderRoomEntity.getOrderByOrderId().setIsFinished((byte)1);
            orderRoomEntity.getOrderByOrderId().setCheckoutMethod((byte)method.intValue());
            orderRoomEntity.getOrderByOrderId().setNotes(note);
            orderRoomEntity.setCheckoutTime(DATE);
            orderRoomEntity.getRoomByRoomId().setRoomState((byte)0);
            session.update(orderRoomEntity);
        }
        session.getTransaction().commit();
    }
}

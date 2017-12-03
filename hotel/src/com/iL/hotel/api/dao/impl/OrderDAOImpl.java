package com.iL.hotel.api.dao.impl;

import com.iL.hotel.api.dao.OrderDAO;
import com.iL.hotel.pojo.CheckedOrderInfoForShow;
import com.iL.hotel.pojo.OrderEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import static com.iL.hotel.common.ConstUtil.*;
public class OrderDAOImpl implements OrderDAO {
    static Configuration configuration = new Configuration().configure();
    static SessionFactory factory = configuration.buildSessionFactory();

    @Override
    public List<OrderEntity> getOrderByOrderId(Integer OrderId) {
        Session session = factory.openSession();
        String hql = getOrderByOrderId;
        Query query = session.createQuery(hql);
        query.setParameter(0,OrderId);
        return (List<OrderEntity> )query.list();
    }

    @Override
    public void addDays(Integer orderId, Integer days) {
        Session session = factory.openSession();
        String hql = getOrderByOrderId;
        Query query = session.createQuery(hql);
        query.setParameter(0,orderId);
        OrderEntity orderEntity=(OrderEntity)query.list().get(0);
        int olddays=orderEntity.getStayDays();
        byte addEdDays=(byte)(days.intValue()+olddays);
        orderEntity.setStayDays(addEdDays);
        session.beginTransaction();
        session.update(orderEntity);
        session.getTransaction().commit();
    }


}

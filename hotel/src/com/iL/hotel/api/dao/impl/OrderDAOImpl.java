package com.iL.hotel.api.dao.impl;

import com.iL.hotel.api.dao.OrderDAO;
import com.iL.hotel.common.utils.HqlMapParameterSetter;
import com.iL.hotel.dao.impl.HibernateBaseDaoImpl;
import com.iL.hotel.pojo.CheckedOrderInfoForShow;
import com.iL.hotel.pojo.OrderEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static com.iL.hotel.common.ConstUtil.*;
@Repository
@Transactional
public class OrderDAOImpl extends HibernateBaseDaoImpl<OrderEntity,Integer> implements OrderDAO {
    static Configuration configuration = new Configuration().configure();
    static SessionFactory factory = configuration.buildSessionFactory();

    @Override
    public List<OrderEntity> getOrderByOrderId(Integer OrderId) {
        HqlMapParameterSetter parameterSetter=new HqlMapParameterSetter();
        parameterSetter.add("orderID",OrderId);
        return (List<OrderEntity>) this.findAll(GET_ORDER_BYORDERID,parameterSetter.getParamMap());
    }

    @Override
    public void addDays(Integer orderId, Integer days) {
        HqlMapParameterSetter parameterSetter=new HqlMapParameterSetter();
        parameterSetter.add("orderID",orderId);
        OrderEntity orderEntity=(OrderEntity)this.findAll(GET_ORDER_BYORDERID,parameterSetter.getParamMap()).get(0);

        int olddays=orderEntity.getStayDays();
        byte addEdDays=(byte)(days.intValue()+olddays);
        orderEntity.setStayDays(addEdDays);
        update(orderEntity);
    }


}

package com.iL.hotel.api.dao.impl;

import com.iL.hotel.api.dao.VIPDAO;
import com.iL.hotel.pojo.VIPForShow;
import com.iL.hotel.pojo.GuestEntity;
import com.iL.hotel.pojo.VipEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Date;
import java.util.List;
import  com.iL.hotel.common.ConstUtil;

public class VIPDAOPImpl implements VIPDAO {
    static Configuration configuration = new Configuration().configure();
    static SessionFactory factory = configuration.buildSessionFactory();
    @Override
    public List<VIPForShow> getAllVIPsForShow() {
        Session session = factory.openSession();
        String hql = ConstUtil.getAllVIPForShow;
        Query query = session.createQuery(hql);
        return (List<VIPForShow>)query.list();
    }
    @Override
    public VipEntity getVIP(Integer id) {
        String hql = ConstUtil.getVIPByGuestId;
        Session session=factory.openSession();
        List<VipEntity> list =session.createQuery(hql).setParameter(0,id).list();
        return list.get(0);
    }
    @Override
    public void addVIP(int guestId, Date birthday, String job, double balance) {
        Session session = factory.openSession();
            GuestEntity guestEntity=(GuestEntity)session.load(GuestEntity.class,guestId);
            guestEntity.setIsVip((byte)1);
            session.clear();
            session.beginTransaction();
            session.update(guestEntity);
            session.getTransaction().commit();
            VipEntity vipEntity=new VipEntity();
            vipEntity.setBalance(balance);
            vipEntity.setBirthday(birthday);
            vipEntity.setJob(job);
            vipEntity.setTotalCosts(0);
            vipEntity.setGuestByGuestId(guestEntity);
            session.clear();
            session.beginTransaction();
            session.save(vipEntity);
            session.getTransaction().commit();

    }

    @Override
    public void editVIP(Integer ids, java.sql.Date birthday, String job, double balance) {
        String hql = ConstUtil.getVIPByGuestId;
        Session session=factory.openSession();
        List<VipEntity> list =session.createQuery(hql).setParameter(0,ids).list();
        for(VipEntity a:list){
            VipEntity vipEntity=(VipEntity) session.load(VipEntity.class,a.getId());
            vipEntity.setBirthday(birthday);
            vipEntity.setJob(job);
            vipEntity.setBalance(balance);
            session.clear();
            session.beginTransaction();
            session.update(vipEntity);
            session.getTransaction().commit();
        }
    }


    @Override
    public void deleteVIP(Integer id) {
        Session session=factory.openSession();
        String hql = ConstUtil.getGuestByGuestId;
        List<GuestEntity> list =session.createQuery(hql).setParameter(0,id).list();
        for(GuestEntity a:list){
            GuestEntity guestEntity=(GuestEntity)session.load(GuestEntity.class,a.getGuestId());
            guestEntity.setIsVip((byte)0);
            session.clear();
            session.beginTransaction();
            session.update(guestEntity);
            session.getTransaction().commit();
        }
        String hql1 = ConstUtil.getVIPByGuestId;
        List<VipEntity> v=session.createQuery(hql1).setParameter(0, id).list();
        for(VipEntity k : v) {
            VipEntity bb = (VipEntity) session.load(VipEntity.class, k.getId());
            session.clear();
            session.beginTransaction();
            session.delete(bb);
            session.getTransaction().commit();
        }
    }


}

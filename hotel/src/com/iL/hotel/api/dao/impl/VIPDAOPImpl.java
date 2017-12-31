package com.iL.hotel.api.dao.impl;

import com.iL.hotel.api.dao.VIPDAO;
import com.iL.hotel.common.utils.HqlMapParameterSetter;
import com.iL.hotel.dao.HibernateBaseDao;
import com.iL.hotel.dao.impl.HibernateBaseDaoImpl;
import com.iL.hotel.pojo.VIPForShow;
import com.iL.hotel.pojo.GuestEntity;
import com.iL.hotel.pojo.VipEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import  com.iL.hotel.common.ConstUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VIPDAOPImpl  extends HibernateBaseDaoImpl<VipEntity,Integer> implements VIPDAO {
    @Override
    public List<VipEntity> getAllVIPsForShow() {
        return (List<VipEntity>)this.findAll();
    }
    @Override
    public List<VipEntity> getVIPs(Integer[] id) {
        List<VipEntity> vipEntities=new ArrayList<VipEntity>();
        for(Integer a:id){
            HqlMapParameterSetter parameterSetter=new HqlMapParameterSetter();
            parameterSetter.add("gid",a);
            vipEntities.add((VipEntity)this.findAll(ConstUtil.GET_VIP_BYGUESTID,parameterSetter.getParamMap()).get(0));
        }
        return  vipEntities;
    }
    @Override
    public void addVIP(VipEntity vipEntity) {
        this.save(vipEntity);
    }

}

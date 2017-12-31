package com.iL.hotel.api.dao;

import com.iL.hotel.dao.HibernateBaseDao;
import com.iL.hotel.pojo.VIPForShow;
import com.iL.hotel.pojo.VipEntity;

import java.sql.Date;
import java.util.List;

public interface VIPDAO extends HibernateBaseDao<VipEntity,Integer> {
    public List<VipEntity> getAllVIPsForShow();
    public List<VipEntity> getVIPs(Integer[] id);
    public void addVIP(VipEntity vipEntity);


}

package com.iL.hotel.api.service.impl;

import com.iL.hotel.api.dao.VIPDAO;
import com.iL.hotel.api.dao.impl.VIPDAOPImpl;
import com.iL.hotel.api.service.VIPService;
import com.iL.hotel.pojo.VIPForShow;
import com.iL.hotel.pojo.VipEntity;

import java.sql.Date;
import java.util.List;


public class VIPServiceImpl implements VIPService{
    static VIPDAO vipdao=new VIPDAOPImpl() ;
    @Override
    public List<VIPForShow> getAllVIPs() {
        return vipdao.getAllVIPsForShow();
    }

    @Override
    public void addVIP(int guestId, Date birthday, String job, double balance) {
        vipdao.addVIP(guestId,birthday,job,balance);
    }

    @Override
    public void deleteVIP(Integer ids) {
        vipdao.deleteVIP(ids);
    }

    @Override
    public void editVIP(Integer ids, java.sql.Date birthday, String job, double balance) {
        vipdao.editVIP(ids, birthday, job, balance);
    }

    @Override
    public void VIPReCharge(Integer id,double money) {
        VipEntity vipEntity=vipdao.getVIP(id);
        vipdao.editVIP(vipEntity.getGuestByGuestId().getGuestId(),vipEntity.getBirthday(),vipEntity.getJob(),vipEntity.getBalance());
    }

}

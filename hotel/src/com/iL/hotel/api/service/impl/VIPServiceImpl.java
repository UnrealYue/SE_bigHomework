package com.iL.hotel.api.service.impl;

import com.iL.hotel.api.dao.GuestDAO;
import com.iL.hotel.api.dao.VIPDAO;
import com.iL.hotel.api.dao.impl.VIPDAOPImpl;
import com.iL.hotel.api.service.VIPService;
import com.iL.hotel.pojo.GuestEntity;
import com.iL.hotel.pojo.VIPForShow;
import com.iL.hotel.pojo.VipEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class VIPServiceImpl implements VIPService{
    @Autowired
    private VIPDAO vipdao;
    @Autowired
    private GuestDAO guestDAO;
    @Override
    @Transactional
    public List<VIPForShow> getAllVIPs() {
        List<VipEntity> vipEntities=vipdao.getAllVIPsForShow();
        List<VIPForShow> vipForShows= new ArrayList<VIPForShow>() ;
        for(VipEntity a:vipEntities){
            VIPForShow b=new VIPForShow();
            b.isVip=a.getGuestByGuestId().getIsVip();
            b.guestId=a.getGuestByGuestId().getGuestId();
            b.guestName=a.getGuestByGuestId().getGuestName();
            b.gendar=a.getGuestByGuestId().getGendar();
            b.idCard=a.getGuestByGuestId().getIdCard();
            b.phoneNum=a.getGuestByGuestId().getPhoneNum();
            b.balance=a.getBalance();
            b.job=a.getJob();
            b.totalCosts=a.getTotalCosts();
            b.birthday=a.getBirthday();
            vipForShows.add(b);
        }
        return vipForShows;
    }

    @Override
    @Transactional
    public void addVIP(int guestId, Date birthday, String job, double balance) {
        GuestEntity guestEntity=guestDAO.getGuestByID(guestId);
        guestEntity.setIsVip((byte)1);
        guestDAO.updateGuest(guestEntity);
        VipEntity vipEntity=new VipEntity();
        vipEntity.setBalance(balance);
        vipEntity.setBirthday(birthday);
        vipEntity.setJob(job);
        vipEntity.setTotalCosts(0);
        vipEntity.setGuestByGuestId(guestEntity);
        vipdao.addVIP(vipEntity);
    }

    @Override
    @Transactional
    public void deleteVIP(Integer[] ids) {
        List<GuestEntity> list = guestDAO.getGuestsById(ids);
        for(GuestEntity a:list){
            a.setIsVip((byte)0);
            guestDAO.update(a);
        }
        List<VipEntity> v=vipdao.getVIPs(ids);
        for(VipEntity k : v) {
           vipdao.delete(k);
        }
    }

    @Override
    @Transactional
    public void editVIP(Integer ids, java.sql.Date birthday, String job, double balance) {
        Integer[] id=new Integer[1];
        id[0]=ids;
        List<VipEntity> list=vipdao.getVIPs(id);
        for(VipEntity a:list){
            a.setBirthday(birthday);
            a.setJob(job);
            a.setBalance(balance);
            vipdao.update(a);
        }
    }

    @Override
    @Transactional
    public void VIPReCharge(Integer id,double money) {
        Integer [] ids=new Integer[1];
        ids[0]=id;
        VipEntity vipEntity=vipdao.getVIPs(ids).get(0);
        editVIP(vipEntity.getGuestByGuestId().getGuestId(),vipEntity.getBirthday(),vipEntity.getJob(),vipEntity.getBalance());
    }

}

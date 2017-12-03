package com.iL.hotel.api.dao;

import com.iL.hotel.pojo.VIPForShow;
import com.iL.hotel.pojo.VipEntity;

import java.sql.Date;
import java.util.List;

public interface VIPDAO {

    public List<VIPForShow> getAllVIPsForShow();
    public VipEntity getVIP(Integer id);
    public void addVIP(int guestId, Date birthday, String job, double balance);
    public void editVIP(Integer ids, java.sql.Date birthday, String job, double balance);
    public void deleteVIP(Integer id);


}

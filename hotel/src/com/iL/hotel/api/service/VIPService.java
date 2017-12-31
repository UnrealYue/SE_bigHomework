package com.iL.hotel.api.service;

import com.iL.hotel.pojo.VIPForShow;

import java.util.List;

public interface VIPService {
    public List<VIPForShow> getAllVIPs();
    public void addVIP(int guestId,java.sql.Date birthday, String job, double balance);
    public void deleteVIP(Integer[] ids);
    public void editVIP(Integer ids, java.sql.Date birthday, String job, double balance);
    public void VIPReCharge(Integer id,double money );
}

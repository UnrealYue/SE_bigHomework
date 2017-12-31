package com.iL.hotel.api.dao;

import com.iL.hotel.dao.HibernateBaseDao;
import com.iL.hotel.pojo.GuestEntity;

import java.util.List;

public interface GuestDAO extends HibernateBaseDao<GuestEntity,Integer> {
    public void updateGuest(GuestEntity guestEntity);
    public GuestEntity getGuestByID(Integer guestId);
    public List<GuestEntity> getGuestsById(Integer[] id);

}

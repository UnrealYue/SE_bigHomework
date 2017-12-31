package com.iL.hotel.api.dao.impl;

import com.iL.hotel.api.dao.GuestDAO;
import com.iL.hotel.common.ConstUtil;
import com.iL.hotel.common.utils.HqlMapParameterSetter;
import com.iL.hotel.dao.impl.HibernateBaseDaoImpl;
import com.iL.hotel.pojo.GuestEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class GuestDAOImpl  extends HibernateBaseDaoImpl<GuestEntity,Integer> implements GuestDAO{
    @Override
    public void updateGuest(GuestEntity guestEntity) {
        this.update(guestEntity);
    }
    @Override
    public GuestEntity getGuestByID(Integer guestId) {
        return this.get(guestId);
    }

    @Override
    public List<GuestEntity> getGuestsById(Integer[] id) {
        List<GuestEntity> guestEntities=new ArrayList<GuestEntity>();
        for(Integer a:id){
            HqlMapParameterSetter parameterSetter=new HqlMapParameterSetter();
            parameterSetter.add("gid",a);
            guestEntities.add((GuestEntity)this.findAll(ConstUtil.GET_GUEST_BYGUESTID,parameterSetter.getParamMap()).get(0));
        }
       return guestEntities;
    }



}

package com.iL.hotel.api.dao.impl;

import com.iL.hotel.api.dao.UsersDao;
import com.iL.hotel.common.ConstUtil;
import com.iL.hotel.common.utils.HqlMapParameterSetter;
import com.iL.hotel.dao.HibernateBaseDao;
import com.iL.hotel.dao.impl.HibernateBaseDaoImpl;
import com.iL.hotel.pojo.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;


@Repository
@Transactional
public class UsersDaoImpl extends HibernateBaseDaoImpl<Users,String> implements UsersDao{
    private static final Logger log = LoggerFactory.getLogger(UsersDaoImpl.class);
    @Override
    public Users userLogin(String username, String password) {
        HqlMapParameterSetter parameterSetter = new HqlMapParameterSetter();
        parameterSetter.add(ConstUtil.DB_USERS_USERNAME,username);
        parameterSetter.add(ConstUtil.DB_USERS_PASSWORD,password);
        parameterSetter.addNotDeleted();
        return (Users) this.findUnique(ConstUtil.USER_LOGIN_HQL,parameterSetter.getParamMap());
    }

    @Override
    public boolean checkIfUsernameUsed(String username) {
        HqlMapParameterSetter parameterSetter = new HqlMapParameterSetter();
        parameterSetter.add(ConstUtil.DB_USERS_USERNAME,username);
        parameterSetter.addNotDeleted();
        Users users = (Users) this.findUnique(ConstUtil.USER_REGIST_CHECK_HQL,parameterSetter.getParamMap());

//        log.debug(users.getUsername());
        if(users == null){
            return false;
        }
        return true;
    }

    @Override
    public Users getUserById(String id) {
        HqlMapParameterSetter parameterSetter = new HqlMapParameterSetter();
        parameterSetter.add(ConstUtil.DB_ID,id);
        parameterSetter.addNotDeleted();
        return (Users) this.findUnique(ConstUtil.USER_GET_USER_HQL,parameterSetter.getParamMap());
    }
}

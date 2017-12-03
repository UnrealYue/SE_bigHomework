package com.iL.hotel.api.service.impl;

import com.iL.hotel.api.dao.UsersDao;
import com.iL.hotel.api.service.UsersService;
import com.iL.hotel.common.ConstUtil;
import com.iL.hotel.common.utils.BaseObjectResult;
import com.iL.hotel.pojo.Users;
import com.iL.hotel.pojo.UsersLoginResult;
import com.iL.hotel.pojo.UsersRegistResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersDao usersDao;

    @Override
    @Transactional
    public void userRegist(String username, String password, BaseObjectResult<UsersRegistResult> result) {
        UsersRegistResult registResult = new UsersRegistResult();
        Users users = new Users(username,password);
        if(this.usersDao.checkIfUsernameUsed(username)){
            result.construct(registResult,false, ConstUtil.HAVE_REGISTER);
            return;
        }
        if(users.checkValid()) {
            this.usersDao.save(users);
            result.construct(registResult,true,ConstUtil.REGISTER_SUCCESS);
            return;
        }
    }

    @Override
    @Transactional
    public void userLogin(String username, String password, BaseObjectResult<UsersLoginResult> result) {
        UsersLoginResult loginResult = new UsersLoginResult();
        Users users = this.usersDao.userLogin(username,Users.getEncryptedPassword(username,password));
        if(users == null){
            result.construct(loginResult,false,ConstUtil.LOGIN_WRONG_PASSWORD);
            return;
        }
        result.construct(loginResult,true,ConstUtil.LOGIN_SUCCESS);
    }

    @Override
    @Transactional
    public void getUserById(String id, BaseObjectResult<Users> result) {
        Users users = this.usersDao.getUserById(id);
        result.construct(users);
    }
}

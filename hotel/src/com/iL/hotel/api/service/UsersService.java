package com.iL.hotel.api.service;

import com.iL.hotel.common.utils.BaseObjectResult;
import com.iL.hotel.pojo.Users;
import com.iL.hotel.pojo.UsersLoginResult;
import com.iL.hotel.pojo.UsersRegistResult;

public interface UsersService {
    public void userRegist(String username, String password, BaseObjectResult<UsersRegistResult> result);
    public void userLogin(String username, String password, BaseObjectResult<UsersLoginResult> result);
    public void getUserById(String id, BaseObjectResult<Users> result);
}

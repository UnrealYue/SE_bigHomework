package com.iL.hotel.api.dao;

import com.iL.hotel.dao.HibernateBaseDao;
import com.iL.hotel.pojo.Users;

public interface UsersDao extends HibernateBaseDao<Users,String>{

    public Users userLogin(final String username, final String password);

    public boolean checkIfUsernameUsed(final String username);

    public Users getUserById(String id);
}

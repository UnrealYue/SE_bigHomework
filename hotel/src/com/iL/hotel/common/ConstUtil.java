package com.iL.hotel.common;

public class ConstUtil {

    //接口地址常量
    public static final String BASE_INTERFACE = "/";

    //管理地址常量
    public static final String LOGIN = "/login";
    public static final String REGIST = "/regist";
    public static final String VIP = "/VIP";
    public static final String GET_ALL_MEMBERS = "/GetAllMembers";
    public static final String ADD_VIP ="/AddVIP";
    public static final String DELETE_VIPS ="/deleteVIPs";
    public static final String EDIT_VIPS="/EditVIPs";
    public static final String CHECKED_ORDERS="/CheckedOrders";
    public static final String GET_CHECKEDORDERS_INFO="/GetCheckedOrdersInfo";
    public static final String CHECKOUT_BY_ROOMID="/CheckOutByRoomID";
    public static final String GET_TIME_FOR_CHECKOUT="/getTimeForCheckOut";
    public static final String CHECKOUT="/CheckOut";
    public static final String ADD_ORDERDAYS="/addOrderDays";

    //视图常量
    public static final String BASE_RESULT_NAME = "result";
    public static final String MODEL_TOKEN_ATTRIBUTE = "token";
    public static final String ADMIN_REDIRECT_VIEW = "redirect:admin";
    public static final String INDEX_VIEW = "index";
    public static final String LOGIN_VIEW = "login";
    //public static final String

    //Hql常量
    //用户登录
    public static final String USER_LOGIN_HQL = "from Users where username = :username and " +
            "password = :password and is_deleted = :isDeleted";
    public static final String USER_REGIST_CHECK_HQL = "from Users where username = :username and is_deleted = :isDeleted";
    public static final String USER_GET_USER_HQL = "select new com.iL.hotel.pojo.Users(id,username,password) form Users where id = :id and is_deleted = :isDeleted";

    //数据库通用字段
    public static final String DB_ID = "id";
    public static final String IS_DELETED = "isDeleted";
    public static final Integer DELETED = 1;
    public static final Integer NOT_DELETED = 0;

    //Users常量
    public static final String DB_USERS_USERNAME = "username";
    public static final String DB_USERS_PASSWORD = "password";

    //返回消息常量
    public static final String LOGIN_SUCCESS = "登录成功";
    public static final String LOGIN_WRONG_PASSWORD = "账号/密码错误";
    public static final String HAVE_REGISTER = "账号已经被注册";
    public static final String REGISTER_SUCCESS = "注册成功";

    //VIP常量
    public static final String GET_VIP_BYGUESTID="from VipEntity where  guestByGuestId.guestId =:gid ";
    public static final String GET_GUEST_BYGUESTID="from GuestEntity  WHERE guestId = :gid ";
    //Order常量
    public static final String GET_ORDER_BYORDERID="from OrderEntity  where orderId=:orderID";
    //Order_Room常量
    //public static final String GET_OR_BYROOMIDLIST="from OrderRoomEntity where roomByRoomId.roomId=:rid ";
    public static final String GET_CHECKEDOR="from OrderRoomEntity where orderByOrderId.isChecked=:a and orderByOrderId.isFinished=:b and orderByOrderId.isDeleted=:c ";
    public static final String GET_OR_BYORDERID="from OrderRoomEntity where orderByOrderId.orderId=:orderID ";

}

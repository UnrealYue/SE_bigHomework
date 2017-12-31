package com.iL.hotel.api.web;

import com.iL.hotel.api.dao.OrderDAO;
import com.iL.hotel.api.dao.Order_RoomDAO;
import com.iL.hotel.api.dao.impl.OrderDAOImpl;
import com.iL.hotel.api.dao.impl.Order_RoomDAOImpl;
import com.iL.hotel.api.service.CheckedOrderService;
import com.iL.hotel.api.service.VIPService;
import com.iL.hotel.pojo.CheckedOrderInfoForShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.iL.hotel.common.ConstUtil.*;
@Controller
@RequestMapping(CHECKED_ORDERS)
public class CheckedOrderController {
    @Autowired
    private CheckedOrderService checkedOrderService;

    @RequestMapping(GET_CHECKEDORDERS_INFO)
    @ResponseBody
    public List<CheckedOrderInfoForShow> getCheckedOrdersInfo(){
        ModelAndView modelAndView=new ModelAndView("/page/checkedOrders.jsp");
        List<CheckedOrderInfoForShow> checkedOrderInfoForShowList=checkedOrderService.getCheckedOrderInfo();
        return   checkedOrderInfoForShowList;
    }


    @RequestMapping(GET_TIME_FOR_CHECKOUT)
    @ResponseBody
    public String getCheckOutTime(){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new java.sql.Timestamp(new Date().getTime())) ;
    }

    @RequestMapping(CHECKOUT)
    public ModelAndView checkOut(Integer ids, String DATE, Integer method,String note){

        checkedOrderService.checkOut(ids, Timestamp.valueOf(DATE),method,note);
        return new ModelAndView("/page/checkedOrders.jsp");
    }

@RequestMapping(ADD_ORDERDAYS)
    public ModelAndView addOrderDays(Integer id,Integer days){
    checkedOrderService.addOrderDays(id, days);
    return new ModelAndView("/page/checkedOrders.jsp");
}


}

package com.iL.hotel.api.web;


import com.iL.hotel.api.dao.OrderDAO;
import com.iL.hotel.api.dao.Order_RoomDAO;
import com.iL.hotel.api.dao.impl.OrderDAOImpl;
import com.iL.hotel.api.dao.impl.Order_RoomDAOImpl;
import com.iL.hotel.api.service.VIPService;

import com.iL.hotel.api.service.impl.VIPServiceImpl;
import com.iL.hotel.pojo.VIPForShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import static com.iL.hotel.common.ConstUtil.*;

@Controller
@RequestMapping(VIP)
public class VIPController {
    @Autowired
    private VIPService vipService;

    @RequestMapping(GET_ALL_MEMBERS)
    @ResponseBody
    public List<VIPForShow> getAllVIPs(){
        List<VIPForShow> vips= vipService.getAllVIPs();
        return vips;
    }

    @RequestMapping(ADD_VIP)
    public ModelAndView addVIP(int guestId,java.sql.Date birthday, String job, double balance){
        //HttpServletRequest t=request;
        //List vips= vipService.getAllVIPs();
        vipService.addVIP(guestId,birthday,job,balance);
        ModelAndView modelAndView = new ModelAndView("/page/vip.jsp");
        return modelAndView;
    }

    @RequestMapping(DELETE_VIPS)
    public ModelAndView deleteVIPs(Integer[] ids){
       //int[] i=ids;
        //
        vipService.deleteVIP(ids);
        ModelAndView modelAndView = new ModelAndView("/page/vip.jsp");
        return modelAndView;
    }


    @RequestMapping(EDIT_VIPS)
    public ModelAndView editVIPs(Integer[] ids, java.sql.Date birthday, String job, double balance){
        vipService.editVIP(ids[0], birthday, job,  balance);
        ModelAndView modelAndView = new ModelAndView("/page/vip.jsp");
        return modelAndView;
    }
}

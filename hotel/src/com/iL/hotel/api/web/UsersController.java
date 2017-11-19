package com.iL.hotel.api.web;

import com.iL.hotel.api.service.UsersService;
import com.iL.hotel.common.ConstUtil;
import com.iL.hotel.common.utils.BaseObjectResult;
import com.iL.hotel.pojo.model.UsersLoginResult;
import com.iL.hotel.pojo.model.UsersRegistResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.iL.hotel.common.ConstUtil.*;

@Controller
@RequestMapping(BASE_INTERFACE)
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(LOGIN)
    public ModelAndView login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpSession session){
        ModelAndView modelAndView;
        BaseObjectResult<UsersLoginResult> result = new BaseObjectResult<UsersLoginResult>();
        this.usersService.userLogin(username, password, result);
        if (result.isSuccess()){
            modelAndView = new ModelAndView(ConstUtil.ADMIN_REDIRECT_VIEW);
            session.setAttribute(ConstUtil.MODEL_TOKEN_ATTRIBUTE, result.getData().getToken());
            return modelAndView;
        } else {
            modelAndView = new ModelAndView(ConstUtil.LOGIN_VIEW);
            modelAndView.addObject(ConstUtil.BASE_RESULT_NAME,result);
            return modelAndView;
        }
    }

    @ResponseBody
    @RequestMapping(REGIST)
    public BaseObjectResult<UsersRegistResult> regist(@RequestParam String username, @RequestParam String password, HttpServletRequest request){
        BaseObjectResult<UsersRegistResult> result = new BaseObjectResult<UsersRegistResult>();
        this.usersService.userRegist(username, password, result);
        return result;
    }

}

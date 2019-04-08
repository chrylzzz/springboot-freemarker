package com.lnsoft.controller;

import com.lnsoft.freemarkerConfig.SortMethod;
import com.lnsoft.mapper.TQsRzMapper;
import com.lnsoft.mapper.TQsUserMapper;
import com.lnsoft.po.*;
import com.lnsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chr on 2018/12/7/0007.
 */
@Controller
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TQsUserMapper tQsUserMapper;
    @Autowired
    private TQsRzMapper tQsRzMapper;

    @RequestMapping(value = "/index")
    public List<User> index() {
        ModelAndView mav = new ModelAndView("index");
        return userService.queryUserInfoAll();
    }

    @RequestMapping(value = "/demo")
    public ModelAndView demo() {
        List<User> userList = userService.queryUserInfoAll();
        Map<String, Object> map = new HashMap<>();
        //viewName习惯为对应页面的名字
        ModelAndView mav = new ModelAndView("demo");

        map.put("java", "Nihao");
        map.put("php", "asd");
        mav.addObject("userList", userList);
        mav.addObject("map", map);
        mav.addObject("intVal", 100);
        return mav;
    }

    //自定义排序
    @RequestMapping(value = "/sort")
    public ModelAndView sort() {
        ModelAndView mav = new ModelAndView();
        //实现fm自定义函数
        mav.addObject("sort_int", new SortMethod());
        return mav;
    }





    ////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/sendPhoneOrEmail/{type}/{number}")
    @ResponseBody
    //接收手机号或者邮箱，type为注册类型  手机号或者类型
    public Map<String,Object> sendPhoneOrEmail(@PathVariable String type,@PathVariable String number){
            Map<String,Object> map = new HashMap<>();
            //虽然前端进行判断了手机号码和验证码不为空，但是我们也进行二次判断  防止恶意注册
            if(StringUtils.isEmpty(type)||StringUtils.isEmpty(number)){
                map.put("500","注册类型或者注册号为空");
                //返回前端
                return map;
            }
            //判断用户是否已经存在
            TQsUserExample tQsUserExample = new TQsUserExample();
            tQsUserExample.createCriteria().andUsernameEqualTo(number);
            List<TQsUser> tQsUser = tQsUserMapper.selectByExample(tQsUserExample);

            if(tQsUser.size()>0){
                map.put("500","对不起，用户名已存在");
                //返回前端
                return map;
            }
            map.put("200","注册成功！");
            return map;
        }
    @RequestMapping(value = "/sendNumber")
    @ResponseBody
    //接收手机号或者邮箱，type为注册类型  手机号或者类型
    public Map<String,Object> sendNumber(HttpServletRequest request){
        //获取用户名
        String username = request.getParameter("username").toString();
        //获取密码
        String password = request.getParameter("password").toString();
        //获取注册类型
        String type = request.getParameter("type").toString();

        Map<String,Object> map = new HashMap<>();
        //虽然前端进行判断了手机号码和验证码不为空，但是我们也进行二次判断  防止恶意注册
        if(StringUtils.isEmpty(type)||StringUtils.isEmpty(username)||StringUtils.isEmpty(type)){
            map.put("500","注册类型或者注册号获取密码为空");
            //返回前端
            return map;
        }
        //防止恶意用户频繁注册，一天只允许一个用户注册5次
        TQsRzExample tQsRzExample = new TQsRzExample();
        if("1".equals(type)){
            tQsRzExample.createCriteria().andTypeEqualTo(type).andPhoneEqualTo(username).
                    andTimeEqualTo(new Date());
        }else{
            tQsRzExample.createCriteria().andTypeEqualTo(type).andEmaiEqualTo(username).
                    andTimeEqualTo(new Date());
        }
        int count = tQsRzMapper.countByExample(tQsRzExample);

        if(count>=5){
            map.put("500","今日注册已上限，请明天再试！");
            //返回前端
            return map;
        }
        //如果没有什么问题的话 我们先插入验证码日志表，其次我们在插入用户表
        TQsUser tQsUser = new TQsUser();

        TQsRz tQsRz = new TQsRz();
        tQsRz.setType(type);
        if("1".equals(type)){
            tQsRz.setPhone(username);
        }else{
            tQsRz.setEmai(password);
        }
        //设置注册时间为今天
        tQsRz.setTime(new Date());
        //密码要进行加密
        tQsUser.setPassword(password);
        //设置用户表
        tQsUser.setUsername(username);
        //插入日志表
        tQsRzMapper.insert(tQsRz);
        //插入用户表
        tQsUserMapper.insert(tQsUser);
        //ooooookkkkkk
        map.put("200","注册成功，您的用户名为:"+username);
        //返回前端
        return map;
    }
}

package com.msb.controller;

import com.msb.dao.ResponseResult;
import com.msb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/user")
    @ResponseBody
    public ResponseResult getUser(HttpServletRequest request){
        //从请求头中获取accessToken
        String accessToken = request.getHeader("Authorization");
        return userService.getUserByAccessToken(accessToken);
    }
}

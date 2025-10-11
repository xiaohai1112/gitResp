package com.msb.controller;

import com.msb.dao.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class TestController {
    @RequestMapping("/test")
    @ResponseBody
    public String test01(){
        return "test.......";
    }
    @GetMapping("/authTest")
    @ResponseBody
    public ResponseResult authTest(){
        return ResponseResult.success("auth 1");
    }
    @GetMapping("/noauthTest")
    @ResponseBody
    public ResponseResult noAuthTest(){
        return ResponseResult.success("no auth 1");
    }
}

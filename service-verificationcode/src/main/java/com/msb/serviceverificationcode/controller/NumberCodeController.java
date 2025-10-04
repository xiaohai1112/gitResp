package com.msb.serviceverificationcode.controller;

import com.msb.dao.ResponseResult;
import com.msb.responese.NumberCodeResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Map;

@Controller
public class NumberCodeController {
    @GetMapping("/numberCode/{size}")
    @ResponseBody
    public ResponseResult NumberCode(@PathVariable int size){
        System.out.println(size);
        //生成验证码
        double vc = (Math.random() * 9 + 1) * Math.pow(10, size - 1);
        int vvc=(int)vc;
        System.out.println("验证码："+vvc);
        //返回值
        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
        numberCodeResponse.setNumberCode(vvc);
        return ResponseResult.success(numberCodeResponse);
    }
}

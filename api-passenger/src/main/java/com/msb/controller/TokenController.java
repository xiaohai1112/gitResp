package com.msb.controller;

import com.msb.dao.ResponseResult;
import com.msb.dao.TokenResponse;
import com.msb.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TokenController {
    @Autowired
    private TokenService tokenService;
    @PostMapping("/token-refresh")
    @ResponseBody
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){
        String refreshToken = tokenResponse.getRefreshToken();
        System.out.println("old token"+refreshToken);
        return tokenService.refreshToken(refreshToken);
    }
}

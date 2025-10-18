package com.msb.servicemap.contorller;

import com.msb.dao.ResponseResult;
import com.msb.servicemap.service.ServiceFromMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {
    @Autowired
    private ServiceFromMapService serviceFromMapService;
    @PostMapping("/service/add")
    public ResponseResult add(String name){
        return serviceFromMapService.addService(name);
    }
}

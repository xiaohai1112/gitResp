package com.msb.servicemap.service;

import com.msb.dao.ResponseResult;
import com.msb.servicemap.remote.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceFromMapService {
    @Autowired
    private ServiceClient serviceClient;
    public ResponseResult addService(String name){
        return serviceClient.service(name);
    }
}

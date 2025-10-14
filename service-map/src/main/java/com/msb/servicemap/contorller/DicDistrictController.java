package com.msb.servicemap.contorller;

import com.msb.dao.DicDistrict;
import com.msb.dao.ResponseResult;
import com.msb.servicemap.mapper.DistrictMapper;
import com.msb.servicemap.service.DicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DicDistrictController {
    @Autowired
    private DicDistrictService districtService;
    @GetMapping("/district")
    public ResponseResult district(String keywords){
        return districtService.dicDistrict(keywords);
    }

//    @Autowired
//    private DistrictMapper districtMapper;
//    @RequestMapping("/test")
//    @ResponseBody
//    public String test(){
//        Map<String,Object> map=new HashMap<>();
//        map.put("address_code","110010");
//        List<DicDistrict> dicDistricts = districtMapper.selectByMap(map);
//        System.out.println(dicDistricts);
//        return "test-map";
//    }
}

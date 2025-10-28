package com.msb.servicemap.contorller;


import com.msb.dao.ResponseResult;
import com.msb.responese.TerminalResponse;
import com.msb.responese.TracksResponese;
import com.msb.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/terminal")
public class TerminalController {
    @Autowired
    private TerminalService terminalService;

    /**
     * 添加轨迹
     * @param name
     * @param desc
     * @return
     */
    @PostMapping("/add")
    public ResponseResult<TerminalResponse> add(String name,String desc){
        return terminalService.add(name ,desc);
    }
    @PostMapping("/aroundsearch")
    public ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius){
        return terminalService.aroundsearch(center,radius);
    }

    /**
     * 查询轨迹
     * @param tid
     * @param starttime
     * @param endtime
     * @return
     */
    @PostMapping("/trsearch")
    public ResponseResult<TracksResponese> trsearch(String tid, Long starttime, Long endtime){
        return terminalService.trsearch(tid,starttime,endtime);
    }
}

package com.msb.servicemap.service;

import com.msb.dao.ResponseResult;
import com.msb.responese.TerminalResponse;
import com.msb.responese.TracksResponese;
import com.msb.servicemap.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {
    @Autowired
    private TerminalClient terminalClient;
    public ResponseResult<TerminalResponse> add(String name,String desc){
        return terminalClient.add(name,desc);
    }
    public ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius){
        return terminalClient.aroundsearch(center,radius);
    }
    public ResponseResult<TracksResponese> trsearch(String tid, Long starttime, Long endtime){
        return terminalClient.trsearch(tid,starttime,endtime);
    }
}

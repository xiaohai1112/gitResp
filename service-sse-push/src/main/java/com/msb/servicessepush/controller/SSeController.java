package com.msb.servicessepush.controller;

import com.msb.Utils.SsePlushPrefixUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SSeController {
    public static Map<String,SseEmitter> sseEmitterMap=new HashMap<>();

    /**
     * 建立连接
     * @param userId
     * @param identy
     * @return
     */
    @GetMapping("/connect")
    public SseEmitter connect(@RequestParam Long userId, @RequestParam String identy){
        System.out.println("driverId:"+userId+"identy"+identy);
        String sseEmitterMapKey= SsePlushPrefixUtils.getConnect(userId,identy);
        SseEmitter sseEmitter = new SseEmitter(0L);
        sseEmitterMap.put(sseEmitterMapKey,sseEmitter);
        return sseEmitter;
    }

    /**
     * 发送消息
     * @param userId
     * @param identy
     * @param content
     * @return
     */
    @GetMapping("/push")
    public String push(@RequestParam Long userId, @RequestParam String identy,@RequestParam String content){
        String sseEmitterMapKey= SsePlushPrefixUtils.getConnect(userId,identy);
        try {
            if (sseEmitterMap.containsKey(sseEmitterMapKey)){
                sseEmitterMap.get(sseEmitterMapKey).send(content);
            }else {
                return "用户不存在";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "给用户"+sseEmitterMapKey+",发送了消息"+content;
    }
    @GetMapping("/close")
    public String ssclose(@RequestParam Long userId, @RequestParam String identy){
        String sseEmitterMapKey= SsePlushPrefixUtils.getConnect(userId,identy);
        System.out.println("关闭"+sseEmitterMapKey);
        if (sseEmitterMap.containsKey(sseEmitterMapKey)){
            sseEmitterMap.remove(sseEmitterMapKey);
        }
        return "移除成功";
    }
}

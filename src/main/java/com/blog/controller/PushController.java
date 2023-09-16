package com.blog.controller;

import com.blog.cache.ClientCache;
import com.blog.domain.MessageInfo;
import com.blog.service.PrivateMsgService;
import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/push")
@CrossOrigin
public class PushController {
    @Resource
    private ClientCache clientCache;
    @Autowired
    PrivateMsgService privateMsgService;

    @GetMapping("/user/{userId}")
    public String pushTuUser(@PathVariable("userId") String userId){
        HashMap<UUID, SocketIOClient> userClient = clientCache.getUserClient(userId);
        userClient.forEach((uuid, socketIOClient) -> {
            //向客户端推送消息
            socketIOClient.sendEvent("chatevent",new MessageInfo());
        });
        return "success";
    }
}

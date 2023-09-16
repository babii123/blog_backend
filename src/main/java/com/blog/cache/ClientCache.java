package com.blog.cache;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassNameClientCache
 * @Description TODO 缓存用户 - 页面sessionId - 通道连接
 * @Author DELL
 * @Date 2022/1/2113:55
 * @Version 1.0
 **/
@Component
public class ClientCache {
    /**
     * TODO 用户信息缓存
     */
    private static Map<String, HashMap<UUID, SocketIOClient>> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * TODO userId-用户ID | sessionId-页面sessionId | socketIOClient-页面对应的通道连接
     * @param userId
     * @param sessionId
     * @param socketIOClient
     */
    public void saveClient(String userId,UUID sessionId,SocketIOClient socketIOClient){
        // 判断之前是否连接过
        HashMap<UUID, SocketIOClient> sessionIdClientCache = concurrentHashMap.get(userId);
        // 未连接
        if(sessionIdClientCache == null){
            sessionIdClientCache = new HashMap<>();
        }
        sessionIdClientCache.put(sessionId,socketIOClient);
        concurrentHashMap.put(userId,sessionIdClientCache);
    }

    /**
     * TODO 获取用户的页面通道信息
     * @param userId
     * @return
     */
    public HashMap<UUID,SocketIOClient> getUserClient(String userId){
        return concurrentHashMap.get(userId);
    }

    /**
     * TODO 根据用户Id及页面sessionID删除页面通道连接
     * @param userId
     * @param sessionId
     */
    public void deleteSessionClientByUserId(String userId,UUID sessionId){
        concurrentHashMap.get(userId).remove(sessionId);
    }

    /**
     * TODO 根据用户Id删除用户通道连接缓存 暂无使用
     * @param userId
     */
    public void deleteUserCacheByUserId(String userId){
        concurrentHashMap.remove(userId);
    }
}

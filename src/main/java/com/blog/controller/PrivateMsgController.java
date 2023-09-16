package com.blog.controller;

import com.blog.cache.ClientCache;
import com.blog.domain.MessageInfo;
import com.blog.domain.PrivateMsg;
import com.blog.service.PrivateMsgService;
import com.blog.utils.JWTUtils;
import com.blog.vo.*;
import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/private_msg")
@CrossOrigin
public class PrivateMsgController {
    @Autowired
    PrivateMsgService privateMsgService;

    @Resource
    private ClientCache clientCache;
    /**
     * 获取指定 两个用户 之间的聊天记录
     * @return
     */
    @GetMapping("/getHistoricalChat/{senderId}/{receiverId}")
    public Result getHistoricalChat(@PathVariable String senderId,@PathVariable String receiverId){
        Result result = new Result();
        result.setData(privateMsgService.getHistoricalChat(senderId,receiverId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Find_Success);
        return result;
    }

    /**
     * 发送一条聊天记录
     * @return
     */
    @PostMapping("/sendPrivateMsg")
    public Result sendPrivateMsg(@RequestBody MessageInfo messageInfo){
        // 存入数据库，状态为未读，返回的PrivateMsg实体返回给前端进行渲染
        PrivateMsg privateMsg = new PrivateMsg();
        privateMsg.setMessageContent(messageInfo.getMessage());
        privateMsg.setSenderId(messageInfo.getSenderId());
        privateMsg.setReceiverId(messageInfo.getReceiverId());
        privateMsg.setStatus(PrivateMsgStatus.UN_READ);
        PrivateMsg privateMsg1 = privateMsgService.sendMsgInsert(privateMsg);
        HashMap<UUID, SocketIOClient> userClient = clientCache.getUserClient(messageInfo.getReceiverId());
        // 想发送的人不在线
        if (userClient==null){
            System.out.println(messageInfo.getReceiverId()+"不在线");
        }else{
            userClient.forEach((uuid, socketIOClient) -> {
                //向客户端推送消息
                socketIOClient.sendEvent("chatevent",privateMsg1);
            });
        }
        Result result = new Result();
        if (privateMsg1==null){
            result.setCode(Code.SAVE_ERR);
            result.setMsg(Msg.Request_Fail);
        }else {
            result.setCode(Code.SAVE_OK);
            result.setMsg(Msg.Request_Success);
        }
        result.setData(privateMsg1);
        return result;
    }

    /**
     * 删除一条记录，需要记录删除的是，是发送者删除的消息还是接收者发送的消息(前端判断)
     * @return
     */
    @DeleteMapping("/delPrivateMsg/{id}/{identity}")
    public Result delPrivateMsg(@PathVariable Integer id,@PathVariable String identity){
        boolean res = privateMsgService.delPrivateMsg(id,identity);
        Result result = new Result();
        result.setData(res);
        if(res){
            result.setCode(Code.DELETE_OK);
            result.setMsg(Msg.Del_Success);
        }else{
            result.setCode(Code.DELETE_ERR);
            result.setMsg(Msg.Del_Fail);
        }
        return result;
    }

    /**
     * 获取未读的私信数量
     * @param userId
     * @return
     */
    @GetMapping("/getUnReadCount/{userId}")
    public Result getUnReadCount(@PathVariable String userId){
//        System.out.println(JWTUtils.getUserId(token));
//        String userId = JWTUtils.getUserId(token);
        Integer count = privateMsgService.getUnReadCount(userId);
        Result result = new Result(Code.GET_OK,Msg.Find_Success,count);
        return result;
    }

    /**
     * 所有消息设置已读
     * @param participantId
     * @param userId
     * @return
     */
    @GetMapping("/setPrivateMsgRead/{userId}/{participantId}")
    public Result setPrivateMsgRead(@PathVariable String participantId,@PathVariable String userId){
        System.out.println(participantId+userId);
        if (privateMsgService.updatePrivateMsgStatus(participantId,userId)){
            Result result = new Result(Code.UPDATE_OK,Msg.Change_Success,true);
            return result;
        }else{
            Result result = new Result(Code.UPDATE_OK,Msg.Change_Success,false);
            return result;
        }
    }
}

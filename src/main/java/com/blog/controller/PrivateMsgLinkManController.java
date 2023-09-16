package com.blog.controller;

import com.blog.domain.PrivateMsgLinkMan;
import com.blog.service.PrivateMsgLinkManService;
import com.blog.vo.Code;
import com.blog.vo.LinkManVO;
import com.blog.vo.Msg;
import com.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private_msg_link_man")
@CrossOrigin
public class PrivateMsgLinkManController {
    @Autowired
    PrivateMsgLinkManService privateMsgLinkManService;

    /**
     * 获取联系人列表，只获取能查到聊天记录的联系人
     * @param userId
     * @return
     */
    @GetMapping("/getLinkManList/{userId}/{participantId}")
    public Result getLinkManList(@PathVariable String userId,@PathVariable String participantId){
        System.out.println(userId);
        List<LinkManVO> linkManVOList = privateMsgLinkManService.getLinkManList(userId,participantId);
        System.out.println(linkManVOList);
        Result result = new Result();
        result.setData(linkManVOList);
        result.setMsg(Msg.Request_Success);
        result.setCode(Code.GET_OK);
        return result;
    }

    /**
     * 设置置顶
     * @return
     */
    @GetMapping("/setPlaceTop/{userId}/{friendId}")
    public Result setPlaceTop(@PathVariable String userId, @PathVariable String friendId){
        boolean res = privateMsgLinkManService.updatePlaceTop(userId, friendId);
        Result result = new Result();
        result.setData(res);
        if(res){
            result.setMsg(Msg.Change_Success);
        }else{
            result.setMsg(Msg.Change_Fail);
        }
        return result;
    }

    /**
     * 删除和联系人的所有聊天记录
     * @return
     */

    public Result delHistoryChat(@PathVariable String userId, @PathVariable String friendId){
        return null;
    }

    /**
     * 修改消息提示，提示或者免打扰
     * @return
     */
    @GetMapping("/setNeedInit/{userId}/{friendId}")
    public Result setNeedInit(@PathVariable String userId, @PathVariable String friendId){
        boolean res = privateMsgLinkManService.updateNeedInit(userId, friendId);
        Result result = new Result();
        result.setData(res);
        if(res){
            result.setCode(Code.UPDATE_OK);
            result.setMsg(Msg.Change_Success);
        }else{
            result.setCode(Code.UPDATE_ERR);
            result.setMsg(Msg.Change_Fail);
        }
        return result;
    }

    /**
     * 获取聊天人的基本信息
     * @param userId
     * @param friendId
     * @return
     */
    @GetMapping("/getLinkManInfo/{userId}/{friendId}")
    public Result getLinkManInfo(@PathVariable String userId,@PathVariable String friendId){
        System.out.println(userId+friendId);
        Result result = new Result();
        result.setData(privateMsgLinkManService.getLinkManInfo(userId, friendId));
        result.setMsg(Msg.Find_Success);
        result.setCode(Code.GET_OK);
        return result;
    }

    /**
     * 插入一位联系人 关系
     * @param privateMsgLinkMan
     * @return
     */
    @PostMapping("/insertLinkMan")
    public Result insertLinkMan(@RequestBody PrivateMsgLinkMan privateMsgLinkMan){
        System.out.println(privateMsgLinkMan);
        boolean res = privateMsgLinkManService.insertLinkMan(privateMsgLinkMan);
        if (res){
            return new Result(Code.SAVE_OK,Msg.Change_Success,res);
        }else {
            return new Result(Code.SAVE_ERR, Msg.Change_Fail, res);
        }
    }
}

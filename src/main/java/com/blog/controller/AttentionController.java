package com.blog.controller;

import com.blog.vo.Code;
import com.blog.vo.Msg;
import com.blog.vo.Result;
import com.blog.service.AttentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attention")
@CrossOrigin
public class AttentionController {
    @Autowired
    AttentionService attentionService;

    public Result isAttention(){
        return null;
    }

    /**
     * 获取用户的关注者列表
     * @param framerId
     * @return
     */
    @GetMapping("/getFollow/{framerId}")
    public Result getFollowListByFramerId(@PathVariable String framerId){
        Result result = new Result();
        result.setData(attentionService.getFollowListByFramerId(framerId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }

    /**
     * 获取用户被关注的列表
     * @param framerId
     * @return
     */
    @GetMapping("/getBeFollow/{framerId}")
    public Result getBeFollowListByFramerId(@PathVariable String framerId){
        Result result = new Result();
        result.setData(attentionService.getBeFollowListByFramerId(framerId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Find_Success);
        return result;
    }

    /**
     * 点击关注的功能，关注了则取消关注，没关注就关注
     * @param framerId
     * @param beFramerId
     * @return
     */
    @GetMapping("/clickFollow/{framerId}/{beFramerId}")
    public Result followFramer(@PathVariable String framerId,@PathVariable String beFramerId){
        Result result = new Result();
        result.setData(attentionService.followFramer(framerId, beFramerId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Change_Success);
        return result;
    }
}

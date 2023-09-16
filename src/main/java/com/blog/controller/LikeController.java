package com.blog.controller;

import com.blog.vo.Code;
import com.blog.vo.Msg;
import com.blog.vo.Result;
import com.blog.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@CrossOrigin
public class LikeController {
    @Autowired
    LikeService likeService;

    /**
     * 获取指定id用户的点赞文章列表
     * @param framerId
     * @return
     */
    @GetMapping("/getListByFramerId/{framerId}")
    public Result getLikeListByFramerId(@PathVariable String framerId){
        Result result = new Result();
        result.setData(likeService.getLikeListByFramerId(framerId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Find_Success);
        return result;
    }

    @GetMapping("/clickLike/{framerId}/{articleId}")
    public Result likeArticle(@PathVariable String framerId,@PathVariable String articleId){
        Result result = new Result();
        result.setData(likeService.likeArticle(framerId, articleId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Change_Success);
        return result;
    }
}

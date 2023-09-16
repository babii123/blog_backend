package com.blog.controller;

import com.blog.service.CollectService;
import com.blog.vo.Code;
import com.blog.vo.Msg;
import com.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collect")
@CrossOrigin
public class CollectController {
    @Autowired
    CollectService collectService;
    /**
     * 点击收藏
     * @param framerId
     * @param articleId
     * @return
     */
    @GetMapping("/clickCollect/{framerId}/{articleId}")
    public Result collectFramer(@PathVariable String framerId, @PathVariable String articleId){
        Result result = new Result();
        result.setData(collectService.collectArticle(framerId, articleId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Change_Success);
        return result;
    }
}

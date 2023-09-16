package com.blog.controller;

import com.blog.vo.*;
import com.blog.service.ArticleService;
import com.blog.service.AttentionService;
import com.blog.service.CollectService;
import com.blog.service.FramerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/framer")
@CrossOrigin
@ResponseBody
public class FramerController {
    @Autowired
    private FramerService framerService;
    @Autowired
    private AttentionService attentionService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CollectService collectService;

    /**
     * 获取用户列表
     * @return
     */
    @GetMapping
    public Result getFramerList(){
        Result result = new Result();
        result.setData(framerService.getFramerList());
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Find_Success);
        return result;
    }

    /**
     * 文章详情页显示用户卡片,framerID 是登录用户 framerP 是作者
     * @param framerI
     * @param framerP
     * @return
     */
    @GetMapping("/getFramerCardInfo/{framerI}/{framerP}")
    public Result getStatistics(@PathVariable String framerI,@PathVariable String framerP){
        System.out.println("framerI="+framerI);
        System.out.println("framerP="+framerP);
        Result result = new Result();
        FramerWorkDataStatisticsVO fwds = framerService.getStatistics(framerP);
        fwds.setIsAttention(attentionService.isAttention(framerI,framerP));
        System.out.println(fwds);
        result.setData(fwds);
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Find_Success);
        return result;
    }

    /**
     * 个人中心页，数据获取
     */
    @GetMapping("/getCenterInfo/{id}")
    public Result getCenterInfo(@PathVariable String id){
        Result result = new Result();
        FramerInfoVO fiv= framerService.getCenterInfo(id);
        fiv.setBeViewed(articleService.getBeViewedByFramerId(id));
        fiv.setBeLiked(articleService.getBeLikedByFramerId(id));
        fiv.setFollowCount(attentionService.getBeFollowCountByFramerId(id));
        fiv.setBeFollowCount(attentionService.getBeFollowCountByFramerId(id));
        fiv.setCollectionSet(collectService.getCollectionSetByFramerId(id));
        System.out.println(fiv);
        result.setData(fiv);
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Find_Success);
        return result;
    }

    /**
     * 编辑资料前先获取资料
     * @param framerId
     * @return
     */
    @GetMapping("/getEditInfo/{framerId}")
    public Result getFramerEditInfo(@PathVariable String framerId){
        Result result = new Result();
        result.setData(framerService.getFramerEditInfo(framerId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Find_Success);
        return result;
    }

    /**
     * 更新个人资料
     * @param editVo
     * @return
     */
    @PostMapping ("/updateEditInfo")
    public Result getFramerEditInfo(@RequestBody FramerEditVo editVo){
        System.out.println(editVo);
        Result result = new Result();
        result.setData(framerService.updateFramerEditInfo(editVo));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Change_Success);
        return result;
    }
}

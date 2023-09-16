package com.blog.controller;

import com.blog.domain.Article;
import com.blog.service.AttentionService;
import com.blog.service.CollectService;
import com.blog.service.LikeService;
import com.blog.vo.*;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    AttentionService attentionService;
    @Autowired
    LikeService likeService;
    @Autowired
    CollectService collectService;

    @RequestMapping("/test")
    public String test() {
        return "Hello";
    }

    @PostMapping("/addArticle")
    public Result addArticle(@RequestBody Article article){
        Result result = new Result();
        result.setData(articleService.addArticle(article));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }

    @DeleteMapping("/delArticle/{articleId}")
    public Result delArticle(@PathVariable String articleId){
        Result result = new Result();
        result.setData(articleService.deleteArticle(articleId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }

    @GetMapping("/getArticle/{articleId}")
    public Result getArticle(@PathVariable String articleId){
        Result result = new Result();
        result.setData(articleService.getArticle(articleId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }

    @PostMapping("/updateArticle")
    public Result updateArticle(@RequestBody Article article){
        Result result = new Result();
        result.setData(articleService.updateArticle(article));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }

    /**
     * 获取首页文章，分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/{page}/{pageSize}")
    public Result getArticleList(@PathVariable Integer page, @PathVariable Integer pageSize){
        System.out.println("page="+page);
        System.out.println("pageSize="+pageSize);
        Result result = new Result();
        result.setData(articleService.getArticleList(page,pageSize));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }

    /**
     * 通过id获取文章列表，并分页查询
     * @param typeId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/{typeId}/{page}/{pageSize}")
    public Result getArticleListByTypeId(@PathVariable String typeId,@PathVariable Integer page,@PathVariable Integer pageSize){
        Result result = new Result();
        result.setData(articleService.getArticleListById(typeId, page, pageSize));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }
    /**
     * 获取所有文章的总数
     * @return
     */
    @GetMapping("/getCount")
    public Result getCountAll(){
        Result result = new Result();
        result.setData(articleService.getCountAll());
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }

    /**
     * 获取指定ID文章的总数
     * @return
     */
    @GetMapping("/getCount/{typeId}")
    public Result getCountAllById(@PathVariable String typeId){
        Result result = new Result();
        try{
            result.setData(articleService.getCountAllById(typeId));
            result.setCode(Code.GET_OK);
            result.setMsg(Msg.Request_Success);
        }catch (Exception e){
            System.out.println(e);
            result.setData(null);
            result.setCode(Code.GET_ERR);
            result.setMsg(Msg.Request_Fail);
        }finally {
            return result;
        }
    }

    /**
     * 根据文章id获取文章详情，用于文章详情页展示
     * @return
     */
    @GetMapping("/getDetailInfo/{id}")
    public Result getDetailInfoById(@PathVariable String id){
        Result result = new Result();
        result.setData(articleService.getArticleById(id));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }
    @GetMapping("/center/{framer_id}")
    public Result getArticleListByFramerId(@PathVariable String framer_id){
        System.out.println("framer_id="+framer_id);
        Result result = new Result();
        result.setData(articleService.getArticleListByFramerId(framer_id));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }

    /**
     * 文章详情页，显示用户是否关注点赞收藏了文章
     * @param framerId 关注者id，就是登录的用户id
     * @param beFramerId 被关注者id，文章的作者
     * @param articleId 文章id
     * @return
     */
    @GetMapping("/getRelate/{framerId}/{beFramerId}/{articleId}")
    public Result getRelateByFramerId(@PathVariable String framerId, @PathVariable String beFramerId,@PathVariable String articleId){
        Result result = new Result();
        DetailRelateVO detailRelateVO = new DetailRelateVO();
        detailRelateVO.setIsAttention(attentionService.isAttention(framerId,beFramerId));
        detailRelateVO.setIsLike(likeService.isLike(framerId,articleId));
        detailRelateVO.setIsCollect(collectService.isCollect(framerId, articleId));
        result.setData(detailRelateVO);
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }

    /**
     * 统计各个类型文章的数目
     * @return
     */
    @GetMapping("/getCountByType")
    public Result getCountAllByType(){
        Result result = new Result();
        result.setData(articleService.getCountAllByType());
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }
    /**
     * 统计各个类型文章的数目，按照用户发布的
     * @return
     */
        @GetMapping("/getCountAllByFramerId/{framerId}")
    public Result getCountAllByFramerId(@PathVariable String framerId){
        Result result = new Result();
        result.setData(articleService.getCountAllByFramerId(framerId));
        result.setCode(Code.GET_OK);
        result.setMsg(Msg.Request_Success);
        return result;
    }
}

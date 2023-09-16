package com.blog.service;

import com.blog.domain.Framer;
import com.blog.vo.FramerEditVo;
import com.blog.vo.FramerInfoVO;
import com.blog.vo.FramerWorkDataStatisticsVO;

import java.util.List;

public interface FramerService {

    /**
     * 注册
     * @param framer
     */
    public boolean addFramer(Framer framer);

    /**
     * 用户名登录
     * @param username
     * @param password
     */
    public boolean framerLogin(String username,String password);

    /**
     * 根据Id删除用户
     * @return
     */
    public boolean deleteFramerById();

    /**
     * 获取所有用户信息的列表
     * @return
     */
    public List<Framer> getFramerList();

    /**
     * 获取指定id的作者的 被点赞数 被阅读数
     * @return
     */
    public FramerWorkDataStatisticsVO getStatistics(String id);

    /**
     * 个人中心数据获取
     * @param id
     * @return
     */
    public FramerInfoVO getCenterInfo(String id);

    /**
     * 编辑个人资料前获取个人信息
     * @param framerId
     * @return
     */
    public FramerEditVo getFramerEditInfo(String framerId);

    /**
     * 编辑个人资料
     * @param editVo
     * @return
     */
    public boolean updateFramerEditInfo(FramerEditVo editVo);
}

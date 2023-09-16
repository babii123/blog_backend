package com.blog.service;

import com.blog.vo.FramerAttentionVO;

import java.util.List;

public interface AttentionService {
    /**
     * 查找用户 1 是否关注了 用户 2
     * @param framerI
     * @param framerP
     * @return
     */
    public boolean isAttention(String framerI,String framerP);

    /**
     * 获取指定用户 关注了多少人
     * @param id
     * @return
     */
    public Integer getFollowCountByFramerId(String id);

    /**
     * 获取指定用户 被多少人关注
     * @param id
     * @return
     */
    public Integer getBeFollowCountByFramerId(String id);

    /**
     * 获取指定用户关注的 用户列表
     * @param framer_id
     * @return
     */
    public List<FramerAttentionVO> getFollowListByFramerId(String framer_id);

    /**
     * 获取指定用户被关注的 用户列表
     * @param framer_id
     * @return
     */
    public List<FramerAttentionVO> getBeFollowListByFramerId(String framer_id);

    /**
     * 关注功能
     * @param FramerI 关注人
     * @param FramerP 被关注人
     * @return
     */
    public boolean followFramer(String FramerI,String FramerP);
}

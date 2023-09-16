package com.blog.dao;

import com.blog.vo.FramerAttentionVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AttentionDao {
    /**
     * 判断两者之间是否有关注关系
     * @param framerI
     * @param framerP
     * @return
     */
    @Select("select count(attention_time) from attention where framer_id_i = #{framer_id_i} and framer_id_p = #{framer_id_p} and is_del = 1")
    public boolean isAttention(@Param("framer_id_i") String framerI, @Param("framer_id_p") String framerP);

    /**
     * 获取关注者的数量
     * @param id
     * @return
     */
    @Select("select ifnull(count(framer_id_p),0) as `count` from attention where framer_id_i = #{id}")
    public Integer getFollowCountByFramerId(String id);

    /**
     * 获取被关注数量
     * @param id
     * @return
     */
    @Select("select ifnull(count(framer_id_i),0) as `count` from attention where framer_id_p = #{id}")
    public Integer getBeFollowCountByFramerId(String id);
    @Select("select id as framerId,`name`as framerName,img_path as framerAvatar,introduce " +
            "from framer where id in (select framer_id_p from attention where framer_id_i = #{framerId})")
    public List<FramerAttentionVO> getFollowListByFramerId(@Param("framerId") String framer_id);
    @Select("select id as framerId,`name`as framerName,img_path as framerAvatar,introduce " +
            "from framer where id in (select framer_id_i from attention where framer_id_p = #{framerId})")
    public List<FramerAttentionVO> getBeFollowListByFramerId(@Param("framerId") String framer_id);

    /**
     * 关注功能
     * @param framerI
     * @param framerP
     * @return
     */
    @Insert("insert into attention (framer_id_i, framer_id_p, attention_time,is_del) values (#{framer_id_i},#{framer_id_p},NOW(),1) " +
            "on duplicate key update is_del = abs(is_del -1)")
    public boolean followFramer(@Param("framer_id_i") String framerI, @Param("framer_id_p") String framerP);
}

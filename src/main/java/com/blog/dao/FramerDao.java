package com.blog.dao;

import com.blog.domain.Framer;
import com.blog.vo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FramerDao {
    /**
     * 新增用户
     * @param framer
     */
    @Insert("insert into framer(id,`name`,recentTime,`password`,email,phone,registerTime) values (#{id},#{name},NOW(),#{password},#{email},#{phone},NOW())")
    public boolean addFramer(Framer framer);

    /**
     * 删除一个用户
     */
    @Delete("delete from framer where id = #{id}")
    public void deleteFramer();

    /**
     * 更新用户信息
     */
    @Update("update framer set name = #{name} where id = #{id}")
    public void updateFramer();

    /**
     * 查询用户列表
     * @return
     */
    @Select("select * from framer")
    public List<Framer> getFramerList();

    /**
     * 登录
     * @param nameOrEmailOrPhone
     * @param password
     * @return
     */
    @Select("select id as framerId,img_path as framerImg from framer where (name = #{nameOrEmailOrPhone} or email = #{nameOrEmailOrPhone} or phone=#{nameOrEmailOrPhone}) and password = #{password}")
    public LoginInfoVo getIdByPassAndNEP(@Param("nameOrEmailOrPhone") String nameOrEmailOrPhone, @Param("password") String password);

    /**
     * 获取获赞数，被阅读数
     * @param id
     * @return
     */
    @Select("select ifnull(sum(like_count),0) as beLiked,ifnull(sum(view_count),0) as beViewed from article where framer_id = #{id}")
    public FramerWorkDataStatisticsVO getStatistics(String id);

    /**
     * 获取个人中心需要展示的用户信息
     * @param id
     * @return
     */
    @Select("select `name` as framerName,\n" +
            "img_path as framerAvatar,\n" +
            "introduce,\n" +
            "DATE_FORMAT(registerTime,'%Y-%m-%d %H:%i:%s') as registerTime \n" +
            "from framer where id = #{id} ")
    public FramerInfoVO getFramerCenterInfo(String id);

    /**
     * 获取个人资料
     * @param framerId
     * @return
     */
    @Select("select id as framerId,name,email,phone,introduce from framer where id = #{framerId}")
    public FramerEditVo getFramerEditInfo(String framerId);

    /**
     * 修改个人资料
     * @param editVo
     * @return
     */
    @Update("update framer set name=#{name},email=#{email},phone=#{phone},introduce=#{introduce} where id = #{framerId}")
    public boolean updateFramerEditInfo(FramerEditVo editVo);
}

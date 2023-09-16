package com.blog.dao;

import com.blog.domain.Article;
import com.blog.domain.PrivateMsg;
import com.blog.domain.PrivateMsgLinkMan;
import com.blog.vo.LinkManInfoVO;
import com.blog.vo.LinkManVO;
import com.blog.vo.PrivateMsgFramer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PrivateMsgLinkManDao {
    /**
     * 查询：private_msg_link_man表中的信息列表
     * @param userId
     * @return
     */
    @Select("SELECT friend_id AS friendId,user_name AS userName,avatar,need_init AS needInit,place_top AS placeTop FROM private_msg_link_man \n" +
            "WHERE user_id = #{userId} AND (\n" +
            "friend_id IN (SELECT receiver_id FROM private_message WHERE sender_id = #{userId} ) \n" +
            "OR \n" +
            "friend_id IN (SELECT sender_id FROM private_message WHERE receiver_id = #{userId})\n" +
            "OR\n" +
            "friend_id = #{participantId})\n"+
            "ORDER BY place_top")
    List<LinkManVO> selectListByUserId(@Param("userId") String userId,@Param("participantId") String participantId);

    /**
     * 查询：private_msg_link_man表中的信息列表
     * @param userId
     * @return
     */
    @Select("SELECT need_init AS needInit,place_top AS placeTop " +
            "FROM private_msg_link_man WHERE user_id = #{userId} and friend_id = #{friendId} LIMIT 1")
    LinkManVO selectOneByUserId(@Param("userId") String userId,@Param("friendId") String friendId);

    /**
     * 查找与这个联系人最近的一条聊天的信息
     * @param senderId
     * @param receiverId
     * @return
     */
    @Select("SELECT send_time AS sendTime,message_content AS messageContent from `private_message` WHERE (sender_id = #{senderId} AND receiver_id = #{receiverId}) OR (sender_id = #{receiverId} AND receiver_id = #{senderId})\n" +
            "ORDER BY ABS(UNIX_TIMESTAMP(send_time)-UNIX_TIMESTAMP(NOW())) LIMIT 1")
    PrivateMsg selectMsgRecent(@Param("senderId") String senderId, @Param("receiverId") String receiverId);

    /**
     * 查找未读消息的数量
     * @param senderId
     * @param receiverId
     * @return
     */
    @Select("SELECT COUNT(id) as num FROM private_message WHERE sender_id = #{senderId} AND receiver_id = #{receiverId} AND status = 0")
    Integer selectUnread(@Param("senderId") String senderId, @Param("receiverId") String receiverId);

    /**
     * 修改是否置顶
     * @param userId
     * @param friendId
     * @return
     */
    @Update("UPDATE private_msg_link_man SET place_top = abs(place_top-1) WHERE user_id = #{userId} AND friend_id = #{friendId}")
    boolean updatePlaceTop(@Param("userId") String userId,@Param("friendId") String friendId);

    /**
     * 修改是否置顶
     * @param userId
     * @param friendId
     * @return
     */
    @Update("UPDATE private_msg_link_man SET need_init = abs(need_init-1) WHERE user_id = #{userId} AND friend_id = #{friendId}")
    boolean updateNeedInit(@Param("userId") String userId,@Param("friendId") String friendId);

    /**
     * 查联系人信息
     * @param friendId
     * @return
     */
    @Select("SELECT name AS userName,img_path AS avatar FROM framer WHERE id = #{friendId}")
    LinkManInfoVO getLinkManInfo(@Param("friendId") String friendId);

    /**
     * 插入一个联系人
     * @return
     */
    @Insert("INSERT INTO `react_blog`.`private_msg_link_man` (`user_id`, `friend_id`, `user_name`, `avatar`, `con_status`)\n" +
            "SELECT #{userId}, #{friendId}, #{userName}, #{avatar}, #{conStatus}\n" +
            "FROM DUAL \n" +
            "WHERE NOT EXISTS(SELECT user_id FROM private_msg_link_man WHERE user_id = #{userId} AND friend_id = #{friendId})")
    boolean insertLinkMan(PrivateMsgLinkMan privateMsgLinkMan);

    @Select("select id as framerId,img_path as framerAvatar,name as framerName from framer where id =#{id}")
    PrivateMsgFramer getFramerInfo(@Param("id") String id);
}


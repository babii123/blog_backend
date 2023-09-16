package com.blog.dao;

import com.blog.domain.PrivateMsg;
import com.blog.vo.HistoryChatVO;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface PrivateMsgDao {

    /**
     * 登录用户想要删除自己发的消息
     * @param id
     * @return
     */
    @Update("UPDATE private_message SET `status` = (\n" +
            "CASE \n" +
            "\tWHEN `status` IN (0,1) THEN `status` = 2\n" +
            "\tWHEN `status` = 3 THEN `status` = 4\n" +
            "\tELSE\n" +
            "\t\t`status`\n" +
            "END CASE;\n" +
            ") WHERE id = #{id}")
    boolean updateStatusDelOne(@Param("id") Integer id);

    /**
     * 登录用户想要删除别人发的消息
     * @param id
     * @return
     */
    @Update("UPDATE private_message SET `status` = (\n" +
            "CASE \n" +
            "\tWHEN `status` IN (0,1) THEN `status` = 2\n" +
            "\tWHEN `status` = 2 THEN `status` = 4\n" +
            "\tELSE\n" +
            "\t\t`status`\n" +
            "END CASE;\n" +
            ") WHERE id = #{id}")
    boolean updateStatusDelTwo(@Param("id") Integer id);

    /**
     * 查一对一的用户聊天记录
     * @param senderId
     * @param receiverId
     * @return
     */
    @Select("SELECT id,sender_id AS senderId,receiver_id AS receiverId,message_type AS messageType,message_content AS messageContent, send_time AS sendTime,`status` " +
            "FROM private_message WHERE " +
            "(sender_id = #{senderId} AND receiver_id = #{receiverId} AND STATUS IN (0,1,3)) " +
            "OR " +
            "(sender_id = #{receiverId} AND receiver_id = #{senderId} AND STATUS IN (0,1,2)) " +
            "ORDER BY send_time")
    List<HistoryChatVO> selectHistoricalChat(@Param("senderId")String senderId, @Param("receiverId") String receiverId);

    /**
     * 插入一条聊天记录，状态为未读
     * @param privateMsg
     * @return
     */
    @Insert("INSERT INTO `react_blog`.`private_message` " +
            "(`sender_id`, `receiver_id`, `message_type`, `message_content`, `send_time`, `status`) " +
            "VALUES " +
            "( #{senderId}, #{receiverId}, 1, #{messageContent}, NOW(), 0);")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
//    @Options(useGeneratedKeys = true,keyProperty = "sendTime",keyColumn = "send_time")
//    @Result()
    void insertPrivateMsg(PrivateMsg privateMsg);
    @Select("select send_time as sendTime from private_message where id = #{id}")
    Date selectSendTime(@Param("id") Integer id);

    /**
     * 当登录用户(receiverId)登入 与某个用户(senderId)的聊天框后 将消息标记为 已读
     * @param senderId
     * @return
     */
    @Update("UPDATE private_message SET `status` = 1 WHERE sender_id = #{senderId} AND receiver_id = #{receiverId} AND send_time < NOW();")
    boolean updatePrivateMsgStatus(@Param("senderId") String senderId,@Param("receiverId") String receiverId);

    /**
     * 获取未读的私信数
     * @param userId
     * @return
     */
    @Select("select count(id) from private_message where TRIM(receiver_id) = #{userId} and `status` = 0")
    int getUnReadCount(@Param("userId") String userId);

}

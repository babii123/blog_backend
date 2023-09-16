package com.blog.service.impl;

import com.blog.dao.PrivateMsgDao;
import com.blog.domain.PrivateMsg;
import com.blog.service.PrivateMsgService;
import com.blog.vo.HistoryChatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateMsgServiceImpl implements PrivateMsgService {
    @Autowired
    PrivateMsgDao privateMsgDao;

    /**
     * 查询登录用户的聊天记录
     * @param senderId
     * @param receiverId
     * @return
     */
    @Override
    public List<HistoryChatVO> GetHistoricalChat(String senderId,String receiverId) {
        return null;
    }

    /**
     * 删除一条消息
     * @param id
     * @param identity
     * @return
     */
    @Override
    public boolean delPrivateMsg(Integer id, String identity) {
        boolean result = false;
        // 登录用户想要删除自己给别人发的消息
        if (identity.equals("sender")){
            result = privateMsgDao.updateStatusDelOne(id);
        }
        // 登录用户想要删除别人给自己发的消息
        else if(identity.equals("receiver")){
            result = privateMsgDao.updateStatusDelTwo(id);
        }
        return result;
    }

    /**
     * 查找一对一的聊天记录
     * @param senderId
     * @param receiverId
     * @return
     */
    @Override
    public List<HistoryChatVO> getHistoricalChat(String senderId, String receiverId) {
        return privateMsgDao.selectHistoricalChat(senderId,receiverId);
    }

    /**
     * 将一条聊天记录插入数据库
     * @param privateMsg
     * @return
     */
    @Override
    public PrivateMsg sendMsgInsert(PrivateMsg privateMsg) {
        try{
            privateMsgDao.insertPrivateMsg(privateMsg);
//            privateMsg.setId(id);
            privateMsg.setSendTime(privateMsgDao.selectSendTime(privateMsg.getId()));
            privateMsg.setStatus(0);
            privateMsg.setMessageType(1);
            System.out.println(privateMsg);
            return privateMsg;
        } catch (NullPointerException e){
            System.out.println(e);
            return new PrivateMsg();
        }
    }

    /**
     * 获取用户未读私信
     * @param userId
     * @return
     */
    @Override
    public Integer getUnReadCount(String userId) {
        System.out.println(userId);
        Integer count = privateMsgDao.getUnReadCount(userId);
        System.out.println("未读数量："+count);
        return count;
    }

    /**
     * 进入聊天，所有消息标记为已读
     * @param senderId
     * @param receiverId
     * @return
     */
    @Override
    public boolean updatePrivateMsgStatus(String senderId, String receiverId) {
        return privateMsgDao.updatePrivateMsgStatus(senderId, receiverId);
    }
}

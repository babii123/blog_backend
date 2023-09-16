package com.blog.service.impl;

import com.blog.dao.AttentionDao;
import com.blog.dao.PrivateMsgLinkManDao;
import com.blog.domain.PrivateMsg;
import com.blog.domain.PrivateMsgLinkMan;
import com.blog.service.PrivateMsgLinkManService;
import com.blog.vo.LinkManInfoVO;
import com.blog.vo.LinkManVO;
import com.blog.vo.PrivateMsgFramer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PrivateMsgLinkManServiceImpl implements PrivateMsgLinkManService {
    @Autowired
    PrivateMsgLinkManDao privateMsgLinkManDao;

    @Autowired
    AttentionDao attentionDao;
    /**
     * 获取指定用户的联系人列表
     * @param senderId
     * @return
     */
    @Override
    public List<LinkManVO> getLinkManList(String senderId,String participantId) {
        try{
            List<LinkManVO> linkManVOList = privateMsgLinkManDao.selectListByUserId(senderId,participantId);
            System.out.println(linkManVOList);
            for(LinkManVO linkManVO : linkManVOList){
                System.out.println(senderId+'-'+linkManVO.getFriendId());
                PrivateMsg privateMsg = privateMsgLinkManDao.selectMsgRecent(senderId,linkManVO.getFriendId());
                System.out.println("哈哈哈哈3");
                Integer unreadNum = privateMsgLinkManDao.selectUnread(linkManVO.getFriendId(),senderId);
                System.out.println("unreadNum："+unreadNum);
                System.out.println(privateMsg);
                linkManVO.setRecentTime(privateMsg.getSendTime());
                linkManVO.setRecentMsg(privateMsg.getMessageContent());
                linkManVO.setUnreadCount(unreadNum);
                return linkManVOList;
            }
        }catch (NullPointerException e){
            System.out.println("获取指定用户的联系人列表为空"+e);
        }
        return null;
    }

    /**
     * 修改置顶
     * @param userId
     * @param friendId
     * @return
     */
    @Override
    public boolean updatePlaceTop(String userId, String friendId) {
        boolean result = privateMsgLinkManDao.updatePlaceTop(userId,friendId);
        return result;
    }

    /**
     * 修改提示
     * @param userId
     * @param friendId
     * @return
     */
    @Override
    public boolean updateNeedInit(String userId, String friendId) {
        boolean result = privateMsgLinkManDao.updateNeedInit(userId, friendId);
        return result;
    }

    /**
     * 返回在聊人的信息
     * @param userId
     * @param friendId
     * @return
     */
    @Override
    public LinkManInfoVO getLinkManInfo(String userId, String friendId){
        // 在framer表中查username和头像
        LinkManInfoVO linkManInfoVO = privateMsgLinkManDao.getLinkManInfo(friendId);
        // 判断friendId是否关注了userId，未关注只能发一条消息
        linkManInfoVO.setAttention(attentionDao.isAttention(friendId,userId));
        linkManInfoVO.setFriendId(friendId);
        System.out.println("PrivateMsgLinkManServiceImpl.getLinkManInfo"+linkManInfoVO);
        try {
            LinkManVO linkManVO = privateMsgLinkManDao.selectOneByUserId(userId, friendId);
            linkManInfoVO.setPlaceTop(linkManVO.getPlaceTop());
            linkManInfoVO.setNeedInit(linkManVO.getNeedInit());
        }catch (NullPointerException e){
            linkManInfoVO.setPlaceTop(0);
            linkManInfoVO.setNeedInit(0);
        }
        return linkManInfoVO;
    }

    /**
     * 插入一个 联系人 关系
     * @param privateMsgLinkMan
     * @return
     */

    @Override
    public boolean insertLinkMan(PrivateMsgLinkMan privateMsgLinkMan) {
        privateMsgLinkMan.setConStatus(
                attentionDao.isAttention(privateMsgLinkMan.getFriendId(), privateMsgLinkMan.getUserId())
                        ?1:0);
        boolean res = privateMsgLinkManDao.insertLinkMan(privateMsgLinkMan);
        PrivateMsgLinkMan privateMsgLinkMan1 = new PrivateMsgLinkMan();
        privateMsgLinkMan1.setUserId(privateMsgLinkMan.getFriendId());
        privateMsgLinkMan1.setFriendId(privateMsgLinkMan1.getUserId());
        PrivateMsgFramer privateMsgFramer = privateMsgLinkManDao.getFramerInfo(privateMsgLinkMan.getUserId());
        privateMsgLinkMan1.setAvatar(privateMsgFramer.getFramerAvatar());
        privateMsgLinkMan.setUserName(privateMsgFramer.getFramerName());
        privateMsgLinkMan1.setConStatus(attentionDao.isAttention(privateMsgLinkMan.getUserId(), privateMsgLinkMan.getFriendId())
                ?1:0);
        privateMsgLinkMan1.setNeedInit(0);
        privateMsgLinkMan1.setPlaceTop(0);
        boolean res1 = privateMsgLinkManDao.insertLinkMan(privateMsgLinkMan1);
        System.out.println("pmlm，insertLinkMan：");
        return res && res1;
    }
}

package com.blog.service;

import com.blog.domain.PrivateMsg;
import com.blog.vo.HistoryChatVO;

import java.util.List;

public interface PrivateMsgService {
    List<HistoryChatVO> GetHistoricalChat(String senderId,String receiverId);
    boolean delPrivateMsg(Integer id,String identity);
    List<HistoryChatVO> getHistoricalChat(String senderId, String receiverId);
    PrivateMsg sendMsgInsert(PrivateMsg privateMsg);
    Integer getUnReadCount(String userId);
    boolean updatePrivateMsgStatus(String senderId,String receiverId);
}

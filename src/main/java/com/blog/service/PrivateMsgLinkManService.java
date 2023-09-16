package com.blog.service;

import com.blog.domain.PrivateMsgLinkMan;
import com.blog.vo.LinkManInfoVO;
import com.blog.vo.LinkManVO;

import java.util.List;

public interface PrivateMsgLinkManService {
    List<LinkManVO> getLinkManList(String userId,String participantId);

    boolean updatePlaceTop(String senderId, String receiverId);

    boolean updateNeedInit(String userId, String friendId);

    LinkManInfoVO getLinkManInfo(String userId, String friendId);

    boolean insertLinkMan(PrivateMsgLinkMan privateMsgLinkMan);
}

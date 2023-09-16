package com.blog.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PrivateMsg {
    private Integer id;
    private String senderId;
    private String receiverId;
    private Integer messageType;
    private String messageContent;
    private Date sendTime;
    private Integer status;
}

//package com.blog.domain;
//
//        import lombok.Data;
//
//        import javax.persistence.Column;
//        import javax.persistence.Entity;
//        import javax.persistence.Id;
//        import javax.persistence.Table;
//        import java.util.Date;

//@Data
//@Entity
//@Table(name = "private_message")
//public class PrivateMsg {
//    @Id
//    @Column(name = "id")
//    private Integer id;
//    @Column(name = "sender_id")
//    private String senderId;
//    @Column(name="receiver_id")
//    private String receiverId;
//    @Column(name="message_type")
//    private String messageType;
//    @Column(name = "message_content")
//    private String messageContent;
//    @Column(name = "send_time")
//    private Date sendTime;
//    @Column(name = "status")
//    private Integer status;
//}
//

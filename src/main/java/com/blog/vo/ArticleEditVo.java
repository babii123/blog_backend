package com.blog.vo;

import lombok.Data;

@Data
public class ArticleEditVo {
    private String id;
    private String title;
    private String articleContent;
    private String typeId;
    private String introduce;
}

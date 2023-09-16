package com.blog.vo;

/**
 * 文章详情页 作者卡片展示信息
 */
public class FramerWorkDataStatisticsVO {
    private boolean isAttention;
    private Integer beLiked;
    private Integer beViewed;

    public boolean getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(boolean attention) {
        isAttention = attention;
    }

    public Integer getBeLiked() {
        return beLiked;
    }

    public void setBeLiked(Integer beLiked) {
        this.beLiked = beLiked;
    }

    public Integer getBeViewed() {
        return beViewed;
    }

    public void setBeViewed(Integer beViewed) {
        this.beViewed = beViewed;
    }
}

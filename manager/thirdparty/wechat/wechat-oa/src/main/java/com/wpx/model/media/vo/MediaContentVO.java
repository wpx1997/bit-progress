package com.wpx.model.media.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author 不会飞的小鹏
 */
public class MediaContentVO {

    /**
     * 文章
     */
    @JsonProperty("news_item")
    private List<ArticleVO> newsItem;

    public List<ArticleVO> getNewsItem() {
        return newsItem;
    }

    public void setNewsItem(List<ArticleVO> newsItem) {
        this.newsItem = newsItem;
    }

    @Override
    public String toString() {
        return "MediaContentVO{" +
                "newsItem=" + newsItem +
                '}';
    }

}

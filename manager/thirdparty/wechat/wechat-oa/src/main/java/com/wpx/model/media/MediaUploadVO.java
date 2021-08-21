package com.wpx.model.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wpx.model.WechatResult;

/**
 * @author 不会飞的小鹏
 */
public class MediaUploadVO extends WechatResult {

    private MediaTypeEnum type;

    @JsonProperty("media_id")
    private String mediaId;

    @JsonProperty("created_at")
    private Integer createdAt;

    private String url;

    public MediaTypeEnum getType() {
        return type;
    }

    public void setType(String type) {
        this.type = MediaTypeEnum.nameOf(type);
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MediaUploadVO{" +
                "type=" + type +
                ", mediaId='" + mediaId + '\'' +
                ", createdAt=" + createdAt +
                ", url='" + url + '\'' +
                '}';
    }

}

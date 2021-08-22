package com.wpx.model.media.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wpx.model.WechatResult;

import java.util.List;

/**
 * @author 不会飞的小鹏
 */
public class MediaListVO extends WechatResult {

    /**
     * 该类型的素材的总数
     */
    @JsonProperty("total_count")
    private String totalCount;

    /**
     * 本次调用获取的素材的数量
     */
    @JsonProperty("item_count")
    private String itemCount;

    List<MediaItemVO> item;

}

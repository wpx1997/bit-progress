package com.wpx.model.kfsession;

import com.alibaba.fastjson.annotation.JSONField;
import com.wpx.model.WechatResult;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author 不会飞的小鹏
 * @description： 客服会话
 */
public class KfSession extends WechatResult {

    /**
     * 客服账号
     * 此接口获取一个客户的会话，如果不存在，则kf_account为空
     */
    @JSONField(name = "kf_account")
    private String kfAccount;

    /**
     * 用户openId
     */
    @JSONField(name = "openid")
    private String openId;

    private LocalDateTime createTime;

    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    @JSONField(name = "createtime")
    public void setCreateTime(Integer createTime) {
        this.createTime = LocalDateTime.ofEpochSecond(createTime, 0, ZoneOffset.ofHours(8));
    }

}
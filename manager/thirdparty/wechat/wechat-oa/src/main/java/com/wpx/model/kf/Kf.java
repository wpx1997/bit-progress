package com.wpx.model.kf;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 * @description： 客服信息
 */
public class Kf {

    /**
     * 完整客服账号，格式为：账号前缀@公众号微信号
     */
    @JSONField(name = "kf_account")
    private String kfAccount;

    /**
     * 客服头像
     */
    @JSONField(name = "kf_headimgurl")
    private String kfHeadImgUrl;

    /**
     * 客服编号
     */
    @JSONField(name = "kf_id")
    private String kfId;

    /**
     * 客服昵称
     */
    @JSONField(name = "kf_nick")
    private String kfNick;

    /**
     * 如果客服帐号已绑定了客服人员微信号， 则此处显示微信号
     */
    @JSONField(name = "kf_wx")
    private String kfWx;

    /**
     * 如果客服帐号尚未绑定微信号，但是已经发起了一个绑定邀请， 则此处显示绑定邀请的微信号
     */
    @JSONField(name = "invite_wx")
    private String inviteWx;

    /**
     * 如果客服帐号尚未绑定微信号，但是已经发起过一个绑定邀请， 邀请的过期时间，为unix 时间戳
     */
    @JSONField(name = "invite_expire_time")
    private Integer inviteExpireTime;

    /**
     * 邀请的状态，有等待确认“waiting”，被拒绝“rejected”， 过期“expired”
     */
    @JSONField(name = "invite_status")
    private String inviteStatus;

    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    public String getKfHeadImgUrl() {
        return kfHeadImgUrl;
    }

    public void setKfHeadImgUrl(String kfHeadImgUrl) {
        this.kfHeadImgUrl = kfHeadImgUrl;
    }

    public String getKfId() {
        return kfId;
    }

    public void setKfId(String kfId) {
        this.kfId = kfId;
    }

    public String getKfNick() {
        return kfNick;
    }

    public void setKfNick(String kfNick) {
        this.kfNick = kfNick;
    }

    public String getKfWx() {
        return kfWx;
    }

    public void setKfWx(String kfWx) {
        this.kfWx = kfWx;
    }

    public String getInviteWx() {
        return inviteWx;
    }

    public void setInviteWx(String inviteWx) {
        this.inviteWx = inviteWx;
    }

    public Integer getInviteExpireTime() {
        return inviteExpireTime;
    }

    public void setInviteExpireTime(Integer inviteExpireTime) {
        this.inviteExpireTime = inviteExpireTime;
    }

    public String getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(String inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

}

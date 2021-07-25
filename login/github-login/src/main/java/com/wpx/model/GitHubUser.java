package com.wpx.model;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author 不会飞的小鹏
 */
public class GitHubUser {

    /**
     * GitHub用户ID
     */
    private Long id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 头像地址
     */
    @JSONField(name = "avatar_url")
    private String avatarUrl;

    /**
     * 用户说明
     */
    private String bio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}

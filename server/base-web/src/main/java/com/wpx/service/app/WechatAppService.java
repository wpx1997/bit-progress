package com.wpx.service.app;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.exception.BaseExceptionMessage;
import com.wpx.manager.redis.WechatAppRedisService;
import com.wpx.mapper.app.WechatAppMapper;
import com.wpx.model.app.wechatapp.WechatApp;
import com.wpx.model.app.wechatapp.pojo.WechatAppRO;
import com.wpx.util.Assert;
import com.wpx.util.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.wpx.exception.BaseExceptionMessage.WECHATAPP_NOT_EXIST_EXCEPTION;

/**
* <p>
    * 微信应用信息 服务类
    * </p>
*
* @author 不会飞的小鹏
* create on 2021-08-28
*/
@Service
@Slf4j
public class WechatAppService extends ServiceImpl<WechatAppMapper, WechatApp> {

    @Autowired
    private WechatAppRedisService wechatAppRedisService;

    /**
     * 获取微信应用信息
     *
     * @param appSign
     */
    public WechatAppRO getWechatAppByAppSign(String appSign) {
        WechatAppRO wechatApp = wechatAppRedisService.getWechatApp(appSign);
        return Objects.isNull(wechatApp) ? refreshWechatApp(appSign) : wechatApp;
    }

    /**
     * 从数据库查询wechatApp的信息刷新到redis
     *
     * @param appSign
     */
    public WechatAppRO refreshWechatApp(String appSign) {
        LambdaQueryChainWrapper<WechatApp> query = lambdaQuery().eq(WechatApp::getAppSign, appSign);
        WechatApp wechatApp = getOne(query);
        Assert.notNull(wechatApp, WECHATAPP_NOT_EXIST_EXCEPTION);
        WechatAppRO wechatAppRO = BeanUtils.copyNonNullProperties(wechatApp, WechatAppRO.class);
        wechatAppRedisService.refreshWechatApp(wechatAppRO);
        return wechatAppRO;
    }

}

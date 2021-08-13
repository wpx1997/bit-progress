package com.wpx.gatewaycms.auth;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author 不会飞的小鹏
 * @Description: GatewayCmsRemoteService的fallback
 */
@Component
public class AuthFeignServiceFallback implements FallbackFactory<AuthFeignService> {

    /**
     * Returns an instance of the fallback appropriate for the given cause.
     *
     * @param cause cause of an exception.
     * @return fallback
     */
    @Override
    public AuthFeignService create(Throwable cause) {
        return null;
    }

}

package com.wpx.util;

import com.wpx.common.constant.VerifyConstant;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wpx
 * Created on 2021/2/5 10:11
 */
public class UserHelper {

    public static Long getUserId() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String userId = request.getHeader(VerifyConstant.USER_ID);
        return Long.parseLong(userId);
    }

}

package com.wpx.util;

import com.wpx.constant.StringConstants;
import com.wpx.exception.ExceptionMessage;

import java.util.Map;

/**
 * @author 不会飞的小鹏
 * @description： url拼接工具类
 */
public class UrlJoinUtils {

    /**
     * 拼接url和参数
     *
     * @param url
     * @param params
     */
    public static String urlJoinParam(String url, Map<String, String> params) {
        Assert.isNotEmpty(url, ExceptionMessage.URL_EMPTY_EXCEPTION);
        if (CollectionUtils.nonEmpty(params)) {
            StringBuilder urlBuilder = new StringBuilder(url);
            params.forEach((key, value) -> {
                boolean hasParam = url.contains(StringConstants.QUESTION_MARK);
                urlBuilder.append(hasParam ? StringConstants.AND : StringConstants.QUESTION_MARK)
                        .append(key)
                        .append(StringConstants.EQUAL_SIGN)
                        .append(value);
            });
            return urlBuilder.toString();
        }
        return url;
    }

}

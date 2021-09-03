package com.wpx.config;

import com.wpx.property.DubboServiceProperties;
import com.wpx.property.ServiceProperties;
import com.wpx.util.CollectionUtils;
import org.apache.dubbo.config.ServiceConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Map;

/**
 * @author 不会飞的小鹏
 * @desc: dubbo配置
 */
public class DubboConfig implements BeanFactoryAware {

    @Autowired
    private DubboServiceProperties dubboServiceProperties;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // 添加多个注册配置
        DefaultListableBeanFactory listableBeanFactory = (DefaultListableBeanFactory) beanFactory;
        Map<String, ServiceProperties> multiple = dubboServiceProperties.getMultiple();
        if (CollectionUtils.nonEmpty(multiple)) {
            multiple.forEach((id, config) -> {
                ServiceConfig serviceConfig = new ServiceConfig();
                serviceConfig.setInterface(config.getInterfaceName());
                serviceConfig.setRegistryIds(config.getRegistryIds());
                serviceConfig.setVersion(config.getVersion());
                listableBeanFactory.registerSingleton(id, serviceConfig);
            });
        }
    }

}

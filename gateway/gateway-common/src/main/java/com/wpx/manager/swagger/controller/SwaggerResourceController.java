package com.wpx.manager.swagger.controller;

import com.wpx.manager.swagger.provider.GatewaySwaggerResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.*;

import java.util.List;

/**
 * @author 不会飞的小鹏
 * @Description: swagger-ui访问资源controller
 */
@RestController
@RequestMapping("swagger-resources")
public class SwaggerResourceController {

    @Autowired
    private GatewaySwaggerResourceProvider gatewaySwaggerResourceProvider;

    @RequestMapping(value = "/configuration/security")
    public ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration/ui")
    public ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return new ResponseEntity<>(gatewaySwaggerResourceProvider.get(), HttpStatus.OK);
    }

}
package com.bitprogress.controller;

import com.bitprogress.auth.AuthFeignService;
import com.bitprogress.model.BooleanVO;
import com.bitprogress.model.ResultVO;
import com.bitprogress.model.login.LogoutDTO;
import com.bitprogress.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 不会飞的小鹏
 */
@Api(tags = "测试")
@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private AuthFeignService authFeignService;

    @GetMapping
    @ApiOperation("测试")
    public ResultVO<BooleanVO> test() {
        System.out.println("aaa");
        Long userId = UserUtils.getUserId();
        System.out.println(userId);
        LogoutDTO logoutDTO = new LogoutDTO(String.valueOf(userId));
        authFeignService.logout(logoutDTO);
        System.out.println(UserUtils.getUserId());
        return ResultVO.successData(BooleanVO.result(true));
    }

}

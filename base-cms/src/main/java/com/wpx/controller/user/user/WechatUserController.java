package com.wpx.controller.user.user;

import com.wpx.model.BooleanVO;
import com.wpx.model.user.wechatuser.pojo.cms.WechatUserCmsAddDTO;
import com.wpx.model.user.wechatuser.pojo.cms.WechatUserCmsQueryDTO;
import com.wpx.model.user.wechatuser.pojo.cms.WechatUserCmsUpdateDTO;
import com.wpx.model.user.wechatuser.pojo.cms.WechatUserCmsVO;
import com.wpx.service.user.WechatUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.model.ResultVO;

import javax.validation.Valid;
import java.util.Set;
import org.springframework.web.bind.annotation.RestController;

/**
* @author 不会飞的小鹏
* created on 2021-08-13
*/
@Api(tags = {"用户信息"})
@RestController
@RequestMapping("/api/base/system/wechatUser")
public class WechatUserController {

    @Autowired
    private WechatUserService wechatUserService;

    @GetMapping
    @ApiOperation("查询详情")
    public ResultVO<WechatUserCmsVO> findById(@RequestParam @ApiParam("wechatUserId") Long wechatUserId) {
        return ResultVO.successData(wechatUserService.findById(wechatUserId));
    }

    @PostMapping
    @ApiOperation("添加")
    public ResultVO<WechatUserCmsVO> save(@RequestBody @Valid WechatUserCmsAddDTO wechatUserAddDTO) {
        return ResultVO.successData(wechatUserService.saveWechatUser(wechatUserAddDTO));
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResultVO<BooleanVO> delete(@RequestParam @ApiParam("wechatUserId列表") Set<Long> wechatUserIds) {
        wechatUserService.deleteWechatUsers(wechatUserIds);
        return ResultVO.successData(BooleanVO.result(true));
    }

    @PutMapping
    @ApiOperation("修改")
    public ResultVO<WechatUserCmsVO> update(@RequestBody @Valid WechatUserCmsUpdateDTO wechatUserUpdateDTO) {
         return ResultVO.successData(wechatUserService.updateWechatUser(wechatUserUpdateDTO));
    }

    @GetMapping("page")
    @ApiOperation("分页")
    public ResultVO<IPage<WechatUserCmsVO>> page(@ModelAttribute WechatUserCmsQueryDTO wechatUserQueryDTO, Page page) {
        return ResultVO.successData(wechatUserService.findWechatUserPage(wechatUserQueryDTO,page));
    }

}


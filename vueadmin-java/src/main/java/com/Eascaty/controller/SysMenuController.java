package com.Eascaty.controller;


import cn.hutool.core.map.MapUtil;
import com.Eascaty.common.dto.SysMenuDto;
import com.Eascaty.common.lang.Result;
import com.Eascaty.entity.SysUser;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zxl
 * @since 2021-11-18
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {

    /**
     * 获取当前用户的菜单和权限信息
     * @param principal
     * @return
     */
    @GetMapping("/nav")
    public Result nav(Principal principal){
        SysUser sysUser = sysUserService.getByUsername(principal.getName());

        // 获取权限信息
        String authorityInfo = sysUserService.getUserAuthorityInfo(sysUser.getId());
        String[] authorityInfoArray = StringUtils.tokenizeToStringArray(authorityInfo, ",");


        //获取导航栏信息
       List<SysMenuDto> navs = sysMenuService.getCurrentUserNav();

        return  Result.succ(MapUtil.builder()
                .put("authoritys", authorityInfoArray)
                .put("nav",navs)
                .map()

        );
    }

}

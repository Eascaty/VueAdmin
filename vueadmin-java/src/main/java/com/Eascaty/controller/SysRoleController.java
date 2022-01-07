package com.Eascaty.controller;


import cn.hutool.core.util.StrUtil;
import com.Eascaty.common.lang.Const;
import com.Eascaty.common.lang.Result;
import com.Eascaty.entity.SysRole;
import com.Eascaty.entity.SysRoleMenu;
import com.Eascaty.entity.SysUserRole;
import com.Eascaty.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zxl
 * @since 2021-11-18
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends BaseController {



    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public Result info(@PathVariable("id") Long id) {

        SysRole sysrole = sysRoleService.getById(id);

//        获取角色相关联的菜单id
        List<SysRoleMenu> roleMenus = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("role_id", id));
        List<Long> menuIds = roleMenus.stream().map(p -> p.getMenuId()).collect(Collectors.toList());

        sysrole.setMenuIds(menuIds);
        return Result.succ(sysrole);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:role:list')")
    public Result list(String name) {

        Page<SysRole> pageData = sysRoleService.page(getPage(),
                new QueryWrapper<SysRole>()
                        .eq(StrUtil.isNotBlank(name), "name", name));
        return Result.succ(pageData);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:role:save')")
    public Result save(@Validated @RequestBody SysRole sysrole) {
        sysrole.setCreated(LocalDateTime.now());
        sysrole.setStatu(Const.STATUS_ON);

        sysRoleService.save(sysrole);
        return Result.succ(sysrole);
    }
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:role:update')")
    public Result info(@Validated @RequestBody SysRole sysrole) {

        sysrole.setUpdated(LocalDateTime.now());

        sysRoleService.updateById(sysrole);

        //      更新缓存
        sysUserService.clearUserAuthorityInfoByRoleId(sysrole.getId());
        return Result.succ(sysrole);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @Transactional
    public Result info(@RequestBody Long[] ids) {

        sysRoleService.removeByIds(Arrays.asList(ids));

        sysUserRoleService.remove(new QueryWrapper<SysUserRole>().in("role_id",ids));
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().in("role_id",ids));

//      缓存同步删除
        Arrays.stream(ids).forEach(id->{
            sysUserService.clearUserAuthorityInfoByRoleId(id);
        });


        return Result.succ("删除成功");
    }

     @Transactional
    @PostMapping("/perm/{roleId}")
     @PreAuthorize("hasAuthority('sys:role:perm')")
    public Result info(@PathVariable("roleId") Long roleId,@RequestBody Long[] menuIds) {

        List<SysRoleMenu> sysRoleMenus = new ArrayList<>();

        Arrays.stream(menuIds).forEach(menuId ->{
            SysRoleMenu rolemenu = new SysRoleMenu();
            rolemenu.setMenuId(menuId);
            rolemenu.setRoleId(roleId);

            sysRoleMenus.add(rolemenu);
        });

//        先删除原来的记录，再保存新的
        sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("role_id",roleId));
        sysRoleMenuService.saveBatch(sysRoleMenus);

//        删除缓存
        sysUserService.clearUserAuthorityInfoByRoleId(roleId);

        return Result.succ(menuIds);
    }

}

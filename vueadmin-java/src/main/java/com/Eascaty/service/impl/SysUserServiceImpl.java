package com.Eascaty.service.impl;

import com.Eascaty.entity.SysMenu;
import com.Eascaty.entity.SysRole;
import com.Eascaty.entity.SysRoleMenu;
import com.Eascaty.entity.SysUser;
import com.Eascaty.mapper.SysMenuMapper;
import com.Eascaty.mapper.SysUserMapper;
import com.Eascaty.service.SysMenuService;
import com.Eascaty.service.SysRoleService;
import com.Eascaty.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zxl
 * @since 2021-11-18
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysUserMapper sysuserMapper;

    @Autowired
    SysMenuService sysmenuService;


    @Override
    public SysUser getByUsername(String username) {

        return getOne(new QueryWrapper<SysUser>().eq("username",username));

    }

    @Override
    public String getUserAuthorityInfo(Long userId) {

//        ROLE_admin,ROLE_normal,sys:user:list,....
        String authority = "";

//        获取角色编码
       List<SysRole> roles = sysRoleService.list(new QueryWrapper<SysRole>()
                .inSql("id","select role_id from sys_user_role where user_id = " +userId));

        if(roles.size() >0){
            String roleCodes = roles.stream().map(r -> "ROLE_"+r.getCode()).collect(Collectors.joining(","));
            authority = roleCodes.concat(",");

        }
//        获取菜单操作编码
        List<Long> menuIds =   sysuserMapper.getNavMenuIds(userId);
        if(menuIds.size() > 0){

            List<SysMenu> menus = sysmenuService.listByIds(menuIds);
            String menuPerms = menus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));

            authority =authority.concat(menuPerms);


        }

        return authority;



    }
}

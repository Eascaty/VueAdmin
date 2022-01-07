package com.Eascaty.service.impl;

import com.Eascaty.entity.SysRole;
import com.Eascaty.mapper.SysRoleMapper;
import com.Eascaty.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zxl
 * @since 2021-11-18
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {


    @Override
    public List<SysRole> listRolesByUserId(Long userId) {
        List<SysRole> sysRoles = this.list(new QueryWrapper<SysRole>()
                .inSql("id", "select role_id from sys_user_role where user_id = " + userId));

        return sysRoles;

    }
}

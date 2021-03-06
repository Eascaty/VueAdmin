package com.Eascaty.service.impl;

import cn.hutool.json.JSONUtil;
import com.Eascaty.common.dto.SysMenuDto;
import com.Eascaty.entity.SysMenu;
import com.Eascaty.entity.SysUser;
import com.Eascaty.mapper.SysMenuMapper;
import com.Eascaty.mapper.SysUserMapper;
import com.Eascaty.service.SysMenuService;
import com.Eascaty.service.SysRoleService;
import com.Eascaty.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public List<SysMenuDto> getCurrentUserNav() {
        String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserService.getByUsername(username);

        List<Long> menuIds = sysUserMapper.getNavMenuIds(sysUser.getId());

        List<SysMenu> menus = this.listByIds(menuIds);

//        转树状结构
        List<SysMenu> menuTree = buildTreeMenu(menus);
//        实体转Dto
        return convert(menuTree);

        



    }

    @Override
    public List<SysMenu> tree() {

//        获取所有菜单信息
        List<SysMenu> sysMenus = this.list(new QueryWrapper<SysMenu>().orderByAsc("orderNum"));
//        转成树状结构
        return buildTreeMenu(sysMenus);
    }

    private List<SysMenuDto> convert(List<SysMenu> menuTree) {
        List<SysMenuDto> menuDtos = new ArrayList<SysMenuDto>();

        menuTree.forEach(m->{
            SysMenuDto dto = new SysMenuDto();

            dto.setId(m.getId());

            dto.setName(m.getPerms());

            dto.setTitle(m.getName());

            dto.setComponent(m.getComponent());
            dto.setPath(m.getPath());

            if(m.getChildren().size() > 0) {

//               子节点调用当前方法进行再次转换
                dto.setChildren(convert(m.getChildren()));
            }

            menuDtos.add(dto);

        });
            return menuDtos;
    }






    private List<SysMenu> buildTreeMenu(List<SysMenu> menus) {
        List<SysMenu> finalMenus = new ArrayList<>();


        //先各自寻找到各自的孩子
        for (SysMenu menu : menus){

            for(SysMenu e : menus){
                if(menu.getId().equals(e.getParentId())){
                    menu.getChildren().add(e);
                }
            }
        if(menu.getParentId() ==0L ){
            finalMenus.add(menu);
        }
        }

        System.out.println(JSONUtil.toJsonStr(finalMenus));
        //提取出父节点


        return finalMenus;
    }


}

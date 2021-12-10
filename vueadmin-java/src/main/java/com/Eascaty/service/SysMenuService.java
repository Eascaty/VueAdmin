package com.Eascaty.service;

import com.Eascaty.common.dto.SysMenuDto;
import com.Eascaty.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zxl
 * @since 2021-11-18
 */

public interface SysMenuService extends IService<SysMenu> {

    List<SysMenuDto> getCurrentUserNav();

    List<SysMenu> tree();


}

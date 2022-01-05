package com.Eascaty.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 实现标准序列化，跟前端的menutree格式对上
 * {
 * name: 'SysUser',
 * title: '用户管理',
 * icon: 'el-icon-s-custom',
 * path: '/sys/users',
 * component: 'sys/User',
 * children: []
 * },
 */

@Data
public class SysMenuDto implements Serializable {

    private Long id;
    private String name;
    private String title;
    private String icon;
    private String path;
    private String component;
    private List<SysMenuDto> children = new ArrayList<>();




}

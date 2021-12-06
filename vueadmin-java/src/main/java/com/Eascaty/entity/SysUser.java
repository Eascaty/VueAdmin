package com.Eascaty.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zxl
 * @since 2021-11-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity  {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String avatar;

    private String email;

    private String city;

    private LocalDateTime lastLogin;


}

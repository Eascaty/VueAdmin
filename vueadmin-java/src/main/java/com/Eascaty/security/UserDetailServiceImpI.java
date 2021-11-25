package com.Eascaty.security;

import com.Eascaty.entity.SysUser;
import com.Eascaty.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpI implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      SysUser sysUser = sysUserService.getByUsername(username);
      if(sysUser == null){
          throw  new UsernameNotFoundException("用户名或密码不正确");
      }
        return null;
    }
}

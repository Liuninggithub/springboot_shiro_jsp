package com.ln.springboot_shiro_jsp.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.interlink.mng.manager.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboReferenceConfiguration {

    @Autowired
    private RegistryConfig registryConfig;

    @Autowired
    private ApplicationConfig applicationConfig;


    @Bean
    public SysUserInfoManager userInfoManager() {
        return (SysUserInfoManager) getBean(SysUserInfoManager.class);
    }
    @Bean
    public SysUserRoleManager sysUserRoleManager() {
        return (SysUserRoleManager) getBean(SysUserRoleManager.class);
    }
    @Bean
    public SysRoleManager sysRoleManager() {
        return (SysRoleManager) getBean(SysRoleManager.class);
    }
    @Bean
    public SysRolePermissionManager sysRolePermissionManager() {
        return (SysRolePermissionManager) getBean(SysRolePermissionManager.class);
    }
    @Bean
    public SysPermissionManager sysPermissionManager() {
        return (SysPermissionManager) getBean(SysPermissionManager.class);
    }





    public Object getBean(Class<?> interfaceClass) {
        ReferenceConfig<Object> reference = new ReferenceConfig<>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(applicationConfig);
        reference.setRegistry(registryConfig); // 多个注册中心可以用setRegistries()
        reference.setInterface(interfaceClass);
        return reference.get();
    }

}

package com.ln.springboot_shiro_jsp.config;

import com.interlink.mng.domain.*;
import com.interlink.mng.dto.SysRolePermissionReq;
import com.interlink.mng.dto.SysUserRoleReq;
import com.interlink.mng.manager.*;
//import com.ln.springboot_shiro_jsp.domain.SysPermission;
//import com.ln.springboot_shiro_jsp.domain.SysRole;
//import com.ln.springboot_shiro_jsp.domain.UserInfo;
//import com.ln.springboot_shiro_jsp.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {


    @Autowired
    private SysUserInfoManager userInfoManager;

    @Autowired
    private SysUserRoleManager sysUserRoleManager;

    @Autowired
    private SysRoleManager sysRoleManager;

    @Autowired
    private SysRolePermissionManager sysRolePermissionManager;

    @Autowired
    private SysPermissionManager sysPermissionManager;

    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        /**
         * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
         * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
         * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
         * 缓存过期之后会再次执行。
         */
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
//        +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        List<SysUserRole> userRoleList = sysUserRoleManager.findByCondition("shiro", new SysUserRoleReq(userInfo.getUid()),1,100).getModel();
        for (SysUserRole userRole : userRoleList){
            // 根据roleid查询role信息
            SysRole sysRole = sysRoleManager.get("shiro", userRole.getRoleId()).getModel();
            // 添加角色信息
            authorizationInfo.addRole(sysRole.getRole());
            // 根据 roleID查询关联权限
            List<SysRolePermission> rolePermissionList = (List<SysRolePermission>) sysRolePermissionManager.findByCondintion("shiro",new SysRolePermissionReq(sysRole.getId()),1,100).getModel();
            for (SysRolePermission rolePermission : rolePermissionList ){
                // 根据permissionId查询权限
                SysPermission sysPermission = sysPermissionManager.get("shiro",rolePermission.getPermissionId()).getModel();
                // 添加权限信息
                authorizationInfo.addStringPermission(sysPermission.getPermission());
            }
        }


//        +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


        return authorizationInfo;
    }


    /**
     * 认证信息.(身份验证)
     * :
     * Authentication 是用来验证用户身份
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());

        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法

        UserInfo userInfo = userInfoManager.findOneByUsernameAndState("shiro", username, "0").getModel();
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            return null;
        }

        /*
         * 获取权限信息:这里没有进行实现，
         * 请自行根据UserInfo,Role,Permission进行实现；
         * 获取之后可以在前端for循环显示所有链接;
         */
        //userInfo.setPermissions(userService.findPermissions(user));


        //账号判断;

        //加密方式;
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );

        return authenticationInfo;
    }

}

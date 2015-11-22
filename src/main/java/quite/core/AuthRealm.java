package quite.core;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Repository;
import quite.entity.rightMoudle.SysUser;
import quite.service.AccountService;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * SysUser: xupeng
 * Date: 13-11-27
 * Time: 下午2:25
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class AuthRealm extends AuthorizingRealm {

    @Resource
    AccountService accountService;

    /**
     * 授权
     * @param principals
     * @return
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        SysUser sysUser = (SysUser) principals.fromRealm(getName()).iterator().next();
//        for (SysRole sysRole : sysUser.getSysRoleList()) {
//            //基于Role的权限信息
//            info.addRole(sysRole.getRoleName());//获得用户的角色
//        }
//        List<SysResource> resourceList = null;
//        if(sysUser.getSysResourceList()==null || sysUser.getSysResourceList().size()==0){
//            resourceList = resourceService.findPermissionResource(sysUser);
//            sysUser.setSysResourceList(resourceList);
//        }else {
//            resourceList=sysUser.getSysResourceList();
//        }
//        for (SysResource resource : resourceList) {
//            if (resource.getPermissionName() != null && !resource.getPermissionName().trim().equals(""))
//                info.addStringPermission(resource.getPermissionName());//获得角色的访问权限
//        }
        return info;
	}

    /**
     * 验证
     * @param token
     * @return
     * @throws AuthenticationException
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String loginName=upToken.getUsername();
        SysUser sysUser = accountService.findUserByLoginName(loginName);
        if (sysUser == null) {
            throw new UnknownAccountException("No account found for sysUser [" + upToken.getUsername() + "]");
        } else if (sysUser.getStatus().equals(0)) {
            throw new DisabledAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo =new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(), getName());
        return authenticationInfo;
	}

    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    protected void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }
}

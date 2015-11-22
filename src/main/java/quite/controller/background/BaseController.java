package quite.controller.background;

import org.apache.shiro.SecurityUtils;
import quite.entity.rightMoudle.SysUser;

/**
 * Created with IntelliJ IDEA.
 * User: pual
 * Date: 2015/10/13
 * Desc:
 */
public class BaseController {

    /**
     * 获取当前登录用户
     * @return
     */
    public static SysUser getCurrentUser () {
        if(SecurityUtils.getSubject().getPrincipal() instanceof SysUser){
            return (SysUser)SecurityUtils.getSubject().getPrincipal();
        }else {
            return null;
        }
    }
}

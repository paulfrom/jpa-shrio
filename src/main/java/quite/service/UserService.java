package quite.service;

import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import quite.dao.SysRoleDao;
import quite.dao.SysUserDao;
import quite.entity.rightMoudle.SysUser;

/**
 * Created by acer on 2015/7/12.
 */
@Component
public class UserService {



    private SysUserDao sysUserDao;

    private SysRoleDao sysRoleDao;

    @CachePut(value = "userCache",key="#result.id")
    public SysUser findUserByLoginName(String loginName){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~in3~~~~~~~~~~~~~~~~~~");
        Optional<SysUser> sysUser = sysUserDao.findByLoginName(loginName);
//        if (sysUser.isPresent()) {
//            Set<SysRole> roleList = sysRoleDao.findRoleByUserId(sysUser.get().getId());
//            sysUser.get().setSysRoleList(roleList);
//        }
        return sysUser.get();
    }



    /**
     * Created with ${PRODUCT_NAME}.
     * User: pual
     * Date: ${DATE}
     * Desc:
     */
    public boolean updateUser(SysUser user) {
        SysUser sysUser = sysUserDao.save(user);
        return sysUser!=null;
    }

    /**
     * User: paul
     * Date: ${DATE}
     * Desc:
     */
    public Page<SysUser> queryUserPage(SysUser sysUser, Pageable pageabel) {
       return sysUserDao.findAll(pageabel);
    }

    /**
     * User: paul
     * Date: ${DATE}
     * Desc:
     */
    @Transactional
    public boolean addSysUser(SysUser sysUser) {
        try {
            if (sysUser.getStatus()!=null&&sysUser.getStatus().equals("on")) {
                sysUser.setStatus("1");
            } else {
                sysUser.setStatus("0");
            }
            SysUser temp = sysUserDao.save(sysUser);
            return temp!=null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * User: paul
     * Date: ${DATE}
     * Desc:
     */
    public int countLoginName(String loginName,String id) {
        return sysUserDao.countByLoginNameAndId(loginName,id);
    }

    /**
     * User: paul
     * Date: ${DATE}
     * Desc:
     */
    public boolean deleteSysUser(Integer userId) {
        try {
            sysUserDao.delete(userId);
        }catch (Exception ex){
            return false;
        }
        return true;
    }
    @Autowired
    public void setUserDao(SysUserDao userDao) {
        this.sysUserDao = userDao;
    }

    @Autowired
    public void setRoleDao(SysRoleDao roleDao) {
        this.sysRoleDao = roleDao;
    }

}

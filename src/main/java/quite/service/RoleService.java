//package quite.service;
//
//import com.common.entity.base.Page;
//import com.common.entity.rightMoudle.SysResource;
//import com.common.entity.rightMoudle.SysRole;
//import com.common.mapper.rightMapper.SysResourceMapper;
//import com.common.mapper.rightMapper.SysRoleMapper;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created with IntelliJ IDEA.
// * User: pual
// * Date: 2015/9/13
// * Desc:
// */
//@Service
//public class RoleService {
//
//    @Resource
//    SysRoleMapper sysRoleMapper;
//    @Resource
//    SysResourceMapper sysResourceMapper;
//
//
//    public List<SysRole> getAllRoles(){
//        return sysRoleMapper.findAllRoles();
//    }
//
//    /**
//     * User: paul
//     * Date: ${DATE}
//     * Desc:
//     */
//    public void queryRolesPage(Page<SysRole> page) {
//        page.setResult(sysRoleMapper.querySysRoleList(page));
//        page.setTotal(sysRoleMapper.querySysRoleCount(page));
//    }
//
//    /**
//     * User: paul
//     * Date: ${DATE}
//     * Desc:
//     */
//    public List<SysResource> getResorcesByRoleId(Integer id) {
//        List<SysResource> result = sysResourceMapper.findResourceByRoleId(id);
//        return result;
//    }
//
//    /**
//     * User: paul
//     * Date: ${DATE}
//     * Desc:
//     */
//    @Transactional
//    public boolean addRoleWithResources(SysRole sysRole, String resourceIds) {
//        Long count=sysRoleMapper.insertSysRole(sysRole);
//        Map<String,Object> map=new HashMap<String, Object>();
//        map.put("roleId",sysRole.getId());
//        map.put("resource",resourceIds.split(","));
//        Long count2=sysRoleMapper.insertSysResourceRole(map);
//        if(count>0&& count2>0){
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * User: paul
//     * Date: ${DATE}
//     * Desc:
//     */
//    @Transactional
//    public boolean editRoleWithResources(SysRole sysRole, String resourceIds) {
//        Long count=sysRoleMapper.updateSysRole(sysRole);
//        Map<String,Object> map=new HashMap<String, Object>();
//        map.put("roleId",sysRole.getId());
//        map.put("resource",resourceIds.split(","));
//        Long count2=sysRoleMapper.deleteSysResourceRoleId(sysRole.getId());
//        Long count3=sysRoleMapper.insertSysResourceRole(map);
//        if(count>0&& count2>0&&count3>0){
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * User: paul
//     * Date: ${DATE}
//     * Desc:
//     */
//    public boolean delete(String roleId) {
//        Long count=sysRoleMapper.deleteById(Integer.parseInt(roleId));
//        Long count2=sysRoleMapper.deleteSysResourceRoleId(Integer.parseInt(roleId));
//        if(count>0&& count2>0){
//            return true;
//        }
//        return false;
//    }
//}

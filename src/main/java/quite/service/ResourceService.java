//package quite.service;
//
//import com.common.entity.rightMoudle.SysResource;
//import com.common.entity.rightMoudle.SysUser;
//import com.common.mapper.rightMapper.SysResourceMapper;
//import com.common.utils.CheckUtil;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: pual
// * Date: 2015/10/13
// * Desc:
// */
//@Service
//public class ResourceService {
//    @javax.annotation.Resource
//    private SysResourceMapper sysResourceMapper;
//
//
//    @Cacheable(value = "sysResourcCache", key="-1")
//    public List<SysResource> findAllResource(){
//        return sysResourceMapper.findAllSysResource();
//    }
//
//    /**
//     * Created with ${PRODUCT_NAME}.
//     * User: pual
//     * Date: ${DATE}
//     * Desc:
//     */
//    @Cacheable(value = "sysResourcCache", key="#user.id")
//    public List<SysResource> findPermissionResource(SysUser user) {
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~in4~~~~~~~~~~~~~~~~~~");
//        return sysResourceMapper.findResourceByRoleIds(user.getSysRoleList());
//    }
//
//    /**
//     * Created with ${PRODUCT_NAME}.
//     * User: pual
//     * Date: ${DATE}
//     * Desc:
//     */
//    @CacheEvict(value = "sysResourcCache",key="-1")
//    public boolean addOrEditResource(SysResource sysResource) {
//        if(CheckUtil.isEmpty(sysResource.getId())) {
//            Long count=sysResourceMapper.insertSysResource(sysResource);
//            return count==1;
//        }else {
//            long count=sysResourceMapper.updateSysResource(sysResource);
//            return count==1;
//        }
//    }
//
//    /**
//     * Created with ${PRODUCT_NAME}.
//     * User: pual
//     * Date: ${DATE}
//     * Desc:
//     */
//    @CacheEvict(value = "sysResourcCache",key="-1")
//    public boolean deletResource(Integer resourceId) {
//        int count = sysResourceMapper.deleteById(resourceId);
//        return count>=1;
//    }
//}

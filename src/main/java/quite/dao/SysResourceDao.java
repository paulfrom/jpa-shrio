package quite.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import quite.entity.rightMoudle.SysResource;
import quite.entity.rightMoudle.SysRole;

import java.util.List;
import java.util.Set;

public interface SysResourceDao extends PagingAndSortingRepository<SysResource, Integer> {
    @Query("select s from SysResource s where s.roles in (:roles)")
    List<SysRole> findSysResourceByRoles(@Param("roles") Set<Integer> roles);
}

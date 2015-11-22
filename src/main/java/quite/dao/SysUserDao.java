/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package quite.dao;

import com.google.common.base.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import quite.entity.rightMoudle.SysUser;

public interface SysUserDao extends PagingAndSortingRepository<SysUser, Integer>, JpaSpecificationExecutor<SysUser> {

	SysUser findByUserName(String name);

	Optional<SysUser> findByLoginName(String loginName);

	@Query("select count(0) from SysUser s where loginName=? and id !=?")
	int countByLoginNameAndId(String loginName, String id);
}

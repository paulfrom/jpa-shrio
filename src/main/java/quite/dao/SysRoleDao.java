/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package quite.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import quite.entity.rightMoudle.SysRole;

public interface SysRoleDao extends PagingAndSortingRepository<SysRole, Integer> {
//    Set<SysRole> findRoleByUserId(Integer id);
}

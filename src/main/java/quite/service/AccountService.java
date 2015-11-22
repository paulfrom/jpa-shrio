/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package quite.service;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import quite.controller.background.BaseController;
import quite.dao.SysResourceDao;
import quite.dao.SysRoleDao;
import quite.dao.SysUserDao;
import quite.entity.rightMoudle.SysResource;
import quite.entity.rightMoudle.SysRole;
import quite.entity.rightMoudle.SysUser;
import quite.utils.ConvertUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户管理业务类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class AccountService extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);
	private SysUserDao userDao;

	private SysRoleDao roleDao;

	@Resource
	private SysResourceDao sysResourceDao;

	private BusinessLogger businessLogger;

	/**
	 * 在保存用户时,发送用户修改通知消息, 由消息接收者异步进行较为耗时的通知邮件发送.
	 * 
	 * 如果企图修改超级用户,取出当前操作员用户,打印其信息然后抛出异常.
	 * 
	 */
	public void saveUser(SysUser user) {

		if (isSupervisor(user)) {
			logger.warn("操作员{}尝试修改超级管理员用户", getCurrentUser().getUserName());
			throw new ServiceException("不能修改超级管理员用户");
		}

		// 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
		if (StringUtils.isNotBlank(user.getPassword())) {
			entryptPassword(user);
		}
		userDao.save(user);

		// 业务日志演示
		Map logData = Maps.newHashMap();
		logData.put("userId", user.getId());
		businessLogger.log("USER", "UPDATE",getCurrentUser().getUserName(), logData);
	}

	/**
	 * 按Id获得用户.
	 */
	public SysUser getUser(Integer id) {
		return userDao.findOne(id);
	}

	/**
	 * 按登录名查询用户.
	 */
	public SysUser findUserByLoginName(String loginName) {
		SysUser tem=userDao.findOne(1);
		Optional<SysUser> sysUser = userDao.findByLoginName(loginName);
		if(sysUser.isPresent()){
			Hibernate.initialize(sysUser.get().getSysRoleList());
		}

		return sysUser.get();
	}

	/**
	 * 按名称查询用户, 并在返回前对用户的延迟加载关联角色进行初始化.
	 */
	public SysUser findUserByNameInitialized(String name) {
		SysUser user = userDao.findByUserName(name);
		return user;
	}

	/**
	 * 获取当前用户数量.
	 */
	public Long getUserCount() {
		return userDao.count();
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(SysUser user) {
		return ((user.getId() != null) && (user.getId() == 1L));
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(SysUser user) {
		try {
			user.setPassword(ConvertUtil.encryptMD5(user.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// --------------------//
	// Role Management //
	// --------------------//

	public List<SysRole> getAllRole() {
		return (List<SysRole>) roleDao.findAll();
	}

	// -----------------//
	// Setter methods //
	// -----------------//

	@Autowired
	public void setUserDao(SysUserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setRoleDao(SysRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setBusinessLogger(BusinessLogger businessLogger) {
		this.businessLogger = businessLogger;
	}

	/**
	 * Created with ${PRODUCT_NAME}.
	 * User: pual
	 * Date: ${DATE}
	 * Desc:
	 */
	public boolean updateUser(SysUser user) {
		user=userDao.save(user);
		return user!=null;
	}

	/**
	 * Created with ${PRODUCT_NAME}.
	 * User: pual
	 * Date: ${DATE}
	 * Desc:
	 */
	public List<SysResource> findPermissionResource(Set<Integer> roles) {
		List<SysRole>  list = sysResourceDao.findSysResourceByRoles(roles);
		return null;
	}

	public List<SysResource> findAllResource(){
		return (List<SysResource>) sysResourceDao.findAll();
	}
}

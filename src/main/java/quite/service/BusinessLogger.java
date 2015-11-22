/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package quite.service;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 打印业务日志，格式为:
 * 
 * 日期,实体类型,操作类型 ,操作用户,json格式的扩展字段
 * 
 * @author calvin
 */
@Component
public class BusinessLogger {
	public static final String BUSINESS_LOGGER_NAME = "business";

	private Logger businessLogger = LoggerFactory.getLogger(BUSINESS_LOGGER_NAME);

	public void log(String entity, String action, String user, Map data) {
		String json = (data != null ? JSON.toJSONString(data) : "{}");
		businessLogger.info("{},{},{},{}", entity, action, user, json);
	}
}

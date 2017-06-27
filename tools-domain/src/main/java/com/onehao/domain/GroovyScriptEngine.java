/**
 * Masapay.com Inc.
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.onehao.domain;

import groovy.lang.GroovyClassLoader;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;


/**
 * 解析脚本引擎
 * GroovyScriptEngine
 * david
 * 2016年8月4日 上午11:55:52
 * @version 1.0.0
 * 
 */
public class GroovyScriptEngine implements InitializingBean {
	private static final Logger logger = Logger.getLogger(GroovyScriptEngine.class);

	/**
	 * 加载解析器
	 *
	 * @param script
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public InstFileGroovyParser loadScriptParser(File script) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("[GroovyScriptEngine] 加载Groovy脚本：" + script);
		}
		return (InstFileGroovyParser) new GroovyClassLoader(getClass().getClassLoader()).parseClass(script).newInstance();
	}

	/** 
	 * @throws Exception
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {

	}

}

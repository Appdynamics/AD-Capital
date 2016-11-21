/*
 * Copyright 2015 AppDynamics, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appdynamics.loan.util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * This is a utility class to lookup Spring managed beans.
 * It abstracts loading the application context. InitServlet Initializes this class.
 * 
 */
public class SpringContext implements BeanFactoryAware {
	
	private static BeanFactory beanFactory = null;
	private static final Logger log = Logger.getLogger(SpringContext.class.getName());
	/**
	 * This method is called by spring at startup. No other class sould call this.
	 */
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	/**
	 * Retuns a spring managed bean with the specified name.
	 */
	public static Object getBean(String name) {
		return beanFactory.getBean(name);
	}
}

/**
 * Copyright (c) 2014 InMotion Innovation Technology. All Rights Reserved. <BR>
 * <BR>
 * This software contains confidential and proprietary information of
 * InMotion Innovation Technology. ("Confidential Information").<BR>
 * <BR>
 * Such Confidential Information shall not be disclosed and it shall
 * only be used in accordance with the terms of the license agreement
 * entered into with IMI; other than in accordance with the written
 * permission of IMI. <BR>
 * 
 **/
package com.imi.dolphin.sdkwebservice.model;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author reja
 *
 */

@JsonInclude(Include.NON_NULL)
public class ExtensionResult implements Serializable {
	private static final long serialVersionUID = 1768303005374821099L;
	private Map<String, String> value;
	private boolean next;
	private boolean success;
	private boolean repeat;
	private boolean agent;
	private Map<String, String> entities;
	
	private Map<String, EasyMap> parameters;


	public Map<String, String> getValue() {
		return value;
	}

	public void setValue(Map<String, String> value) {
		this.value = value;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isAgent() {
		return agent;
	}

	public void setAgent(boolean agent) {
		this.agent = agent;
	}

	public Map<String, String> getEntities() {
		return entities;
	}

	public void setEntities(Map<String, String> entities) {
		this.entities = entities;
	}
	
	public Map<String, EasyMap> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, EasyMap> parameters) {
		this.parameters = parameters;
	}
}


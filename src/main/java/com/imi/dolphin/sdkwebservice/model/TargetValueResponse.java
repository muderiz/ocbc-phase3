/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 *
 * @author cokkyturnip
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TargetValueResponse {

	public int Channel_ID;
	public String Ext_Reff_ID;
	public int RC;
	public String rc_description;
	public String Rate;
	public int Target_Amount;
	public int Investment_Type;
	public int Result;
	
	public String Final_Future_Value;

	public TargetValueResponse() {
	}

	@Override
	public String toString() {
		return "ClassPojo [Ext_Reff_ID = " + Ext_Reff_ID + ", RC = " + RC + ", Rate = " + Rate + ", Final_Future_Value = " + Final_Future_Value + ", Channel_ID = " + Channel_ID + ", rc_description = " + rc_description + ", Investment_Type = " + Investment_Type + ", Result = " + Result + "]";
	}
}

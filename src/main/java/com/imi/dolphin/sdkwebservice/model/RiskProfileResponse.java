/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.model;

/**
 *
 * @author chochong
 */
public class RiskProfileResponse {

	public String Risk_Profile_Type;

	public String Ext_Reff_ID;

	public int RC;

	public int Risk_Profile_ID;

	public String Channel_ID;

	public String RC_Description;

	public String getRisk_Profile_Type() {
		return Risk_Profile_Type;
	}

	public void setRisk_Profile_Type(String Risk_Profile_Type) {
		this.Risk_Profile_Type = Risk_Profile_Type;
	}

	public String getExt_Reff_ID() {
		return Ext_Reff_ID;
	}

	public void setExt_Reff_ID(String Ext_Reff_ID) {
		this.Ext_Reff_ID = Ext_Reff_ID;
	}

	public int getRC() {
		return RC;
	}

	public void setRC(int RC) {
		this.RC = RC;
	}

	public int getRisk_Profile_ID() {
		return Risk_Profile_ID;
	}

	public void setRisk_Profile_ID(int Risk_Profile_ID) {
		this.Risk_Profile_ID = Risk_Profile_ID;
	}

	public String getChannel_ID() {
		return Channel_ID;
	}

	public void setChannel_ID(String Channel_ID) {
		this.Channel_ID = Channel_ID;
	}

	public String getRC_Description() {
		return RC_Description;
	}

	public void setRC_Description(String RC_Description) {
		this.RC_Description = RC_Description;
	}

	@Override
	public String toString() {
		return "ClassPojo [Risk_Profile_Type = " + Risk_Profile_Type + ", Ext_Reff_ID = " + Ext_Reff_ID + ", RC = " + RC + ", Risk_Profile_ID = " + Risk_Profile_ID + ", Channel_ID = " + Channel_ID + ", RC_Description = " + RC_Description + "]";
	}
}

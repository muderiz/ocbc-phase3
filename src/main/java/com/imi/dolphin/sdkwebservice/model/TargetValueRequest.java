/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author cokkyturnip
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TargetValueRequest {

	public int Channel_ID;
	public String Ext_Reff_ID;
	public String Payment_Type;
	public String Yearly_Return_Code;
	public int Tenor;
	public int Initial_Amount;
	public int Pre_Calculated_Future_Value;
	public String Future_Value_Code;
	public int EC_ID;
	public int Children_Age;
	public int Due_Date;
	public int Product_ID;
	public int Risk_Profile_ID;

	public String Country;
	public String Present_Value;

}

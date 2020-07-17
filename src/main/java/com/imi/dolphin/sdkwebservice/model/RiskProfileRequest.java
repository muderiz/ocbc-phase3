/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.model;

/**
 *
 * @author cokkyturnip
 */
public class RiskProfileRequest {

	public String Ext_Reff_ID;

	public String[] Financial_Condition = new String[0];

	public String Time_Horizon;

	public int Channel_ID = 1;

	public String Investment_Knowledge;

	public String Potential_Loss;

	public String[] Investment_Instrument = new String[0];

	public String Avg_Placement_Fund= "";

	public String Risk_Profile_Channel = "WPP";

	public String Source_of_Income;

	public String Return_Characteristic = "";

	public String Income_Usage;

	public String Time_Horizon_Structured_Product="";

	public String Placement_Frequency="";
}

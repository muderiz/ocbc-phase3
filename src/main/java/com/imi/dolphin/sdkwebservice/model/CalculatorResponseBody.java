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
public class CalculatorResponseBody {

	public String Channel_ID;
	public String Ext_Reff_ID;
	public int RC;
	public String RC_Description;

//<editor-fold>
//==========================	Future Value / Deposito		==========================
	public String Rate;
	public long Result;
//	public String Channel_ID;
//	public String Ext_Reff_ID;
//	public int RC;
//	public String RC_Description;

//</editor-fold>
	
//<editor-fold>
//==========================	Present Value	==========================
//	public String Channel_ID;
//	public String Ext_Reff_ID;
//	public int RC;
//	public String RC_Description;
//	public String Rate;
//	public int Result;
	public boolean Investment_Type;
//</editor-fold>

//<editor-fold>
//==========================	Target Value	==========================
//	public int Channel_ID;
//	public String Ext_Reff_ID;
//	public int RC;
//	public String RC_Description;
//	public String Rate;
	public long Target_Amount;
//	public boolean Investment_Type;
//	public int Result;

//</editor-fold>
//<editor-fold>
//==========================	List Product	==========================
//	public String Ext_Reff_ID;
//  public String RC;
//  public String Channel_ID;
//  public String RC_Description;
	public Product[] List_Product;
//</editor-fold>

//<editor-fold>
//==========================	Risk Profile	==========================
	public int Risk_Profile_ID;
	public String Risk_Profile_Type;
//	public String Ext_Reff_ID;
//	public int RC;
//	public String Channel_ID;
//	public String RC_Description;
//</editor-fold>

}

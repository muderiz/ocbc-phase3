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
public class CalculatorRequestBody {

	public int Channel_ID = 1;
	public String Ext_Reff_ID;

//<editor-fold>
//==========================	Future Value	==========================
	public int Investment_Type;
	public String Future_Value_Type;
	public String Yearly_Return_Code;
	public int Tenor;
	public long Investment_Amount;
	public int Due_Date;
	public int Product_ID;
	public int Risk_Profile_ID;
//</editor-fold>
	
//<editor-fold>
//==========================	Future Value Deposito	==========================
	public double Tax;
//</editor-fold>

//<editor-fold>
//==========================	Present Value	==========================	
  public String Present_Value_Type;
  public int Payment;
  public long Target_Amount;
//  public String Yearly_Return_Code;
//  public int Tenor;
//  public int Due_Date;
//	public int Product_ID;
  
//</editor-fold>	
	
//<editor-fold>
//==========================	Target Value	==========================	
	public String Payment_Type;
	public long Initial_Amount;
	public long Pre_Calculated_Future_Value;
	public String Future_Value_Code;
	public int EC_ID;
	public int Children_Age;
//	public String Yearly_Return_Code;
//	public int Tenor;
//	public int Due_Date;
//	public int Product_ID;
//	public int Risk_Profile_ID;
//</editor-fold>
	
//<editor-fold>
//==========================	List Product	==========================
	public long Nominal_Amount;
	public String[] List_Product_Type;
	public String Tipe;
	public String MFA_List;
//</editor-fold>
	
//<editor-fold>
//==========================	Risk Profile	==========================
	public String[] Financial_Condition;
	public String Time_Horizon;
	public String Investment_Knowledge;
	public String Potential_Loss;
	public String[] Investment_Instrument;
	public String Avg_Placement_Fund;
	public String Risk_Profile_Channel;
	public String Source_of_Income;
	public String Return_Characteristic;
	public String Income_Usage;
	public String Time_Horizon_Structured_Product;
	public String Placement_Frequency;
//</editor-fold>
}

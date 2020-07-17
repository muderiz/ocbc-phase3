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
public class FutureValueRequest {

    public int Channel_ID;
    public String Ext_Reff_ID;
    public int Investment_Type;
    public String Future_Value_Type;
    public String Yearly_Return_Code;
    public int Tenor;
    public int Investment_Amount;
    public int Due_Date;
    public int Product_ID;
    public int Risk_Profile_ID;
	public double Tax;

    @Override
    public String toString() {
        return "ClassPojo [Ext_Reff_ID = " + Ext_Reff_ID + ", Investment_Amount = " + Investment_Amount + ", Risk_Profile_ID = " + Risk_Profile_ID + ", Due_Date = " + Due_Date + ", Product_ID = " + Product_ID + ", Future_Value_Type = " + Future_Value_Type + ", Yearly_Return_Code = " + Yearly_Return_Code + ", Channel_ID = " + Channel_ID + ", Investment_Type = " + Investment_Type + ", Tenor = " + Tenor + "]";
    }
}

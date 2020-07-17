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
public class RiskProfile {

    private String Ext_Reff_ID;

    private String[] Financial_Condition;

    private String Time_Horizon;

    private String Channel_ID;

    private String Investment_Knowledge;

    private String Potential_Loss;

    private String[] Investment_Instrument;

    private String Avg_Placement_Fund;

    private String Risk_Profile_Channel;

    private String Source_of_Income;

    private String Return_Characteristic;

    private String Income_Usage;

    private String Time_Horizon_Structured_Product;

    private String Placement_Frequency;

    public String getExt_Reff_ID ()
    {
        return Ext_Reff_ID;
    }

    public void setExt_Reff_ID (String Ext_Reff_ID)
    {
        this.Ext_Reff_ID = Ext_Reff_ID;
    }

    public String[] getFinancial_Condition ()
    {
        return Financial_Condition;
    }

    public void setFinancial_Condition (String[] Financial_Condition)
    {
        this.Financial_Condition = Financial_Condition;
    }

    public String getTime_Horizon ()
    {
        return Time_Horizon;
    }

    public void setTime_Horizon (String Time_Horizon)
    {
        this.Time_Horizon = Time_Horizon;
    }

    public String getChannel_ID ()
    {
        return Channel_ID;
    }

    public void setChannel_ID (String Channel_ID)
    {
        this.Channel_ID = Channel_ID;
    }

    public String getInvestment_Knowledge ()
    {
        return Investment_Knowledge;
    }

    public void setInvestment_Knowledge (String Investment_Knowledge)
    {
        this.Investment_Knowledge = Investment_Knowledge;
    }

    public String getPotential_Loss ()
    {
        return Potential_Loss;
    }

    public void setPotential_Loss (String Potential_Loss)
    {
        this.Potential_Loss = Potential_Loss;
    }

    public String[] getInvestment_Instrument ()
    {
        return Investment_Instrument;
    }

    public void setInvestment_Instrument (String[] Investment_Instrument)
    {
        this.Investment_Instrument = Investment_Instrument;
    }

    public String getAvg_Placement_Fund ()
    {
        return Avg_Placement_Fund;
    }

    public void setAvg_Placement_Fund (String Avg_Placement_Fund)
    {
        this.Avg_Placement_Fund = Avg_Placement_Fund;
    }

    public String getRisk_Profile_Channel ()
    {
        return Risk_Profile_Channel;
    }

    public void setRisk_Profile_Channel (String Risk_Profile_Channel)
    {
        this.Risk_Profile_Channel = Risk_Profile_Channel;
    }

    public String getSource_of_Income ()
    {
        return Source_of_Income;
    }

    public void setSource_of_Income (String Source_of_Income)
    {
        this.Source_of_Income = Source_of_Income;
    }

    public String getReturn_Characteristic ()
    {
        return Return_Characteristic;
    }

    public void setReturn_Characteristic (String Return_Characteristic)
    {
        this.Return_Characteristic = Return_Characteristic;
    }

    public String getIncome_Usage ()
    {
        return Income_Usage;
    }

    public void setIncome_Usage (String Income_Usage)
    {
        this.Income_Usage = Income_Usage;
    }

    public String getTime_Horizon_Structured_Product ()
    {
        return Time_Horizon_Structured_Product;
    }

    public void setTime_Horizon_Structured_Product (String Time_Horizon_Structured_Product)
    {
        this.Time_Horizon_Structured_Product = Time_Horizon_Structured_Product;
    }

    public String getPlacement_Frequency ()
    {
        return Placement_Frequency;
    }

    public void setPlacement_Frequency (String Placement_Frequency)
    {
        this.Placement_Frequency = Placement_Frequency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Ext_Reff_ID = "+Ext_Reff_ID+", Financial_Condition = "+Financial_Condition+", Time_Horizon = "+Time_Horizon+", Channel_ID = "+Channel_ID+", Investment_Knowledge = "+Investment_Knowledge+", Potential_Loss = "+Potential_Loss+", Investment_Instrument = "+Investment_Instrument+", Avg_Placement_Fund = "+Avg_Placement_Fund+", Risk_Profile_Channel = "+Risk_Profile_Channel+", Source_of_Income = "+Source_of_Income+", Return_Characteristic = "+Return_Characteristic+", Income_Usage = "+Income_Usage+", Time_Horizon_Structured_Product = "+Time_Horizon_Structured_Product+", Placement_Frequency = "+Placement_Frequency+"]";
    }    
}

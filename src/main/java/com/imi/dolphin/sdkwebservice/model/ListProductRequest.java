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
public class ListProductRequest {
    
    public String Ext_Reff_ID;

    public String Risk_Profile_ID;

    public String Nominal_Amount;

    public String Channel_ID;

    public String[] List_Product_Type;

    public String Tipe;

    public String MFA_List;

    public String getExt_Reff_ID ()
    {
        return Ext_Reff_ID;
    }

    public void setExt_Reff_ID (String Ext_Reff_ID)
    {
        this.Ext_Reff_ID = Ext_Reff_ID;
    }

    public String getRisk_Profile_ID ()
    {
        return Risk_Profile_ID;
    }

    public void setRisk_Profile_ID (String Risk_Profile_ID)
    {
        this.Risk_Profile_ID = Risk_Profile_ID;
    }

    public String getNominal_Amount ()
    {
        return Nominal_Amount;
    }

    public void setNominal_Amount (String Nominal_Amount)
    {
        this.Nominal_Amount = Nominal_Amount;
    }

    public String getChannel_ID ()
    {
        return Channel_ID;
    }

    public void setChannel_ID (String Channel_ID)
    {
        this.Channel_ID = Channel_ID;
    }

    public String[] getList_Product_Type ()
    {
        return List_Product_Type;
    }

    public void setList_Product_Type (String[] List_Product_Type)
    {
        this.List_Product_Type = List_Product_Type;
    }

    public String getTipe ()
    {
        return Tipe;
    }

    public void setTipe (String Tipe)
    {
        this.Tipe = Tipe;
    }

    public String getMFA_List ()
    {
        return MFA_List;
    }

    public void setMFA_List (String MFA_List)
    {
        this.MFA_List = MFA_List;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Ext_Reff_ID = "+Ext_Reff_ID+", Risk_Profile_ID = "+Risk_Profile_ID+", Nominal_Amount = "+Nominal_Amount+", Channel_ID = "+Channel_ID+", List_Product_Type = "+List_Product_Type+", Tipe = "+Tipe+", MFA_List = "+MFA_List+"]";
    }
}

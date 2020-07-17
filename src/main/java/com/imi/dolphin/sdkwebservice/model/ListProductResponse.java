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
public class ListProductResponse
{
    public String Ext_Reff_ID;

    public String RC;

    public String Channel_ID;

    public String RC_Description;

    public Product[] List_Product;

    public String getExt_Reff_ID ()
    {
        return Ext_Reff_ID;
    }

    public void setExt_Reff_ID (String Ext_Reff_ID)
    {
        this.Ext_Reff_ID = Ext_Reff_ID;
    }

    public String getRC ()
    {
        return RC;
    }

    public void setRC (String RC)
    {
        this.RC = RC;
    }

    public String getChannel_ID ()
    {
        return Channel_ID;
    }

    public void setChannel_ID (String Channel_ID)
    {
        this.Channel_ID = Channel_ID;
    }

    public String getRC_Description ()
    {
        return RC_Description;
    }

    public void setRC_Description (String RC_Description)
    {
        this.RC_Description = RC_Description;
    }

    public Product[] getList_Product ()
    {
        return List_Product;
    }

    public void setList_Product (Product[] List_Product)
    {
        this.List_Product = List_Product;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Ext_Reff_ID = "+Ext_Reff_ID+", RC = "+RC+", Channel_ID = "+Channel_ID+", RC_Description = "+RC_Description+", List_Product = "+List_Product+"]";
    }
}

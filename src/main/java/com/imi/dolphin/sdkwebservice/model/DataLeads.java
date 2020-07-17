/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.model;

import java.util.List;

/**
 *
 * @author chochong
 */
public class DataLeads {

    public String Ext_Reff_ID;

    public String Channel_ID;

    public Transaction_Data[] Transaction_Data;

    public String getExt_Reff_ID() {
        return Ext_Reff_ID;
    }

    public void setExt_Reff_ID(String Ext_Reff_ID) {
        this.Ext_Reff_ID = Ext_Reff_ID;
    }

    public String getChannel_ID() {
        return Channel_ID;
    }

    public void setChannel_ID(String Channel_ID) {
        this.Channel_ID = Channel_ID;
    }

    public Transaction_Data[] getTransaction_Data() {
        return Transaction_Data;
    }

    public void setTransaction_Data(Transaction_Data[] Transaction_Data) {
        this.Transaction_Data = Transaction_Data;
    }

    @Override
    public String toString() {
        return "ClassPojo [Ext_Reff_ID = " + Ext_Reff_ID + ", Channel_ID = " + Channel_ID + ", Transaction_Data = " + Transaction_Data + "]";
    }
}

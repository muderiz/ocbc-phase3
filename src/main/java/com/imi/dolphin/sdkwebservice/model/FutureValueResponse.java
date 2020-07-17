/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

/**
 *
 * @author cokkyturnip
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FutureValueResponse{
    
    public String Channel_ID;
    public String Ext_Reff_ID;
    public int RC;
    public String rc_description;
    public String Rate;
    public int Result;

    @Override
    public String toString() {
        return "ClassPojo [ Channel_ID = " + Channel_ID + ",Ext_Reff_ID = " + Ext_Reff_ID + ", RC = " + RC + ", rc_description = " + rc_description + ",Rate = " + Rate + ",  Result = " + Result + "]";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author chochong
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
    String kota;

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }
    
    
}

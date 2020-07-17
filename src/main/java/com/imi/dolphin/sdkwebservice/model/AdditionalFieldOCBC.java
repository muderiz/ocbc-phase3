/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.model;

import java.io.Serializable;

/**
 *
 * @author chochong
 */
public class AdditionalFieldOCBC implements Serializable {

    private String pendapatan;
    private String statusPerkawinan;
    private String tabungan;
    private String statusInvestasi;
    private String profileResiko;
    private String value;

    public AdditionalFieldOCBC(String pendapatan, String statusPerkawinan, String tabungan, String statusInvestasi, String profileResiko, String value) {
        this.pendapatan = pendapatan;
        this.statusPerkawinan = statusPerkawinan;
        this.tabungan = tabungan;
        this.statusInvestasi = statusInvestasi;
        this.profileResiko = profileResiko;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getProfileResiko() {
        return profileResiko;
    }

    public void setProfileResiko(String profileResiko) {
        this.profileResiko = profileResiko;
    }

    public AdditionalFieldOCBC() {
    }

    public String getPendapatan() {
        return pendapatan;
    }

    public void setPendapatan(String pendapatan) {
        this.pendapatan = pendapatan;
    }

    public String getStatusPerkawinan() {
        return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
        this.statusPerkawinan = statusPerkawinan;
    }

    public String getTabungan() {
        return tabungan;
    }

    public void setTabungan(String tabungan) {
        this.tabungan = tabungan;
    }

    public String getStatusInvestasi() {
        return statusInvestasi;
    }

    public void setStatusInvestasi(String statusInvestasi) {
        this.statusInvestasi = statusInvestasi;
    }

}

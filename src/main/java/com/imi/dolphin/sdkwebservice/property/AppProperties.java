/**
 * Copyright (c) 2014 InMotion Innovation Technology. All Rights Reserved. <BR>
 * <BR>
 * This software contains confidential and proprietary information of InMotion
 * Innovation Technology. ("Confidential Information").<BR>
 * <BR>
 * Such Confidential Information shall not be disclosed and it shall only be
 * used in accordance with the terms of the license agreement entered into with
 * IMI; other than in accordance with the written permission of IMI. <BR>
 *
 *
 */
package com.imi.dolphin.sdkwebservice.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author reja
 *
 */
@Component
public class AppProperties {

    @Value("${ocbc.pictures.etc}")
    public String OCBC_PICTURES_ETC;

    @Value("${ocbc.pictures.conservative}")
    String OCBC_PICTURE_CONSERVATIVE;

    @Value("${ocbc.pictures.balance}")
    String OCBC_PICTURE_BALANCE;

    @Value("${ocbc.pictures.growth}")
    String OCBC_PICTURE_GROWTH;

    @Value("${ocbc.pictures.aggressive}")
    String OCBC_PICTURE_AGGRESSIVE;

    @Value("${ocbc.carousel.gambar.kota}")
    String ocbc_carousel_gambar_kota;

    @Value("${ocbc.carousel.gambar.download}")
    String ocbc_carousel_gambar_download;

    @Value("${ocbc.carousel.title.download}")
    String ocbc_carousel_title_download;

    @Value("${ocbc.carousel.investratio1}")
    String ocbc_carousel_investratio1;

    @Value("${ocbc.carousel.investratio2}")
    String ocbc_carousel_investratio2;

    @Value("${ocbc.carousel.investratio3}")
    String ocbc_carousel_investratio3;

    @Value("${ocbc.carousel.investratio4}")
    String ocbc_carousel_investratio4;

    @Value("${ocbc.carousel.investratio5}")
    String ocbc_carousel_investratio5;

    @Value("${ocbc.carousel.gambar.kantorCabangTerdekat}")
    String ocbc_carousel_gambar_kantorCabangTerdekat;

    @Value("${ocbc.carousel.value.kantorCabangTerdekat1}")
    String ocbc_carousel_value_kantorCabangTerdekat1;

    @Value("${ocbc.carousel.value.kantorCabangTerdekat2}")
    String ocbc_carousel_value_kantorCabangTerdekat2;

    @Value("${ocbc.carousel.title.kantorCabangTerdekat}")
    String ocbc_carousel_title_kantorCabangTerdekat;

    @Value("${ocbc.carousel.subtitle.kantorCabangTerdekat}")
    String ocbc_carousel_subtitle_kantorCabangTerdekat;

    @Value("${ocbc.carousel.nameButton.kantorCabangTerdekat}")
    String ocbc_carousel_nameButton_kantorCabangTerdekat;

    @Value("${ocbc.carousel.gambar.lainnyasatu}")
    String ocbc_carousel_gambar_lainnya;

    @Value("${ocbc.carousel.title.lainnyasatu}")
    String ocbc_carousel_title_lainnya;

    @Value("${ocbc.carousel.subtitle.lainnyasatu}")
    String ocbc_carousel_subtitle_lainnya;

    @Value("${ocbc.carousel.nameButton.lainnyasatu}")
    String ocbc_carousel_nameButton_lainnya;

    @Value("${ocbc.carousel.gambar.pendidikan}")
    String ocbc_carousel_gambar_pendidikan;

    @Value("${ocbc.carousel.title.pendidikan}")
    String ocbc_carousel_title_pendidikan;

    @Value("${ocbc.carousel.subtitle.pendidikan}")
    String ocbc_carousel_subtitle_pendidikan;

    @Value("${ocbc.carousel.nameButton.pendidikan}")
    String ocbc_carousel_nameButton_pendidikan;

    @Value("${ocbc.carousel.gambar.growth}")
    String ocbc_carousel_gambar_growth;

    @Value("${ocbc.carousel.title.growth}")
    String ocbc_carousel_title_growth;

    @Value("${ocbc.carousel.subtitle.growth}")
    String ocbc_carousel_subtitle_growth;

    @Value("${ocbc.carousel.nameButton.growth}")
    String ocbc_carousel_nameButton_growth;

    @Value("${ocbc.carousel.gambar.riskprofile}")
    String ocbc_carousel_gambar_riskprofile;

    @Value("${ocbc.carousel.title.riskprofile}")
    String ocbc_carousel_title_riskprofile;

    @Value("${ocbc.carousel.subtitle.riskprofile}")
    String ocbc_carousel_subtitle_riskprofile;

    @Value("${ocbc.carousel.nameButton.riskprofile}")
    String ocbc_carousel_nameButton_riskprofile;

    @Value("${ocbc.carousel.gambar.syaratdanketentuan}")
    String ocbc_carousel_gambar_syaratdanketentuan;

    @Value("${ocbc.carousel.title.syaratdanketentuan}")
    String ocbc_carousel_title_syaratdanketentuan;

    @Value("${ocbc.carousel.subtitle.syaratdanketentuan}")
    String ocbc_carousel_subtitle_syaratdanketentuan;

    @Value("${ocbc.carousel.nameButton.syaratdanketentuan}")
    String ocbc_carousel_nameButton_syaratdanketentuan;

    @Value("${ocbc.carousel.gambar.syaratdanketentuan.tidak}")
    String ocbc_carousel_gambar_syaratdanketentuan_tidak;

    @Value("${ocbc.carousel.title.syaratdanketentuan.tidak}")
    String ocbc_carousel_title_syaratdanketentuan_tidak;

    @Value("${ocbc.carousel.subtitle.syaratdanketentuan.tidak}")
    String ocbc_carousel_subtitle_syaratdanketentuan_tidak;

    @Value("${ocbc.carousel.nameButton.syaratdanketentuan.tidak}")
    String ocbc_carousel_nameButton_syaratdanketentuan_tidak;

    @Value("${ocbc.carousel.gambar.lainnyanonton}")
    String ocbc_carousel_gambar_lainnya_nonton;

    @Value("${ocbc.carousel.gambar.lainnyaliburan}")
    String ocbc_carousel_gambar_lainnya_liburan;

    @Value("${ocbc.carousel.gambar.lainnyabeli}")
    String ocbc_carousel_gambar_lainnya_beli;

    @Value("${server.port}")
    String servicePort;

    @Value("${app.form.id}")
    String formId;

    @Value("${app.mode}")
    public String APP_MODE;

    @Value("${mail.username}")
    String mailUsername;

    @Value("${mail.password}")
    String mailPassword;

    @Value("${mail.smtp.auth}")
    String mailStmpAuth;

    @Value("${mail.smtp.starttls.enable}")
    String mailSmtpTls;

    @Value("${mail.smtp.host}")
    String mailSmtpHost;

    @Value("${mail.smtp.port}")
    String mailSmtpPort;

    @Value("${sdk.connectTimeout}")
    String sdkConnectTimeout;

    @Value("${sdk.readTimeout}")
    String sdkReadTimeout;

    @Value("${sdk.username}")
    String sdkDolphinUsername;

    @Value("${sdk.password}")
    String sdkDolphinPassword;

    @Value("${sdk.dolphin.base.url}")
    String sdkDolphinBaseUrl;

    @Value("${sdk.dolphin.graph.auth}")
    String sdkDolphinGraphAuth;

    @Value("${sdk.dolphin.graph.contacts}")
    String sdkDolphinGraphContacts;

    @Value("${sdk.dolphin.graph.contacts.update}")
    String sdkDolphinGraphContactsUpdate;

    @Value("${ocbc.webview.base_url}")
    public String OCBC_WEBVIEW_BASE_URL;

    @Value("${ocbc.webview.base_url.tnc}")
    public String OCBC_WEBVIEW_BASE_URL_TNC;

    @Value("${ocbc.webview.base_url.growth}")
    public String ocbcWebViewBaseUrlGrowth;

    @Value("${ocbc.webview.base_url.education}")
    public String ocbcWebViewBaseUrlEducation;

    @Value("${ocbc.webview.base_url.etc}")
    public String ocbcWebViewBaseUrlEtc;

    @Value("${ocbc.webview.base_url.pdf}")
    public String OCBC_WEBVIEW_BASE_URL_PDF;

    @Value("${ocbc.wpp.base_url}")
    public String OCBC_WPP_BASE_URL;

    @Value("${ocbc.wpp.base_url.risk_profile}")
    public String OCBC_WPP_PATH_RISK_PROFILE;

    @Value("${ocbc.wpp.base_url.list_product}")
    public String OCBC_WPP_PATH_LIST_PRODUCT;

    @Value("${ocbc.wpp.base_url.future_value}")
    public String OCBC_WPP_PATH_FUTURE_VALUE;

    @Value("${ocbc.wpp.base_url.future_value_deposito}")
    public String OCBC_WPP_PATH_FUTURE_VALUE_DEPOSITO;

    @Value("${ocbc.wpp.base_url.target_value}")
    public String OCBC_WPP_PATH_TARGET_VALUE;

    @Value("${ocbc.wpp.base_url.present_value}")
    public String OCBC_WPP_PATH_PRESENT_VALUE;

    @Value("${ocbc.wpp.base_url.data_leads}")
    public String OCBC_WPP_PATH_DATA_LEADS;

    @Value("${ocbc.wpp.base_url.pdf_generator}")
    public String OCBC_WPP_PATH_PDF_GENERATOR;

    @Value("${ocbc.folder.pictures}")
    public String OCBC_FOLDER_PICTURES;

    @Value("${ocbc.folder.resources}")
    public String OCBC_FOLDER_RESOURCES;

    @Value("${ocbc.mail.subject}")
    public String OCBC_MAIL_SUBJECT;

    @Value("${ocbc.mail.body}")
    public String OCBC_MAIL_BODY;

    @Value("${ocbc.folder.pdf}")
    public String OCBC_FOLDER_PDF;

    @Value("${ocbc.encrypt.salt}")
    public String OCBC_ENCRYPT_SALT;

    @Value("${ocbc.encrypt.key}")
    public String OCBC_ENCRYPT_KEY;

    @Value("${ocbc.encrypt.token.expired}")
    public String OCBC_ENCRYPT_TOKEN_EXP;

    @Value("${ocbc.link.onemobile}")
    public String OCBC_LINK_ONEMOBILE;

    @Value("${ocbc.link.daftarnasabah}")
    public String OCBC_LINK_DAFTARNASABAH;

    @Value("${ocbc.pictures.quotes}")
    public String OCBC_PICTURES_QUOTES;

    @Value("${ocbc.pictures.onemobile}")
    public String OCBC_PICTURES_ONEMOBILE;

    @Value("${ocbc.pictures.daftarnasabah}")
    public String OCBC_PICTURES_DAFTARNASABAH;

    @Value("${ocbc.pictures.carouselquotes}")
    public String OCBC_PICTURES_CAROUSELQUOTES;

    @Value("${ocbc.pictures.recomproduct.lumpsum}")
    public String OCBC_PICTURES_RECOMPRODUCT_LUMPSUM;

    @Value("${ocbc.pictures.recomproduct.monthly}")
    public String OCBC_PICTURES_RECOMPRODUCT_MONTHLY;

    @Value("${ocbc.pictures.recomproduct.taka}")
    public String OCBC_PICTURES_RECOMPRODUCT_TAKA;

    @Value("${ocbc.pictures.recomproduct.deposito}")
    public String OCBC_PICTURES_RECOMPRODUCT_DEPOSITO;

    @Value("${ocbc.pictures.infografik1}")
    public String OCBC_PICTURES_INFOGRAFIK1;

    @Value("${ocbc.pictures.infografik2}")
    public String OCBC_PICTURES_INFOGRAFIK2;

    @Value("${ocbc.pictures.infografik3}")
    public String OCBC_PICTURES_INFOGRAFIK3;

    @Value("${ocbc.pictures.infografik4}")
    public String OCBC_PICTURES_INFOGRAFIK4;

    @Value("${ocbc.pictures.infografik5}")
    public String OCBC_PICTURES_INFOGRAFIK5;

    @Value("${ocbc.pictures.infografik6}")
    public String OCBC_PICTURES_INFOGRAFIK6;

    @Value("${ocbc.pictures.infografik7}")
    public String OCBC_PICTURES_INFOGRAFIK7;

    public String getOCBC_PICTURE_CONSERVATIVE() {
        return OCBC_PICTURE_CONSERVATIVE;
    }

    public void setOCBC_PICTURE_CONSERVATIVE(String OCBC_PICTURE_CONSERVATIVE) {
        this.OCBC_PICTURE_CONSERVATIVE = OCBC_PICTURE_CONSERVATIVE;
    }

    public String getOCBC_PICTURE_BALANCE() {
        return OCBC_PICTURE_BALANCE;
    }

    public void setOCBC_PICTURE_BALANCE(String OCBC_PICTURE_BALANCE) {
        this.OCBC_PICTURE_BALANCE = OCBC_PICTURE_BALANCE;
    }

    public String getOCBC_PICTURE_GROWTH() {
        return OCBC_PICTURE_GROWTH;
    }

    public void setOCBC_PICTURE_GROWTH(String OCBC_PICTURE_GROWTH) {
        this.OCBC_PICTURE_GROWTH = OCBC_PICTURE_GROWTH;
    }

    public String getOCBC_PICTURE_AGGRESSIVE() {
        return OCBC_PICTURE_AGGRESSIVE;
    }

    public void setOCBC_PICTURE_AGGRESSIVE(String OCBC_PICTURE_AGGRESSIVE) {
        this.OCBC_PICTURE_AGGRESSIVE = OCBC_PICTURE_AGGRESSIVE;
    }

    public String getOCBC_PICTURES_INFOGRAFIK7() {
        return OCBC_PICTURES_INFOGRAFIK7;
    }

    public void setOCBC_PICTURES_INFOGRAFIK7(String OCBC_PICTURES_INFOGRAFIK7) {
        this.OCBC_PICTURES_INFOGRAFIK7 = OCBC_PICTURES_INFOGRAFIK7;
    }

    public String getOcbc_carousel_title_download() {
        return ocbc_carousel_title_download;
    }

    public void setOcbc_carousel_title_download(String ocbc_carousel_title_download) {
        this.ocbc_carousel_title_download = ocbc_carousel_title_download;
    }

    public String getOcbc_carousel_gambar_download() {
        return ocbc_carousel_gambar_download;
    }

    public void setOcbc_carousel_gambar_download(String ocbc_carousel_gambar_download) {
        this.ocbc_carousel_gambar_download = ocbc_carousel_gambar_download;
    }

    public String getOcbc_carousel_gambar_riskprofile() {
        return ocbc_carousel_gambar_riskprofile;
    }

    public void setOcbc_carousel_gambar_riskprofile(String ocbc_carousel_gambar_riskprofile) {
        this.ocbc_carousel_gambar_riskprofile = ocbc_carousel_gambar_riskprofile;
    }

    public String getOcbc_carousel_title_riskprofile() {
        return ocbc_carousel_title_riskprofile;
    }

    public void setOcbc_carousel_title_riskprofile(String ocbc_carousel_title_riskprofile) {
        this.ocbc_carousel_title_riskprofile = ocbc_carousel_title_riskprofile;
    }

    public String getOcbc_carousel_subtitle_riskprofile() {
        return ocbc_carousel_subtitle_riskprofile;
    }

    public void setOcbc_carousel_subtitle_riskprofile(String ocbc_carousel_subtitle_riskprofile) {
        this.ocbc_carousel_subtitle_riskprofile = ocbc_carousel_subtitle_riskprofile;
    }

    public String getOcbc_carousel_nameButton_riskprofile() {
        return ocbc_carousel_nameButton_riskprofile;
    }

    public void setOcbc_carousel_nameButton_riskprofile(String ocbc_carousel_nameButton_riskprofile) {
        this.ocbc_carousel_nameButton_riskprofile = ocbc_carousel_nameButton_riskprofile;
    }

    public String getOcbc_carousel_gambar_growth() {
        return ocbc_carousel_gambar_growth;
    }

    public void setOcbc_carousel_gambar_growth(String ocbc_carousel_gambar_growth) {
        this.ocbc_carousel_gambar_growth = ocbc_carousel_gambar_growth;
    }

    public String getOcbc_carousel_title_growth() {
        return ocbc_carousel_title_growth;
    }

    public void setOcbc_carousel_title_growth(String ocbc_carousel_title_growth) {
        this.ocbc_carousel_title_growth = ocbc_carousel_title_growth;
    }

    public String getOcbc_carousel_subtitle_growth() {
        return ocbc_carousel_subtitle_growth;
    }

    public void setOcbc_carousel_subtitle_growth(String ocbc_carousel_subtitle_growth) {
        this.ocbc_carousel_subtitle_growth = ocbc_carousel_subtitle_growth;
    }

    public String getOcbc_carousel_nameButton_growth() {
        return ocbc_carousel_nameButton_growth;
    }

    public void setOcbc_carousel_nameButton_growth(String ocbc_carousel_nameButton_growth) {
        this.ocbc_carousel_nameButton_growth = ocbc_carousel_nameButton_growth;
    }

    public String getOcbc_carousel_gambar_pendidikan() {
        return ocbc_carousel_gambar_pendidikan;
    }

    public void setOcbc_carousel_gambar_pendidikan(String ocbc_carousel_gambar_pendidikan) {
        this.ocbc_carousel_gambar_pendidikan = ocbc_carousel_gambar_pendidikan;
    }

    public String getOcbc_carousel_title_pendidikan() {
        return ocbc_carousel_title_pendidikan;
    }

    public void setOcbc_carousel_title_pendidikan(String ocbc_carousel_title_pendidikan) {
        this.ocbc_carousel_title_pendidikan = ocbc_carousel_title_pendidikan;
    }

    public String getOcbc_carousel_subtitle_pendidikan() {
        return ocbc_carousel_subtitle_pendidikan;
    }

    public void setOcbc_carousel_subtitle_pendidikan(String ocbc_carousel_subtitle_pendidikan) {
        this.ocbc_carousel_subtitle_pendidikan = ocbc_carousel_subtitle_pendidikan;
    }

    public String getOcbc_carousel_nameButton_pendidikan() {
        return ocbc_carousel_nameButton_pendidikan;
    }

    public void setOcbc_carousel_nameButton_pendidikan(String ocbc_carousel_nameButton_pendidikan) {
        this.ocbc_carousel_nameButton_pendidikan = ocbc_carousel_nameButton_pendidikan;
    }

    public String getOcbc_carousel_gambar_syaratdanketentuan() {
        return ocbc_carousel_gambar_syaratdanketentuan;
    }

    public void setOcbc_carousel_gambar_syaratdanketentuan(String ocbc_carousel_gambar_syaratdanketentuan) {
        this.ocbc_carousel_gambar_syaratdanketentuan = ocbc_carousel_gambar_syaratdanketentuan;
    }

    public String getOcbc_carousel_title_syaratdanketentuan() {
        return ocbc_carousel_title_syaratdanketentuan;
    }

    public void setOcbc_carousel_title_syaratdanketentuan(String ocbc_carousel_title_syaratdanketentuan) {
        this.ocbc_carousel_title_syaratdanketentuan = ocbc_carousel_title_syaratdanketentuan;
    }

    public String getOcbc_carousel_subtitle_syaratdanketentuan() {
        return ocbc_carousel_subtitle_syaratdanketentuan;
    }

    public void setOcbc_carousel_subtitle_syaratdanketentuan(String ocbc_carousel_subtitle_syaratdanketentuan) {
        this.ocbc_carousel_subtitle_syaratdanketentuan = ocbc_carousel_subtitle_syaratdanketentuan;
    }

    public String getOcbc_carousel_nameButton_syaratdanketentuan() {
        return ocbc_carousel_nameButton_syaratdanketentuan;
    }

    public void setOcbc_carousel_nameButton_syaratdanketentuan(String ocbc_carousel_nameButton_syaratdanketentuan) {
        this.ocbc_carousel_nameButton_syaratdanketentuan = ocbc_carousel_nameButton_syaratdanketentuan;
    }

    public String getOcbc_carousel_gambar_syaratdanketentuan_tidak() {
        return ocbc_carousel_gambar_syaratdanketentuan_tidak;
    }

    public void setOcbc_carousel_gambar_syaratdanketentuan_tidak(String ocbc_carousel_gambar_syaratdanketentuan_tidak) {
        this.ocbc_carousel_gambar_syaratdanketentuan_tidak = ocbc_carousel_gambar_syaratdanketentuan_tidak;
    }

    public String getOcbc_carousel_title_syaratdanketentuan_tidak() {
        return ocbc_carousel_title_syaratdanketentuan_tidak;
    }

    public void setOcbc_carousel_title_syaratdanketentuan_tidak(String ocbc_carousel_title_syaratdanketentuan_tidak) {
        this.ocbc_carousel_title_syaratdanketentuan_tidak = ocbc_carousel_title_syaratdanketentuan_tidak;
    }

    public String getOcbc_carousel_subtitle_syaratdanketentuan_tidak() {
        return ocbc_carousel_subtitle_syaratdanketentuan_tidak;
    }

    public void setOcbc_carousel_subtitle_syaratdanketentuan_tidak(String ocbc_carousel_subtitle_syaratdanketentuan_tidak) {
        this.ocbc_carousel_subtitle_syaratdanketentuan_tidak = ocbc_carousel_subtitle_syaratdanketentuan_tidak;
    }

    public String getOcbc_carousel_nameButton_syaratdanketentuan_tidak() {
        return ocbc_carousel_nameButton_syaratdanketentuan_tidak;
    }

    public void setOcbc_carousel_nameButton_syaratdanketentuan_tidak(String ocbc_carousel_nameButton_syaratdanketentuan_tidak) {
        this.ocbc_carousel_nameButton_syaratdanketentuan_tidak = ocbc_carousel_nameButton_syaratdanketentuan_tidak;
    }

    public String getOcbc_carousel_gambar_lainnya_nonton() {
        return ocbc_carousel_gambar_lainnya_nonton;
    }

    public void setOcbc_carousel_gambar_lainnya_nonton(String ocbc_carousel_gambar_lainnya_nonton) {
        this.ocbc_carousel_gambar_lainnya_nonton = ocbc_carousel_gambar_lainnya_nonton;
    }

    public String getOcbc_carousel_gambar_lainnya_liburan() {
        return ocbc_carousel_gambar_lainnya_liburan;
    }

    public void setOcbc_carousel_gambar_lainnya_liburan(String ocbc_carousel_gambar_lainnya_liburan) {
        this.ocbc_carousel_gambar_lainnya_liburan = ocbc_carousel_gambar_lainnya_liburan;
    }

    public String getOcbc_carousel_gambar_lainnya_beli() {
        return ocbc_carousel_gambar_lainnya_beli;
    }

    public void setOcbc_carousel_gambar_lainnya_beli(String ocbc_carousel_gambar_lainnya_beli) {
        this.ocbc_carousel_gambar_lainnya_beli = ocbc_carousel_gambar_lainnya_beli;
    }

    public String getOCBC_WPP_BASE_URL() {
        return OCBC_WPP_BASE_URL;
    }

    public String getOcbc_carousel_gambar_lainnya() {
        return ocbc_carousel_gambar_lainnya;
    }

    public void setOcbc_carousel_gambar_lainnya(String ocbc_carousel_gambar_lainnya) {
        this.ocbc_carousel_gambar_lainnya = ocbc_carousel_gambar_lainnya;
    }

    public String getOcbc_carousel_title_lainnya() {
        return ocbc_carousel_title_lainnya;
    }

    public void setOcbc_carousel_title_lainnya(String ocbc_carousel_title_lainnya) {
        this.ocbc_carousel_title_lainnya = ocbc_carousel_title_lainnya;
    }

    public String getOcbc_carousel_subtitle_lainnya() {
        return ocbc_carousel_subtitle_lainnya;
    }

    public void setOcbc_carousel_subtitle_lainnya(String ocbc_carousel_subtitle_lainnya) {
        this.ocbc_carousel_subtitle_lainnya = ocbc_carousel_subtitle_lainnya;
    }

    public String getOcbc_carousel_nameButton_lainnya() {
        return ocbc_carousel_nameButton_lainnya;
    }

    public void setOcbc_carousel_nameButton_lainnya(String ocbc_carousel_nameButton_lainnya) {
        this.ocbc_carousel_nameButton_lainnya = ocbc_carousel_nameButton_lainnya;
    }

    public String getOcbc_carousel_gambar_kantorCabangTerdekat() {
        return ocbc_carousel_gambar_kantorCabangTerdekat;
    }

    public String getOcbc_carousel_gambar_kota() {
        return ocbc_carousel_gambar_kota;
    }

    public void setOcbc_carousel_gambar_kota(String ocbc_carousel_gambar_kota) {
        this.ocbc_carousel_gambar_kota = ocbc_carousel_gambar_kota;
    }

    public void setOcbc_carousel_gambar_kantorCabangTerdekat(String ocbc_carousel_gambar_kantorCabangTerdekat) {
        this.ocbc_carousel_gambar_kantorCabangTerdekat = ocbc_carousel_gambar_kantorCabangTerdekat;
    }

    public String getOcbc_carousel_value_kantorCabangTerdekat1() {
        return ocbc_carousel_value_kantorCabangTerdekat1;
    }

    public void setOcbc_carousel_value_kantorCabangTerdekat1(String ocbc_carousel_value_kantorCabangTerdekat1) {
        this.ocbc_carousel_value_kantorCabangTerdekat1 = ocbc_carousel_value_kantorCabangTerdekat1;
    }

    public String getOcbc_carousel_value_kantorCabangTerdekat2() {
        return ocbc_carousel_value_kantorCabangTerdekat2;
    }

    public void setOcbc_carousel_value_kantorCabangTerdekat2(String ocbc_carousel_value_kantorCabangTerdekat2) {
        this.ocbc_carousel_value_kantorCabangTerdekat2 = ocbc_carousel_value_kantorCabangTerdekat2;
    }

    public String getOcbc_carousel_title_kantorCabangTerdekat() {
        return ocbc_carousel_title_kantorCabangTerdekat;
    }

    public void setOcbc_carousel_title_kantorCabangTerdekat(String ocbc_carousel_title_kantorCabangTerdekat) {
        this.ocbc_carousel_title_kantorCabangTerdekat = ocbc_carousel_title_kantorCabangTerdekat;
    }

    public String getOcbc_carousel_subtitle_kantorCabangTerdekat() {
        return ocbc_carousel_subtitle_kantorCabangTerdekat;
    }

    public void setOcbc_carousel_subtitle_kantorCabangTerdekat(String ocbc_carousel_subtitle_kantorCabangTerdekat) {
        this.ocbc_carousel_subtitle_kantorCabangTerdekat = ocbc_carousel_subtitle_kantorCabangTerdekat;
    }

    public String getOcbc_carousel_nameButton_kantorCabangTerdekat() {
        return ocbc_carousel_nameButton_kantorCabangTerdekat;
    }

    public void setOcbc_carousel_nameButton_kantorCabangTerdekat(String ocbc_carousel_nameButton_kantorCabangTerdekat) {
        this.ocbc_carousel_nameButton_kantorCabangTerdekat = ocbc_carousel_nameButton_kantorCabangTerdekat;
    }

    public void setOCBC_WPP_BASE_URL(String OCBC_WPP_BASE_URL) {
        this.OCBC_WPP_BASE_URL = OCBC_WPP_BASE_URL;
    }

    public String getOCBC_WPP_PATH_RISK_PROFILE() {
        return OCBC_WPP_PATH_RISK_PROFILE;
    }

    public void setOCBC_WPP_PATH_RISK_PROFILE(String OCBC_WPP_PATH_RISK_PROFILE) {
        this.OCBC_WPP_PATH_RISK_PROFILE = OCBC_WPP_PATH_RISK_PROFILE;
    }

    public String getOCBC_WPP_PATH_LIST_PRODUCT() {
        return OCBC_WPP_PATH_LIST_PRODUCT;
    }

    public void setOCBC_WPP_PATH_LIST_PRODUCT(String OCBC_WPP_PATH_LIST_PRODUCT) {
        this.OCBC_WPP_PATH_LIST_PRODUCT = OCBC_WPP_PATH_LIST_PRODUCT;
    }

    public String getOCBC_WPP_PATH_FUTURE_VALUE() {
        return OCBC_WPP_PATH_FUTURE_VALUE;
    }

    public void setOCBC_WPP_PATH_FUTURE_VALUE(String OCBC_WPP_PATH_FUTURE_VALUE) {
        this.OCBC_WPP_PATH_FUTURE_VALUE = OCBC_WPP_PATH_FUTURE_VALUE;
    }

    public String getOCBC_WPP_PATH_FUTURE_VALUE_DEPOSITO() {
        return OCBC_WPP_PATH_FUTURE_VALUE_DEPOSITO;
    }

    public void setOCBC_WPP_PATH_FUTURE_VALUE_DEPOSITO(String OCBC_WPP_PATH_FUTURE_VALUE_DEPOSITO) {
        this.OCBC_WPP_PATH_FUTURE_VALUE_DEPOSITO = OCBC_WPP_PATH_FUTURE_VALUE_DEPOSITO;
    }

    public String getOCBC_WPP_PATH_TARGET_VALUE() {
        return OCBC_WPP_PATH_TARGET_VALUE;
    }

    public void setOCBC_WPP_PATH_TARGET_VALUE(String OCBC_WPP_PATH_TARGET_VALUE) {
        this.OCBC_WPP_PATH_TARGET_VALUE = OCBC_WPP_PATH_TARGET_VALUE;
    }

    public String getOCBC_WPP_PATH_PRESENT_VALUE() {
        return OCBC_WPP_PATH_PRESENT_VALUE;
    }

    public void setOCBC_WPP_PATH_PRESENT_VALUE(String OCBC_WPP_PATH_PRESENT_VALUE) {
        this.OCBC_WPP_PATH_PRESENT_VALUE = OCBC_WPP_PATH_PRESENT_VALUE;
    }

    public String getOCBC_WPP_PATH_DATA_LEADS() {
        return OCBC_WPP_PATH_DATA_LEADS;
    }

    public void setOCBC_WPP_PATH_DATA_LEADS(String OCBC_WPP_PATH_DATA_LEADS) {
        this.OCBC_WPP_PATH_DATA_LEADS = OCBC_WPP_PATH_DATA_LEADS;
    }

    public String getOCBC_WPP_PATH_PDF_GENERATOR() {
        return OCBC_WPP_PATH_PDF_GENERATOR;
    }

    public void setOCBC_WPP_PATH_PDF_GENERATOR(String OCBC_WPP_PATH_PDF_GENERATOR) {
        this.OCBC_WPP_PATH_PDF_GENERATOR = OCBC_WPP_PATH_PDF_GENERATOR;
    }

    public String getOCBC_WEBVIEW_BASE_URL() {
        return OCBC_WEBVIEW_BASE_URL;
    }

    public void setOCBC_WEBVIEW_BASE_URL(String OCBC_WEBVIEW_BASE_URL) {
        this.OCBC_WEBVIEW_BASE_URL = OCBC_WEBVIEW_BASE_URL;
    }

    public String getOcbcWebViewBaseUrlGrowth() {
        return ocbcWebViewBaseUrlGrowth;
    }

    public void setOcbcWebViewBaseUrlGrowth(String ocbcWebViewBaseUrlGrowth) {
        this.ocbcWebViewBaseUrlGrowth = ocbcWebViewBaseUrlGrowth;
    }

    public String getOcbcWebViewBaseUrlEducation() {
        return ocbcWebViewBaseUrlEducation;
    }

    public void setOcbcWebViewBaseUrlEducation(String ocbcWebViewBaseUrlEducation) {
        this.ocbcWebViewBaseUrlEducation = ocbcWebViewBaseUrlEducation;
    }

    public String getOcbcWebViewBaseUrlEtc() {
        return ocbcWebViewBaseUrlEtc;
    }

    public void setOcbcWebViewBaseUrlEtc(String ocbcWebViewBaseUrlEtc) {
        this.ocbcWebViewBaseUrlEtc = ocbcWebViewBaseUrlEtc;
    }

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getMailStmpAuth() {
        return mailStmpAuth;
    }

    public void setMailStmpAuth(String mailStmpAuth) {
        this.mailStmpAuth = mailStmpAuth;
    }

    public String getMailSmtpTls() {
        return mailSmtpTls;
    }

    public void setMailSmtpTls(String mailSmtpTls) {
        this.mailSmtpTls = mailSmtpTls;
    }

    public String getMailSmtpHost() {
        return mailSmtpHost;
    }

    public void setMailSmtpHost(String mailSmtpHost) {
        this.mailSmtpHost = mailSmtpHost;
    }

    public String getMailSmtpPort() {
        return mailSmtpPort;
    }

    public void setMailSmtpPort(String mailSmtpPort) {
        this.mailSmtpPort = mailSmtpPort;
    }

    /**
     * @return the sdkConnectTimeout
     */
    public String getSdkConnectTimeout() {
        return sdkConnectTimeout;
    }

    /**
     * @param sdkConnectTimeout the sdkConnectTimeout to set
     */
    public void setSdkConnectTimeout(String sdkConnectTimeout) {
        this.sdkConnectTimeout = sdkConnectTimeout;
    }

    /**
     * @return the sdkReadTimeout
     */
    public String getSdkReadTimeout() {
        return sdkReadTimeout;
    }

    /**
     * @param sdkReadTimeout the sdkReadTimeout to set
     */
    public void setSdkReadTimeout(String sdkReadTimeout) {
        this.sdkReadTimeout = sdkReadTimeout;
    }

    /**
     * @return the sdkDolphinUsername
     */
    public String getSdkDolphinUsername() {
        return sdkDolphinUsername;
    }

    /**
     * @param sdkDolphinUsername the sdkDolphinUsername to set
     */
    public void setSdkDolphinUsername(String sdkDolphinUsername) {
        this.sdkDolphinUsername = sdkDolphinUsername;
    }

    /**
     * @return the sdkDolphinPassword
     */
    public String getSdkDolphinPassword() {
        return sdkDolphinPassword;
    }

    /**
     * @param sdkDolphinPassword the sdkDolphinPassword to set
     */
    public void setSdkDolphinPassword(String sdkDolphinPassword) {
        this.sdkDolphinPassword = sdkDolphinPassword;
    }

    /**
     * @return the sdkDolphinBaseUrl
     */
    public String getSdkDolphinBaseUrl() {
        return sdkDolphinBaseUrl;
    }

    /**
     * @param sdkDolphinBaseUrl the sdkDolphinBaseUrl to set
     */
    public void setSdkDolphinBaseUrl(String sdkDolphinBaseUrl) {
        this.sdkDolphinBaseUrl = sdkDolphinBaseUrl;
    }

    /**
     * @return the sdkDolphinGraphAuth
     */
    public String getSdkDolphinGraphAuth() {
        return sdkDolphinGraphAuth;
    }

    /**
     * @param sdkDolphinGraphAuth the sdkDolphinGraphAuth to set
     */
    public void setSdkDolphinGraphAuth(String sdkDolphinGraphAuth) {
        this.sdkDolphinGraphAuth = sdkDolphinGraphAuth;
    }

    /**
     * @return the sdkDolphinGraphContacts
     */
    public String getSdkDolphinGraphContacts() {
        return sdkDolphinGraphContacts;
    }

    /**
     * @param sdkDolphinGraphContacts the sdkDolphinGraphContacts to set
     */
    public void setSdkDolphinGraphContacts(String sdkDolphinGraphContacts) {
        this.sdkDolphinGraphContacts = sdkDolphinGraphContacts;
    }

    /**
     * @return the sdkDolphinGraphContactsUpdate
     */
    public String getSdkDolphinGraphContactsUpdate() {
        return sdkDolphinGraphContactsUpdate;
    }

    /**
     * @param sdkDolphinGraphContactsUpdate the sdkDolphinGraphContactsUpdate to
     * set
     */
    public void setSdkDolphinGraphContactsUpdate(String sdkDolphinGraphContactsUpdate) {
        this.sdkDolphinGraphContactsUpdate = sdkDolphinGraphContactsUpdate;
    }

    public String getOcbc_carousel_investratio1() {
        return ocbc_carousel_investratio1;
    }

    public void setOcbc_carousel_investratio1(String ocbc_carousel_investratio1) {
        this.ocbc_carousel_investratio1 = ocbc_carousel_investratio1;
    }

    public String getOcbc_carousel_investratio2() {
        return ocbc_carousel_investratio2;
    }

    public void setOcbc_carousel_investratio2(String ocbc_carousel_investratio2) {
        this.ocbc_carousel_investratio2 = ocbc_carousel_investratio2;
    }

    public String getOcbc_carousel_investratio3() {
        return ocbc_carousel_investratio3;
    }

    public void setOcbc_carousel_investratio3(String ocbc_carousel_investratio3) {
        this.ocbc_carousel_investratio3 = ocbc_carousel_investratio3;
    }

    public String getOcbc_carousel_investratio4() {
        return ocbc_carousel_investratio4;
    }

    public void setOcbc_carousel_investratio4(String ocbc_carousel_investratio4) {
        this.ocbc_carousel_investratio4 = ocbc_carousel_investratio4;
    }

    public String getOcbc_carousel_investratio5() {
        return ocbc_carousel_investratio5;
    }

    public void setOcbc_carousel_investratio5(String ocbc_carousel_investratio5) {
        this.ocbc_carousel_investratio5 = ocbc_carousel_investratio5;
    }

    public String getOCBC_PICTURES_ETC() {
        return OCBC_PICTURES_ETC;
    }

    public void setOCBC_PICTURES_ETC(String OCBC_PICTURES_ETC) {
        this.OCBC_PICTURES_ETC = OCBC_PICTURES_ETC;
    }

    public String getAPP_MODE() {
        return APP_MODE;
    }

    public void setAPP_MODE(String APP_MODE) {
        this.APP_MODE = APP_MODE;
    }

    public String getOCBC_WEBVIEW_BASE_URL_TNC() {
        return OCBC_WEBVIEW_BASE_URL_TNC;
    }

    public void setOCBC_WEBVIEW_BASE_URL_TNC(String OCBC_WEBVIEW_BASE_URL_TNC) {
        this.OCBC_WEBVIEW_BASE_URL_TNC = OCBC_WEBVIEW_BASE_URL_TNC;
    }

    public String getOCBC_WEBVIEW_BASE_URL_PDF() {
        return OCBC_WEBVIEW_BASE_URL_PDF;
    }

    public void setOCBC_WEBVIEW_BASE_URL_PDF(String OCBC_WEBVIEW_BASE_URL_PDF) {
        this.OCBC_WEBVIEW_BASE_URL_PDF = OCBC_WEBVIEW_BASE_URL_PDF;
    }

    public String getOCBC_FOLDER_PICTURES() {
        return OCBC_FOLDER_PICTURES;
    }

    public void setOCBC_FOLDER_PICTURES(String OCBC_FOLDER_PICTURES) {
        this.OCBC_FOLDER_PICTURES = OCBC_FOLDER_PICTURES;
    }

    public String getOCBC_FOLDER_RESOURCES() {
        return OCBC_FOLDER_RESOURCES;
    }

    public void setOCBC_FOLDER_RESOURCES(String OCBC_FOLDER_RESOURCES) {
        this.OCBC_FOLDER_RESOURCES = OCBC_FOLDER_RESOURCES;
    }

    public String getOCBC_MAIL_SUBJECT() {
        return OCBC_MAIL_SUBJECT;
    }

    public void setOCBC_MAIL_SUBJECT(String OCBC_MAIL_SUBJECT) {
        this.OCBC_MAIL_SUBJECT = OCBC_MAIL_SUBJECT;
    }

    public String getOCBC_MAIL_BODY() {
        return OCBC_MAIL_BODY;
    }

    public void setOCBC_MAIL_BODY(String OCBC_MAIL_BODY) {
        this.OCBC_MAIL_BODY = OCBC_MAIL_BODY;
    }

    public String getOCBC_FOLDER_PDF() {
        return OCBC_FOLDER_PDF;
    }

    public void setOCBC_FOLDER_PDF(String OCBC_FOLDER_PDF) {
        this.OCBC_FOLDER_PDF = OCBC_FOLDER_PDF;
    }

    public String getOCBC_ENCRYPT_SALT() {
        return OCBC_ENCRYPT_SALT;
    }

    public void setOCBC_ENCRYPT_SALT(String OCBC_ENCRYPT_SALT) {
        this.OCBC_ENCRYPT_SALT = OCBC_ENCRYPT_SALT;
    }

    public String getOCBC_ENCRYPT_KEY() {
        return OCBC_ENCRYPT_KEY;
    }

    public void setOCBC_ENCRYPT_KEY(String OCBC_ENCRYPT_KEY) {
        this.OCBC_ENCRYPT_KEY = OCBC_ENCRYPT_KEY;
    }

    public String getOCBC_ENCRYPT_TOKEN_EXP() {
        return OCBC_ENCRYPT_TOKEN_EXP;
    }

    public void setOCBC_ENCRYPT_TOKEN_EXP(String OCBC_ENCRYPT_TOKEN_EXP) {
        this.OCBC_ENCRYPT_TOKEN_EXP = OCBC_ENCRYPT_TOKEN_EXP;
    }

    public String getOCBC_LINK_ONEMOBILE() {
        return OCBC_LINK_ONEMOBILE;
    }

    public void setOCBC_LINK_ONEMOBILE(String OCBC_LINK_ONEMOBILE) {
        this.OCBC_LINK_ONEMOBILE = OCBC_LINK_ONEMOBILE;
    }

    public String getOCBC_LINK_DAFTARNASABAH() {
        return OCBC_LINK_DAFTARNASABAH;
    }

    public void setOCBC_LINK_DAFTARNASABAH(String OCBC_LINK_DAFTARNASABAH) {
        this.OCBC_LINK_DAFTARNASABAH = OCBC_LINK_DAFTARNASABAH;
    }

    public String getOCBC_PICTURES_QUOTES() {
        return OCBC_PICTURES_QUOTES;
    }

    public void setOCBC_PICTURES_QUOTES(String OCBC_PICTURE_QUOTES) {
        this.OCBC_PICTURES_QUOTES = OCBC_PICTURE_QUOTES;
    }

    public String getOCBC_PICTURES_ONEMOBILE() {
        return OCBC_PICTURES_ONEMOBILE;
    }

    public void setOCBC_PICTURES_ONEMOBILE(String OCBC_PICTURES_ONEMOBILE) {
        this.OCBC_PICTURES_ONEMOBILE = OCBC_PICTURES_ONEMOBILE;
    }

    public String getOCBC_PICTURES_DAFTARNASABAH() {
        return OCBC_PICTURES_DAFTARNASABAH;
    }

    public void setOCBC_PICTURES_DAFTARNASABAH(String OCBC_PICTURES_DAFTARNASABAH) {
        this.OCBC_PICTURES_DAFTARNASABAH = OCBC_PICTURES_DAFTARNASABAH;
    }

    public String getOCBC_PICTURES_CAROUSELQUOTES() {
        return OCBC_PICTURES_CAROUSELQUOTES;
    }

    public void setOCBC_PICTURES_CAROUSELQUOTES(String OCBC_PICTURES_CAROUSELQUOTES) {
        this.OCBC_PICTURES_CAROUSELQUOTES = OCBC_PICTURES_CAROUSELQUOTES;
    }

    public String getOCBC_PICTURES_RECOMPRODUCT_LUMPSUM() {
        return OCBC_PICTURES_RECOMPRODUCT_LUMPSUM;
    }

    public void setOCBC_PICTURES_RECOMPRODUCT_LUMPSUM(String OCBC_PICTURES_RECOMPRODUCT_LUMPSUM) {
        this.OCBC_PICTURES_RECOMPRODUCT_LUMPSUM = OCBC_PICTURES_RECOMPRODUCT_LUMPSUM;
    }

    public String getOCBC_PICTURES_RECOMPRODUCT_MONTHLY() {
        return OCBC_PICTURES_RECOMPRODUCT_MONTHLY;
    }

    public void setOCBC_PICTURES_RECOMPRODUCT_MONTHLY(String OCBC_PICTURES_RECOMPRODUCT_MONTHLY) {
        this.OCBC_PICTURES_RECOMPRODUCT_MONTHLY = OCBC_PICTURES_RECOMPRODUCT_MONTHLY;
    }

    public String getOCBC_PICTURES_RECOMPRODUCT_TAKA() {
        return OCBC_PICTURES_RECOMPRODUCT_TAKA;
    }

    public void setOCBC_PICTURES_RECOMPRODUCT_TAKA(String OCBC_PICTURES_RECOMPRODUCT_TAKA) {
        this.OCBC_PICTURES_RECOMPRODUCT_TAKA = OCBC_PICTURES_RECOMPRODUCT_TAKA;
    }

    public String getOCBC_PICTURES_RECOMPRODUCT_DEPOSITO() {
        return OCBC_PICTURES_RECOMPRODUCT_DEPOSITO;
    }

    public void setOCBC_PICTURES_RECOMPRODUCT_DEPOSITO(String OCBC_PICTURES_RECOMPRODUCT_DEPOSITO) {
        this.OCBC_PICTURES_RECOMPRODUCT_DEPOSITO = OCBC_PICTURES_RECOMPRODUCT_DEPOSITO;
    }

    public String getOCBC_PICTURES_INFOGRAFIK1() {
        return OCBC_PICTURES_INFOGRAFIK1;
    }

    public void setOCBC_PICTURES_INFOGRAFIK1(String OCBC_PICTURES_INFOGRAFIK1) {
        this.OCBC_PICTURES_INFOGRAFIK1 = OCBC_PICTURES_INFOGRAFIK1;
    }

    public String getOCBC_PICTURES_INFOGRAFIK2() {
        return OCBC_PICTURES_INFOGRAFIK2;
    }

    public void setOCBC_PICTURES_INFOGRAFIK2(String OCBC_PICTURES_INFOGRAFIK2) {
        this.OCBC_PICTURES_INFOGRAFIK2 = OCBC_PICTURES_INFOGRAFIK2;
    }

    public String getOCBC_PICTURES_INFOGRAFIK3() {
        return OCBC_PICTURES_INFOGRAFIK3;
    }

    public void setOCBC_PICTURES_INFOGRAFIK3(String OCBC_PICTURES_INFOGRAFIK3) {
        this.OCBC_PICTURES_INFOGRAFIK3 = OCBC_PICTURES_INFOGRAFIK3;
    }

    public String getOCBC_PICTURES_INFOGRAFIK4() {
        return OCBC_PICTURES_INFOGRAFIK4;
    }

    public void setOCBC_PICTURES_INFOGRAFIK4(String OCBC_PICTURES_INFOGRAFIK4) {
        this.OCBC_PICTURES_INFOGRAFIK4 = OCBC_PICTURES_INFOGRAFIK4;
    }

    public String getOCBC_PICTURES_INFOGRAFIK5() {
        return OCBC_PICTURES_INFOGRAFIK5;
    }

    public void setOCBC_PICTURES_INFOGRAFIK5(String OCBC_PICTURES_INFOGRAFIK5) {
        this.OCBC_PICTURES_INFOGRAFIK5 = OCBC_PICTURES_INFOGRAFIK5;
    }

    public String getOCBC_PICTURES_INFOGRAFIK6() {
        return OCBC_PICTURES_INFOGRAFIK6;
    }

    public void setOCBC_PICTURES_INFOGRAFIK6(String OCBC_PICTURES_INFOGRAFIK6) {
        this.OCBC_PICTURES_INFOGRAFIK6 = OCBC_PICTURES_INFOGRAFIK6;
    }

}

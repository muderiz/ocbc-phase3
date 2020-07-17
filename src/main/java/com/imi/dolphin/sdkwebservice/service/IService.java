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
package com.imi.dolphin.sdkwebservice.service;

import com.imi.dolphin.sdkwebservice.model.ExtensionRequest;
import com.imi.dolphin.sdkwebservice.model.ExtensionResult;
import com.imi.dolphin.sdkwebservice.model.RiskProfileResponse;

/**
 *
 * @author reja
 *
 */
public interface IService {

    ExtensionResult getSrnResult(ExtensionRequest extensionRequest);

    ExtensionResult getCustomerInfo(ExtensionRequest extensionRequest);

    ExtensionResult modifyCustomerName(ExtensionRequest extensionRequest);

    ExtensionResult getProductInfo(ExtensionRequest extensionRequest);

    ExtensionResult getMessageBody(ExtensionRequest extensionRequest);

    ExtensionResult getQuickReplies(ExtensionRequest extensionRequest);

    ExtensionResult getForms(ExtensionRequest extensionRequest);

    ExtensionResult getButtons(ExtensionRequest extensionRequest);

    ExtensionResult getCarousel(ExtensionRequest extensionRequest);

    ExtensionResult doTransferToAgent(ExtensionRequest extensionRequest);

    ExtensionResult doSendLocation(ExtensionRequest extensionRequest);

    ExtensionResult getImage(ExtensionRequest extensionRequest);

    ExtensionResult getSplitConversation(ExtensionRequest extensionRequest);

    ExtensionResult doSendMail(ExtensionRequest extensionRequest);

    ExtensionResult getDolphinResponse(ExtensionRequest extensionRequest);

    ExtensionResult setName(ExtensionRequest extensionRequest);

    ExtensionResult validasiNama(ExtensionRequest extensionRequest);

    ExtensionResult entitasNama(ExtensionRequest extensionRequest);

    ExtensionResult setAdditionalField(ExtensionRequest extensionRequest);

    ExtensionResult getAdditionalField(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_validasiSyaratKetentuan(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_entitasSyaratDanKetentuan(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_entitasUmur(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_validasiUmur(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_entitasStatusPerkawinan(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_validasiStatusPerkawinan(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_entitasPendapatan(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_validasiPendapatan(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_entitasTabungan(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_validasiTabungan(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_entitasStatusInvestasi(ExtensionRequest extensionRequest);

    ExtensionResult mulaiBerinvestasi_validasiStatusInvestasi(ExtensionRequest extensionRequest);
    
    ExtensionResult coba_video(ExtensionRequest extensionRequest);
    
    ExtensionResult mulaiBerinvestasi_validasiRiskProfile(ExtensionRequest extensionRequest);

//    ExtensionResult pertumbuhanAset_entitasPendanaan(ExtensionRequest extensionRequest);
//
//    ExtensionResult pertumbuhanAset_ValidasiPendanaan(ExtensionRequest extensionRequest);
//
//    ExtensionResult pertumbuhanAset_entitasLamaInvestasi(ExtensionRequest extensionRequest);
//
//    ExtensionResult pertumbuhanAset_ValidasiLamaInvestasi(ExtensionRequest extensionRequest);
//
//    ExtensionResult pertumbuhanAset_entitasBesarDanaInvestasi(ExtensionRequest extensionRequest);
//
//    ExtensionResult pertumbuhanAset_ValidasiBesarDanaInvestasi(ExtensionRequest extensionRequest);

//    ExtensionResult pendidikanKuliahAnak_entitasUsiaAnak(ExtensionRequest extensionRequest);
//
//    ExtensionResult pendidikanKuliahAnak_validasiUsiaAnak(ExtensionRequest extensionRequest);
//
//    ExtensionResult pendidikanKuliahAnak_entitasNegaraKuliah(ExtensionRequest extensionRequest);
//
//    ExtensionResult pendidikanKuliahAnak_validasiNegaraKuliah(ExtensionRequest extensionRequest);
//
//    ExtensionResult pendidikanKuliahAnak_entitasDanaSekarang(ExtensionRequest extensionRequest);
//
//    ExtensionResult pendidikanKuliahAnak_validasiDanaSekarang(ExtensionRequest extensionRequest);

//    ExtensionResult lifeGoalLainnya_entitasJudulLifeGoal(ExtensionRequest extensionRequest);
//
//    ExtensionResult lifeGoalLainnya_validasiJudulLifeGoal(ExtensionRequest extensionRequest);
//    
//    ExtensionResult lifeGoalLainnya_entitasKebutuhanDana(ExtensionRequest extensionRequest);
//    
//    ExtensionResult lifeGoalLainnya_validasiKebutuhanDana(ExtensionRequest extensionRequest);
//    
//    ExtensionResult lifeGoalLainnya_entitasDanaSekarang(ExtensionRequest extensionRequest);
//    
//    ExtensionResult lifeGoalLainnya_validasiDanaSekarang(ExtensionRequest extensionRequest);
//    
//    ExtensionResult lifeGoalLainnya_entitasLamaBerinvestasi(ExtensionRequest extensionRequest);
//    
//    ExtensionResult lifeGoalLainnya_validasiLamaBerinvestasi(ExtensionRequest extensionRequest);
//    
//    ExtensionResult riskProfile_entitasSumberDana(ExtensionRequest extensionRequest);
//
//    ExtensionResult riskProfile_validasiSumberDana(ExtensionRequest extensionRequest);
//
//    ExtensionResult riskProfile_entitasPengetahuan(ExtensionRequest extensionRequest);
//
//    ExtensionResult riskProfile_validasiPengetahuan(ExtensionRequest extensionRequest);
//
//    ExtensionResult riskProfile_entitasPembagianHasil(ExtensionRequest extensionRequest);
//
//    ExtensionResult riskProfile_validasiPembagianHasil(ExtensionRequest extensionRequest);
//
//    ExtensionResult riskProfile_entitasSkenarioKeuntungan(ExtensionRequest extensionRequest);
//
//    ExtensionResult riskProfile_validasiSkenarioKeuntungan(ExtensionRequest extensionRequest);

//    ExtensionResult setDataMulaiBerinvestasi(ExtensionRequest extensionRequest);
    
//    ExtensionResult pertumbuhanAset_validasiWebView(ExtensionRequest extensionRequest);
    
//    ExtensionResult pendidikanKuliahAnak_validasiWebView(ExtensionRequest extensionRequest);
    
//    ExtensionResult lifeGoalLainnya_validasiWebView(ExtensionRequest extensionRequest);
    
    ExtensionResult getCarouselGambarDinamis(ExtensionRequest extensionRequest);

//    ExtensionResult contactUser_entitasNama(ExtensionRequest extensionRequest);
//
//    ExtensionResult contactUser_validasiNama(ExtensionRequest extensionRequest);
//
//    ExtensionResult contactUser_entitasEmail(ExtensionRequest extensionRequest);
//
//    ExtensionResult contactUser_validasiEmail(ExtensionRequest extensionRequest);
//
//    ExtensionResult contactUser_entitasLokasi(ExtensionRequest extensionRequest);
//
//    ExtensionResult contactUser_validasiLokasi(ExtensionRequest extensionRequest);
//
//    ExtensionResult contactUser_entitasNoTlp(ExtensionRequest extensionRequest);
//
//    ExtensionResult contactUser_validasiNoTlp(ExtensionRequest extensionRequest);

    String getBodyFromApia();

    int getBodyFormApib();

    GetBodyFromAPI getBodyObject();

//    ExtensionResult riskProfile_final(ExtensionRequest extensionRequest);
    
//    ExtensionResult pertumbuhanAset_webView(ExtensionRequest extensionRequest);
    
//    ExtensionResult pendidikanKuliahAnak_webView(ExtensionRequest extensionRequest);
    
//    ExtensionResult lifeGoalLainnya_webView(ExtensionRequest extensionRequest);
}

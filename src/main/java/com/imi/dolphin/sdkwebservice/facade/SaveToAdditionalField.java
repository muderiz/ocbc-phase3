/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.facade;

import com.google.gson.Gson;
import com.imi.dolphin.sdkwebservice.model.Contact;
import com.imi.dolphin.sdkwebservice.model.DataLeads;
import com.imi.dolphin.sdkwebservice.model.Transaction_Data;
import com.imi.dolphin.sdkwebservice.model.UserToken;
import com.imi.dolphin.sdkwebservice.service.IDolphinService;
import com.imi.dolphin.sdkwebservice.service.ServiceImp;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chochong
 */
@Service
public class SaveToAdditionalField {

    private static final Logger log = LogManager.getLogger(ServiceImp.class);
    private UserToken userToken;
    private boolean status = true;
    @Autowired
    IDolphinService svcDolphinService;

    /**
     * Digunakan untuk menyimpan data kedalam variabel global yang akan digunakan kembali pada proses selanjutkan
     * @param contactId berupa string
     * @param sumber_dana berupa string
     * @param pengetahuan berupa string
     * @param pembagian_hasil berupa string
     * @param skenario_keuntungan berupa string
     * @return boolean true of false
     */
    public boolean save_Risk_Profile(String contactId, String sumber_dana, String pengetahuan, String pembagian_hasil, String skenario_keuntungan) {
        log.debug("save_Risk_Profile() contactId: {}", contactId);
        try {
            userToken = svcDolphinService.getUserToken(userToken);
            Contact contact = svcDolphinService.getCustomer(userToken, contactId);
            String b = contact.getAdditionalField().get(0);
            DataLeads dataLeads1 = new Gson().fromJson(b, DataLeads.class);
            dataLeads1.getTransaction_Data()[0].getEC_ID();
            ArrayList<Transaction_Data> list_transaction_Data = new ArrayList<>();
            Transaction_Data transaction_Data = new Transaction_Data();
            transaction_Data.setName(dataLeads1.getTransaction_Data()[0].getName());
            transaction_Data.setAge(dataLeads1.getTransaction_Data()[0].getAge());
            transaction_Data.setEmail(dataLeads1.getTransaction_Data()[0].getEmail());
            transaction_Data.setPhone_Number(dataLeads1.getTransaction_Data()[0].getPhone_Number());
            transaction_Data.setLocation(dataLeads1.getTransaction_Data()[0].getLocation());
            transaction_Data.setIs_Married(dataLeads1.getTransaction_Data()[0].getIs_Married());
            transaction_Data.setIs_Already_Invest(dataLeads1.getTransaction_Data()[0].getIs_Already_Invest());
            transaction_Data.setFinancial_Info_Income_Range_ID(dataLeads1.getTransaction_Data()[0].getFinancial_Info_Income_Range_ID());
            transaction_Data.setFinancial_Info_Saved_Income_ID(dataLeads1.getTransaction_Data()[0].getFinancial_Info_Saved_Income_ID());
            transaction_Data.setRisk_Profile_ID(dataLeads1.getTransaction_Data()[0].getRisk_Profile_ID());
            transaction_Data.setLife_Goal_ID(1);
            transaction_Data.setFinancial_Goal(dataLeads1.getTransaction_Data()[0].getFinancial_Goal());
            transaction_Data.setInvestment_Type(dataLeads1.getTransaction_Data()[0].getInvestment_Type());
            transaction_Data.setTenor(dataLeads1.getTransaction_Data()[0].getTenor());
            transaction_Data.setInvestment_Amount(dataLeads1.getTransaction_Data()[0].getInvestment_Amount());
            transaction_Data.setTarget_Amount(dataLeads1.getTransaction_Data()[0].getTarget_Amount());
            transaction_Data.setInitial_Amount(dataLeads1.getTransaction_Data()[0].getInitial_Amount());
            transaction_Data.setEC_ID(dataLeads1.getTransaction_Data()[0].getEC_ID());
            transaction_Data.setChildren_Age(dataLeads1.getTransaction_Data()[0].getChildren_Age());
            transaction_Data.setProduct_ID(dataLeads1.getTransaction_Data()[0].getProduct_ID());
            transaction_Data.setDatetime_Start_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
            transaction_Data.setDatetime_End_Process(dataLeads1.getTransaction_Data()[0].getDatetime_Start_Process());
            transaction_Data.setTime_Horizon(dataLeads1.getTransaction_Data()[0].getTime_Horizon());
            transaction_Data.setSource_of_Income(sumber_dana);
            transaction_Data.setInvestment_Knowledge(pengetahuan);
            transaction_Data.setIncome_Usage(pembagian_hasil);
            transaction_Data.setPotential_Loss(skenario_keuntungan);
            transaction_Data.setPlatform_Name("OCBC NISP Website / risk profile berhasil");
            list_transaction_Data.add(transaction_Data);
            Transaction_Data[] arrTransaction_data = new Transaction_Data[1];
            arrTransaction_data = list_transaction_Data.toArray(arrTransaction_data);
            DataLeads dataLeads = new DataLeads();
            dataLeads.setChannel_ID("1");
            dataLeads.setExt_Reff_ID("ASDASDS907098");
            dataLeads.setTransaction_Data(arrTransaction_data);
            List<String> listData = new ArrayList<>();
            listData.add("" + new Gson().toJson(dataLeads, DataLeads.class) + "");
            contact.setAdditionalField(listData);
            contact = svcDolphinService.updateCustomer(userToken, contact);
        } catch (Exception e) {
            status = false;
            log.debug("save_Risk_Profile() Exception: {}", e);
        }
        return status;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author chochong
 */
public class ConvertToParam {
    
    private static final Logger log = LogManager.getLogger(ServiceImp.class);

    /**
     * convert to value data leads
     * @param input from user
     * @return a is integer
     */
    public int convertStatusPerkawinan(String input) {
        int a = 10;
        if ("Belum".equalsIgnoreCase(input)) {
            a = 0;
        } else if ("Sudah".equalsIgnoreCase(input)) {
            a = 1;
        }
        log.debug("convertMulaiBerinvestasiStatusPerkawinan() hasil Convert " + input + " = {} ", a);
        return a;
    }
    
    /**
     * convert to value data leads
     * @param input from user
     * @return a is integer
     */
    public int convertPendapatanBulanan(String input) {
        int a = 10;
        if("kurang dari 15 juta".equalsIgnoreCase(input)){
            a = 1;
        } else if("antara 15 sampai 35 juta".equalsIgnoreCase(input)){
            a = 2;
        } else if("lebih dari 35 juta".equalsIgnoreCase(input)){
            a = 3;
        }
        log.debug("convertMulaiBerinvestasiPendapatanBulanan() hasil Convert " + input + " = {} ", a);
        return a;
    }
    
    /**
     * convert to value data leads
     * @param input from user
     * @return a is integer
     */
    public int convertTabungPendapatan(String input) {
        int a = 10;
        if("kurang dari 10%".equalsIgnoreCase(input)){
            a = 1;
        } else if("antara 10 sampai 30%".equalsIgnoreCase(input)){
            a = 2;
        } else if("lebih dari 30%".equalsIgnoreCase(input)){
            a = 3;
        }
        log.debug("convertMulaiBerinvestasiTabungPendapatan() hasil Convert " + input + " = {} ", a);
        return a;
    }
    
    /**
     * convert to value data leads
     * @param input from user
     * @return a is integer
     */
    public int convertStatusInvestasi(String input) {
        int a = 10;
        if ("Belum".equalsIgnoreCase(input)) {
            a = 0;
        } else if ("Sudah".equalsIgnoreCase(input)) {
            a = 1;
        }
        log.debug("convertMulaiBerinvestasiStatusInvestasi() hasil Convert " + input + " = {} ", a);
        return a;
    }
    
    /**
     * convert to value data leads
     * @param input from user
     * @return a is integer
     */
    public int convertMulaiBerinvestasiRiskProfile(String input) {
        
        String b = input.toLowerCase()
                .replace("pilih 1", "Conservative")
                .replace("pilih 2", "Balance")
                .replace("pilih 3", "Growth")
                .replace("pilih 4", "Aggressive")
                .trim();
        int a = 0;
        if ("Conservative".equalsIgnoreCase(b)) {
            a = 1;
        } else if ("Balance".equalsIgnoreCase(b)) {
            a = 2;
        } else if ("Growth".equalsIgnoreCase(b)) {
            a = 3;
        } else if ("Aggressive".equalsIgnoreCase(b)) {
            a = 4;
        } else if ("Hitung Profil Risiko Kembali".equalsIgnoreCase(b)) {
            a = 0;
        }
        log.debug("convertMulaiBerinvestasiRiskProfile() hasil Convert " + b + " = {} ", a);
        return a;
    }

    /**
     * convert to value data leads
     * @param input from user
     * @return a is String
     */
    public String convertRiskProfileSkenarioKeuntungan(String input) {
        String a = "";
        if ("pilih a".equalsIgnoreCase(input)) {
            a = "5.a";
        } else if ("pilih b".equalsIgnoreCase(input)) {
            a = "5.b";
        } else if ("pilih c".equalsIgnoreCase(input)) {
            a = "5.c";
        } else if ("pilih d".equalsIgnoreCase(input)) {
            a = "5.d";
        } else if ("pilih e".equalsIgnoreCase(input)) {
            a = "5.e";
        }
        log.debug("convertRiskProfileSkenarioKeuntungan() hasil Convert " + input + " = {} ", a);
        return a;
    }

    /**
     * convert to value data leads
     * @param input from user
     * @return a is String
     */
    public String convertRiskProfileSumberDana(String input) {
        String a = "";
        if ("gaji".equalsIgnoreCase(input)) {
            a = "2.a";
        } else if ("Usaha/bisnis pribadi".equalsIgnoreCase(input)) {
            a = "2.b";
        } else if ("Warisan/dari orang tua".equalsIgnoreCase(input)) {
            a = "2.c";
        } else if ("Tidak ada sumber tetap".equalsIgnoreCase(input)) {
            a = "2.d";
        }
        log.debug("convertRiskProfileSumberDana() hasil Convert " + input + " = {} ", a);
        return a;
    }

    /**
     * convert to value data leads
     * @param input from user
     * @return a is String
     */
    public String convertRiskProfilePengetahuan(String input) {
        String a = "";
        if ("Sangat Terbatas".equalsIgnoreCase(input)) {
            a = "3.a";
        } else if ("Terbatas".equalsIgnoreCase(input)) {
            a = "3.b";
        } else if ("Cukup".equalsIgnoreCase(input)) {
            a = "3.c";
        } else if ("Mengerti".equalsIgnoreCase(input)) {
            a = "3.d";
        } else if ("Sangat Mengerti".equalsIgnoreCase(input)) {
            a = "3.e";
        }
        log.debug("convertRiskProfilePengetahuan() hasil Convert " + input + " = {} ", a);
        return a;
    }

    /**
     * convert to value data leads
     * @param input from user
     * @return a is String
     */
    public String convertRiskProfilePembagianKeuntungan(String input) {
        String a = "";
        if ("pilih a".equalsIgnoreCase(input)) {
            a = "4.a";
        } else if ("pilih b".equalsIgnoreCase(input)) {
            a = "4.b";
        } else if ("pilih c".equalsIgnoreCase(input)) {
            a = "4.c";
        } else if ("pilih d".equalsIgnoreCase(input)) {
            a = "4.d";
        } else if ("pilih e".equalsIgnoreCase(input)) {
            a = "4.e";
        }
        log.debug("convertRiskProfilePembagianKeuntungan() hasil Convert " + input + " = {} ", a);
        return a;
    }

    /**
     * convert to value data leads
     * @param input from user
     * @return a is integer
     */
    public int convertPertumbuhanAsetPendanaan(String input) {
        int a = 10;
        if ("Perbulan".equalsIgnoreCase(input)) {
            a = 0;
        } else if ("Lumpsum".equalsIgnoreCase(input)) {
            a = 1;
        }
        log.debug("convertPertumbuhanAsetPendanaan() hasil Convert " + input + " = {} ", a);
        return a;
    }

    /**
     * convert to value data leads
     * @param input from user
     * @return a is integer
     */
    public int convertPendidikanKuliahAnakNegaraTujuan(String input) {
        int a = 0;
        if ("indonesia".equalsIgnoreCase(input)) {
            a = 1;
        } else if ("Singapura".equalsIgnoreCase(input)) {
            a = 2;
        } else if ("Britania Raya/ Inggris".equalsIgnoreCase(input)) {
            a = 3;
        } else if ("Amerika Serikat Negeri".equalsIgnoreCase(input)) {
            a = 4;
        } else if ("Amerika Serikat Swasta".equalsIgnoreCase(input)) {
            a = 5;
        } else if ("Australia".equalsIgnoreCase(input)) {
            a = 6;
        }
        log.debug("convertPendidikanKuliahAnakNegaraTujuan() hasil Convert " + input + " = {} ", a);
        return a;
    }
    
    public int convertToSalary(String input){
        int a = 0;
        return a;
    }
    
    
}

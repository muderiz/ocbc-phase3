/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author chochong contact number 082238946301 (leo)
 *
 */
@Service
public class ValidationMethod {

    private static final Logger log = LogManager.getLogger(ServiceImp.class);

    /**
     * Digunakan validasi Name
     *
     * @param name bertipe string merupakan inputan user yang di tangkap oleh
     * bot
     * @return result true or false
     */
    public Boolean valName(String name) {
        log.debug("valName() dari user di tangkap BOT {} ", name);
        boolean result = true;
        String[] nama = name.split("");
        if (nama.length < 2) {
            result = false;
        } else {
            for (int i = 0; i < nama.length; i++) {
                if (nama[i].matches("[0-9!@#$%^&*_(){}=+-/:;'\"<>?|]*")) {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * Digunakan untuk memisahkan satu baris kalimat yang di tandai dengan
     *
     * @param data nilai yang akan di oleh
     * @return hasil merupakan array string
     */
    public String[] split_webView(String data) {
        String[] hasil = data.split("&");
        return hasil;
    }

    public String[] split_Judul_lifegoal(String data) {
        String[] hasil = data.split(" ");
        return hasil;
    }

    /**
     * Digunakan untuk mereplace sparator agar sesuai dengan format yang
     * diinginkan
     *
     * @param data yang akan di olah
     * @return String hasilReplace
     */
    public String replaceSparatorRp(String data) {
        Long inputan = Long.parseLong(data);
        DecimalFormat df = new DecimalFormat("###,###");
        System.out.println("Formatted decimal: " + df.format(inputan));
        String hasilReplace = df.format(inputan);
        return hasilReplace;
    }

    /**
     * Digunakan untuk mereplace
     *
     * @param inputUser variabel yang akan di olah
     * @return String a hasil dari replace
     */
    public String replaceSparator(String inputUser) {
        String a = "";
        a = inputUser.toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .replace("rp", "")
                .replace("rbu", "").replace("rb", "")
                .trim();
        return a;
    }

    /**
     * untuk memvalidasi syarat dan ketentuan
     *
     * @param responseUser variabel yang akan di olah
     * @return boolean true or false
     */
    public Boolean valSyaratKetentuan(String responseUser) {
        boolean result = true;

        log.debug("valSyaratKetentuan() variabel lokal status: {}", responseUser);

        if ("Setuju".equalsIgnoreCase(responseUser)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * untuk melakukan validasi terhadap status pernikahan
     *
     * @param responseUser variabel yang akan di olah
     * @return result merupakan maping string yang akan digunakan untuk
     * melakukan set terhadap entitas
     */
    public Map<String, String> valStatusPerkawinan(String responseUser) {
        log.debug("valStatusPerkawinan() dari user di tangkap BOT {} ", responseUser);
        Map<String, String> result = new HashMap<>();
        //Free typing akan disinonimkan menjadi 'ya' atau 'tidak'
        switch (responseUser.toLowerCase().trim()) {
            case "0":
            case "tidak":
            case "belum":
                result.put("status_perkawinan", "0");
                result.put("validasi_status_perkawinan", "validasiStatusPerkawinan");
                break;
            case "1":
            case "ya":
            case "sudah":
                result.put("status_perkawinan", "1");
                result.put("validasi_status_perkawinan", "validasiStatusPerkawinan");
                break;
            default:
                result.put("status_perkawinan", "");
                result.put("validasi_status_perkawinan", "");
                break;
        }
        return result;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap risk profile
     *
     * @param responseUser variabel yang akan di olah
     * @return result merupakan mapping string yang digunakan untuk melakukan
     * set terhadap entitas
     */
    public Map<String, String> valRiskProfile(String responseUser) {
        log.debug("valRiskProfile() dari user di tangkap BOT {} ", responseUser);
        Map<String, String> result = new HashMap<>();
        //Free typing akan disinonimkan menjadi 'ya' atau 'tidak'
        switch (responseUser.toLowerCase().trim()) {
            case "conservative":
            case "balance":
            case "growth":
            case "aggressive":
            case "pilih 1":
            case "pilih 2":
            case "pilih 3":
            case "pilih 4":
            case "hitung profil risiko kembali":
            case "hitung profil risiko batal":
                result.put("profile_resiko", responseUser);
                result.put("validasi_profile_resiko", responseUser);
                break;
            default:
                result.put("profile_resiko", "");
                result.put("validasi_profile_resiko", "");
                break;
        }
        return result;
    }

    /**
     * Digunakan untuk melakukan validaasi terhadap age
     *
     * @param age variabel yang akan di olah
     * @return result string
     */
    public String ValAge(String age) {
        log.debug("ValAge() dari user di tangkap BOT {} ", age);
        String result = "";
        String replace = age;
        boolean status = valAngka(replace);
        log.debug("ValAge() dari user di tangkap BOT {} ", replace);
        if (status == false) {
            result = "Mengandung Angka";
        } else {
            Double umur = Double.parseDouble(replace);
            if (umur < 17) {
                result = "Terlalu Muda";
            }
        }
        return result;
    }

    /**
     * Digunakan untuk mengganti format mata uang dari Rp ke USD
     *
     * @param userInput variabel yang akan di olah
     * @return Currency String adalah hasil akhir yang akan di simpan kedalam
     * entitas
     */
    public String convertFormatRptoUSD(String userInput) {
        String currency = userInput;
        int indexDot = 0, indexComma = 0;

        // 12.000.000,00 vs 12,000,000.00
        indexDot = userInput.indexOf(".");
        indexComma = userInput.indexOf(",");

        //format RP => USD
        String[] arrStr;
        if (indexDot > 0 && indexComma > 0 && indexDot < indexComma) {
            //12.000.000,00 ==> 12,000,000.00 
            arrStr = userInput.split(",");
            currency = arrStr[0].replace(".", ",") + "." + arrStr[1];
        } else if (indexDot < 0 && indexComma > 0) {
            //12000000,00 VS 12,000,000
            arrStr = userInput.split(",");
            if (arrStr.length == 2 && arrStr[1].length() == 3) {
                currency = userInput.replace("\\.", ",");

            } else {
                currency = userInput.replace(",", ".");

            }
        } else if (indexDot > 0 && indexComma < 0) {
            //12000000.00 VS 12.000.000
            arrStr = userInput.split("\\.");
            if (arrStr.length == 2 && arrStr[1].length() == 2) {
                currency = userInput.replace(".", ",");
            } else {
                currency = userInput.replace(".", ",");
            }
        }

        return currency;
    }

    public String replaceInputDana(String dana) {
        String danaa = "";
        danaa = dana.toLowerCase()
                .replace(" ", "")
                .replace("-", "")
                .replace(".", "")
                .replace(",", "")
                .replace("rp.", "")
                .replace("rp", "")
                .replace(".00", "")
                .replace(",00", "")
                .replace("jt", "000000")
                .replace("juta", "000000")
                .replace("m", "000000000")
                .replace("miliar", "000000000")
                .replace("t", "000000000000")
                .replace("triliun", "000000000000")
                .replace("ribu", "000").replace("rbu", "000").replace("rb", "000")
                .replace("rupiah", "")
                .trim();
        return danaa;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap besar dana investasi yang
     * dimasukkan oleh user
     *
     * @param responseUser variabel yang akan di olah
     * @return result merupakan mapping yang akan di gunakan untuk melakukan set
     * terhadap entitas
     */
    public Map<String, String> valBesarDanaInvestasi(String responseUser) {
        log.debug("valBesarDanaInvestasi() dari user di tangkap BOT {} ", responseUser);

        Map<String, String> result = new HashMap<>();

        double _money;
        String tempResponseUser = replaceInputDana(responseUser);
        String currency = convertFormatRptoUSD(tempResponseUser);

        if (valMoneyRp(currency)) {
            _money = Double.valueOf(currency.replace(",", ""));
            result.put("besar_dana_invesatasi", String.format("%.2f", _money));
            result.put("validasi_besar_dana_invesatasi", "validasiBesarDanaInvestasi");

        } else {
            result.put("besar_dana_invesatasi", "");
            result.put("validasi_besar_dana_invesatasi", "");

        }
        return result;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap pendapatan yang telah di
     * inputkan oleh user
     *
     * @param responseUser variabel yang akan di olah
     * @return mengembalikan nilai mapping yang akan digunakan untuk melakukan
     * set terhadap entitas
     */
    public Map<String, String> valPendapatan(String responseUser) {
        log.debug("valPendapatan() dari user di tangkap BOT {} ", responseUser);
        double _double;
        Map<String, String> result = new HashMap<>();
        String newResponseUser = responseUser.toLowerCase()
                .replace(",", ".")
                .replace("-", "")
                .replace("juta", "")
                .replace("ribu", "")
                .replace("rb", "").replace("rbu", "")
                .replace("jt", "")
                .replace("pilih", "opsi")
                .trim();

        //Free typing akan di convert jadi angka (double)
        if (valDouble(newResponseUser) && !responseUser.equals("1") && !responseUser.equals("2") && !responseUser.equals("3")) {
            _double = Double.parseDouble(newResponseUser);

            if (_double < 0) {//ex: -12.5 (negative)
                newResponseUser = "invalid";
            } else {//ex: 12.5 / 123 / 12.55 jt // 900 rb
                String[] arr = newResponseUser.split("\\."); //dot is a special character
                if (newResponseUser.contains("jt") || arr[0].length() <= 3) {
                    //dalam bentuk juta
                    if (_double < 15) {
                        newResponseUser = "1";
                    } else if (_double >= 15 && _double <= 35) {
                        newResponseUser = "2";
                    } else if (_double >= 35) {
                        newResponseUser = "3";
                    }
                } else if (newResponseUser.contains("rb")) {
                    //dalam bentuk ribuan
                    if (_double < 15000) {
                        newResponseUser = "1";
                    } else if (_double >= 15000 && _double <= 35000) {
                        newResponseUser = "2";
                    } else if (_double >= 35000) {
                        newResponseUser = "3";
                    }
                } else {
                    if (_double < 15000000) {
                        newResponseUser = "1";
                    } else if (_double >= 15000000 && _double <= 35000000) {
                        newResponseUser = "2";
                    } else if (_double >= 35000000) {
                        newResponseUser = "3";
                    }
                }

            }
        }

        switch (newResponseUser) {
            case "1":
            case "opsi pertama":
            case "opsi satu":
            case "opsi a":
            case "opsi 1":
                result.put("pendapatan", "1");
                result.put("validasi_pendapatan", "validasiPendapatan");
                break;
            case "2":
            case "opsi kedua":
            case "opsi dua":
            case "opsi b":
            case "opsi 2":
                result.put("pendapatan", "2");
                result.put("validasi_pendapatan", "validasiPendapatan");
                break;
            case "3":
            case "opsi ketiga":
            case "opsi tiga":
            case "opsi c":
            case "opsi 3":
            case "opsi terakhir":
                result.put("pendapatan", "3");
                result.put("validasi_pendapatan", "validasiPendapatan");
                break;
            default:// invalid
                result.put("pendapatan", "");
                result.put("validasi_pendapatan", "");
                break;
        }
        return result;
    }

    /**
     * DIgunakan untuk melakukan validasi terhadap tabungan yang sudah di
     * inputkan user
     *
     * @param responseUser variabel yang akan di olah
     * @return result merupakan mapping yang akan di gunakan untuk melakukan set
     * terhadap entitas
     */
    public Map<String, String> valTabungan(String responseUser) {
        log.debug("valTabungan() dari user di tangkap BOT {} ", responseUser);
        int _angka;
        Map<String, String> result = new HashMap<>();
        String newResponseUser = responseUser.toLowerCase()
                .replace("%", "")
                .replace("-", "")
                .replace("pilih", "opsi")
                .trim();

        //Free typing akan di convert jadi angka (int)
        if (valAngka(newResponseUser) && !responseUser.equals("1") && !responseUser.equals("2") && !responseUser.equals("3")) {
            _angka = Integer.parseInt(newResponseUser);
            if (_angka < 10) {
                newResponseUser = "1";
            } else if (_angka >= 10 && _angka <= 30) {
                newResponseUser = "2";
            } else {
                newResponseUser = "3";
            }
        }
        switch (newResponseUser) {
            case "1":
            case "opsi pertama":
            case "opsi satu":
            case "opsi a":
            case "opsi 1":
                result.put("tabungan", "1");
                result.put("validasi_tabungan", "validasiTabungan");
                break;
            case "2":
            case "opsi kedua":
            case "opsi dua":
            case "opsi b":
            case "opsi 2":
                result.put("tabungan", "2");
                result.put("validasi_tabungan", "validasiTabungan");
                break;
            case "3":
            case "opsi ketiga":
            case "opsi tiga":
            case "opsi c":
            case "opsi 3":
            case "opsi terakhir":
                result.put("tabungan", "3");
                result.put("validasi_tabungan", "validasiTabungan");
                break;
            default:// invalid
                result.put("tabungan", "");
                result.put("validasi_tabungan", "");
                break;
        }
        return result;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap pendanaan yang di sudah di
     * input oleh user
     *
     * @param responseUser variabel yang akan di olah
     * @return result merupakan mapping yang akan digunakan untuk set terhadap
     * entitas
     */
    public Map<String, String> valPendanaan(String responseUser) {
        log.debug("valPendanaan() dari user di tangkap BOT {} ", responseUser);
        Map<String, String> result = new HashMap<>();

        //free typing akan disinonimkan menjadi monthly atau lumpsum
        switch (responseUser.toLowerCase().trim()) {
            case "0":
            case "perbulan":
                result.put("pendanaan", "0");
                result.put("validasi_pendanaan", "validasi_pendanaan");

                break;
            case "1":
            case "sekali di pertama":
                result.put("pendanaan", "1");
                result.put("validasi_pendanaan", "validasi_pendanaan");

                break;
            default:
                result.put("pendanaan", "");
                result.put("validasi_pendanaan", "");

                break;
        }
        return result;

    }

    /**
     * Digunakan untuk melakukan validasi terhadap negara kuliah
     *
     * @param responseUser variabel yang akan di olah
     * @return result merupakan mapping yang digunakan untuk melakukan set
     * terhadap entitas
     */
    public Map<String, String> valNegaraKuliah(String responseUser) {
        log.debug("valNegaraKuliah() dari user di tangkap BOT {} ", responseUser);
        Map<String, String> result = new HashMap<>();

        switch (responseUser.toLowerCase().replace("opsi", "").trim()) {
            case "1":
            case "indonesia":
            case "pertama":
                result.put("negara_kuliah", "1");
                result.put("validasi_negara_kuliah", "Indonesia");
                break;
            case "2":
            case "singapura":
            case "kedua":
                result.put("negara_kuliah", "2");
                result.put("validasi_negara_kuliah", "Singapura");
                break;
            case "3":
            case "inggris":
            case "ketiga":
                result.put("negara_kuliah", "3");
                result.put("validasi_negara_kuliah", "Britania Raya/ Inggris");
                break;
            case "4":
            case "usa negeri":
            case "keempat":
                result.put("negara_kuliah", "4");
                result.put("validasi_negara_kuliah", "Amerika Serikat (Universitas Negeri)");
                break;
            case "5":
            case "usa swasta":
            case "kelima":
                result.put("negara_kuliah", "5");
                result.put("validasi_negara_kuliah", "Amerika Serikat (Universitas Swasta)");
                break;
            case "6":
            case "australia":
            case "keenam":
            case "terakhir":
                result.put("negara_kuliah", "6");
                result.put("validasi_negara_kuliah", "Australia");
                break;
            default:
                result.put("negara_kuliah", "");
                result.put("validasi_negara_kuliah", "");

                break;
        }
        return result;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap pengetahuan yang diinputkan
     * oleh user
     *
     * @param responseUser variabel yang akan di olah
     * @return result merupakan mapping yang akan digunakan untuk melakukan set
     * terhadap entitas
     */
    public Map<String, String> valPengetahuan(String responseUser) {
        log.debug("valPengetahuan() dari user di tangkap BOT {} ", responseUser);

        Map<String, String> parameters = new HashMap<>();
        switch (responseUser.toLowerCase()
                .trim()) {
            case "3.a":
            case "sangat terbatas":
            case "sangat minimum":
            case "sangat minim":
            case "sangat kurang":
            case "terbatas banget":
            case "minimum banget":
            case "minim banget":
            case "kurang banget":
                parameters.put("pengetahuan", "3.a");
                parameters.put("validasi_pengetahuan", "validasiPengatahuan");
                break;
            case "3.b":
            case "terbatas":
            case "minimum":
            case "minim":
            case "kurang":
                parameters.put("pengetahuan", "3.b");
                parameters.put("validasi_pengetahuan", "validasiPengatahuan");
                break;
            case "3.c":
            case "cukup":
                parameters.put("pengetahuan", "3.c");
                parameters.put("validasi_pengetahuan", "validasiPengatahuan");
                break;
            case "3.d":
            case "mengerti":
            case "ngerti":
            case "paham":
                parameters.put("pengetahuan", "3.d");
                parameters.put("validasi_pengetahuan", "validasiPengatahuan");
                break;
            case "3.e":
            case "sangat mengerti":
            case "sangat ngerti":
            case "sangat paham":
            case "mengerti banget":
            case "ngerti banget":
            case "paham banget":
                parameters.put("pengetahuan", "3.e");
                parameters.put("validasi_pengetahuan", "validasiPengatahuan");
                break;
            default:
                parameters.put("pengetahuan", "");
                parameters.put("validasi_pengetahuan", "");
                break;

        }
        return parameters;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap sumber dana yang di masukkan
     * oleh user
     *
     * @param responseUser variabel yang akan di olah
     * @return result merupakan mapping yang akan di gunakan untuk melakukan set
     * terhadap entitas
     */
    public Map<String, String> valSumberDana(String responseUser) {
        log.debug("valSumberDana() dari user di tangkap BOT {} ", responseUser);

        Map<String, String> parameters = new HashMap<>();
        switch (responseUser.toLowerCase()) {
            case "2.a":
            case "gaji":
                parameters.put("sumber_dana", "2.a");
                parameters.put("validasi_sumber_dana", "validasi_sumber_dana");
                break;
            case "2.b":
            case "usaha":
            case "bisnis":
            case "usaha pribadi":
            case "usaha sendiri":
            case "bisnis pribadi":
            case "bisnis sendiri":
                parameters.put("sumber_dana", "2.b");
                parameters.put("validasi_sumber_dana", "validasi_sumber_dana");
                break;
            case "2.c":
            case "warisan":
            case "dari orang tua":
            case "orang tua":
            case "harta":
            case "peninggalan":
                parameters.put("sumber_dana", "2.c");
                parameters.put("validasi_sumber_dana", "validasi_sumber_dana");
                break;
            case "2.d":
            case "tidak ada sumber tetap":
            case "tidak tetap":
            case "engga tetap":
            case "ga tetap":
                parameters.put("sumber_dana", "2.d");
                parameters.put("validasi_sumber_dana", "validasi_sumber_dana");
                break;
            default:
                parameters.put("sumber_dana", "");
                parameters.put("validasi_sumber_dana", "");
                break;

        }
        return parameters;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap status investasi yang
     * diinputkan oleh user
     *
     * @param responseUser variabel yang akan di olah
     * @return result merupakan mapping yang akan di gunakan untuk melakukan set
     * terhadap entitas
     */
    public Map<String, String> valStatusInvestasi(String responseUser) {
        log.debug("valStatusInvestasi() dari user di tangkap BOT {} ", responseUser);
        Map<String, String> result = new HashMap<>();
        //Free typing akan disinonimkan menjadi 'ya' atau 'tidak'
        switch (responseUser.toLowerCase().trim()) {
            case "0":
            case "tidak":
            case "belum":
            case "belum pernah nih":
                result.put("status_investasi", "0");
                result.put("validasi_status_investasi", "validasiStatusInvestasi");
                result.put("profile_resiko", "0");
                result.put("validasi_profile_resiko", "validasiRiskProfile");
                break;
            case "1":
            case "ya":
            case "sudah":
                result.put("status_investasi", "1");
                result.put("validasi_status_investasi", "validasiStatusInvestasi");
                break;
            default:
                result.put("status_investasi", "");
                result.put("validasi_status_investasi", "");
                result.put("validasi_profile_resiko", "");
                break;
        }
        return result;
    }

    public Map<String, String> valStatusReksadana(String responseUser) {
        log.debug("valStatusReksadana() dari user di tangkap BOT {} ", responseUser);
        Map<String, String> result = new HashMap<>();
        //Free typing akan disinonimkan menjadi 'ya' atau 'tidak'
        switch (responseUser.toLowerCase().trim()) {
            case "belum":
            case "sudah":
                result.put("pertanyaan_reksadana", responseUser);
                break;
            default:
                result.put("pertanyaan_reksadana", "");
                result.put("belumtau_reksadana", "");
                break;
        }
        return result;
    }

    /**
     * Digunakan untuk melakukan validasi angka dari hasil inputan user
     *
     * @param a variabel yang akan di olah
     * @return result merupakan boolean true or false
     */
    public Boolean valAngka(String a) {
        log.debug("valAngka() dari user di tangkap BOT {} ", a);
        boolean result = true;
        if (a.matches("[0-9]*")) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Digunakan untuk validasi nomor telp yang sudah di inputkan oleh user
     *
     * @param a variabel yang akan di olah
     * @return result berupakan boolean berupa true or false
     */
    public Boolean valNoTlp(String a) {
        log.debug("valAngka() dari user di tangkap BOT {} ", a);
        boolean result = true;
        result = a.matches("^(^\\+62\\s?|^0)(\\d{3,4}-?){2}\\d{3,4}$");
        return result;
    }

    /**
     * Digunakan untuk merika angka yang di masukkan apakah bertipe data double
     * atau bukan
     *
     * @param a variabel yang akan di olah
     * @return true or false boolean
     */
    public boolean valDouble(String a) {
        log.debug("valDouble() dari user di tangkap BOT {} ", a);
        if (a.matches("^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Digunakan untuk melakukan validasi terhadap jumlah uang yang di inputkan
     * oleh user
     *
     * @param a variabel yang akan di olah
     * @return true or false boolean
     */
    public boolean valMoneyRp(String a) {
        log.debug("valMoneyRp() dari user di tangkap BOT {} ", a);
        if (a.matches("^((rp|rp\\.)(\\s)?)?([1-9]{1}[0-9]{0,2}(\\,[0-9]{3})*(\\.[0-9]{0,2})?|[1-9]{1}[0-9]{0,}(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|(\\.[0-9]{1,2})?)$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Digunakan untuk memeriksa email
     *
     * @param email variabel yang akan di olah
     * @return result digunakan untuk melakukan set entitas
     */
    public Map<String, String> valEmail(String email) {
        log.debug("valBesarDanaInvestasi() dari user di tangkap BOT {} ", email);
        String regx = "^[A-Z0-9._-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        Map<String, String> result = new HashMap<>();
        if (matcher.find()) {
            result.put("email", email);
            result.put("validasi_email", "validasi_email");
        } else {
            result.put("email", "");
            result.put("validasi_email", "");

        }
        return result;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap lama investasi yang
     * diinputkan oleh user
     *
     * @param responseUser variabel yang akan di olah
     * @return clearEntities digunakan untuk melakukan set entitas
     */
    public Map<String, String> valLamaInvestasi(String responseUser) {
        log.debug("valLamaInvestasi() dari user di tangkap BOT {} ", responseUser);
        Map<String, String> clearEntities = new HashMap<>();
        responseUser = responseUser.toLowerCase()
                .replace(",", ".")
                .replace("tahun", "")
                .replace("thn", ".")
                .trim();
        if (valAngka(responseUser)) {
            if (Integer.parseInt(responseUser) <= 600) {
                clearEntities.put("lama_berinvestasi", responseUser);
                clearEntities.put("validasi_lama_berinvestasi", "validasiLamaBerinvestasi");
            } else {
                clearEntities.put("lama_berinvestasi", "600");
                clearEntities.put("validasi_lama_berinvestasi", "validasiLamaBerinvestasi");
            }
        } else {
            clearEntities.put("lama_berinvestasi", "");
            clearEntities.put("validasi_lama_berinvestasi", "");
        }

        return clearEntities;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap lama investasi dari
     * pertumbuhan aset
     *
     * @param responseUser variabel yang akan di olah
     * @return clearEntities digunakan untuk melakukan set terhadap entitas
     */
    public Map<String, String> valLamaInvestasi_pertumbuhanAset(String responseUser) {
        log.debug("valLamaInvestasi() dari user di tangkap BOT {} ", responseUser);
        Map<String, String> clearEntities = new HashMap<>();
        String replace = responseUser.toLowerCase()
                .replace(",", ".")
                .replace("tahun", "")
                .replace("thn", ".")
                .trim();
        if (valAngka(replace)) {
            boolean status = valDouble(replace);
            log.debug("ValAge() dari user di tangkap BOT {} ", replace);
            if (status == false) {
                clearEntities.put("lama_investasi", "");
                clearEntities.put("validasi_lama_investasi", "");
            } else {
                Double umur = Double.parseDouble(replace);
                if (umur <= 600) {
                    clearEntities.put("lama_investasi", responseUser);
                    clearEntities.put("validasi_lama_investasi", "validasiLamaInvestasi");
                } else {
                    clearEntities.put("lama_investasi", "600");
                    clearEntities.put("validasi_lama_investasi", "validasiLamaInvestasi");
                }
            }
        } else {
            clearEntities.put("lama_investasi", "");
            clearEntities.put("validasi_lama_investasi", "");
        }

        return clearEntities;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap usia anak yang diinputkan
     * oleh user
     *
     * @param responUser variabel yang akan di olah
     * @return result digunakan untuk melakukan set terhadap entitas
     */
    public Map<String, String> valUsiaAnak(String responUser) {
        log.debug("valUsiaAnak() dari user di tangkap BOT {} ", responUser);
        Map<String, String> result = new HashMap<>();
        if (valAngka(responUser)) {
            result.put("usia_anak", responUser);
            result.put("validasi_usia_anak", "validasi_usia_anak");
        } else {
            result.put("usia_anak", "");
            result.put("validasi_usia_anak", "");
        }

        return result;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap dana sekarang yang di miliki
     * oleh user
     *
     * @param responseUser variabel yang akan di olah
     * @return result digunakan untuk melakukan set terhadap entitas
     */
    public Map<String, String> validasiDanaSekarang(String responseUser) {
        log.debug("validasiDanaSekarang() dari user di tangkap BOT {} ", responseUser);
        Map<String, String> result = new HashMap<>();
        double _money;
        String tempResponseUser = replaceInputDana(responseUser);
        String currency = convertFormatRptoUSD(tempResponseUser);

        if (valMoneyRp(currency)) {
            _money = Double.valueOf(currency.replace(",", ""));
            result.put("dana_sekarang", String.format("%.2f", _money));
            result.put("validasi_dana_sekarang", "validasi_dana_sekarang");

        } else {
            result.put("dana_sekarang", "");
            result.put("validasi_dana_sekarang", "");
        }

        return result;
    }

    /**
     * Digunakan untuk melakukan validasi jugul life goal yang diinputkan oleh
     * user
     *
     * @param responseUser variabel yang akan di olah
     * @return result digunakan untuk melakukan set terhadap entitas
     */
    public Map<String, String> validasiJudulLifeGoal(String responseUser) {
        log.debug("validasiJudulLifeGoal() dari user di tangkap BOT {} ", responseUser);

        Map<String, String> result = new HashMap<>();

        if (responseUser.length() <= 40) {
            result.put("judul_life_goal", responseUser);
            result.put("validasi_judullifegoal", "validasiJudulLifeGoal");
        } else {
            result.put("judul_life_goal", "");
            result.put("validasi_judullifegoal", "");
        }
        return result;
    }

    public Map<String, String> validasiJudulLifeGoalLiburan(String responseUser) {
        log.debug("validasiJudulLifeGoal() dari user di tangkap BOT {} ", responseUser);

        Map<String, String> result = new HashMap<>();

        if (responseUser.length() <= 40) {
            result.put("judul_life_goal", "Liburan ke " + responseUser);
            result.put("validasi_judullifegoal", "validasiJudulLifeGoal");
        } else {
            result.put("judul_life_goal", "");
            result.put("validasi_judullifegoal", "");
        }
        return result;
    }

    public Map<String, String> validasiJudulLifeGoalBeliBarang(String responseUser) {
        log.debug("validasiJudulLifeGoal() dari user di tangkap BOT {} ", responseUser);

        Map<String, String> result = new HashMap<>();

        if (responseUser.length() <= 40) {
            result.put("judul_life_goal", "Beli " + responseUser);
            result.put("validasi_judullifegoal", "validasiJudulLifeGoal");
        } else {
            result.put("judul_life_goal", "");
            result.put("validasi_judullifegoal", "");
        }
        return result;
    }

    public Map<String, String> validasiJudulLifeGoalNontonKonser(String responseUser) {
        log.debug("validasiJudulLifeGoal() dari user di tangkap BOT {} ", responseUser);

        Map<String, String> result = new HashMap<>();

        if (responseUser.length() <= 40) {
            result.put("judul_life_goal", "Nonton Konser " + responseUser);
            result.put("validasi_judullifegoal", "validasiJudulLifeGoal");
        } else {
            result.put("judul_life_goal", "");
            result.put("validasi_judullifegoal", "");
        }
        return result;
    }

    /**
     * Digunakan untuk validasi kebutuhan dana yang diinputkan oleh user
     *
     * @param responseUser variabel yang akan di olah
     * @return result digunakan untuk melakukan set terhadap entitas
     */
    public Map<String, String> validasiKebutuhanDana(String responseUser) {
        log.debug("validasiKebutuhanDana() dari user di tangkap BOT {} ", responseUser);

        Map<String, String> result = new HashMap<>();
        double _money;
        String tempResponseUser = replaceInputDana(responseUser);
        String currency = convertFormatRptoUSD(tempResponseUser);

        if (valMoneyRp(currency)) {
            _money = Double.valueOf(currency.replace(",", ""));
            result.put("kebutuhan_dana", String.format("%.2f", _money));
            result.put("validasi_kebutuhan_dana", "validasi_kebutuhan_dana");

        } else {
            result.put("kebutuhan_dana", "");
            result.put("validasi_kebutuhan_dana", "");
        }

        return result;
    }

    /**
     * DIgunakan untuk melakukan validasi terhadap pembagian hasil yang sudah di
     * inputkan oleh iser
     *
     * @param responseUser variabel yang akan di olah
     * @return parameters digunakan untuk melakukan set terhadap entitas
     */
    public Map<String, String> validasiPembagianHasil(String responseUser) {
        log.debug("validasiPembagianHasil() dari user di tangkap BOT {} ", responseUser);

        Map<String, String> parameters = new HashMap<>();

        switch (responseUser.toLowerCase()
                .replace("pilih", "")
                .replace("opsi", "")
                .trim()) {
            case "a":
            case "1":
                parameters.put("pembagian_hasil", "4.a");
                parameters.put("validasi_pembagian_hasil", "validasiPembagianHasil");

                break;
            case "b":
            case "2":
                parameters.put("pembagian_hasil", "4.b");
                parameters.put("validasi_pembagian_hasil", "validasiPembagianHasil");

                break;
            case "c":
            case "3":
                parameters.put("pembagian_hasil", "4.c");
                parameters.put("validasi_pembagian_hasil", "validasiPembagianHasil");

                break;
            case "d":
            case "4":
                parameters.put("pembagian_hasil", "4.d");
                parameters.put("validasi_pembagian_hasil", "validasiPembagianHasil");

                break;
            case "e":
            case "5":
                parameters.put("pembagian_hasil", "4.e");
                parameters.put("validasi_pembagian_hasil", "validasiPembagianHasil");

                break;
            default:
                parameters.put("pembagian_hasil", "");
                parameters.put("validasi_pembagian_hasil", "");

                break;
        }

        return parameters;
    }

    /**
     * Digunakan untuk melakukan validasi terhadap skenario keuntungan yang
     * diinputkan oleh user
     *
     * @param responseUser variabel yang akan di olah
     * @return parameter digunakan untuk melakukan set terhadap entitas
     */
    public Map<String, String> validasiSkenarioKeuntungan(String responseUser) {
        log.debug("validasiSkenarioKeuntungan() dari user di tangkap BOT {} ", responseUser);

        Map<String, String> parameters = new HashMap<>();
        switch (responseUser.toLowerCase()
                .replace("pilih", "")
                .replace("opsi", "")
                .trim()) {
            case "a":
            case "1":
                parameters.put("skenario_keuntungan", "5.a");
                parameters.put("validasi_skenario_keuntungan", "validasi_skenario_keuntungan");
                break;
            case "b":
            case "2":
                parameters.put("skenario_keuntungan", "5.b");
                parameters.put("validasi_skenario_keuntungan", "validasi_skenario_keuntungan");
                break;
            case "c":
            case "3":
                parameters.put("skenario_keuntungan", "5.c");
                parameters.put("validasi_skenario_keuntungan", "validasi_skenario_keuntungan");
                break;
            case "d":
            case "4":
                parameters.put("skenario_keuntungan", "5.d");
                parameters.put("validasi_skenario_keuntungan", "validasi_skenario_keuntungan");
                break;
            case "e":
            case "5":
                parameters.put("skenario_keuntungan", "5.e");
                parameters.put("validasi_skenario_keuntungan", "validasi_skenario_keuntungan");
                break;
            default:
                parameters.put("skenario_keuntungan", "");
                parameters.put("validasi_skenario_keuntungan", "");
                break;
        }
        return parameters;
    }

    /**
     * digunakan untuk melakukan validasi terhadap life goal yang sudah
     * diinputkan oleh user
     *
     * @param lifeGoal variabel yang akan di olah
     * @return entities digunakan untuk melakukan set terhadap entitas
     */
    public Map<String, String> valLifeGoal(String lifeGoal) {

        Map<String, String> entities = new HashMap<>();
        boolean boolClear = true;

        if (lifeGoal.equalsIgnoreCase("pertumbuhan aset")) {
            boolClear = false;
            entities.put("lifegoal", "pertumbuhan aset");
        }

        if (lifeGoal.equalsIgnoreCase("pendidikan kuliah anak")) {
            boolClear = false;
            entities.put("lifegoal", "pendidikan kuliah anak");
        }

        if (lifeGoal.equalsIgnoreCase("life goal lainnya")) {
            boolClear = false;
            entities.put("lifegoal", "life goal lainnya");
        }

        if (lifeGoal.equalsIgnoreCase("saya tidak tahu")) {
            boolClear = false;
            entities.put("lifegoal", "saya tidak tahu");
        }

        if (boolClear) {
            entities.put("lifegoal", "");
        } else {
            entities.put("validasi_lifegoal", "validasi_lifegoal");
        }

        return entities;
    }

}

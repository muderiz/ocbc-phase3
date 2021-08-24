/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.service;

import com.imi.dolphin.sdkwebservice.property.AppProperties;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author chochong
 */
@Service
public class MatrixParameter {

    @Autowired
    AppProperties appProperties;

    public String yearlyReturnCodeFV(int riskId, String futureValuePhase) {
        String yearlyReturnCode = "";
        if (riskId == 0) {
            switch (futureValuePhase) {
                case "DEFAULT":
                    yearlyReturnCode = "A";
                    break;
                case "SV":
                    yearlyReturnCode = "C";
                    break;
                case "TK":
                    yearlyReturnCode = "-";
                    break;
                case "MF":
                    yearlyReturnCode = "-";
                    break;
            }
        } else {
            switch (futureValuePhase) {
                case "DEFAULT":
                    yearlyReturnCode = "B";
                    break;
                case "SV":
                    yearlyReturnCode = "C";
                    break;
                case "TK":
                    yearlyReturnCode = "D";
                    break;
                case "MF":
                    yearlyReturnCode = "E";
                    break;
            }
        }
        return yearlyReturnCode;
    }

    public String yearlyReturnCodeTV(int riskId, String futureValuePhase) {
        String yearlyReturnCode = "";
        if (riskId == 0) {
            switch (futureValuePhase) {
                case "CE":
                    yearlyReturnCode = "A";
                    break;
                case "OG":
                    yearlyReturnCode = "C";
                    break;
                case "SV":
                    yearlyReturnCode = "E";
                    break;
                case "TK":
                    yearlyReturnCode = "-";
                    break;
                case "MF":
                    yearlyReturnCode = "-";
                    break;
            }
        } else {
            switch (futureValuePhase) {
                case "CE":
                    yearlyReturnCode = "B";
                    break;
                case "OG":
                    yearlyReturnCode = "D";
                    break;
                case "SV":
                    yearlyReturnCode = "E";
                    break;
                case "TK":
                    yearlyReturnCode = "F";
                    break;
                case "MF":
                    yearlyReturnCode = "G";
                    break;
            }
        }
        return yearlyReturnCode;
    }

    public String futureValueCodeTV(String futureValuePhase) {
        String yearlyReturnCode = "";
        switch (futureValuePhase) {
            case "CE":
                yearlyReturnCode = "A";
                break;
            case "OG":
                yearlyReturnCode = "B";
                break;
            case "SV":
                yearlyReturnCode = "C";
                break;
            case "TK":
                yearlyReturnCode = "D";
                break;
            case "MF":
                yearlyReturnCode = "E";
                break;
        }
        return yearlyReturnCode;
    }

    public String futureValueTypeFV(int riskId, String futureValuePhase) {
        String yearlyReturnCode = "";
        if (riskId == 0) {
            switch (futureValuePhase) {
                case "DEFAULT":
                    yearlyReturnCode = "AG";
                    break;
                case "SV":
                    yearlyReturnCode = "SV";
                    break;
                case "TK":
                    yearlyReturnCode = "-";
                    break;
                case "MF":
                    yearlyReturnCode = "-";
                    break;
            }
        } else {
            switch (futureValuePhase) {
                case "DEFAULT":
                    yearlyReturnCode = "AG";
                    break;
                case "SV":
                    yearlyReturnCode = "SV";
                    break;
                case "TK":
                    yearlyReturnCode = "TK";
                    break;
                case "MF":
                    yearlyReturnCode = "MF";
                    break;
            }
        }
        return yearlyReturnCode;
    }

    public String paymentTypeTV(String futureValuePhase) {
        String yearlyReturnCode = "";
        switch (futureValuePhase) {
            case "CE":
                yearlyReturnCode = "CE";
                break;
            case "OG":
                yearlyReturnCode = "OG";
                break;
            case "SV":
                yearlyReturnCode = "SV";
                break;
            case "TK":
                yearlyReturnCode = "TK";
                break;
            case "MF":
                yearlyReturnCode = "MF";
                break;
        }
        return yearlyReturnCode;
    }

    public Map<String, String> listProduct(int lifeGoalId, String investmentType, int Tenor, long investmentAmount) {
//        String a = null;

        Map<String, String> param = new LinkedHashMap<>();
        if (lifeGoalId == 1) {//Pertumbuhan Aset
            if ("0".equals(investmentType)) {//Monthly
                if (Tenor > 36) {
                    if (investmentAmount >= 8000000) {
                        param.put("List_Product_Type", "MFA");
                        param.put("MFA_List", "3");
                    } else {
                        param.put("List_Product_Type", "MFA");
                        param.put("MFA_List", "3");
                    }
                } else {
                    if (investmentAmount >= 8000000) {
                        param.put("List_Product_Type", "MFA,TK");
                        param.put("MFA_List", "2");
                    } else {
                        param.put("List_Product_Type", "MFA,TK");
                        param.put("MFA_List", "2");
                    }
                }
            } else {//Lumpsum
                if (Tenor > 36) {
                    if (investmentAmount >= 8000000) {
                        param.put("List_Product_Type", "MFA,DP");
                        param.put("MFA_List", "2");
                    } else {
                        param.put("List_Product_Type", "MFA");
                        param.put("MFA_List", "3");
                    }
                } else {
                    if (investmentAmount >= 8000000) {
                        param.put("List_Product_Type", "MFA,DP");
                        param.put("MFA_List", "2");
                    } else {
                        param.put("List_Product_Type", "MFA");
                        param.put("MFA_List", "3");
                    }
                }
            }
        } else {//Pendidikan & Life Goal Lainnya
            if (Tenor > 36) {
                if (investmentAmount >= 8000000) {
                    param.put("List_Product_Type", "MFA,DP");
                    param.put("MFA_List", "2");
                } else {
                    param.put("List_Product_Type", "MFA");
                    param.put("MFA_List", "2");
                }
            } else {
                if (investmentAmount >= 8000000) {
                    param.put("List_Product_Type", "MFA,DP,TK");
                    param.put("MFA_List", "1");
                } else {
                    param.put("List_Product_Type", "MFA,TK");
                    param.put("MFA_List", "2");
                }
            }
        }
//		return a;
        return param;
    }

    public List<String> getCalculatorPath(int life_goal_id, String product_type) {
        List<String> pathCalulator = new ArrayList<String>();
        switch (life_goal_id) {
            case 1://Pertumbuhan Aset
                switch (product_type) {
                    case "MFA":
                    case "TK":
                        pathCalulator.add(appProperties.OCBC_WPP_PATH_FUTURE_VALUE);
                        break;
                    case "DP":
                        pathCalulator.add(appProperties.OCBC_WPP_PATH_FUTURE_VALUE_DEPOSITO);
                        break;
                }
                break;
            case 2://Pendidikan Anak
                switch (product_type) {
                    case "MFA":
                        pathCalulator.add(appProperties.OCBC_WPP_PATH_PRESENT_VALUE);   //Lumpsum
                        pathCalulator.add(appProperties.OCBC_WPP_PATH_TARGET_VALUE);    //Perbulan
                        break;
                    case "TK":
                        pathCalulator.add(appProperties.OCBC_WPP_PATH_TARGET_VALUE);    //perbulan
                        break;
                    case "DP":
                        pathCalulator.add(appProperties.OCBC_WPP_PATH_PRESENT_VALUE);   //lumpsum
                        break;
                }
                break;
            case 3://Life Goal Lainnya
                switch (product_type) {
                    case "MFA":
                        pathCalulator.add(appProperties.OCBC_WPP_PATH_PRESENT_VALUE);
                        pathCalulator.add(appProperties.OCBC_WPP_PATH_TARGET_VALUE);
                        break;
                    case "TK":
                        pathCalulator.add(appProperties.OCBC_WPP_PATH_TARGET_VALUE);
                        break;
                    case "DP":
                        pathCalulator.add(appProperties.OCBC_WPP_PATH_PRESENT_VALUE);
                        break;
                }
                break;
        }
        return pathCalulator;
    }

    public String getFutureValueType(String calculator, String productType) {
        String futureValueType = "";
        switch (productType) {
            case "TK":
                futureValueType = "TK";
                break;
            case "MFA":
            case "MFB":
                futureValueType = "MF";
                break;
        }
        return futureValueType;
    }

    public String getYearlyReturnCode(String calculator, String productType) {
        String yearlyReturnCode = "";
        switch (calculator) {
            case "FV":
                switch (productType) {
                    case "TK":
                        yearlyReturnCode = "D";
                        break;
                    case "MFA":
                    case "MFB":
                        yearlyReturnCode = "E";
                        break;
                }
                break;
            case "TV":
                switch (productType) {
                    case "TK":
                        yearlyReturnCode = "F";
                        break;
                    case "MFA":
                    case "MFB":
                        yearlyReturnCode = "G";
                        break;
                }
                break;
            case "PV":
                switch (productType) {
                    case "DP":
                        yearlyReturnCode = "B";
                        break;
                    case "MFA":
                    case "MFB":
                        yearlyReturnCode = "C";
                        break;
                }
                break;
        }
        return yearlyReturnCode;
    }

    public String getFutureValueCode(String calculator, String productType) {
        String futureValueCode = "";
        switch (calculator) {
            case "TV":
                switch (productType) {
                    case "TK":
                        futureValueCode = "D";
                        break;
                    case "MFA":
                    case "MFB":
                        futureValueCode = "E";
                        break;
                }
                break;
        }
        return futureValueCode;
    }

}

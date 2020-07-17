/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.model;

/**
 *
 * @author cokkyturnip
 */
public class Product {
	public String Mutual_Fund_Type;

    public String Product_Rate;

    public int Product_ID;

    public String Average_Rate;

    public String YTD;

    public String Product_Name;

    public String Bad_Rate;

    public String Performance_of_6_month;

    public String Performance_of_1_month;

    public String Performance_of_12_month;

    public String Good_Rate;

    public double Standard_Deviation;

    public String Performance_of_60_month;

    public String Mutual_Fund_Nav;

    public String Mutual_Fund_Risk_Profile_ID;

    public String Product_Type;

    public String Mutual_Fund_Code;

    public String getMutual_Fund_Type ()
    {
        return Mutual_Fund_Type;
    }

    public void setMutual_Fund_Type (String Mutual_Fund_Type)
    {
        this.Mutual_Fund_Type = Mutual_Fund_Type;
    }

    public String getProduct_Rate ()
    {
        return Product_Rate;
    }

    public void setProduct_Rate (String Product_Rate)
    {
        this.Product_Rate = Product_Rate;
    }

    public int getProduct_ID ()
    {
        return Product_ID;
    }

    public void setProduct_ID (int Product_ID)
    {
        this.Product_ID = Product_ID;
    }

    public String getAverage_Rate ()
    {
        return Average_Rate;
    }

    public void setAverage_Rate (String Average_Rate)
    {
        this.Average_Rate = Average_Rate;
    }

    public String getYTD ()
    {
        return YTD;
    }

    public void setYTD (String YTD)
    {
        this.YTD = YTD;
    }

    public String getProduct_Name ()
    {
        return Product_Name;
    }

    public void setProduct_Name (String Product_Name)
    {
        this.Product_Name = Product_Name;
    }

    public String getBad_Rate ()
    {
        return Bad_Rate;
    }

    public void setBad_Rate (String Bad_Rate)
    {
        this.Bad_Rate = Bad_Rate;
    }

    public String getPerformance_of_6_month ()
    {
        return Performance_of_6_month;
    }

    public void setPerformance_of_6_month (String Performance_of_6_month)
    {
        this.Performance_of_6_month = Performance_of_6_month;
    }

    public String getPerformance_of_1_month ()
    {
        return Performance_of_1_month;
    }

    public void setPerformance_of_1_month (String Performance_of_1_month)
    {
        this.Performance_of_1_month = Performance_of_1_month;
    }

    public String getPerformance_of_12_month ()
    {
        return Performance_of_12_month;
    }

    public void setPerformance_of_12_month (String Performance_of_12_month)
    {
        this.Performance_of_12_month = Performance_of_12_month;
    }

    public String getGood_Rate ()
    {
        return Good_Rate;
    }

    public void setGood_Rate (String Good_Rate)
    {
        this.Good_Rate = Good_Rate;
    }

    public double getStandard_Deviation ()
    {
        return Standard_Deviation;
    }

    public void setStandard_Deviation (double Standard_Deviation)
    {
        this.Standard_Deviation = Standard_Deviation;
    }

    public String getPerformance_of_60_month ()
    {
        return Performance_of_60_month;
    }

    public void setPerformance_of_60_month (String Performance_of_60_month)
    {
        this.Performance_of_60_month = Performance_of_60_month;
    }

    public String getMutual_Fund_Nav ()
    {
        return Mutual_Fund_Nav;
    }

    public void setMutual_Fund_Nav (String Mutual_Fund_Nav)
    {
        this.Mutual_Fund_Nav = Mutual_Fund_Nav;
    }

    public String getMutual_Fund_Risk_Profile_ID ()
    {
        return Mutual_Fund_Risk_Profile_ID;
    }

    public void setMutual_Fund_Risk_Profile_ID (String Mutual_Fund_Risk_Profile_ID)
    {
        this.Mutual_Fund_Risk_Profile_ID = Mutual_Fund_Risk_Profile_ID;
    }

    public String getProduct_Type ()
    {
        return Product_Type;
    }

    public void setProduct_Type (String Product_Type)
    {
        this.Product_Type = Product_Type;
    }

    public String getMutual_Fund_Code ()
    {
        return Mutual_Fund_Code;
    }

    public void setMutual_Fund_Code (String Mutual_Fund_Code)
    {
        this.Mutual_Fund_Code = Mutual_Fund_Code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Mutual_Fund_Type = "+Mutual_Fund_Type+", Product_Rate = "+Product_Rate+", Product_ID = "+Product_ID+", Average_Rate = "+Average_Rate+", YTD = "+YTD+", Product_Name = "+Product_Name+", Bad_Rate = "+Bad_Rate+", Performance_of_6_month = "+Performance_of_6_month+", Performance_of_1_month = "+Performance_of_1_month+", Performance_of_12_month = "+Performance_of_12_month+", Good_Rate = "+Good_Rate+", Standard_Deviation = "+Standard_Deviation+", Performance_of_60_month = "+Performance_of_60_month+", Mutual_Fund_Nav = "+Mutual_Fund_Nav+", Mutual_Fund_Risk_Profile_ID = "+Mutual_Fund_Risk_Profile_ID+", Product_Type = "+Product_Type+", Mutual_Fund_Code = "+Mutual_Fund_Code+"]";
    }
}

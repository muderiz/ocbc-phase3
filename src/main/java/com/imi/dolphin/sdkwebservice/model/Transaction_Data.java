/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imi.dolphin.sdkwebservice.model;

/**
 *
 * @author chochong
 */
public class Transaction_Data {

    //alamat email
    public String Email;

    //apakah sudah pernah berinvestasi atau belum ============================== (mulai berinvestasi)
    // value 0 (belum pernah)
    // value 1 (sudah pernah)
    public int Is_Already_Invest;

    //Pendapatan yang bisa di tabung =========================================== (mulai berinvestasi)
    //value 1 ('Kurang dari 10%')
    //value 2 ('sekitar 10%-30%')
    //value 3 ('lebih dari 30%')
    public int Financial_Info_Saved_Income_ID;

    public int Product_ID;

    public String Platform_Name;

    // nomor telpon yang dapat di hubungi
    public String Phone_Number;

    // umur anak =============================================================== (pendidikan kuliah anak)
    public int Children_Age;

    // nama lengkap ============================================================ (greetings)
    public String Name;

    // dana yang di butuhkan untuk ============================================= (life goal lainnya)
    // value longInteger
    public long Target_Amount;

    // skenario_keuntungan ===================================================== (Risk profile)
    // value 5.a ('Pilih A' )
    // value 5.b ('Pilih B')
    // value 5.c ('Pilih C')
    // value 5.d ('Pilih D')
    // value 5.e ('Pilih E')
    public String Potential_Loss;

    // dana yang sudah di sisihkan ============================================= (life goal lainnya)
    public long Initial_Amount;

    public String Datetime_Start_Process;

    // sumber pendapatan ======================================================= (Risk Profile)
    // value 2.a ('Gaji')
    // value 2.b ('Usaha/ bisnis pribadi')
    // value 2.c ('Warisan/ dari orang tua')
    // value 2.d ('Tidak ada sumber tetap')
    public String Source_of_Income;

    // umur
    public int Age;

    //status pernikahan ======================================================== (mulai berinvestasi)
    // value 0 (belum menikah)
    // value 1 (sudah menikah)
    public int Is_Married;

    public String Time_Horizon;

    // pendapatan perbulan ===================================================== (mulai berinvestasi)
    // value 1 ('Kurang dari Rp. 15 juta')
    // value 2 ('Rp. 15 juta - 35 juta')
    // value 3 ('Lebih dari Rp. 35 juta')
    public int Financial_Info_Income_Range_ID;

    //judul investasi ========================================================== (life goal lainnya)
    public String Financial_Goal;

    // life goal =============================================================== (life goal)
    // value 1 (pertumbuhan aset)
    // value 2 (pendidikan kuliah anak)
    // value 3 (lainnya)
    // value 3 (aku belum tahu nih)
    public int Life_Goal_ID;

    // pengetahuan berinvestasi ================================================ (risk profile)
    // value 3.a (Sangat Terbatas)
    // value 3.b (Terbatas)
    // value 3.c (Cukup)
    // value 3.d (Mengerti)
    // value 3.e (Sangat Mengerti)
    public String Investment_Knowledge;

    // berapa tahun kah akan melakukan investasi =============================== (pertumbuhan aset)
    //                                              ============================ (life goal lainnya)
    //                                              ============================
    public int Tenor;

    // dimanakah anak akan di kuliahkan ======================================== (pendidikan kuliah anak)
    // value 1 (indonesia)
    // value 2 (Singapura)
    // value 3 (Britania Raya/ Inggris)
    // value 4 (Amerika Serikat (Universitas Negeri))
    // value 5 (Amerika Serikat (Universitas Swasta))
    // value 6 (Australia)
    public int EC_ID;

    //dana perbulan yang ingin di investasikan ================================= (pertumbuhan aset)
    public long Investment_Amount;

    // risk profile ============================================================ (risk profile)
    // value 1 (Conservative)
    // value 2 (Balance)
    // value 3 (Growth)
    // value 4 (Aggressive)
    // value 0 (Hitung Profil Risiko Kembali)
    public int Risk_Profile_ID;

    public String Datetime_End_Process;

    // pembagian hasil keuntungan ============================================== (risk profile)
    // value 4.a 'Menarik seluruh hasil untuk kebutuhan sehari-hari'
    // value 4.b 'Menarik sebagian besar hasil dan menginvestasikan sisanya' 
    // value 4.c 'Menarik setengahnya dan menginvestasikan setengahnya'
    // value 4.d 'Menarik sebagian kecil hasil dan menginvestasikan sisanya' 
    // value 4.e 'Menginvestasikan kembali seluruh hasil investasi'
    public String Income_Usage;

    public String Location;

    // tipe berinvestasi ======================================================= (pertumbuhan aset)
    // value 0 (perbulan)
    // value 1 (Lumsum)
    public int Investment_Type;

    public int Rating;

    public String Rating_Comment;

    public String Status_Nasabah;

    public String getStatus_Nasabah() {
        return Status_Nasabah;
    }

    public void setStatus_Nasabah(String Status_Nasabah) {
        this.Status_Nasabah = Status_Nasabah;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }

    public String getRating_Comment() {
        return Rating_Comment;
    }

    public void setRating_Comment(String Rating_Comment) {
        this.Rating_Comment = Rating_Comment;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getIs_Already_Invest() {
        return Is_Already_Invest;
    }

    public void setIs_Already_Invest(int Is_Already_Invest) {
        this.Is_Already_Invest = Is_Already_Invest;
    }

    public int getFinancial_Info_Saved_Income_ID() {
        return Financial_Info_Saved_Income_ID;
    }

    public void setFinancial_Info_Saved_Income_ID(int Financial_Info_Saved_Income_ID) {
        this.Financial_Info_Saved_Income_ID = Financial_Info_Saved_Income_ID;
    }

    public int getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(int Product_ID) {
        this.Product_ID = Product_ID;
    }

    public String getPlatform_Name() {
        return Platform_Name;
    }

    public void setPlatform_Name(String Platform_Name) {
        this.Platform_Name = Platform_Name;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String Phone_Number) {
        this.Phone_Number = Phone_Number;
    }

    public int getChildren_Age() {
        return Children_Age;
    }

    public void setChildren_Age(int Children_Age) {
        this.Children_Age = Children_Age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public long getTarget_Amount() {
        return Target_Amount;
    }

    public void setTarget_Amount(long Target_Amount) {
        this.Target_Amount = Target_Amount;
    }

    public String getPotential_Loss() {
        return Potential_Loss;
    }

    public void setPotential_Loss(String Potential_Loss) {
        this.Potential_Loss = Potential_Loss;
    }

    public long getInitial_Amount() {
        return Initial_Amount;
    }

    public void setInitial_Amount(long Initial_Amount) {
        this.Initial_Amount = Initial_Amount;
    }

    public String getDatetime_Start_Process() {
        return Datetime_Start_Process;
    }

    public void setDatetime_Start_Process(String Datetime_Start_Process) {
        this.Datetime_Start_Process = Datetime_Start_Process;
    }

    public String getSource_of_Income() {
        return Source_of_Income;
    }

    public void setSource_of_Income(String Source_of_Income) {
        this.Source_of_Income = Source_of_Income;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getIs_Married() {
        return Is_Married;
    }

    public void setIs_Married(int Is_Married) {
        this.Is_Married = Is_Married;
    }

    public String getTime_Horizon() {
        return Time_Horizon;
    }

    public void setTime_Horizon(String Time_Horizon) {
        this.Time_Horizon = Time_Horizon;
    }

    public int getFinancial_Info_Income_Range_ID() {
        return Financial_Info_Income_Range_ID;
    }

    public void setFinancial_Info_Income_Range_ID(int Financial_Info_Income_Range_ID) {
        this.Financial_Info_Income_Range_ID = Financial_Info_Income_Range_ID;
    }

    public String getFinancial_Goal() {
        return Financial_Goal;
    }

    public void setFinancial_Goal(String Financial_Goal) {
        this.Financial_Goal = Financial_Goal;
    }

    public int getLife_Goal_ID() {
        return Life_Goal_ID;
    }

    public void setLife_Goal_ID(int Life_Goal_ID) {
        this.Life_Goal_ID = Life_Goal_ID;
    }

    public String getInvestment_Knowledge() {
        return Investment_Knowledge;
    }

    public void setInvestment_Knowledge(String Investment_Knowledge) {
        this.Investment_Knowledge = Investment_Knowledge;
    }

    public int getTenor() {
        return Tenor;
    }

    public void setTenor(int Tenor) {
        this.Tenor = Tenor;
    }

    public int getEC_ID() {
        return EC_ID;
    }

    public void setEC_ID(int EC_ID) {
        this.EC_ID = EC_ID;
    }

    public long getInvestment_Amount() {
        return Investment_Amount;
    }

    public void setInvestment_Amount(long Investment_Amount) {
        this.Investment_Amount = Investment_Amount;
    }

    public int getRisk_Profile_ID() {
        return Risk_Profile_ID;
    }

    public void setRisk_Profile_ID(int Risk_Profile_ID) {
        this.Risk_Profile_ID = Risk_Profile_ID;
    }

    public String getDatetime_End_Process() {
        return Datetime_End_Process;
    }

    public void setDatetime_End_Process(String Datetime_End_Process) {
        this.Datetime_End_Process = Datetime_End_Process;
    }

    public String getIncome_Usage() {
        return Income_Usage;
    }

    public void setIncome_Usage(String Income_Usage) {
        this.Income_Usage = Income_Usage;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getInvestment_Type() {
        return Investment_Type;
    }

    public void setInvestment_Type(int Investment_Type) {
        this.Investment_Type = Investment_Type;
    }

    @Override
    public String toString() {
        return "ClassPojo [Email = " + Email + ", Is_Already_Invest = " + Is_Already_Invest + ", Financial_Info_Saved_Income_ID = " + Financial_Info_Saved_Income_ID + ", Product_ID = " + Product_ID + ", Platform_Name = " + Platform_Name + ", Phone_Number = " + Phone_Number + ", Children_Age = " + Children_Age + ", Name = " + Name + ", Target_Amount = " + Target_Amount + ", Potential_Loss = " + Potential_Loss + ", Initial_Amount = " + Initial_Amount + ", Datetime_Start_Process = " + Datetime_Start_Process + ", Source_of_Income = " + Source_of_Income + ", Age = " + Age + ", Is_Married = " + Is_Married + ", Time_Horizon = " + Time_Horizon + ", Financial_Info_Income_Range_ID = " + Financial_Info_Income_Range_ID + ", Financial_Goal = " + Financial_Goal + ", Life_Goal_ID = " + Life_Goal_ID + ", Investment_Knowledge = " + Investment_Knowledge + ", Tenor = " + Tenor + ", EC_ID = " + EC_ID + ", Investment_Amount = " + Investment_Amount + ", Risk_Profile_ID = " + Risk_Profile_ID + ", Datetime_End_Process = " + Datetime_End_Process + ", Income_Usage = " + Income_Usage + ", Location = " + Location + ", Investment_Type = " + Investment_Type + "]";
    }
}

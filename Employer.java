package com.example.dtl;

public class Employer {
    Integer id;
    String name,c_name,est_yr,Address,TradeLc,Email,type;

    public Employer(Integer id, String name, String c_name, String est_yr, String address, String tradeLc, String email, String type) {
        this.id = id;
        this.name = name;
        this.c_name = c_name;
        this.est_yr = est_yr;
        Address = address;
        TradeLc = tradeLc;
        Email = email;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getC_name() {
        return c_name;
    }

    public String getEst_yr() {
        return est_yr;
    }

    public String getAddress() {
        return Address;
    }

    public String getTradeLc() {
        return TradeLc;
    }

    public String getEmail() {
        return Email;
    }

    public String getType() {
        return type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public void setEst_yr(String est_yr) {
        this.est_yr = est_yr;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setTradeLc(String tradeLc) {
        TradeLc = tradeLc;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setType(String type) {
        this.type = type;
    }
}

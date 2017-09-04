package com.example.odisys.oms.model;

/**
 * Created by Odisys on 08/05/2017.
 */

public class Cusromer {
    private String cusromer_ID;
    private String cusromer_Nama;
    private String no_tlp;
    private String address;
    private String email;
    private String store_ID;
    private String store_name;
    public Cusromer(String cusromer_ID, String cusromer_Nama, String no_tlp, String address, String email, String store_ID, String store_name) {
        this.cusromer_ID = cusromer_ID;
        this.cusromer_Nama = cusromer_Nama;
        this.no_tlp = no_tlp;
        this.address = address;
        this.email = email;
        this.store_ID = store_ID;
        this.store_name = store_name;
    }

    public String getCusromer_ID() {
        return cusromer_ID;
    }

    public void setCusromer_ID(String cusromer_ID) {
        this.cusromer_ID = cusromer_ID;
    }

    public String getCusromer_Nama() {
        return cusromer_Nama;
    }

    public void setCusromer_Nama(String cusromer_Nama) {
        this.cusromer_Nama = cusromer_Nama;
    }

    public String getNo_tlp() {
        return no_tlp;
    }

    public void setNo_tlp(String no_tlp) {
        this.no_tlp = no_tlp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStore_ID() {
        return store_ID;
    }

    public void setStore_ID(String store_ID) {
        this.store_ID = store_ID;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
}

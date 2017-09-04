package com.example.odisys.oms.model;

/**
 * Created by Odisys on 08/05/2017.
 */

public class Store {
    private String store_id;
    private String store_name;
    private String store_lead;
    private String address;

    public Store(String store_id, String store_name, String store_lead, String address) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.store_lead = store_lead;
        this.address = address;
    }

    public Store() {
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_lead() {
        return store_lead;
    }

    public void setStore_lead(String store_lead) {
        this.store_lead = store_lead;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

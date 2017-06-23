package com.example.odisys.oms.model;

/**
 * Created by Odisys on 20/05/2017.
 */

public class SORevisi {
    private String product_code;
    private String product_name;
    private String store_name;
    private String customer_name;
    private String address;
    private int quantity;

    public SORevisi(String product_code, String product_name, String store_name, String customer_name, String address, int quantity) {
        this.product_code = product_code;
        this.product_name = product_name;
        this.store_name = store_name;
        this.customer_name = customer_name;
        this.address = address;
        this.quantity = quantity;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

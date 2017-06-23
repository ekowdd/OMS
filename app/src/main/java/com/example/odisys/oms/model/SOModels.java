package com.example.odisys.oms.model;

/**
 * Created by Odisys on 08/03/2017.
 */

public class SOModels {
    private int images;
    private String product_code;
    private String producr_name;
    private String store_name;
    private String address;
    private int quantity;

    public SOModels(int images, String product_code, String producr_name, String store_name, String address, int quantity) {
        this.images = images;
        this.product_code = product_code;
        this.producr_name = producr_name;
        this.store_name = store_name;
        this.address = address;
        this.quantity = quantity;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProducr_name() {
        return producr_name;
    }

    public void setProducr_name(String producr_name) {
        this.producr_name = producr_name;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
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

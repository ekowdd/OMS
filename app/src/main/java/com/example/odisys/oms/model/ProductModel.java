package com.example.odisys.oms.model;

/**
 * Created by Odisys on 25/02/2017.
 */

public class ProductModel {
    private String imgs;
    private String product_code;
    private String product_name;
    private String nama_file;
    private String type;
    private String barcode;
    private String qty;
    private String value_s;
    private String total;
    private String keterangan;

    public ProductModel(String imgs, String product_code, String product_name, String nama_file, String type, String barcode, String qty, String value_s, String total, String keterangan) {
        this.imgs = imgs;
        this.product_code = product_code;
        this.product_name = product_name;
        this.nama_file = nama_file;
        this.type = type;
        this.barcode = barcode;
        this.qty = qty;
        this.value_s = value_s;
        this.total = total;
        this.keterangan = keterangan;
    }


    public ProductModel() {
    }


    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
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

    public String getNama_file() {
        return nama_file;
    }

    public void setNama_file(String nama_file) {
        this.nama_file = nama_file;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getValue_s() {
        return value_s;
    }

    public void setValue_s(String value_s) {
        this.value_s = value_s;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}

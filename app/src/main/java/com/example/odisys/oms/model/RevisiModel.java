package com.example.odisys.oms.model;

/**
 * Created by eko on 7/17/17.
 */

public class RevisiModel {
    private String
            trans_code,
            revisi_id,
            product_code,
            barcode,
            product_name,
            store_name,
            store_lead,
            nik,
            nama,
            tanggal,
            waktu,
            quantity,
            value,
            total,
            missed,
            total_value_missed;

    public RevisiModel(String trans_code, String revisi_id, String product_code, String barcode, String product_name, String store_name, String store_lead, String nik, String nama, String tanggal, String waktu, String quantity, String value, String total, String missed, String total_value_missed) {
        this.trans_code = trans_code;
        this.revisi_id = revisi_id;
        this.product_code = product_code;
        this.barcode = barcode;
        this.product_name = product_name;
        this.store_name = store_name;
        this.store_lead = store_lead;
        this.nik = nik;
        this.nama = nama;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.quantity = quantity;
        this.value = value;
        this.total = total;
        this.missed = missed;
        this.total_value_missed = total_value_missed;
    }

    public String getTrans_code() {
        return trans_code;
    }

    public void setTrans_code(String trans_code) {
        this.trans_code = trans_code;
    }

    public String getRevisi_id() {
        return revisi_id;
    }

    public void setRevisi_id(String revisi_id) {
        this.revisi_id = revisi_id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public String getStore_lead() {
        return store_lead;
    }

    public void setStore_lead(String store_lead) {
        this.store_lead = store_lead;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMissed() {
        return missed;
    }

    public void setMissed(String missed) {
        this.missed = missed;
    }

    public String getTotal_value_missed() {
        return total_value_missed;
    }

    public void setTotal_value_missed(String total_value_missed) {
        this.total_value_missed = total_value_missed;
    }
}

package com.example.odisys.oms.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Odisys on 17/03/2017.
 */

public class Users {
    private String nik;
    private String namaLengkap;
    private String email;
    private String status;
    private String password;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Users(String nik, String namaLengkap, String email, String status, String password, Map<String, Object> additionalProperties) {
        this.nik = nik;
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.status = status;
        this.password = password;
        this.additionalProperties = additionalProperties;
    }

    public Users(){}

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}

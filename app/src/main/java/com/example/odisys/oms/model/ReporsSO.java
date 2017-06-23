package com.example.odisys.oms.model;

/**
 * Created by Odisys on 20/05/2017.
 */

public class ReporsSO {
    private String store_name;
    private String product_name;
    private String address;
    private int quantity;
    private int values;
    private int total;
    private int deposit;
    private int people_min;
    private int devisit;

    public ReporsSO(String product_name, String address, int quantity, int values, int total, int deposit, int people_min, int devisit) {
        this.product_name = product_name;
        this.address = address;
        this.quantity = quantity;
        this.values = values;
        this.total = total;
        this.deposit = deposit;
        this.people_min = people_min;
        this.devisit = devisit;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getPeople_min() {
        return people_min;
    }

    public void setPeople_min(int people_min) {
        this.people_min = people_min;
    }

    public int getDevisit() {
        return devisit;
    }

    public void setDevisit(int devisit) {
        this.devisit = devisit;
    }
}

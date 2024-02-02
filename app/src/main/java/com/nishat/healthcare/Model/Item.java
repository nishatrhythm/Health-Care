package com.nishat.healthcare.Model;

import java.io.Serializable;

public class Item implements Serializable {
    private String id,categoryName,name,price,doctorName,doctorAddress,doctorCategory,doctorExperties,doctorMobileNumber;

    public Item(String id, String categoryName, String name, String price) {
        this.id = id;
        this.categoryName = categoryName;
        this.name = name;
        this.price = price;
    }

    public Item(String id, String categoryName, String price, String doctorName, String doctorAddress, String doctorCategory, String doctorExperties, String doctorMobileNumber) {
        this.id = id;
        this.categoryName = categoryName;
        this.price = price;
        this.doctorName = doctorName;
        this.doctorAddress = doctorAddress;
        this.doctorCategory = doctorCategory;
        this.doctorExperties = doctorExperties;
        this.doctorMobileNumber = doctorMobileNumber;
    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getDoctorCategory() {
        return doctorCategory;
    }

    public void setDoctorCategory(String doctorCategory) {
        this.doctorCategory = doctorCategory;
    }

    public String getDoctorExperties() {
        return doctorExperties;
    }

    public void setDoctorExperties(String doctorExperties) {
        this.doctorExperties = doctorExperties;
    }

    public String getDoctorMobileNumber() {
        return doctorMobileNumber;
    }

    public void setDoctorMobileNumber(String doctorMobileNumber) {
        this.doctorMobileNumber = doctorMobileNumber;
    }
}

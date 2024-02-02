package com.nishat.healthcare.Model;

import java.io.Serializable;
import java.util.Date;

public class Cart implements Serializable {
    String id,name;
    Item item;
    Date addedDate;
    private String userId;

    public Cart(String id, String name, Item item, Date addedDate,String userId) {
        this.id = id;
        this.name = name;
        this.item = item;
        this.addedDate = addedDate;
        this.userId=userId;
    }

    public Cart() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

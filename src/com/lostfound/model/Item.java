package com.lostfound.model;

public class Item {

    private int itemId;
    private String itemName;
    private String category;
    private String description;
    private String color;
    private String brand;
    private String identificationDetails;
    private String status;

    public Item() {
    }

    public Item(int itemId, String itemName, String category,
                String description, String color, String brand,
                String identificationDetails, String status) {

        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.color = color;
        this.brand = brand;
        this.identificationDetails = identificationDetails;
        this.status = status;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getIdentificationDetails() {
        return identificationDetails;
    }

    public void setIdentificationDetails(String identificationDetails) {
        this.identificationDetails = identificationDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
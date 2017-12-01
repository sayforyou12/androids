package com.cafe.common.Model;


public class ModelCafeMenu {
    // SLF4J Logging


    private String brand;
    private String menucd;
    private String menu_name;
    private Integer price;
    private String description;

    public ModelCafeMenu(String menu_name, Integer price, String description) {
        this.menu_name = menu_name;
        this.price = price;
        this.description = description;
    }

    public ModelCafeMenu() {
    }

    public ModelCafeMenu(String brand, String menucd, String menu_name, Integer price, String description) {
        this.brand = brand;
        this.menucd = menucd;
        this.menu_name = menu_name;
        this.price = price;
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMenucd() {
        return menucd;
    }

    public void setMenucd(String menucd) {
        this.menucd = menucd;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


package com.pobprojekt.javapobprojekt.core;

public abstract class Product {
    protected ClassType classType;

    private long id;
    private String name;
    private double price;
    private String manufacturer;

    private static long lastId;

    public Product(String name, String manufacturer, double price) {
        this.id = getNextId();
        this.classType = ClassType.Product;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public ClassType getClassType() {
        return classType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public abstract String getAdditionalInfo();

    public static long getNextId() {
        return lastId++;
    }
}

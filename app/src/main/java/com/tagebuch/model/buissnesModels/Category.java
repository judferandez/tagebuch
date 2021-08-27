package com.tagebuch.model.buissnesModels;

public class Category {
    private int categoryId;
    private String name;
    private  String description;
    private int colorId;

    public Category(int categoryId, String name, String description, int colorId) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.colorId = colorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return colorId;
    }

    public void setColor(int colorId) {
        this.colorId = colorId;
    }
}

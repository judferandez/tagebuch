package com.tagebuch.model.buissnesModels;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey
    @NonNull
    private String _idCategory;
    @NonNull
    private String name;
    @NonNull
    private  String description;
    @NonNull
    private String color;

    @Ignore
    public Category(){
        this._idCategory = UUID.randomUUID().toString();
        this.name = "";
        this.description = "";
        this.color = "";
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getColor() {
        return color;
    }

    public void setColor(@NonNull String color) {
        this.color = color;
    }
}

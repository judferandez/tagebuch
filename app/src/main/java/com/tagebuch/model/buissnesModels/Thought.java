package com.tagebuch.model.buissnesModels;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "thoughts")
public class Thought {
    @PrimaryKey
    @NonNull
    private String _idThought;
    @NonNull
    private String title;
    @NonNull
    private  String description;
    @NonNull
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    private String createdTime;

    public Thought(){
        this._idThought = UUID.randomUUID().toString();
        this.title = "";
        this.description = "";
        this.createdTime = "";
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(@NonNull String createdTime) {
        this.createdTime = createdTime;
    }
}

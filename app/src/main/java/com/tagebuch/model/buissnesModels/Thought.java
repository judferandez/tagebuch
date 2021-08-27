package com.tagebuch.model.buissnesModels;

import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "thoughts")
public class Thought implements Comparable<Thought> {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    private String _id;
    @NonNull
    @ColumnInfo(name="title")
    private String title;
    @NonNull
    @ColumnInfo(name="description")
    private  String description;
    @NonNull
    @ColumnInfo(name="create_date")
    private String createdTime;
    @NonNull
    @ColumnInfo(name="category_id")
    private int categoryId;

    public Thought(){
        this._id = UUID.randomUUID().toString();
        this.title = "";
        this.description = "";
        this.createdTime = (DateFormat.format("MMMM d, yyyy ", (new Date()).getTime())).toString();;
        this.categoryId = 0;
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

    @NonNull
    public String get_id() {
        return _id;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    @NonNull
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NonNull int categoryId) {
        this.categoryId = categoryId;
    }

    @Ignore
    @Override
    public int compareTo(Thought f) {

        if (categoryId > f.categoryId) {
            return 1;
        }
        else if (categoryId <  f.categoryId) {
            return -1;
        }
        else {
            return 0;
        }

    }
}

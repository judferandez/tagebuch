package com.tagebuch.model.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tagebuch.model.buissnesModels.Category;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM categories")
    List<Category> getAllCategories();

    @Insert
    void insertCategories(Category ... categories);

    @Insert
    void insertCategory(Category category);

    @Update
    void updateCategories(List<Category> categories);

    @Update
    void updateCategory(Category category);

    @Delete
    void deleteAllCategories(Category ... categories);

    @Delete
    void deleteCategory(Category category);
}

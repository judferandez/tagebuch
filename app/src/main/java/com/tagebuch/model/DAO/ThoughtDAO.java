package com.tagebuch.model.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tagebuch.model.buissnesModels.Thought;

import java.util.List;

@Dao
public interface ThoughtDAO {
    @Query("SELECT * FROM thoughts ORDER BY categoryId")
    List<Thought> getAllThoughts();

    @Insert
    void insertThought(Thought thought);

    @Update
    void updateThought(Thought thought);

    @Delete
    void deleteThought(Thought thought);
}

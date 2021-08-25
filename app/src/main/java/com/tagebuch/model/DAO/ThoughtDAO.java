package com.tagebuch.model.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tagebuch.model.buissnesModels.Category;
import com.tagebuch.model.buissnesModels.Thought;

import java.util.List;

@Dao
public interface ThoughtDAO {
    @Query("SELECT * FROM thoughts")
    List<Thought> getAllThoughts();

    @Insert
    void insertThoughts(Thought ... thoughts);

    @Insert
    void insertThought(Thought thought);

    @Update
    void updateThoughts(List<Thought> thoughts);

    @Update
    void updateThought(Thought thought);

    @Delete
    void deleteThoughts(Thought ... thoughts);

    @Delete
    void deleteThought(Thought thought);
}

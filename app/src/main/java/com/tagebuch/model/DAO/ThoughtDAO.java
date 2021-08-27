package com.tagebuch.model.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.tagebuch.model.buissnesModels.Thought;

import java.util.List;

@Dao
public interface ThoughtDAO {
    @Query("SELECT * FROM thoughts")
    public List<Thought> loadAllThoughts();

    @Query("DELETE FROM thoughts")
    public void nukeTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertThoughts(Thought... thoughts);
}

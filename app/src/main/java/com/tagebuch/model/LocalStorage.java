package com.tagebuch.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tagebuch.model.buissnesModels.Category;
import com.tagebuch.model.DAO.CategoryDAO;
import com.tagebuch.model.buissnesModels.Thought;
import com.tagebuch.model.DAO.ThoughtDAO;

@Database(entities = {Category.class, Thought.class}, version = 1)
public abstract class LocalStorage extends RoomDatabase {
    public abstract CategoryDAO categoryDAO();
    public  abstract ThoughtDAO thoughtDAO();

    private static LocalStorage localStorage;

    public static LocalStorage getLocalStorage(final Context context){
        if(localStorage == null){
            localStorage = Room.databaseBuilder(
                    context,
                    LocalStorage.class,
                    "tagebuch"
                    )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return  localStorage;
    }
}

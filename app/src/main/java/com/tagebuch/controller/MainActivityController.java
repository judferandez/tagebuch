package com.tagebuch.controller;

import com.tagebuch.model.DAO.CategoryDAO;
import com.tagebuch.model.DAO.ThoughtDAO;
import com.tagebuch.model.LocalStorage;
import com.tagebuch.model.buissnesModels.Thought;
import com.tagebuch.view.MainActivity;

public class MainActivityController {
    private CategoryDAO categoryDAO;
    private ThoughtDAO thoughtDAO;

    public void register(MainActivity mainActivity, String title, String description ){
        if(title == null || title.compareTo("") == 0){
            mainActivity.fieldValidateMandatory("titulo");
            return;
        }
        if(description == null || description.compareTo("") == 0){
            mainActivity.fieldValidateMandatory("descripción");
            return;
        }
        if(title.length()>100){
            mainActivity.validateTitleLength();
            return;
        }

        Thought thought = new Thought();
        thought.setTitle("Pensamientico szs");
        thought.setDescription("La descripcion perrona");
        this.thoughtDAO = LocalStorage.getLocalStorage(mainActivity.getApplicationContext()).thoughtDAO();
        this.thoughtDAO.insertThought(thought);
    }
}

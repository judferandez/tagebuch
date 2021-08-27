package com.tagebuch.controller;

import android.graphics.Color;

import com.tagebuch.memento.ThoughtsCareTaker;
import com.tagebuch.model.DAO.ThoughtDAO;
import com.tagebuch.model.LocalStorage;
import com.tagebuch.model.buissnesModels.Category;
import com.tagebuch.model.buissnesModels.Thought;
import com.tagebuch.view.ThoughtsView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThoughtController {
    private final ThoughtDAO thoughtDAO;
    private List<Thought> thoughtsList = new ArrayList<>();
    private final List<Category> categoryList = new ArrayList<>();
    private final ThoughtsCareTaker careTaker;
    private final ThoughtsView thoughtView;

    public ThoughtController(ThoughtsView thoughtsView){
        thoughtView = thoughtsView;
        this.thoughtDAO = LocalStorage.getLocalStorage(thoughtsView.getApplicationContext()).thoughtDAO();
        setCategoryList();
        careTaker = new ThoughtsCareTaker();
        setThoughtList();
    }

    public void setThoughtList(){
        thoughtsList = this.thoughtDAO.loadAllThoughts();
        careTaker.createMemento(thoughtsList);
    }

    public void register(String title, String description, int categoryId ){
        if(isValidThought(title, description)) {
            Thought newThought = new Thought();
            newThought.setTitle(title);
            newThought.setDescription(description);
            newThought.setCategoryId(categoryId);
            thoughtsList.add(newThought);
            thoughtView.refreshThoughts();
            careTaker.createMemento(thoughtsList);
            saveThoughtListInDatabase();
        }
    }

    public void saveThoughtListInDatabase(){
        this.thoughtDAO.nukeTable();
        for(Thought element: thoughtsList){
            this.thoughtDAO.insertThoughts(element);
        }
    }

    public void undoAction(){
        if(careTaker.canUndo()){
            generateNewList(careTaker.getUndo().getThoughtsState());
        }
    }

    public void redoAction(){
        if(careTaker.canRedo()){
            generateNewList(careTaker.getRedo().getThoughtsState());
        }
    }

    public void generateNewList(List<Thought> mementoThoughtsList){
        List<Thought> newThoughtList = new ArrayList<>();
        for(Thought element: mementoThoughtsList){
            Thought newThought = new Thought();
            newThought.set_id(element.get_id());
            newThought.setTitle(element.getTitle());
            newThought.setDescription(element.getDescription());
            newThought.setCreatedTime(element.getCreatedTime());
            newThought.setCategoryId(element.getCategoryId());
            newThoughtList.add(newThought);
        }
        thoughtsList = newThoughtList;
        thoughtView.refreshThoughts();
        saveThoughtListInDatabase();
    }

    public List<Thought> list(){
            Collections.sort(thoughtsList);
            return thoughtsList;
    }

    public void edit(String thoughtId, String title, String description){
        if(isValidThought(title, description)) {
            Thought thought = getThoughtById(thoughtId);
            int index = thoughtsList.indexOf(thought);
            thought.setTitle(title);
            thought.setDescription(description);
            thoughtsList.set(index, thought);
            thoughtView.refreshThoughts();
            careTaker.createMemento(thoughtsList);
            saveThoughtListInDatabase();
        }
    }

    public Thought getThoughtById(String thoughtId){
        for (Thought element : thoughtsList) {
            if(element.get_id().equals(thoughtId)){
                return element;
            }
        }
        return null;
    }

    public boolean isValidThought(String title, String description){
        if(title == null || title.compareTo("") == 0){
            thoughtView.fieldValidateMandatory();
            return false;
        }
        if(description == null || description.compareTo("") == 0){
            thoughtView.fieldValidateMandatory();
            return false;
        }
        if(title.length()>100){
            thoughtView.validateTitleLength();
            return false;
        }
        return true;
    }

    public void delete(String thoughtId){
        Thought thought = getThoughtById(thoughtId);
        int index = thoughtsList.indexOf(thought);
        thoughtsList.remove(index);
        thoughtView.refreshThoughts();
        careTaker.createMemento(thoughtsList);
        saveThoughtListInDatabase();
    }

    public void setCategoryList(){
        Category category1 = new Category(0, "Romantico", "Estos pensamientos son aquellos con relación a la pareja o al amor", Color.RED);
        Category category2 = new Category(1, "Creativo", "Estos pensamientos son aquellos con relación a una idea genial", Color.YELLOW);
        Category category3 = new Category(2, "Logico", "Estos pensamientos son aquellos con relación a la razon y la logica", Color.BLUE);
        Category category4 = new Category(3, "Matematico", "Estos pensamientos son aquellos con relación a numeros", Color.CYAN);
        Category category5 = new Category(4, "Gamer", "Estos pensamientos son aquellos con relación a Cattleya Gaming", Color.GRAY);
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
        categoryList.add(category4);
        categoryList.add(category5);
    }

    public List<Category> getCategoryList (){
        return categoryList;
    }
}

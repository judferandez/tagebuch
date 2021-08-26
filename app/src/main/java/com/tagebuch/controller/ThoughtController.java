package com.tagebuch.controller;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.Toast;

import com.tagebuch.memento.ThoughtsCareTaker;
import com.tagebuch.memento.ThoughtsMemento;
import com.tagebuch.model.DAO.ThoughtDAO;
import com.tagebuch.model.buissnesModels.Category;
import com.tagebuch.model.buissnesModels.Thought;
import com.tagebuch.view.ThoughtsView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThoughtController {
    private ThoughtDAO thoughtDAO;
    private List<Thought> thoughtsList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();
    private ThoughtsCareTaker careTaker = new ThoughtsCareTaker();

    public void register(ThoughtsView thoughtsView, String title, String description, int categoryId ){
        if(isValidThought(thoughtsView, title, description)) {
            Thought newThought = new Thought();
            newThought.setTitle(title);
            newThought.setDescription(description);
            newThought.setCategoryId(categoryId);
            thoughtsList.add(newThought);
            thoughtsView.refreshThoughts();
            createMemento();
            // this.thoughtDAO = LocalStorage.getLocalStorage(thoughtsView.getApplicationContext()).thoughtDAO();
            // this.thoughtDAO.insertThought(newThought);
        }
    }

    private void createMemento(){
        ThoughtsMemento memento = new ThoughtsMemento();
        memento.setThoughtsState(thoughtsList);
        careTaker.addThoughtsMementoToStack(memento);
    }

    public void undoAction(ThoughtsView thoughtsView){
        if(careTaker.canUndo()){
            thoughtsList = careTaker.getUndo().getThoughtsState();
            thoughtsView.refreshThoughts();
        }
    }

    public void redoAction(ThoughtsView thoughtsView){
        if(careTaker.canRedo()){
            thoughtsList = careTaker.getRedo().getThoughtsState();
            thoughtsView.refreshThoughts();
        }
    }

    public List<Thought> list(ThoughtsView thoughtsView){
            // this.thoughtDAO = LocalStorage.getLocalStorage(thoughtsView.getApplicationContext()).thoughtDAO();
            // thoughtsList = this.thoughtDAO.getAllThoughts();
            Collections.sort(thoughtsList);
            return thoughtsList;
    }

    public void edit(ThoughtsView thoughtsView, String thoughtId, String title, String description){
        if(isValidThought(thoughtsView, title, description)) {
            Thought thought = getThoughtById(thoughtId);
            int index = thoughtsList.indexOf(thought);
            thought.setTitle(title);
            thought.setDescription(description);
            thoughtsList.set(index, thought);
            thoughtsView.refreshThoughts();
            createMemento();
            // this.thoughtDAO = LocalStorage.getLocalStorage(thoughtsView.getApplicationContext()).thoughtDAO();
            // this.thoughtDAO.updateThought(thought);
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

    public boolean isValidThought(ThoughtsView thoughtsView, String title, String description){
        if(title == null || title.compareTo("") == 0){
            thoughtsView.fieldValidateMandatory();
            return false;
        }
        if(description == null || description.compareTo("") == 0){
            thoughtsView.fieldValidateMandatory();
            return false;
        }
        if(title.length()>100){
            thoughtsView.validateTitleLength();
            return false;
        }
        return true;
    }

    public void delete(ThoughtsView thoughtsView, String thoughtId){
        Thought thought = getThoughtById(thoughtId);
        int index = thoughtsList.indexOf(thought);
        thoughtsList.remove(index);
        thoughtsView.refreshThoughts();
        createMemento();
        // this.thoughtDAO = LocalStorage.getLocalStorage(thoughtsView.getApplicationContext()).thoughtDAO();
        // this.thoughtDAO.deleteThought(thought);
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

package com.tagebuch.memento;


import com.tagebuch.model.buissnesModels.Thought;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ThoughtsCareTaker {
    private final Stack<ThoughtsMemento> undoStack = new Stack<>();
    private final Stack<ThoughtsMemento> redoStack = new Stack<>();

    public void createMemento(List<Thought> thoughtList){
        ThoughtsMemento memento = new ThoughtsMemento();
        List<Thought> newThoughtList = new ArrayList<>();
        for(Thought element: thoughtList){
            Thought newThought = new Thought();
            newThought.set_id(element.get_id());
            newThought.setTitle(element.getTitle());
            newThought.setDescription(element.getDescription());
            newThought.setCreatedTime(element.getCreatedTime());
            newThought.setCategoryId(element.getCategoryId());
            newThoughtList.add(newThought);
        }
        memento.setThoughtsState(newThoughtList);
        addThoughtsMementoToStack(memento);
    }

    public ThoughtsMemento getUndo(){
        redoStack.push(undoStack.pop());
        return undoStack.peek();
    }

    public ThoughtsMemento getRedo(){
        undoStack.push(redoStack.peek());
        return redoStack.pop();
    }

    public void addThoughtsMementoToStack(ThoughtsMemento thoughtsMemento){
        if(thoughtsMemento != null){
            undoStack.push(thoughtsMemento);
            redoStack.clear();
        }
    }

    public boolean canUndo(){
        if(undoStack.size() > 1){
            return true;
        }
        return false;
    }

    public boolean canRedo(){
        if(redoStack.size() >= 1){
            return true;
        }
        return false;
    }
}

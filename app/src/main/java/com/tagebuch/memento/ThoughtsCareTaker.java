package com.tagebuch.memento;

import android.util.Log;
import android.widget.Toast;

import com.tagebuch.model.buissnesModels.Thought;
import com.tagebuch.view.ThoughtsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ThoughtsCareTaker {
    private Stack<ThoughtsMemento> undoStack = new Stack<>();
    private Stack<ThoughtsMemento> redoStack = new Stack<>();
    private ThoughtsView thoughtsView;

    public ThoughtsCareTaker(List<Thought> thoughtList, ThoughtsView thoughtView){
        thoughtsView = thoughtView;
        createMemento(thoughtList);
    }

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
            String aux = "|UndoStack| \n";
            int index = 1;
            for (ThoughtsMemento mement: undoStack){
                aux = aux + "|Memento " + index + "| \n";
                aux = aux + "|List| \n";
                int index2 = 1;
                for(Thought element: mement.getThoughtsState()){
                    if(index2 == mement.getThoughtsState().size()) {
                        aux = aux + element.getTitle() + " \n";
                    } else {
                        aux = aux + element.getTitle() + " --- ";
                    }
                    index2++;
                }
                index++;
            }
            Toast.makeText(thoughtsView, aux, Toast.LENGTH_LONG).show();
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

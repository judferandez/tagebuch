package com.tagebuch.memento;

import android.util.Log;

import java.util.Stack;

public class ThoughtsCareTaker {
    private Stack<ThoughtsMemento> undoStack = new Stack<>();
    private Stack<ThoughtsMemento> redoStack = new Stack<>();

    public ThoughtsMemento getUndo(){
        if(undoStack.size() >= 1){
            ThoughtsMemento memento = undoStack.pop();
            redoStack.push(memento);
            return memento;
        }
        return  null;
    }

    public ThoughtsMemento getRedo(){
        if(redoStack.size() >= 1){
            undoStack.push(redoStack.pop());
            ThoughtsMemento memento = redoStack.peek();
            return memento;
        }
        return null;
    }

    public void addThoughtsMementoToStack(ThoughtsMemento thoughtsMemento){
        if(thoughtsMemento != null){
            undoStack.push(thoughtsMemento);
            redoStack.clear();
        }
    }

    public boolean canUndo(){
        if(undoStack.size() >= 1){
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

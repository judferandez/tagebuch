package com.tagebuch.memento;

import com.tagebuch.model.buissnesModels.Thought;

import java.util.ArrayList;
import java.util.List;

public class ThoughtsMemento {
    private List<Thought> thoughtsState = new ArrayList<>();

    public List<Thought> getThoughtsState() {
        return thoughtsState;
    }

    public void setThoughtsState(List<Thought> thoughtsState) {
        this.thoughtsState = thoughtsState;
    }
}

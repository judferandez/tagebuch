package com.tagebuch.view.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tagebuch.R;
import com.tagebuch.controller.ThoughtController;

public class ThoughtInformationFragment extends Fragment {

    private View rootView;
    private int colorId;
    private String title, description, date, thoughtId;
    private TextView titleTextView, descriptionTextView, dateTextView, newTitleTextView, newDescriptionTextView;
    private Button removeButton, editButton, saveButton;
    private ConstraintLayout thoughtLayout;
    private ThoughtController thoughtController;

    public static ThoughtInformationFragment newInstance(
            ThoughtController thoughtController,
            String thoughtId, String title, String description,
            String date, int colorId) {
        ThoughtInformationFragment fragment = new ThoughtInformationFragment();
        fragment.setMainActivityController(thoughtController);
        fragment.setThoughtId(thoughtId);
        fragment.setTitle(title);
        fragment.setDescription(description);
        fragment.setDate(date);
        fragment.setColorId(colorId);
        return fragment;
    }

    public ThoughtInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_thought_information, container, false);
        removeButton = rootView.findViewById(R.id.delete_button);
        removeButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeThough(view);
            }
        }));
        editButton = rootView.findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editThought(view);
            }
        });
        titleTextView = rootView.findViewById(R.id.title_fragment_thought_value);
        descriptionTextView = rootView.findViewById(R.id.description_fragment_thought_value);
        dateTextView = rootView.findViewById(R.id.date_fragment_thought_value);
        thoughtLayout = rootView.findViewById(R.id.thought_layout);
        titleTextView.setText(title);
        descriptionTextView.setText(description);
        dateTextView.setText(date);
        thoughtLayout.setBackgroundColor(colorId);
        return  rootView;
    }

    private void removeThough(View view){
        thoughtController.delete(thoughtId);
    }

    private void editThought(View view){
        AlertDialog.Builder alertForm = new AlertDialog.Builder(view.getContext());
        LayoutInflater inflater = getLayoutInflater();
        View formView = inflater.inflate(R.layout.dialog_edit_form, null);
        newTitleTextView = formView.findViewById(R.id.title_create_form);
        newDescriptionTextView = formView.findViewById(R.id.description_create_form);
        saveButton = formView.findViewById(R.id.confirm_create_button);
        alertForm.setView(formView);
        AlertDialog dialog = alertForm.create();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thoughtController.edit(thoughtId, newTitleTextView.getText().toString(), newDescriptionTextView.getText().toString());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public String getThoughtId() {
        return thoughtId;
    }

    public void setThoughtId(String thoughtId) {
        this.thoughtId = thoughtId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public ThoughtController getMainActivityController() { return thoughtController; }

    public void setMainActivityController(ThoughtController thoughtController) {
        this.thoughtController = thoughtController;
    }
}
package com.tagebuch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tagebuch.R;
import com.tagebuch.controller.ThoughtController;
import com.tagebuch.model.buissnesModels.Thought;
import com.tagebuch.view.fragments.ThoughtInformationFragment;

public class ThoughtsView extends AppCompatActivity {

    private ThoughtController thoughtController;
    private TextView newTitleTextView, newDescriptionTextView, newCategoryName, newCategoryDescription;
    private Button createButton, saveButton, previousButton, nextButton, undoButton, redoButton;
    private ConstraintLayout newContainerCategoryLayout;
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thoughtController = new ThoughtController(this);
        createButton = findViewById(R.id.register_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { createThought(view); }
        });
        undoButton = findViewById(R.id.undo_button);
        redoButton = findViewById(R.id.redo_button);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { thoughtController.undoAction(); }
        });
        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { thoughtController.redoAction(); }
        });
        printListOfThoughts();

    }

    private ThoughtsView getMainActivity(){
        return this;
    }

    private void printListOfThoughts(){
        for (Thought element : thoughtController.list()) {
            getSupportFragmentManager().beginTransaction().add(
                    R.id.linear_layout,
                    ThoughtInformationFragment.newInstance(
                            thoughtController,
                            element.get_id(),
                            element.getTitle(),
                            element.getDescription(),
                            element.getCreatedTime(),
                            thoughtController.getCategoryList().get(element.getCategoryId()).getColor()
                    )
            ).commit();
        }
    }

    private void createThought(View view){
        AlertDialog.Builder alertForm = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View formView = inflater.inflate(R.layout.dialog_create_form, null);
        newTitleTextView = formView.findViewById(R.id.title_create_form);
        newDescriptionTextView = formView.findViewById(R.id.description_create_form);
        newCategoryName = formView.findViewById(R.id.category_name_create_form);
        newCategoryDescription = formView.findViewById(R.id.category_description_create_form);
        newContainerCategoryLayout = formView.findViewById(R.id.category_layout);
        saveButton = formView.findViewById(R.id.confirm_create_button);
        previousButton = formView.findViewById(R.id.previous_button);
        categoryId = 0;
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { categoryToLeft(); }
        });
        nextButton = formView.findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { categoryToRight(); }
        });
        alertForm.setView(formView);
        AlertDialog dialog = alertForm.create();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { registerThought(dialog); }
        });
        dialog.show();
    }

    public  void registerThought(Dialog dialog){
        thoughtController.register(
                newTitleTextView.getText().toString(),
                newDescriptionTextView.getText().toString(),
                categoryId);
        dialog.dismiss();
    }

    public void refreshThoughts(){
        removeOldThoughts();
        printListOfThoughts();
    }

    public void removeOldThoughts(){
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    public void categoryToLeft(){
        setCategoryIndex(categoryId - 1);
    }

    public void categoryToRight(){
        setCategoryIndex(categoryId + 1);
    }

    public void setCategoryIndex(int newCategoryId){
        if(newCategoryId < 0){
            categoryId = thoughtController.getCategoryList().size() - 1;
        }else if(newCategoryId == thoughtController.getCategoryList().size()){
            categoryId = 0;
        }else{
            categoryId = newCategoryId;
        }
        setCategoryId();
    }

    public void setCategoryId(){
        if(!thoughtController.getCategoryList().isEmpty()) {
            newCategoryName.setText(thoughtController.getCategoryList().get(categoryId).getName());
            newCategoryDescription.setText(thoughtController.getCategoryList().get(categoryId).getDescription());
            newContainerCategoryLayout.setBackgroundColor(thoughtController.getCategoryList().get(categoryId).getColor());
        }
    }

    public void fieldValidateMandatory(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Todos los campos son obligatorios")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void validateTitleLength(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El titulo debe tener menos de 100 caracteres")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
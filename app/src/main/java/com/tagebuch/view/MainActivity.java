package com.tagebuch.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import com.tagebuch.R;
import com.tagebuch.controller.MainActivityController;

public class MainActivity extends AppCompatActivity {

    private MainActivityController mainActivityController;
    private EditText titleEditText;
    private EditText descriptionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleEditText = findViewById(R.id.title_thought);
        descriptionEditText = findViewById(R.id.description_thought);
        mainActivityController = new MainActivityController();
    }

    public void fieldValidateMandatory(String formField){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El " + formField + " es obligatorio")
                .setTitle("Advertencia")
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
        builder.setMessage("El titulo debe tener una longitud maxima de 100 caracteres")
                .setTitle("Advertencia")
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
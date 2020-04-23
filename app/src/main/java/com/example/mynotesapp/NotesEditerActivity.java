package com.example.mynotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

public class NotesEditerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editer);

        EditText editText= (EditText) findViewById(R.id.editText);

        Intent intent = getIntent();
         int noteId = intent.getIntExtra("noteId",-1);

        if(noteId != -1)
        {                 // put static in array arraylist
            editText.setText(MainActivity.notes.get(noteId));
        }
        else
        {
            MainActivity.notes.add("My Note " + noteId);
            noteId = MainActivity.notes.size() - 1;
        }

        final int finalNoteId = noteId;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                MainActivity.notes.set(finalNoteId, String.valueOf(s));
               MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.mynotesapp", Context.MODE_PRIVATE);
                //Set
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //Toast.makeText(this, "dasf", Toast.LENGTH_LONG).show();
    }
}

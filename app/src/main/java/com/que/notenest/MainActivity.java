package com.que.notenest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.que.notenest.Activity.InsertNotesActivity;
import com.que.notenest.Adapter.NotesAdapter;
import com.que.notenest.ViewModel.NotesViewModel;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton ftb;
    RecyclerView notesRecyclerView;
    NotesViewModel notesViewModel;
    NotesAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ftb = findViewById(R.id.newNotesBtn);
        notesRecyclerView = findViewById(R.id.notesRecyclerView);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        ftb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InsertNotesActivity.class);
                startActivity(i);
            }
        });

        notesViewModel.getAllNotes.observe(this,notes -> {
            notesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new NotesAdapter(MainActivity.this,notes);
            notesRecyclerView.setAdapter(adapter);

        });
    }
}
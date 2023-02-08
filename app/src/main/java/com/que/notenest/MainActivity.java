package com.que.notenest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.que.notenest.Activity.InsertNotesActivity;
import com.que.notenest.Adapter.NotesAdapter;
import com.que.notenest.Model.Notes;
import com.que.notenest.ViewModel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton ftb;
    RecyclerView notesRecyclerView;
    NotesViewModel notesViewModel;
    NotesAdapter adapter;

    TextView noFilter, hightolow, lowtohigh;
    List<Notes> filterNotesAllList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ftb = findViewById(R.id.newNotesBtn);
        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        noFilter = findViewById(R.id.noFilter);
        hightolow = findViewById(R.id.high2low);
        lowtohigh = findViewById(R.id.low2high);


        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        noFilter.setOnClickListener(v -> {
            loadData(0);
            hightolow.setBackgroundResource(R.drawable.filter_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_shape);
            noFilter.setBackgroundResource(R.drawable.filter_selected_shape);
        });
        hightolow.setOnClickListener(v -> {
            loadData(1);
            hightolow.setBackgroundResource(R.drawable.filter_selected_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_shape);
            noFilter.setBackgroundResource(R.drawable.filter_shape);
        });
        lowtohigh.setOnClickListener(v -> {
            loadData(2);
            hightolow.setBackgroundResource(R.drawable.filter_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_selected_shape);
            noFilter.setBackgroundResource(R.drawable.filter_shape);
        });

        ftb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InsertNotesActivity.class);
                startActivity(i);
            }
        });

//        notesViewModel.lowtohigh.observe(this,notes -> {
//            notesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//            adapter = new NotesAdapter(MainActivity.this,notes);
//            notesRecyclerView.setAdapter(adapter);
//        });

        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                filterNotesAllList = notes;
            }
        });

    }

    private void loadData(int i) {
        if (i==0){
            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);

                }
            });
        } else if(i == 1){
            notesViewModel.hightolow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesAllList = notes;
                }
            });
        } else if(i == 2){
            notesViewModel.lowtohigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filterNotesAllList = notes;
                }
            });
        }

    }

    public void setAdapter(List<Notes> notes) {
        notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this, notes);
        notesRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_notes, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView =(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }




    // for search
    private void NotesFilter(String newText) {
        Log.e("@@@@", "NotesFilter: "+newText );

        ArrayList<Notes> FilterNames = new ArrayList<>();
        for (Notes notes:this.filterNotesAllList){
            if (notes.notesTitel.contains(newText) || notes.notesSubtitel.contains(newText)){
                FilterNames.add(notes);
            }
        }
        this.adapter.searchNotes(FilterNames);

    }



}
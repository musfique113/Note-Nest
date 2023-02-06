package com.que.notenest.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.que.notenest.Model.Notes;
import com.que.notenest.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getallNotes;

    }

    void insertNote(Notes notes){
        repository.insertNotes(notes);
    }

    void deletNote(int id){
        repository.deleteNotes(id);
    }

    void updateNote(Notes notes){
        repository.updateNotes(notes);
    }
}

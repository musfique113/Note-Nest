package com.que.notenest.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.que.notenest.Model.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Note_Database  ")
    LiveData<List<Notes>> getallNotes();

    //List<Notes> getallNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM Note_Database WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void uptadeNotes(Notes notes);

}

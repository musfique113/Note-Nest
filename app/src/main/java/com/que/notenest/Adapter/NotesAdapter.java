package com.que.notenest.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.que.notenest.Database.NotesDatabase;
import com.que.notenest.MainActivity;
import com.que.notenest.Model.Notes;
import com.que.notenest.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewholder> {

    MainActivity mainActivity;
    List<Notes> notes;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;

    }

    @NonNull
    @Override
    public notesViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new notesViewholder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.notesViewholder holder, int position) {

        Notes note = notes.get(position);
        if (note.notesPriority.equals("1")) {
            holder.notesPriority.setBackgroundResource(R.drawable.green);
        } else if (note.notesPriority.equals("2")) {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow);
        } else if (note.notesPriority.equals("3")) {
            holder.notesPriority.setBackgroundResource(R.drawable.red);
        }

        holder.title.setText(note.notesTitel);
        holder.subtitle.setText(note.notesSubtitel);
        holder.notesDate.setText(note.notesDate);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class notesViewholder extends RecyclerView.ViewHolder {

        TextView title, subtitle, notesDate;
        View notesPriority;

        public notesViewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notesTitle);
            subtitle = itemView.findViewById(R.id.notesSubtitle);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);
        }
    }
}

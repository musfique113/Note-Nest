package com.que.notenest.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.que.notenest.Model.Notes;
import com.que.notenest.R;
import com.que.notenest.ViewModel.NotesViewModel;
import com.que.notenest.databinding.ActivityInsertNotesBinding;
import com.que.notenest.databinding.ActivityUpdateNotesBinding;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    NotesViewModel notesViewModel;
    String priority = "1";
    String stitle,ssubtitle,snotes,spriority;
    int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        spriority = getIntent().getStringExtra("priority");
        iid = getIntent().getIntExtra("id",0);
        snotes = getIntent().getStringExtra("note");


        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upNotes.setText(snotes);

        if (spriority.equals("1")){
            binding.greenPriority.setImageResource(R.drawable.baseline_done_24);
        } else if (spriority.equals("2")){
            binding.yellowPriority.setImageResource(R.drawable.baseline_done_24);
        } else if(spriority.equals("3")){
            binding.redPriority.setImageResource(R.drawable.baseline_done_24);
        }
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.greenPriority.setOnClickListener(v ->{
            binding.greenPriority.setImageResource(R.drawable.baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            priority = "1";

        });

        binding.yellowPriority.setOnClickListener(v ->{
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.baseline_done_24);
            binding.redPriority.setImageResource(0);

            priority = "2";

        });

        binding.redPriority.setOnClickListener(v ->{
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.baseline_done_24);

            priority = "3";

        });

        binding.updateNotesBtn.setOnClickListener(v -> {

            String title = binding.upTitle.getText().toString();
            String subtitle = binding.upSubtitle.getText().toString();
            String notes = binding.upNotes.getText().toString();
            
            UpdateNotes(title , subtitle , notes);

        });


    }

    private void UpdateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());

        Notes updateNotes = new Notes();

        updateNotes.id = iid;
        updateNotes.notesTitel = title;
        updateNotes.notesSubtitel = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesPriority = priority;
        updateNotes.notesDate = sequence.toString();

        notesViewModel.updateNote(updateNotes);


        Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.icDelete){
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotesActivity.this,R.style.BottomSheetStyle);

            View view  = LayoutInflater.from(UpdateNotesActivity.this).inflate(R.layout.delet_bottom_sheet,
                    (LinearLayout)findViewById(R.id.deleteBottomSheet));
            sheetDialog.setContentView(view);

            TextView yes,no;
            yes = view.findViewById(R.id.deleteYes);
            no = view.findViewById(R.id.deleteNo);
            yes.setOnClickListener(v ->{
                notesViewModel.deletNote(iid);
                finish();

            });

            no.setOnClickListener(v ->{
                sheetDialog.dismiss();
            });


            sheetDialog.show();
        }
        return true;
    }
}
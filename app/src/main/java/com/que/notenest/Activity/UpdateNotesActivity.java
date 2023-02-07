package com.que.notenest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.que.notenest.R;
import com.que.notenest.ViewModel.NotesViewModel;
import com.que.notenest.databinding.ActivityInsertNotesBinding;
import com.que.notenest.databinding.ActivityUpdateNotesBinding;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    NotesViewModel notesViewModel;
    String priority = "1";
    String stitle,ssubtitle,snotes,spriority,sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        spriority = getIntent().getStringExtra("priority");
        sid = getIntent().getStringExtra("id");
        snotes = getIntent().getStringExtra("note");


        binding.upTitle.setText(stitle);
        binding.upSubtitle.setText(ssubtitle);
        binding.upNotes.setText(snotes);

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
        CharSequence sequence = DateFormat.format("MMMM d,YYYY",date.getTime());


        Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        finish();
    }
}
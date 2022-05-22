package com.example.afinal.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.afinal.R;
import com.example.afinal.Submissions;
import com.example.afinal.databinding.FragmentGalleryBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    EditText editEventName;
    EditText editEventDate;
    EditText editEventPrice;
    EditText editEventDescription;

    Button SubmitEvent;

    DatabaseReference reference;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        View myView = inflater.inflate(R.layout.fragment_gallery, container, false);

        editEventDate = myView.findViewById(R.id.editEventDate);
        editEventName = myView.findViewById(R.id.editEventName);
        editEventPrice = myView.findViewById(R.id.editEventPrice);
        editEventDescription = myView.findViewById(R.id.editEventDescription);

        reference = FirebaseDatabase.getInstance().getReference().child("Submissions");

        SubmitEvent = (Button) myView.findViewById(R.id.SubmitEvent);
        SubmitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertdata();
            }
        });


        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return myView;
    }

    private void insertdata() {
        String eventName = editEventName.getText().toString();
        String eventDate = editEventDate.getText().toString();
        String eventPrice = editEventPrice.getText().toString();
        String eventDescription = editEventDescription.getText().toString();

        Submissions submissions = new Submissions(eventName, eventDate, eventPrice, eventDescription);

        reference.push().setValue(submissions);
        Toast.makeText(getContext(), "Thank you! Your submission will be reviewed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.afinal.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.afinal.FoodDetail;
import com.example.afinal.LoginSuccess;
import com.example.afinal.MainActivity;
import com.example.afinal.R;
import com.example.afinal.User;
import com.example.afinal.databinding.FragmentSlideshowBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SlideshowFragment extends Fragment{

    private FragmentSlideshowBinding binding;

    private FirebaseUser user;
    DatabaseReference reference;

    private Button btnLogOut;

    TextView nameTextView;
    TextView emailTextView;
    TextView phoneTextView;
    private String userID;

    View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        View myView = inflater.inflate(R.layout.fragment_slideshow, container, false);

        final TextView nameTextView = (TextView) myView.findViewById(R.id.Name);
        final TextView emailTextView = (TextView) myView.findViewById(R.id.email);
        final TextView phoneTextView = (TextView) myView.findViewById(R.id.Phone);

        //Uses reference to Firebase Realtime Database User variable to output the current User details
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String Name = userProfile.name;
                    nameTextView.setText(Name);

                    String email = userProfile.email;
                    emailTextView.setText(email);

                    String Phone = userProfile.phone;
                    phoneTextView.setText(Phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        //Signs out user by signing out their firebase authentication instance and taking them to mainactivity
        btnLogOut = (Button) myView.findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Goodbye!", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                Context context = view.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return myView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

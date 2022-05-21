package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PartiesActivity extends AppCompatActivity {

    private FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;

    RecyclerView recyclerView;
    PartyAdapter partyAdapter;
    ArrayList<Parties> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parties);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Parties");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_parties);
        reference = FirebaseDatabase.getInstance().getReference("Parties");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        partyAdapter = new PartyAdapter(this,list);
        recyclerView.setAdapter(partyAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Parties parties = dataSnapshot.getValue(Parties.class);
                    list.add(parties);


                }
                partyAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
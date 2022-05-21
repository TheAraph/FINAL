package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FoodDetail extends AppCompatActivity {

    TextView menu_name, menu_price, menu_description;
    //ImageView food_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btncart;

    String categoryId = "";

    FirebaseDatabase database;
    DatabaseReference category;
    Category currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //Firebase

        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");

        btncart = (FloatingActionButton) findViewById(R.id.btncart);

        menu_description = (TextView) findViewById(R.id.menu_description);
        menu_name = (TextView) findViewById(R.id.menu_name);
        menu_price = (TextView) findViewById(R.id.menu_price);


        //food_image = (ImageView)findViewById(R.id.img_food);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);

        if (getIntent() != null)
            categoryId = getIntent().getStringExtra("CategoryId");
    }

    private void getDetailCategory(String categoryId) {
        category.child(categoryId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Category category = snapshot.getValue(Category.class);

                collapsingToolbarLayout.setTitle(category.getName());

                menu_price.setText(category.getPrice());
                menu_name.setText(category.getName());
                menu_description.setText(category.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
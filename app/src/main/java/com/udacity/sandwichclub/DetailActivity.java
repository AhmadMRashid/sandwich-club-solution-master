package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private ImageView img ;
    private Toolbar tb;
  //  private TextView name;
    private TextView known;
    private TextView origin;
    private TextView description;
    Sandwich sandwich;
    private TextView ingredients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // floating Action Button (extra feature with snackbar)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Scroll to read more!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        tb = findViewById(R.id.toolbar);
        img = findViewById(R.id.image_iv);
        //name = findViewById(R.id.sandwich_name);
        known = findViewById(R.id.also_known_tv);
        origin = findViewById(R.id.origin_tv);
        description = findViewById(R.id.description_tv);
        ingredients = findViewById(R.id.ingredients_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(img);

        setTitle(sandwich.getMainName());
        populateUI();

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        tb.setTitle(sandwich.getMainName());
        description.setText(sandwich.getDescription());
        origin.setText(sandwich.getPlaceOfOrigin());
        for (String knownitem : sandwich.getAlsoKnownAs()){
            known.append(knownitem + "\n");
        }

        for (String ing : sandwich.getIngredients()){
            ingredients.append(ing + ", ");
        }
    }



    }



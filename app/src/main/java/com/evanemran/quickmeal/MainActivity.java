package com.evanemran.quickmeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.evanemran.quickmeal.Adapters.RandomMealAdapter;
import com.evanemran.quickmeal.Listeners.CustomOnClickListener;
import com.evanemran.quickmeal.Listeners.RandomAPIResponseListener;
import com.evanemran.quickmeal.Models.RandomRecipe;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RequestManager manager;
    RandomMealAdapter adapter;
//    ProgressDialog dialog;
    RecyclerView recyclerView;
    List<String> tags = new ArrayList<>();
    Spinner spinner;
    SearchView searchView_home;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_random);
        spinner = findViewById(R.id.spinner_tags);
        searchView_home = findViewById(R.id.searchView_home);
        progressBar = (ProgressBar)findViewById(R.id.loader);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
        manager = new RequestManager(this);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        searchView_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "Will be added soon!", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



//        tags.add("dessert");
//        dialog = new ProgressDialog(this);
//        dialog.setTitle("Loading...");

//        manager.GetRandomRecipes(listener, tags);
//        dialog.show();

    }

    private final RandomAPIResponseListener listener = new RandomAPIResponseListener() {
        @Override
        public void didFetch(List<RandomRecipe> responses, String message) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
            adapter = new RandomMealAdapter(MainActivity.this, responses, customOnClickListener);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void didError(String message) {
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString().toLowerCase());
            manager.GetRandomRecipes(listener, tags);
            recyclerView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private final CustomOnClickListener customOnClickListener = new CustomOnClickListener() {
        @Override
        public void onClick(String text) {
            startActivity(new Intent(MainActivity.this, RecipeDetailActivity.class)
            .putExtra("id", text));
        }
    };


}
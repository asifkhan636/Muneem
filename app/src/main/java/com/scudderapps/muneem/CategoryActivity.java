package com.scudderapps.muneem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scudderapps.muneem.Adapter.CategoryAdapter;
import com.scudderapps.muneem.Dialogs.AddCategoryDialog;
import com.scudderapps.muneem.Model.CategoryData;
import com.scudderapps.muneem.ViewModels.CategoryViewModel;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView categoryView;
    CategoryAdapter categoryAdapter;
    FloatingActionButton addCategory;
    private CategoryViewModel categoryViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        categoryView = findViewById(R.id.category_view);
        addCategory = findViewById(R.id.addCategory);

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCategoryDialog addCategoryDialog = new AddCategoryDialog(CategoryActivity.this);
                addCategoryDialog.show(getSupportFragmentManager(), "Add Category");
            }
        });

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryViewModel.getAllCategory().observe(this, new Observer<List<CategoryData>>() {
            @Override
            public void onChanged(@Nullable List<CategoryData> categoryData) {
                //setting up the data in recycler view.
                categoryView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                categoryAdapter = new CategoryAdapter();
                categoryView.setAdapter(categoryAdapter);
                categoryAdapter.setCategoryList(categoryData);
            }
        });
    }
}

package com.vlad.lesson4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vlad.lesson4.adapter.RVAdapterCategoryHelp;
import com.vlad.lesson4.model.ItemForChooseCategoryHelp;

import java.util.ArrayList;

public class HelpCategoriesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<ItemForChooseCategoryHelp> listItems;

    public final static int TWO = 2;
    public final static String EMPTY = " ";

    public static Intent createStartIntent(Context context) {
        return new Intent(context, HelpCategoriesActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_categories);

        toolbar = findViewById(R.id.include);
        recyclerView = findViewById(R.id.recyclerViewChooseCategoryHelp);
        toolbar.setTitle(EMPTY);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, TWO);

        final RVAdapterCategoryHelp rvAdapterCategoryHelp = new RVAdapterCategoryHelp(initializeItemsForChooseCategory());

        recyclerView.setLayoutManager(gridLayoutManager);

        rvAdapterCategoryHelp.notifyDataSetChanged();
        recyclerView.setAdapter(rvAdapterCategoryHelp);
        rvAdapterCategoryHelp.setOnItemClickListener(new RVAdapterCategoryHelp.OnItemClickListener() {
            @Override
            public void onClickItem(ItemForChooseCategoryHelp item) {
            }
        });


    }


    private ArrayList<ItemForChooseCategoryHelp> initializeItemsForChooseCategory() {

        listItems = new ArrayList<>();

        listItems.add(new ItemForChooseCategoryHelp(R.drawable.child_category, getString(R.string.child_category)));
        listItems.add(new ItemForChooseCategoryHelp(R.drawable.adults_category, getString(R.string.adults_category)));
        listItems.add(new ItemForChooseCategoryHelp(R.drawable.seniors_category, getString(R.string.seniors_category)));
        listItems.add(new ItemForChooseCategoryHelp(R.drawable.animals_category, getString(R.string.animals_category)));
        listItems.add(new ItemForChooseCategoryHelp(R.drawable.events_category, getString(R.string.events_category)));

        return listItems;
    }
}

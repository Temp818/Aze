package com.example.recyclerviewselection;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StableIdKeyProvider;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private SelectionTracker<Long> tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("Jean", "Toto"));
        persons.add(new Person("Michel", "Pol"));
        persons.add(new Person("Nicol", "Albert"));
        persons.add(new Person("Pol", "Jacques"));
        persons.add(new Person("Didier", "Martin"));
        persons.add(new Person("Christine", "Lou"));
        persons.add(new Person("Louise", "Walv"));
        persons.add(new Person("Monique", "Deni"));
        persons.add(new Person("Marine", "Tuche"));

        adapter = new Adapter(persons);
        recyclerView.setAdapter(adapter);

        tracker = new SelectionTracker.Builder<>(
                "mySelection",
                recyclerView,
                new StableIdKeyProvider(recyclerView),
                new MyItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage())
                .withSelectionPredicate(SelectionPredicates.createSelectAnything())
                .build();
        adapter.setTracker(tracker);
    }
}
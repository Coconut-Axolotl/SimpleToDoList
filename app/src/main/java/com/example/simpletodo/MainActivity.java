package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;

   Button btnAdd;
   EditText Etitem;
   RecyclerView rvitems;
   ItemsAdapter itemsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        Etitem = findViewById(R.id.Etitem);
        rvitems = findViewById(R.id.rvitems);


        items = new ArrayList<>();
        items.add("Buy Milk");
        items.add("Go to the Gym");
        items.add("Have Fun");

        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener(){
            @Override
            public void onItemLongClicked(int position) {
                // Delete item
                items.remove(position);
                // Notify the position
                itemsAdapter.notifyItemRemoved(position);
                // Debug Remove Message
                Toast.makeText(getApplicationContext(), "Item Successfully removed", Toast.LENGTH_SHORT).show();

            }
        };
        itemsAdapter  = new ItemsAdapter(items, onLongClickListener);
        rvitems.setAdapter(itemsAdapter);
        rvitems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = Etitem.getText().toString();
                // Add item to the model
                items.add(todoItem);
                // Notify the adapter that an item is inserted
                itemsAdapter.notifyItemInserted(items.size() - 1);
                Etitem.setText("");
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private File getDataFile() {
        return new  File(getFilesDir(), "data.txt");

    }

}
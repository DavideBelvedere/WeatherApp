package com.example.davidebelvedere.weatherapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private MyRecyclerAdapter customAdapter;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utility.initDataSource(MainActivity.this);
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);


        customAdapter = new MyRecyclerAdapter(Utility.getDataSourceItemList(), this);

        myRecyclerView.setAdapter(customAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        customAdapter.updateData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add: {
                final EditText newCityName = new EditText(MainActivity.this);

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Aggiungi Città").setMessage("Scrivi il nome della città da aggiungere").setView(newCityName)
                        .setPositiveButton("Aggiungi", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Utility.addItem(new City(String.valueOf(newCityName.getText())));
                                customAdapter.updateData();

                            }
                        }).setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}

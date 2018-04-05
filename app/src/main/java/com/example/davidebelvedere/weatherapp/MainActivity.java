package com.example.davidebelvedere.weatherapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Utility.REQUEST_CODE);

        } else {
            Utility.getDataSourceItemList().get(0).setNome(Utility.getCurrentCityName(MainActivity.this));
            customAdapter.notifyItemChanged(0);
        }

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
                                customAdapter.notifyItemInserted(Utility.getCount());

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == Utility.REQUEST_CODE) {
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
               Utility.getDataSourceItemList().get(0).setNome(Utility.getCurrentCityName(MainActivity.this));
               customAdapter.notifyItemChanged(0);
            } else {
                Toast.makeText(this, "Non ho il permesso per accedere alla tua posizione", Toast.LENGTH_SHORT).show();
            }
        }
    }


}

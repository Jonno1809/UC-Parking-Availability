package com.jonno1809.ucparkingavailability;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class CarParkDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_park_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        CarPark carPark = intent.getParcelableExtra("carPark");

        TextView tvName = findViewById(R.id.tvName);
        TextView tvFree = findViewById(R.id.tvFree);
        TextView tvOccupied = findViewById(R.id.tvOccupied);
        TextView tvType = findViewById(R.id.tvType);
        TextView tvCapacity = findViewById(R.id.tvCapacity);

        tvName.setText(getString(R.string.car_park_name, carPark.getName()));
        tvFree.setText(getString(R.string.car_park_free, carPark.getFree()));
        tvOccupied.setText(getString(R.string.car_park_occupied, carPark.getOccupied()));
        tvType.setText(getString(R.string.car_park_type, carPark.getType()));
        tvCapacity.setText(getString(R.string.car_park_capactity, carPark.getCapacity()));
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

}

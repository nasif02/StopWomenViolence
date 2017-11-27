package com.ewu.bug.swv.hive.mysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ewu.bug.swv.R;

public class MainScreenActivity extends AppCompatActivity {

    Button btnViewProducts;
    Button btnNewProduct;
    Button btnLab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Buttons
        btnViewProducts = (Button) findViewById(R.id.btnViewProducts);
        btnNewProduct = (Button) findViewById(R.id.btnCreateProduct);
        btnLab = (Button) findViewById(R.id.btnLab);

        // view products click event
        btnViewProducts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
                startActivity(i);

            }
        });


        // view products click event
        btnNewProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching create new product activity
                Intent i = new Intent(getApplicationContext(), NewProductActivity.class);
                startActivity(i);

            }
        });


        btnLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }
}

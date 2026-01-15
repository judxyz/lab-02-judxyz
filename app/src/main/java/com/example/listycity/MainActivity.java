package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // declare the variables to reference later
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button button_add;
    Button button_del;
    EditText edittext;
    Button button_submit;

    String inputtext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        button_add = findViewById(R.id.button_add);
        button_del = findViewById(R.id.button_delete);
        edittext = findViewById(R.id.edittext);
        button_submit = findViewById(R.id.button_submit);
        inputtext = edittext.getText().toString();


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext.setVisibility(View.VISIBLE);
                button_submit.setVisibility(View.VISIBLE);
            }
        });

        button_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // listen for button click in cityList
                cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dataList.remove(position);
                        cityAdapter.notifyDataSetChanged();
                        cityList.setOnItemClickListener(null);
                        Toast.makeText(MainActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();

                    }
                });

            }

        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Submit Button Clicked", Toast.LENGTH_SHORT).show();
                edittext.setVisibility(View.GONE);
                button_submit.setVisibility(View.GONE);
                inputtext = edittext.getText().toString();
                dataList.add(inputtext);

            }
        });






    }
}
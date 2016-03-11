package com.example.android.lab02_interactive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView quautityTextView;
    private TextView priceTextView;
    private int number = 0;
    private int price = 5;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                                R.array.fruit,
                                                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(listener);
    }

    public void submitOrder(View view){
        switch (view.getId()){
            case R.id.order:
                display(Integer.parseInt(quautityTextView.getText().toString()));
                break;
            case  R.id.cancel:
                cancel();
                break;
        }

    }


    public void countCale(View view){
        switch (view.getId()){
            case R.id.add:
                number++;
                break;
            case R.id.remove:
                number--;
                break;
        }
        quautityTextView.setText(String.valueOf(number));
    }


    private void findViews(){
        quautityTextView = (TextView)findViewById(R.id.quantity_text_view);
        priceTextView = (TextView)findViewById(R.id.price_text_view);
        spinner = (Spinner)findViewById(R.id.spinner);
    }

    AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position == 0){
                price = 10;
            }else if (position == 1){
                price = 20;
            }else {
                price = 30;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void display(int number) {
        int total = price * number;
        String myString = NumberFormat.getCurrencyInstance(Locale.TAIWAN).format(total);
        priceTextView.setText(myString);
    }

    private void cancel(){
        String myString = "NT$0.00";
        quautityTextView.setText(String.valueOf(0));
        priceTextView.setText(myString);
    }
}

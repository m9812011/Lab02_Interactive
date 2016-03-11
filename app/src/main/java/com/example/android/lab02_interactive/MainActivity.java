package com.example.android.lab02_interactive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView quautityTextView;
    private TextView priceTextView;
    private int number = 0;
    private int price = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    public void submitOrder(View view){
        display(Integer.parseInt(quautityTextView.getText().toString()));
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
    }

    private void display(int number) {
        int total = price * number;
        String myString = NumberFormat.getCurrencyInstance(Locale.TAIWAN).format(total);
        priceTextView.setText(myString);
    }
}

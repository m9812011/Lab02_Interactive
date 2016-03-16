package com.example.android.lab02_interactive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView quautityTextView;
    private TextView priceTextView;
    private Button orderButton;
    private int mNumber = 0;
    private int mPrice = 10;
    private final String mNT$= "NT$";
    private StringBuilder mTotalPriceMessage = new StringBuilder(mNT$);//"NT$0"也可以(動態)
//    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.fruit,
//                android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(listener);
    }

    public void submit(View view){
        switch (view.getId()){
            case R.id.order:
                buttonSwitch(view);
                display();
                break;
            case  R.id.cancel:
                cancel();
                break;
        }

    }

    public void countCale(View view){
        switch (view.getId()){
            case R.id.add:
                    ++mNumber;
                break;
            case R.id.remove:
                if(mNumber > 0) {
                    --mNumber;
                }
                break;
        }
        buttonSwitch(view);
        display();

    }

    private void findViews(){
        quautityTextView = (TextView)findViewById(R.id.quantity_text_view);
        priceTextView = (TextView)findViewById(R.id.price_text_view);
        orderButton = (Button)findViewById(R.id.order);
//        spinner = (Spinner)findViewById(R.id.spinner);
    }

    AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position == 0){
                mPrice = 10;
            }else if (position == 1){
                mPrice = 20;
            }else {
                mPrice = 30;
            }
            display();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void display() {
        int total = mPrice * mNumber;
//        String mPriceFormat = NumberFormat.getCurrencyInstance(Locale.TAIWAN).format(total);
//        StringBuilder mPriceMessage =  new StringBuilder();
//        mPriceMessage.append(mPriceFormat).append(mNumber == 0 ? "\nFree" : "\nThank you");

        /***/
        int startIndex = mNT$.length();//計算要從第幾個開始刪除(包含)
        int endIndex = mTotalPriceMessage.length();//計算要刪除到哪一個字(不包含)
        mTotalPriceMessage.delete(startIndex,endIndex).append(total).append(mNumber == 0 ? "\nFree" : "\nThank you");

        quautityTextView.setText(String.valueOf(mNumber));
        priceTextView.setText(mTotalPriceMessage);


    }

    private void cancel() {
        mNumber = 0;
        quautityTextView.setText(String.valueOf(mNumber));
        priceTextView.setText("NT$0.00\nFree");
    }

    //控制orderButton避免連續點擊產生多餘的String
    private void buttonSwitch(View view){
        switch (view.getId()){
            case R.id.add:
            case R.id.remove:
                //orderButton.setVisibility(View.VISIBLE);
                orderButton.setEnabled(true);
                Log.i("MainActivity","啟動 Order Button");
                break;
            case R.id.order:
                //orderButton.setVisibility(View.INVISIBLE);
                orderButton.setEnabled(false);
                Log.i("MainActivity","關閉 Order Button");
                break;

        }
    }
}

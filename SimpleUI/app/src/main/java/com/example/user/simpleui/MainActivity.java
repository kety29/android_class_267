package com.example.user.simpleui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_CODE_DRINK_MENU_ACTIVITY=0;
    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    //CheckBox checkBox;
    ListView listView;
    Spinner storeSpinner;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ArrayList<Order> orders=new ArrayList<>();
    String drinkName="black tea";
    String menuResults = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.d("Debug", "SimpleUI");
        textView= (TextView) findViewById(R.id.textView);
        textView.setText("Hello TextView");
        editText=(EditText) findViewById(R.id.editText);
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        listView=(ListView) findViewById(R.id.listView);
        storeSpinner=(Spinner) findViewById(R.id.spinner);
        sharedPreferences=getSharedPreferences("setting", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        setupOrdersData();
        setupListView();
        setupSpinner();

        editText.setText(sharedPreferences.getString("editText", ""));
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String text = editText.getText().toString();
                editor.putString("editText", text);
                editor.apply();

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    click(v);
                    return true;
                }
                return false;
            }
        });

        textView.setText(sharedPreferences.getString("textView", ""));
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString("textView", s.toString());
                editor.apply();
            }
        });

        Log.d("Debug","Main Activity OnCreate");
    }

    void setupListView(){

        OrderAdapter adapter=new OrderAdapter(this,orders);
        listView.setAdapter(adapter);
    }

    void setupSpinner(){
        String[] data=getResources().getStringArray(R.array.storeInfo);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,data);
        storeSpinner.setAdapter(adapter);
    }
    void setupOrdersData(){
        String content=Utils.readFile(this,"history");
        String[] datas = content.split("\n");
        for(int i=0;i<datas.length;i++){
            Order order=Order.newInstanceWithData(datas[i]);
            if(order!=null){
                orders.add(order);
            }
        }
    }

    public void click(View view)
    {
        String note=editText.getText().toString();
        Order order=new Order();
        order.note=note;
        order.menuResults=menuResults;
        order.storeInfo=(String)storeSpinner.getSelectedItem();
        orders.add(order);
        Utils.writeFile(this, "history", order.getJsonObject().toString());
        textView.setText(note);
        editText.setText("");
        setupListView();
    }

    public void goToMenu(View view){
        Intent intent=new Intent();
        intent.setClass(this, DrinkMenuActivity.class);
        startActivityForResult(intent, REQUEST_CODE_DRINK_MENU_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE_DRINK_MENU_ACTIVITY){
            if(resultCode==RESULT_OK){
                menuResults=data.getStringExtra("results");
              textView.setText(data.getStringExtra("results"));
                Toast.makeText(this,"完成訂購",Toast.LENGTH_LONG).show();
            }
            else{
                if(requestCode==RESULT_CANCELED){
                    Toast.makeText(this,"未完成訂購",Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("Debug", "Main Activity OnStart");
    }

    @Override
     protected void onStop() {
        super.onStop();
        Log.d("Debug", "Main Activity OnStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "Main Activity onPause");
    }

    @Override
     protected void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "Main Activity OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "Main Activity onRestart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "Main Activity onResume");
    }
}

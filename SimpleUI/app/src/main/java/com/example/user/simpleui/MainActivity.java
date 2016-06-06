package com.example.user.simpleui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    RadioGroup radioGroup;
    CheckBox checkBox;
    ListView listView;
    ArrayList<String> drinks=new ArrayList<>();
//    String selectedSex="Male";
//    String name="";
//    String sex="";
    String drinkName="black tea";

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

        setupListView();



        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN){
                    click(v);
                    return true;
                }
                return false;
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == R.id.maleRadioButton) {
//                    selectedSex = "Male";
//                } else if (checkedId == R.id.femaleRadioButton) {
//                    selectedSex = "Female";
//                }
                //依選擇顯示文字
                RadioButton radioButton=(RadioButton)findViewById(checkedId);
                drinkName=radioButton.getText().toString();
            }
        });

//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//             changTextView();
//            }
//        });
    }

    void setupListView(){
//        String[] data=new String[]{"1","2","3","4","5","6"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,drinks);
        listView.setAdapter(adapter);
    }

    public void click(View view)
    {
        String note=editText.getText().toString();
//        sex=selectedSex;
//        changTextView();

        drinks.add(drinkName);
        textView.setText(drinkName);
        editText.setText("");
        setupListView();
    }

//    public void changTextView(){
//        if(name.equals(""))
//            return;
//        if(checkBox.isChecked()){
//            textView.setText(name);
//        }else{
//            String content=name+" sex: "+sex;
//            textView.setText(content);
//        }
//
//    }

}

package com.example.user.simpleui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DrinkMenuActivity extends AppCompatActivity implements DrinkOrderDialog.OnFragmentInteractionListener{
    ListView drinkListView;
    TextView priceTextView;

    ArrayList<Drink> drinks=new ArrayList<>();
    ArrayList<Drink> drinkOrders=new ArrayList<>();

    //SET DATA
    String[] names={"冬瓜紅茶","玫瑰鹽奶蓋紅茶","珍珠紅茶拿鐵","紅茶拿鐵"};
    int[] mPrices={25,35,45,35};
    int[] lPrinces={35,45,55,45};
    int[] imageId={R.drawable.drink1,R.drawable.drink2,R.drawable.drink3,R.drawable.drink4};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu);
        setData();//將資料放入Array <drinks>

        drinkListView=(ListView)findViewById(R.id.drinkListView);
        priceTextView=(TextView)findViewById(R.id.priceTextView);
        setupListView();//將drinks放入adapter
        drinkListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                DrinkAdapter drinkAdapter=(DrinkAdapter)parent.getAdapter();
//                Drink drink=(Drink)drinkAdapter.getItem(position);
//                drinkOrders.add(drink);
//                updateTototalPrice();
                Drink drink=(Drink)parent.getAdapter().getItem(position);
                ShowDetailDrinkMenu(drink);

            }
        });


        Log.d("Debug", "Drink Menu Activity onCreate");
    }

    private void ShowDetailDrinkMenu(Drink drink){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        //DrinkOrderDialog orderDialog=DrinkOrderDialog.newInstance("", "");//new
        DrinkOrder drinkOrder=new DrinkOrder();
        drinkOrder.mPrice=drink.mPrice;
        drinkOrder.lPrice=drink.lPrince;
        drinkOrder.drinkName=drink.name;
        DrinkOrderDialog orderDialog=DrinkOrderDialog.newInstance(drinkOrder);
        orderDialog.show(ft,"DrinkOrderDialog");
//        ft.replace(R.id.root,orderDialog);
//        ft.addToBackStack(null);
//        ft.commit();

    }

    private void updateTototalPrice(){
        int total=0;
        for(Drink drink:drinkOrders){
            total+=drink.mPrice;
        }
        priceTextView.setText(String.valueOf(total));
    }

    private void setData(){
        for(int i=0;i<4;i++){
            Drink drink=new Drink();
            drink.name=names[i];
            drink.mPrice=mPrices[i];
            drink.lPrince=lPrinces[i];
            drink.imageId=imageId[i];
            drinks.add(drink);
        }
    }
    private void setupListView(){
        DrinkAdapter adapter=new DrinkAdapter(this,drinks);
        drinkListView.setAdapter(adapter);
    }

    public void done(View view){
        Intent intent=new Intent();
        JSONArray array=new JSONArray();
        for(Drink drink:drinkOrders){
            JSONObject object=drink.getData();
            array.put(object);
        }
        intent.putExtra("results",array.toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View view){
        Intent intent=new Intent();
        //intent.putExtra("results", "未訂購完成");
        setResult(RESULT_CANCELED, intent);
        finish();
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d("Debug", "Drink Menu Activity OnStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Debug", "Drink Menu Activity OnStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "Drink Menu Activity onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Debug", "Drink Menu Activity OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "Drink Menu Activity onRestart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "Drink Menu Activity onResume");
    }
}

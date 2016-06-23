package com.example.user.simpleui;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by user on 2016/6/6.
 */

@ParseClassName("Order")
public class Order extends ParseObject{
//    String note;
//    String menuResults;
//    String storeInfo;



    public String getNote(){return getString("note");}
    public void setNote(String note){put("note",note);}

    public String getmenuResults(){
        String menuResults=getString("menuResults");
        if(menuResults==null){
            menuResults="";
        }
        return menuResults;
    }
    public void setmenuResults(String menuResults){put("menuResults",menuResults);}

    public String getstoreInfo(){return getString("storeInfo");}
    public void setstoreInfo(String storeInfo){put("storeInfo",storeInfo);}

    public static ParseQuery<Order> getQuery(){return ParseQuery.getQuery(Order.class);}

    public static void getOrdersFromRemote(final FindCallback<Order> callback){
        getQuery().findInBackground(new FindCallback<Order>() {
            @Override
            public void done(List<Order> objects, ParseException e) {
                if(e==null){
                    ParseObject.pinAllInBackground(objects);
                }
                callback.done(objects,e);
            }
        });
    }

    public JSONObject getJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("note", getNote());
            jsonObject.put("menuResults", getmenuResults());
            jsonObject.put("storeInfo",getstoreInfo());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static Order newInstanceWithData(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            Order order = new Order();
            order.setNote("note");
            order.setstoreInfo("storeInfo");
            order.setmenuResults("menuResults");
            return order;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
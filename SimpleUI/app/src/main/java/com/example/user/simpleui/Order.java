package com.example.user.simpleui;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2016/6/6.
 */
public class Order {
    String note;
    String menuResults;
    String storeInfo;

    public JSONObject getJsonObject(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("note",note);
            jsonObject.put("menuResults",menuResults);
            jsonObject.put("storeInfo",storeInfo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static Order newInstanceWithData(String data){
        JSONObject jsonObject=new JSONObject();
        Order order=new Order();
        try {
            order.note=jsonObject.getString("note");
            order.menuResults=jsonObject.getString("menuResults");
            order.storeInfo=jsonObject.getString("storeInfo");
            return order;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.example.jsondemo;

import android.util.Log;

import com.google.gson.Gson;

/**
 * Created by 阳瑞 on 2017/3/5.
 */
public class Jsoonutil2 {
    public void pares(String JsonData2)
    {

        Gson gson = new Gson();
        user user = gson.fromJson(JsonData2, user.class);
        Log.i("Json", "name = "+ user.getName());
        Log.i("Json", "age = "+ user.getAge());
    }
}

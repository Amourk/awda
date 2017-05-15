package com.example.jsondemo;

import android.util.JsonReader;
import android.util.Log;

import java.io.StringReader;

/**
 * Created by 阳瑞 on 2017/3/4.
 */
public class JsonUtil {
    public void parseJson(String JsonData) {
        try {
            JsonReader reader = new JsonReader(new StringReader(JsonData));
//            reader.beginArray();
            while (reader.hasNext())
            {
                reader.beginObject();
                while (reader.hasNext())
                {
                    String data = reader.nextName();
                    Log.i("Json", "data = "+ data);
                    if (data.equals("name"))
                    {
//                        Log.i("Json", "name = "+ reader.nextString());
                    }
                      else  if (data.equals("age"))
                    {
//                        Log.i("Json","age = "+reader.nextInt());
                    }
                }
                reader.endObject();
            }
//            reader.endArray();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

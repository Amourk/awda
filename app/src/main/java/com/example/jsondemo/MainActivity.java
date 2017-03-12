package com.example.jsondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView textView;
    private Button button;
    private String JsonData = "[{\"name\":\"kimi\",\"age\":20},{\"name\":\"jos\",\"age\":18}]";
    private String JsonData2 = "{\"name\":\"kimi\",\"age\":20}";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
    }
    public void doClick(View view)
    {
        switch (view.getId())
        {
            case R.id.button:
//                JsonUtil jsonUtil= new JsonUtil();
//                jsonUtil.parseJson(JsonData);
               Jsoonutil2 jsoonutil2 = new Jsoonutil2();
                jsoonutil2.pares(JsonData2);

                break;
        }

    }
}

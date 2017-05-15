package com.example.jsondemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String msg1;//接收到的服务回馈消息
    private ImageView imageView;
    public TextView textView, Conten;
    private Button button, get, post, postjson,next,shang;
    private String JsonData = "[{\"name\":\"kimi\",\"age\":20},{\"name\":\"jos\",\"age\":18}]";
    private String JsonData2 = "{\"name\":\"kimi\",\"age\":20}";
//    private String JsonData3 =

    private String url = "http://192.168.0.126:8080/Login/login?username=Amour&password=123";
    private String url1 = "http://192.168.0.116:8080/TotemDown/LoginServe?username=linyuanbin&password=123456";
    private String url2 = "http://192.168.0.18:8080/android_Server/LoginServlet?username=lin&password=123";
    public  ArrayList<String> ima111 = new ArrayList<>();
    public static Bitmap[] tup = new Bitmap[10];
    public static Bitmap[] bigtup = null;
    public String[] image = new String[10];
    public  ArrayList<Bitmap> tupp = new ArrayList<>();

    private int i = 4;

    ImageLoader imageLoader;

    OkHttpClient okHttpClient = new OkHttpClient();


    Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    System.out.println("111");
//                    tup = (Bitmap[]) msg.obj;
                    tupp = (ArrayList<Bitmap>) msg.obj;
                    for (int k = 0 ; k <tupp.size(); k++)
                    {
                        Log.i("ccccc", "tupp =  "+ tupp.get(k));
                    }
                    imageView.setImageBitmap(tupp.get(i));
                    break;
                case 1:
//                    i++;
                    tupp = (ArrayList<Bitmap>) msg.obj;
                    for (int k = 0 ; k <tupp.size(); k++)
                    {
                        Log.i("dddddd", "tupp =  "+ tupp.get(k));
                    }
                    i++;
                    Log.i("iiiii", "i2 =  " + i);
//                    imageView.setImageBitmap(tupp.get(i));
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        button = (Button) findViewById(R.id.button);
        get = (Button) findViewById(R.id.get);
        Conten = (TextView) findViewById(R.id.content);
        post = (Button) findViewById(R.id.post);
        postjson = (Button) findViewById(R.id.postjson);
        imageView = (ImageView) findViewById(R.id.ima);
        next = (Button) findViewById(R.id.next);
        shang = (Button) findViewById(R.id.shang);


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);


//        try {
//            flash();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        try {
            flash1();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void flash() throws UnsupportedEncodingException {
        String name1 = "request";

        String key = "{\"state\":\""+name1+"\"}";
        URLDecoder.decode(key, "utf-8");
        RequestBody requestBody1 = RequestBody
                .create(MediaType.parse("text/plain; charset=utf-8"), key);
        Request.Builder builder3 = new Request.Builder();
        Request request2 = builder3
                .url(url1)
                .post(requestBody1)
                .build();

        okhttp3.Call call1 = okHttpClient.newCall(request2);
        call1.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("info", " GET请求失败！！！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String res = response.body().string();
                Log.i("infoo", " GET请求成功！！！");

                Log.i("infoo", "res = " + res);


                Type listType = new TypeToken<ArrayList<Picture>>() {
                }.getType();

                ArrayList<Picture> foos = new Gson().fromJson(res, listType);
                for (int i = 0; i < foos.size(); i++) {
//                    image[i] = foos.get(i).getPAddress();
                    ima111.add(foos.get(i).getPAddress());
                    System.out.println("name [" + i + "] = " + foos.get(i).getPAddress());
                }
                for (int j = 0; j < ima111.size(); j++) {
                    Log.i("info111", "ima =  " + ima111.get(j));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DisplayImageOptions options = new DisplayImageOptions.Builder()
                                .delayBeforeLoading(1000)
                                .resetViewBeforeLoading(true)  // default
                                .cacheInMemory(true) // default => false
                                .cacheOnDisk(true) // default => false
                                .build();

                        imageLoader.displayImage(ima111.get(i), imageView, options, new ImageLoadingListener() {
                            @Override
                            public void onLoadingStarted(String imageUri, View view) {
//                        Toast.makeText(getApplicationContext(), "Loading Started", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                        Toast.makeText(getApplicationContext(), "Loading Failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                        Toast.makeText(getApplicationContext(), "Loading Complete", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLoadingCancelled(String imageUri, View view) {
//                        Toast.makeText(getApplicationContext(), "Loading Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        }



    public void flash1() throws UnsupportedEncodingException {
        String name1 = "request";

        String key = "{\"state\":\""+name1+"\"}";
        URLDecoder.decode(key, "utf-8");
        RequestBody requestBody1 = RequestBody
                .create(MediaType.parse("text/plain; charset=utf-8"), key);
        Request.Builder builder3 = new Request.Builder();
        Request request2 = builder3
                .url(url1)
                .post(requestBody1)
                .build();

        okhttp3.Call call1 = okHttpClient.newCall(request2);
        call1.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("info", " GET请求失败！！！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String res = response.body().string();
                Log.i("infoo", " GET请求成功！！！");

                Log.i("infoo", "res = " + res);


                Type listType = new TypeToken<ArrayList<Picture>>(){}.getType();

                ArrayList<Picture> foos = new Gson().fromJson(res, listType);
//                String newIma = foos.get(0).getPAddress();
//
//                for (int j = 0 ; j < ima111.size() ; j++ )
//                {
//                    Log.i("AAAAA", "ima =  " +ima111.get(j));
//                }
//
//                ima111.add(9,newIma);
//                ima111.remove(0);
//
//                for (int j = 0 ; j < ima111.size() ; j++ )
//                {
//                    Log.i("BBBBBB", "ima =  " +ima111.get(j));
//                }
//
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                                .delayBeforeLoading(1000)
//                                .resetViewBeforeLoading(true)  // default
//                                .cacheInMemory(true) // default => false
//                                .cacheOnDisk(true) // default => false
//                                .build();
//
//                        imageLoader.displayImage(ima111.get(i), imageView, options, new ImageLoadingListener() {
//                            @Override
//                            public void onLoadingStarted(String imageUri, View view) {
////                        Toast.makeText(getApplicationContext(), "Loading Started", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
////                        Toast.makeText(getApplicationContext(), "Loading Failed", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
////                        Toast.makeText(getApplicationContext(), "Loading Complete", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onLoadingCancelled(String imageUri, View view) {
////                        Toast.makeText(getApplicationContext(), "Loading Cancelled", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });



                for (int i = 0; i < foos.size(); i++) {
//                    image[i] = foos.get(i).getPAddress();
                    ima111.add(foos.get(i).getPAddress());
                    System.out.println("name ["+ i +"] = " + foos.get(i).getPAddress());
                }
                for (int j = 0 ; j < ima111.size() ; j++ )
                {
                    Log.i("info111", "ima =  " +ima111.get(j));
                }
                Thread mThread = new Thread() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        for (int i = 0; i < ima111.size(); i++) {
                            msg1 = ima111.get(i);
                            Bitmap bmp = getURLimage(msg1);
//                            tup[i] = bmp;
                            tupp.add(bmp);
                            msg.what = 0;
                            msg.obj = tupp;
                            System.out.println("000");
                        }
                        handle.sendMessage(msg); //新建线程加载图片信息，发送到消息队列中
                    }
                };
                mThread.start();
            }
        });
    }
    public void flash2() throws UnsupportedEncodingException {
        String name1 = "request";

        String key = "{\"state\":\""+name1+"\"}";
        URLDecoder.decode(key, "utf-8");
        RequestBody requestBody1 = RequestBody
                .create(MediaType.parse("text/plain; charset=utf-8"), key);
        Request.Builder builder3 = new Request.Builder();
        Request request2 = builder3
                .url(url1)
                .post(requestBody1)
                .build();

        okhttp3.Call call1 = okHttpClient.newCall(request2);
        call1.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("info", " GET请求失败！！！");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String res = response.body().string();
                Log.i("infoo", " GET请求成功！！！");

                Log.i("infoo", "res = " + res);


                Type listType = new TypeToken<ArrayList<Picture>>(){}.getType();

                ArrayList<Picture> foos = new Gson().fromJson(res, listType);
                final String newIma = foos.get(0).getPAddress();

//                for (int i = 0; i < foos.size(); i++) {
////                    image[i] = foos.get(i).getPAddress();
//                    ima111.add(foos.get(i).getPAddress());
//                    System.out.println("name ["+ i +"] = " + foos.get(i).getPAddress());
//                }
//                for (int j = 0 ; j < ima111.size() ; j++ )
//                {
//                    Log.i("info111", "ima =  " +ima111.get(j));
//                }
                Thread mThread = new Thread() {
                    @Override
                    public void run() {
                        Message msg = new Message();
//                        for (int i = 0; i < ima111.size(); i++) {
//                            msg1 = ima111.get(i);
                            msg1= newIma;
                            Bitmap bmp = getURLimage(msg1);
//                            tup[i] = bmp;
                            tupp.add(10,bmp);
                            tupp.remove(0);
                            msg.what = 1;
                            msg.obj = tupp;
                            System.out.println("000");
//                        }
                        handle.sendMessage(msg); //新建线程加载图片信息，发送到消息队列中
                    }
                };
                mThread.start();
            }
        });

    }


    public void doClick(View view) throws IOException {
        switch (view.getId()) {
            case R.id.button:
                JsonUtil jsonUtil= new JsonUtil();
                jsonUtil.parseJson(JsonData2);
//                Jsoonutil2 jsoonutil2 = new Jsoonutil2();
//                jsoonutil2.pares(JsonData2);

                break;
            case R.id.get:
                Request.Builder builder = new Request.Builder();
                Request request = builder
                        .get()
                        .url(url)
                        .build();
//                CallHttp(request);
                break;
            case R.id.post:
//                String name1 = "你好啊";
//                int age = 20;
//                String key1 = "{\"name\":\""+name1+"\",\"age\":"+age + "}";
                FormBody.Builder builder2 = new FormBody.Builder();
                RequestBody requestBody = builder2.add("username", "s").build();
                Request.Builder builder1 = new Request.Builder();
                Request request1 = builder1
                        .url(url1 + "login")
                        .post(requestBody)
                        .build();
//                CallHttp(request1);
                break;
            case R.id.postjson:
//                String name1 = "request";
//
//                String key = "{\"state\":\"" +name1+ "\"}";
//                URLDecoder.decode(key, "utf-8");
//                RequestBody requestBody1 = RequestBody
//                        .create(MediaType.parse("text/plain; charset=utf-8"), key);
//                Request.Builder builder3 = new Request.Builder();
//                Request request2 = builder3
//                        .url(url1)
//                        .post(requestBody1)
//                        .build();
//                CallHttp(request2);


                break;
            case R.id.next:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (i == tupp.size()-1)
                {
                    Toast.makeText(MainActivity.this, "亲 到最后一张了哦！！！", Toast.LENGTH_SHORT).show();
                }
                else {
                    i++;
                    imageView.setImageBitmap(tupp.get(i));
                }
                    }
                });
                break;
            case R.id.shang:

//                flash2();
                if (i>0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            i--;
                            Log.i("iiiii", "i1 =  " + i);
                            imageView.setImageBitmap(tupp.get(i));
                            try {
                                flash2();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

//                        }
                        }
                    });
                }
                break;

        }
    }




    public Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}











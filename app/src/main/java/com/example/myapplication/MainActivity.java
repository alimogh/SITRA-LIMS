package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    int registeration_status_int;
    int device_accept_status_int;
    String custid;
    String registeration_status;
    String device_accept_status;
    private String url = "http://lab.sitraonline.org/index.php/api/validate_device_registration";
    private String geturl="http://lab.sitraonline.org/index.php/api/app_sitralims_validate_device_registration";
    ImageButton splash;
    //Duration for splash screen
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    String deviceid="";
    String android_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send();
        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
         android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.i("add",android_id);
        // splash=(ImageButton) findViewById(R.id.splash);
        //Splash Screen
        deviceid=android_id;

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

            }
        }, SPLASH_DISPLAY_LENGTH);

    }

public void send(){
    RequestQueue queue = Volley.newRequestQueue(this);
    StringRequest request = new StringRequest(Request.Method.POST, geturl, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            Log.i("My success", "" + response);


            try {
                //************parsing response object************

                JSONObject object = new JSONObject(response);
                Log.i("object",""+object);


                custid = object.getString("custid");
                registeration_status = object.getString("registration_status");
                device_accept_status = object.getString("device_accept_status");

                if(registeration_status_int==1&&device_accept_status_int==1){
                    Toast.makeText(MainActivity.this, "Waiting for approval", Toast.LENGTH_SHORT).show();
                }
                if(registeration_status_int==0&&device_accept_status_int==0){
                    Intent intent=new Intent(MainActivity.this,Register.class);
                    startActivity(intent);
                }
                if(registeration_status_int==2&&device_accept_status_int==2){
                    Intent intent=new Intent(MainActivity.this,HomeScreen.class);
                    intent.putExtra("custid",custid);
                    startActivity(intent);
                }
                //****************parsing common data*************

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "Please complete the registeration process/Contact SITRA", Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(MainActivity.this,DeviceId.class);
//               Intent intent=new Intent(MainActivity.this,HomeScreen.class);
//                startActivity(intent);
            }


        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(MainActivity.this, "Please check connectivity", Toast.LENGTH_SHORT).show();
            Log.i("My error", "" + error);
        }
    }) {
        @Override

        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> map = new HashMap<String, String>();
            map.put("deviceid", deviceid);
            Log.i("device id",deviceid);

            return map;
        }
    };
    queue.add(request);
//    Intent intent=new Intent(MainActivity.this,HomeScreen.class);
//                startActivity(intent);

}
}
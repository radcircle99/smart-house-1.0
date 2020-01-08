package com.example.smart_house10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

import org.json.JSONObject;

public class Action_lamp_salon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_lamp_salon);
        SwipeButton swipeButton = (SwipeButton) findViewById(R.id.swipe_btn);

        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {

                if (active == true) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String url = "http://192.168.43.76:8080/LedOn";
                            RequestQueue requestQueue = Volley.newRequestQueue(getParent());
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject jsonObject) {
                                            try {
                                                Toast.makeText(Action_lamp_salon.this, "Lammpe de fond allumée", Toast.LENGTH_SHORT).show();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            error.printStackTrace();
                                        }


                                    });
                            requestQueue.add(jsonObjectRequest);
                        }
                    });
                    thread.start();
                }else

                {
                    Thread thread=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String url = "http://192.168.43.76:8080/LedOff";
                            RequestQueue requestQueue = Volley.newRequestQueue(getParent());
                            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject jsonObject) {
                                            try {
                                                Toast.makeText(Action_lamp_salon.this, "Lammpe de fond éteinte", Toast.LENGTH_SHORT).show();
                                            }catch(Exception e){
                                                e.printStackTrace();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            error.printStackTrace();
                                        }


                                    });
                            requestQueue.add(jsonObjectRequest);
                        }
                    });
                    thread.start();
                }
            }
        });
                }

            }

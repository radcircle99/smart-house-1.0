package com.example.smart_house10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

public class Temp_salon_indicator extends Fragment {
    View view;

    TextView min,max,date,valeur;
    SimpleDateFormat dateFormat,dateFormat2,dateFormat3;
    CircularProgressIndicator circularProgress;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.temp_sal_frag1,container,false);
        dateFormat3 = new SimpleDateFormat("HH:mm");
        date =view.findViewById(R.id.temp_sal_date);
        getDate();
        valeur=view.findViewById(R.id.temp_act_sal);
        min=view.findViewById(R.id.temp_min_sal);
        max=view.findViewById(R.id.temp_max_sal);
        getTempValue();
        circularProgress = view.findViewById(R.id.circular_progress);
        circularProgress.setMaxProgress(100); //interval (30-45)+
        return view;
    }
    //Fonction qui nous permettra de réceptionner la température du système
    private void getTempValue() {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                //http://192.168.43.76:8080/getInfoTemp
                String url = "https://api.myjson.com/bins/es5wk";
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                try {
                                    Double valTemp = jsonObject.getDouble("valeur");
                                    Double minTemp = jsonObject.getDouble("min");
                                    Double maxTemp = jsonObject.getDouble("max");

                                    valeur.setText( valTemp+ " °C");
                                    min.setText( minTemp+ " °C");
                                    max.setText(maxTemp+ " °C");
                                    circularProgress.setCurrentProgress(valTemp);
                                    circularProgress.setProgressTextAdapter(new CircularProgressIndicator.ProgressTextAdapter() {
                                        @NonNull
                                        @Override
                                        public String formatText(double currentProgress) {
                                            return String.valueOf(currentProgress) + " °C";
                                        }
                                    });
                                } catch (JSONException ex) {
                                    ex.printStackTrace();
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
    public void getDate(){
        dateFormat2=new SimpleDateFormat("dd-MM-yy");
        String string=dateFormat2.format(new Date());
        date.setText(string);
    }
}

package com.example.smart_house10;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Temp_salon_manag extends Fragment {
    View view;
    LineGraphSeries<DataPoint> lines;
    GraphView graph;
    SimpleDateFormat dateFormat,dateFormat2,dateFormat3;
    double[] value;
    Long[] time;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.temp_sal_frag2,container,false);
        graph=view.findViewById(R.id.graph);
        dateFormat3 = new SimpleDateFormat("HH:mm");
        getTemps();


        return view;
    }
    public void getTemps() {
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                //graph.setTitle("diagramme de temperature");
                graph.setTitleColor(Color.RED);
                dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

                GridLabelRenderer renderer = graph.getGridLabelRenderer();
                //renderer.setHorizontalAxisTitle("heures");
                renderer.setHorizontalLabelsVisible(true);
                lines= new LineGraphSeries<DataPoint>();
                //https://api.myjson.com/bins/1gxqqw
                //http://192.168.43.29:8080/GetTemperature
                //http://192.168.43.76:8080/GetTemperature
                String url = "https://api.myjson.com/bins/1gxqqw";
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray jsonArray) {
                                value=new double[jsonArray.length()];
                                time=new Long[jsonArray.length()];
                                try {

                                    for (int i=0;i<jsonArray.length();i++){
                                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                                        value[i]=jsonObject.getDouble("valeur");
                                        String time1=jsonObject.getString("time");

                                        //Date d=new Date(Timestamp.valueOf(time1).getTime());
                                        Date dd=dateFormat.parse(time1);
                                        lines.appendData(new DataPoint(dd.getTime(),value[i]),true,jsonArray.length());
                                    }

                                }catch (Exception e){
                                    Log.i("msg",e.getMessage());
                                }
                                graph.addSeries(lines);
                                //graph.getViewport().setMinY(1);
                                //graph.getViewport().setMaxY(50);
                                //graph.getViewport().setYAxisBoundsManual(true);
                                lines.setDrawDataPoints(true);
                                lines.setDataPointsRadius(10);
                                lines.setColor(Color.YELLOW);
                                //fonction qui permet de formater l'axe x en format hh:mm
                                graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){

                                    @Override
                                    public String formatLabel(double value, boolean isValueX) {
                                        if(isValueX){
                                            return dateFormat3.format(new Date((long)value));
                                        }else{
                                            return super.formatLabel(value,isValueX);
                                        }
                                    }
                                });
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                error.printStackTrace();

                            }

                        });
                requestQueue.add(jsonObjectRequest);
            }
        });
        thread1.start();
    }
}


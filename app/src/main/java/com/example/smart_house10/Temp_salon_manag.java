package com.example.smart_house10;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;


import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Temp_salon_manag extends Fragment implements OnChartGestureListener, OnChartValueSelectedListener {
    View view;
    private LineChart mChart;
    private static final String TAG ="Activity temp_manag";


    SimpleDateFormat dateFormat,dateFormat2,dateFormat3;
    double[] value;
    Long[] time;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.temp_sal_frag2,container,false);
       mChart = (LineChart)view.findViewById(R.id.graph);
       mChart.setOnChartValueSelectedListener(Temp_salon_manag.this);
       mChart.setOnChartGestureListener(Temp_salon_manag.this);
      mChart.setViewPortOffsets(0, 0, 0, 0);
        mChart.setBackgroundColor(Color.parseColor("#00333639"));
        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        mChart.setMaxHighlightDistance(300);

        mChart.animateXY(5000,5000);
        mChart.invalidate();


        dateFormat3 = new SimpleDateFormat("HH:mm");
        getTemps();


        return view;
    }

    //Pour suivre depuis le logout en cas d'erreur

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
            Log.i(TAG,"onChartGestureStart: X" + me.getX() +"Y: "+ me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
            Log.i(TAG,"onChartGestureEnd: " + lastPerformedGesture);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i(TAG,"onChartLongPressed: ");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i(TAG,"onChartDoubleTapped: ");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i(TAG,"onChartSingleTapped: ");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i(TAG,"onChartFling: veloX: " +velocityX + "veloY: "+velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i(TAG,"onChartScale: scaleX: " +scaleX + "scaleY: "+scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i(TAG,"onChartTranslate: dX: " +dX + "dY: "+dY);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
            Log.i(TAG,"onValueSelected: "+ e.toString());
    }

    @Override
    public void onNothingSelected() {

    }
    public void getTemps() {

        //Définition des limites au cas on devrait alarmer l'utilisateur
    /*    LimitLine upper_limit = new LimitLine(30f,"Chaleur intense");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f,10f,0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(15f);

        LimitLine lower_limit = new LimitLine(0f,"Fraîcheur intense");
        upper_limit.setLineWidth(4f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        upper_limit.enableDashedLine(10f,10f,0f);
        upper_limit.setTextSize(15f);*/

        final ArrayList<Entry>yValues = new ArrayList<>();



        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                //https://api.myjson.com/bins/1gxqqw
                //http://192.168.43.29:8080/GetTemperature
                //http://192.168.43.76:8080/GetTemperature
                String url = "https://api.myjson.com/bins/1gxqqw";
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray jsonArray) {
                                value = new double[jsonArray.length()];
                                time = new Long[jsonArray.length()];
                                try {

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        value[i] = jsonObject.getDouble("valeur");
                                        String time1 = jsonObject.getString("time");

                                        //Date d=new Date(Timestamp.valueOf(time1).getTime());
                                        Date dd = dateFormat.parse(time1);
                                        yValues.add(new Entry(i, (float) value[i]));
                                    }

                                }catch (Exception e){
                                    Log.i("msg",e.getMessage());
                                }

                                YAxis leftAxis =mChart.getAxisLeft();
                                leftAxis.setTypeface(Typeface.SANS_SERIF);
                                leftAxis.setLabelCount(6, false);
                                leftAxis.setTextColor(Color.BLACK);
                                leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
                                leftAxis.setDrawGridLines(false);
                                leftAxis.setAxisLineColor(Color.WHITE);
       /* leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);*/
                                leftAxis.setAxisMaximum(60f);
                                leftAxis.setAxisMinimum(-50f);
                                leftAxis.enableGridDashedLine(10f,10f,0);
                                leftAxis.setDrawLimitLinesBehindData(true);

                                mChart.getAxisRight().setEnabled(false);

                                XAxis xAxis = mChart.getXAxis();
                                xAxis.setEnabled(true);
                                xAxis.setDrawGridLines(false);
                                xAxis.setTextColor(Color.BLACK);
                                xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
                                LineDataSet set1 = new LineDataSet(yValues,"Température ");
                                //Partie relative à la courbe tracée
                                set1.setFillAlpha(110);
                                set1.setColor(Color.RED);
                                set1.setLineWidth(1f);
                                set1.setValueTextSize(10f);
                                set1.setValueTextColor(Color.BLACK);
                                set1.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
                                set1.setCubicIntensity(0.2f);
                                set1.setDrawFilled(true);
                                set1.setDrawCircles(true);
                                set1.setCircleRadius(4f);
                                set1.setLineWidth(1.8f);
                                set1.setCircleColor(Color.WHITE);
                                set1.setHighLightColor(Color.rgb(244, 117, 117));

                                set1.setColor(Color.WHITE);
                                set1.setFillColor(Color.parseColor("#2084C2"));

                                set1.setDrawHorizontalHighlightIndicator(false);
                                set1.setFillFormatter(new IFillFormatter() {
                                    @Override
                                    public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                                        return mChart.getAxisLeft().getAxisMinimum();
                                    }
                                });

                                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                                dataSets.add(set1);

                                LineData data = new LineData(dataSets);
                                //Remplissage du chart avec les paramètres
                                mChart.setData(data);
                                data.setDrawValues(true);
                            }



                        },new Response.ErrorListener() {

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


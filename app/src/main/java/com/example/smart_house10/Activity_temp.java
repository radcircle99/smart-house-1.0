package com.example.smart_house10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.smart_house10.Adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
//Activitée réservée à la surveillance de l'état de la température
public class Activity_temp extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageButton btn_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        // Ajout du bind du tablayout et de l'adaptateur
        tabLayout =(TabLayout) findViewById(R.id.tabLayout_temp_id);
        viewPager =(ViewPager) findViewById(R.id.viewpager_temp_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Ajout des fragments
        adapter.AddFragment(new Temp_salon_indicator(),"Température ");
        adapter.AddFragment(new Temp_salon_manag(),"Evolution");
        //Adapter Setup
        viewPager.setAdapter(adapter);
        //fusion du tablayout avec leview pager
        tabLayout.setupWithViewPager(viewPager);
        //Tab icons setup
     TabLayout.Tab text01 = tabLayout.getTabAt(0);
     TabLayout.Tab text02 = tabLayout.getTabAt(1);
        text01.setText("");
        text02.setText("");
        text02.setIcon(R.drawable.ic_action_name);
        text01.setIcon(R.drawable.ic_action_name_temp);


        btn_return =(ImageButton)findViewById(R.id.image_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
}

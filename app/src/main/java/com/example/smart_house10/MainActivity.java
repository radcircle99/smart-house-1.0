package com.example.smart_house10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.smart_house10.Adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private CardView cardView,cardView1,cardView2;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Binder les composantes
        cardView =(CardView)findViewById(R.id.toolbartop);
        toolbar=(Toolbar)findViewById(R.id.toolbar);

        //Paramétrage de la barre d'outils
        toolbar.setTitle("SMART HOUSE");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_dehaze_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
//Adaptation selonla version du SDK android de l'hôte
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            toolbar.setElevation(10.f);
        }//Rendre arrondi  la toolbar par l'intermédiaire de la cardview
        cardView.setRadius(25.f);
        cardView1 =(CardView)findViewById(R.id.Card1);
        cardView1.setRadius(25.f);
        cardView2 =(CardView)findViewById(R.id.Card2);
        cardView2.setRadius(12.f);

        // Ajout du bind du tablayout et de l'adaptateur
        tabLayout =(TabLayout) findViewById(R.id.tabLayout_id);
        viewPager =(ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Ajout des fragments
        adapter.AddFragment(new CapSalonFragment(),"Salon");
        adapter.AddFragment(new CapTerrasseFragment(),"Terrasse");
        adapter.AddFragment(new CapCuisineFragment(),"Cuisine");
        adapter.AddFragment(new CapChambreparFragment(),"Chambre parents");
        adapter.AddFragment(new CapChambreparFragment(),"Chambre enfants");

        adapter.AddFragment(new CapChambreparFragment(),"Chambre amis");
        //Adapter Setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



    }



}

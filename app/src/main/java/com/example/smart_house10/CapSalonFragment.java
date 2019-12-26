package com.example.smart_house10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

public class CapSalonFragment extends Fragment {
    View view;
    CardView cardView,cardView1,cardView2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.salon_fragment,container,false);
       cardView = (CardView)view.findViewById(R.id.Cardsal);
       cardView.setRadius(25.f);

        cardView1 = (CardView)view.findViewById(R.id.Cardsaltemp);
        cardView1.setRadius(25.f);

        cardView2 = (CardView)view.findViewById(R.id.Cardsallum);
        cardView2.setRadius(25.f);

        return view;
    }
}

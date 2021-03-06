package com.example.smart_house10;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
//Fragment relié à la vue de l'état du salon , c'est ici qu'à été développé le linkage avec l'acquisition de la température
public class CapSalonFragment extends Fragment {
    View view;
    CardView cardView,cardView1,cardView2;
    ImageButton btn_temp_acq;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.salon_fragment,container,false);
       cardView = (CardView)view.findViewById(R.id.Cardsal);
       cardView.setRadius(25.f);

       //Cardview contrôlant les lampes du salon
        cardView1 = (CardView) view.findViewById(R.id.lampe_sal);

       btn_temp_acq=(ImageButton)view.findViewById(R.id.temp_sal);
       btn_temp_acq.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(view.getContext(), Activity_temp.class);
               startActivity(intent);
           }
       });
       cardView1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(view.getContext(), Action_lamp_salon.class);
               startActivity(intent);
           }
       });


        return view;
    }
}

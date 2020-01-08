package com.example.smart_house10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
//Fragment relié à la vue de l'état dans la chambre des parents
public class CapChambreparFragment extends Fragment {
    CardView cardView;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view =inflater.inflate(R.layout.chmabrepar_fragment,container,false);
    cardView = (CardView) view.findViewById(R.id.Cardchamp);
    cardView.setRadius(40.f);
        return view;
    }
}

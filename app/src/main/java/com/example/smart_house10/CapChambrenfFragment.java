package com.example.smart_house10;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
//Fragment relié à la vue de l'état de la chambre des enfants
public class CapChambrenfFragment extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.chambreenf_fragment,container,false);
        CardView cardView = (CardView)view.findViewById(R.id.Cardchamenf);
        cardView.setRadius(40.f);
        return view;
    }
}

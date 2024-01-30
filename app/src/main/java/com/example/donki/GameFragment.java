package com.example.donki;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.media.Image;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class GameFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_game_fragment, container, false);
        Lienzo lienzo = new Lienzo(requireContext());
        ConstraintLayout cl = rootView.findViewById(R.id.clFondoGame);
        cl.addView(lienzo);
        ImageView img = rootView.findViewById(R.id.imgviewgame);
        Animacion ani = new Animacion(img,350,170,R.drawable.animacion);
        ani.comenzarAnimacion();
        String s = new String();

        return rootView;
    }
}

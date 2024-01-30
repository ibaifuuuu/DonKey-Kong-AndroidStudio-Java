package com.example.donki;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class GameOverFragment extends Fragment {
    private View vista;
    private int posXAnimKingKong;
    private int posYAnimKingKong ;
    Sonido son;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragmentogameover, container, false);
        configurarAnimacionesIniciales();
        configurarSonido();
        return vista;
    }

    private void configurarAnimacionesIniciales(){

        ImageView imgView = vista.findViewById(R.id.imgv);

        posXAnimKingKong = Pantalla.getMitadPantallaAncho();
        posYAnimKingKong = Pantalla.getMitadPantallaAlto();

        Animacion ani = new Animacion(imgView, posXAnimKingKong, posYAnimKingKong, R.drawable.baile);
        ani.moverAPunto(950,170,3000);

    }
    private void configurarSonido(){
        son = new Sonido(getContext(), R.raw.ganar);
        son.iniciar();
    }
}

package com.example.donki;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class GameFragment extends Fragment implements View.OnTouchListener{

    private ImageView imLui, btDRCH, btIZQ, btSalto;
    private static int xLui, yLui;
    private AnimationDrawable luiguidr, luiguiiz, luiguiS, luiguip;

    private static boolean animando;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_game_fragment, container, false);
        Lienzo lienzo = new Lienzo(requireContext());
        ConstraintLayout cl = rootView.findViewById(R.id.clFondoGame);
        cl.addView(lienzo);
        ImageView img = rootView.findViewById(R.id.imgviewgame);
        Animacion mono = new Animacion(img,350,125,R.drawable.animacion);
        mono.comenzarAnimacion();

        imLui = rootView.findViewById(R.id.imLuigui);
        imLui.setBackgroundResource(R.drawable.aniluiguiparado);
        luiguip = (AnimationDrawable) imLui.getBackground();
        luiguip.start();

        btIZQ = rootView.findViewById(R.id.ivIZQ);
        btDRCH = rootView.findViewById(R.id.ivDRCH);
        btSalto = rootView.findViewById(R.id.ivSALTO);

        btIZQ.setOnTouchListener(this);
        btDRCH.setOnTouchListener(this);
        btSalto.setOnTouchListener(this);

        xLui = 350;
        yLui = Pantalla.getAltoPantalla()-230;

        imLui.setX(xLui);
        imLui.setY(yLui);

        return rootView;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (v == btIZQ) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                xLui = xLui - 8;
                imLui.setBackgroundResource(R.drawable.aniluiguizq);
                luiguiiz = (AnimationDrawable) imLui.getBackground();
                luiguiiz.start();

                // Iniciar la ejecución recurrente
                moveLeftHandler.post(moveLeftRunnable);

            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                luiguiiz.stop();
                imLui.setBackgroundResource(R.drawable.luigui1iz);

                // Detener la ejecución recurrente
                moveLeftHandler.removeCallbacks(moveLeftRunnable);
            }

        } else if (v == btDRCH) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                animando = true;
                xLui = xLui + 8;
                imLui.setBackgroundResource(R.drawable.aniluiguidr);
                luiguidr = (AnimationDrawable) imLui.getBackground();
                luiguidr.start();

                // Iniciar la ejecución recurrente
                moveRightHandler.post(moveRightRunnable);

            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                luiguidr.stop();
                imLui.setBackgroundResource(R.drawable.luigui1dr);

                // Detener la ejecución recurrente
                moveRightHandler.removeCallbacks(moveRightRunnable);
            }
        }

        return true;
    }

    // Manejador y Runnable para el movimiento a la izquierda
    private Handler moveLeftHandler = new Handler();
    private Runnable moveLeftRunnable = new Runnable() {
        @Override
        public void run() {
            xLui = xLui - 8;
            imLui.setX(xLui); // Actualizar la posición directamente
            moveLeftHandler.postDelayed(this, 0); // 60 FPS
        }
    };

    // Manejador y Runnable para el movimiento a la derecha
    private Handler moveRightHandler = new Handler();
    private Runnable moveRightRunnable = new Runnable() {
        @Override
        public void run() {
            xLui = xLui + 8;
            imLui.setX(xLui); // Actualizar la posición directamente
            moveRightHandler.postDelayed(this, 0); // 60 FPS
        }
    };



}

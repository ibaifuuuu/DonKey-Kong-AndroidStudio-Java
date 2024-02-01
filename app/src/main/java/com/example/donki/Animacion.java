package com.example.donki;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;


public class Animacion {

    private ImageView imgView;
    private AnimationDrawable animacion;
    private float xPrincipalAnimacion;
    private float yPrincipalAnimacion;
    private int idPrimeraFoto;

    private float mitadAnchoImageView;
    private float mitadAltoImageView;
    private int iterationCount = 0;
    private Handler handler = new Handler();

    private static final int MAX_ITERATIONS = 1000;  // Adjust the number of iterations as needed




    public Animacion(ImageView imgView, int xPrincipalAnimacion, int yPrincipalAnimacion, int idXmlAnimacion) {
        this.imgView = imgView;
        this.idPrimeraFoto = idXmlAnimacion;
        setDrawableResource(idXmlAnimacion);
        initializeAnimation();
        initializeImageDimensions();
        this.xPrincipalAnimacion = xPrincipalAnimacion ;
        this.yPrincipalAnimacion = yPrincipalAnimacion ;

    }

    private void setDrawableResource(int idXmlAnimacion) {
        try {
            imgView.setBackgroundResource(idXmlAnimacion);
        } catch (Exception e) {
            Log.e("Animacion", "Error setting background resource", e);
        }
    }

    private void initializeAnimation() {
        try {
            animacion = (AnimationDrawable) imgView.getBackground();
            animacion.setOneShot(false);
        } catch (Exception e) {
            Log.e("Animacion", "Error initializing animation", e);
        }
    }

    private void initializeImageDimensions() {
        mitadAnchoImageView = imgView.getWidth() / 2.0f;
        mitadAltoImageView = imgView.getHeight() / 2.0f;
    }

    public void comenzarAnimacion() {
        moverAPunto(0, 0, 0);
        moverAPunto(xPrincipalAnimacion, yPrincipalAnimacion, 1000);
        iterationCount = 0;
        startAnimationSequence();

    }

    private void startAnimationSequence() {
        if (iterationCount < MAX_ITERATIONS) {
            moverAPunto(0, 0, 0);
            moverAPunto(xPrincipalAnimacion, yPrincipalAnimacion, 1000);
            animacion.start();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    moverAPunto(xPrincipalAnimacion + 60, 125, 200);
                }
            }, 1200);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    moverAPunto(xPrincipalAnimacion - 60, 125, 200);
                    // Increment the iteration count
                    iterationCount++;
                    // Start the next iteration after a delay
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startAnimationSequence();
                        }
                    }, 500); // Adjust the delay between iterations as needed
                }
            }, 1500);
        }
    }



    public void pararAnimacion() {
        animacion.stop();
        imgView.setBackgroundResource(idPrimeraFoto);
    }

    public void moverAPunto(float x, float y, int duration) {
        imgView.animate().x(x).y(y).setDuration(duration);
    }
}

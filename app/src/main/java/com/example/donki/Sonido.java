package com.example.donki;

import android.content.Context;
import android.media.MediaPlayer;

public class Sonido {

    private MediaPlayer mp;
    private int posicion = 0;
    private Context contexto;
    int codigoArchivoSonido;

    public boolean pausado = true;

    public Sonido(Context contexto, int codigoArchivoSonido){

        this.contexto = contexto;
        this.codigoArchivoSonido = codigoArchivoSonido;

    }

    public void destruir() {

        if(mp!=null){

            mp.release();
        }
    }

    public void iniciar() {
        destruir();
        mp = MediaPlayer.create(contexto,codigoArchivoSonido);
        mp.start();
        pausado = false;

    }
    public void activarReproduccionCircular(boolean act){

        mp.setLooping(act);

    }

    public void pausar() {
        if(mp != null && mp.isPlaying()) {
            posicion = mp.getCurrentPosition();
            mp.pause();
            pausado = true;
        }
    }

    public void continuar() {

        if(mp != null && mp.isPlaying()==false) {

            mp.seekTo(posicion);
            mp.start();
            pausado = false;

        }
    }

    public void detener() {
        if(mp != null) {
            mp.stop();
            posicion = 0;
            pausado = false;
        }
    }

}

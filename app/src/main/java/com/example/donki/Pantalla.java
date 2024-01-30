package com.example.donki;

public class Pantalla {

    private static int anchoPantalla;
    private static int altoPantalla;
    private static int mitadPantallaAncho;
    private static int mitadPantallaAlto;
    private static final int inicioPantallaAncho = 0;
    private static final int inicioPantallaAlto = 0;




    public static int getAltoPantalla() {
        return altoPantalla;
    }

    public static int getAnchoPantalla() {
        return anchoPantalla;
    }

    public static int getInicioPantallaAlto() {
        return inicioPantallaAlto;
    }

    public static int getInicioPantallaAncho() {
        return inicioPantallaAncho;
    }

    public static int getMitadPantallaAlto() {
        return mitadPantallaAlto;
    }

    public static int getMitadPantallaAncho() {
        return mitadPantallaAncho;
    }

    public static void setAltoPantalla(int altoPantalla) {
        Pantalla.altoPantalla = altoPantalla;
        Pantalla.mitadPantallaAlto = altoPantalla/2;
    }

    public static void setAnchoPantalla(int anchoPantalla) {
        anchoPantalla = anchoPantalla ;
        Pantalla.anchoPantalla = anchoPantalla;
        Pantalla.mitadPantallaAncho = anchoPantalla/2;
    }


}

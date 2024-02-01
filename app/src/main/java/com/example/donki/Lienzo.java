package com.example.donki;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Linea {
    int xInicial;
    int yInicial;
    int xFinal;
    int yFinal;

    public Linea(int xInicial, int yInicial, int xFinal, int yFinal) {
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.xFinal = xFinal;
        this.yFinal = yFinal;
    }
}

public class Lienzo extends View {
    
    private Map<String, int[]> colores = new HashMap<>();
    private Paint pincel;
    private Canvas canvas;

    
    private float ancho;
    private float alto;


    public List<Point> puntosMovimiento = new ArrayList<>();

    

    public Lienzo(Context context) {
        super(context);
        pincel = new Paint();
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        inicializarColores();
        this.canvas = canvas;
        ancho = canvas.getWidth();
        alto = canvas.getHeight();
        cambiarGrosorPincel(30);

        List<Linea> lineasRectas = new ArrayList<>();


        cambiarColorPincel("Marron");
        dibujarLinea(300,250,600,250);
        lineasRectas.add(new Linea(300,250,600,250));

        cambiarColorPincel("Verde");
        dibujarLinea(600,250,1600,250);
        lineasRectas.add(new Linea(600,250,1600,250));


        dibujarLinea(300,450,1600,450);
        cambiarColorPincel("Marron");
        dibujarLinea(1600,450,1900,380);
        lineasRectas.add(new Linea(1600,450,1900,380));


        cambiarColorPincel("Verde");
        dibujarLinea(300,650,1600,650);
        cambiarColorPincel("Marron");
        dibujarLinea(300,650,10,580);
        lineasRectas.add(new Linea(300,750,10,680));



        /*cambiarColorPincel("Verde");
        dibujarLinea(300,750,1600,750);
        cambiarColorPincel("Marron");
        dibujarLinea(1600,750,1900,680);
        lineasRectas.add(new Linea(1600,850,1900,780));*/



        cambiarColorPincel("Marron");
        dibujarLinea(0,Pantalla.getAltoPantalla()-150,Pantalla.getAnchoPantalla()+400,Pantalla.getAltoPantalla()-150);
        lineasRectas.add(new Linea(0,Pantalla.getAltoPantalla(),1900,680));



    }

    public void anadirPuntoMovimiento(int xCuadrado, int yCuadrado){

        Point punto = new Point(xCuadrado,yCuadrado);
        puntosMovimiento.add(punto);

    }

    public void dibujarCirculo(float x, float y, float radio) {

        canvas.drawCircle(x , y , radio, pincel);

    }

    public void dibujarTextoCamino(){

        Path camino = new Path();

        int marcador = 0;

        for (Point punto:puntosMovimiento) {

            if(marcador==0){

                camino.moveTo(punto.x,punto.y);

            }
            else{

                camino.lineTo(punto.x,punto.y);
            }
            marcador++;
        }


        canvas.drawTextOnPath("Hola Mundo Hola Mundo", camino, 0, 0, pincel);

    }

    public void dibujarTextoCaminoCircular(float xCuadrado, float yCuadrado, float radio) {
        Path camino = new Path();


        for (int angulo = 0; angulo <= 360; angulo += 10) {
            double radianes = Math.toRadians(angulo);
            float x = (xCuadrado) + (float) (radio * Math.cos(radianes));
            float y = (yCuadrado) + (float) (radio * Math.sin(radianes));

            if (angulo == 0) {
                camino.moveTo(x, y);
            } else {
                camino.lineTo(x, y);
            }
        }

        // Dibujar el texto a lo largo del camino circular
        canvas.drawTextOnPath("Hola Mundo Hola Mundo", camino, 0, 0, pincel);
    }

    public void dibujarOvalo(float x, float y, float ancho, float alto) {
        canvas.drawOval(
                x ,
                y ,
                (x + ancho) ,
                (y + alto),
                pincel
        );
    }

    public void dibujarArco(float x, float y, float radio, float inicioAngulo, float sweepAngulo, boolean usarCentro) {
        canvas.drawArc(
                x  - radio,
                y  - radio,
                x + radio,
                y  + radio,
                inicioAngulo,
                sweepAngulo,
                usarCentro,
                pincel
        );
    }




    //------------------------------------------ METODOS UTILIZABLES ---------------------------------------------------


    private void cambiarColorPincel(String nombreColor) {
        int[] rgb = colores.get(nombreColor);

        if (rgb != null) {
            pincel.setARGB(255, rgb[0], rgb[1], rgb[2]);
        } else {
            Log.d("ERROR CONTROLADO", "No se encontró el color " + nombreColor + " en la paleta de colores");
        }
    }

    private void cambiarTamanoLetra(int tamano){

        pincel.setTextSize(tamano);

    }

    private void cambiarFuenteLetraExterna(String nombreFuente){

        Typeface face = Typeface.createFromAsset(getContext().getAssets(), nombreFuente);
        pincel.setTypeface(face);

    }

    private void cambiarGrosorPincel(float grosor) {
        pincel.setStrokeWidth(grosor);
    }

    private void activarRellenoPincel(boolean activado) {

        if (activado){
            pincel.setStyle(Paint.Style.FILL);
        }
        else{
            pincel.setStyle(Paint.Style.STROKE);
        }
    }

    private void dibujarPixel(float x, float y) {
        if (pincel != null && canvas != null) {
            canvas.drawPoint(x, y, pincel);
        }

    }

    private void dibujarTexto(String texto, float x, float y, int tamanoFuente){

        cambiarTamanoLetra(tamanoFuente);
        canvas.drawText(texto,x,y,pincel);

    }


    private void dibujarLineaCuadricula(float xComienzo, float yComienzo, float xFinal, float yFinal){

        canvas.drawLine(xComienzo, yComienzo, xFinal, yFinal, pincel);

    }

    private void dibujarLinea(int xComienzo,int yComienzo,int xFinal, int yFinal){

        canvas.drawLine(xComienzo, yComienzo, xFinal, yFinal, pincel);

    }

    private void dibujarRectangulo(float xComienzo, float yComienzo,float xFinal, float yFinal){

        canvas.drawRect(xComienzo,yComienzo,xFinal,yFinal,pincel);
    }

    public void pintarFondo(String nombreColor){

        int[] rgb = colores.get(nombreColor);

        if (rgb != null) {
            canvas.drawARGB(255, rgb[0], rgb[1], rgb[2]);
        } else {
            Log.d("ERROR CONTROLADO", "No se encontró el color " + nombreColor + " en la paleta de colores");
        }
    }



    //-------------------------------------------------------------------------------------------------------------------

    private void inicializarColores() {
        colores.put("Rojo", new int[]{255, 0, 0});
        colores.put("Verde", new int[]{0, 205, 80});
        colores.put("Azul", new int[]{0, 0, 255});
        colores.put("Amarillo", new int[]{255, 255, 0});
        colores.put("Cian", new int[]{0, 255, 255});
        colores.put("Magenta", new int[]{255, 0, 255});
        colores.put("Naranja", new int[]{255, 165, 0});
        colores.put("Rosa", new int[]{255, 192, 203});
        colores.put("Morado", new int[]{128, 0, 128});
        colores.put("Turquesa", new int[]{64, 224, 208});
        colores.put("Ocre", new int[]{204, 119, 34});
        colores.put("Negro", new int[]{0, 0, 0});
        colores.put("Blanco", new int[]{255, 255, 255});
        colores.put("Marron", new int[]{139, 69, 19});
    }

}


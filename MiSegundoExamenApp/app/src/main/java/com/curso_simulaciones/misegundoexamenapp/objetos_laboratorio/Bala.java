package com.curso_simulaciones.misegundoexamenapp.objetos_laboratorio;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Bala {


    private float largo;
    private float longitud;
    private float posicionInicialX, posicionInicialY;
    private float posicionX, posicionY, posicionAngular;
    private int color1 = Color.rgb(255, 0, 0);  //rojo
    private int color2 = Color.rgb(0, 255, 0);  //verde
    private int color3 = Color.rgb(0, 0, 255);  //Azul
    private int color4 = Color.BLACK;


    /**
     * Constructor por defecto
     * Rueda centrada en (0,0),
     *
     */
    public Bala() {
        this.largo = largo;
    }

    /**
     * Constructor de Rueda centrada
     * en (posicionX,posicionY),
     * con posición angular cero
     */

    public Bala(float posicionInicialX, float posicionInicialY, float largo, float longitud) {
        this.posicionX = posicionInicialX;
        this.posicionY = posicionInicialY;

        this.posicionInicialX = posicionInicialX;
        this.posicionInicialY = posicionInicialY;

        this.largo = largo;
        this.longitud = longitud;
    }

    /**
     * Modifica el valor del radio de la rueda
     *
     * @param radio
     */
    public void setRadioPolea(float radio) {
        this.largo = radio;
    }

    /**
     * Devuelve el valor del radio de la polea
     *
     * @return radio
     */
    public float getRadioPolea() {
        return largo;
    }


    /**
     * Modifica los colores de la rueda
     *
     * @param color1, color2, color 3
     */
    public void setColorPolea(int color1, int color2, int color3, int color4) {

        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
    }

    /**
     * Devuelve el color de la polea
     *
     * @return color
     */
    public int getColorPolea() {
        return color1;
    }


    /**
     * Modifica la posición (x,y) de la polea
     *
     * @param desplazamientoX
     * @param desplazamientoY
     */
    public void moverPolea(float desplazamientoX, float desplazamientoY) {

        this.posicionX = this.posicionInicialX + desplazamientoX;
        this.posicionY = this.posicionInicialY + desplazamientoY;

    }


    /**
     * Modifica la posición angular en grados de la polea
     *
     * @param posicionAngular
     */
    public void moverPolea(float posicionAngular) {
        this.posicionX = this.posicionInicialX;
        this.posicionY = this.posicionInicialY;

        this.posicionAngular = posicionAngular;
    }

    /**
     * Modifica la posición (x,y) y genera
     * una rotación alrededor del eje que pasa por (x,y)
     *
     * @param desplazamientoX
     * @param desplazamientoY
     * @param posicionAngular
     */

    public void moverPolea(float desplazamientoX, float desplazamientoY, float posicionAngular) {
        this.posicionX = this.posicionInicialX + desplazamientoX;
        this.posicionY = this.posicionInicialY + desplazamientoY;

        this.posicionAngular = posicionAngular;
    }


    public void dibujese(Canvas canvas, Paint pincel) {
        canvas.save();
        //rotar
        canvas.rotate(posicionAngular, posicionX, posicionY);

        //  ***************************************** Rueda Izquierda **************************
        float desviacion = 0.5f*longitud+0.22f*largo;
        float radioCirculos = 0.02f*largo;
        // Dibujar el circulo rojo
        pincel.setStrokeWidth(0.005f*largo);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(color1);
        canvas.drawCircle(posicionX-desviacion, posicionY, 0.2f*largo  - 4.0f*radioCirculos, pincel);

        // Dibujar el contorno del circulo rojo
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setColor(Color.BLACK);
        canvas.drawCircle(posicionX-desviacion, posicionY, 0.2f*largo - 4.0f*radioCirculos , pincel);

        // Dibujar el eje
        pincel.setStrokeWidth(0.2f*largo);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.BLACK);
        canvas.drawCircle(posicionX-desviacion, posicionY, 0.03f*largo, pincel);

        // Dibujar el circulo verde
        pincel.setStrokeWidth(0.04f*largo);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setColor(color2);
        canvas.drawCircle(posicionX-desviacion, posicionY, 0.2f*largo + 0.02f*largo , pincel);


        // Se dibujan los circulos blancos
        // Primero se calcula el angulo entre ellos, para poder rotar el Canvas
        float anguloEntreCirculos = (float) (360.0 / 6);
        // Se define el radio de los círculos
        for (int i = 0; i < 6+1; i = i + 1) {
            float j = (float) i;
            // Se calcula el ángulo de rotación para la actual iteración
            float anguloRotacion = (float) (anguloEntreCirculos * j);
            if (i%2 == 0){
                pincel.setColor(color3);
            } else {
                pincel.setColor(color4);
            }
            // Se rota el canvas respecto al centro
            canvas.rotate(anguloRotacion, posicionX-desviacion, posicionY);
            // Se dibuja el círculo de manera que quede tangente internamente al círculo rojo
            canvas.drawCircle(posicionX-desviacion, posicionY-0.2f*largo + 2.0f*radioCirculos, radioCirculos,pincel);
            // Se devuelve el canvas a su posición original
            canvas.rotate(-anguloRotacion, posicionX-desviacion, posicionY);

        }


        //  ***************************************** Rueda Derecha **************************
        // Dibujar el circulo rojo
        pincel.setStrokeWidth(0.005f*largo);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(color1);
        canvas.drawCircle(posicionX+desviacion, posicionY, 0.2f*largo  - 4.0f*radioCirculos, pincel);

        // Dibujar el contorno del circulo rojo
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setColor(Color.BLACK);
        canvas.drawCircle(posicionX+desviacion, posicionY, 0.2f*largo - 4.0f*radioCirculos , pincel);

        // Dibujar el eje
        pincel.setStrokeWidth(0.2f*largo);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.BLACK);
        canvas.drawCircle(posicionX+desviacion, posicionY, 0.03f*largo, pincel);

        // Dibujar el circulo verde
        pincel.setStrokeWidth(0.04f*largo);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setColor(color2);
        canvas.drawCircle(posicionX+desviacion, posicionY, 0.2f*largo + 0.02f*largo , pincel);


        // Se dibujan los circulos blancos
        // Primero se calcula el angulo entre ellos, para poder rotar el Canvas
        // Se define el radio de los círculos
        for (int i = 0; i < 6+1; i = i + 1) {
            float j = (float) i;
            // Se calcula el ángulo de rotación para la actual iteración
            float anguloRotacion = (float) (anguloEntreCirculos * j);
            if (i%2 == 0){
                pincel.setColor(color3);
            } else {
                pincel.setColor(color4);
            }
            // Se rota el canvas respecto al centro
            canvas.rotate(anguloRotacion, posicionX+desviacion, posicionY);
            // Se dibuja el círculo de manera que quede tangente internamente al círculo rojo
            canvas.drawCircle(posicionX+desviacion, posicionY-0.2f*largo + 2.0f*radioCirculos, radioCirculos,pincel);
            // Se devuelve el canvas a su posición original
            canvas.rotate(-anguloRotacion, posicionX+desviacion, posicionY);

        }

        // ************************ Barra **********************

        pincel.setStrokeWidth(0.05f*largo);
        pincel.setColor(Color.BLACK);
        canvas.drawLine(posicionX-0.5f*longitud,posicionY,posicionX+0.5f*longitud,posicionY,pincel);
        //regresar la rotación
        canvas.restore();
    }
}

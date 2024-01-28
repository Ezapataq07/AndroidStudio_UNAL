package com.curso_simulaciones.miprimerexamenapp.componentes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class Rueda extends View {

    private float largo;

    /**
     * Constructor de Rueda
     */
    public Rueda(Context context) {

        super(context);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        }
    }

    /**
     * @param canvas
     */

    //método para dibujar
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        se graba el estado actual del canvas
        para al final restaurarlo
        */
        canvas.save();


         /*
         La vista tendra las mismas dimensiones de su
         contenedor
         */
        float ancho = this.getWidth();//ancho de la vista
        float alto = this.getHeight();//alto de la vista

        /*
         Se define la variable largo como el 80%
         del menor valor entre alto y largo del
         contenedor
         */

        if (ancho > alto) {

            largo = 0.8f * alto;

        } else {

            largo = 0.8f * ancho;


        }

        /*
          se hace tralación del (0,0) al centro
          del contenedor
        */
        canvas.translate(0.5f * ancho, 0.5f * alto);

        //configurando el pincel
        Paint pincel = new Paint();
        //evita efecto sierra
        pincel.setAntiAlias(true);
        //tamaño texto
        pincel.setTextSize(0.05f * largo);
        //para mejor manejo de la métrica de texto
        pincel.setLinearText(true);
        //para efectos de buen escalado de bitmaps
        pincel.setFilterBitmap(true);
        //para buen manejo de gradientes de color
        pincel.setDither(true);


        // Titulo
        pincel.setColor(Color.BLACK);
        pincel.setTextSize(40f);
        pincel.setTextAlign(Paint.Align.CENTER);
        pincel.setTypeface(Typeface.SERIF);
        canvas.drawText("MiPrimerExamenApp: Emanuel Zapata Querubín", 0, -0.5f*largo, pincel);


        // Dibujar el circulo rojo
        pincel.setStrokeWidth(0.005f*largo);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.RED);
        canvas.drawCircle(0, 0, 0.2f*largo, pincel);

        // Dibujar el contorno del circulo rojo
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, 0.2f*largo, pincel);

        // Dibujar el eje
        pincel.setStrokeWidth(0.2f*largo);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, 0.07f*largo, pincel);

        // Dibujar el circulo verde
        pincel.setStrokeWidth(0.04f*largo);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setColor(Color.GREEN);
        canvas.drawCircle(0, 0, 0.25f*largo, pincel);


        // Se dibujan los circulos blancos
        // Primero se calcula el angulo entre ellos, para poder rotar el Canvas
        float anguloEntreCirculos = (float) (360.0 / 6);
        // Se define el radio de los círculos
        float radioCirculos = 0.02f*largo;
        for (int i = 0; i < 6+1; i = i + 1) {
            float j = (float) i;
            // Se calcula el ángulo de rotación para la actual iteración
            float anguloRotacion = (float) (anguloEntreCirculos * j);
            // Se rota el canvas respecto al centro
            canvas.rotate(anguloRotacion, 0, 0);
            // Se dibuja el círculo de manera que quede tangente internamente al círculo rojo
            pincel.setColor(Color.WHITE);
            canvas.drawCircle(0, -0.2f*largo + 2.0f*radioCirculos, radioCirculos,pincel);
            // Se devuelve el canvas a su posición original
            canvas.rotate(-anguloRotacion, 0, 0);

        }
    }
}


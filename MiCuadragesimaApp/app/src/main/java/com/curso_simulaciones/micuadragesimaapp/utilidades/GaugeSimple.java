package com.curso_simulaciones.micuadragesimaapp.utilidades;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

public class GaugeSimple extends View {


    private float minimo = -0;
    private float maximo = 100f;
    private float medida = 0.0f;
    private String unidades = "UNIDADES";
    private float minimo_2 = -30f;
    private float maximo_2 = 70f;
    private float medida_2 = 0.0f;
    private String unidades_2 = "UNIDADES";
    private int colorPrimerTercio = Color.rgb(200, 200, 0);
    private int colorSegundoTercio = Color.rgb(0, 180, 0);
    private int colorTercerTercio = Color.RED;
    private int colorLineas = Color.BLACK;
    private int colorFondo = Color.WHITE;
    private int colorTablerroDespliegue = Color.BLACK;
    private int colorNumerosDesplieggue = Color.WHITE;
    private int colorFranjaDinamica = Color.rgb(0, 0, 255);
    private int angPrimertercio = 100;
    private int angSegundoTercio = 100;
    private int angTercerTercio = 50;


    /**
     * Constructor de GaugeSimple
     */

    public GaugeSimple(Context context) {

        super(context);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        }

    }

    /**
     * Modifica el rango de medicion
     * desde minimo hasta maximo
     *
     * @param minimo
     * @param maximo
     */

    public void setRango(float minimo, float maximo) {

        this.minimo = minimo;
        this.maximo = maximo;

    }
    public void setRango_2(float minimo_2, float maximo_2) {

        this.minimo_2 = minimo_2;
        this.maximo_2 = maximo_2;

    }


    /**
     * Modifica el valor medido
     *
     * @param medida
     */

    public void setMedida(float medida) {

        this.medida = medida;

    }
    public void setMedida_2(float medida_2) {

        this.medida_2 = medida_2;

    }

    /**
     * Regresa el valor medido
     *
     * @return medda
     */
    public float getMedida() {

        return medida;
    }


    /**
     * Modifica las unidades del instrumento virtual
     *
     * @param unidades
     */
    public void setUnidades(String unidades) {

        this.unidades = unidades;

    }

    public void setUnidades_2(String unidades_2) {

        this.unidades_2 = unidades_2;

    }


    /**
     * Modifica los colores de los sectores circulares
     *
     * @param colorPrimerTercio
     * @param colorSegundoTercio
     * @param colorTercerTercio
     */
    public void setColorSectores(int colorPrimerTercio, int colorSegundoTercio, int colorTercerTercio) {

        this.colorPrimerTercio = colorPrimerTercio;
        this.colorSegundoTercio = colorSegundoTercio;
        this.colorTercerTercio = colorTercerTercio;

    }

    /**
     * Modifica los angulos de los sectores circulares
     * Deben sumar 250 grados
     *
     * @param angPrimerTercio
     * @param angSegundoTercio
     * @param angTercerTercio
     */
    public void setAngulosSectores(int angPrimerTercio, int angSegundoTercio, int angTercerTercio) {
        this.angPrimertercio = angPrimerTercio;
        this.angSegundoTercio = angSegundoTercio;
        this.angTercerTercio = angTercerTercio;

    }

    public void setColorFranjaDinámica(int colorFranjaDinamica) {

        this.colorFranjaDinamica = colorFranjaDinamica;


    }

    /**
     * Modifica el color de fondo del tacometro
     *
     * @param colorFondo
     */
    public void setColorFondoTacometro(int colorFondo) {

        this.colorFondo = colorFondo;


    }


    /**
     * Modifica el color de las lineas del tacometro
     *
     * @param color_lineas
     */
    public void setColorLineasTacometro(int color_lineas) {

        this.colorLineas = color_lineas;


    }

    /**
     * Modifica el color del tablero de despliegue
     *
     * @param colorTableroDespliegue
     */

    public void setColorTableroDespliegue(int colorTableroDespliegue) {

        this.colorTablerroDespliegue = colorTableroDespliegue;

    }


    /**
     * Modifica el color del numero que se despliega
     *
     * @param colorNumerosDesplieggue
     */

    public void setColorNumeroDespliegue(int colorNumerosDesplieggue) {

        this.colorNumerosDesplieggue = colorNumerosDesplieggue;

    }


    /**
     * @param canvas
     */

    //método para dibujar
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        /*
         La vista tendra las mismas dimensiones de su
         contenedor
         */
        float ancho = this.getWidth();//ancho de la vista
        float alto = this.getHeight();//alto de la vista
        float largo = 0f;

        /*
          Se define la vairable larho como el 80%
          del menor valor entre al y largo
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
        float esquinaSuperiorIzquierdaX = -0.5f * largo;
        float esquinaSuperiorIzquierdaY = -0.5f * largo;
        float esquinaInferiorDerechaX = 0.5f * largo;
        float esquinaInferiorDerechaY = 0.5f * largo;

        RectF rect = new RectF(esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY,
                esquinaInferiorDerechaX, esquinaInferiorDerechaY);

        pincel.setStyle(Paint.Style.FILL);

       /*
        Partamos en tres segmentos de diferente color.
       */

        pincel.setColor(colorPrimerTercio);
        canvas.drawArc(rect, 145, angPrimertercio, true, pincel);
        pincel.setColor(colorSegundoTercio);
        canvas.drawArc(rect, 145 + angPrimertercio, angSegundoTercio, true, pincel);
        pincel.setColor(colorTercerTercio);
        canvas.drawArc(rect, 145 + angPrimertercio + angSegundoTercio, angTercerTercio, true, pincel);

        //franja dinámica
        float angulo_rotacion_medida = 235 + (250f / (maximo - minimo)) * (medida - minimo);
        float a = (float) 0.03 * largo;
        rect = new RectF(esquinaSuperiorIzquierdaX + a, esquinaSuperiorIzquierdaY + a,
                esquinaInferiorDerechaX - a, esquinaInferiorDerechaY - a);
        pincel.setColor(colorFranjaDinamica);
        canvas.drawArc(rect, 145, angulo_rotacion_medida - 235, true, pincel);


        float radio = (float) (0.45 * largo);
        pincel.setColor(colorFondo);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setColor(colorLineas);
        pincel.setStyle(Paint.Style.STROKE);
        //canvas.drawCircle(0, 0, radio, pincel);
        pincel.setStrokeWidth(1f);
        //canvas.drawCircle(0, 0, 0.5f * largo, pincel);

      /*
        Hacer las divisiones
      */

        pincel.setStrokeWidth(0.005f * largo);

       /*
         Divisiones grandes
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 50 grados comenzando
         en 235 grados.
         Se aprovechará para marcar
       */

        float indent = (float) (0.1 * largo);
        float posicionY = (float) (0.5 * largo);

        for (int i = 0; i < 6; i = i + 1) {
            float anguloRotacion = 235 + 50 * i;
            canvas.rotate(anguloRotacion, 0, 0);
            canvas.drawLine(0, -posicionY, 0, -posicionY + indent, pincel);

            //marcas
            // Centrar números con las divisiones grandes
            int valorIncrementoMarcas = (int) ((maximo - minimo) / 5f);
            int valorMarca = (int) (minimo + valorIncrementoMarcas * i);
            String numero = "" + valorMarca;
            float anchoCadenaNumero = pincel.measureText(numero);
            float posicionXNumero = -0.5f * anchoCadenaNumero;
            float posicionYNumero = (float) (-posicionY + 1.4 * indent);
            //dibujar los números
            pincel.setStyle(Paint.Style.FILL);

            canvas.drawText(numero, posicionXNumero, posicionYNumero, pincel);
            canvas.rotate(-anguloRotacion, 0, 0);


        }


        /*
         Divisiones pequeñas
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 10 grados comenzando
         en 235 grados.
         */
        pincel.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 26; i = i + 1) {

            float anguloRotacion = 235 + 10 * i;
            canvas.rotate(anguloRotacion, 0, 0);
            canvas.drawLine(0, -posicionY, 0, -posicionY + (float) (0.2 * indent), pincel);
            canvas.rotate(-anguloRotacion, 0, 0);

        }

        //Dibujar aguja
        pincel.setStrokeWidth(0.005f * largo);
        pincel.setColor(Color.RED);
        // float angulo_rotacion_medida = 235 + (250f / (maximo - minimo)) * (medida - minimo);
        canvas.rotate(angulo_rotacion_medida, 0, 0);
        canvas.drawLine(0, -posicionY, 0, 0, pincel);
        canvas.rotate(-angulo_rotacion_medida, 0, 0);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, (float) (0.1 * indent), pincel);
        //unidades
        pincel.setColor(colorLineas);
        float anchoCadenaUnidades = pincel.measureText(unidades);
        canvas.drawText(unidades, -0.5f * anchoCadenaUnidades-0.2f*largo, -0.08f * largo, pincel);
        //pantallita de despliegue
        float anchoCadenaNumero = pincel.measureText("" + medida);
        RectF rect_1 = new RectF(-0.3f * largo, -0.05f * largo, -0.1f * largo, 0.05f * largo);
        pincel.setColor(colorTablerroDespliegue);
        canvas.drawRect(rect_1, pincel);
        pincel.setColor(colorNumerosDesplieggue);
        canvas.drawText("" + medida, -0.5f * anchoCadenaNumero-0.2f*largo, 0.02f*largo, pincel);

        //unidades
        pincel.setColor(colorLineas);
        anchoCadenaUnidades = pincel.measureText(unidades_2);
        canvas.drawText(unidades_2, -0.5f * anchoCadenaUnidades+0.2f*largo, -0.08f * largo, pincel);
        //pantallita de despliegue
        anchoCadenaNumero = pincel.measureText("" + medida_2);
        rect_1 = new RectF(0.1f * largo, -0.05f * largo, 0.3f * largo, 0.05f * largo);
        pincel.setColor(colorTablerroDespliegue);
        canvas.drawRect(rect_1, pincel);
        pincel.setColor(colorNumerosDesplieggue);
        canvas.drawText("" + medida_2, -0.5f * anchoCadenaNumero+0.2f*largo, 0.02f*largo, pincel);
        pincel.setColor(Color.BLACK);
        pincel.setTextSize(0.08f*largo);
        pincel.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("% HR", -0.5f*pincel.measureText("% HR"), -0.2f*largo, pincel);




        /***************************************************************************************************/


        /*
          se hace tralación del (0,0) al centro
          del contenedor
         */
        canvas.translate(0f * ancho, 0.2f * alto);

        largo = largo * 0.3f;

        Paint pincel2 = new Paint();
        //evita efecto sierra
        pincel2.setAntiAlias(true);
        //tamaño texto
        pincel2.setTextSize(0.05f * largo);
        //para mejor manejo de la métrica de texto
        pincel2.setLinearText(true);
        //para efectos de buen escalado de bitmaps
        pincel2.setFilterBitmap(true);
        //para buen manejo de gradientes de color
        pincel2.setDither(true);
        float esquinaSuperiorIzquierdaX_2 = -0.5f * largo;
        float esquinaSuperiorIzquierdaY_2 = -0.5f * largo;
        float esquinaInferiorDerechaX_2 = 0.5f * largo;
        float esquinaInferiorDerechaY_2 = 0.5f * largo;

        RectF rect_2 = new RectF(esquinaSuperiorIzquierdaX_2, esquinaSuperiorIzquierdaY_2,
                esquinaInferiorDerechaX_2, esquinaInferiorDerechaY_2);

        pincel2.setStyle(Paint.Style.FILL);

       /*
        Partamos en tres segmentos de diferente color.
       */

        pincel2.setColor(colorPrimerTercio);
        canvas.drawArc(rect_2, 145, angPrimertercio, true, pincel2);
        pincel2.setColor(colorSegundoTercio);
        canvas.drawArc(rect_2, 145 + angPrimertercio, angSegundoTercio, true, pincel2);
        pincel2.setColor(colorTercerTercio);
        canvas.drawArc(rect_2, 145 + angPrimertercio + angSegundoTercio, angTercerTercio, true, pincel2);

        //franja dinámica
        float angulo_rotacion_medida_2 = 235 + (250f / (maximo_2 - minimo_2)) * (medida_2 - minimo_2);

        float a_2 = (float) ( 0.03 * largo);
        rect_2 = new RectF(esquinaSuperiorIzquierdaX_2 + a_2, esquinaSuperiorIzquierdaY_2 + a_2,
                esquinaInferiorDerechaX_2 - a_2, esquinaInferiorDerechaY_2 - a_2);
        pincel2.setColor(colorFranjaDinamica);
        canvas.drawArc(rect_2, 145, angulo_rotacion_medida_2 - 235, true, pincel2);


        float radio_2 = (float) (0.45 * largo);
        pincel2.setColor(colorFondo);
        canvas.drawCircle(0, 0, radio_2, pincel2);
        pincel2.setColor(colorLineas);
        pincel2.setStyle(Paint.Style.STROKE);
        //canvas.drawCircle(0, 0, radio_2, pincel2);
        pincel2.setStrokeWidth(1f);
        //canvas.drawCircle(0, 0, 0.5f * largo, pincel2);

      /*
        Hacer las divisiones
      */

        pincel2.setStrokeWidth(0.005f * largo);

       /*
         Divisiones grandes
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 50 grados comenzando
         en 235 grados.
         Se aprovechará para marcar
       */

        float indent_2 = (float) (0.1 * largo);
        float posicionY_2 = (float) (0.5 * largo);

        for (int i = 0; i < 6; i = i + 1) {
            float anguloRotacion = 235 + 50 * i;
            canvas.rotate(anguloRotacion, 0, 0);
            canvas.drawLine(0, -posicionY_2, 0, -posicionY_2 + indent_2, pincel2);

            //marcas
            // Centrar números con las divisiones grandes
            int valorIncrementoMarcas = (int) ((maximo_2 - minimo_2) / 5f);
            int valorMarca = (int) (minimo_2 + valorIncrementoMarcas * i);
            String numero = "" + valorMarca;
            float anchoCadenaNumero_2 = pincel2.measureText(numero);
            float posicionXNumero = -0.5f * anchoCadenaNumero_2;
            float posicionYNumero = (float) (-posicionY_2 + 1.7 * indent_2);
            //dibujar los números
            pincel2.setStyle(Paint.Style.FILL);
            pincel2.setTextSize(0.1f*largo);
            canvas.drawText(numero, posicionXNumero, posicionYNumero, pincel2);
            canvas.rotate(-anguloRotacion, 0, 0);

        }


        /*
         Divisiones pequeñas
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 10 grados comenzando
         en 235 grados.
         */
        pincel2.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 26; i = i + 1) {

            float anguloRotacion = 235 + 10 * i;
            canvas.rotate(anguloRotacion, 0, 0);
            canvas.drawLine(0, -posicionY_2, 0, -posicionY_2 + (float) (0.2 * indent_2), pincel2);
            canvas.rotate(-anguloRotacion, 0, 0);

        }

        //Dibujar aguja
        pincel2.setStrokeWidth(0.005f * largo);
        pincel2.setColor(Color.RED);
        // float angulo_rotacion_medida = 235 + (250f / (maximo - minimo)) * (medida - minimo);
        canvas.rotate(angulo_rotacion_medida_2, 0, 0);
        canvas.drawLine(0, -posicionY_2, 0, 0, pincel2);
        canvas.rotate(-angulo_rotacion_medida_2, 0, 0);
        pincel2.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, (float) (0.1 * indent_2), pincel2);

        pincel2.setColor(Color.BLACK);
        pincel2.setTextSize(0.2f*largo);
        pincel2.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("°C", -0.5f*pincel2.measureText("°C"), 0.4f*largo, pincel2);
//        //unidades
//        pincel2.setColor(colorLineas);
//        float anchoCadenaUnidades_2 = pincel2.measureText(unidades_2);
//        canvas.drawText(unidades_2, -0.5f * anchoCadenaUnidades_2, 0.11f * largo, pincel2);
//        //pantallita de despliegue
//        float anchoCadenaNumero_2 = pincel2.measureText("" + medida_2);
//        RectF rect_22 = new RectF(-0.12f * largo, 0.14f * largo, 0.12f * largo, 0.23f * largo);
//        pincel2.setColor(colorTablerroDespliegue);
//        canvas.drawRect(rect_22, pincel2);
//        pincel2.setColor(colorNumerosDesplieggue);
//        canvas.drawText("" + medida_2, -0.5f * anchoCadenaNumero_2, 0.2f * largo, pincel2);

        //franja amarilla transparente


        canvas.restore();

        invalidate();//para animación

    }//fin onDraw

}

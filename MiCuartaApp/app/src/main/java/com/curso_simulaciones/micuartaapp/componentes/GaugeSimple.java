package com.curso_simulaciones.micuartaapp.componentes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class GaugeSimple extends View {
    private float largo;
    private float minimo = 0;
    private float maximo = 100f;
    private float medida = 40.0f;//tomar como medida inicial
    private String unidades = "UNIDADES";
    private int colorPrimerTercio = Color.rgb(200, 200, 0);
    private int colorSegundoTercio = Color.rgb(0, 180, 0);
    private int colorTercerTercio = Color.RED;
    private int colorLineas = Color.BLACK;
    private int colorFondo = Color.WHITE;
    private int colorTablerroDespliegue = Color.BLACK;
    private int colorNumerosDesplieggue = Color.WHITE;
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


    /**
     * Modifica el valor medido
     *
     * @param medida
     */
    public void setMedida(float medida) {

        this.medida = medida;

    }


    /**
     * Regresa el valor medido
     *
     * @return medida
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


        float esquinaSuperiorIzquierdaX = -0.5f * largo;
        float esquinaSuperiorIzquierdaY = -0.5f * largo;
        float esquinaInferiorDerechaX = 0.5f * largo;
        float esquinaInferiorDerechaY = 0.5f * largo;

        //dibujar los tres segementos circulares
        RectF rect = new RectF(esquinaSuperiorIzquierdaX, esquinaSuperiorIzquierdaY,
                esquinaInferiorDerechaX, esquinaInferiorDerechaY);

        pincel.setColor(colorPrimerTercio);
        canvas.drawArc(rect, 145, angPrimertercio, true, pincel);
        pincel.setColor(colorSegundoTercio);
        canvas.drawArc(rect, 145 + angPrimertercio, angSegundoTercio, true, pincel);
        pincel.setColor(colorTercerTercio);
        canvas.drawArc(rect, 145 + angPrimertercio + angSegundoTercio, angTercerTercio, true, pincel);



        float indent = (float) (0.2 * largo);
        float posicionY = (float) (0.5 * largo);


        /*
         dibujar el tacometro sin la aguja
        */
        //aqui empieza el dibujo
        float radio = (float) (0.45 * largo);
        pincel.setColor(colorFondo);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setColor(colorLineas);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setStrokeWidth(1f);
        canvas.drawCircle(0, 0, 0.5f * largo, pincel);

        //grosor de las líneas
        pincel.setStrokeWidth(0.005f * largo);

         /*
         Divisiones grandes
         Se dibuja primero la división vertical.
         Luego se repite rotando de a 50 grados comenzando
         en 235 grados.
         Se aprovechará para marcar
         */
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
            float posicionYNumero = (float) (-posicionY + 1.2 * indent);
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
        //aqui termina dibujo del tacometro sin aguja



        /*
        dibujar la aguja
        */
        //aqui empieza dibujo de la aguja
        //calcular angulo para ubicar la aguja de acuerdo al valor medido
        float angulo_rotacion_medida = 235 + (250f / (maximo - minimo)) * (medida - minimo);
        //Dibujar aguja
        pincel.setStrokeWidth(0.005f * largo);
        pincel.setColor(Color.RED);
        canvas.rotate(angulo_rotacion_medida, 0, 0);
        canvas.drawLine(0, -posicionY, 0, 0, pincel);
        canvas.rotate(-angulo_rotacion_medida, 0, 0);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, (float) (0.1 * indent), pincel);
        //aquí termina dibujo de la aguja

        //Dibujar las unidades
        pincel.setColor(colorLineas);
        float anchoCadenaUnidades = pincel.measureText(unidades);
        canvas.drawText(unidades, -0.5f * anchoCadenaUnidades, 0.11f * largo, pincel);
        //aqui termina dibujo de las unidades

        //aqui empieza deibujopantallita de despliegue
        float anchoCadenaNumero = pincel.measureText("" + medida);
        RectF rect_1 = new RectF(-0.12f * largo, 0.14f * largo, 0.12f * largo, 0.23f * largo);
        pincel.setColor(colorTablerroDespliegue);
        canvas.drawRect(rect_1, pincel);
        pincel.setColor(colorNumerosDesplieggue);
        canvas.drawText("" + medida, -0.5f * anchoCadenaNumero, 0.2f * largo, pincel);
        //aqui termina dibujo pantallita de despliegue




        //se restaura el canvas al estado incial
        //el que se garbó al principio de este método
        canvas.restore();

        //para efectos de animación
        invalidate();

    }//fin onDraw

}

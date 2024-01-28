package com.curso_simulaciones.mivigesimaquintaapp.controlador;

import android.app.Activity;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.curso_simulaciones.mivigesimaquintaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimaquintaapp.vista.CR;
import com.curso_simulaciones.mivigesimaquintaapp.vista.Pizarra;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Cuerda;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.CuerpoRectangular;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Flecha;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Marca;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Masa;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.ObjetoLaboratorio;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Polea;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Regla;

public class ActividadControladora extends Activity {

    //variable tamaño de las letras basado en resolución de pantalla
    private int tamanoLetraResolucionIncluida;

    private Pizarra pizarra;
    //Objetos GUI necesarios
    private TextView text_m1, text_m2;
    private SeekBar seek_bar_m1, seek_bar_m2;
    private Button boton_empezar, boton_pausar;



    //valores de las variables
    private float m1 = 15;
    private float m2 = 10;

    //valores iniciales puntos escena
    //centros de masa de las masas
    private float x1_pixeles, x2_pixeles, y1_pixeles, y2_pixeles;
    //centros de masa de las poleas
    private float xp1_pixeles, xp2_pixeles, yp1_pixeles, yp2_pixeles, xp3_pixeles, yp3_pixeles;
    //radio de las poleas
    private float radio;

    //dimensiones de los bloques
    private float ancho_bloque, alto_bloque;


    private Masa masa_1, masa_2;
    private Polea polea_1, polea_2, polea_3;
    private Flecha flecha_x, flecha_y;
    private Regla regla_1, regla_2;
    private CuerpoRectangular bloque1, bloque2, bloque3;
    private Cuerda cuerda_1, cuerda_2, cuerda_3, cuerda_4, cuerda_5;
    private Marca marca;

    private ObjetoLaboratorio[] objetos = new ObjetoLaboratorio[20];

    //hilo responsable de controlar la animación
    private HiloAnimacion hilo;


    @RequiresApi(api = Build.VERSION_CODES.P)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gestionarResolucion();


        //para crear elementos de la GUI
        crearElementosGUI();

        //para informar cómo se debe pegar el adminitrador de
        //diseño obtenido con el método GUI
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

        //hilo que administrador los cálculos
        hilo = new HiloAnimacion(this);
        hilo.start();


        //para administrar los eventos
        eventos();


    }//fin onCreate

    /*Método auxiliar para asuntos de resolución*/
    private void gestionarResolucion() {

        tamanoLetraResolucionIncluida = (int) (0.8f * AlmacenDatosRAM.tamanoLetraResolucionIncluida);
        /*
        Según el diseño de la GUI se puede anticipar cuál es la
        dimensión de la pizarra. En este caso es el 80% del ancho
        de la pantalla y el 100% del alto de la misma
        El cálculo del alto y el ancho de la pantalla
        se hizo en la clase principal con el móvil
        en portrait y aquí estamos en posición lanscape.
        Por tanto troquemos alto por ancho y viceversa
        */
        CR.anchoPizarra = 0.80f* AlmacenDatosRAM.alto_pantalla;
        CR.altoPizarra = AlmacenDatosRAM.ancho_pantalla;


    }

    /*método responsable de la creación de los elementos de la GUI*/
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void crearElementosGUI() {

        text_m1 = new TextView(this);
        text_m1.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        text_m1.setGravity(Gravity.CENTER);
        String marca_m1 = "MASA M1 \n 5 a 25 kg";//con salto de línea
        text_m1.setTextColor(Color.WHITE);
        text_m1.setText(marca_m1);

        text_m2 = new TextView(this);
        text_m2.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        text_m2.setGravity(Gravity.CENTER);
        text_m2.setTextColor(Color.WHITE);
        String marca_m2 = "MASA M2 \n 5 a 25 kg ";//con salto de línea
        text_m2.setText(marca_m2);


        seek_bar_m1 = new SeekBar(this);
        seek_bar_m1.setMax(20);
        seek_bar_m1.setProgress(15);//masa m1 inicial 15
        AlmacenDatosRAM.m1 = 15;
        seek_bar_m1.getProgressDrawable().setColorFilter(Color.rgb(232, 33, 39), PorterDuff.Mode.MULTIPLY);
        seek_bar_m1.setScaleY(0.5f);


        seek_bar_m2 = new SeekBar(this);
        seek_bar_m2.setMax(20);
        seek_bar_m2.setProgress(10);//masa m2 inicial 510
        seek_bar_m2.getProgressDrawable().setColorFilter(Color.rgb(232, 33, 39), PorterDuff.Mode.MULTIPLY);
        AlmacenDatosRAM.m2 = 10;
        seek_bar_m2.setScaleY(0.5f);


        boton_empezar = new Button(this);
        boton_empezar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        boton_empezar.setText("EMPEZAR");


        boton_empezar.getBackground().setColorFilter(Color.rgb(232, 33, 39), PorterDuff.Mode.MULTIPLY);

        boton_pausar = new Button(this);
        boton_pausar.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        boton_pausar.setText("PAUSAR");

        boton_pausar.getBackground().setColorFilter(Color.rgb(232, 33, 39), PorterDuff.Mode.MULTIPLY);
        boton_pausar.setEnabled(false);


        //crear pizarra sabiendo de antemano sus dimensiones
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.rgb(51,51,51));
        pizarra.setSistemaCoordenadas(CR.pcApxX(80f),CR.pcApxY(10f),1, 1);

        crearObjetosLaboratorio();

    }//fin crearElementosGUI


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        //el linear principal
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.HORIZONTAL);
        linear_principal.setBackgroundColor(Color.rgb(232, 33, 39));
        linear_principal.setWeightSum(10.0f);

        //linear secudario izquierda
        LinearLayout linear_izquierda = new LinearLayout(this);
        linear_izquierda.setWeightSum(1.0f);
        linear_izquierda.setBackgroundColor(Color.rgb(51,51,51));

        //linear secudario derecha
        LinearLayout linear_derecha = new LinearLayout(this);
        linear_derecha.setBackgroundColor(Color.rgb(51,51,51));
        linear_derecha.setWeightSum(6.0f);


        //pegar estos secundarios al princial
        LinearLayout.LayoutParams parametros_pegado_izquierdo = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        //ocupará el 80% de linear_principal
        parametros_pegado_izquierdo.setMargins(5,5,5,5);
        parametros_pegado_izquierdo.weight = 8.0f;
        linear_principal.addView(linear_izquierda, parametros_pegado_izquierdo);

        //pegar estos secundarios al princial
        LinearLayout.LayoutParams parametros_pegado_derecho = new LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        //ocupará el 20% de linear_principal
        parametros_pegado_derecho.weight = 2.0f;
        parametros_pegado_derecho.setMargins(5,5,5,5);
        linear_principal.addView(linear_derecha, parametros_pegado_derecho);


        //pegar comonentes a linear_derecha.
        //ojo: el cero va a la derecha ya que se agregaran verticalmente
        linear_derecha.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams parametros_pegado_componentes = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        //ocupará el 80% de linear_principal
        parametros_pegado_componentes.setMargins(5, 5, 5, 5);
        parametros_pegado_componentes.weight = 1.0f;

        linear_derecha.addView(text_m1, parametros_pegado_componentes);
        linear_derecha.addView(seek_bar_m1, parametros_pegado_componentes);

        linear_derecha.addView(text_m2, parametros_pegado_componentes);
        linear_derecha.addView(seek_bar_m2, parametros_pegado_componentes);
        linear_derecha.addView(boton_empezar, parametros_pegado_componentes);
        linear_derecha.addView(boton_pausar, parametros_pegado_componentes);

        //pegar la pizarra al linear izquierdo
        linear_izquierda.setOrientation(LinearLayout.VERTICAL);
        linear_izquierda.addView(pizarra);


        return linear_principal;
    }//fin gui

    private void eventos() {

        //evento botones
        boton_empezar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                if (boton_empezar.getText() == "EMPEZAR") {

                    // hilo.pausa = false;
                    hilo.pausa = false;
                    seek_bar_m2.setEnabled(false);
                    seek_bar_m1.setEnabled(false);
                    boton_empezar.setText("NUEVO");
                    boton_pausar.setText("PAUSAR");
                    boton_pausar.setEnabled(true);

                } else {


                    //hilo.pausa=true;
                    hilo.pausa = true;
                    seek_bar_m2.setEnabled(true);
                    seek_bar_m1.setEnabled(true);
                    boton_empezar.setText("EMPEZAR");
                    boton_pausar.setEnabled(false);
                    // detener();
                    // hilo = null;


                }

            }


        });


        //evento del boton activar
        boton_pausar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                if (boton_pausar.getText() == "PAUSAR") {

                    boton_pausar.setText("CONTINUAR");
                    hilo.pausa = true;

                } else {


                    boton_pausar.setText("PAUSAR");
                    hilo.pausa = false;

                }

            }

        });



        //eventos seekbar

        // seek_bar_m1
        seek_bar_m1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int progressChanged = progress;
                m1 =5 + progressChanged;//5 será el valor mínimo

                actualizarValoresIniciales();


            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        // seek_bar_m2
        seek_bar_m2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int progressChanged = progress;

                m2 = 5+progressChanged;//5 será el valor mínimo

                actualizarValoresIniciales();

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

    }


    /*
    Crea los objetos cuerpo rígido con su estado inicia
    -X esta en porcentaje del annco del canvas
    -Y está en porcentaje del alto del canvas
    -Cualquier otra dimensión está en porcentaje del menor
     entre el alto y el ancho del canvas
    */
    private void crearObjetosLaboratorio() {

        /*
          coordenadas iniciales
          de puntos básicos y
          dimensiones de los elementos
        */

        //radio de las poleas
        radio= CR.pcApxL(5);
        //almacenar valor del radio
        AlmacenDatosRAM.radio= radio;

        //dimensiones de cada masa: m1 y m2
        ancho_bloque= 2*radio;
        alto_bloque=radio;

        //coordenadas de las masas
        //abscisa y ordenada del centro de m1
        x2_pixeles= 0;
        y2_pixeles= CR.pcApxY(50f);
        //almacenar las coordenadas iniciales de m1
        AlmacenDatosRAM.x2_en_pixeles=x2_pixeles;
        AlmacenDatosRAM.yi2_en_pixeles = y2_pixeles;

        //abscisa y ordenada del centro de m2
        x1_pixeles= -16*radio;
        y1_pixeles=CR.pcApxY(50f);
        //almacenar las coordenadas iniciales de m2
        AlmacenDatosRAM.x1_en_pixeles=x1_pixeles;
        AlmacenDatosRAM.yi1_en_pixeles = y1_pixeles;

        //coordenadas de las poleas
        //abscisa y ordenada del centro de la polea 1
        xp1_pixeles=0;
        yp1_pixeles=0;
        //abscisa y ordenada del centro de la polea 2
        xp2_pixeles= 0;
        yp2_pixeles= y1_pixeles - 3*radio;

        // abscisa y ordenada del centro de la polea 3
        xp3_pixeles = -15*radio;
        yp3_pixeles = 0;


        /*
        Creación de objetos físicos y dibujo del
        estado inicial de la escena física
       */

        //4  cuerdas
        cuerda_1=new Cuerda(xp1_pixeles+radio,yp1_pixeles, xp2_pixeles+radio,yp2_pixeles);
        cuerda_1.setColor(Color.rgb(255,209,209));
        cuerda_1.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[0]=cuerda_1;

        cuerda_2=new Cuerda(xp2_pixeles-radio,yp2_pixeles,xp2_pixeles-radio,yp1_pixeles+3*radio);
        cuerda_2.setColor(Color.rgb(255,209,209));
        cuerda_2.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[1]=cuerda_2;
//
        cuerda_3=new Cuerda(xp1_pixeles,yp2_pixeles+radio,xp1_pixeles,y1_pixeles-0.5f*alto_bloque);
        cuerda_3.setColor(Color.rgb(255,209,209));
        cuerda_3.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[2]=cuerda_3;
//
        cuerda_4=new Cuerda(xp1_pixeles,yp1_pixeles-radio,xp3_pixeles,yp1_pixeles-radio);
        cuerda_4.setColor(Color.rgb(255,209,209));
        cuerda_4.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[3]=cuerda_4;

        cuerda_5=new Cuerda(xp3_pixeles-radio,yp3_pixeles,xp3_pixeles-radio,y2_pixeles-0.5f*alto_bloque);
        cuerda_5.setColor(Color.rgb(255,209,209));
        cuerda_5.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[4]=cuerda_5;

        //dos poleas
        //polea 1 (polea azul)
        polea_1=new Polea(xp1_pixeles,yp1_pixeles,radio);
        polea_1.setColor(Color.rgb(255,189,27));
        polea_1.setGrosorLinea(CR.pcApxL(0.5f));
        polea_1.setSoportePolea(true);
        polea_1.rotarEje(45);
        objetos[5]=polea_1;


        //polea 2 (polea roja)
        polea_2=new Polea(xp2_pixeles,yp2_pixeles,radio);
        polea_2.setColor(Color.GREEN);
        polea_2.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[6]=polea_2;

        //polea 1 (polea azul)
        polea_3=new Polea(xp3_pixeles,yp3_pixeles,radio);
        polea_3.setColor(Color.RED);
        polea_3.setGrosorLinea(CR.pcApxL(0.5f));
        polea_3.setSoportePolea(true);
        polea_3.rotarEje(-45);
        objetos[7]=polea_3;







        //2 masas
        //masa 2
        masa_1=new Masa(x1_pixeles,y1_pixeles,ancho_bloque,alto_bloque);
        masa_1.setColor(Color.GREEN);
        masa_1.setColorMarca(Color.BLACK);
        masa_1.setMarca("M1");
        objetos[8]=masa_1;


        //masa 1
        masa_2=new Masa(x2_pixeles,y2_pixeles,ancho_bloque,alto_bloque);
        masa_2.setColor(Color.RED);
        masa_2.setColorMarca(Color.BLACK);
        masa_2.setMarca("M2");
        objetos[9]=masa_2;




        //tres flechas
        //eje y
        flecha_y=new Flecha(0,CR.pcApxY(0f), CR.pcApxL(10));
        flecha_y.rotar(90);
        objetos[11]=flecha_y;

        //eje x
        flecha_x=new Flecha(CR.pcApxX(0f),CR.pcApxY(0f), CR.pcApxL(10));
        objetos[12]=flecha_x;



        //marcas de lo ejes
        //despliega el nombre del Eje y
        marca=new Marca("y",0,2.8f*radio);
        marca.setColor(Color.WHITE);
        marca.setTamano(CR.pcApxL(3f));
        objetos[15]=marca;
        //despliega el nombre del Eje y
        marca=new Marca("x",2.3f*radio,CR.pcApxY(1f));
        marca.setColor(Color.WHITE);
        marca.setTamano(CR.pcApxL(3f));
        objetos[16]=marca;

//        bloque1=new CuerpoRectangular(CR.pcApxX(50.5f)-9*radio,CR.pcApxY(60f),10*radio,CR.pcApxL(76));
        bloque1=new CuerpoRectangular(-7.5f*radio,2*radio,13*radio,2*radio);
        bloque1.setColor(Color.rgb(0,143,255 ));
        bloque1.setGrosorLinea(0);
        objetos[17]=bloque1;

        bloque2=new CuerpoRectangular(-7.5f*radio,9*radio,10*radio,12*radio);
        bloque2.setColor(Color.rgb(0,143,255));
        bloque2.setGrosorLinea(0);
        objetos[18]=bloque2;

        bloque3=new CuerpoRectangular(-7.5f*radio,16*radio,20*radio,2*radio);
        bloque3.setColor(Color.rgb(0,143,255 ));
        bloque3.setGrosorLinea(0);
        objetos[19]=bloque3;



        //desplegar la escena inicial
        pizarra.setEstadoEscena(objetos);

    }


    /*
    Cambia el estado de movimiento de los cuerpos rígidos
   -X esta en porcentaje del ancho del canvas
   -Y está en porcentaje del alto del canvas
   -Cualquier otra dimensión está en porcentaje del menor
    entre el alto y el ancho del canvas
   */
    public void cambiarEstadosEscenaPizarra() {

         /*
          variables necesarias para actualizar la
          escena física.
         */


        /*
         Coordenadas de los centros de las masas:

         Recoge los valores y1 y y2 calculados
         en con la clase ModeloFisico.

         Los valores x1 y x2 se mantienen constantes
         y fueron asignados en la clase ActividadPrincipal
         */

        float x1_en_pixeles = AlmacenDatosRAM.x1_en_pixeles;
        float y1_en_pixeles =  AlmacenDatosRAM.y1_en_pixeles;

        float x2_en_pixeles = AlmacenDatosRAM.x2_en_pixeles;
        float y2_en_pixeles = AlmacenDatosRAM.y2_en_pixeles;


        /*
         Desplazamiento angular de las poleas:

         Recoge los valores de teta_1 y teaa_2
         calculados con la clase ModeloFisico
         */
        float teta_1 =AlmacenDatosRAM.teta_1;
        float teta_2 = AlmacenDatosRAM.teta_2;

        /*
          Coordenadas de la polea 2.
          La polea 1 sólo rota

         */
        float xp2_en_pixeles = x2_en_pixeles;
        float yp2_en_pixeles = y2_en_pixeles - 3 * radio;

        float xp1_en_pixeles = 0;
        float yp1_en_pixeles = 0;

        float xp3_en_pixeles = -15*radio;
        float yp3_en_pixeles = 0;

        /*
          Ahora sí, actualizar la posición de los
          objetos que se mueven
        */
        //mover las poleas
        //mover polea_1
        polea_1.mover(-teta_1);
        //mover polea_2
        polea_2.mover(0, AlmacenDatosRAM.desplazamiento_m2_en_pixeles, teta_2);
        polea_3.mover(-teta_1);


        //mover masa 1
        masa_1.mover(0,AlmacenDatosRAM.desplazamiento_m1_en_pixeles);
        masa_2.mover(0, AlmacenDatosRAM.desplazamiento_m2_en_pixeles);



        // mover las flechas señaladoras y cuerdas
        //mover flecha 2
        y1_en_pixeles = y1_pixeles + AlmacenDatosRAM.desplazamiento_m1_en_pixeles;
        yp2_en_pixeles = yp2_pixeles + AlmacenDatosRAM.desplazamiento_m2_en_pixeles;
        y2_en_pixeles = y2_pixeles + AlmacenDatosRAM.desplazamiento_m2_en_pixeles;
        //mover la cuerda 3
        cuerda_3.setPosicionInicial(xp1_en_pixeles,yp2_en_pixeles+radio);
        cuerda_3.setPosicionFinal(xp1_en_pixeles, y2_en_pixeles - 0.5f * alto_bloque);


        //mover flecha 3
        y2_en_pixeles = y2_pixeles + AlmacenDatosRAM.desplazamiento_m2_en_pixeles;
        //mover la cuerda 1

        cuerda_1.setPosicionFinal(xp2_en_pixeles + radio, yp2_en_pixeles);
        //mover la cuerda 2

        cuerda_2.setPosicionInicial(xp2_en_pixeles - radio, yp2_en_pixeles);

        //mover la cuerda 5
        y1_en_pixeles = y1_pixeles + AlmacenDatosRAM.desplazamiento_m1_en_pixeles;
        cuerda_5.setPosicionFinal(xp3_en_pixeles-radio,y1_en_pixeles-0.5f*alto_bloque);


    }




    private void actualizarValoresIniciales(){


        AlmacenDatosRAM.m1=m1;
        AlmacenDatosRAM.m2=m2;


        float origenY_en_metros = 0.1f;
        //almacenar
        AlmacenDatosRAM.origenY_en_metros = origenY_en_metros;


        //condiciones iniciales de posición
        AlmacenDatosRAM.yi1_en_metros = 0.602f;
        AlmacenDatosRAM.yi2_en_metros = 0.602f;


    }



}


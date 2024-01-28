package com.curso_simulaciones.midecimacuartaapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio.EstrellaFija;
import com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio.Marciano;
import com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio.ObjetoEspacial;
import com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio.Selenita;
import com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio.Venusiano;
import com.curso_simulaciones.midecimacuartaapp.vista.CR;
import com.curso_simulaciones.midecimacuartaapp.vista.Pizarra;

public class ActividadPrincipalMiDecimaCuartaApp extends Activity implements Runnable {

    //Pizarra para dibujar
    Pizarra pizarra;
    //objetos dibujables para Pizarra
    private Marciano marciano_1;
    private Selenita selenita_1, selenita_2;
    private Venusiano venusiano_1, venusiano_2;
    private EstrellaFija estrella_1, estrella_2, estrella_3, estrella_4, estrella_5;
    private ObjetoEspacial[] objetos_espaciales = new ObjetoEspacial[15];

    private float aumento_1=1;
    private int colorVariable = -1331716;


    //período de muestreo en mislisegucnos
    private long periodo_muestreo = 50;
    private float tiempo;
    //hilo responsable de controlar la animación
    private Thread hilo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        llamada al método para crear los elementos de la
        interfaz gráfica de usuario (GUI)
        */
        crearElementosGui();

        /*
        para informar cómo se debe adaptar la GUI a la pantalla del dispositivo
        */
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        /*
        pegar al contenedor la GUI:
        en el argumento se está llamando al método crearGui()
        */
        this.setContentView(crearGui(), parametro_layout_principal);

        /*
         las poleas con responsividad se crearan dentro
         del hilo con el fin de garantizar que las dimensiones
         de la pizarra donde se desplegarán con responsividad
         tenga ya dimensiones no nulas
         */
        //hilo que administra la animación
        hilo = new Thread(this);
        hilo.start();

    }


    //crear los objetos de la interfaz gráfica de usuario (GUI)
    private void crearElementosGui() {

        //crear pizarra sabiendo de antemano sus dimensiones
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.WHITE);
    }


    //organizar la distribución de los objetos de de la GUI usando administradores de diseño
    private LinearLayout crearGui() {

        //el inear principal
        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setBackgroundColor(Color.BLACK);
        linearPrincipal.setWeightSum(10.0f);


        //linear secundario arriba
        LinearLayout linearArriba = new LinearLayout(this);


        //linear secundario abajo
        LinearLayout linearAbajo = new LinearLayout(this);
        linearAbajo.setBackgroundColor(Color.YELLOW);


        //pegar linearArriba al princial
        LinearLayout.LayoutParams parametrosPegadoArriba = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegadoArriba.weight = 8.5f;
        parametrosPegadoArriba.setMargins(50, 50, 50, 50);
        linearPrincipal.addView(linearArriba, parametrosPegadoArriba);


        //pegar linearAbajo al princial
        LinearLayout.LayoutParams parametrosPegadoAbajo = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegadoAbajo.weight = 1.5f;
        linearPrincipal.addView(linearAbajo, parametrosPegadoAbajo);


        //pegar pizarra a linearArriba
        linearArriba.addView(pizarra);


        return linearPrincipal;

    }


    @Override
    public void run() {

        boolean ON = true;

        //hilo sin fin
        while (true) {

            try {
                Thread.sleep(periodo_muestreo);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

            /*
            hacer la creación de las poleas con
            responsividad SÓLO cuando se garantice que la
            GUI se conformó completamente con el fin de que
            las dimensiones de la pizarra NO SEAN NULAS.
            */
            if (pizarra.getWidth() != 0 && ON == true) {
                crearObjetosConResponsividad();
                ON = false;
            }

            //ya creadas las poleas con responsividad hacer efectiva la animación
            if (ON == false) {
                tiempo = tiempo + 0.05f;
                //cambio de estado de la escena física en la pizarra
                cambiarEstadosEscenaPizarra(tiempo);
            }


        }

    }

    private void crearObjetosConResponsividad() {

        CR.anchoPizarra = pizarra.getWidth();
        CR.altoPizarra = pizarra.getHeight();
        crearObjetosLaboratorio();

    }

    /*
    Crea los objetos espaciales con su estado inicial
    -X esta en porcentaje del annco del canvas
    -Y está en porcentaje del alto del canvas
    -Cualquier otra dimensión está en porcentaje del menor
     entre el alto y el ancho del canvas
    */
    private void crearObjetosLaboratorio() {

        //marciano 1
        float x_1 = CR.pcApxX(10);
        float y_1 = CR.pcApxY(75);
        marciano_1 =new Marciano(x_1,y_1);
        marciano_1.setRatioMarciano(CR.pcApxL(5));
        marciano_1.setColor(colorVariable);
        //agregarlo al arreglo de objetos espaciales
        objetos_espaciales[1] = marciano_1;


        //estrella 1
        x_1 = CR.pcApxX(50);
        y_1 = CR.pcApxY(50);
        estrella_1 =new EstrellaFija(x_1,y_1);
        estrella_1.setRatioEstrella(CR.pcApxL(10));
        estrella_1.setColor(Color.RED);
        //agregarlo al arreglo de objetos espaciales
        objetos_espaciales[2] = estrella_1;


        //estrella 2
        x_1 = CR.pcApxX(20);
        y_1 = CR.pcApxY(20);
        estrella_2 =new EstrellaFija(x_1,y_1);
        estrella_2.setRatioEstrella(CR.pcApxL(5));
        estrella_2.setColor(Color.GREEN);
        //agregarlo al arreglo de objetos espaciales
        objetos_espaciales[3] = estrella_2;

        //estrella 3
        x_1 = CR.pcApxX(80);
        y_1 = CR.pcApxY(80);
        estrella_3 =new EstrellaFija(x_1,y_1);
        estrella_3.setRatioEstrella(CR.pcApxL(7));
        estrella_3.setColor(Color.BLACK);
        //agregarlo al arreglo de objetos espaciales
        objetos_espaciales[4] = estrella_3;


        //estrella 4
        x_1 = CR.pcApxX(80);
        y_1 = CR.pcApxY(20);
        estrella_4 =new EstrellaFija(x_1,y_1);
        estrella_4.setRatioEstrella(CR.pcApxL(20));
        estrella_4.setColor(Color.BLUE);
        //agregarlo al arreglo de objetos espaciales
        objetos_espaciales[5] = estrella_4;


        //estrella 5
        x_1 = CR.pcApxX(20);
        y_1 = CR.pcApxY(80);
        estrella_5 =new EstrellaFija(x_1,y_1);
        estrella_5.setRatioEstrella(CR.pcApxL(15));
        estrella_5.setColor(Color.CYAN);
        //agregarlo al arreglo de objetos espaciales
        objetos_espaciales[6] = estrella_5;

        //selenita 1
        x_1 = CR.pcApxX(0);
        y_1 = CR.pcApxY(30);
        selenita_1 =new Selenita(x_1,y_1);
        selenita_1.setRatioSelenita(CR.pcApxL(10));
        selenita_1.setColor(Color.rgb(250,120,0));
        //agregarlo al arreglo de objetos espaciales
        objetos_espaciales[7] = selenita_1;

        //venusiano 1
        x_1 = CR.pcApxX(0);
        y_1 = CR.pcApxY(80);
        venusiano_1 =new Venusiano(x_1,y_1);
        venusiano_1.setRatioVenuciano(CR.pcApxL(8));
        venusiano_1.setColor(Color.rgb(0,143,255));
        //agregarlo al arreglo de objetos espaciales
        objetos_espaciales[8] = venusiano_1;

        //selenita 2
        x_1 = CR.pcApxX(40);
        y_1 = CR.pcApxY(0);
        selenita_2 =new Selenita(x_1,y_1);
        selenita_2.setRatioSelenita(CR.pcApxL(10));
        selenita_2.setColor(Color.rgb(0,255,151));
        //agregarlo al arreglo de objetos espaciales
        objetos_espaciales[9] = selenita_2;

        //venusiano 2
        x_1 = CR.pcApxX(50);
        y_1 = CR.pcApxY(50);
        venusiano_2 =new Venusiano(x_1,y_1);
        venusiano_2.setRatioVenuciano(CR.pcApxL(3));
        venusiano_2.setColor(Color.rgb(208,13,13));
        //agregarlo al arreglo de objetos espaciales
        objetos_espaciales[10] = venusiano_2;

        //desplegar la escena inicial
        pizarra.setEstadoEscena(objetos_espaciales);

    }


    /*
    Cambia el estado de movimiento de los objetos espaciales
    -X esta en porcentaje del annco del canvas
    -Y está en porcentaje del alto del canvas
    -Cualquier otra dimensión está en porcentaje del menor
     entre el alto y el ancho del canvas
    */
    private void cambiarEstadosEscenaPizarra(float tiempo) {


        //modelo de la marciano_1
        //movimiento parabólico
        float desplazamiento_x_1 = CR.pcApxX(5* tiempo);//MU
        float desplazamiento_y_1 = CR.pcApxY(-35*tiempo + 8.9f* tiempo*tiempo);//caída libre
        //mover al marciano
        marciano_1.mover(desplazamiento_x_1, desplazamiento_y_1);
        //aumentar tamañp de marciano_1
        aumento_1 = aumento_1+0.05f;
        marciano_1.setMagnificar(aumento_1);
        colorVariable=colorVariable-10;
        marciano_1.setColor(colorVariable);


        //movimiento selenita
        float teta_selenita = 150f * tiempo;//ecuación MCU
        float desplazamiento_selenita_x = CR.pcApxX(5*tiempo);
        float desplazamiento_selenita_y = 0;

        selenita_1.mover(desplazamiento_selenita_x,desplazamiento_selenita_y,teta_selenita);

        //
        float desplazamiento_venusiano1_x = CR.pcApxX(4 * tiempo);
        float desplazamiento_venusiano1_y = CR.pcApxY((float) Math.sin(10f*tiempo) * 5f);

        venusiano_1.mover(desplazamiento_venusiano1_x,desplazamiento_venusiano1_y);

        //
        float desplazamiento_selenita2_x = CR.pcApxX((float) Math.sin(2f*tiempo) * 15f);
        float desplazamiento_selenita2_y = CR.pcApxY(10f*tiempo);
        float teta_selenita2 = 1000f*tiempo;

        selenita_2.mover(desplazamiento_selenita2_x,desplazamiento_selenita2_y, teta_selenita2);

        //
        float desplazamiento_venusiano2_x = CR.pcApxL((float) (30f*Math.cos(2f*tiempo)));
        float desplazamiento_venusiano2_y = CR.pcApxL((float) (30f*Math.sin(2f*tiempo)));
        float teta_venusiano2 = 400f*tiempo;

        venusiano_2.mover(desplazamiento_venusiano2_x,desplazamiento_venusiano2_y, teta_venusiano2);

    }
}


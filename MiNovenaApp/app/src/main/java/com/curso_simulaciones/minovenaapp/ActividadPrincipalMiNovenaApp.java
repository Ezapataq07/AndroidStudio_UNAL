package com.curso_simulaciones.minovenaapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.minovenaapp.objetos_laboratorio.Rueda;
import com.curso_simulaciones.minovenaapp.vista.CR;
import com.curso_simulaciones.minovenaapp.vista.Pizarra;

public class ActividadPrincipalMiNovenaApp extends Activity implements Runnable {

    //dimensiones de pantalla
    private float ancho_pantalla, alto_pantalla;
    //Pizarra para dibujar
    private Pizarra pizarra;
    //objetos dibujables para Pizarra
    //arreglo que permite contener 10 ruedas
    private Rueda[] ruedas = new Rueda[10];
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
         las ruedas con responsividad se crearan dentro
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

        //el linear principal
        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setBackgroundColor(Color.BLACK);
        linearPrincipal.setWeightSum(10.0f);


        //linear secundario arriba
        LinearLayout linearArriba = new LinearLayout(this);



        //linear secundario abajo
        LinearLayout linearAbajo = new LinearLayout(this);
        linearAbajo.setBackgroundColor(Color.BLACK);


        //pegar linearArriba al princial
        LinearLayout.LayoutParams parametrosPegadoArriba = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosPegadoArriba.weight = 8.5f;
        parametrosPegadoArriba.setMargins(20,20,20,20);
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

        boolean ON =true;

        //hilo sin fin
        while (true) {

            try {
                Thread.sleep(periodo_muestreo);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

            /*
            hacer la creación de las ruedas con
            responsividad SÓLO cuando se garantice que la
            GUI se conformó completamente con el fin de que
            las dimensiones de la pizarra NO SEAN NULAS.
            */
            if(pizarra.getWidth()!=0&& ON==true) {
                crearRuedasConResponsividad();
                ON=false;
            }

            //ya creadas las ruedas con responsividad hacer efectiva la animación
            if (ON==false) {
                tiempo = tiempo + 0.05f;
                //cambio de estado de la escena física en la pizarra
                cambiarEstadosEscenaPizarra(tiempo);
            }


        }

    }

    private void crearRuedasConResponsividad(){

        CR.anchoPizarra = pizarra.getWidth();
        CR.altoPizarra = pizarra.getHeight();
        crearObjetosLaboratorio();

    }

    /*Crea los objetos cuerpo rígido con su estado inicial*/
    private void crearObjetosLaboratorio() {

        float radio = CR.pcApxL(80);

        //centro de las poleas
        float x_c = 0;
        float y_c = 0;


        //rueda 1 con su centro en esquina inferior derecha
        x_c = CR.pcApxX(100);
        y_c = CR.pcApxY(100);
        ruedas[0] = new Rueda(x_c, y_c, CR.pcApxL(50));
        ruedas[0].setColorPolea(Color.YELLOW, Color.BLUE,Color.RED);


        //rueda 2con su centro en el centro del canvas
        x_c = CR.pcApxX(50);
        y_c = CR.pcApxY(50);
        ruedas[1] = new Rueda(x_c, y_c, CR.pcApxL(70));
        ruedas[1].setColorPolea(Color.rgb(0, 82, 159),Color.rgb(254, 190, 16),Color.WHITE);

        //rueda 3 con su centro en X = 0  e
        // Y = 0.25 de la altura del canvas
        x_c = CR.pcApxX(0);
        y_c = CR.pcApxY(25);
        ruedas[2] = new Rueda(x_c, y_c,  CR.pcApxL(60));
        ruedas[2].setColorPolea(Color.RED,Color.BLACK,Color.BLUE);

        //rueda 4 con su centro en X = 0 e
        // Y = 0.75 de la altura del canvas
        x_c = CR.pcApxX(0);
        y_c = CR.pcApxY(75);
        ruedas[3] = new Rueda(x_c, y_c, CR.pcApxL(40));
        ruedas[3].setColorPolea(Color.rgb(0, 77, 152), Color.rgb(237, 187, 0),Color.rgb(168, 19, 62));

        pizarra.setEstadoEscena(ruedas);

    }




    /*
    Cambia el esatado de los objetos polea y lo comunica a pizarra
    */
    private void cambiarEstadosEscenaPizarra(float tiempo) {



        //rueda 1

        float teta_0 = -200f * tiempo;//ecuación MCU

        //rueda 2
        float teta_1 = 200f * tiempo;//ecuación MCU


        //rueda 3
        float desplazamiento_2_x = CR.pcApxX(10*tiempo);
        float desplazamiento_2_y = 0;

        //rueda 4
        float teta_3 = 150f * tiempo;//ecuación MCU
        float desplazamiento_3_x = CR.pcApxX(5*tiempo);
        float desplazamiento_3_y = 0;

        //mover las ruedas

        //mover rueda 1
        ruedas[0].moverPolea(teta_0);
        //mover rueda 2
        ruedas[1].moverPolea(teta_1);
        //mover rueda 3
        ruedas[2].moverPolea(desplazamiento_2_x,desplazamiento_2_y);
        //mover rueda 4
        ruedas[3].moverPolea(desplazamiento_3_x,desplazamiento_3_y,teta_3);
    }

}

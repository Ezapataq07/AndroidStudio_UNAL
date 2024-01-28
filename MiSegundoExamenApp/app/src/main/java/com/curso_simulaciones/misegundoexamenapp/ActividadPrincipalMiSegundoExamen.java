package com.curso_simulaciones.misegundoexamenapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.misegundoexamenapp.objetos_laboratorio.Bala;
import com.curso_simulaciones.misegundoexamenapp.vista.CR;
import com.curso_simulaciones.misegundoexamenapp.vista.Pizarra;

public class ActividadPrincipalMiSegundoExamen extends Activity implements Runnable {

    //dimensiones de pantalla
    private float ancho_pantalla, alto_pantalla;
    //Pizarra para dibujar
    private Pizarra pizarra;
    //objetos dibujables para Pizarra
    //arreglo que permite contener 10 ruedas
    private Bala[] balas = new Bala[10];
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
        float l_c = 0;


        //rueda 1 con su centro en esquina inferior derecha
        x_c = CR.pcApxX(100);
        y_c = CR.pcApxY(100);
        l_c = CR.pcApxL(10);
        balas[0] = new Bala(x_c, y_c, CR.pcApxL(20),l_c);
        balas[0].setColorPolea(Color.YELLOW, Color.BLUE,Color.RED,Color.GREEN);


        //rueda 2con su centro en el centro del canvas
        x_c = CR.pcApxX(50);
        y_c = CR.pcApxY(50);
        l_c = CR.pcApxL(15);
        balas[1] = new Bala(x_c, y_c, CR.pcApxL(15),l_c);
        balas[1].setColorPolea(Color.rgb(0, 82, 159),Color.rgb(254, 190, 16),Color.BLACK,Color.RED);

        //rueda 3 con su centro en X = 0  e
        // Y = 0.25 de la altura del canvas
        x_c = CR.pcApxX(0);
        y_c = CR.pcApxY(25);
        l_c = CR.pcApxL(10);
        balas[2] = new Bala(x_c, y_c,  CR.pcApxL(22),l_c);
        balas[2].setColorPolea(Color.RED,Color.BLACK,Color.BLUE,Color.CYAN);

        //rueda 4 con su centro en X = 0 e
        // Y = 0.75 de la altura del canvas
        x_c = CR.pcApxX(0);
        y_c = CR.pcApxY(75);
        l_c = CR.pcApxL(20);
        balas[3] = new Bala(x_c, y_c, CR.pcApxL(30),l_c);
        balas[3].setColorPolea(Color.rgb(0, 77, 152), Color.rgb(237, 187, 0),Color.rgb(168, 19, 62),Color.GRAY);

        //bala 5 con su centro en esquina inferior izquierda
        x_c = CR.pcApxX(0);
        y_c = CR.pcApxY(100);
        l_c = CR.pcApxL(10);
        balas[4] = new Bala(x_c, y_c, CR.pcApxL(20),l_c);
        balas[4].setColorPolea(Color.GREEN, Color.YELLOW,Color.BLACK,Color.GREEN);

        //bala 6 con su centro en esquina superior izquierda
        x_c = CR.pcApxX(0);
        y_c = CR.pcApxY(0);
        l_c = CR.pcApxL(20);
        balas[5] = new Bala(x_c, y_c, CR.pcApxL(5),l_c);
        balas[5].setColorPolea(Color.CYAN, Color.MAGENTA,Color.BLACK,Color.RED);

        //bala 7 con su centro en esquina superior derecha
        x_c = CR.pcApxX(100);
        y_c = CR.pcApxY(0);
        l_c = CR.pcApxL(5);
        balas[6] = new Bala(x_c, y_c, CR.pcApxL(20),l_c);
        balas[6].setColorPolea(Color.WHITE, Color.BLACK,Color.BLUE,Color.RED);


        pizarra.setEstadoEscena(balas);

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

        //bala 5
        float teta_4 = -100f * tiempo;//ecuación MCU

        //bala 6
        float teta_5 = 400f * tiempo;//ecuación MCU

        //bala 7
        float teta_6 = 50f * tiempo;//ecuación MCU

        //mover las ruedas

        //mover rueda 1
        balas[0].moverPolea(teta_0);
        //mover rueda 2
        balas[1].moverPolea(teta_1);
        //mover rueda 3
        balas[2].moverPolea(desplazamiento_2_x,desplazamiento_2_y);
        //mover rueda 4
        balas[3].moverPolea(desplazamiento_3_x,desplazamiento_3_y,teta_3);
        //mover bala 5
        balas[4].moverPolea(teta_4);
        //mover bala 6
        balas[5].moverPolea(teta_5);
        //mover bala 7
        balas[6].moverPolea(teta_6);
    }
}

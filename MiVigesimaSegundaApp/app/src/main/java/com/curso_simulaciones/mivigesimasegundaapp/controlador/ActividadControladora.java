package com.curso_simulaciones.mivigesimasegundaapp.controlador;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.mivigesimasegundaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimasegundaapp.vista.CR;
import com.curso_simulaciones.mivigesimasegundaapp.vista.Pizarra;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Cuerda;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.CuerpoRectangular;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Marca;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Masa;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.ObjetoLaboratorio;
import com.curso_simulaciones.simulphysics.objetos_laboratorio.Polea;

public class ActividadControladora extends Activity {


    private Pizarra pizarra;

    private Masa masa_1, masa_2;
    private Polea polea_1, polea_2, polea_3;
    private CuerpoRectangular barra, bloque1, bloque2;
    private Cuerda cuerda_1, cuerda_2, cuerda_3, cuerda_4, cuerda_5, cuerda_6;
    private Marca marca1, marca2;

    private ObjetoLaboratorio[] objetos = new ObjetoLaboratorio[20];


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


    }//fin onCreate


    /*Método auxiliar para asuntos de resolución*/
    private void gestionarResolucion() {

        /*
        Según el diseño de la GUI se puede anticipar cuál es la
        dimensión de la pizarra. En este caso es el 100% del ancho
        de la pantalla y el 100% del alto de la misma
        */
        CR.anchoPizarra = AlmacenDatosRAM.ancho_pantalla;
        CR.altoPizarra = AlmacenDatosRAM.alto_pantalla;

    }

    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {


        //crear pizarra sabiendo de antemano sus dimensiones
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.WHITE);

        crearObjetosLaboratorio();


    }//fin crearElementosGUI


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        //el linear principal
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);


        //pegar pizarra a linearArriba
        linear_principal.addView(pizarra);

        return linear_principal;

    }//fin crearGUI



    /*
   Crea los objetos cuerpo rígido con su estado inicia
   -X esta en porcentaje del annco del canvas
   -Y está en porcentaje del alto del canvas
   -Cualquier otra dimensión está en porcentaje del menor
    entre el alto y el ancho del canvas
   */
    private void crearObjetosLaboratorio() {

         /*
          Coordenadas de puntos básicos y
          dimensiones de los elementos
         */
        //radio de las poleas
        float radio= CR.pcApxL(6);

        //dimensiones de cada masa: m1 y m2
        float ancho_bloque= 2.5f*radio;
        float alto_bloque=1.5f*radio;

        //coordenadas de las masas
        //abscisa y ordenada del centro de la masa 1
        float x1= CR.pcApxX(50f);
        float y1= CR.pcApxY(80f);
        //abscisa y ordenada del centro de la masa 2
        float x2= x1-10f*radio;
        float y2=CR.pcApxY(15f);

        //coordenadas de las poleas
        //abscisa y ordenada del centro de la polea 1
        float xp1=x1;
        float yp1=CR.pcApxY(60f);
        //abscisa y ordenada del centro de la polea 2
        float xp2=x1-radio;
        float yp2= yp1-3*radio;

        // centro de polea 3
        float xp3 = xp2 - 2*radio;
        float yp3 = y2+CR.pcApxX(1f);

        /*
        Creación de objetos físicos y dibujo del
        estado inicial de la escena física
       */
        //dos poleas
        //polea 1 (polea azul)
        polea_1=new Polea(xp1,yp1,radio);
        polea_1.setColor(Color.GREEN);
        polea_1.setGrosorLinea(CR.pcApxL(0.5f));
        polea_1.rotarEje(180);
        objetos[0]=polea_1;

        //polea 2 (polea roja)
        polea_2=new Polea(xp2,yp2,radio);
        polea_2.setColor(Color.RED);
        polea_2.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[1]=polea_2;

        polea_3=new Polea(xp3, yp3,radio);
        polea_3.setColor(Color.BLUE);
        polea_3.setGrosorLinea(CR.pcApxL(0.5f));
        polea_3.rotarEje(45);
        objetos[2]=polea_3;



        //barra superior que sostiene el sistem
        barra=new CuerpoRectangular(CR.pcApxX(50),CR.pcApxY(5),CR.pcApxL(30),CR.pcApxL(5));
        barra.setColor(Color.rgb(255, 195, 0 ));
        barra.setGrosorLinea(0);
        objetos[3]=barra;


        //4  cuerdas
        cuerda_1=new Cuerda(xp1+radio,yp1, xp1+radio,CR.pcApxY(5)+CR.pcApxL(5)/2);
        cuerda_1.setColor(Color.BLACK);
        cuerda_1.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[4]=cuerda_1;

        cuerda_2=new Cuerda(xp1-radio,yp1,xp1-radio,yp2+radio);
        cuerda_2.setColor(Color.BLACK);
        cuerda_2.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[5]=cuerda_2;

        cuerda_3=new Cuerda(xp1,yp2,xp1,CR.pcApxY(5)+CR.pcApxL(5)/2);
        cuerda_3.setColor(Color.BLACK);
        cuerda_3.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[6]=cuerda_3;

        //cuerda_4=new Cuerda(xp2,yp2+0.5f*alto_barrita,xp2,y2-0.5f*alto_bloque);
        cuerda_4=new Cuerda(xp2-radio,yp2,xp2-radio,yp3);
        cuerda_4.setColor(Color.BLACK);
        cuerda_4.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[7]=cuerda_4;

        cuerda_5=new Cuerda(xp1,yp1+radio,xp1,y1-alto_bloque/2);
        cuerda_5.setColor(Color.BLACK);
        cuerda_5.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[8]=cuerda_5;

        cuerda_6=new Cuerda(xp3,yp3-radio,x2+ancho_bloque/2,yp3-radio);
        cuerda_6.setColor(Color.BLACK);
        cuerda_6.setGrosorLinea(CR.pcApxL(0.5f));
        objetos[9]=cuerda_6;

        //2 masas
        //masa 2
        masa_2=new Masa(x2,y2,ancho_bloque,ancho_bloque);
        masa_2.setColor(Color.RED);
        masa_2.setColorMarca(Color.BLACK);
        masa_2.setMarca("M1");
        objetos[10]=masa_2;

        //masa 1
        masa_1=new Masa(x1,y1,ancho_bloque,alto_bloque);
        masa_1.setColor(Color.RED);
        masa_1.setColorMarca(Color.BLACK);
        masa_1.setMarca("M2");
        objetos[11]=masa_1;

        bloque1=new CuerpoRectangular(CR.pcApxX(50.5f)-9*radio,CR.pcApxY(60f),10*radio,CR.pcApxL(76));
        bloque1.setColor(Color.rgb(255, 195, 0 ));
        bloque1.setGrosorLinea(0);
        objetos[12]=bloque1;

        bloque2=new CuerpoRectangular(CR.pcApxX(50f),CR.pcApxY(94.5f),7.7f*radio,CR.pcApxL(7));
        bloque2.setColor(Color.rgb(255, 195, 0 ));
        bloque2.setGrosorLinea(0);
        objetos[13]=bloque2;

        //una marca
        //despliega el nombre del Eje y
        marca1 =new Marca("P2",xp1+1.5f*radio,yp1+.25f*radio);
        marca1.setColor(Color.BLACK);
        marca1.setTamano(CR.pcApxL(5f));
        marca1.rotar(0);
        objetos[14]= marca1;

        marca2 =new Marca("P1",xp2-2.5f*radio,yp2+.25f*radio);
        marca2.setColor(Color.BLACK);
        marca2.setTamano(CR.pcApxL(5f));
        marca2.rotar(0);
        objetos[15]= marca2;




        //desplegar la escena inicial
        pizarra.setEstadoEscena(objetos);

    }


}


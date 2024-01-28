package com.curso_simulaciones.midecimanovenaapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.curso_simulaciones.midecimanovenaapp.actividades_secundarias.ActividadSecundaria_1;
import com.curso_simulaciones.midecimanovenaapp.actividades_secundarias.ActividadSecundaria_2;
import com.curso_simulaciones.midecimanovenaapp.actividades_secundarias.ActividadSecundaria_3;
import com.curso_simulaciones.midecimanovenaapp.datos.AlmacenDatosRAM;

public class ActividadPrincipalMiDecimaNovenaApp extends Activity {
    private int tamanoLetraResolucionIncluida;
    private Button botonUno, botonDos, botonTres, botonCuatro;

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

        //para administrar los eventos
        eventos();


    } //fin del método onCreate

    private void gestionarResolucion() {

        //independencia de la resolución de la pantalla
        DisplayMetrics displayMetrics = this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;
        int dimensionReferencia;

        //tomar el menor valor entre alto y ancho de pantalla
        if (alto > ancho) {

            dimensionReferencia = ancho;
        } else {

            dimensionReferencia = alto;
        }


        //una estimación de un buen tamaño
        int tamanoLetra = dimensionReferencia / 20;


        //tamano de letra para usar acomodado a la resolución de pantalla
        tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);

        //guardar en el almacen de datos para que otras clases la accedan fácilmente
        AlmacenDatosRAM.tamanoLetraResolucionIncluida = tamanoLetraResolucionIncluida;


    }//fin método gestionarResolucion()

    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {



   /*
     1. El tamaño a usar de la letra tiene corrección de
        resolución y tamaño de pantalla.

     2. Se usa un diseño de color de botón especial
        PorterDuff.Mode.MULTIPLY. Podría usarse uno más sencillo
        Como por ejemplo boton.setBackgroundColor(Color.RED)
    */

        botonUno = new Button(this);
        botonUno.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonUno.setText("1");
        botonUno.getBackground().setColorFilter(Color.rgb(0, 150, 255), PorterDuff.Mode.MULTIPLY);

        botonDos = new Button(this);
        botonDos.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonDos.setText("2");
        botonDos.getBackground().setColorFilter(Color.rgb(0, 150, 255), PorterDuff.Mode.MULTIPLY);

        botonTres = new Button(this);
        botonTres.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonTres.setText("3");
        botonTres.getBackground().setColorFilter(Color.rgb(0, 150, 255), PorterDuff.Mode.MULTIPLY);

        botonCuatro = new Button(this);
        botonCuatro.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonCuatro.setText("4");
        botonCuatro.getBackground().setColorFilter(Color.rgb(0, 150, 255), PorterDuff.Mode.MULTIPLY);
        botonCuatro.setEnabled(false);
    }//fin método crearElementosGUI

    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {

        LinearLayout linearPrincipal = new LinearLayout(this);

        linearPrincipal.setBackgroundColor(Color.BLACK);
        //los componentes se agregarán verticalmente
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        //para definir los pesos de las filas que se agregaran
        linearPrincipal.setWeightSum(10.0f);

        // Superior
        LinearLayout linearSuperior = new LinearLayout(this);
        linearSuperior.setBackgroundColor(Color.RED);
        // Fondo Superior
        Drawable fondo = getResources().getDrawable(R.drawable.los100);
        linearSuperior.setBackgroundDrawable(fondo);

        // Inferior
        LinearLayout linearInferior = new LinearLayout(this);
        linearInferior.setBackgroundColor(Color.argb(90,10,10,200));


        // Pegar Superior

        LinearLayout.LayoutParams parametrosSuperior = new  LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosSuperior.weight = 7.0f;
        parametrosSuperior.setMargins(10,10,10,10);

        // Pegar Inferior

        LinearLayout.LayoutParams parametrosInferior = new  LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosInferior.weight = 3.0f;
        parametrosInferior.setMargins(10,10,10,10);
        // Se pegan a Linear Principal
        linearPrincipal.addView(linearSuperior, parametrosSuperior);
        linearPrincipal.addView(linearInferior, parametrosInferior);

        // Inferior Izquierdo
        LinearLayout linearInferiorIzquierdo = new LinearLayout(this);
        linearInferiorIzquierdo.setBackgroundColor(Color.GREEN);
        // Fondo Inferior Izquierdo
        fondo = getResources().getDrawable(R.drawable.maywe);
        linearInferiorIzquierdo.setBackgroundDrawable(fondo);

        // Inferior derecho
        LinearLayout linearInferiorDerecho = new LinearLayout(this);
        linearInferiorDerecho.setBackgroundColor(Color.rgb(70,70,70));

        // Pesos Inferior
        linearInferior.setOrientation(LinearLayout.HORIZONTAL);
        linearInferior.setWeightSum(10.0f);


        // Pegar inferior Izquierdo
        LinearLayout.LayoutParams parametrosInferiorIzquierdo = new  LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosInferiorIzquierdo.weight = 8.0f;
        parametrosInferiorIzquierdo.setMargins(10,10,10,10);

        // Pegar Inferior Derecho
        LinearLayout.LayoutParams parametrosInferiorDerecho = new  LinearLayout.LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosInferiorDerecho.weight = 2.0f;
        parametrosInferiorDerecho.setMargins(10,10,10,10);


        linearInferior.addView(linearInferiorIzquierdo, parametrosInferiorIzquierdo);
        linearInferior.addView(linearInferiorDerecho, parametrosInferiorDerecho);

        linearInferiorDerecho.setOrientation(LinearLayout.VERTICAL);
        linearInferiorDerecho.setWeightSum(4.0f);





        // pegado de bontones

        LinearLayout.LayoutParams parametros_pegado_botones = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametros_pegado_botones.weight = 1.0f;
        linearInferiorDerecho.addView(botonUno, parametros_pegado_botones);
        linearInferiorDerecho.addView(botonDos, parametros_pegado_botones);
        linearInferiorDerecho.addView(botonTres, parametros_pegado_botones);
        linearInferiorDerecho.addView(botonCuatro, parametros_pegado_botones);


        return linearPrincipal;


    }// fin crear GUI


    /*Administra los eventos de la GUI*/
    private void eventos() {

        //evento del boton con etiqueta UNO
        botonUno.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarActividadSecundaria_1();
            }
        });


        //evento del boton con etiqueta UNO
        botonDos.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarActividadSecundaria_2();
            }
        });

        //evento del boton con etiqueta UNO
        botonTres.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarActividadSecundaria_3();
            }
        });

    } // Fin metodo eventos

    //métodos que lanzar las actividades secundarias
    private void lanzarActividadSecundaria_1() {


        Intent intent = new Intent(this, ActividadSecundaria_1.class);
        startActivity(intent);

    }


    private void lanzarActividadSecundaria_2() {


        Intent intent = new Intent(this, ActividadSecundaria_2.class);
        startActivity(intent);

    }

    private void lanzarActividadSecundaria_3() {


        Intent intent = new Intent(this, ActividadSecundaria_3.class);
        startActivity(intent);
    }

    /* Métodos automático*/
    protected void onDestroy() {
        //Volver los valores de los daos a su estado por defecto
        AlmacenDatosRAM.nombre1 = "Escriba aquí";
        AlmacenDatosRAM.nombre2 = "Escriba aquí";


        this.finish();
        super.onDestroy();
    }


    /* Métodos automáticos*/

    protected void onResume(){
        super.onResume();

        if(AlmacenDatosRAM.habilitar_boton_tres==true){
            botonTres.setEnabled(true);
        } else {

            botonTres.setEnabled(false);

        }
    }

}

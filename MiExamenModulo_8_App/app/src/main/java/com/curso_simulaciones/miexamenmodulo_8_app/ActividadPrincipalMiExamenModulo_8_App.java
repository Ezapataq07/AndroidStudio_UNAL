package com.curso_simulaciones.miexamenmodulo_8_app;

import android.app.Activity;
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

import com.curso_simulaciones.miexamenmodulo_8_app.vista.Pizarra;

public class ActividadPrincipalMiExamenModulo_8_App extends Activity {

    private int tamanoLetraResolucionIncluida;
    private Button botonUno, botonDos, botonTres, botonCuatro, botonCinco;
    private LinearLayout linearIzquierdo;
    Pizarra pizarra;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        gestionarResolucion();
        //para crear elementos de la GUI
        crearElementosGUI();


        /*
        Para informar cómo se debe pegar el administrador de
        diseño LinearLayout obtenido con el método crearGui()
        */
        ViewGroup.LayoutParams parametro_layout_principal = new           ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,          ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);

        eventos();



    }//fin del método onCreate


    private void gestionarResolucion(){

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
    }

    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI() {


 /*
 1. El tamaño a usar de la letra tiene corrección de
    resolución y tamaño de pantalla.
*/

        botonUno = new Button(this);
        botonUno.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonUno.setText("UNO");
        botonUno.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);

        botonDos = new Button(this);
        botonDos.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonDos.setText("DOS");
        botonDos.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        botonDos.setEnabled(false);

        botonTres = new Button(this);
        botonTres.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonTres.setText("TRES");
        botonTres.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        botonTres.setEnabled(false);

        botonCuatro = new Button(this);
        botonCuatro.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonCuatro.setText("CUATRO");
        botonCuatro.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        botonCuatro.setEnabled(false);

        botonCinco = new Button(this);
        botonCinco.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        botonCinco.setText("CINCO");
        botonCinco.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        botonCinco.setEnabled(false);

        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.YELLOW);
    }//fin método crearElementosGUI



    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI(){

        LinearLayout linearPrincipal = new LinearLayout(this);

        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setWeightSum(10f);
        linearPrincipal.setBackgroundColor(Color.BLACK);


        LinearLayout linearSuperior = new LinearLayout(this);
        linearSuperior.setBackgroundColor(Color.WHITE);

        linearSuperior.setOrientation(LinearLayout.HORIZONTAL);
        linearSuperior.setWeightSum(5f);

        LinearLayout linearInferior = new LinearLayout(this);
        linearInferior.setBackgroundColor(Color.BLUE);

        linearInferior.setOrientation(LinearLayout.HORIZONTAL);
        linearInferior.setWeightSum(10f);


        LinearLayout.LayoutParams parametrosSuperior = new  LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametrosSuperior.weight = 2.0f;
        parametrosSuperior.setMargins(10,10,10,10);

        linearPrincipal.addView(linearSuperior,parametrosSuperior);

        LinearLayout.LayoutParams parametrosInferior = new  LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosInferior.weight = 8.0f;
        parametrosInferior.setMargins(10,10,10,10);

        linearPrincipal.addView(linearInferior,parametrosInferior);

        LinearLayout linearInferiorDerecho = new LinearLayout(this);
        Drawable fondo = getResources().getDrawable(R.drawable.got);
        linearInferiorDerecho.setBackgroundDrawable(fondo);

        LinearLayout linearInferiorIzquierdo = new LinearLayout(this);




        LinearLayout.LayoutParams parametrosInferiorDerecho = new  LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosInferiorDerecho.weight = 3.0f;
        parametrosInferiorDerecho.setMargins(10,10,10,10);

        LinearLayout.LayoutParams parametrosInferiorIzquierdo = new  LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosInferiorIzquierdo.weight = 7.0f;
        parametrosInferiorIzquierdo.setMargins(10,10,10,10);

        linearInferior.addView(linearInferiorIzquierdo, parametrosInferiorIzquierdo);
        linearInferior.addView(linearInferiorDerecho, parametrosInferiorDerecho);

        linearInferiorIzquierdo.addView(pizarra);

        LinearLayout.LayoutParams parametros_pegado_botones = new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_botones.weight = 1.0f;
        parametros_pegado_botones.setMargins(10,10,10,10);
        linearSuperior.addView(botonUno, parametros_pegado_botones);
        linearSuperior.addView(botonDos, parametros_pegado_botones);
        linearSuperior.addView(botonTres, parametros_pegado_botones);
        linearSuperior.addView(botonCuatro, parametros_pegado_botones);
        linearSuperior.addView(botonCinco, parametros_pegado_botones);


        return linearPrincipal;
    }//fin método crearGUI

    /*Administra los eventos de la GUI*/
    private void eventos() {

        //evento del boton con etiqueta UNO
        botonUno.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                pizarra.setBackgroundColor(Color.WHITE);
                botonUno.setEnabled(false);
                botonCinco.setEnabled(true);
            }
        });

        //evento del boton con etiqueta DOS
        botonDos.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

            }
        });

        //evento del boton con etiqueta TRES
        botonTres.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

            }
        });

        //evento del boton con etiqueta CUATRO
        botonCuatro.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

            }
        });

        //evento del boton con etiqueta CINCO
        botonCinco.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                botonUno.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
                botonDos.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
                botonTres.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
                botonCuatro.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
                botonCinco.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
//                botonUno.setBackgroundColor(Color.BLUE);
//                botonDos.setBackgroundColor(Color.BLUE);
//                botonTres.setBackgroundColor(Color.BLUE);
//                botonCuatro.setBackgroundColor(Color.BLUE);
//                botonCinco.setBackgroundColor(Color.BLUE);

            }
        });
    }



    protected void onDestroy() {

        this.finish();

        super.onDestroy();

    }//fin del método onDestroy

}

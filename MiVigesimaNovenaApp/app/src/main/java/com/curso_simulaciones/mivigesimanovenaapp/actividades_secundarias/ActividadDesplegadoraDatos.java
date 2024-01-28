package com.curso_simulaciones.mivigesimanovenaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.mivigesimanovenaapp.R;
import com.curso_simulaciones.mivigesimanovenaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimanovenaapp.utilidades.Boton;
import com.curso_simulaciones.mivigesimanovenaapp.utilidades.Gaussimetro;
import com.curso_simulaciones.mivigesimanovenaapp.utilidades.Graficador;

public class ActividadDesplegadoraDatos extends Activity {


    private Boton Bx, By, Bz, B;

    private Gaussimetro gaussimetro;
    public Graficador graficador;

    /*Hilo responsable de la animación
     El trabajo de animación es mejor manejarlo en hilo
     aparte para evitar bloqueos de la aplicación
     debido al manejo simultáneo de la GUI con la Acivity
    */
    private HiloAnimacion hilo;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //para crear elementos de la GUI
        crearElementosGUI();

        /*
        Para informar cómo se debe pegar el administrador de
        diseño LinearLayout obtenido con el método crearGui()
        */
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        //pegar el contenedor con la GUI
        this.setContentView(crearGUI(), parametro_layout_principal);


        eventos();

        hilo = new HiloAnimacion(this);
        hilo.start();

    }//fin del método onCreate

    private void crearElementosGUI() {


        //botones
        Bx = new Boton(this);
        Bx.setImagen(R.drawable.ax);
        By = new Boton(this);
        By.setImagen(R.drawable.ay);
        Bz = new Boton(this);
        Bz.setImagen(R.drawable.az);
        B = new Boton(this);
        B.setImagen(R.drawable.a);

        //gauge
        gaussimetro = new Gaussimetro(this);

        //graficador
        graficador = new Graficador(this);
        //se está muestreando cada segundo (1000 ms)
        graficador.setTituloEjeX("Tiempo (s)");
        graficador.setTituloEjeY("Campo magnético Bx (uT)");
        graficador.setGrosorLinea(2f);
        graficador.setColorLinea(Color.RED);
        graficador.setColorValores(Color.YELLOW);
        graficador.setColorMarcadores(Color.GREEN);
        graficador.setColorFondo(Color.BLACK);
        graficador.setColorTextoEjes(Color.WHITE);


    }


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {


        LinearLayout linear_layout_principal = new LinearLayout(this);
        linear_layout_principal.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.FILL);
        linear_layout_principal.setBackgroundColor(Color.BLACK);
        linear_layout_principal.setPadding(20, 20, 20, 20);
        linear_layout_principal.setWeightSum(10);


        //LinearLayout primera columna
        LinearLayout linear_layout_primera_columna = new LinearLayout(this);
        linear_layout_primera_columna.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_primera_columna.setGravity(Gravity.FILL);
        linear_layout_primera_columna.setBackgroundColor(Color.rgb(245, 245, 245));
        //parámetro para pegar la primera columna al principal
        LinearLayout.LayoutParams parametros_primera_columna = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_primera_columna.weight = 4.0f;
        parametros_primera_columna.setMargins(10, 10, 5, 10);
        linear_layout_primera_columna.setLayoutParams(parametros_primera_columna);


        //LinearLayout segunda columna
        LinearLayout linear_layout_segunda_columna = new LinearLayout(this);
        linear_layout_segunda_columna.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_segunda_columna.setGravity(Gravity.FILL);
        linear_layout_segunda_columna.setBackgroundColor(Color.rgb(245, 245, 245));
        linear_layout_segunda_columna.setWeightSum(1.0f);
        //parámetro para pegar la segunda columna al principal
        LinearLayout.LayoutParams parametros_segunda_columna = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_segunda_columna.weight = 4.0f;
        parametros_segunda_columna.setMargins(5, 10, 10, 10);
        linear_layout_segunda_columna.setLayoutParams(parametros_segunda_columna);

        //LinearLayout tercera columna
        LinearLayout linear_layout_tercera_columna = new LinearLayout(this);
        linear_layout_tercera_columna.setOrientation(LinearLayout.VERTICAL);
        linear_layout_tercera_columna.setGravity(Gravity.FILL);
        linear_layout_tercera_columna.setBackgroundColor(Color.rgb(40,40,40));
        linear_layout_tercera_columna.setWeightSum(4.0f);
        //parámetro para pegar la tercera columna al principal
        LinearLayout.LayoutParams parametros_tercera_columna = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_tercera_columna.weight = 2.0f;
        parametros_tercera_columna.setMargins(5, 10, 10, 10);
        linear_layout_tercera_columna.setLayoutParams(parametros_tercera_columna);


        //peado de las tres columnas al principal
        linear_layout_principal.addView(linear_layout_primera_columna);
        linear_layout_principal.addView(linear_layout_segunda_columna);
        linear_layout_principal.addView(linear_layout_tercera_columna);


        //pegar gauge primera columna
        linear_layout_primera_columna.addView(gaussimetro);

        //pegar grafico en segunda columna
        LinearLayout.LayoutParams parametros_grafica = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_grafica.weight = 1.0f;
        linear_layout_segunda_columna.addView(graficador, parametros_grafica);

        //pegar botones tercera columna
        LinearLayout.LayoutParams parametros_pegado_boton = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_pegado_boton.weight = 1.0f;
        linear_layout_tercera_columna.addView(Bx, parametros_pegado_boton);
        linear_layout_tercera_columna.addView(By, parametros_pegado_boton);
        linear_layout_tercera_columna.addView(Bz, parametros_pegado_boton);
        linear_layout_tercera_columna.addView(B, parametros_pegado_boton);


        return linear_layout_principal;


    }


    private void eventos() {

        Bx.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                lanzarDatosBx();


            }
        });

        By.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarDatosBy();
            }
        });

        Bz.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarDatosBz();
            }
        });

        B.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarDatosB();
            }
        });


    }

    private void lanzarDatosBx() {

        resetear();
        gaussimetro.setComponenteGaussimetro(1);
        gaussimetro.setRango(-2000,2000);

        graficador.setTituloEjeY("Campo magnético Bx (uT)");
        hilo.corriendo = true;

    }

    private void lanzarDatosBy() {

        resetear();
        gaussimetro.setComponenteGaussimetro(2);
        gaussimetro.setRango(-2000,2000);
        graficador.setTituloEjeY("Campo magnético By (uT)");
        hilo.corriendo = true;

    }

    private void lanzarDatosBz() {

        resetear();
        gaussimetro.setComponenteGaussimetro(3);
        gaussimetro.setRango(-2000,2000);
        graficador.setTituloEjeY("Campo magnético Bz (uT)");
        hilo.corriendo = true;

    }

    private void lanzarDatosB() {

        resetear();
        gaussimetro.setComponenteGaussimetro(4);
        gaussimetro.setRango(0, 3000);
        graficador.setTituloEjeY("Campo magnético B (uT)");
        hilo.corriendo = true;

    }





    protected void onPause() {

        hilo.corriendo = false;
        AlmacenDatosRAM.datos.clear();
        hilo.contador = 0;
        super.onPause();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        hilo.corriendo = true;
    }


    private void resetear() {

        hilo.corriendo = false;
        AlmacenDatosRAM.datos.clear();
        hilo.tiempo = 0;
        hilo.contador = 0;

    }

}


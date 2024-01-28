package com.curso_simulaciones.micuadragesimaapp;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.micuadragesimaapp.actividades_secundarias.ActividadComoClienteBluetooth;
import com.curso_simulaciones.micuadragesimaapp.actividades_secundarias.ActividadConfiguracion;
import com.curso_simulaciones.micuadragesimaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.micuadragesimaapp.utilidades.Boton;

public class ActividadPrincipalMiCuadragesimaApp extends Activity {


    private Boton entrar, salir, ajustar;

    private BluetoothAdapter BA;

    LinearLayout linear_layout_segunda_fila;




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gestionarResolucion();

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



    }//fin del método onCreate

    /*Método auxiliar para asuntos de resolución*/
    private void gestionarResolucion() {

        //independencia de la resolución de la pantalla
        DisplayMetrics displayMetrics = this.getApplicationContext().getResources().getDisplayMetrics();
        int alto = displayMetrics.heightPixels;
        int ancho = displayMetrics.widthPixels;
        AlmacenDatosRAM.ancho=ancho;
        AlmacenDatosRAM.alto=alto;
        int dimensionReferencia;

        //tomar el menor valor entre alto y ancho de pantalla
        if (alto > ancho) {

            dimensionReferencia = ancho;
        } else {

            dimensionReferencia = alto;
        }

        AlmacenDatosRAM.dimensionReferencia =dimensionReferencia;

        //una estimación de un buen tamaño
        int tamanoLetra = dimensionReferencia / 20;


        //tamano de letra para usar acomodado a la resolución de pantalla
        int tamanoLetraResolucionIncluida = (int) (tamanoLetra / displayMetrics.scaledDensity);

        //guardar en el almacen de datos para que otras clases la accedan fácilmente
        AlmacenDatosRAM.tamanoLetraResolucionIncluida = tamanoLetraResolucionIncluida;

    }


    private void crearElementosGUI() {

        entrar = new Boton(this);
        entrar.setImagen(R.drawable.entrar);

        salir = new Boton(this);
        salir.setImagen(R.drawable.salir);

        ajustar = new Boton(this);
        ajustar.setImagen(R.drawable.configuracion);

    }


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crearGUI() {


        LinearLayout linear_layout_principal = new LinearLayout(this);
        linear_layout_principal.setOrientation(LinearLayout.VERTICAL);
        linear_layout_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_layout_principal.setGravity(Gravity.FILL);
        linear_layout_principal.setBackgroundColor(Color.WHITE);
        linear_layout_principal.setWeightSum(10);


        //LinearLayout primera fila
        LinearLayout linear_layout_primera_fila = new LinearLayout(this);
        linear_layout_primera_fila.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_primera_fila.setGravity(Gravity.FILL);
        linear_layout_primera_fila.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams parametros_primera_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);//ViewGroup.LayoutParams.WRAP_CONTENT);
        parametros_primera_fila.weight = 8.0f;
        linear_layout_primera_fila.setLayoutParams(parametros_primera_fila);


        //fondo primera fila
        Drawable fondo = getResources().getDrawable(R.drawable.luxometro_android);
        linear_layout_primera_fila.setBackgroundDrawable(fondo);

        //LinearLayout segunda fila
        linear_layout_segunda_fila = new LinearLayout(this);
        linear_layout_segunda_fila.setOrientation(LinearLayout.HORIZONTAL);
        linear_layout_segunda_fila.setGravity(Gravity.FILL);
        LinearLayout.LayoutParams parametros_segunda_fila = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametros_segunda_fila.weight = 2.0f;
        linear_layout_segunda_fila.setWeightSum(2.0f);
        linear_layout_segunda_fila.setLayoutParams(parametros_segunda_fila);


        LinearLayout.LayoutParams parametros_pegado_boton = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegado_boton.weight = 1.0f;
        entrar.setLayoutParams(parametros_pegado_boton);
        salir.setLayoutParams(parametros_pegado_boton);
        ajustar.setLayoutParams(parametros_pegado_boton);
        linear_layout_segunda_fila.addView(entrar);
        linear_layout_segunda_fila.addView(ajustar);


        linear_layout_principal.addView(linear_layout_primera_fila);
        linear_layout_principal.addView(linear_layout_segunda_fila);


        return linear_layout_principal;


    }


    private void eventos() {

        entrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                activarBluetooth();
                lanzarActividadComunicacion();
                linear_layout_segunda_fila.removeAllViews();
                linear_layout_segunda_fila.addView(salir);
                linear_layout_segunda_fila.addView(ajustar);


            }
        });

        salir.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                finish();
            }
        });

        ajustar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                lanzarActividadConfiguracion();

            }
        });


    }



    private void activarBluetooth(){

        BA = BluetoothAdapter.getDefaultAdapter();
        //activar bluetooth si está desactivado
        if (!BA.isEnabled()) {
            BA.enable();
        }

    }

    private void lanzarActividadComunicacion() {

        Intent intent = new Intent(this, ActividadComoClienteBluetooth.class);
        startActivity(intent);

    }

    private void lanzarActividadConfiguracion(){

        Intent intent = new Intent(this, ActividadConfiguracion.class);
        startActivity(intent);

    }


    protected void onPause() {
        super.onPause();

    }

    protected void onDestroy() {
        super.onDestroy();
        BA.disable();
        // AlmacenDatosRAM.conexion_bluetooth= "  ";
        finish();

    }


}

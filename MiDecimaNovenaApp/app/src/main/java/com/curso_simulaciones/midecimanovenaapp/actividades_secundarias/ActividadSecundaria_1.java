package com.curso_simulaciones.midecimanovenaapp.actividades_secundarias;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.curso_simulaciones.midecimanovenaapp.R;
import com.curso_simulaciones.midecimanovenaapp.datos.AlmacenDatosRAM;

public class ActividadSecundaria_1 extends Activity {

    private int tamanoLetraResolucionIncluida;
    private int margenesResolucionIncluida;
    private TextView textNombre;

    private EditText editNombre;

    private ImageView imagen;

    public void onCreate(Bundle savedInstanceState){
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


    private void gestionarResolucion(){


        tamanoLetraResolucionIncluida = (int)(0.8f*AlmacenDatosRAM.tamanoLetraResolucionIncluida);
        margenesResolucionIncluida = (int)(1.2f* AlmacenDatosRAM.tamanoLetraResolucionIncluida);


    }//fin método gestionarResolucion()

    /*método responsable de la creación de los elementos de la GUI*/
    private void crearElementosGUI(){

        textNombre = new TextView(this);
        textNombre.setGravity(Gravity.FILL_VERTICAL);
        textNombre.setBackgroundColor(Color.rgb(0, 150, 255));
        textNombre.setTextSize(tamanoLetraResolucionIncluida);
        textNombre.setPadding(margenesResolucionIncluida, 0, 0, 0);
        textNombre.setText("NOMBRE");
        textNombre.setTextColor(Color.BLACK);
        textNombre.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        editNombre = new EditText(this);
        editNombre.setTSize(tamanoLetraResolucionIncluida);
        editNombre.setText(AlmacenDatosRAM.nombre1);
        editNombre.setTextColor(Color.BLACK);
        editNombre.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


    }//fin método crearElementosGUI


    /*método responsable de administrar el diseño de la GUI*/
    private LinearLayout crextearGUI(){

        LinearLayout linearPrincipal = new LinearLayout(this);
        linearPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearPrincipal.setWeightSum(10.0f);
        linearPrincipal.setBackgroundColor(Color.BLACK);


        // Superior
        LinearLayout linearSuperior = new LinearLayout(this);
        linearSuperior.setBackgroundColor(Color.RED);
        // Fondo Superior
        Drawable fondo = getResources().getDrawable(R.drawable.wonkru);
        linearSuperior.setBackgroundDrawable(fondo);

        // Inferior
        LinearLayout linearInferior = new LinearLayout(this);
        linearInferior.setBackgroundColor(Color.argb(90,10,10,200));


        // Pegar Superior

        LinearLayout.LayoutParams parametrosSuperior = new  LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosSuperior.weight = 9.0f;
        parametrosSuperior.setMargins(10,10,10,10);

        // Pegar Inferior

        LinearLayout.LayoutParams parametrosInferior = new  LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
        parametrosInferior.weight = 1.0f;
        parametrosInferior.setMargins(10,10,10,10);
        // Se pegan a Linear Principal
        linearPrincipal.addView(linearSuperior, parametrosSuperior);
        linearPrincipal.addView(linearInferior, parametrosInferior);

        // Pesos Inferior
        linearInferior.setOrientation(LinearLayout.HORIZONTAL);
        linearInferior.setWeightSum(2.0f);

        //Pegar Textos
        LinearLayout.LayoutParams parametrosText = new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosText.weight = 1.0f;
        int a = margenesResolucionIncluida;
        parametrosText.setMargins(a,a,a,a);

        linearInferior.addView(textNombre, parametrosText);
        linearInferior.addView(editNombre, parametrosText);


        return linearPrincipal;

    }//fin método crearGUI

    /* Este método es automático*/
    protected void onPause() {

        String nombre1 = editNombre.getText().toString();

        AlmacenDatosRAM.nombre1 = nombre1;

        AlmacenDatosRAM.habilitar_boton_tres=true;

        super.onPause();


    }
    /*Administra los eventos de la GUI*/
    private void eventos(){


    }//fin método eventos


}

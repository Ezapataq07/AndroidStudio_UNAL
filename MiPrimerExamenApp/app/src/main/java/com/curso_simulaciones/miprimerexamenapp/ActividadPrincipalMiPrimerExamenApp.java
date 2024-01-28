package com.curso_simulaciones.miprimerexamenapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.miprimerexamenapp.componentes.Rueda;

public class ActividadPrincipalMiPrimerExamenApp extends Activity {

    private Rueda rueda_1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    /*llamada al método para crear los elementos de la interfaz
    gráfica de usuario (GUI)*/
        crearElementosGui();


    /*para informar cómo se debe adaptar la GUI a la pantalla del
    dispositivo*/
        ViewGroup.LayoutParams parametro_layout_principal = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


    /*pegar al contenedor la GUI: en el argumento se está llamando
    al método crearGui()*/
        this.setContentView(crearGui(), parametro_layout_principal);

    }


    /*crear los objetos de la interfaz gráfica de usuario (GUI)*/
    private void crearElementosGui() {

        //crear objeto Rueda
        rueda_1= new Rueda(this);
    }


    /*organizar la distribución de los objetos de de la GUI usando
    administradores de diseño*/
    private LinearLayout crearGui() {

        //administrador de diseño
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.HORIZONTAL);
        linear_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_principal.setGravity(Gravity.FILL);
        linear_principal.setBackgroundColor(Color.rgb(50, 70, 250));
        linear_principal.setWeightSum(1);

        LinearLayout linear_centro = new LinearLayout(this);
        linear_centro.setOrientation(LinearLayout.VERTICAL);
        linear_centro.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_centro.setGravity(Gravity.FILL);
        linear_centro.setBackgroundColor(Color.WHITE);
        linear_centro.setWeightSum(1);


        //parametro para pegar los gauges
        LinearLayout.LayoutParams parametrosPegadaRuedas= new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametrosPegadaRuedas.setMargins(30, 30, 30, 30);
        parametrosPegadaRuedas.weight = 1.0f;

        //pegar rueda
        linear_centro.addView(rueda_1,parametrosPegadaRuedas);


        //parametro para pegar los linear al pricipal
        LinearLayout.LayoutParams parametrosPegadaLinear= new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadaLinear.setMargins(20, 20, 20, 20);
        parametrosPegadaLinear.weight = 1.0f;

        linear_principal.addView(linear_centro,parametrosPegadaLinear);

        return linear_principal;

    }
}

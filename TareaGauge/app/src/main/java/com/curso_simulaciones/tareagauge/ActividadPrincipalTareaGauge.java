package com.curso_simulaciones.tareagauge;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.tareagauge.componentes.GaugeSimple;

public class ActividadPrincipalTareaGauge extends Activity {
    private GaugeSimple tacometro_1, tacometro_2,tacometro_3;


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

        //crear objeto GaugeSimple
        tacometro_1=new GaugeSimple(this);
        //cambiar atributos (propiedades)
        //darle color blanco al lienzo antes de pega
        tacometro_1.setBackgroundColor(Color.rgb(4,4,4));

        tacometro_1.setColorSectores(Color.rgb(20,20,20), Color.rgb(20,20,20), Color.rgb(20,20,20));
        //asignar las unidades
        tacometro_1.setUnidades("mph");
        //asignar rangos
        tacometro_1.setRango(0,240);
        //asignar la medida
        tacometro_1.setMedida(213);

        tacometro_1.setNumeroDivisiones(12);

        tacometro_1.setColorFondoTacometro(Color.rgb(27,27,27));

        tacometro_1.setColorLineasTacometro(Color.rgb(4,160,240));

        tacometro_1.setAngulosSectores(90,90,180);

        tacometro_1.setColorNumeroDespliegue(Color.WHITE);

        tacometro_1.setColorTableroDespliegue(Color.rgb(27,27,27));

        //crear objeto GaugeSimple
        tacometro_2=new GaugeSimple(this);
        //cambiar atributos (propiedades)
        //darle color blanco al lienzo antes de pega
        tacometro_2.setBackgroundColor(Color.rgb(4,4,4));

        tacometro_2.setColorSectores(Color.rgb(20,20,20), Color.rgb(20,20,20), Color.rgb(20,20,20));
        //asignar las unidades
        tacometro_2.setUnidades("km/h");
        //asignar rangos
        tacometro_2.setRango(0,1024);
        //asignar la medida
        tacometro_2.setMedida(980);

        tacometro_2.setNumeroDivisiones(8);

        tacometro_2.setColorFondoTacometro(Color.rgb(27,27,27));

        tacometro_2.setColorLineasTacometro(Color.rgb(4,160,240));

        tacometro_2.setAngulosSectores(90,90,180);

        tacometro_2.setColorNumeroDespliegue(Color.WHITE);

        tacometro_2.setColorTableroDespliegue(Color.rgb(27,27,27));

        //crear objeto GaugeSimple
        tacometro_3=new GaugeSimple(this);
        //cambiar atributos (propiedades)
        //darle color blanco al lienzo antes de pega
        tacometro_3.setBackgroundColor(Color.rgb(4,4,4));

        tacometro_3.setColorSectores(Color.rgb(20,20,20), Color.rgb(20,20,20), Color.rgb(20,20,20));
        //asignar las unidades
        tacometro_3.setUnidades("rpm");
        //asignar rangos
        tacometro_3.setRango(0,16);
        //asignar la medida
        tacometro_3.setMedida(11);

        tacometro_3.setNumeroDivisiones(16);

        tacometro_3.setColorFondoTacometro(Color.rgb(27,27,27));

        tacometro_3.setColorLineasTacometro(Color.rgb(4,160,240));

        tacometro_3.setAngulosSectores(90,90,180);

        tacometro_3.setColorNumeroDespliegue(Color.WHITE);

        tacometro_3.setColorTableroDespliegue(Color.rgb(27,27,27));


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
        linear_principal.setWeightSum(3);

        LinearLayout linear_izquierdo = new LinearLayout(this);
        linear_izquierdo.setOrientation(LinearLayout.VERTICAL);
        linear_izquierdo.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_izquierdo.setGravity(Gravity.FILL);
        linear_izquierdo.setBackgroundColor(Color.RED);
        linear_izquierdo.setWeightSum(1);

        LinearLayout linear_centro = new LinearLayout(this);
        linear_centro.setOrientation(LinearLayout.VERTICAL);
        linear_centro.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_centro.setGravity(Gravity.FILL);
        linear_centro.setBackgroundColor(Color.BLUE);
        linear_centro.setWeightSum(1);

        LinearLayout linear_derecho = new LinearLayout(this);
        linear_derecho.setOrientation(LinearLayout.VERTICAL);
        linear_derecho.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_derecho.setGravity(Gravity.FILL);
        linear_derecho.setBackgroundColor(Color.GREEN);
        linear_derecho.setWeightSum(1);


        //parametro para pegar los gauges
        LinearLayout.LayoutParams parametrosPegadaGauges= new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,0);
        parametrosPegadaGauges.setMargins(20, 20, 20, 20);
        parametrosPegadaGauges.weight = 1.0f;

        //pegar gauges
        linear_izquierdo.addView(tacometro_1,parametrosPegadaGauges);
        linear_centro.addView(tacometro_2,parametrosPegadaGauges);
        linear_derecho.addView(tacometro_3,parametrosPegadaGauges);


        //parametro para pegar los linear al pricipal
        LinearLayout.LayoutParams parametrosPegadaLinear= new LinearLayout.LayoutParams(0,android.view.ViewGroup.LayoutParams.MATCH_PARENT);
        parametrosPegadaLinear.setMargins(20, 20, 20, 20);
        parametrosPegadaLinear.weight = 1.0f;

        linear_principal.addView(linear_izquierdo,parametrosPegadaLinear);
        linear_principal.addView(linear_centro,parametrosPegadaLinear);
        linear_principal.addView(linear_derecho,parametrosPegadaLinear);

        return linear_principal;

    }
}

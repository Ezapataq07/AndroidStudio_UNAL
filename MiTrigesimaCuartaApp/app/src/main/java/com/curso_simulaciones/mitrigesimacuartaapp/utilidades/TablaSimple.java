package com.curso_simulaciones.mitrigesimacuartaapp.utilidades;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.curso_simulaciones.mitrigesimacuartaapp.datos.AlmacenDatosRAM;


public class TablaSimple extends LinearLayout {

    private ScrollView panelScroll;
    private TableLayout table;
    private int tamanoLetraResolucionIncluida;
    private int dimensionReferencia;
    private Context context;
    private int contador = -1;
    private String etiquetaT="t";
    private String etiquetaBx="Bx";
    private String etiquetaBy="By";
    private String etiquetaBz="Bz";
    private String etiquetaB="B";
    private float t,Bx, By, Bz, B;
    private int colorColumna1= Color.MAGENTA;
    private int colorColumna2=Color.RED;
    private int colorColumna3=Color.BLUE;
    private int colorColumna4=Color.BLUE;
    private int colorColumna5=Color.BLUE;
    private int colorColumna6=Color.CYAN;
    // private int n=0;


    /**
     * Cosntructor de TablaSimple
     * @param context
     */
    public TablaSimple(Context context) {
        super(context);
        this.context=context;

        gestionarResolucion();



        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        gui();

    }


    private void gestionarResolucion(){

        /*
        El alto en la actividad principal (PORTRAI)
        corresponde al ancho aquí (LANSCAPE
        */
        dimensionReferencia = (int)(0.4f* AlmacenDatosRAM.alto);

        tamanoLetraResolucionIncluida = (int)(0.6* AlmacenDatosRAM.tamanoLetraResolucionIncluida);


    }//fin método gestionarResolucion()


    private void gui(){

        panelScroll = new ScrollView(context);
        table=new TableLayout(context);

        LinearLayout linearLayoutPrincipal = new LinearLayout(context);
        linearLayoutPrincipal.setOrientation(LinearLayout.VERTICAL);
        linearLayoutPrincipal.setBackgroundColor(Color.BLACK);

        LayoutParams parametroPegado = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);

        panelScroll.addView(table);
        linearLayoutPrincipal.addView(panelScroll);

        this.addView(linearLayoutPrincipal, parametroPegado);


    }

    /**
     * Modifica las etiquetas de las columnas

     */
    public void setEtiquetaColumnas(String etiquetaT, String etiquetaBx, String etiquetaBy, String etiquetaBz, String etiquetaB){

        this.etiquetaT=etiquetaT;
        this.etiquetaBx=etiquetaBx;
        this.etiquetaBy=etiquetaBy;
        this.etiquetaBz=etiquetaBz;
        this.etiquetaB=etiquetaB;

    }

    /**
     * Envía los datos a la tabla
     */
    public void enviarDatos(float t, float Bx, float By, float Bz, float B){

        this.t=t;
        this.Bx=Bx;
        this.By=By;
        this.Bz=Bz;
        this.B=B;
        contador=contador+1;
        incrementarFila();

    }


    /**
     * Borra los datos enviados a la tabla
     */
    public void borrar(){
        removerFilas();
    }

    /**
     * Modifica los colores de las columnas
     * @param colorColumna1
     * @param colorColumna2
     * @param colorColumna3
     */
    public void setColorColumnas(int colorColumna1, int colorColumna2,int colorColumna3, int colorColumna4, int colorColumna5,int colorColumna6){

        this.colorColumna1=colorColumna1;
        this.colorColumna2=colorColumna2;
        this.colorColumna3=colorColumna3;
        this.colorColumna4=colorColumna4;
        this.colorColumna5=colorColumna5;
        this.colorColumna6=colorColumna6;

    }


    private void incrementarFila() {

        //crear nueva TableRow
        TableRow fila = new TableRow(context);

        TableRow.LayoutParams layoutTexto = new TableRow.LayoutParams((int)(0.2*dimensionReferencia), TableRow.LayoutParams.WRAP_CONTENT);


        //columna uno
        TextView textNumeroDato = new TextView(context);
        textNumeroDato.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textNumeroDato.setLayoutParams(layoutTexto);

        if(contador==0){

            textNumeroDato.setText("# de DATO");

        } else {

            textNumeroDato.setText("DATO " + contador);
        }


        textNumeroDato.setTextSize(tamanoLetraResolucionIncluida);
        textNumeroDato.setTextColor(colorColumna1);
        textNumeroDato.setGravity(Gravity.CENTER_HORIZONTAL);

        //columna 2
        TextView textValorT = new TextView(context);
        textValorT.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textValorT.setLayoutParams(layoutTexto);

        if(contador==0)
        {
            textValorT.setText(etiquetaT);

        }  else {

            textValorT.setText("" + t);

        }


        textValorT.setTextSize(tamanoLetraResolucionIncluida);
        textValorT.setTextColor(colorColumna2);
        textValorT.setGravity(Gravity.CENTER_HORIZONTAL);

        //columna 3
        TextView textValorBx = new TextView(context);
        textValorBx.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textValorBx.setLayoutParams(layoutTexto);

        if(contador==0)
        {
            textValorBx.setText(etiquetaBx);

        } else {

            textValorBx.setText("" + Bx);

        }

        textValorBx.setTextSize(tamanoLetraResolucionIncluida);
        textValorBx.setTextColor(colorColumna3);
        textValorBx.setGravity(Gravity.CENTER_HORIZONTAL);


        //columna 4


        TextView textValorBy = new TextView(context);
        textValorBy.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textValorBy.setLayoutParams(layoutTexto);

        if(contador==0)
        {
            textValorBy.setText(etiquetaBy);

        } else {

            textValorBy.setText("" + By);

        }

        textValorBy.setTextSize(tamanoLetraResolucionIncluida);
        textValorBy.setTextColor(colorColumna4);
        textValorBy.setGravity(Gravity.CENTER_HORIZONTAL);

        // columna 5

        TextView textValorBz = new TextView(context);
        textValorBz.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textValorBz.setLayoutParams(layoutTexto);

        if(contador==0)
        {
            textValorBz.setText(etiquetaBz);

        } else {

            textValorBz.setText("" + Bz);

        }

        textValorBz.setTextSize(tamanoLetraResolucionIncluida);
        textValorBz.setTextColor(colorColumna5);
        textValorBz.setGravity(Gravity.CENTER_HORIZONTAL);

        // columna 6

        TextView textValorB = new TextView(context);
        textValorB.setTextSize(TypedValue.COMPLEX_UNIT_SP, tamanoLetraResolucionIncluida);
        textValorB.setLayoutParams(layoutTexto);

        if(contador==0)
        {
            textValorB.setText(etiquetaB);

        } else {

            textValorB.setText("" + B);

        }

        textValorB.setTextSize(tamanoLetraResolucionIncluida);
        textValorB.setTextColor(colorColumna6);
        textValorB.setGravity(Gravity.CENTER_HORIZONTAL);


        fila.setGravity(Gravity.CENTER_HORIZONTAL);
        //adicionar las tres columnas a la fila
        fila.addView(textNumeroDato);
        fila.addView(textValorT);
        fila.addView(textValorBx);
        fila.addView(textValorBy);
        fila.addView(textValorBz);
        fila.addView(textValorB);

        //Adicionar TabRow a la Tabla
        table.addView( fila, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));


    }


    private void removerFilas() {


        if (contador > 1) {

            table.removeAllViews();
            contador=-1;

        }

    }
}


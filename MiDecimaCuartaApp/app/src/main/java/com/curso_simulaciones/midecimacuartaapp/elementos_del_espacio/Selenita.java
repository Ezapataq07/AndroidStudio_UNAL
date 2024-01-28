package com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Selenita extends Extraterrestre {

    protected float ratio;

    /**
     * Constructor por defecto
     * del marciano con "centroide" en (0,0)
     * y de radio 50f
     */
    public Selenita() {
        super();
    }


    /**
     * Constructor del marciano con "centroide"
     * en (posicionCentroideX,posicionCentroideY)
     * y diámetro igual a 2*radio
     */

    public Selenita(float posicionCentroideX, float posicionCentroideY) {
        super(posicionCentroideX, posicionCentroideY);

    }


    public void setRatioSelenita(float ratio){
        this.ratio = ratio;
    }




    //se implementó este método dque en su clase madre es abstracto
    public void dibujese(Canvas canvas, Paint pincel) {

        //estilo del pindel
        pincel.setStyle(Paint.Style.STROKE);
        //grosor del pincel
        pincel.setStrokeWidth(2f);
        //color del pincel
        pincel.setColor(color);


        canvas.save();

        //magnificar
        canvas.scale(magnificacion,magnificacion,posicionCentroideX,posicionCentroideY);
        //rotar
        canvas.rotate(posicionAngularRotacionEjeXY,posicionEjeRotacionX,posicionEjeRotacionY);
        //dibujar círculo de la cara del marciano
        float radio=ratio;
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(color);
        canvas.drawRect(posicionCentroideX-ratio*0.7f,posicionCentroideY+ratio*1f,posicionCentroideX+ratio*0.7f,posicionCentroideY-ratio*1f,pincel);
        pincel.setColor(Color.BLACK);



        //dibujar los ojitos del marcianito
        float separacionX=0.3f*radio;
        float separacionY=0.5f*radio;
        float posicionOjoCentroIzquierdoX=posicionCentroideX-separacionX;
        float posicionOjoCentroIzquierdoY=posicionCentroideY-separacionY;
        float posicionOjoCentroDerechoX=posicionCentroideX+separacionX;
        float posicionOjoCentroDerechoY=posicionCentroideY-separacionY;
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.WHITE);
        canvas.drawRect(posicionOjoCentroIzquierdoX-radio*0.2f,posicionOjoCentroIzquierdoY+0.08f*radio,posicionOjoCentroIzquierdoX+0.2f*radio,posicionOjoCentroIzquierdoY-0.08f*radio,pincel);
        canvas.drawRect(posicionOjoCentroDerechoX-radio*0.2f,posicionOjoCentroDerechoY+0.08f*radio,posicionOjoCentroDerechoX+0.2f*radio,posicionOjoCentroDerechoY-0.08f*radio,pincel);
        //dibujar la pupila de los ojitos

        //dibujar la boquita del marcianito
        float altoBoca= 0.08f*radio;
        float anchoBoca=0.8f*radio;
        pincel.setColor(Color.WHITE);
        float descenso=0.4f*radio;
        float posicionBocaCentroX=posicionCentroideX;
        float posicionBocaCentroY=posicionCentroideY+descenso;
        float xs_izquierda=posicionBocaCentroX-0.5f*anchoBoca;
        float ys_izquierda=posicionBocaCentroY-0.5f*altoBoca;
        float xi_derecha=posicionBocaCentroX+0.5f*anchoBoca;
        float yi_derecha=posicionBocaCentroY+0.5f*altoBoca;
        canvas.drawRect(xs_izquierda,ys_izquierda,xi_derecha,yi_derecha,pincel);

        //dibujar la antena
        pincel.setColor(color);
        pincel.setStrokeWidth(0.1f*radio);
        canvas.drawLine(posicionCentroideX,posicionCentroideY-radio*1f,posicionCentroideX,posicionCentroideY-radio*1.5f,pincel);
        canvas.drawCircle(posicionCentroideX,posicionCentroideY-radio*1.5f,0.18f*radio,pincel);

        // dibujar circulitos
        canvas.drawCircle(posicionCentroideX+0.7f*radio,posicionCentroideY+1f*radio,0.2f*radio,pincel);
        canvas.drawCircle(posicionCentroideX-0.7f*radio,posicionCentroideY+1f*radio,0.2f*radio,pincel);

        pincel.setColor(Color.BLACK);
        canvas.drawCircle(posicionCentroideX,posicionCentroideY,radio*0.05f,pincel);



        //regresa la rotación
        canvas.restore();


    }
}


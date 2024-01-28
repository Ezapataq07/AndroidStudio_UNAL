package com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Venusiano extends Extraterrestre {

    protected float ratio;

    /**
     * Constructor por defecto
     * del marciano con "centroide" en (0,0)
     * y de radio 50f
     */
    public Venusiano() {
        super();
    }


    /**
     * Constructor del marciano con "centroide"
     * en (posicionCentroideX,posicionCentroideY)
     * y diámetro igual a 2*radio
     */

    public Venusiano(float posicionCentroideX, float posicionCentroideY) {
        super(posicionCentroideX, posicionCentroideY);

    }


    public void setRatioVenuciano(float ratio){
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
        canvas.drawCircle(posicionCentroideX, posicionCentroideY, radio, pincel);
        pincel.setColor(Color.BLACK);





        //dibujar los ojitos del marcianito
        float separacionX = 0.4f*radio;
        float separacionY = 0.3f*radio;
        float posicionOjoCentroIzquierdoX=posicionCentroideX-separacionX;
        float posicionOjoCentroIzquierdoY=posicionCentroideY-separacionY;
        float posicionOjoCentroDerechoX=posicionCentroideX+separacionX;
        float posicionOjoCentroDerechoY=posicionCentroideY-separacionY;
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.WHITE);
        canvas.drawCircle(posicionOjoCentroIzquierdoX, posicionOjoCentroIzquierdoY, 0.35f*radio, pincel);
        canvas.drawCircle(posicionOjoCentroDerechoX, posicionOjoCentroDerechoY, 0.35f*radio, pincel);
        //dibujar la pupila de los ojitos
        pincel.setColor(color);
        canvas.drawCircle(posicionOjoCentroIzquierdoX, posicionOjoCentroIzquierdoY, 0.1f*radio, pincel);
        canvas.drawCircle(posicionOjoCentroDerechoX, posicionOjoCentroDerechoY, 0.1f*radio, pincel);

        //dibujar la boquita del marcianito
        float altoBoca= 0.05f*radio;
        float anchoBoca=0.7f*radio;
        pincel.setColor(Color.WHITE);
        float descenso=0.45f*radio;
        float posicionBocaCentroX=posicionCentroideX;
        float posicionBocaCentroY=posicionCentroideY+descenso;
        float xs_izquierda=posicionBocaCentroX-0.5f*anchoBoca;
        float ys_izquierda=posicionBocaCentroY-0.5f*altoBoca;
        float xi_derecha=posicionBocaCentroX+0.5f*anchoBoca;
        float yi_derecha=posicionBocaCentroY+0.5f*altoBoca;
        canvas.drawRect(xs_izquierda,ys_izquierda,xi_derecha,yi_derecha,pincel);

        // dibujar gorrita
        RectF rectF_1 = new RectF(posicionCentroideX-.85f*radio, posicionCentroideY-1.25f*radio, posicionCentroideX+.85f*radio, posicionCentroideY-0.65f*radio);
        pincel.setStrokeWidth(0.5f);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(color);
        canvas.drawArc(rectF_1, 0, 360, true, pincel);


        pincel.setColor(Color.BLACK);
        canvas.drawCircle(posicionCentroideX,posicionCentroideY,radio*0.05f,pincel);




        //regresa la rotación
        canvas.restore();


    }
}


package com.curso_simulaciones.midecimacuartaapp.elementos_del_espacio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;

public class EstrellaFija extends Extraterrestre {

    protected float ratio;

    /**
     * Constructor por defecto
     * del marciano con "centroide" en (0,0)
     * y de radio 50f
     */
    public EstrellaFija() {
        super();
    }


    /**
     * Constructor del marciano con "centroide"
     * en (posicionCentroideX,posicionCentroideY)
     * y diámetro igual a 2*radio
     */

    public EstrellaFija(float posicionCentroideX, float posicionCentroideY) {
        super(posicionCentroideX, posicionCentroideY);

    }


    public void setRatioEstrella(float ratio){
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


        float anguloTriangulos = 360f/5.0f;

        for (int i = 0; i < 5; i = i + 1) {
            float j = (float) i;
            // Se calcula el ángulo de rotación para la actual iteración
            float anguloRotacion = (float) (anguloTriangulos * j);
            // Se rota el canvas respecto al centro
            canvas.rotate(anguloRotacion, posicionCentroideX, posicionCentroideY);

            pincel.setStyle(Paint.Style.FILL_AND_STROKE);
            pincel.setColor(color);

            Point a = new Point((int) (posicionCentroideX-.5f*ratio), (int) posicionCentroideY);
            Point b = new Point((int) (posicionCentroideX+.5f*ratio),  (int) posicionCentroideY);
            Point c = new Point((int) posicionCentroideX, (int) (posicionCentroideY-1f*ratio));

            Path path = new Path();
            path.moveTo(a.x, a.y);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(a.x, a.y);
            path.close();


            canvas.drawPath(path, pincel);

            // Se devuelve el canvas a su posición original
            canvas.rotate(-anguloRotacion, posicionCentroideX, posicionCentroideY);

        }




//
//        pincel.setColor(Color.BLACK);
//        canvas.drawCircle(posicionCentroideX,posicionCentroideY,ratio*0.05f,pincel);




        //regresa la rotación
        canvas.restore();


    }
}


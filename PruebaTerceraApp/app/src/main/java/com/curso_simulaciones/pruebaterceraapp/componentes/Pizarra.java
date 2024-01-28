package com.curso_simulaciones.pruebaterceraapp.componentes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

public class Pizarra extends View {
    public Pizarra(Context context) {
        super(context);
    }

    //método para dibujar
    protected void onDraw(Canvas canvas) {

        Paint pincel = new Paint();
        pincel.setAntiAlias(true);

        /*Tamaño del texto*/
        pincel.setColor(Color.RED);
        pincel.setTextSize(40f);
        pincel.setTextAlign(Paint.Align.CENTER);
        /*texto que comienza a escribirse en (20,30) de tamaño de letra 40 pixeles y de color rojo */
        canvas.drawText("Hola Jóvenes, bienvenidos a sus primeros dibujos", 1112f, 50f, pincel);

        /*
        Dibuja un línea negra de espesor 5
        desde (30,100) hasta (200,180)
        */
        pincel.setStrokeWidth(5);
        pincel.setColor(Color.BLACK);
        canvas.drawLine(30, 100, 2200, 100, pincel);

        /*
          Dibujar círculo de color rojo no
          relleno con centro en (600, 150) y
          radio 150 pixeles. Grosor de trazo 10
        */
        pincel.setStrokeWidth(10);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setColor(Color.GREEN);
        canvas.drawCircle(1112, 500, 250, pincel);


        /*
         Dibujar rectángulo de color verde
         r=0, g=250, b=0) con 100 de
         transparencia relleno con esquina
         superior izquierda en (400,150) y
         esquina inferior derecha en (800,550)
        */
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.argb(100, 150, 250, 0));
        canvas.drawRect(1500, 150, 2000, 700, pincel);


        /*
         Dibujar rectángulo de color rojo
         r=255, g=0, b=0) con esquina
         superior izquierda en (350,250) y
         esquina inferior derecha en (850,450)
        */
        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.rgb(255, 0, 100));
        canvas.drawRect(1550, 250, 1950, 600, pincel);

        /*
         Dibujar rectángulo cuyo
         borde es negro de espesor 2,
         no relleno, con esquina superior
         izquierda en (550,100) y
         esquina inferior derecha en (700,200)
        */
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(4);
        pincel.setColor(Color.BLACK);
        canvas.drawRect(1500, 150, 2000,700, pincel);

        /*
         Dibujar arco inscrito en rectángulo
         con esquina superior izquierda en (50,200)
         y esquina inferior derecha en (300,450),
         con inicio en 90 grados y desplazándose
         135 grados (el arco es de 135 grados).
         Color magenta y espesor de línea 2.
        */
        RectF rectF = new RectF(100, 200, 600, 700);
        pincel.setColor(Color.BLUE);
        canvas.drawArc(rectF, 90, 220, false, pincel);

        /*
         Dibujar sector circular inscrito en rectángulo
         con esquina superior izquierda en (50,400)
         y esquina inferior derecha en (300,650),
         no relleno, de espesor de línea 0.5 y de color negro,
         con inicio en 90 grados y desplazándose
         135 grados (el arco es de 135 grados).
        */
        RectF rectF_1 = new RectF(150, 250, 550, 650);
        pincel.setStrokeWidth(4.0f);
        pincel.setColor(Color.rgb(100,0,250));
        canvas.drawArc(rectF_1, 90, 170, true, pincel);


        /*
         Dibujar sector circular inscrito en rectángulo
         con esquina superior izquierda en (250,400)
         y esquina inferior derecha en (500,650),
         relleno, de color negro con inicio en 90 grados
         y desplazándose 135 grados (el arco es de 135 grados).
        */
        RectF rectF_2 = new RectF(150, 250, 550, 650);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF_2, 90, 120, true, pincel);

        /*
         Dibujar triángulo con línea azul y vértices en
        (600,400); (300,650); (100,50)
        */
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(2);
        pincel.setColor(Color.MAGENTA);

        Point a = new Point(350, 700);
        Point b = new Point(350, 450);
        Point c = new Point(510, 250);

        Path path = new Path();
        path.moveTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.moveTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.moveTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();

        canvas.drawPath(path, pincel);

        /*
        Algo especial.
        Muchísimos efectos se pueden lograr con las
        librerías de dibujo de ANDROID, sin embargo
        no es objeto de estas notas. Aquí con lo básico
        es suficiente.
         */
        Path trazo = new Path();
        trazo.addCircle(1112, 500, 150, Path.Direction.CCW);
        pincel.setColor(Color.rgb(100,250,100));
        pincel.setStrokeWidth(8);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawPath(trazo, pincel);
        pincel.setStrokeWidth(1);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setTextSize(20);
        pincel.setTypeface(Typeface.SANS_SERIF);
        pincel.setColor(Color.BLACK);

        canvas.drawTextOnPath("Escuela de Física Universidad Nacional de Colombia  -Medellín-", trazo, 10, 40, pincel);

    }

}

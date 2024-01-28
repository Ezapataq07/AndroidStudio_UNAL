package com.curso_simulaciones.mivigesimaquintaapp.modelo;

import com.curso_simulaciones.mivigesimaquintaapp.datos.AlmacenDatosRAM;
import com.curso_simulaciones.mivigesimaquintaapp.vista.CR;

public class ModeloFisico {

    //gravedad
    private float g = 9.8f;//en m/s2


    private float desplazamiento_m1_en_pixeles, desplazamiento_m2_en_pixeles;
    private float factorConversion_metroApixel, factorConversion_pixelAmetro;
    private float y1_en_pixeles, y2_en_pixeles;


    public ModeloFisico() {


    }


    /*
      Dados los valores de las masas calcula posiciones,
      aceleraciones, tensión, desplazamiento
      angular de las poleas

     */
    public void setCalculos(float tiempo, float m1, float m2 ) {

        factorConversion();


        float a = ((m2 - 2*m1) / (m2 + 4*m1)) * g;

        //cálculo de la tensión en la cuerda
        float F = m1*g + 2* m1 * a;

        //aceleraciones de m1 y m2 en m/s^^2
        float a1 = -2*a;
        float a2 = a;

        //desplazamiento de las masas en metros
        float desplazamiento_m1_en_metros = 0.5f * a1 * tiempo * tiempo;
        float desplazamiento_m2_en_metros = 0.5f * a2 * tiempo * tiempo;

        //almacenar valores para que pizarra los reporte
        AlmacenDatosRAM.desplazamiento_m1_en_metros = desplazamiento_m1_en_metros;
        AlmacenDatosRAM.desplazamiento_m2_en_metros = desplazamiento_m2_en_metros;

    /*
     Convertir m a pixeles
    */
        //desplazamientos de las masas en pixeles
        desplazamiento_m1_en_pixeles = factorConversion_metroApixel * desplazamiento_m1_en_metros;
        desplazamiento_m2_en_pixeles = factorConversion_metroApixel * desplazamiento_m2_en_metros;

        AlmacenDatosRAM.desplazamiento_m1_en_pixeles = desplazamiento_m1_en_pixeles;
        AlmacenDatosRAM.desplazamiento_m2_en_pixeles = desplazamiento_m2_en_pixeles;


        //posición inciales en pixeles
        float yi1_en_pixeles = AlmacenDatosRAM.yi1_en_pixeles;
        float yi2_en_pixeles = AlmacenDatosRAM.yi2_en_pixeles;

        //posiciones de las masas en pixeles
        y1_en_pixeles = yi1_en_pixeles + desplazamiento_m1_en_pixeles;
        y2_en_pixeles = yi2_en_pixeles + desplazamiento_m2_en_pixeles;
        AlmacenDatosRAM.y1_en_pixeles = y1_en_pixeles;
        AlmacenDatosRAM.y2_en_pixeles = y2_en_pixeles;



        float teta_2 = (float) (Math.toDegrees(desplazamiento_m2_en_pixeles / AlmacenDatosRAM.radio));
        float teta_1 = -2f * teta_2;



        AlmacenDatosRAM.y1_en_metros = factorConversion_pixelAmetro * y1_en_pixeles;
        AlmacenDatosRAM.y2_en_metros = factorConversion_pixelAmetro * y2_en_pixeles;

        //enviar resultados a AlmacenDatosRAM
        AlmacenDatosRAM.a1 = a1;
        AlmacenDatosRAM.a2 = a2;

        AlmacenDatosRAM.teta_1 = teta_1;
        AlmacenDatosRAM.teta_2 = teta_2;
        AlmacenDatosRAM.F = F;

        AlmacenDatosRAM.tiempo = tiempo;


    }


    private void factorConversion() {

     /*
    Para dar una equivalencia de pixeles
    en metros se asumirá que 1 m equivale
    al ALTO de la pantalla (en posición
    LANSCAPE) en pixeles. Con base en esto
    el factor de conversion de metros a pixeles
    es:

    factorConversion_metroApixel= (ALTO en pixeles/ 1 metro)
    factorConversion_pixelAmetro= (1 metro / ALTO en pixeles)

    */

        factorConversion_metroApixel = CR.pcApxY(100f) / 2;

        factorConversion_pixelAmetro = 2 / CR.pcApxY(100f);

    }



}


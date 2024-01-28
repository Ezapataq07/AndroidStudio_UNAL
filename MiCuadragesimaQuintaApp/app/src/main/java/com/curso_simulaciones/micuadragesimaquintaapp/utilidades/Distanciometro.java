package com.curso_simulaciones.micuadragesimaquintaapp.utilidades;


import android.content.Context;

public class Distanciometro extends GaugeSimple {


    public Distanciometro(Context context){
        super(context);


    }


    public void cambiarEscala(float medida){

        float maximo=20f;
        float minimo=0f;

        if(medida>0 && ((medida < 20)^(medida==20))){
            maximo=20f;
            minimo=0f;

        }

        if(medida>20 && ((medida < 50)^(medida==50f ))){

            maximo=50f;
            minimo=0f;

        }

        if(medida>50 && ((medida < 100)^(medida==100f ))){

            maximo=100f;
            minimo=0f;

        }


        if(medida>100 && ((medida < 200)^(medida==200f ))){

            maximo=200f;
            minimo=0f;

        }

        if(medida>200 && ((medida < 300)^(medida==300f ))){

            maximo=300f;
            minimo=0f;

        }

        if(medida>300 && ((medida < 600)^(medida==600f ))){

            maximo=600f;
            minimo=0f;

        }

        if(medida>600 && ((medida < 2000)^(medida==2000f ))){

            maximo=2000f;
            minimo=600f;

        }

        if(medida>2000 && ((medida < 4000)^(medida==4000f ))){

            maximo=4000f;
            minimo=2000f;

        }


        this.setRango(minimo,maximo);

    }


}


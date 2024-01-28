package com.curso_simulaciones.mitrigesimasextaapp.utilidades;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gaussimetro extends GaugeSimple implements SensorEventListener {

    private SensorManager sensorManager;
    private int componenteCampo = 4;

    public Gaussimetro(Context context) {
        super(context);
        //estado inicial
        setComponenteGaussimetro(componenteCampo);

    }


    public void setComponenteGaussimetro(int componenteCampo) {

        this.componenteCampo = componenteCampo;

        if (componenteCampo ==1) {
            this.setUnidades("Bx (uT)");
            this.setRango(-20,20);}

        if (componenteCampo ==2) {
            this.setUnidades("By (uT)");
            this.setRango(-20,20);}

        if (componenteCampo ==3) {
            this.setUnidades("Bz (uT)");
            this.setRango(-20,20);}

        if (componenteCampo ==4) {
            this.setUnidades("B (uT)");
            this.setRango(0,20);}



    }

    public void captarSensor(Context context) {

        //captamos el servicio del sensor
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_FASTEST);

    }

    //se activa sólo cuando hay cambios
    public void onSensorChanged(SensorEvent event) {

        //en x
        float B = 0;
        float medida_x = 0;
        float medida_y = 0;
        float medida_z = 0;
        float medida = 0;

        medida_x = event.values[SensorManager.DATA_X];
        medida_y = event.values[SensorManager.DATA_Y];
        medida_z = event.values[SensorManager.DATA_Z];
        float resultado = medida_x * medida_x + medida_y * medida_y + medida_z * medida_z;
        B = (float) (Math.sqrt(resultado));


        if (componenteCampo == 1) {
            medida = medida_x;
            this.setUnidades(" Bx (uT)");
            this.setRango(-20,20);
        }
        if (componenteCampo == 2){
            medida = medida_y;
            this.setUnidades(" By (uT)");
            this.setRango(-20,20);
        }
        if (componenteCampo == 3){
            medida = medida_z;
            this.setUnidades(" Bz (uT)");
            this.setRango(-20,20);
        }
        if (componenteCampo == 4){
            medida = B;
            this.setUnidades(" B (uT)");
            this.setRango(0,20);
        }

        //un decimal
        medida = (float) (Math.round(medida * 10) / 10.0f);
        this.setMedida(medida);
        cambiarEscala(medida);
        //almacenar dato actual
        //AlmacenDatosRAM.datoActualAceleracion = medida;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void cambiarEscala(float medida){

        float maximo=100f;
        float minimo=0f;

        if(medida>0 && ((medida < 100)^(medida==100f ))){

            maximo=100f;
            minimo=0f;

        }

        if(medida>100 && ((medida < 1000)^(medida==1000f ))){

            maximo=1000f;
            minimo=100f;

        }


        if(medida>1000 && ((medida < 5000)^(medida==5000f ))){

            maximo=5000f;
            minimo=1000f;

        }

        if(medida>5000 && ((medida < 10000)^(medida==10000f ))){

            maximo=10000f;
            minimo=5000f;

        }

        if(medida>10000 && ((medida < 50000)^(medida==50000f ))){

            maximo=50000f;
            minimo=10000f;

        }

        // negativos
        if(medida<0 && ((medida > -100)^(medida==-100f ))){

            maximo=0f;
            minimo=-100f;

        }

        if(medida<-100 && ((medida > -1000)^(medida==-1000f ))){

            maximo=-100f;
            minimo=-1000f;

        }


        if(medida<-1000 && ((medida > -5000)^(medida==-5000f ))){

            maximo=-1000f;
            minimo=-5000f;

        }

        if(medida<-5000 && ((medida > -10000)^(medida==-10000f ))){

            maximo=-5000f;
            minimo=-10000f;

        }

        if(medida<-10000 && ((medida > -50000)^(medida==-50000f ))){

            maximo=-10000f;
            minimo=-50000f;

        }


        this.setRango(minimo,maximo);

    }

}


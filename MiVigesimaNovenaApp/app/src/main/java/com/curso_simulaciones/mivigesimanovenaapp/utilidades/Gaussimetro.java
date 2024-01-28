package com.curso_simulaciones.mivigesimanovenaapp.utilidades;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.curso_simulaciones.mivigesimanovenaapp.datos.AlmacenDatosRAM;

public class Gaussimetro extends GaugePersonal implements SensorEventListener {

    private SensorManager sensorManager;
    private int componenteCampo = 1;

    public Gaussimetro(Context context) {
        super(context);

        captarSensor(context);

        this.setRango(-2000, 2000);
        this.setBackgroundColor(Color.rgb(4,4,4));
        this. setColorSectores(Color.rgb(20,20,20), Color.rgb(20,20,20), Color.rgb(20,20,20));
        this.setColorFondoTacometro(Color.rgb(27,27,27));

    }


    public void setComponenteGaussimetro(int componenteCampo) {

        this.componenteCampo = componenteCampo;

    }

    private void captarSensor(Context context) {

        //captamos el servicio del sensor
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_FASTEST);

    }

    //se activa sólo cuando hay cambios
    public void onSensorChanged(SensorEvent event) {

        //en x
        float a = 0;
        float medida_x = 0;
        float medida_y = 0;
        float medida_z = 0;
        float medida = 0;

        medida_x = event.values[SensorManager.DATA_X];
        medida_y = event.values[SensorManager.DATA_Y];
        medida_z = event.values[SensorManager.DATA_Z];
        float resultado = medida_x * medida_x + medida_y * medida_y + medida_z * medida_z;
        a = (float) (Math.sqrt(resultado));


        if (componenteCampo == 1) {
            medida = medida_x;
            this.setUnidades(" Bx (uT)");
        }
        if (componenteCampo == 2){
            medida = medida_y;
            this.setUnidades(" By (uT)");
        }
        if (componenteCampo == 3){
            medida = medida_z;
            this.setUnidades(" Bz (uT)");
        }
        if (componenteCampo == 4){
            medida = a;
            this.setUnidades(" B (uT)");
        }

        //un decimal
        medida = (float) (Math.round(medida * 10) / 10.0f);
        this.setMedida(medida);
        //almacenar dato actual
        AlmacenDatosRAM.datoActual = medida;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}


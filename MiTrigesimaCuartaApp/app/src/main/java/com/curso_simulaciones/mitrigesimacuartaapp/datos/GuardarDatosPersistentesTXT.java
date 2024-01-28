package com.curso_simulaciones.mitrigesimacuartaapp.datos;

import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class GuardarDatosPersistentesTXT {

     /*
      El vector es similar a un arreglo []
      con la diferencia que el mismo se redimensiona
      al doble de su tamaño cuando se llena (se duplica).
      Además, proporciona métodos adicionales
      para añadir, eliminar elementos, e insertar
      elementos entre otros dos existentes.
      Se pueden guardar objetos de diferentes tipo.
     */

    private Vector almacen_tiempo = new Vector();
    private Vector almacen_datosBx = new Vector();
    private Vector almacen_datosBy = new Vector();
    private Vector almacen_datosBz = new Vector();
    private Vector almacen_datosB = new Vector();


    /**
     * Constructor del Manejador de  Archivos
     */
    public GuardarDatosPersistentesTXT(){


    }

    /**
     * Para recibir los datos que se grabarán
     * en archivo .txt en una carpeta definida
     * por el usuario en un documento.
     * Serán dos columnas una para X y otra para Y.

     */

    public void llenarDatos(double tiempo, double datoBx, double datoBy,double datoBz,double datoB) {

        almacen_tiempo.addElement(tiempo);
        almacen_datosBx.addElement(datoBx);
        almacen_datosBy.addElement(datoBy);
        almacen_datosBz.addElement(datoBz);
        almacen_datosB.addElement(datoB);

    }


    /**
     * Para borrar los datos. Estos no serán
     * guardados en el arcivo .txt.
     */
    public void borrarDatos() {

        almacen_tiempo.removeAllElements();
        almacen_datosBx.removeAllElements();
        almacen_datosBy.removeAllElements();
        almacen_datosBz.removeAllElements();
        almacen_datosB.removeAllElements();

    }


    /**
     * Para guradar los datos en formato .txt
     * @param actividad
     * @param carpeta
     */

    public void guardar(Activity actividad, String carpeta) {

        Date date = new Date();
        DateFormat hora_fecha = new SimpleDateFormat("yy-MM-dd hh:mm:ss");


        try {

            /*
              Paso 1 y Paso 2: Crear y abrir flujo de salida
              Esto es, crear y abrir el canal de salida
            */

            String marca= hora_fecha.format(date).toString();
            String nombre_archivo ="datos_"+marca+ ".txt";

            File file=null;
            File path = null;

            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q){
                //versiones inferiores a Android 10
                path = new File(Environment.getExternalStorageDirectory(), carpeta);

            } else {

                //versiones desde Android 10 en adelante
                path = new File(actividad.getExternalFilesDir(null), carpeta);

            }

            file = new File(path, nombre_archivo);
            FileOutputStream flujoSalida= new FileOutputStream(file);

            /*
             Agregar filtro. En este caso se le pasa a
             OutputStreamWriter para que escriba
           */
            OutputStreamWriter escritor = new OutputStreamWriter(flujoSalida);//fos);


            /*
              Paso 3: Escribir información mientras haya
            */

            // Escribimos el String en el archivo
            for (int i = 0; i < almacen_tiempo.size(); i = i + 1) {
                //importar los datos
                double tiempo = ((Double)(almacen_tiempo.get(i))).doubleValue();
                double datoBx = ((Double)(almacen_datosBx.get(i))).doubleValue();
                double datoBy = ((Double)(almacen_datosBy.get(i))).doubleValue();
                double datoBz = ((Double)(almacen_datosBz.get(i))).doubleValue();
                double datoB = ((Double)(almacen_datosB.get(i))).doubleValue();

                escritor.write(""+(i+1));

                escritor.write("\t");
                escritor.write("\t");
                escritor.write("\t");
                escritor.write("\t");

                //desplegar con dos decimales
                float tiempo_dos_decimales = (float)(Math.round(tiempo * 100) / 100f);
                escritor.write(""+tiempo_dos_decimales);

                escritor.write("\t");
                escritor.write("\t");
                escritor.write("\t");
                escritor.write("\t");

                float dato_dos_decimales= (float)(Math.round(datoBx * 100) / 100f);
                escritor.write(""+dato_dos_decimales);

                escritor.write("\t");
                escritor.write("\t");
                escritor.write("\t");
                escritor.write("\t");

                dato_dos_decimales= (float)(Math.round(datoBy * 100) / 100f);
                escritor.write(""+dato_dos_decimales);

                escritor.write("\t");
                escritor.write("\t");
                escritor.write("\t");
                escritor.write("\t");

                dato_dos_decimales= (float)(Math.round(datoBz * 100) / 100f);
                escritor.write(""+dato_dos_decimales);

                escritor.write("\t");
                escritor.write("\t");
                escritor.write("\t");
                escritor.write("\t");

                dato_dos_decimales= (float)(Math.round(datoB * 100) / 100f);
                escritor.write(""+dato_dos_decimales + "\r\n");



            }

           /*
            Paso 4: cerrar canal
           */
            escritor.flush();
            escritor.close();

            // Mostramos que se ha guardado
            String aviso= "Los datos fueron guardados en la carpeta:" + "\n" +
                    "Mis archivos/"+carpeta;
            Toast.makeText(actividad, aviso, 2000).show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


}


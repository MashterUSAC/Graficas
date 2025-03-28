package Ordenar;

import modelo.AlgoritmoOrdenamiento;

public class BubbleSort extends AlgoritmoOrdenamiento {
    @Override
    public void ordenar(boolean ascendente) {
        tiempoInicio = System.currentTimeMillis();
        pasos = 0;
        int n = datos.length;
        
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                boolean condicion = ascendente ? 
                    datos[j] > datos[j+1] : datos[j] < datos[j+1];
                
                if (condicion) {
                    intercambiar(j, j+1);
                }
            }
        }
    }
}
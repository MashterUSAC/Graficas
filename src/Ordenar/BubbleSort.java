package Ordenar;

import Modelo.AlgoritmoOrdenamiento;
import Modelo.ProgresoOrdenamiento;

public class BubbleSort implements AlgoritmoOrdenamiento {
    @Override
    public void ordenar(int[] valores, String[] categorias, boolean ascendente, 
                      int velocidadMs, ProgresoOrdenamiento callback) {
        int n = valores.length;
        int pasos = 0;
        
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if ((ascendente && valores[j] > valores[j+1]) || 
                    (!ascendente && valores[j] < valores[j+1])) {
                    // Intercambiar valores
                    int tempVal = valores[j];
                    valores[j] = valores[j+1];
                    valores[j+1] = tempVal;
                    
                    // Intercambiar categorías
                    String tempCat = categorias[j];
                    categorias[j] = categorias[j+1];
                    categorias[j+1] = tempCat;
                    
                    pasos++;
                    
                    // Notificar progreso
                    if (callback != null) {
                        callback.actualizar(valores.clone(), categorias.clone(), pasos);
                    }
                    
                    // Pausa para visualización
                    try {
                        Thread.sleep(velocidadMs);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }
}
package Ordenar;

import Modelo.AlgoritmoOrdenamiento;
import javax.swing.*;

public class BubbleSort implements AlgoritmoOrdenamiento {
    @Override
    public void ordenar(int[] valores, String[] categorias, boolean ascendente, int velocidad) {
        int n = valores.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                boolean debeIntercambiar = ascendente ? 
                    valores[j] > valores[j+1] : 
                    valores[j] < valores[j+1];
                
                if (debeIntercambiar) {
                    // Intercambiar valores
                    int tempVal = valores[j];
                    valores[j] = valores[j+1];
                    valores[j+1] = tempVal;
                    
                    // Intercambiar categorías
                    String tempCat = categorias[j];
                    categorias[j] = categorias[j+1];
                    categorias[j+1] = tempCat;
                    
                    // Pausa para visualización
                    try {
                        Thread.sleep(velocidad);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}
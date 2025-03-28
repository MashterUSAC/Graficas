package Ordenar;

import modelo.AlgoritmoOrdenamiento;

public class QuickSort extends AlgoritmoOrdenamiento {
    @Override
    public void ordenar(boolean ascendente) {
        tiempoInicio = System.currentTimeMillis();
        pasos = 0;
        quickSort(0, datos.length - 1, ascendente);
    }
    
    private void quickSort(int low, int high, boolean ascendente) {
        if (low < high) {
            int pi = particion(low, high, ascendente);
            quickSort(low, pi - 1, ascendente);
            quickSort(pi + 1, high, ascendente);
        }
    }
    
    private int particion(int low, int high, boolean ascendente) {
        int pivot = datos[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            boolean condicion = ascendente ? 
                datos[j] < pivot : datos[j] > pivot;
                
            if (condicion) {
                i++;
                intercambiar(i, j);
            }
        }
        intercambiar(i + 1, high);
        return i + 1;
    }
}
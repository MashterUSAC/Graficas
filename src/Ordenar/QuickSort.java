package Ordenar;

import Modelo.AlgoritmoOrdenamiento;
import Modelo.ProgresoOrdenamiento;

public class QuickSort implements AlgoritmoOrdenamiento {
    @Override
    public void ordenar(int[] valores, String[] categorias, boolean ascendente, 
                      int velocidadMs, ProgresoOrdenamiento callback) {
        quickSort(valores, categorias, 0, valores.length-1, ascendente, velocidadMs, callback);
    }

    private void quickSort(int[] arr, String[] cats, int low, int high, 
                         boolean ascendente, int velocidadMs, ProgresoOrdenamiento callback) {
        if (low < high) {
            int pi = partition(arr, cats, low, high, ascendente, velocidadMs, callback);
            
            quickSort(arr, cats, low, pi-1, ascendente, velocidadMs, callback);
            quickSort(arr, cats, pi+1, high, ascendente, velocidadMs, callback);
        }
    }

    private int partition(int[] arr, String[] cats, int low, int high, 
                         boolean ascendente, int velocidadMs, ProgresoOrdenamiento callback) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if ((ascendente && arr[j] < pivot) || (!ascendente && arr[j] > pivot)) {
                i++;
                
                // Intercambiar arr[i] y arr[j]
                int tempVal = arr[i];
                arr[i] = arr[j];
                arr[j] = tempVal;
                
                // Intercambiar cats[i] y cats[j]
                String tempCat = cats[i];
                cats[i] = cats[j];
                cats[j] = tempCat;
                
                // Notificar progreso
                if (callback != null) {
                    callback.actualizar(arr.clone(), cats.clone(), 0); // Pasos se cuentan diferente
                }
                
                // Pausa para visualización
                try {
                    Thread.sleep(velocidadMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return -1;
                }
            }
        }
        
        // Intercambiar arr[i+1] y arr[high] (pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        
        // Intercambiar categorías correspondientes
        String tempCat = cats[i+1];
        cats[i+1] = cats[high];
        cats[high] = tempCat;
        
        return i+1;
    }
}
package Modelo.Ordenar;

import Modelo.AlgoritmoOrdenamiento;
import javax.swing.SwingUtilities;

public class QuickSort implements AlgoritmoOrdenamiento {
    @Override
    public void ordenar(int[] valores, String[] categorias, boolean ascendente, int velocidad) {
        quickSort(valores, categorias, 0, valores.length-1, ascendente, velocidad);
    }
    
    private void quickSort(int[] arr, String[] cats, int low, int high, boolean ascendente, int velocidad) {
        if (low < high) {
            int pi = partition(arr, cats, low, high, ascendente, velocidad);
            quickSort(arr, cats, low, pi-1, ascendente, velocidad);
            quickSort(arr, cats, pi+1, high, ascendente, velocidad);
        }
    }
    
    private int partition(int[] arr, String[] cats, int low, int high, boolean ascendente, int velocidad) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            boolean condicion = ascendente ? 
                arr[j] < pivot : 
                arr[j] > pivot;
            
            if (condicion) {
                i++;
                swap(arr, cats, i, j);
                
                try {
                    Thread.sleep(velocidad);
                    SwingUtilities.invokeLater(() -> {});
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        swap(arr, cats, i+1, high);
        return i+1;
    }
    
    private void swap(int[] arr, String[] cats, int i, int j) {
        int tempVal = arr[i];
        arr[i] = arr[j];
        arr[j] = tempVal;
        
        String tempCat = cats[i];
        cats[i] = cats[j];
        cats[j] = tempCat;
    }
}
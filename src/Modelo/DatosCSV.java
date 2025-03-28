import java.util.Arrays;

public class DatosCSV {
    private String[] categorias;
    private int[] valores;
    private String ejeX;
    private String ejeY;
    private String tituloGrafica;

    public void cargarDatos(String[] categorias, int[] valores, String ejeX, String ejeY) {
        this.categorias = Arrays.copyOf(categorias, categorias.length);
        this.valores = Arrays.copyOf(valores, valores.length);
        this.ejeX = ejeX;
        this.ejeY = ejeY;
    }

    public void setTituloGrafica(String titulo) {
        this.tituloGrafica = titulo;
    }

    // Getters
    public String[] getCategorias() { return categorias; }
    public int[] getValores() { return valores; }
    public String getEjeX() { return ejeX; }
    public String getEjeY() { return ejeY; }
    public String getTituloGrafica() { return tituloGrafica; }

    // Método para ordenar los datos
    public void ordenarDatos(boolean ascendente, String algoritmo) {
        // Implementar los diferentes algoritmos de ordenamiento aquí
        switch(algoritmo) {
            case "Bubble Sort":
                bubbleSort(ascendente);
                break;
            case "Insert Sort":
                insertSort(ascendente);
                break;
            // Añadir los demás algoritmos...
        }
    }

    private void bubbleSort(boolean ascendente) {
        int n = valores.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                boolean condicion = ascendente ? valores[j] > valores[j+1] : valores[j] < valores[j+1];
                if (condicion) {
                    // Intercambiar valores
                    int tempVal = valores[j];
                    valores[j] = valores[j+1];
                    valores[j+1] = tempVal;
                    
                    // Intercambiar categorías
                    String tempCat = categorias[j];
                    categorias[j] = categorias[j+1];
                    categorias[j+1] = tempCat;
                }
            }
        }
    }

    private void insertSort(boolean ascendente) {
        // Implementación de Insert Sort
        // Similar a bubbleSort pero con la lógica de Insertion Sort
    }
    
    private void selectSort(boolean ascendente) {
    int n = valores.length;
    for (int i = 0; i < n-1; i++) {
        int extremo = i;
        for (int j = i+1; j < n; j++) {
            boolean condicion = ascendente ? valores[j] < valores[extremo] : valores[j] > valores[extremo];
            if (condicion) {
                extremo = j;
            }
        }
        
        // Intercambiar
        int tempVal = valores[extremo];
        valores[extremo] = valores[i];
        valores[i] = tempVal;
        
        String tempCat = categorias[extremo];
        categorias[extremo] = categorias[i];
        categorias[i] = tempCat;
    }
}

private void quickSort(boolean ascendente) {
    quickSortRecursivo(0, valores.length-1, ascendente);
}

private void quickSortRecursivo(int low, int high, boolean ascendente) {
    if (low < high) {
        int pi = particion(low, high, ascendente);
        quickSortRecursivo(low, pi-1, ascendente);
        quickSortRecursivo(pi+1, high, ascendente);
    }
}

private int particion(int low, int high, boolean ascendente) {
    int pivot = valores[high];
    int i = low - 1;
    
    for (int j = low; j < high; j++) {
        boolean condicion = ascendente ? valores[j] < pivot : valores[j] > pivot;
        if (condicion) {
            i++;
            
            // Intercambiar valores
            int tempVal = valores[i];
            valores[i] = valores[j];
            valores[j] = tempVal;
            
            // Intercambiar categorías
            String tempCat = categorias[i];
            categorias[i] = categorias[j];
            categorias[j] = tempCat;
        }
    }
    
    // Intercambiar el pivote
    int tempVal = valores[i+1];
    valores[i+1] = valores[high];
    valores[high] = tempVal;
    
    String tempCat = categorias[i+1];
    categorias[i+1] = categorias[high];
    categorias[high] = tempCat;
    
    return i+1;
}

// Implementar también mergeSort y shellSort...
    
    // Implementar los demás algoritmos de ordenamiento...
}
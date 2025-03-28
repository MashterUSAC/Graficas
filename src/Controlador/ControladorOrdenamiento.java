import java.util.Vector;

public class ControladorOrdenamiento {
    private long tiempoInicio;
    private int pasos;
    private int delay;

    public ControladorOrdenamiento() {
        pasos = 0;
    }

    public void setVelocidad(String velocidad) {
        switch (velocidad) {
            case "Alta":
                delay = 100;
                break;
            case "Media":
                delay = 500;
                break;
            case "Baja":
                delay = 1000;
                break;
            default:
                delay = 500;
        }
    }

    public void iniciarTiempo() {
        tiempoInicio = System.currentTimeMillis();
        pasos = 0;
    }

    public String getTiempoTranscurrido() {
        long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
        long minutos = (tiempoTranscurrido / 1000) / 60;
        long segundos = (tiempoTranscurrido / 1000) % 60;
        long milisegundos = tiempoTranscurrido % 1000;
        
        return String.format("%02d:%02d:%03d", minutos, segundos, milisegundos);
    }

    public int getPasos() {
        return pasos;
    }

    public void incrementarPasos() {
        pasos++;
    }

    public int getDelay() {
        return delay;
    }

    // Métodos de ordenamiento (ejemplo con Bubble Sort)
    public void bubbleSort(Vector<Integer> valores, Vector<String> categorias, boolean ascendente) {
        int n = valores.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                boolean condicion = ascendente ? 
                    valores.get(j) > valores.get(j+1) : 
                    valores.get(j) < valores.get(j+1);
                
                if (condicion) {
                    // Intercambiar valores
                    int tempVal = valores.get(j);
                    valores.set(j, valores.get(j+1));
                    valores.set(j+1, tempVal);
                    
                    // Intercambiar categorías
                    String tempCat = categorias.get(j);
                    categorias.set(j, categorias.get(j+1));
                    categorias.set(j+1, tempCat);
                   
                    incrementarPasos();
                    
                    try {
                        Thread.sleep(getDelay());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    // Aquí deberías implementar los demás algoritmos de ordenamiento...
}
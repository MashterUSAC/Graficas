package modelo;

public abstract class AlgoritmoOrdenamiento {
    protected int[] datos;
    protected int pasos;
    protected long tiempoInicio;
    
    public abstract void ordenar(boolean ascendente);
    
    public void setDatos(int[] datos) {
        this.datos = datos.clone();
    }
    
    public int[] getDatos() {
        return datos;
    }
    
    public int getPasos() {
        return pasos;
    }
    
    public long getTiempoEjecucion() {
        return System.currentTimeMillis() - tiempoInicio;
    }
    
    protected void intercambiar(int i, int j) {
        int temp = datos[i];
        datos[i] = datos[j];
        datos[j] = temp;
        pasos++;
    }
}
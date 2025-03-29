package Modelo;

public interface AlgoritmoOrdenamiento {
    void ordenar(int[] valores, String[] categorias, boolean ascendente, 
               int velocidadMs, ProgresoOrdenamiento callback);
}
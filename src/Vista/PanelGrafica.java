package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PanelGrafica extends JPanel {
    private String[] categorias;
    private int[] valores;
    private String titulo = "Gráfica de Datos";
    
    public PanelGrafica() {
        setPreferredSize(new Dimension(800, 400));
        setBackground(Color.WHITE);
    }
    
    public void setDatos(String[] categorias, int[] valores) {
        this.categorias = categorias;
        this.valores = valores;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        if (valores == null || categorias == null || valores.length == 0) {
            g2d.drawString("No hay datos para mostrar", getWidth()/2-50, getHeight()/2);
            return;
        }
        
        // Dibujar gráfica de barras
        int anchoBarra = getWidth() / (valores.length * 2);
        int maxValor = getMaxValue(valores);
        
        // Dibujar ejes
        g2d.drawLine(50, getHeight()-50, getWidth()-50, getHeight()-50); // Eje X
        g2d.drawLine(50, 50, 50, getHeight()-50); // Eje Y
        
        // Dibujar barras
        for (int i = 0; i < valores.length; i++) {
            int alturaBarra = (int)((valores[i] / (double)maxValor) * (getHeight()-100));
            int x = 60 + i * (anchoBarra + 10);
            int y = getHeight() - 50 - alturaBarra;
            
            g2d.setColor(new Color(70, 130, 180));
            g2d.fillRect(x, y, anchoBarra, alturaBarra);
            
            // Etiquetas
            g2d.setColor(Color.BLACK);
            g2d.drawString(categorias[i], x, getHeight()-35);
            g2d.drawString(String.valueOf(valores[i]), x, y-5);
        }
    }
    
    private int getMaxValue(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
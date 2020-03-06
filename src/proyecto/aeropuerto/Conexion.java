/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.aeropuerto;

import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author SamuelLMiller & Gtoro98
 */
public class Conexion {
    
    private Ciudad paisOrigen;
    private Ciudad paisDestino;
    private Vuelo vuelo;
    
    public Conexion() {
    }
    
    public Conexion(Ciudad paisOrigen, Ciudad paisDestino, Vuelo vuelo) {
        this.paisOrigen = paisOrigen;
        this.paisDestino = paisDestino;
        this.vuelo = vuelo;
    }

    public Ciudad getPaisOrigen() {
        return paisOrigen;
    }
    
    public Ciudad getPaisDestino() {
        return paisDestino;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setPaisOrigen(Ciudad paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public void setPaisDestino(Ciudad paisDestino) {
        this.paisDestino = paisDestino;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    
    public void drawLine(JPanel panel, Graphics2D g2, int x1, int y1, int x2, int y2){
        g2 = (Graphics2D) panel.getGraphics();
        g2.drawLine(x1, y1, x2, y2);
    }
    
    public void removeLine(JPanel panel, Graphics2D g2, int x1, int y1, int x2, int y2){
        g2 = (Graphics2D) panel.getGraphics();
        g2.setColor(panel.getBackground());
        g2.drawLine(x1, y1, x2, y2);
        
    }
    
}

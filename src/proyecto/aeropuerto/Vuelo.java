/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.aeropuerto;

import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author SamuelLMiller & Gtoro98
 */
public class Vuelo {
    private int numVuelo; 
    private String aerolinea;
    private double costo;
    private double duracion; //En horas. Ej: 2,3 horas
    private int x1, y1, x2, y2;

    public Vuelo() {
    }
    
    public Vuelo(int numVuelo, String aerolinea, double costo, double duracion) {
        this.numVuelo = numVuelo;
        this.aerolinea = aerolinea;
        this.costo = costo;
        this.duracion = duracion;
    }

    public int getNumVuelo() {
        return numVuelo;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
   

    public String getAerolinea() {
        return aerolinea;
    }

    public double getCosto() {
        return costo;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setNumVuelo(int numVuelo) {
        this.numVuelo = numVuelo;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
    
}

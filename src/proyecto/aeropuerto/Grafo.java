/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.aeropuerto;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 *
 * @author SamuelLMiller & Gtoro98
 */
public class Grafo {
    private Ciudad ciudad;
    private Vuelo vuelo;
    private Conexion conexion;
    public ListaCiudades cityList = new ListaCiudades();
    public ListaVuelos flightList = new ListaVuelos();
    private MenuPrincipal component;
    double matrizTiempo[][];
    double matrizPrecio[][];
    Ciudad matrizCiudades[][];
    int camino[];
    //Cambie de tipo Vuelo a tipo Conexion
    private Conexion matrixAdy[][];
    private int numCiudades;

    public Ciudad[][] getMatrizCiudades() {
        return matrizCiudades;
    }

    public double[][] getMatrizTiempo() {
        return matrizTiempo;
    }

    public void setMatrizCiudades(Ciudad[][] matrizCiudades) {
        this.matrizCiudades = matrizCiudades;
    }

    public void setMatrizTiempo(double[][] matrizTiempo) {
        this.matrizTiempo = matrizTiempo;
    }

    public void setCamino(int[] camino) {
        this.camino = camino;
    }
    
    
    
    //Creando matriz de adyacencia vacia
    public Grafo(int numCiudades) {
        this.numCiudades = numCiudades;
        
        matrixAdy = new Conexion[numCiudades][numCiudades];
        
        for (int i = 0; i < this.getNumCiudades(); i++) {
            for (int j = 0; j < this.getNumCiudades(); j++) {
                matrixAdy[i][j] = null;
            }
            
        }
        
    }
    
    //Metodo para modificar la matriz
    public void refreshGrafo(){  
        this.setNumCiudades(this.getNumCiudades() + 1);
        Conexion[][] matrixAux = new Conexion[this.getNumCiudades()][this.getNumCiudades()];
        
        for (int i = 0; i < this.getNumCiudades(); i++) {
            for (int j = 0; j < this.getNumCiudades(); j++) {
                
                if(i == this.getNumCiudades()-1 || j == this.getNumCiudades()-1){
                    matrixAux[i][j] = null;
                }
                else{
                   matrixAux[i][j] = this.getMatrixAdy()[i][j]; 
                }
            }   
        }
        
        this.setMatrixAdy(matrixAux);
    }
    
    //Metodo para eliminar la ciudad de la matriz de adyacencia
    public void removeCity(int n){
        
        this.setNumCiudades(this.getNumCiudades() - 1);
        Conexion[][] matrixAux = new Conexion[this.getNumCiudades()][this.getNumCiudades()];
        
        for (int i = 0; i < this.getNumCiudades(); i++) {
            for (int j = 0; j < this.getNumCiudades(); j++) {
                if (i == n || j == n){
                    continue;
                }
                else{
                    matrixAux[i][j] = this.getMatrixAdy()[i][j];
                }
            }
        }
        
        this.setMatrixAdy(matrixAux);
    }
    
    public void print(){
        for (int i = 0; i < this.getNumCiudades(); i++) {
            for (int j = 0; j < this.getNumCiudades(); j++) {
                System.out.print(matrixAdy[i][j]);
            }
            System.out.println("");
        }
    }
    
    //Getters y Setters
    public Conexion[][] getMatrixAdy() {
        return matrixAdy;
    }

    public int getNumCiudades() {
        return numCiudades;
    }

    public double[][] getMatrizPrecio() {
        return matrizPrecio;
    }

    public void setMatrizPrecio(double[][] matrizPrecio) {
        this.matrizPrecio = matrizPrecio;
    }
    

    public void setMatrixAdy(Conexion[][] matrixAdy) {
        this.matrixAdy = matrixAdy;
    }

    public void setNumCiudades(int numCiudades) {
        this.numCiudades = numCiudades;
    }
    
    
    public Conexion getConexion() {
        return conexion;
    }
    
    public Ciudad getCiudad() {
        return ciudad;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    
    //Metodo para repintar todos los circulos del panel
    public void repaintCircles(JPanel jPanel, Graphics2D g2){
        //Redibujar todos las ciudades (circulos)
        for (int i = 0; i < this.numCiudades; i++) {
            this.cityList.obtainValue(i).drawCircle(jPanel, g2, this.cityList.obtainValue(i).getX(), this.cityList.obtainValue(i).getY(), this.cityList.obtainValue(i).getNombre());
        }
    }
    
    //Metodo para repintar todas las lineas del panel
    public void repaintLines(JPanel jPanel, Graphics2D g2){
        //Redibujar todos los vuelos (lineas)
        for (int i = 0; i < this.flightList.size; i++) {
            this.flightList.obtainValue(i).drawLine(jPanel, g2, flightList.obtainValue(i).getPaisOrigen().getX(), flightList.obtainValue(i).getPaisOrigen().getY(), flightList.obtainValue(i).getPaisDestino().getX(), flightList.obtainValue(i).getPaisDestino().getY());
        }
    }
    
    //Metodo para obtener un numero random para asignarle a una posicion X
    public int randomX(){
        int randomX = (int) (Math.random() * (845 - 30) + 1) + 30;
        return randomX;
    }
    
    //Metodo para obtener un numero random para asignarle a una posicion Y
    public int randomY(){
        int randomY = (int)(Math.random() * (495-30) + 1) + 30;

        return randomY;
    }
    
        //Metodo para añadir un vuelo a la matriz de adyacencia
    public void addVuelo(Conexion connection){

        Ciudad ciudadDes = connection.getPaisDestino();
        Ciudad ciudadOr = connection.getPaisOrigen();

        int posOr = 0, posDes = 0;

        for (int i = 0; i < cityList.size; i++) {
            if(cityList.obtainValue(i) == ciudadDes){
                posDes = i;
                break;
            }
        }
        for (int j = 0; j < cityList.size; j++) {
            if(cityList.obtainValue(j) == ciudadOr){
                posOr = j;
                break;
            }
        }
          getMatrixAdy()[posOr][posDes] = connection;
    }
    
    //Método para comprobar si un string es un double
    public boolean isDouble(String duracion){
        try{
            Double.parseDouble(duracion);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    
    //Metodo para comprobar si una string es un int
    public boolean isInt(String numVuelo){
        try{
            Integer.parseInt(numVuelo);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    
    //Metodo para agregar ua ciudad
    public void agregarCiudad(String ciudadNombre, JPanel panel, Graphics2D g2){
        
        //Creamos un nuevo objeto de tipo ciudad
        Ciudad city = new Ciudad(ciudadNombre);
        
        
        String ciudadPais = JOptionPane.showInputDialog(null, "Ingrese el país en el que se encuentra la ciudad", "Agregar Ciudad", JOptionPane.QUESTION_MESSAGE);

            //Si ingresó un país vacío
            while("".equals(ciudadPais)){
                JOptionPane.showMessageDialog(null, "Tiene que ingresar el país", "Advertencia", JOptionPane.WARNING_MESSAGE);
                ciudadPais = JOptionPane.showInputDialog(null, "Ingrese el país en el que se encuentra la ciudad", "Agregar Ciudad", JOptionPane.QUESTION_MESSAGE);
            }

            //Si seleccionó el botón cancelar
            if (ciudadPais == null){
                
            }
            else{
                city.setPais(ciudadPais);
                String ciudadAero = JOptionPane.showInputDialog(null, "Ingrese el nombre del aeropuerto", "Agregar Ciudad", JOptionPane.QUESTION_MESSAGE);

                //Si ingresó un aeropuerto vacío
                while("".equals(ciudadAero)){
                    JOptionPane.showMessageDialog(null, "Tiene que ingresar el nombre del aeropuerto", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    ciudadAero = JOptionPane.showInputDialog(null, "Ingrese el nombre del aeropuerto", "Agregar Ciudad", JOptionPane.QUESTION_MESSAGE);
                }
                //Si seleccionó el botón cancelar
                if(ciudadAero == null){
                }

                else{
                    city.setAeropuerto(ciudadAero);
                    this.cityList.insertFinal(city);

                    //Variables para saber si la ciudad escrita ya está registrada
                    int temp1 = this.cityList.getIndex(city);
                    boolean flag = true;

                    while (temp1 - 1 >= 0){
                        //Si se consigue una ciudad con el mismo nombre, entonces se elimina de la lista de ciudades
                        if(city.getNombre().equalsIgnoreCase(this.cityList.obtainValue(temp1 -1).getNombre())){
                            JOptionPane.showMessageDialog(null, "La ciudad " + city.getNombre() + " ya está registrada", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            flag = false;
                            this.cityList.removeElement(city);
                            break;
                        }
                        temp1--;
                    }

                    if(flag){
                        refreshGrafo();
                        
                        //Empezaremos a conseguir posiciones 'X' e 'Y' para asignarselas aleatoriamente a la ciudad creada
                        int x = randomX();
                        int y = randomY();

                        int temp = this.cityList.getIndex(city);
                        while (temp - 1 >= 0){
                            if (this.cityList.obtainValue(temp - 1).getX() == x || this.cityList.obtainValue(temp - 1).getY() == y || Math.abs(this.cityList.obtainValue(temp - 1).getX() - x) < 60 || Math.abs(this.cityList.obtainValue(temp - 1).getY() - x) < 60){
                                x = randomX();
                                y = randomY();
                            }
                            else{
                                temp--;
                            }
                        }
                        
                        //Una vez conseguidas, se las asignamos a la ciudad
                        city.setX(x);
                        city.setY(y);
                        
                        //Dibujamos el circulo
                        city.drawCircle(panel, g2, city.getX(), city.getY(), city.getNombre());
                        
                        //Redibujamos todos los vuelos y ciudades
                        repaintLines(panel, g2);
                        repaintCircles(panel, g2);
                    }
                }
            } 
    }
    
    
    //Metodo para eliminar una ciudad
    public void eliminarCiudad(JPanel jPanel, Graphics2D g2){
        if (cityList.size == 0){
            JOptionPane.showMessageDialog(null, "No existe ninguna ciudad registrada en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            String[] ciudades = new String[cityList.size];

                for (int i = 0; i < cityList.size; i++) {
                    ciudades[i] = cityList.obtainValue(i).getNombre();
                }

                String ciudadesMenu = (String) JOptionPane.showInputDialog(null, "Seleccione la ciudad que desee eliminar", "Eliminar Ciudades", JOptionPane.QUESTION_MESSAGE, null, ciudades, ciudades[0]);
                
                //Si selecciono el boton cancelar
                if (ciudadesMenu == null){
                    
                }
                else{
                    int ans = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar la ciudad " + ciudadesMenu + "?", "Eliminar Ciudad", JOptionPane.YES_NO_OPTION);
                    
                    int ciudad = 0;
                    if(ans == 0){
                        
                        //Conseguir y eliminar la ciudad dentro de nuestra lista de ciudades
                        for (int i = 0; i < cityList.size; i++) {
                            if(ciudadesMenu.equals(cityList.obtainValue(i).getNombre())){
                                ciudad = i;
                                cityList.obtainValue(i).removeCircle(jPanel, g2, cityList.obtainValue(i).getX(), cityList.obtainValue(i).getY(), cityList.obtainValue(i).getNombre());
                                cityList.removeElementByIndex(i);
                                break;
                            }
                        }
                        
                        //Conseguir si existen vuelos de dicha ciudad y eliminarlo
                        int n = 0;
                        while (n < flightList.size){
                            if(ciudadesMenu.equals(flightList.obtainValue(n).getPaisOrigen().getNombre()) || ciudadesMenu.equals(flightList.obtainValue(n).getPaisDestino().getNombre())){
                                if(ciudadesMenu.equals(flightList.obtainValue(n).getPaisOrigen().getNombre())){
                                    flightList.obtainValue(n).removeLine(jPanel, g2, flightList.obtainValue(n).getPaisOrigen().getX(), flightList.obtainValue(n).getPaisOrigen().getY(), flightList.obtainValue(n).getPaisDestino().getX(), flightList.obtainValue(n).getPaisDestino().getY());
                                    flightList.obtainValue(n).getPaisDestino().drawCircle(jPanel, g2, flightList.obtainValue(n).getPaisDestino().getX(), flightList.obtainValue(n).getPaisDestino().getY(), flightList.obtainValue(n).getPaisDestino().getNombre());
                                }
                                else if(ciudadesMenu.equals(flightList.obtainValue(n).getPaisDestino().getNombre())){
                                    flightList.obtainValue(n).removeLine(jPanel, g2, flightList.obtainValue(n).getPaisDestino().getX(), flightList.obtainValue(n).getPaisDestino().getY(), flightList.obtainValue(n).getPaisOrigen().getX(), flightList.obtainValue(n).getPaisOrigen().getY());
                                    flightList.obtainValue(n).getPaisOrigen().drawCircle(jPanel, g2, flightList.obtainValue(n).getPaisOrigen().getX(), flightList.obtainValue(n).getPaisOrigen().getY(), flightList.obtainValue(n).getPaisOrigen().getNombre());
                                }
                                flightList.removeElementByIndex(n);
                            }
                            else{
                                n++;
                            }

                        }
                        //Eliminamos la ciudad de la matriz de adyacencia
                        removeCity(ciudad);
                        
                        //Repintamos todos los vuelos y ciudades
                        repaintLines(jPanel, g2);
                        repaintCircles(jPanel, g2);

                    }
                }
            
        }
    }
    
    public void removeAll(JPanel jPanel, Graphics2D g2){
//        jPanel.setBackground(Color.WHITE);
        jPanel.getGraphics().setColor(Color.WHITE);
        jPanel.setBackground(Color.WHITE);
        jPanel.getGraphics().clearRect(0, 0, jPanel.getWidth(), jPanel.getHeight());
        jPanel.getGraphics().setColor(Color.WHITE);
        jPanel.setBackground(Color.WHITE);
//        jPanel.getGraphics().fillRect(0, 0, jPanel.getWidth(), jPanel.getHeight());
        

        //Eliminamos todos los vuelos
        for (int i = flightList.size - 1; i >= 0; i--) {
//            flightList.obtainValue(i).removeLine(jPanel, g2, flightList.obtainValue(i).getPaisOrigen().getX(), flightList.obtainValue(i).getPaisOrigen().getY(), flightList.obtainValue(i).getPaisDestino().getX(), flightList.obtainValue(i).getPaisDestino().getY());
            flightList.removeElementByIndex(i);
        }
        
        //Eliminamos todas las ciudades
        for (int i = cityList.size - 1; i >= 0; i--) {
//            cityList.obtainValue(i).removeCircle(jPanel, g2, cityList.obtainValue(i).getX(), cityList.obtainValue(i).getY(), cityList.obtainValue(i).getNombre());
            cityList.removeElementByIndex(i);
            removeCity(i);
        }

    }
    
    //Metodo para agregar un vuelo
    public void agregarVuelo(JPanel panel, Graphics2D g2){
        Vuelo flight = new Vuelo();
        Conexion connection = new Conexion();
        
        //Si no existen al menos 2 ciudades en el sistema
        if (cityList.size < 2){
            JOptionPane.showMessageDialog(null, "Hacen falta al menos dos ciudades en el sistema para realizar un vuelo", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
        else{
            //Convierto la lista de ciudades a un Array para poder utilizarlo en el menú desplegable
            String[] ciudades = new String[cityList.size];
            
            for (int i = 0; i < cityList.size; i++) {
                ciudades[i] = cityList.obtainValue(i).getNombre();
            }
           
            String ciudadOr = (String) JOptionPane.showInputDialog(null, "Selecciona la ciudad origen", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE, null, ciudades, ciudades[0]);
            Ciudad ciudadOrigen = null;
            
            for (int i = 0; i < cityList.size; i++) {
                if(cityList.obtainValue(i).getNombre() == ciudadOr){
                    ciudadOrigen = cityList.obtainValue(i);
                }
            }
            //Si se selecciona el botón cancelar
            if (ciudadOr == null){
                
            }
            else{
                String ciudadDes = (String) JOptionPane.showInputDialog(null, "Selecciona la ciudad destino", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE, null, ciudades, ciudades[0]);
                Ciudad ciudadDestino = null;
                
                for (int i = 0; i < cityList.size; i++) {
                    if(cityList.obtainValue(i).getNombre() == ciudadDes){
                        ciudadDestino = cityList.obtainValue(i);
                        break;
                    }
                }
                //Si se selecciona el botón cancelar
                if(ciudadDes == null){
                    
                }
                else{
                    //Si se selecciona la misma ciudad
                    if(ciudadOr.equals(ciudadDes)){
                        JOptionPane.showMessageDialog(null, "No se puede realizar un viaje de una ciudad a ella misma", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    }
                    else{                         
                        
                        //Indico la duracion del vuelo
                        String temp = JOptionPane.showInputDialog(null, "Indique la duración del vuelo en horas", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE);
                        
                        //Si hizo click en cancelar
                        if (temp == null){
                            
                        }
                        else{
                            while (isDouble(temp) == false || "".equals(temp)){
                                JOptionPane.showMessageDialog(null, "Lo que ingresó anteriormente no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                                temp = JOptionPane.showInputDialog(null, "Indique la duración del vuelo en horas", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE);
                            }
                            
                            double duracion = Double.parseDouble(temp);
                            
                            //Ingreso el costo del vuelo
                            String costo = JOptionPane.showInputDialog(null, "Ingrese el costo del vuelo", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE);
                            
                            //Si hizo click en cancelar
                            if (costo == null){
                                
                            }
                            else{
                                while(isDouble(costo) == false || "".equals(costo)){
                                    JOptionPane.showMessageDialog(null, "Lo que ingresó anteriormente no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                                    costo = JOptionPane.showInputDialog(null, "Ingrese el costo del vuelo", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE);
                                }
                                
                                double cost = Double.parseDouble(costo);
                                
                                //Indico el numero de vuelo
                                String numVuelo = JOptionPane.showInputDialog(null, "Indique el número de vuelo", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE);
                                
                                //Si hizo click en cancelar
                                if (numVuelo == null){

                                }
                                else{
                                    
                                    while(isInt(numVuelo) == false || "".equals(numVuelo)){
                                        JOptionPane.showMessageDialog(null, "Lo que ingresó anteriormente no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                                        numVuelo = JOptionPane.showInputDialog(null, "Indique el número de vuelo", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE);
                                    }

                                    int flightNum = Integer.parseInt(numVuelo);
                                    
                                    //Indico el nombre de la aerolinea
                                    String lineaAerea = JOptionPane.showInputDialog(null, "Indique el nombre de la linea aerea", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE);
                                    
                                    //Si hizo click en cancelar
                                    if (lineaAerea == null){

                                    }
                                    else{
                                        while("".equals(lineaAerea)){
                                            JOptionPane.showMessageDialog(null, "Lo que ingresó anteriormente no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                                            lineaAerea = JOptionPane.showInputDialog(null, "Indique el nombre de la linea aerea", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE);
                                        }
                                        
                                        connection.setPaisOrigen(ciudadOrigen); //Ciudad origen
                                        connection.setPaisDestino(ciudadDestino); //Ciudad destino
                                        flight.setDuracion(duracion); //Duracion
                                        flight.setCosto(cost); //Costo
                                        flight.setNumVuelo(flightNum); //Numero de vuelo
                                        flight.setAerolinea(lineaAerea); //Aerolinea
                                        
                                        connection.setVuelo(flight); //Agrego el vuelo a la conexion
                                        
                                        //Verifico si ya existe el vuelo
                                        int temp1 = flightList.size;
                                        boolean flag = true;

                                        while (temp1 - 1 >= 0){
                                            if(connection.getPaisOrigen().equals(flightList.obtainValue(temp1 - 1).getPaisOrigen()) && connection.getPaisDestino().equals(flightList.obtainValue(temp1 - 1).getPaisDestino())){
                                                JOptionPane.showMessageDialog(null, "El vuelo " + connection.getPaisOrigen().getNombre().toString() + "-" + connection.getPaisDestino().getNombre().toString() + " ya existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
                                                flag = false;
                                                break;
                                            }
                                            temp1--;
                                        }
                                        
                                        if (flag){
                                            flightList.insertFinal(connection); //Agrego la conexion a la lista de vuelos
                                            addVuelo(connection); //Agrego el vuelo a la matriz de adyacencia
                                            connection.drawLine(panel, g2, ciudadOrigen.getX(), ciudadOrigen.getY(), ciudadDestino.getX(), ciudadDestino.getY());
//                                            ciudadOrigen.drawCircle(panel, g2, ciudadOrigen.getX(), ciudadOrigen.getY(), ciudadOrigen.getNombre());
//                                            ciudadDestino.drawCircle(panel, g2, ciudadDestino.getX(), ciudadDestino.getY(), ciudadDestino.getNombre());

                                            //Repinto todos los vuelos y ciudades
                                            repaintLines(panel, g2);
                                            repaintCircles(panel, g2);
                                        }    
                                    }
                                }    
                            }   
                        }  
                    }  
                }         
            } 
        }   
    }
    
    //Metodo para modificar el vuelo
    public void modificarVuelo(){
        //Si no existe ningun vuelo en el sistema
        if (flightList.size == 0){
            JOptionPane.showMessageDialog(null, "No existe ningun vuelo en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            //Convierto la lista de vuelos en un array para mostrarlo en el menu desplegable
            String[] vuelos = new String[flightList.size];

            for (int i = 0; i < flightList.size; i++) {
                vuelos[i] = flightList.obtainValue(i).getPaisOrigen().getNombre() + "-" + flightList.obtainValue(i).getPaisDestino().getNombre();
            }

            String vueloMenu = (String) JOptionPane.showInputDialog(null, "Seleccione el vuelo", "Modificar Vuelo", JOptionPane.QUESTION_MESSAGE, null, vuelos, vuelos[0]);
            Conexion vuelo = null;
            
            //Si hizo click en cancelar
            if (vueloMenu == null){

            }
            else{
                for (int i = 0; i < flightList.size; i++) {
                    if(vueloMenu.equals(flightList.obtainValue(i).getPaisOrigen().getNombre() + "-" + flightList.obtainValue(i).getPaisDestino().getNombre())){
                        vuelo = flightList.obtainValue(i);
                        break;
                    }
                }

                //Lista de opciones para colocar en el spinner
                String[] opcionesMod = {"Numero de vuelo", "Aerolinea", "Costo", "Duracion"};

                String opciones = (String) JOptionPane.showInputDialog(null, "Que desea modificar?", "Modificar Vuelo", JOptionPane.QUESTION_MESSAGE, null, opcionesMod, opcionesMod[0]);

                if (opciones == null){

                }
                else{
                    //Los casos de las opciones
                    switch (opciones){
                        case "Numero de vuelo":
                            String numVuelo = JOptionPane.showInputDialog(null, "El numero de vuelo actual es " + vuelo.getVuelo().getNumVuelo() + "\nIngrese el nuevo numero", "Modificar Vuelo", JOptionPane.QUESTION_MESSAGE);

                            if(numVuelo == null){
                                break;
                            }
                            else{
                               while(isInt(numVuelo) == false || "".equals(numVuelo)){
                                    JOptionPane.showMessageDialog(null, "Lo que ingresó anteriormente no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                                    numVuelo = JOptionPane.showInputDialog(null, "El numero de vuelo actual es " + vuelo.getVuelo().getNumVuelo() + "\nIngrese el nuevo numero", "Modificar Vuelo", JOptionPane.QUESTION_MESSAGE);
                                }

                                int flightNum = Integer.parseInt(numVuelo);
                                vuelo.getVuelo().setNumVuelo(flightNum); 
                                JOptionPane.showMessageDialog(null, "El nuevo numero de vuelo es " + vuelo.getVuelo().getNumVuelo(), "Modificar Vuelo", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }

                        case "Aerolinea":
                            String aerolinea = JOptionPane.showInputDialog(null, "El nombre de la aerolinea actual es " + vuelo.getVuelo().getAerolinea().toString() +"\nIngrese el nuevo nombre", "Modificar Vuelo", JOptionPane.QUESTION_MESSAGE);

                            if(aerolinea == null){

                            }
                            else{
                                while("".equals(aerolinea)){
                                    JOptionPane.showMessageDialog(null, "Lo que ingresó anteriormente no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                                    aerolinea = JOptionPane.showInputDialog(null, "Indique el nombre de la linea aerea", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE);

                                }
                                vuelo.getVuelo().setAerolinea(aerolinea);
                                JOptionPane.showMessageDialog(null, "El nuevo nombre de la aerolinea es " + vuelo.getVuelo().getAerolinea().toString(), "Modificar Vuelo", JOptionPane.INFORMATION_MESSAGE);
                            }
                            break;

                        case "Costo":
                            String costo = JOptionPane.showInputDialog(null, "El costo actual del vuelo es " + vuelo.getVuelo().getCosto() + "\nIngrese el nuevo costo", JOptionPane.QUESTION_MESSAGE);

                            if(costo == null){

                            }
                            else{
                                while(isDouble(costo) == false || "".equals(costo)){
                                    JOptionPane.showMessageDialog(null, "Lo que ingresó anteriormente no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                                    costo = JOptionPane.showInputDialog(null, "Ingrese el costo del vuelo", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE);
                                }

                                double cost = Double.parseDouble(costo);
                                vuelo.getVuelo().setCosto(cost);
                                JOptionPane.showMessageDialog(null, "El nuevo costo del vuelo es " + vuelo.getVuelo().getCosto(), "Modificar Vuelo", JOptionPane.INFORMATION_MESSAGE);
                            }
                            break;

                        case "Duracion":
                            String temp = JOptionPane.showInputDialog(null, "La duracion actual del vuelo es de " + vuelo.getVuelo().getDuracion() + " horas \nIngrese la nueva duracion", "Modificar Vuelo", JOptionPane.QUESTION_MESSAGE);

                            if (temp == null){

                            }
                            else{
                                while (isDouble(temp) == false || "".equals(temp)){
                                    JOptionPane.showMessageDialog(null, "Lo que ingresó anteriormente no es válido", "Error", JOptionPane.ERROR_MESSAGE);
                                    temp = JOptionPane.showInputDialog(null, "Indique la duración del vuelo en horas", "Agregar Vuelo", JOptionPane.QUESTION_MESSAGE);
                                }

                                double duracion = Double.parseDouble(temp);
                                vuelo.getVuelo().setDuracion(duracion);
                                JOptionPane.showMessageDialog(null, "La nueva duracion del vuelo es de " + vuelo.getVuelo().getDuracion() + " horas", "Modificar Vuelo", JOptionPane.INFORMATION_MESSAGE);
                            }
                            break;
                    }  
                }
            }
        }  
    }
    
    //Metodo para eliminar un vuelo
    public void eliminarVuelo(JPanel panel, Graphics2D g2){
        //Si no existe ningun vuelo en el sistema
        if (flightList.size == 0){
            JOptionPane.showMessageDialog(null, "No existe ningun vuelo registrado en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            
            //Convierto la lista de vuelos en un array para mostrarlo en el menu desplegable
            String[] vuelos = new String[flightList.size];

            for (int i = 0; i < flightList.size; i++) {
                vuelos[i] = flightList.obtainValue(i).getPaisOrigen().getNombre() + "-" + flightList.obtainValue(i).getPaisDestino().getNombre();
            }

            String vueloMenu = (String) JOptionPane.showInputDialog(null, "Seleccione el vuelo que desee eliminar", "Eliminar Vuelo", JOptionPane.QUESTION_MESSAGE, null, vuelos, vuelos[0]);
            Conexion vuelo = null;

            if (vueloMenu == null){

            }
            else{
                //Variables que me ayudaran a saber la posicion de las ciudades
                int posOr = 0, posDes = 0;
                String or = "";
                String des = "";
                
                //Verificar con el usuario su decision de eliminar la ciudad
                int ans = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el vuelo " + vueloMenu + "?", "Eliminar Vuelo", JOptionPane.YES_NO_OPTION);
                if (ans == 0){
                    for (int i = 0; i < flightList.size; i++) {
                        if(vueloMenu.equals(flightList.obtainValue(i).getPaisOrigen().getNombre() + "-" + flightList.obtainValue(i).getPaisDestino().getNombre())){
                            or = flightList.obtainValue(i).getPaisOrigen().getNombre();
                            des = flightList.obtainValue(i).getPaisDestino().getNombre();
                            
                            for (int j = 0; j < getNumCiudades(); j++) {
                                if(or.equals(cityList.obtainValue(j).getNombre())){
                                    posOr = j;
                                }
                                else if (des.equals(cityList.obtainValue(j).getNombre())){
                                    posDes = j;
                                }
                            }
                            flightList.obtainValue(i).removeLine(panel, g2, flightList.obtainValue(i).getPaisOrigen().getX(), flightList.obtainValue(i).getPaisOrigen().getY(), flightList.obtainValue(i).getPaisDestino().getX(), flightList.obtainValue(i).getPaisDestino().getY());
//                            flightList.obtainValue(i).getPaisOrigen().drawCircle(panel, g2, flightList.obtainValue(i).getPaisOrigen().getX(), flightList.obtainValue(i).getPaisOrigen().getY(), flightList.obtainValue(i).getPaisOrigen().getNombre());
//                            flightList.obtainValue(i).getPaisDestino().drawCircle(panel, g2, flightList.obtainValue(i).getPaisDestino().getX(), flightList.obtainValue(i).getPaisDestino().getY(), flightList.obtainValue(i).getPaisDestino().getNombre());
                            flightList.removeElementByIndex(i);
                            break;
                            
                        }
                    }
                    //Elimino el vuelo de la matriz de adyacencia
                    getMatrixAdy()[posOr][posDes] = null;
                    
                    //Repinto todos los vuelos y ciudades
                    repaintLines(panel, g2);
                    repaintCircles(panel, g2);
                }
                else{

                }
            }
        }   
    }
    
    //Metodo para cargar un archivo
    public void cargarArchivo(JPanel jPanel, Graphics2D g2, File file) throws IOException{
        int cont = 0;
        this.removeAll(jPanel, g2);
        
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br1 = new BufferedReader(fr);
            String info = null;
            
            while(!"fciudades".equals((info = br1.readLine()))){ 
                String[] objetoCiudad = info.split(",");    
     
                String nombreCiudad = objetoCiudad[0];
                String aeropuertoCiudad = objetoCiudad[1];
                String paisCiudad = objetoCiudad[2];
               
                Ciudad city = new Ciudad(nombreCiudad, aeropuertoCiudad, paisCiudad);
               
                cityList.insertFinal(city);
                refreshGrafo();
                
                int x = randomX();
                int y = randomY();

                int temp = cityList.getIndex(city);

                while (temp - 1 >= 0){
                    if(cityList.obtainValue(temp-1).getX() == x || cityList.obtainValue(temp-1).getY() == y || Math.abs(cityList.obtainValue(temp-1).getX() - x) < 30 || Math.abs(cityList.obtainValue(temp-1).getY() - y) < 30){
                        x = randomX();
                        y = randomY();
                    }
                    else{
                        temp--;
                    }
                }
                        
                city.setX(x);
                city.setY(y);

                city.drawCircle(jPanel, g2, city.getX(), city.getY(), city.getNombre());
                this.repaintCircles(jPanel, g2);
                
                       
            }
           
            cityList.printList();
            
            BufferedReader br2 = new BufferedReader(new FileReader(file));
            
            String infoCon = null;
            
            while(!"fvuelos".equals(infoCon = br2.readLine())){}
            infoCon = br2.readLine();
            
             while(!"fvuelos".equals(info = br1.readLine())){

                String[] objetoVuelo = info.split(",");    
                String[] objetoCon = infoCon.split(",");    

                int numVuelo = Integer.parseInt(objetoVuelo[0]);
                String aerolineaVuelo = objetoVuelo[1];
                double costoVuelo = Double.parseDouble(objetoVuelo[2]);
                double duracionVuelo = Double.parseDouble(objetoVuelo[3]);;

                Vuelo vuelo = new Vuelo(numVuelo, aerolineaVuelo, costoVuelo, duracionVuelo);
                
                String ciudadOrigen = objetoCon[0];
                String ciudadDestino = objetoCon[1];
                int numCon = Integer.parseInt(objetoCon[2]);
               
                System.out.println( ciudadOrigen + "" + ciudadDestino);
                Ciudad ciudadOr = null;
                int posOr = -1;
                for(int i = 0; i < cityList.size;i++){
                    if(cityList.obtainValue(i).getNombre().equals(ciudadOrigen)){
                        ciudadOr = cityList.obtainValue(i);
                        posOr = i;
                        break;
                    }
                
                }
                Ciudad ciudadDes = null;
                int posDes = -1;
                for(int j = 0; j < cityList.size;j++){
                
                    if(cityList.obtainValue(j).getNombre().equals(ciudadDestino)){
                        ciudadDes = cityList.obtainValue(j);
                        posDes = j;
                        break;
                    }

                }
                infoCon = br2.readLine();
                if(posOr != -1 && posDes != -1){
                    
                Conexion con = new Conexion(ciudadOr, ciudadDes, vuelo); 
                    
                getMatrixAdy()[posOr][posDes] = con;
                flightList.insertFinal(con);
                con.drawLine(jPanel, g2, con.getPaisOrigen().getX(), con.getPaisOrigen().getY(), con.getPaisDestino().getX(), con.getPaisDestino().getY());
                this.repaintLines(jPanel, g2);
                this.repaintCircles(jPanel, g2);
//                ciudadOr.drawCircle(jPanel, g2, ciudadOr.getX(), ciudadOr.getY(), ciudadOr.getNombre());
//                ciudadDes.drawCircle(jPanel, g2, ciudadDes.getX(), ciudadDes.getY(), ciudadDes.getNombre());
                
                }
             }
             flightList.printList();
             br1.close();
             br2.close();
            
     } catch (FileNotFoundException ex) {
            Logger.getLogger(ProyectoAeropuerto.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + file + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }

    //Metodo para guardar el archivo
    public void guardarArchivo(File file){
             
        try {
            String line;
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            
            for(int i = 0; i < cityList.size; i++){
                line = cityList.obtainValue(i).getNombre() + "," + cityList.obtainValue(i).getAeropuerto() + "," + cityList.obtainValue(i).getPais();
                bw.write(line);
                bw.newLine();
            }
            bw.write("fciudades");
            bw.newLine();
            for(int j = 0; j < flightList.size; j++){
                line = Integer.toString(flightList.obtainValue(j).getVuelo().getNumVuelo()) + "," + flightList.obtainValue(j).getVuelo().getAerolinea() + "," + flightList.obtainValue(j).getVuelo().getCosto() + "," + flightList.obtainValue(j).getVuelo().getDuracion();
                bw.write(line);
                bw.newLine();
            }
            bw.write("fvuelos");
            bw.newLine();
            for(int k = 0; k < flightList.size; k++){
                line = flightList.obtainValue(k).getPaisOrigen().getNombre() + "," + flightList.obtainValue(k).getPaisDestino().getNombre() + "," + flightList.obtainValue(k).getVuelo().getNumVuelo();
                bw.write(line);
                bw.newLine();
            }
            bw.write("fconecciones");
            bw.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void FloydWarshallTiempo(){

        double matrizTiempo[][] = new double[this.matrixAdy.length][this.matrixAdy.length];
        Ciudad matrizCiudades[][] = new Ciudad[this.matrixAdy.length][this.matrixAdy.length];

       //Se crean la matriz de duraciones y la matriz de caminos(ciudades) con los vuelos   
       for(int i = 0; i < this.matrixAdy.length; i++){
           for(int j = 0; j< this.matrixAdy.length; j++){

               if(i == j){
                   matrizTiempo[i][j] = -1;
                   matrizCiudades[i][j] = null;
               }
               else if(this.matrixAdy[i][j] != null){
                    //Cambio de matriz
//                   matrizTiempo[i][j] = this.matrixAdy[i][j].getDuracion();
                     matrizTiempo[i][j] = this.matrixAdy[i][j].getVuelo().getDuracion();

//                   matrizCiudades[i][j] = this.listaCiudades.get(i);
                   matrizCiudades[i][j] = cityList.obtainValue(i);
               }
               else{
                   matrizTiempo[i][j] = Double.POSITIVE_INFINITY;
                   matrizCiudades[i][j] = null;
               }
           }
       }
       for(int x = 0; x < matrizTiempo.length; x++){
             for(int y = 0; y < matrizTiempo.length; y++){
                 if(matrizTiempo[x][y] == Double.POSITIVE_INFINITY){
                     System.out.print("# ");
                 }
                 else{
                 System.out.print(matrizTiempo[x][y] + " ");
                 }
             }
             System.out.println("");
         }
          System.out.println("Matrices creadas");

       for(int p = 0; p < this.matrixAdy.length; p++){

           for(int i = 0; i < this.matrixAdy.length; i++){
             for(int j = 0; j < this.matrixAdy.length; j++){

                 if(p != i && j != p){
                     double escala = matrizTiempo[i][p] + matrizTiempo[p][j];

                     if(matrizTiempo[i][j] > escala){
                         System.out.println(escala + " por " + matrizTiempo[i][j]);
                         matrizTiempo[i][j] = escala;

//                         matrizCiudades[i][j] = this.listaCiudades.get(p);
                         matrizCiudades[i][j] = cityList.obtainValue(p);

                     }   
                 }

           }
          }
       }
         for(int x = 0; x < matrizTiempo.length; x++){
             for(int y = 0; y < matrizTiempo.length; y++){
                 System.out.print(matrizTiempo[x][y] + " ");
             }
             System.out.println("");
         }


       this.setMatrizCiudades(matrizCiudades);
       this.setMatrizTiempo(matrizTiempo);
    }
    
    public double menorTiempo(Ciudad origen, Ciudad destino){
        int ciudadOr = -1, ciudadDes = -1;
        
        for (int i = 0; i < cityList.size; i++) {
//            if (listaCiudades.get(i).equals(origen)){
//                ciudadOr = i;
//            }
//            else if (listaCiudades.get(i).equals(destino)){
//                ciudadDes = i;
//            }
            if(cityList.obtainValue(i).equals(origen)){
                ciudadOr = i;
            }
            else if(cityList.obtainValue(i).equals(destino)){
                ciudadDes = i;
            }
        }
        
        return matrizTiempo[ciudadOr][ciudadDes];
        
    }
    
    public void FloydWarshallPrecio(){

        double matrizPrecio[][] = new double[this.matrixAdy.length][this.matrixAdy.length];
        Ciudad matrizCiudades[][] = new Ciudad[this.matrixAdy.length][this.matrixAdy.length];

       //Se crean la matriz de duraciones y la matriz de caminos(ciudades) con los vuelos   
       for(int i = 0; i < this.matrixAdy.length; i++){
           for(int j = 0; j< this.matrixAdy.length; j++){

               if(i == j){
                   matrizPrecio[i][j] = -1;
                   matrizCiudades[i][j] = null;
               }
               else if(this.matrixAdy[i][j] != null){
                   //Matriz cambio
//                   matrizPrecio[i][j] = this.matrixAdy[i][j].getCosto();
                    matrizPrecio[i][j] = this.matrixAdy[i][j].getVuelo().getCosto();

//                   matrizCiudades[i][j] = this.listaCiudades.get(i);
                    matrizCiudades[i][j] = cityList.obtainValue(i);
               }
               else{
                   matrizPrecio[i][j] = Double.POSITIVE_INFINITY;
                   matrizCiudades[i][j] = null;
               }
           }
       }
       for(int x = 0; x < matrizPrecio.length; x++){
             for(int y = 0; y < matrizPrecio.length; y++){
                 if(matrizPrecio[x][y] == Double.POSITIVE_INFINITY){
                     System.out.print("# ");
                 }
                 else{
                 System.out.print(matrizPrecio[x][y] + " ");
                 }
             }
             System.out.println("");
         }
          System.out.println("Matrices creadas");

       for(int p = 0; p < this.matrixAdy.length; p++){

           for(int i = 0; i < this.matrixAdy.length; i++){
             for(int j = 0; j < this.matrixAdy.length; j++){

                 if(p != i && j != p){
                     double escala = matrizPrecio[i][p] + matrizPrecio[p][j];

                     if(matrizPrecio[i][j] > escala){
                         System.out.println(escala + " por " + matrizPrecio[i][j]);
                         matrizPrecio[i][j] = escala;

//                         matrizCiudades[i][j] = this.listaCiudades.get(p);
                         matrizCiudades[i][j] = cityList.obtainValue(p);

                     }   
                 }

           }
          }
       }
       
       this.setMatrizCiudades(matrizCiudades);
       this.setMatrizPrecio(matrizPrecio);
       
    }
    
    public double menorPrecio(Ciudad origen, Ciudad destino){
        int ciudadOr = -1, ciudadDes = -1;
        
        for (int i = 0; i < cityList.size; i++) {
//            if (listaCiudades.get(i).equals(origen)){
//                ciudadOr = i;
//            }
//            else if (listaCiudades.get(i).equals(destino)){
//                ciudadDes = i;
//            }
            if(cityList.obtainValue(i).equals(origen)){
                ciudadOr = i;
            }
            else if (cityList.obtainValue(i).equals(destino)){
                ciudadDes = i;
            }
        }
        
        return matrizPrecio[ciudadOr][ciudadDes];
        
    }
    
    //Metodos para conseguir todos los caminos de una ciudad origen a una ciudad destino
    public void printAllPaths(Ciudad origen, Ciudad destino){
        ListaCiudades pathList = new ListaCiudades();
        boolean[] isVisited = new boolean[this.numCiudades];
        
        pathList.insertFinal(origen);
        
        printAllPathUtil(origen, destino, pathList, isVisited);
    }
    
    public void printAllPathUtil(Ciudad origen, Ciudad destino, ListaCiudades localPathList, boolean[] isVisited){
        String result = "";
        if(origen.equals(destino)){
            result = localPathList.toStringList();
            
            Caminos.caminosShared.showText(result);
            isVisited[localPathList.getIndex(origen)] = false;
            return;
        }
              
            int origenPos = -1;

            for (int i = 0; i < cityList.size; i++) {
                if(cityList.obtainValue(i) == origen){
                    isVisited[i] = true;
                    origenPos = i;
                    break;
                }
            }

            for (int i = 0; i < matrixAdy.length; i++) {
                if(matrixAdy[origenPos][i] != null && !isVisited[i]){
                    localPathList.insertFinal(cityList.obtainValue(i));
                    printAllPathUtil(cityList.obtainValue(i), destino, localPathList, isVisited);
                    localPathList.removeElement(cityList.obtainValue(i));
                }
                
            }
            
            isVisited[localPathList.getIndex(origen)] = false;
        
    }
     public double DijkstraTiempo(Ciudad origen, Ciudad destino){
   
        
        //Declarando aux
        double[] distancia = new double[matrixAdy.length];
        boolean[] visitado = new boolean[matrixAdy.length];
        int[] camino = new int[matrixAdy.length];
        double min;
        
                for (int i = 0; i < matrixAdy.length; i++) {
            visitado[i] = false;
            camino[i] = numCiudad(origen);
            
          
        }
        
        int siguiente = numCiudad(origen);
        
         for(int j = 0; j < matrixAdy.length; j++){
             if(matrixAdy[numCiudad(origen)][j] != null){
             distancia[j] = matrixAdy[numCiudad(origen)][j].getVuelo().getDuracion();
             }
             else{
             distancia[j] = Double.POSITIVE_INFINITY;
             }
            }

        distancia[siguiente] = 0;
        visitado[siguiente] = true;
        
 
        for (int i = 0; i < matrixAdy.length; i++) {
            
            min = Integer.MAX_VALUE;
            
           
            for (int j = 0; j < matrixAdy.length; j++) {
                if (min > distancia[j] && !visitado[j]) {
                    min = distancia[j];
                    siguiente = j;
                }
            }
            visitado[siguiente] = true;

            for (int z = 0; z < matrixAdy.length; z++) {

                if (!visitado[z]) {
                    if(matrixAdy[siguiente][z] != null){
                        if ((min + matrixAdy[siguiente][z].getVuelo().getDuracion()) < distancia[z]) {
                            distancia[z] = min + matrixAdy[siguiente][z].getVuelo().getDuracion();
                            camino[z] = siguiente;

                        }
                    }
                }
            }
        }
        this.setCamino(camino);
        return distancia[numCiudad(destino)];
      
    
}
     public double DijkstraPrecio(Ciudad origen, Ciudad destino){
   
        
        //Declarando aux
        double[] precio = new double[matrixAdy.length];
        boolean[] visitado = new boolean[matrixAdy.length];
        int[] camino = new int[matrixAdy.length];
        double min;
        
                for (int i = 0; i < matrixAdy.length; i++) {
            visitado[i] = false;
            camino[i] = numCiudad(origen);
            
          
        }
        
        int siguiente = numCiudad(origen);
        
         for(int j = 0; j < matrixAdy.length; j++){
             if(matrixAdy[numCiudad(origen)][j] != null){
             precio[j] = matrixAdy[numCiudad(origen)][j].getVuelo().getCosto();
             }
             else{
             precio[j] = Double.POSITIVE_INFINITY;
             }
            }

        precio[siguiente] = 0;
        visitado[siguiente] = true;
        
 
        for (int i = 0; i < matrixAdy.length; i++) {
            
            min = Integer.MAX_VALUE;
            
           
            for (int j = 0; j < matrixAdy.length; j++) {
                if (min > precio[j] && !visitado[j]) {
                    min = precio[j];
                    siguiente = j;
                }
            }
            visitado[siguiente] = true;

            for (int z = 0; z < matrixAdy.length; z++) {

                if (!visitado[z]) {
                    if(matrixAdy[siguiente][z] != null){
                        if ((min + matrixAdy[siguiente][z].getVuelo().getCosto()) < precio[z]) {
                            precio[z] = min + matrixAdy[siguiente][z].getVuelo().getCosto();
                            camino[z] = siguiente;

                        }
                    }
                }
            }
        }
        this.setCamino(camino);
        return precio[numCiudad(destino)];
      
    
}
          public int numCiudad(Ciudad ciudad) {
       int pos = 0;
       
        for (int i = 0; i < cityList.size; i++) {
           
            if (cityList.obtainValue(i).equals(ciudad)) {
                pos = i;
                return pos;
            }
        }
return pos;
    }
          
          public String buscarCaminoFloyd(Ciudad origen, Ciudad destino){
          
          String camino = "";
          Ciudad aux = origen;
          while (aux != matrizCiudades[numCiudad(aux)][numCiudad(destino)]){
              camino = camino + aux.getNombre() + ">";
              aux = matrizCiudades[numCiudad(aux)][numCiudad(destino)];
          
          }
          camino = camino + aux.getNombre() + ">";
          camino = camino + destino.getNombre();
          
          
          return camino;
          }
          
          public String buscarCaminoDijkstra(Ciudad origen,Ciudad destino){
          
              Ciudad aux = origen;
              String camino = origen.getNombre() + ">";
              
              camino = camino + aux.getNombre();
              
             while(this.cityList.obtainValue(this.camino[numCiudad(aux)]) != origen){
                  camino = camino + aux.getNombre() + ">";
                  aux = cityList.obtainValue(this.camino[numCiudad(destino)]);
              
              }
             
              camino = camino + destino.getNombre();
          

          
        
          
          return camino;
          }
}

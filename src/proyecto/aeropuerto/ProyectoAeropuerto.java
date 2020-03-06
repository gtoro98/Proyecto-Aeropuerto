/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.aeropuerto;

/**
 *
 * @author SamuelLMiller & Gtoro98
 */
public class ProyectoAeropuerto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
//        MenuPrincipal ppl = new MenuPrincipal();
//        ppl.setVisible(true);
        MenuPrincipal.shared.initialize();
        Caminos.caminosShared.initialize();
        MenuPrincipal.shared.setVisible(true);
        
    }
    
    
    
}

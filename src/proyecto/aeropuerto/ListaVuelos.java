/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.aeropuerto;

import javax.swing.JOptionPane;

/**
 *
 * @author SamuelLMiller & Gtoro98
 */
public class ListaVuelos {
    private class Nodo{
        public Nodo next = null;
        public Conexion info;
        

        public Nodo(Conexion info) {
            this.info = info;
        }
    
    }
    
    public Nodo first = null;
    public int size;
    
    //To insert at the end
    public void insertFinal(Conexion info){
        Nodo newNodo =  new Nodo(info);

        if(first == null){
            first = newNodo;
        }
        else{
            Nodo aux = first;
            while(aux.next != null){
                aux = aux.next;
            }
            aux.next = newNodo;
        }

        size++;
    }

    //To obtain the value of
    public Conexion obtainValue(int n){

        if(first == null){
            return null;
        }
        else{
            if (n == 0){
                return first.info;
            }
            else if (n < size){
                int count = 0;
                Nodo aux = first;
                while(count < n){
                    aux = aux.next;
                    count++;
                }
                return aux.info;
            }
            else{
                return null;
            }
        }
    }
    
    //Print list
    public void printList(){

        for (int i = 0; i < this.size; i++) {
            System.out.println(this.obtainValue(i).getPaisOrigen().getNombre() + "-" + this.obtainValue(i).getPaisDestino().getNombre());
        }
    }
    
    
    public int getIndex(Conexion elem){
        if(first == null){
            return -1;
        }
        else{
            int count = 0;
            Nodo aux = first;
            while (aux.info != elem && aux.next != null){
                aux = aux.next;
                count++;
            }
            if (aux.info == elem){
                return count;
            }
            else{
                return -1;
            }
        }
    }
    
    public int getSize(){
        return size;
    }
    
    //To remove the first element
    public void removeFirst(){
        if (first != null){
            Nodo aux = first;
            first = first.next;
            aux.next = null;
            size--;
        }
    }
    
    //To remove the last one
    public void removeLast(){
        if (first != null){
            if (first.next == null){
                first = null;
            }
            else{
                Nodo aux = first;
                while(aux.next.next != null){
                    aux = aux.next;
                }
                aux.next = null;
                size--;
            }
        }
    }
    
    public void removeElement(Conexion elem){
        if(first != null){
            Nodo aux1 = first;
            Object temp = aux1.info;
            int index = 0;
            while(aux1.next != null && temp != elem){
                aux1 = aux1.next;
                temp = aux1.info;
                index++;
            }
            
            if(temp == elem){
//                Nodo aux2 = aux1.next;
//                aux1.next = aux2.next;
//                aux2.next = null;
//                size--;
                removeElementByIndex(index);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se encontro lo que se desea eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
    
    //To remove an specific element
    public void removeElementByIndex(int n){
        if(first != null){
            if (n == 0){
                this.removeFirst();

            }
            else if (n < size){
                Nodo aux1 = first;
                int count = 0;
                while (aux1.next != null && count < (n-1)){
                    aux1 = aux1.next;
                    count++;
                }
                Nodo aux2 = aux1.next;
                aux1.next = aux2.next;
                aux2.next = null;
                size--;

            }
        }
    }
    
    
}

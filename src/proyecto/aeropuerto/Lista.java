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
public class Lista {
    
    private class Nodo{
        public Nodo next = null;
        public Object info;
        

        public Nodo(Object info) {
            this.info = info;
        }
    
    }
    
    public Nodo first = null;
    public int size;
    
    //To insert at the end
    public void insertFinal(Object info){
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
    public Object obtainValue(int n){

        if(first == null){
            return -1;
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
                return -1;
            }
        }
    }
    
    
    public int getIndex(Object elem){
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
    
    //To remove an specific element
//    public void removeElement(int n){
//        if(first != null){
//            if (n == 0){
//                this.removeFirst();
//
//            }
//            else if (n < size){
//                Nodo aux1 = first;
//                int count = 0;
//                while (aux1.next != null && count < (n-1)){
//                    aux1 = aux1.next;
//                    count++;
//                }
//                Nodo aux2 = aux1.next;
//                aux1.next = aux2.next;
//                aux2.next = null;
//                size--;
//
//            }
//        }
//    }
    
    public void removeElement(Object elem){
        if(first != null){
            Nodo aux1 = first;
            Object temp = aux1.info;
            while(aux1.next != null && temp != elem){
                aux1 = aux1.next;
                temp = aux1.info;
            }
            
            if(temp == elem){
                Nodo aux2 = aux1.next;
                aux1.next = aux2.next;
                aux2.next = null;
                size--;
            }
            else{
                JOptionPane.showMessageDialog(null, "No se encontro lo que se desea eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
    

}

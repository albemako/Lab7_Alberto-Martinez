/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7_alberto.martinez;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alberto
 */
public class Cajero extends Thread{
    public String nombre;
    public int ide;
    public ArrayList<Orden>ordenes=new ArrayList();
    public TablaDeOrdenes tdo;

    public Cajero(TablaDeOrdenes tdo) {
        this.tdo = tdo;
    }
    
    
    
    public Cajero() {
    }

    public Cajero(String nombre, int ide, TablaDeOrdenes tdo) {
        this.nombre = nombre;
        this.ide = ide;
        this.tdo = tdo;
    }
    
     

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public ArrayList<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(ArrayList<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    public void run(){
        int sleep;
        int cont=0;
        TablaDeOrdenes to=new TablaDeOrdenes();
            to.l_cajero.setText(nombre);
            to.pack();
            to.setVisible(true);
            
            while (true) {
                
                
                DefaultTableModel modelo=(DefaultTableModel)to.jt_procesando.getModel();
                
                while (ordenes.size()>cont) { 
                    for (int i = cont; i < ordenes.size(); i++) {
                    to.l_cliente.setText(ordenes.get(i).getCliente().getNombre());
                    for (int j = 0; j < ordenes.get(i).productos.size(); j++) {
                        sleep=1000*ordenes.get(i).productos.get(j).getTiempo();
                        to.tf_procesando.setText(ordenes.get(i).productos.get(j).getNombre());
                        try {
                        Thread.sleep(sleep);
                        } catch (InterruptedException ex) {
                        }
                        Object[]newrow={ordenes.get(i).productos.get(j).getNombre(),ordenes.get(i).productos.get(j).getPrecio(), ordenes.get(i).productos.get(j).getTiempo()};
                        modelo.addRow(newrow);
                        to.jt_procesando.setModel(modelo);
                        
                    }
                    cont++;
                }
                    
                }
                
                
                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }
            
        }
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Clases;

import java.util.ArrayList;

public class Cliente {
    private String nombre;
    private double contacto;
    private ArrayList<Producto> productosComprados = new ArrayList<>();
    private ArrayList<Mascota> mascotasAdoptadas = new ArrayList<>();

// arraylist en producto y mascota ya que no sabemos cual sea la cantidad fija

    public Cliente(String nombre, double contacto) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.productosComprados = new ArrayList<>();
        this.mascotasAdoptadas =  new ArrayList<>();
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getContacto() {
        return contacto;
    }

    public void setContacto(int contacto) {
        this.contacto = contacto;
    }
    public void adoptarMascota(Mascota mascota) {
        mascotasAdoptadas.add(mascota);
    }
    public void mostrarMascotasAdoptadas() {
        if (mascotasAdoptadas.isEmpty()) {
            System.out.println(nombre + " aún no ha adoptado ninguna mascota.");
        } else {
            System.out.println("Mascotas adoptadas por " + nombre + ":");   
            for (Mascota mascota : mascotasAdoptadas) { // para cada mascota en la array de mascotas
                System.out.println(mascota.mostrarInformacion()); // imprimir la info de c/u de las mascotas adoptadas
            }
        }
    }public void productosComprados(Producto producto) {
        productosComprados.add(producto);
    }
    public void mostrarProductosComprados() {
        if (productosComprados.isEmpty()) { // si la lista no tiene productos
            System.out.println(nombre + " aún no ha comprado ningun producto.");
        } else {
            System.out.println("Producto comprado por " + nombre + ":");
            for (Producto producto : productosComprados) { // por cada producto mostrar su info
                System.out.println(producto.mostrarInformacion());
            }
        }
    }




}
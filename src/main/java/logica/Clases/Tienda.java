/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Clases;
import logica.Excepciones.MascotaNoDisponible;
import logica.Excepciones.ProductoNoDisponible;

import java.util.ArrayList;

public class Tienda {
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Producto> listaProductos;
    private ArrayList<Mascota> listaMascotas;

    public Tienda(ArrayList<Cliente> listaClientes, ArrayList<Producto> listaProductos, ArrayList<Mascota> listaMascotas) {
        this.listaClientes = listaClientes;
        this.listaProductos = listaProductos;
        this.listaMascotas = listaMascotas;
    }

    public void agregarMascota(Mascota mascota) {
        listaMascotas.add(mascota);
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public void listaMascotasDisponibles() {
        if (listaMascotas.isEmpty()) {
            System.err.println("No hay mascotas disponibles.");
        } else {
            System.out.println("Mascotas disponibles:");
            for (Mascota mascota : listaMascotas) {
                System.out.println(mascota.mostrarInformacion());
                //para cada mascota en la lista de mascotas ( las que no han sido adoptadas)
                // imprime su información
            }
        }
    }
    public void listaProductosDisponibles(){
        if (listaProductos.isEmpty()){
            System.err.println("No hay productos disponibles");
        }else {
            System.out.println("Productos disponibles");
            for (Producto producto : listaProductos){
                System.out.println(producto.mostrarInformacion());
            }
        }
    }

    public void registrarAdopcion(String nombreMascota, String nombreCliente, double contactoCliente) throws MascotaNoDisponible {
        Mascota mascotaAdoptada = buscarMascota(nombreMascota);
        if (mascotaAdoptada == null || !mascotaAdoptada.isDisponibleAdoptar()) {
            throw new MascotaNoDisponible("La mascota no está disponible para adopción.");
        }

        // Buscar o registrar cliente
        Cliente cliente = buscarORegistrarCliente(nombreCliente, contactoCliente);

        // Registrar adopción
        cliente.adoptarMascota(mascotaAdoptada);
        mascotaAdoptada.setDisponibleAdoptar(false);
        listaMascotas.remove(mascotaAdoptada);
        System.out.println("¡Adopción registrada con éxito!");
    }



    // LO MISMO QUE ARRIBA PERO CON PRODUCTOS
    public void registrarCompra(String nombreProducto, String nombreCliente, double contactoCliente) throws ProductoNoDisponible {
        Producto productoComprado = buscarProducto(nombreProducto);
        if (productoComprado == null) {
            throw new ProductoNoDisponible("El producto no está disponible.");
        }

        Cliente cliente = buscarORegistrarCliente(nombreCliente, contactoCliente);
        cliente.productosComprados(productoComprado);
        listaProductos.remove(productoComprado);
        System.out.println("¡Compra registrada con éxito!");
    }

    // Métodos auxiliares
    public Mascota buscarMascota(String nombreMascota) {
        for (Mascota mascota : listaMascotas) {
            if (mascota.getNombre().equalsIgnoreCase(nombreMascota)) {
                return mascota;
            }
        }
        return null;
    }

    public Producto buscarProducto(String nombreProducto) {
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                return producto;
            }
        }
        return null;
    }

    public Cliente buscarORegistrarCliente(String nombre, double contacto) {
        // Buscar cliente existente
        for (Cliente cliente : listaClientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombre) && cliente.getContacto() == contacto) {
                return cliente;
            }
        }
        // Si no se encuentra, registrar nuevo cliente
        Cliente nuevoCliente = new Cliente(nombre, (int) contacto);
        listaClientes.add(nuevoCliente);
        return nuevoCliente;
    }

}

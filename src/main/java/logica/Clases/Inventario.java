/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Clases;

import java.util.ArrayList;
public class Inventario {

    public static ArrayList<Mascota> inicializarMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        mascotas.add(new Mascota("Dastan", "Gato", 2, "Energetico", true));
        mascotas.add(new Mascota("Oreo", "Perro", 4, "Saludable", true));
        mascotas.add(new Mascota("Charlie", "Ave", 1, "Frágil", true));
        mascotas.add(new Mascota("Bella", "Conejo", 3, "Saludable", true));
        mascotas.add(new Mascota("Furita" , "Gato" , 5 , "Saludable" , true ));
        return mascotas;
    }

    public static ArrayList<Producto> inicializarProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Alimento para perros", 15.99, 10, "Alimento"));
        productos.add(new Producto("Rascador para gatos", 20.50, 5, "Accesorio"));
        productos.add(new Producto("Jaula para aves", 45.00, 2, "Accesorio"));
        productos.add(new Producto("Juguete para conejos", 12.75, 8, "Juguete"));
        return productos;
    }
}

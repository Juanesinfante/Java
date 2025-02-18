/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.Clases;
public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    private String estadoSalud;
    private boolean disponibleAdoptar;

    public Mascota(String nombre, String especie, int edad, String estadoSalud, boolean disponibleAdoptar) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.estadoSalud = estadoSalud;
        this.disponibleAdoptar = disponibleAdoptar;
    }  // Caracteristicas de la mascota

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public boolean isDisponibleAdoptar() {
        return disponibleAdoptar;
    }

    public void setDisponibleAdoptar(boolean disponibleAdoptar) {
        this.disponibleAdoptar = disponibleAdoptar;
    }

    public String mostrarInfo(){
        return "Nombre "  + getNombre() +"  Especie: " + getEspecie() + " Edad: " + getEdad();
    }

    public String mostrarInformacion() {
        return "Nombre: " + nombre +
                ", Especie: " + especie +
                ", Edad: " + edad +
                ", Estado de Salud: " + estadoSalud +
                ", Disponible: " + (disponibleAdoptar ? "Sí" : "No");
    }



}


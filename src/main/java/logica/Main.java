package logica;



import logica.Clases.Cliente;
import logica.Clases.Mascota;
import logica.Clases.Producto;
import logica.Clases.Tienda;
import logica.Clases.Inventario;
import logica.Excepciones.MascotaNoDisponible;
import logica.Excepciones.ProductoNoDisponible;
import igu.Ventana;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MascotaNoDisponible {
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);

        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Producto> productos = Inventario.inicializarProductos();
        ArrayList<Mascota> mascotas = Inventario.inicializarMascotas();

        Tienda tienda = new Tienda(clientes, productos, mascotas);

        System.out.println("Bienvenido a la tienda de Adopción Karito");

        boolean salir = false;
        Scanner scanner = new Scanner(System.in);

        try {
            while (!salir) {
                System.out.println("\nSeleccione una opción:");
                System.out.println("1. Ver lista de Mascotas en adopción");
                System.out.println("2. Ver lista de productos disponibles");
                System.out.println("3. Ingresar como nuevo cliente");
                System.out.println("4. Adoptar Mascota");
                System.out.println("5. Comprar producto");
                System.out.println("6. Verificar mascotas adoptadas por el cliente");
                System.out.println("7. Verificar productos comprados por el cliente");
                System.out.println("8. Salir");
                System.out.print("Opción: ");

                if (!scanner.hasNextInt()) {
                    String entradaTexto = scanner.nextLine().trim();
                    if (entradaTexto.equalsIgnoreCase("salir")) {
                        salir = true;
                        break;
                    } else {
                        System.out.println("⚠️ Opción no válida. Ingrese un número del 1 al 8.");
                        continue;
                    }
                }

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        tienda.listaMascotasDisponibles();
                        salir = preguntarSalir(scanner);
                        break;

                    case 2:
                        tienda.listaProductosDisponibles();
                        salir = preguntarSalir(scanner);
                        break;

                    case 3:
                        System.out.print("Ingrese el nombre del nuevo cliente: ");
                        String nombCliente = scanner.nextLine();
                        System.out.print("Ingrese contacto del cliente: ");
                        if (!scanner.hasNextDouble()) {
                            System.out.println("⚠️ Error: Contacto inválido.");
                            scanner.next();
                            break;
                        }
                        double contactoCliente = scanner.nextDouble();
                        scanner.nextLine();

                        clientes.add(new Cliente(nombCliente, contactoCliente));
                        System.out.println("Bienvenido " + nombCliente);
                        salir = preguntarSalir(scanner);
                        break;

                    case 4:
                        System.out.println("Ingrese el nombre de la mascota que desea adoptar:");
                        String nombreMascotaAdoptar = scanner.nextLine();

                        System.out.print("Ingrese el nombre del cliente: ");
                        String nombreCliente = scanner.nextLine();
                        System.out.print("Ingrese el contacto del cliente: ");
                        if (!scanner.hasNextDouble()) {
                            System.out.println("⚠️ Error: Contacto inválido.");
                            scanner.next();
                            break;
                        }
                        double contactoClient = scanner.nextDouble();
                        scanner.nextLine();

                        tienda.registrarAdopcion(nombreMascotaAdoptar, nombreCliente, contactoClient);
                        Cliente clienteAdoptante = tienda.buscarORegistrarCliente(nombreCliente, contactoClient);
                        clienteAdoptante.mostrarMascotasAdoptadas();
                        salir = preguntarSalir(scanner);
                        break;

                    case 5:
                        System.out.println("Ingrese el nombre del producto que desea comprar:");
                        String nombreProductoAComprar = scanner.nextLine();

                        System.out.print("Ingrese el nombre del cliente: ");
                        nombreCliente = scanner.nextLine();
                        System.out.print("Ingrese el contacto del cliente: ");
                        if (!scanner.hasNextDouble()) {
                            System.out.println("⚠️ Error: Contacto inválido.");
                            scanner.next();
                            break;
                        }
                        contactoClient = scanner.nextDouble();
                        scanner.nextLine();

                        tienda.registrarCompra(nombreProductoAComprar, nombreCliente, contactoClient);
                        salir = preguntarSalir(scanner);
                        break;

                    case 6:
                        System.out.print("Ingrese el nombre del cliente: ");
                        nombreCliente = scanner.nextLine();
                        System.out.print("Ingrese el contacto del cliente: ");
                        if (!scanner.hasNextDouble()) {
                            System.out.println("⚠️ Error: Contacto inválido.");
                            scanner.next();
                            break;
                        }
                        contactoClient = scanner.nextDouble();
                        scanner.nextLine();

                        Cliente clienteMascotas = tienda.buscarORegistrarCliente(nombreCliente, contactoClient);
                        clienteMascotas.mostrarMascotasAdoptadas();
                        salir = preguntarSalir(scanner);
                        break;

                    case 7:
                        System.out.print("Ingrese el nombre del cliente: ");
                        nombreCliente = scanner.nextLine();
                        System.out.print("Ingrese el contacto del cliente: ");
                        if (!scanner.hasNextDouble()) {
                            System.out.println("⚠️ Error: Contacto inválido.");
                            scanner.next();
                            break;
                        }
                        contactoCliente = scanner.nextDouble();
                        scanner.nextLine();

                        Cliente clienteProductos = tienda.buscarORegistrarCliente(nombreCliente, contactoCliente);
                        clienteProductos.mostrarProductosComprados();
                        salir = preguntarSalir(scanner);
                        break;

                    case 8:
                        salir = true;
                        break;

                    default:
                        System.out.println("⚠️ Opción no válida. Intente de nuevo.");
                }
            }
        } catch (InputMismatchException e) {
            System.err.println("⚠️ Ha ingresado un valor incorrecto.");
        } catch (ProductoNoDisponible e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }

        System.out.println("\nGracias por usar el sistema. ¡Hasta pronto!");
    }

    // Método para verificar si el usuario quiere salir o retroceder
    private static boolean preguntarSalir(Scanner scanner) {
        while (true) {
            System.out.println("\nEscriba 'salir' para cerrar el programa o 'retroceder' para volver al menú:");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("salir")) {
                return true;
            } else if (input.equals("retroceder")) {
                return false;
            } else {
                System.out.println("⚠️ Entrada no válida. Intente de nuevo.");
            }
        }
    }
}

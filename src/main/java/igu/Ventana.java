package Clases;

import Excepciones.MascotaNoDisponible;
import Excepciones.ProductoNoDisponible;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ventana extends JFrame {
    private static final int VER_MASCOTAS_ADOPCION = 0;
    private static final int VER_PRODUCTOS_DISPONIBLES = 1;
    private static final int INGRESAR_NUEVO_CLIENTE = 2;
    private static final int ADOPTAR_MASCOTA = 3;
    private static final int COMPRAR_PRODUCTO = 4;
    private static final int VERIFICAR_MASCOTAS_ADOPTADAS = 5;
    private static final int VERIFICAR_PRODUCTOS_COMPRADOS = 6;
    private static final int SALIR = 7;

    private JList<String> opcionesLista;
    private JTextArea textAreaInfo;
    private JButton btnEjecutar;
    private Tienda tienda;

    public Ventana() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Producto> productos = Inventario.inicializarProductos();
        ArrayList<Mascota> mascotas = Inventario.inicializarMascotas();
        tienda = new Tienda(clientes, productos, mascotas);

        initComponents();
        agregarEventos();
    }

    private void initComponents() {
        setTitle("Tienda de Mascotas Karito");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Tienda de Mascotas Karito", SwingConstants.CENTER);
        titulo.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(46, 204, 113));
        titulo.setPreferredSize(new Dimension(700, 70));
        add(titulo, BorderLayout.NORTH);

        JPanel panelOpciones = new JPanel(new BorderLayout());
        panelOpciones.setBorder(BorderFactory.createTitledBorder("Opciones"));
        panelOpciones.setPreferredSize(new Dimension(280, 400));

        String[] opciones = {
                "Ver Mascotas en Adopción",
                "Ver Productos Disponibles",
                "Registrar Nuevo Cliente",
                "Adoptar Mascota",
                "Comprar Producto",
                "Ver Mascotas Adoptadas",
                "Ver Productos Comprados",
                "Salir"
        };

        opcionesLista = new JList<>(opciones);
        opcionesLista.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        opcionesLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelOpciones.add(new JScrollPane(opcionesLista), BorderLayout.CENTER);

        btnEjecutar = new JButton("Ejecutar");
        btnEjecutar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEjecutar.setBackground(new Color(46, 204, 113));
        btnEjecutar.setForeground(Color.WHITE);
        btnEjecutar.setFocusPainted(false);
        panelOpciones.add(btnEjecutar, BorderLayout.SOUTH);

        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBorder(BorderFactory.createTitledBorder("Información"));
        textAreaInfo = new JTextArea();
        textAreaInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textAreaInfo.setEditable(false);
        textAreaInfo.setLineWrap(true);
        textAreaInfo.setWrapStyleWord(true);
        panelInfo.add(new JScrollPane(textAreaInfo), BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panelOpciones, BorderLayout.WEST);
        mainPanel.add(panelInfo, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void agregarEventos() {
        btnEjecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = opcionesLista.getSelectedIndex();
                try {
                    ejecutarOpcionSeleccionada(opcion);
                } catch (ProductoNoDisponible ex) {
                    throw new RuntimeException(ex);
                } catch (MascotaNoDisponible ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void ejecutarOpcionSeleccionada(int opcion) throws ProductoNoDisponible, MascotaNoDisponible {
        switch (opcion) {
            case VER_MASCOTAS_ADOPCION:
                textAreaInfo.setText(tienda.listaMascotasDisponibles());
                break;
            case VER_PRODUCTOS_DISPONIBLES:
                textAreaInfo.setText(tienda.listaProductosDisponibles());
                break;
            case INGRESAR_NUEVO_CLIENTE:
                String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
                String contactoClienteStr = JOptionPane.showInputDialog("Ingrese el contacto del cliente:");
                int contactoCliente = Integer.parseInt(contactoClienteStr);
                tienda.buscarORegistrarCliente(nombreCliente, contactoCliente);
                textAreaInfo.setText("Cliente registrado exitosamente.");
                break;
            case ADOPTAR_MASCOTA:
                String nombreMascota = JOptionPane.showInputDialog("Ingrese el nombre de la mascota a adoptar:");
                String clienteAdopcion = JOptionPane.showInputDialog("Ingrese su nombre:");
                String contactoAdopcionStr = JOptionPane.showInputDialog("Ingrese su contacto:");
                int contactoAdopcion = Integer.parseInt(contactoAdopcionStr);
                tienda.registrarAdopcion(nombreMascota, clienteAdopcion, contactoAdopcion);
                textAreaInfo.setText("¡Mascota adoptada con éxito!");
                break;
            case COMPRAR_PRODUCTO:
                String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto a comprar:");
                String clienteCompra = JOptionPane.showInputDialog("Ingrese su nombre:");
                String contactoCompraStr = JOptionPane.showInputDialog("Ingrese su contacto:");
                int contactoCompra = Integer.parseInt(contactoCompraStr);
                tienda.registrarCompra(nombreProducto, clienteCompra, contactoCompra);
                textAreaInfo.setText("¡Producto comprado con éxito!");
                break;
            case VERIFICAR_MASCOTAS_ADOPTADAS:
                String nombreClienteAdoptante = JOptionPane.showInputDialog("Ingrese el nombre del cliente que adoptó la mascota:");
                double contactoClienteAdoptante = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el contacto del cliente:"));

                Cliente clienteEncontrado = null;

                // Buscar cliente en la lista
                for (Cliente cliente : tienda.getListaClientes()) {
                    if (cliente.getNombre().equalsIgnoreCase(nombreClienteAdoptante) && cliente.getContacto() == contactoClienteAdoptante) {
                        clienteEncontrado = cliente;
                        break;
                    }
                }

                if (clienteEncontrado != null) {
                    StringBuilder infoMascotas = new StringBuilder("Mascotas Adoptadas:\n");
                    infoMascotas.append(clienteEncontrado.obtenerMascotasAdoptadas(nombreClienteAdoptante, contactoClienteAdoptante)).append("\n");
                    textAreaInfo.setText(infoMascotas.toString());
                } else {
                    textAreaInfo.setText("Cliente no encontrado o sin mascotas adoptadas.");
                }
                break;

            case VERIFICAR_PRODUCTOS_COMPRADOS:
                StringBuilder infoProductos = new StringBuilder("Productos Comprados:\n");
                for (Cliente cliente : tienda.getListaClientes()) {
                    infoProductos.append(cliente.obtenerProductosComprados()).append("\n");
                }
                textAreaInfo.setText(infoProductos.toString());
                break;
            case SALIR:
                JOptionPane.showMessageDialog(this, "Saliendo del programa.");
                System.exit(0);
                break;
            default:
                textAreaInfo.setText("Seleccione una opción válida.");
                break;
        }
    }
}

package POOwerCoders.vista;

import java.util.Scanner;

/**
 * VistaConsola actúa como el punto de entrada principal de la aplicación.
 * Muestra un menú general para gestionar clientes, artículos y pedidos.
 */
public class VistaConsola {

    // Instancias de las vistas correspondientes a cada módulo
    private final VistaClientes vistaClientes = new VistaClientes();
    private final VistaArticulos vistaArticulos = new VistaArticulos();
    private final VistaPedidos vistaPedidos = new VistaPedidos();

    // Scanner para capturar la entrada del usuario por teclado
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que inicia la aplicación y muestra el menú general.
     */
    public void iniciarAplicacion() {
        int opcion;

        // Bucle que mantiene activo el menú hasta que el usuario elige salir
        do {
            // Menú principal en consola
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Artículos");
            System.out.println("3. Gestión de Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            // Lectura de opción del usuario
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            // Dependiendo de la opción, se delega el control a la vista correspondiente
            switch (opcion) {
                case 1 -> vistaClientes.mostrarMenu();  // Ir a gestión de clientes
                case 2 -> vistaArticulos.mostrarMenu(); // Ir a gestión de artículos
                case 3 -> vistaPedidos.mostrarMenu();   // Ir a gestión de pedidos
                case 0 -> System.out.println("👋 Saliendo de la aplicación..."); // Salida
                default -> System.out.println("❌ Opción no válida, intente de nuevo."); // Error de opción
            }

        } while (opcion != 0); // El bucle continúa hasta que se elige "Salir"
    }
}

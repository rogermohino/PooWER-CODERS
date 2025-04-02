package POOwerCoders.vista;

import java.util.Scanner;

public class VistaConsola {
    private final VistaClientes vistaClientes = new VistaClientes();
    private final VistaArticulos vistaArticulos = new VistaArticulos();
    private final VistaPedidos vistaPedidos = new VistaPedidos();
    private final Scanner scanner = new Scanner(System.in);

    public void iniciarAplicacion() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Artículos");
            System.out.println("3. Gestión de Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> vistaClientes.mostrarMenu();
                case 2 -> vistaArticulos.mostrarMenu();
                case 3 -> vistaPedidos.mostrarMenu();
                case 0 -> System.out.println("👋 Saliendo de la aplicación...");
                default -> System.out.println("❌ Opción no válida, intente de nuevo.");
            }
        } while (opcion != 0);
    }
}

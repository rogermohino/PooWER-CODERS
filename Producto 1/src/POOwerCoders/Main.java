package POOwerCoders;

import POOwerCoders.vista.VistaArticulos;
import POOwerCoders.vista.VistaClientes;
import POOwerCoders.vista.VistaPedidos;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        VistaClientes vistaClientes = new VistaClientes();
        VistaArticulos vistaArticulos = new VistaArticulos();
        VistaPedidos vistaPedidos = new VistaPedidos();

        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n====== MENÚ PRINCIPAL ======");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Artículos");
            System.out.println("3. Gestión de Pedidos");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> vistaClientes.mostrarMenu();
                case 2 -> vistaArticulos.mostrarMenu();
                case 3 -> vistaPedidos.mostrarMenu();
            }
        } while (opcion != 0);

        System.out.println("👋 ¡Hasta luego!");

    }
}

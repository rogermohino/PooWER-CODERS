package PO0werCoders.vista;

import PO0werCoders.controlador.ControlPedido;
import POOwerCoders.modelo.Pedido;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class VistaPedidos {
    private ControlPedido controlPedido = new ControlPedido();
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Pedidos ---");
            System.out.println("1. Añadir pedido");
            System.out.println("2. Listar pedidos");
            System.out.println("0. Volver al menú principal");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarPedido();
                case 2 -> listarPedidos();
            }
        } while (opcion != 0);
    }

    private void agregarPedido() {
        System.out.print("Número de pedido: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.print("NIF cliente: ");
        String nif = scanner.nextLine();
        System.out.print("Código artículo: ");
        String codigo = scanner.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        Pedido p = new Pedido(numero, nif, codigo, cantidad, LocalDateTime.now());
        controlPedido.agregarPedido(p);
        System.out.println("✅ Pedido añadido.");
    }

    private void listarPedidos() {
        List<Pedido> pedidos = controlPedido.listarPedidos();
        System.out.println("\nLista de pedidos:");
        for (Pedido p : pedidos) {
            System.out.println(p.getNumeroPedido() + " - Cliente: " + p.getNifCliente() + ", Artículo: " + p.getCodigoArticulo());
        }
    }
}


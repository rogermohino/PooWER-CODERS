package POOwerCoders.vista;

import POOwerCoders.controlador.ControlPedido;
import POOwerCoders.controlador.ControlCliente;
import POOwerCoders.controlador.ControlArticulo;
import POOwerCoders.modelo.Cliente;
import POOwerCoders.modelo.Articulo;
import POOwerCoders.modelo.Pedido;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class VistaPedidos {
    private final ControlPedido controlPedido = new ControlPedido();
    private final ControlCliente controlCliente = new ControlCliente();
    private final ControlArticulo controlArticulo = new ControlArticulo();
    private final Scanner scanner = new Scanner(System.in);

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
        Cliente cliente = controlCliente.buscarCliente(nif);
        if (cliente == null) {
            System.out.println("❌ Cliente no encontrado. No se puede añadir el pedido.");
            return;
        }

        System.out.print("Código artículo: ");
        String codigo = scanner.nextLine();
        Articulo articulo = controlArticulo.buscarArticulo(codigo);
        if (articulo == null) {
            System.out.println("❌ Artículo no encontrado. No se puede añadir el pedido.");
            return;
        }

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine();

        Pedido p = new Pedido(numero, cliente, articulo, cantidad, LocalDateTime.now());
        controlPedido.agregarPedido(p);
        System.out.println("✅ Pedido añadido correctamente.");
    }

    private void listarPedidos() {
        List<Pedido> pedidos = controlPedido.listarPedidos();
        System.out.println("\n📋 Lista de pedidos:");
        for (Pedido p : pedidos) {
            System.out.println(
                    "Pedido #" + p.getNumeroPedido() +
                            " - Cliente: " + p.getCliente().getNombre() +
                            " (" + p.getCliente().getNif() + ")" +
                            ", Artículo: " + p.getArticulo().getDescripcion() +
                            ", Cantidad: " + p.getCantidad() +
                            ", Fecha: " + p.getFechaHora()
            );
        }
    }
}

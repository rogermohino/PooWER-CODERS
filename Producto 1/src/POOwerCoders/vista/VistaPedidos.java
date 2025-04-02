package POOwerCoders.vista;

import POOwerCoders.controlador.ControlPedido;
import POOwerCoders.controlador.ControlCliente;
import POOwerCoders.controlador.ControlArticulo;
import POOwerCoders.excepciones.DatosInvalidosException;
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
            System.out.println("2. Listar todos los pedidos");
            System.out.println("3. Ver pedidos pendientes");
            System.out.println("4. Ver pedidos enviados");
            System.out.println("5. Eliminar pedido");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarPedido();
                case 2 -> listarPedidos();
                case 3 -> mostrarPedidosPendientes();
                case 4 -> mostrarPedidosEnviados();
                case 5 -> eliminarPedido();
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

        try {
            controlPedido.agregarPedido(p);
            System.out.println("✅ Pedido añadido correctamente.");
        } catch (DatosInvalidosException e) {
            System.out.println("❌ No se pudo añadir el pedido: " + e.getMessage());
        }
    }


    private void listarPedidos() {
        List<Pedido> pedidos = controlPedido.listarPedidos();
        System.out.println("\n📋 Lista de pedidos:");
        for (Pedido p : pedidos) {
            mostrarDetallePedido(p);
        }
    }
    private void mostrarPedidosPendientes() {
        System.out.print("¿Filtrar por NIF cliente? (s/n): ");
        String respuesta = scanner.nextLine();
        String nif = null;
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Introduce el NIF: ");
            nif = scanner.nextLine();
        }

        try {
            List<Pedido> pendientes = controlPedido.obtenerPedidosPendientes(nif);
            System.out.println("\n Pedidos pendientes:");
            for (Pedido p : pendientes) {
                mostrarDetallePedido(p);
            }
        } catch (DatosInvalidosException e) {
            System.out.println("ℹ️ " + e.getMessage());
        }
    }

    private void mostrarPedidosEnviados() {
        System.out.print("¿Filtrar por NIF cliente? (s/n): ");
        String respuesta = scanner.nextLine();
        String nif = null;
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.print("Introduce el NIF: ");
            nif = scanner.nextLine();
        }

        try {
            List<Pedido> enviados = controlPedido.obtenerPedidosEnviados(nif);
            System.out.println("\n Pedidos enviados:");
            for (Pedido p : enviados) {
                mostrarDetallePedido(p);
            }
        } catch (DatosInvalidosException e) {
            System.out.println("ℹ️ " + e.getMessage());
        }
    }

    private void eliminarPedido() {
        System.out.print("Número del pedido a eliminar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        try {
            controlPedido.eliminarPedido(numero);
            System.out.println(" Pedido eliminado correctamente.");
        } catch (DatosInvalidosException e) {
            System.out.println("❌ No se pudo eliminar el pedido: " + e.getMessage());
        }
    }

    private void mostrarDetallePedido(Pedido p) {
        System.out.printf("Pedido #%d - Cliente: %s (%s), Artículo: %s, Cantidad: %d, Fecha: %s\n",
                p.getNumeroPedido(),
                p.getCliente().getNombre(),
                p.getCliente().getNif(),
                p.getArticulo().getDescripcion(),
                p.getCantidad(),
                p.getFechaHora());
    }


}

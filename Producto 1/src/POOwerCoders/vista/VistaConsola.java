package POOwerCoders.vista;

import POOwerCoders.controlador.Controlador;
import POOwerCoders.excepciones.DatosInvalidosException;
import POOwerCoders.modelo.*;


import java.util.List;
import java.util.Scanner;

public class VistaConsola {
    private Controlador controlador;
    private Scanner scanner;

    public VistaConsola(Controlador controlador) {
        this.controlador = controlador;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Agregar Artículo");
            System.out.println("3. Agregar Pedido");
            System.out.println("4. Mostrar Clientes");
            System.out.println("5. Mostrar Clientes Estándar");
            System.out.println("6. Mostrar Clientes Premium");
            System.out.println("7. Mostrar Artículos");
            System.out.println("8. Mostrar Pedidos");
            System.out.println("9. Eliminar Pedido");
            System.out.println("10. Mostrar Pedidos Pendientes");
            System.out.println("11. Mostrar Pedidos Enviados");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> agregarArticulo();
                case 3 -> agregarPedido();
                case 4 -> mostrarClientes();
                case 5 -> mostrarClientesEstandar();
                case 6 ->  mostrarClientesPremium();
                case 7 -> mostrarArticulos();
                case 8 -> mostrarPedidos();
                case 9 -> eliminarPedido();
                case 10 -> mostrarPedidosPendientes();
                case 11 -> mostrarPedidosEnviados();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (opcion != 0);
    }

    //Contiene manejo de excepciones personalizadas
    private void agregarCliente() {
        System.out.print("Ingrese nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Ingrese NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        int tipoCliente = 0;
        while (tipoCliente != 1 && tipoCliente != 2) {
            System.out.println("Seleccione el tipo de cliente:");
            System.out.println("1. Cliente Estándar");
            System.out.println("2. Cliente Premium");
            System.out.print("Opción: ");

            try {
                tipoCliente = Integer.parseInt(scanner.nextLine());
                if (tipoCliente != 1 && tipoCliente != 2) {
                    System.out.println("Error: Seleccione una opción válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }

        Cliente cliente;
        if (tipoCliente == 1) {
            cliente = new ClienteEstandar(nombre, domicilio, nif, email);
        } else {
            cliente = new ClientePremium(nombre, domicilio, nif, email);
        }

        try {
            controlador.agregarCliente(cliente);
            System.out.println("Cliente agregado correctamente.");
        } catch (DatosInvalidosException e) {
            System.out.println(e.getMessage());  // Muestra el mensaje de error si hay datos inválidos
        }
    }



    private void agregarArticulo() {
        System.out.print("Ingrese código del artículo: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese precio de venta: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese gastos de envío: ");
        double gastosEnvio = scanner.nextDouble();
        System.out.print("Ingrese tiempo de preparación (en minutos): ");
        int tiempoPreparacion = scanner.nextInt();
        scanner.nextLine();

        Articulo articulo = new Articulo(codigo, descripcion, precio, gastosEnvio, tiempoPreparacion);
        controlador.agregarArticulo(articulo);
        System.out.println("Artículo agregado correctamente.");
    }

    //Contiene manejo de excepciones personalizadas
    private void agregarPedido() {
        System.out.print("Ingrese el NIF del cliente: ");
        String nif = scanner.nextLine();
        Cliente cliente = null;

        // Buscar cliente por NIF
        for (Cliente c : controlador.obtenerClientes()) {
            if (c.getNif().equals(nif)) {
                cliente = c;
                break;
            }
        }

        // Si el cliente no existe, se pide que se registre uno nuevo
        if (cliente == null) {
            System.out.println("El cliente no existe. Se registrará un nuevo cliente.");
            cliente = registrarNuevoCliente(nif);

            try {
                controlador.agregarCliente(cliente);
            } catch (DatosInvalidosException e) {
                System.out.println(e.getMessage()); // Captura el error si los datos del cliente son inválidos
                return; // Sale del método para evitar continuar con un cliente inválido
            }
        }

        System.out.print("Ingrese el código del artículo: ");
        String codigoArticulo = scanner.nextLine();
        Articulo articulo = null;

        // Buscar artículo por código
        for (Articulo a : controlador.obtenerArticulos()) {
            if (a.getCodigo().equals(codigoArticulo)) {
                articulo = a;
                break;
            }
        }

        if (articulo == null) {
            System.out.println("Error: El artículo no existe.");
            return;
        }

        int cantidad;
        while (true) {
            try {
                System.out.print("Ingrese la cantidad de unidades: ");
                cantidad = Integer.parseInt(scanner.nextLine());
                if (cantidad <= 0) {
                    System.out.println("Error: La cantidad debe ser mayor a 0.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }

        // Crear el pedido y capturar la excepción en caso de datos inválidos
        try {
            Pedido pedido = new Pedido(
                    controlador.obtenerPedidos().size() + 1,
                    cliente,
                    articulo,
                    cantidad,
                    java.time.LocalDateTime.now()
            );

            controlador.agregarPedido(pedido);
            System.out.println("Pedido agregado correctamente.");

        } catch (DatosInvalidosException e) {
            System.out.println(e.getMessage()); // Captura el error si los datos del pedido son inválidos
        }
    }




    // Método para agregar un nuevo cliente directamnte desde agregar pedido.
    private Cliente registrarNuevoCliente(String nif) {
        System.out.print("Ingrese nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        int tipoCliente = 0;
        while (tipoCliente != 1 && tipoCliente != 2) {
            System.out.println("Seleccione el tipo de cliente:");
            System.out.println("1. Cliente Estándar");
            System.out.println("2. Cliente Premium");
            System.out.print("Opción: ");

            try {
                tipoCliente = Integer.parseInt(scanner.nextLine());
                if (tipoCliente != 1 && tipoCliente != 2) {
                    System.out.println("Error: Seleccione una opción válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }

        return (tipoCliente == 1)
                ? new ClienteEstandar(nombre, domicilio, nif, email)
                : new ClientePremium(nombre, domicilio, nif, email);
    }



    private void mostrarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        for (Cliente cliente : controlador.obtenerClientes()) {
            System.out.println(cliente);
        }
    }

    private void mostrarClientesEstandar() {
        System.out.println("\n--- CLIENTES ESTÁNDAR ---");
        for (ClienteEstandar cliente : controlador.obtenerClientesEstandar()) {
            System.out.println(cliente);
        }
    }

    private void mostrarClientesPremium() {
        System.out.println("\n--- CLIENTES PREMIUM ---");
        for (ClientePremium cliente : controlador.obtenerClientesPremium()) {
            System.out.println(cliente);
        }
    }

    private void mostrarArticulos() {
        System.out.println("\n--- LISTA DE ARTÍCULOS ---");
        for (Articulo articulo : controlador.obtenerArticulos()) {
            System.out.println(articulo);
        }
    }

    private void mostrarPedidos() {
        System.out.println("\n--- LISTA DE PEDIDOS ---");
        for (Pedido pedido : controlador.obtenerPedidos()) {
            System.out.println(pedido);
        }
    }


    private void eliminarPedido() {
        System.out.print("Ingrese el número del pedido a eliminar: ");
        int numeroPedido;

        try {
            numeroPedido = Integer.parseInt(scanner.nextLine());

            try {
                controlador.eliminarPedido(numeroPedido);
                System.out.println("Pedido eliminado correctamente.");
            } catch (DatosInvalidosException e) {
                System.out.println(e.getMessage()); // Captura el error si el pedido no se puede eliminar
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número válido.");
        }
    }


    private void mostrarPedidosPendientes() {
        System.out.print("Ingrese el NIF del cliente (o presione Enter para mostrar todos): ");
        String nif = scanner.nextLine().trim();
        if (nif.isEmpty()) {
            nif = null;
        }

        try {
            List<Pedido> pedidosPendientes = controlador.obtenerPedidosPendientes(nif);
            System.out.println("\n--- PEDIDOS PENDIENTES ---");
            for (Pedido p : pedidosPendientes) {
                System.out.println(p);
            }
        } catch (DatosInvalidosException e) {
            System.out.println(e.getMessage()); // Captura el error si no hay pedidos pendientes
        }
    }





    private void mostrarPedidosEnviados() {
        System.out.print("Ingrese el NIF del cliente (o presione Enter para mostrar todos): ");
        String nif = scanner.nextLine().trim();
        if (nif.isEmpty()) {
            nif = null;
        }

        try {
            List<Pedido> pedidosEnviados = controlador.obtenerPedidosEnviados(nif);
            System.out.println("\n--- PEDIDOS ENVIADOS ---");
            for (Pedido p : pedidosEnviados) {
                System.out.println(p);
            }
        } catch (DatosInvalidosException e) {
            System.out.println(e.getMessage()); // Captura el error si no hay pedidos enviados
        }
    }


}

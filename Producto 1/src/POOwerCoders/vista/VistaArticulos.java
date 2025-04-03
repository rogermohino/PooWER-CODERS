package POOwerCoders.vista;

import POOwerCoders.controlador.ControlArticulo;
import POOwerCoders.modelo.Articulo;

import java.util.Scanner;
import java.util.List;

public class VistaArticulos {
    private ControlArticulo controlArticulo = new ControlArticulo();
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Artículos ---");
            System.out.println("1. Añadir artículo");
            System.out.println("2. Listar todos los artículos");
            System.out.println("3. Buscar artículos por rango de precio");
            System.out.println("4. Buscar artículos por descripción");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarArticulo();
                case 2 -> listarArticulos();
                case 3 -> buscarPorPrecio();
                case 4 -> buscarPorDescripcion();
            }
        } while (opcion != 0);
    }

    private void agregarArticulo() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Precio de venta: ");
        double precio = scanner.nextDouble();
        System.out.print("Gastos de envío: ");
        double envio = scanner.nextDouble();
        System.out.print("Tiempo de preparación (minutos): ");
        int tiempo = scanner.nextInt();
        scanner.nextLine();

        Articulo a = new Articulo(codigo, descripcion, precio, envio, tiempo);
        controlArticulo.agregarArticulo(a);
        System.out.println("✅ Artículo añadido.");
    }

    private void listarArticulos() {
        List<Articulo> articulos = controlArticulo.listarArticulos();
        System.out.println("\nLista de artículos:");
        for (Articulo a : articulos) {
            System.out.println(a.getCodigo() + " - " + a.getDescripcion());
        }
    }

    private void buscarPorPrecio() {
        System.out.print("Precio mínimo: ");
        double min = scanner.nextDouble();
        System.out.print("Precio máximo: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        List<Articulo> articulos = controlArticulo.buscarPorRangoPrecio(min, max);
        System.out.println("\n📋 Artículos en ese rango de precio:");
        for (Articulo a : articulos) {
            System.out.printf("%s - %s | Precio: %.2f €\n", a.getCodigo(), a.getDescripcion(), a.getPrecioVenta());
        }
    }

    private void buscarPorDescripcion() {
        System.out.print("Palabra clave: ");
        String palabra = scanner.nextLine();

        List<Articulo> articulos = controlArticulo.buscarPorDescripcion(palabra);
        System.out.println("\n📋 Artículos que coinciden con la descripción:");
        for (Articulo a : articulos) {
            System.out.printf("%s - %s\n", a.getCodigo(), a.getDescripcion());
        }
    }

}


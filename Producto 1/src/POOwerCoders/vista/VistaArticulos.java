package POOwerCoders.vista;

import POOwerCoders.controlador.ControlArticulo;
import POOwerCoders.modelo.Articulo;

import java.util.Scanner;
import java.util.List;

/**
 * VistaArticulos representa la vista del módulo de gestión de artículos.
 * Permite al usuario interactuar mediante consola para añadir, listar o buscar artículos.
 */
public class VistaArticulos {

    // Controlador que maneja la lógica de negocio relacionada con artículos
    private ControlArticulo controlArticulo = new ControlArticulo();

    // Scanner para leer datos introducidos por el usuario
    private Scanner scanner = new Scanner(System.in);

    /**
     * Muestra el menú principal de gestión de artículos y ejecuta las opciones seleccionadas.
     */
    public void mostrarMenu() {
        int opcion;
        do {
            // Mostrar opciones disponibles
            System.out.println("\n--- Gestión de Artículos ---");
            System.out.println("1. Añadir artículo");
            System.out.println("2. Listar todos los artículos");
            System.out.println("3. Buscar artículos por rango de precio");
            System.out.println("4. Buscar artículos por descripción");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            // Leer opción del usuario
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            // Ejecutar acción según la opción seleccionada
            switch (opcion) {
                case 1 -> agregarArticulo();
                case 2 -> listarArticulos();
                case 3 -> buscarPorPrecio();
                case 4 -> buscarPorDescripcion();
            }
        } while (opcion != 0); // Salir si se elige 0
    }

    /**
     * Permite al usuario agregar un nuevo artículo ingresando los datos por consola.
     */
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
        scanner.nextLine(); // Limpiar el buffer

        // Crear objeto Articulo y enviarlo al controlador
        Articulo a = new Articulo(codigo, descripcion, precio, envio, tiempo);
        controlArticulo.agregarArticulo(a);

        System.out.println("✅ Artículo añadido.");
    }

    /**
     * Muestra todos los artículos registrados en la base de datos.
     */
    private void listarArticulos() {
        List<Articulo> articulos = controlArticulo.listarArticulos();
        System.out.println("\n📦 Lista de artículos:");
        for (Articulo a : articulos) {
            System.out.println(a.getCodigo() + " - " + a.getDescripcion());
        }
    }

    /**
     * Permite buscar artículos por un rango de precio (mínimo y máximo).
     */
    private void buscarPorPrecio() {
        System.out.print("Precio mínimo: ");
        double min = scanner.nextDouble();
        System.out.print("Precio máximo: ");
        double max = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer

        // Buscar artículos dentro del rango indicado
        List<Articulo> articulos = controlArticulo.buscarPorRangoPrecio(min, max);
        System.out.println("\n📋 Artículos en ese rango de precio:");
        for (Articulo a : articulos) {
            System.out.printf("%s - %s | Precio: %.2f €\n", a.getCodigo(), a.getDescripcion(), a.getPrecioVenta());
        }
    }

    /**
     * Permite buscar artículos que contengan una palabra clave en su descripción.
     */
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

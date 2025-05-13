package POOwerCoders.vista;

import POOwerCoders.modelo.Cliente;
import POOwerCoders.excepciones.DatosInvalidosException;
import POOwerCoders.modelo.ClienteEstandar;
import POOwerCoders.modelo.ClientePremium;
import POOwerCoders.controlador.ControlCliente;

import java.util.Scanner;
import java.util.List;

/**
 * VistaClientes gestiona la interacción por consola con el usuario
 * para operar sobre clientes: añadirlos, listarlos y filtrarlos por tipo.
 */
public class VistaClientes {

    // Controlador encargado de la lógica relacionada con los clientes
    private ControlCliente controlCliente = new ControlCliente();

    // Scanner para leer datos introducidos por teclado
    private Scanner scanner = new Scanner(System.in);

    /**
     * Muestra el menú de opciones disponibles para la gestión de clientes.
     */
    public void mostrarMenu() {
        int opcion;
        do {
            // Menú principal
            System.out.println("\n--- Gestión de Clientes ---");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Listar todos los clientes");
            System.out.println("3. Listar clientes estándar");
            System.out.println("4. Listar clientes premium");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            // Lectura de la opción
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            // Lógica según la opción elegida
            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> listarClientes();
                case 3 -> listarClientesEstandar();
                case 4 -> listarClientesPremium();
            }
        } while (opcion != 0);
    }

    /**
     * Solicita los datos del nuevo cliente al usuario y lo agrega mediante el controlador.
     * Puede ser cliente estándar o premium.
     */
    private void agregarCliente() {
        try {
            // Entrada de datos por parte del usuario
            System.out.print("NIF: ");
            String nif = scanner.nextLine();

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Domicilio: ");
            String domicilio = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("¿Es cliente Premium? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            // Crear el tipo de cliente según la respuesta
            Cliente cliente;
            if (respuesta.equals("s")) {
                cliente = new ClientePremium(nombre, domicilio, nif, email);
            } else {
                cliente = new ClienteEstandar(nombre, domicilio, nif, email);
            }

            // Llamar al controlador para añadirlo
            controlCliente.agregarCliente(cliente);
            System.out.println("✅ Cliente añadido correctamente.");

        } catch (DatosInvalidosException e) {
            // Error personalizado en caso de datos inválidos
            System.err.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            // Error general no esperado
            System.err.println("❌ Error inesperado al añadir cliente: " + e.getMessage());
            scanner.nextLine(); // Limpia el buffer para evitar bucles infinitos
        }
    }

    /**
     * Muestra la lista de todos los clientes registrados.
     */
    private void listarClientes() {
        try {
            List<Cliente> clientes = controlCliente.listarClientes();
            System.out.println("\n📋 Lista de clientes:");
            for (Cliente c : clientes) {
                System.out.println(c.getNif() + " - " + c.getNombre());
            }
        } catch (Exception e) {
            System.err.println("❌ Error al listar clientes: " + e.getMessage());
        }
    }

    /**
     * Muestra únicamente los clientes que son del tipo estándar.
     */
    private void listarClientesEstandar() {
        try {
            List<ClienteEstandar> estandar = controlCliente.obtenerClientesEstandar();
            System.out.println("\n📋 Clientes estándar:");
            for (ClienteEstandar c : estandar) {
                System.out.println(c.getNif() + " - " + c.getNombre());
            }
        } catch (Exception e) {
            System.err.println("❌ Error al listar clientes estándar: " + e.getMessage());
        }
    }

    /**
     * Muestra únicamente los clientes que son del tipo premium,
     * incluyendo su cuota anual y el porcentaje de descuento en envío.
     */
    private void listarClientesPremium() {
        try {
            List<ClientePremium> premium = controlCliente.obtenerClientesPremium();
            System.out.println("\n📋 Clientes premium:");
            for (ClientePremium c : premium) {
                System.out.println(c.getNif() + " - " + c.getNombre() +
                        " | Cuota anual: " + c.getCuotaAnual() +
                        " | Descuento envío: " + (c.getDescuentoEnvio() * 100) + "%");
            }
        } catch (Exception e) {
            System.err.println("❌ Error al listar clientes premium: " + e.getMessage());
        }
    }

}

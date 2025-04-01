package POOwerCoders.vista;

import POOwerCoders.controlador.ControlCliente;
import POOwerCoders.modelo.Cliente;

import java.util.Scanner;
import java.util.List;

public class VistaClientes {
    private ControlCliente controlCliente = new ControlCliente();
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Clientes ---");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Listar clientes");
            System.out.println("0. Volver al menú principal");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> listarClientes();
            }
        } while (opcion != 0);
    }

    private void agregarCliente() {
        System.out.print("NIF: ");
        String nif = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Domicilio: ");
        String domicilio = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Cliente cliente = new Cliente(nombre, domicilio, nif, email);
        controlCliente.agregarCliente(cliente);
        System.out.println("✅ Cliente añadido correctamente.");
    }

    private void listarClientes() {
        List<Cliente> clientes = controlCliente.listarClientes();
        System.out.println("\nLista de clientes:");
        for (Cliente c : clientes) {
            System.out.println(c.getNif() + " - " + c.getNombre());
        }
    }
}


package com.poowercoders.POOwerCoders.vista;

import com.poowercoders.POOwerCoders.modelo.Cliente;
import com.poowercoders.POOwerCoders.excepciones.DatosInvalidosException;
import com.poowercoders.POOwerCoders.modelo.ClienteEstandar;
import com.poowercoders.POOwerCoders.modelo.ClientePremium;
import com.poowercoders.POOwerCoders.controlador.ControlCliente;

import java.util.Scanner;
import java.util.List;

/**
 * Clase encargada de mostrar el menú y gestionar acciones relacionadas con los clientes.
 */
public class VistaClientes {
    private final ControlCliente controlCliente = new ControlCliente();
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Clientes ---");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Listar todos los clientes");
            System.out.println("3. Listar clientes estándar");
            System.out.println("4. Listar clientes premium");
            System.out.println("0. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> agregarCliente();
                case 2 -> listarClientes();
                case 3 -> listarClientesEstandar();
                case 4 -> listarClientesPremium();
            }
        } while (opcion != 0);
    }

    private void agregarCliente() {
        try {
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

            Cliente cliente;
            if (respuesta.equals("s")) {
                cliente = new ClientePremium(nombre, domicilio, nif, email);
            } else {
                cliente = new ClienteEstandar(nombre, domicilio, nif, email);
            }

            controlCliente.agregarCliente(cliente);
            System.out.println("✅ Cliente añadido correctamente.");

        } catch (DatosInvalidosException e) {
            System.err.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Error inesperado al añadir cliente: " + e.getMessage());
            scanner.nextLine(); // Limpia el buffer
        }
    }

    private void listarClientes() {
        List<Cliente> clientes = controlCliente.listarClientes();
        System.out.println("\nLista de clientes:");
        for (Cliente c : clientes) {
            System.out.println(c.getNif() + " - " + c.getNombre());
        }
    }

    private void listarClientesEstandar() {
        List<ClienteEstandar> estandar = controlCliente.obtenerClientesEstandar();
        System.out.println("\n📋 Clientes estándar:");
        for (ClienteEstandar c : estandar) {
            System.out.println(c.getNif() + " - " + c.getNombre());
        }
    }

    private void listarClientesPremium() {
        List<ClientePremium> premium = controlCliente.obtenerClientesPremium();
        System.out.println("\n📋 Clientes premium:");
        for (ClientePremium c : premium) {
            System.out.println(c.getNif() + " - " + c.getNombre() +
                    " | Cuota anual: " + c.getCuotaAnual() +
                    " | Descuento envío: " + (c.getDescuentoEnvio() * 100) + "%");
        }
    }
}

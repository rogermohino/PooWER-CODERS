package POOwerCoders.controlador;

import POOwerCoders.modelo.*;
import POOwerCoders.excepciones.DatosInvalidosException;

import java.util.ArrayList;
import java.util.List;

public class Controlador {
    private OnlineStore tienda;

    public Controlador(OnlineStore tienda) {
        this.tienda = tienda;
    }

    //Contiene el manejo de excepciones personalizadas
    public void agregarCliente(Cliente cliente) throws DatosInvalidosException {
        if (cliente.getNombre().isEmpty() || cliente.getNif().isEmpty() || cliente.getEmail().isEmpty()) {
            throw new DatosInvalidosException("Error: El nombre, NIF y email del cliente no pueden estar vacíos.");
        }

        tienda.añadirCliente(cliente);
    }

    public void agregarArticulo(Articulo articulo) {
        tienda.añadirArticulo(articulo);
    }


    //Contiene el manejo de excepciones personalizadas
    public void agregarPedido(Pedido pedido) throws DatosInvalidosException {
        if (pedido.getCliente() == null) {
            throw new DatosInvalidosException("Error: El pedido debe tener un cliente válido.");
        }

        if (pedido.getArticulo() == null) {
            throw new DatosInvalidosException("Error: El pedido debe incluir un artículo válido.");
        }

        if (pedido.getCantidad() <= 0) {
            throw new DatosInvalidosException("Error: La cantidad del pedido debe ser mayor a 0.");
        }

        tienda.añadirPedido(pedido);
    }


    public List<Cliente> obtenerClientes() {
        return tienda.getClientes();
    }

    public List<Articulo> obtenerArticulos() {
        return tienda.getArticulos();
    }

    public List<Pedido> obtenerPedidos() {
        return tienda.getPedidos();
    }

    public List<ClienteEstandar> obtenerClientesEstandar() {
        List<ClienteEstandar> clientesEstandar = new ArrayList<>();
        for (Cliente c : tienda.getClientes()) {
            if (c instanceof ClienteEstandar) {
                clientesEstandar.add((ClienteEstandar) c);
            }
        }
        return clientesEstandar;
    }

    public List<ClientePremium> obtenerClientesPremium() {
        List<ClientePremium> clientesPremium = new ArrayList<>();
        for (Cliente c : tienda.getClientes()) {
            if (c instanceof ClientePremium) {
                clientesPremium.add((ClientePremium) c);
            }
        }
        return clientesPremium;
    }

    public boolean eliminarPedido(int numeroPedido) {
        Pedido pedidoAEliminar = null;

        // Buscar el pedido por número
        for (Pedido p : tienda.getPedidos()) {
            if (p.getNumeroPedido() == numeroPedido) {
                pedidoAEliminar = p;
                break;
            }
        }

        if (pedidoAEliminar == null) {
            System.out.println("Error: No se encontró un pedido con ese número.");
            return false;
        }

        // Verificar si el tiempo de preparación ha pasado
        long minutosTranscurridos = java.time.Duration.between(
                pedidoAEliminar.getFechaHora(), java.time.LocalDateTime.now()
        ).toMinutes();

        if (minutosTranscurridos > pedidoAEliminar.getArticulo().getTiempoPreparacion()) {
            System.out.println("Error: No se puede eliminar el pedido porque ya ha sido enviado.");
            return false;
        }

        tienda.getPedidos().remove(pedidoAEliminar);
        System.out.println("Pedido eliminado correctamente.");
        return true;
    }

    public List<Pedido> obtenerPedidosPendientes(String nifCliente) {
        List<Pedido> pedidosPendientes = new ArrayList<>();
        for (Pedido p : tienda.getPedidos()) {
            long minutosTranscurridos = java.time.Duration.between(
                    p.getFechaHora(), java.time.LocalDateTime.now()
            ).toMinutes();

            if (minutosTranscurridos < p.getArticulo().getTiempoPreparacion()) {
                if (nifCliente == null || p.getCliente().getNif().equals(nifCliente)) {
                    pedidosPendientes.add(p);
                }
            }
        }
        return pedidosPendientes;
    }

    public List<Pedido> obtenerPedidosEnviados(String nifCliente) {
        List<Pedido> pedidosEnviados = new ArrayList<>();
        for (Pedido p : tienda.getPedidos()) {
            long minutosTranscurridos = java.time.Duration.between(
                    p.getFechaHora(), java.time.LocalDateTime.now()
            ).toMinutes();

            if (minutosTranscurridos >= p.getArticulo().getTiempoPreparacion()) {
                if (nifCliente == null || p.getCliente().getNif().equals(nifCliente)) {
                    pedidosEnviados.add(p);
                }
            }
        }
        return pedidosEnviados;
    }


}

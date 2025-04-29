package com.poowercoders.POOwerCoders.controlador;

import com.poowercoders.POOwerCoders.excepciones.DatosInvalidosException;
import com.poowercoders.POOwerCoders.modelo.Pedido;
import com.poowercoders.POOwerCoders.modelo.DAO.DAOFactory;
import com.poowercoders.POOwerCoders.modelo.DAO.PedidoDAO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControlPedido {

    private PedidoDAO pedidoDAO;

    public ControlPedido() {
        this.pedidoDAO = DAOFactory.getPedidoDAO();
    }

    public void agregarPedido(Pedido pedido) throws DatosInvalidosException {
        if (pedido.getCliente() == null || pedido.getArticulo() == null) {
            throw new DatosInvalidosException("Cliente o artículo no válido.");
        }
        if (pedido.getCantidad() <= 0) {
            throw new DatosInvalidosException("La cantidad debe ser mayor a cero.");
        }
        if (buscarPedido(pedido.getNumeroPedido()) != null) {
            throw new DatosInvalidosException("Ya existe un pedido con ese número.");
        }
        pedidoDAO.insertar(pedido);
    }

    public Pedido buscarPedido(int numero) {
        return pedidoDAO.buscarPorNumero(numero);
    }

    public List<Pedido> listarPedidos() {
        return pedidoDAO.listarTodos();
    }

    public void eliminarPedido(int numeroPedido) throws DatosInvalidosException {
        Pedido pedido = pedidoDAO.buscarPorNumero(numeroPedido);
        if (pedido == null) {
            throw new DatosInvalidosException("No se encontró el pedido.");
        }

        long minutosTranscurridos = Duration.between(pedido.getFechaHora(), LocalDateTime.now()).toMinutes();
        if (minutosTranscurridos > pedido.getArticulo().getTiempoPreparacion()) {
            throw new DatosInvalidosException("No se puede eliminar: el pedido ya ha sido enviado.");
        }
        pedidoDAO.eliminar(numeroPedido);
    }

    public List<Pedido> obtenerPedidosPendientes(String nifCliente) throws DatosInvalidosException {
        List<Pedido> pendientes = new ArrayList<>();
        for (Pedido p : pedidoDAO.listarTodos()) {
            long minutos = Duration.between(p.getFechaHora(), LocalDateTime.now()).toMinutes();
            if (minutos < p.getArticulo().getTiempoPreparacion() &&
                    (nifCliente == null || p.getCliente().getNif().equalsIgnoreCase(nifCliente))) {
                pendientes.add(p);
            }
        }
        if (pendientes.isEmpty()) {
            throw new DatosInvalidosException("No hay pedidos pendientes.");
        }
        return pendientes;
    }

    public List<Pedido> obtenerPedidosEnviados(String nifCliente) throws DatosInvalidosException {
        List<Pedido> enviados = new ArrayList<>();
        for (Pedido p : pedidoDAO.listarTodos()) {
            long minutos = Duration.between(p.getFechaHora(), LocalDateTime.now()).toMinutes();
            if (minutos >= p.getArticulo().getTiempoPreparacion() &&
                    (nifCliente == null || p.getCliente().getNif().equalsIgnoreCase(nifCliente))) {
                enviados.add(p);
            }
        }
        if (enviados.isEmpty()) {
            throw new DatosInvalidosException("No hay pedidos enviados.");
        }
        return enviados;
    }
}

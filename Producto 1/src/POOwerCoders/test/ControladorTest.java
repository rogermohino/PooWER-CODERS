package POOwerCoders.test;

import POOwerCoders.controlador.ControlPedido;
import POOwerCoders.modelo.*;
import POOwerCoders.excepciones.DatosInvalidosException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlPedidoTest {
    private ControlPedido controlPedido;

    @BeforeEach
    void setUp() {
        controlPedido = new ControlPedido(); // usa la implementación real
    }

    @Test
    void testAgregarPedidoValido() {
        Cliente cliente = new ClienteEstandar("Ana", "Calle Sol", "11223344C", "ana@mail.com");
        Articulo articulo = new Articulo("B001", "Lámpara", 30.0, 5.0, 15);
        Pedido pedido = new Pedido(999, cliente, articulo, 2, LocalDateTime.now());

        try {
            controlPedido.agregarPedido(pedido);
            List<Pedido> pedidos = controlPedido.listarPedidos();
            assertTrue(pedidos.stream().anyMatch(p -> p.getNumeroPedido() == 999),
                    "El pedido debería haberse agregado correctamente.");
        } catch (DatosInvalidosException e) {
            fail("No debería lanzarse una excepción para un pedido válido.");
        }
    }

    @Test
    void testAgregarPedidoInvalido_clienteNulo() {
        Articulo articulo = new Articulo("C002", "Sofá", 200.0, 20.0, 45);
        Pedido pedido = new Pedido(1000, null, articulo, 1, LocalDateTime.now());

        assertThrows(DatosInvalidosException.class, () ->
                        controlPedido.agregarPedido(pedido),
                "Debe lanzar excepción si el cliente es nulo");
    }

    @Test
    void testAgregarPedidoInvalido_cantidadNegativa() {
        Cliente cliente = new ClienteEstandar("Luis", "Calle Luna", "99887766D", "luis@mail.com");
        Articulo articulo = new Articulo("Z123", "Teclado", 50.0, 5.0, 5);
        Pedido pedido = new Pedido(1001, cliente, articulo, -3, LocalDateTime.now());

        assertThrows(DatosInvalidosException.class, () ->
                        controlPedido.agregarPedido(pedido),
                "Debe lanzar excepción si la cantidad es menor o igual a cero");
    }
}


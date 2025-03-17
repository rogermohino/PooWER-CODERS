//Pruebas JUnit para Controlador.agregarPedido(Pedido pedido) → Asegura que solo se agreguen pedidos válidos y maneja excepciones.

package POOwerCoders.test;

import POOwerCoders.controlador.Controlador;
import POOwerCoders.excepciones.DatosInvalidosException;
import POOwerCoders.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControladorTest {
    private Controlador controlador;
    private OnlineStore tienda;

    @BeforeEach
    void setUp() {
        tienda = new OnlineStore();
        controlador = new Controlador(tienda);
    }

    @Test
    void testAgregarPedidoValido() {
        Cliente cliente = new ClienteEstandar("Ana", "Calle Sol", "11223344C", "ana@mail.com");
        Articulo articulo = new Articulo("B001", "Lámpara", 30.0, 5.0, 15);
        Pedido pedido = new Pedido(1, cliente, articulo, 2, LocalDateTime.now());

        try {
            controlador.agregarPedido(pedido);
            List<Pedido> pedidos = controlador.obtenerPedidos();
            assertTrue(pedidos.contains(pedido), "El pedido debería haberse agregado correctamente.");
        } catch (DatosInvalidosException e) {
            fail("No debería lanzarse una excepción para un pedido válido.");
        }
    }

    @Test
    void testAgregarPedidoInvalido() {
        Cliente cliente = null;
        Articulo articulo = new Articulo("C002", "Sofá", 200.0, 20.0, 45);
        Pedido pedido = new Pedido(2, cliente, articulo, 1, LocalDateTime.now());

        assertThrows(DatosInvalidosException.class, () -> controlador.agregarPedido(pedido),
                "Debería lanzar una excepción si el cliente es nulo.");
    }
}

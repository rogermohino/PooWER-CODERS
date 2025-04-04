package POOwerCoders.test;

// Importaciones necesarias
import POOwerCoders.controlador.ControlPedido;
import POOwerCoders.modelo.*;
import POOwerCoders.excepciones.DatosInvalidosException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta clase contiene pruebas unitarias para la clase ControlPedido
 * utilizando JUnit 5. Se comprueba el comportamiento esperado
 * cuando se agregan pedidos válidos o inválidos.
 */
class ControlPedidoTest {

    private ControlPedido controlPedido; // Objeto que vamos a probar

    /**
     * Este método se ejecuta antes de cada prueba (@BeforeEach).
     * Inicializa un nuevo objeto ControlPedido para asegurar que cada test
     * empiece con un entorno limpio.
     */
    @BeforeEach
    void setUp() {
        controlPedido = new ControlPedido(); // Se usa la clase real, no un mock
    }

    /**
     * Prueba que verifica si se puede agregar un pedido válido sin errores.
     * Se espera que el pedido se guarde correctamente y que aparezca en la lista.
     */
    @Test
    void testAgregarPedidoValido() {
        // Creamos un cliente, un artículo y un pedido válido
        Cliente cliente = new ClienteEstandar("Ana", "Calle Sol", "11223344C", "ana@mail.com");
        Articulo articulo = new Articulo("B001", "Lámpara", 30.0, 5.0, 15);
        Pedido pedido = new Pedido(999, cliente, articulo, 2, LocalDateTime.now());

        try {
            // Intentamos agregar el pedido
            controlPedido.agregarPedido(pedido);

            // Verificamos si el pedido aparece en la lista
            List<Pedido> pedidos = controlPedido.listarPedidos();
            assertTrue(
                    pedidos.stream().anyMatch(p -> p.getNumeroPedido() == 999),
                    "El pedido debería haberse agregado correctamente."
            );
        } catch (DatosInvalidosException e) {
            // Si se lanza una excepción, la prueba falla
            fail("No debería lanzarse una excepción para un pedido válido.");
        }
    }

    /**
     * Prueba que verifica que no se permite agregar un pedido con cliente nulo.
     * Se espera que se lance una excepción personalizada.
     */
    @Test
    void testAgregarPedidoInvalido_clienteNulo() {
        Articulo articulo = new Articulo("C002", "Sofá", 200.0, 20.0, 45);
        Pedido pedido = new Pedido(1000, null, articulo, 1, LocalDateTime.now());

        // Se espera una excepción al intentar agregar este pedido
        assertThrows(
                DatosInvalidosException.class,
                () -> controlPedido.agregarPedido(pedido),
                "Debe lanzar excepción si el cliente es nulo"
        );
    }

    /**
     * Prueba que verifica que no se permite agregar un pedido con cantidad negativa.
     * Se espera que se lance una excepción personalizada.
     */
    @Test
    void testAgregarPedidoInvalido_cantidadNegativa() {
        Cliente cliente = new ClienteEstandar("Luis", "Calle Luna", "99887766D", "luis@mail.com");
        Articulo articulo = new Articulo("Z123", "Teclado", 50.0, 5.0, 5);
        Pedido pedido = new Pedido(1001, cliente, articulo, -3, LocalDateTime.now());

        // Se espera una excepción al intentar agregar este pedido
        assertThrows(
                DatosInvalidosException.class,
                () -> controlPedido.agregarPedido(pedido),
                "Debe lanzar excepción si la cantidad es menor o igual a cero"
        );
    }
}

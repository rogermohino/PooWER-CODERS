package POOwerCoders.test;

import POOwerCoders.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas JUnit para verificar que el método calcularPrecio()
 * de la clase Pedido funciona correctamente para clientes estándar y premium.
 */
class PedidoTest {

    /**
     * Verifica el cálculo del precio de un pedido hecho por un cliente estándar.
     * Para clientes estándar, el precio es:
     * (precio del artículo * cantidad) + gastos de envío
     */
    @Test
    void testCalcularPrecio_ClienteEstandar() {
        // Creamos un cliente estándar
        Cliente cliente = new ClienteEstandar("Juan", "Calle Falsa 123", "12345678A", "juan@mail.com");

        // Creamos un artículo con precio 100€ y gastos de envío 10€
        Articulo articulo = new Articulo("A001", "Mesa de madera", 100.0, 10.0, 30);

        // Creamos un pedido de 2 unidades del artículo
        Pedido pedido = new Pedido(1, cliente, articulo, 2, java.time.LocalDateTime.now());

        // Cálculo esperado: (100 x 2) + 10 = 210€
        double precioEsperado = (100.0 * 2) + 10.0;

        // Verificamos si el precio calculado es correcto
        assertEquals(precioEsperado, pedido.calcularPrecio(), "El precio del pedido para cliente estándar es incorrecto.");
    }

    /**
     * Verifica el cálculo del precio de un pedido hecho por un cliente premium.
     * Los clientes premium tienen un 20% de descuento en los gastos de envío.
     * Para clientes premium, el precio es:
     * (precio del artículo * cantidad) + (gastos de envío * 0.8)
     */
    @Test
    void testCalcularPrecio_ClientePremium() {
        // Creamos un cliente premium
        Cliente cliente = new ClientePremium("María", "Avenida Siempre Viva", "87654321B", "maria@mail.com");

        // Artículo con precio 50€, gastos de envío 5€
        Articulo articulo = new Articulo("A002", "Silla de madera", 50.0, 5.0, 20);

        // Pedido de 3 unidades
        Pedido pedido = new Pedido(2, cliente, articulo, 3, java.time.LocalDateTime.now());

        // Cálculo esperado: (50 x 3) + (5 * 0.8) = 150 + 4 = 154€
        double precioEsperado = (50.0 * 3) + (5.0 * 0.8);

        // Verificamos si el precio calculado es correcto
        assertEquals(precioEsperado, pedido.calcularPrecio(), "El precio del pedido para cliente premium es incorrecto.");
    }
}

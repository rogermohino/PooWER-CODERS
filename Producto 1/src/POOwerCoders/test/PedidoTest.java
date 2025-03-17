//Pruebas JUnit para Pedido.calcularPrecio() → Verifica si el cálculo del precio del pedido es correcto.

package POOwerCoders.test;

import POOwerCoders.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    void testCalcularPrecio_ClienteEstandar() {
        Cliente cliente = new ClienteEstandar("Juan", "Calle Falsa 123", "12345678A", "juan@mail.com");
        Articulo articulo = new Articulo("A001", "Mesa de madera", 100.0, 10.0, 30);
        Pedido pedido = new Pedido(1, cliente, articulo, 2, java.time.LocalDateTime.now());

        double precioEsperado = (100.0 * 2) + 10.0; // Precio del artículo * cantidad + gastos de envío
        assertEquals(precioEsperado, pedido.calcularPrecio(), "El precio del pedido para cliente estándar es incorrecto.");
    }

    @Test
    void testCalcularPrecio_ClientePremium() {
        Cliente cliente = new ClientePremium("María", "Avenida Siempre Viva", "87654321B", "maria@mail.com");
        Articulo articulo = new Articulo("A002", "Silla de madera", 50.0, 5.0, 20);
        Pedido pedido = new Pedido(2, cliente, articulo, 3, java.time.LocalDateTime.now());

        double precioEsperado = (50.0 * 3) + (5.0 * 0.8); // Precio + 20% de descuento en gastos de envío
        assertEquals(precioEsperado, pedido.calcularPrecio(), "El precio del pedido para cliente premium es incorrecto.");
    }
}

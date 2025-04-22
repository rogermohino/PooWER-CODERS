package com.poowercoders.POOwerCoders.modelo;

/**
 * Interfaz IPedido que define el comportamiento general de un pedido.
 *
 * Cualquier clase que implemente esta interfaz deberá proporcionar la lógica
 * de cálculo del precio y la posibilidad de cancelación del pedido.
 *
 * NOTA: Al ser una interfaz:
 * - No contiene atributos concretos.
 * - No tiene constructor.
 * - No tiene implementación de métodos (solo las firmas).
 */
public interface IPedido {

    /**
     * Método para calcular el precio total del pedido.
     * Deberá ser implementado por la clase que represente el pedido.
     *
     * @return Precio total del pedido.
     */
    double calcularPrecio();

    /**
     * Método para determinar si el pedido puede cancelarse.
     * Deberá implementarse según la lógica de negocio (por ejemplo,
     * dependiendo del tiempo transcurrido desde su creación).
     *
     * @return true si el pedido puede cancelarse, false si no.
     */
    boolean cancelarPedido();
}

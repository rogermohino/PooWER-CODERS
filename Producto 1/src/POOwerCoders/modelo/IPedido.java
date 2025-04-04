package POOwerCoders.modelo;

/**
 * Interfaz que define el comportamiento esperado de un Pedido.
 * Una interfaz no contiene atributos ni implementación de métodos,
 * solo define qué métodos deben implementar las clases que la usen.
 */
public interface IPedido {

    // No se definen atributos en una interfaz, solo métodos a implementar.

    // No es necesario definir constructores, ya que las interfaces no pueden instanciarse directamente.

    // Tampoco se definen getters o setters, ya que no hay atributos propios en la interfaz.

    /**
     * Método que debe calcular el precio total del pedido.
     * Este cálculo lo implementará la clase concreta que implemente esta interfaz.
     *
     * @return El precio total del pedido.
     */
    double calcularPrecio();

    /**
     * Método que debe intentar cancelar un pedido.
     * La lógica (si se puede cancelar o no) estará en la clase que implemente este método.
     *
     * @return true si el pedido se canceló, false si no es posible cancelarlo.
     */
    boolean cancelarPedido();
}

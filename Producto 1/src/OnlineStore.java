import java.util.ArrayList;
import java.util.List;

public class OnlineStore {
    //Atributos
    private List<Cliente> clientes = new ArrayList<>();
    private List<Articulo> articulos = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();


    //Getters
    public List<Cliente> getClientes() { return clientes; }
    public List<Articulo> getArticulos() { return articulos; }
    public List<Pedido> getPedidos() { return pedidos; }


    //Métodos utilizados en lugar de Setters, para evitar modificar toda la lista
    public void añadirArticulo(Articulo a) { articulos.add(a); }
    public void añadirCliente(Cliente c) { clientes.add(c); }
    public void añadirPedido(Pedido p) { pedidos.add(p); }
}


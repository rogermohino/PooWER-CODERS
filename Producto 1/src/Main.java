package POOwerCoders;

import POOwerCoders.controlador.Controlador;
import POOwerCoders.modelo.OnlineStore;
import POOwerCoders.vista.VistaConsola;

public class Main {
    public static void main(String[] args) {
        OnlineStore tienda = new OnlineStore();
        Controlador controlador = new Controlador(tienda);
        VistaConsola vista = new VistaConsola(controlador);

        vista.mostrarMenu();
    }
}

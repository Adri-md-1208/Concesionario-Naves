package concesionario;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("TU CONCESIONARIO DE NAVES FAVORITO");
            ClientManager manager = new ClientManager();
            manager.management();
        }
    }
}
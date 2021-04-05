package sample;

import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("TU CONCESIONARIO DE NAVES FAVORITO");
            ClientManager manager = new ClientManager();
            manager.management();


        }
    }
}

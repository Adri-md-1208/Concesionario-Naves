package concesionario;

import java.io.IOException;
import java.util.Scanner;

public class SubscribeToShip {

    public void subscribe(Client client) throws IOException, ClassNotFoundException {
        System.out.println("Elija el tipo de nave a la que suscribirse:");
        String[] tipos = {"Carguero", "Destructor", "Caza", "Estacion Espacial"};
        int cont = 1;
        for (String tipo : tipos) {
            System.out.println(cont++ + ". " + tipo);
        }
        Scanner sc = new Scanner(System.in);
        String tipo = tipos[Integer.parseInt(sc.nextLine()) - 1];
        Admin admin = new Admin();
        admin.setSuscriptor(tipo,client,true);
        System.out.println("Se ha suscrito con éxito a las naves de tipo " + tipo);
    }
}
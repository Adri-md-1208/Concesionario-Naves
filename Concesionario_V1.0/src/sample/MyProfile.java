package sample;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MyProfile {

    public boolean myAccount(Client client) throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        List<Client> clientList = admin.getClientList();
        for (Client clientToSearch: clientList) {
            if(clientToSearch.getEmail().equals(client.getEmail())) {
                client = clientToSearch;
            }
        }
        //Ya tenemos el cliente actualizado.
        System.out.println("Nombre: " + client.getName());
        System.out.println("Email: " + client.getEmail());
        System.out.println("Id: " + client.getId() + "\n");
        System.out.println("Último Acceso: " + client.getUltimoAcceso());
        System.out.println("Advertencias: " + client.getWarnings() + "\n");
        System.out.println("Suscripciones activas: ");
        if (client.isSuscritoEstacionEspacial()) {
            System.out.println("- Estaciones espaciales");
        }
        if (client.isSuscritoDestructor()) {
            System.out.println("- Destructores");
        }
        if (client.isSuscritoCarguero()) {
            System.out.println("- Cargueros");
        }
        if (client.isSuscritoCaza()) {
            System.out.println("- Cazas");
        }
        System.out.println("\nPulse cualquier tecla para salir");
        Scanner sc = new Scanner(System.in);
        sc.next();
        return true;
    }

}

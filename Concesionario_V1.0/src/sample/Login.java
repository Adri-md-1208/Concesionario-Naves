package sample;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Login {

    private Client client;

    public Client login() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Por favor, introduzca su email");
        String mail = sc.next();
        Admin admin = new Admin();
        List<Client> clientList = admin.getClientList();
        boolean found = false;
        for (Client clientSearch : clientList) {
            if (clientSearch.getEmail().equals(mail)) {
                client = clientSearch;
                found = true;
            }
        }
        if (found == false) {
            System.out.println("Este cliente no está registrado\n");
            return null;
        }
        System.out.println(client.getNick() + ", introduzca su contraseña por favor"); //nick sin comillas cuando se tenga el nick del cliente.
        for (int i = 0; i < 3; i++) {
            String password = sc.next();
            if (password.equals(client.getPassword())) {
                System.out.println("Login completado");
                return client;
            }
            System.out.println("Contraseña fallida");
        }
        System.out.println("Contraseña fallida tres veces");
        System.out.println("Login fallido\n");
        return null;
    }

}

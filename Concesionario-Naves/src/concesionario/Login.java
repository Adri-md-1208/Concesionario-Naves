package concesionario;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Login {

    private Client client;

    public Client login() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Por favor, introduzca su email");
        String mail = sc.next();
        boolean found = true;
        if (!mail.equals("Admin")){
            Admin admin = new Admin();
            List<Client> clientList = admin.getClientList();
            found = false;
            for (Client clientSearch : clientList) {
                if (clientSearch.getEmail().equals(mail)) {
                    client = clientSearch;
                    found = true;
                }
            }
        }

        if (found == false) {
            System.out.println("Este cliente no está registrado\n");
            return null;
        }
        try{
            System.out.println(client.getNick() + ", introduzca su contraseña por favor"); //nick sin comillas cuando se tenga el nick del cliente.
        }
        catch(Exception noNick){
            System.out.println("Introduzca su contraseña de admin");
        }

        for (int i = 0; i < 3; i++) {
            String password = sc.next();
            try {
                if (mail.equals(client.getEmail())) {
                    if (password.equals(client.getPassword())) {
                        System.out.println("Login completado");
                        return client;
                    }
                }
            }
            catch(Exception noClient){
                if(mail.equals("Admin")&&password.equals("Admin")){
                    System.out.println("Sesión de administrador inciada");
                    Client Admin = new Client("null","null","null",0,"null","null","",false,0,false,false,null, null);
                    return Admin;
                }
                }

                System.out.println("Contraseña fallida");
            }

        System.out.println("Contraseña fallida tres veces");
        System.out.println("Login fallido\n");
        return null;
    }
}

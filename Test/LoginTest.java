import concesionario.Admin;
import concesionario.Client;
import concesionario.Login;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
public class LoginTest {
    public Client client;
    @BeforeAll
    static void beforeAll() {
    }

    @Test
    void usuarioCorrecto() throws IOException, ClassNotFoundException {
        System.out.println("Por favor, introduzca su email");
        String mail = "alelopez@gmail.com";
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
        assertEquals(client.getNick(),"AlexLopezAdrados");
    }

    @Test
    void usuarioNoExiste() throws IOException, ClassNotFoundException {
        System.out.println("Por favor, introduzca su email");
        String mail = "AA";
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
        }
        assertFalse(found);
    }

    @Test
    void usuarioAdmin() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Por favor, introduzca su email");
        String mail = "Admin";
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
        }
        try{
            System.out.println(client.getNick() + ", introduzca su contraseña por favor"); //nick sin comillas cuando se tenga el nick del cliente.
        }
        catch(Exception noNick){
            System.out.println("Introduzca su contraseña de admin");
        }
        assertThrows(nullPoin.class,()->{
            System.out.println("ok");
        });
    }
}
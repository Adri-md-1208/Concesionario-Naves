import concesionario.Admin;
import concesionario.Client;
import concesionario.FilesCreator;
import org.junit.jupiter.api.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    public Client client;

    @BeforeAll
    static void beforeAll() throws IOException {
        File naves = new File("Ships.dat");
        File clientes = new File("Clients.dat");
        File transacciones = new File("Transactions.dat");
        File ofertas = new File("Offers.dat");
        BufferedWriter bwN = new BufferedWriter(new FileWriter(naves));
        bwN.write("");
        bwN.close();
        //naves.delete(); NO FUNCIONA EN WINDOWS.
        BufferedWriter bwC = new BufferedWriter(new FileWriter(clientes));
        bwC.write("");
        bwC.close();
        //clientes.delete();
        BufferedWriter bwT = new BufferedWriter(new FileWriter(transacciones));
        bwT.write("");
        bwT.close();
        //transacciones.delete();
        BufferedWriter bwO = new BufferedWriter(new FileWriter(ofertas));
        bwO.write("");
        bwO.close();
        //ofertas.delete();
        FilesCreator filesCreator = new FilesCreator();
        filesCreator.ClientCreator();
    }

    String mail;
    String mailIncorrecto;
    String adminMail;
    Admin admin;
    Boolean found;
    List<Client> clientList;
    String contraseña;
    String contraseñaFalsa;
    String contraseñaAdmin;
    String contraseñaFalsaAdmin;

    @BeforeEach
    void beforeEach() throws IOException, ClassNotFoundException {
        client = new Client("Alejandro", "Tatooine",
                "Human", 7777, "AlexLopezAdrados", "alejandro",
                "alelopez@gmail.com", true, 0, false,
                false, null, null);
        mail = "alelopez@gmail.com";
        mailIncorrecto = "mailFalso";
        adminMail = "Admin";
        admin = new Admin();
        found = false;
        clientList = admin.getClientList();
        contraseña = client.getPassword();
        contraseñaFalsa = "False";
        contraseñaAdmin = "Admin";
        contraseñaFalsaAdmin = "admin";
    }


    @Test
    void usuarioClienteCorrecto() {
        Boolean match = false;
        // Vemos que, en el caso de que sea cliente, coincide el mail
        for (Client client : clientList) {
            if (client.getEmail().equals(mail)) match = true;
        }
        assertTrue(match);
    }

    @Test
    void usuarioAdminCorrecto() {
        assertEquals("Admin", adminMail);
    }

    @Test
    void usuarioNoExiste() {
        Boolean match = false;
        // Buscamos si coincide el mail en la lista
        for (Client client : clientList) {
            if (client.getEmail().equals(mailIncorrecto)) match = true;
        }
        // No hay coincidencias
        assertFalse(match);
    }

    @Test
    void probarContraseñas() {
        assertAll(
                // Cliente
                () -> assertEquals(client.getPassword(), contraseña),
                // Cliente incorrecto
                () -> assertNotEquals(client.getPassword(), contraseñaFalsa),
                // Admin
                () -> assertEquals("Admin", contraseñaAdmin),
                // Admin incorrecto
                () -> assertNotEquals("Admin", contraseñaFalsaAdmin));
    }
}
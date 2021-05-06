import concesionario.Admin;
import concesionario.Client;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    public Client client;

    @BeforeAll
    static void beforeAll() {
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
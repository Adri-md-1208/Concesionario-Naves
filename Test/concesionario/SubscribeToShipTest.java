package concesionario;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SubscribeToShipTest {

    static String[] tipos;
    static Admin admin;
    static Client client;

    // Necesitamos iniciar el programa para que el archivo clientes exista
    @BeforeAll
    static void setUp() {
        tipos = new String[] {"Carguero", "Destructor", "Caza", "Estacion espacial"};
        admin = new Admin();
        client = new Client("Alejandro", "Tatooine",
                "Human", 7777, "AlexLopezAdrados", "alejandro",
                "alelopez@gmail.com", true, 0, false,
                false, null, null);
    }

    @Test
    @Disabled
    void correctSubscription() throws IOException, ClassNotFoundException {
        // Nos desuscribimos de todos los tipos
        if (client.isSuscritoCarguero()) admin.setSuscriptor("Carguero", client, false);
        if (client.isSuscritoDestructor()) admin.setSuscriptor("Destructor", client, false);
        if (client.isSuscritoCaza()) admin.setSuscriptor("Caza", client, false);
        if (client.isSuscritoEstacionEspacial()) admin.setSuscriptor("Estacion Espacial", client, false);

        // Una vez desuscritos, probamos el metodo de suscripcion con todos los tipos
        admin.setSuscriptor("Carguero", client, true);
        admin.setSuscriptor("Destructor", client, true);
        admin.setSuscriptor("Caza", client, true);
        admin.setSuscriptor("Estacion Espacial", client, true);
        assertAll(
                () -> assertTrue(client.isSuscritoCarguero()),
                () -> assertTrue(client.isSuscritoDestructor()),
                () -> assertTrue(client.isSuscritoCaza()),
                () -> assertTrue(client.isSuscritoEstacionEspacial())
        );
    }

}
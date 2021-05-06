import concesionario.Admin;
import concesionario.TestOperation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SubscribeToShipTest {

    static String[] tipos;
    static Admin admin;

    // Necesitamos iniciar el programa para que el archivo admin.getClientList().get(0)es exista
    @BeforeAll
    static void setUp() throws IOException, ClassNotFoundException {
        TestOperation testOperation = new TestOperation();
        File clientsFile = new File("Clients.dat");
        clientsFile.delete();
        admin = new Admin();
        admin.addClient(testOperation.crearCliente1Test());
        tipos = new String[] {"Carguero", "Destructor", "Caza", "Estacion Espacial"};
    }

    @Test
    void correctSubscription() throws IOException, ClassNotFoundException {
        // Nos desuscribimos de todos los tipos
        if (admin.getClientList().get(0).isSuscritoCarguero()) admin.setSuscriptor("Carguero", admin.getClientList().get(0), false);
        if (admin.getClientList().get(0).isSuscritoDestructor()) admin.setSuscriptor("Destructor", admin.getClientList().get(0), false);
        if (admin.getClientList().get(0).isSuscritoCaza()) admin.setSuscriptor("Caza", admin.getClientList().get(0), false);
        if (admin.getClientList().get(0).isSuscritoEstacionEspacial()) admin.setSuscriptor("Estacion Espacial", admin.getClientList().get(0), false);

        // Una vez desuscritos, probamos el metodo de suscripcion con todos los tipos
        admin.setSuscriptor("Carguero", admin.getClientList().get(0), true);
        admin.setSuscriptor("Destructor", admin.getClientList().get(0), true);
        admin.setSuscriptor("Caza", admin.getClientList().get(0), true);
        admin.setSuscriptor("Estacion Espacial", admin.getClientList().get(0), true);
        assertAll(
                () -> assertTrue(admin.getClientList().get(0).isSuscritoCarguero()),
                () -> assertTrue(admin.getClientList().get(0).isSuscritoDestructor()),
                () -> assertTrue(admin.getClientList().get(0).isSuscritoCaza()),
                () -> assertTrue(admin.getClientList().get(0).isSuscritoEstacionEspacial())
        );
    }
}
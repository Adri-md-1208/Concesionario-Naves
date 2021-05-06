import concesionario.Admin;
import concesionario.TestOperation;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class UnsubscribeToShipTest {

    // Necesitamos iniciar el programa para que el archivo admin.getClientList().get(0) exista

    // Objetos requeridos para las pruebas
    static Admin admin;
    static List<String> suscriptions;
    static String[] tiposNaves;

    @BeforeAll
    static void beforeAll() throws IOException {
        TestOperation testOperation = new TestOperation();
        File clientsFile = new File("Clients.dat");
        clientsFile.delete();
        admin = new Admin();
        admin.addClient(testOperation.crearCliente1Test());
        System.out.println("Prueba de la clase UnsubscribeToShip: ");
        // Iniciamos los objetos
        tiposNaves = new String[]{"Carguero", "Destructor", "Caza", "Estacion Espacial"};
        suscriptions = new ArrayList<>();
    }

    @AfterAll
    static void afterAll() {
    }

    @BeforeEach
    void beforeEach() { }

    @AfterEach
    void afterEach() throws IOException, ClassNotFoundException {
        // Borramos las suscripciones y desuscribimos al usuario
        suscriptions.clear();
        admin.getClientList().get(0).setSuscritoCarguero(false);
        admin.getClientList().get(0).setSuscritoDestructor(false);
        admin.getClientList().get(0).setSuscritoCaza(false);
        admin.getClientList().get(0).setSuscritoEstacionEspacial(false);
    }

    @Test
    void tiposNavesCorrecto() throws IOException, ClassNotFoundException {

        // Nos suscribimos a todos los tipos de naves
        admin.getClientList().get(0).setSuscritoCarguero(true);
        admin.getClientList().get(0).setSuscritoDestructor(true);
        admin.getClientList().get(0).setSuscritoCaza(true);
        admin.getClientList().get(0).setSuscritoEstacionEspacial(true);

        // En el bucle, no añadimos las suscripciones a las naves
        // De esta forma comprobamos que llegamos a todas las ramas del if
        for (String s : tiposNaves){
            if(admin.getClientList().get(0).isSuscritoCarguero()){ }
            if(admin.getClientList().get(0).isSuscritoDestructor()){ }
            if(admin.getClientList().get(0).isSuscritoCaza()){ }
            if(admin.getClientList().get(0).isSuscritoEstacionEspacial()){ }
        } //Comprobamos que efectivamente está suscrito y no está añadido a suscripciones
        assertAll(() -> assertEquals(suscriptions.isEmpty(), admin.getClientList().get(0).isSuscritoCarguero()),
                  () -> assertEquals(suscriptions.isEmpty(), admin.getClientList().get(0).isSuscritoDestructor()),
                  () -> assertEquals(suscriptions.isEmpty(), admin.getClientList().get(0).isSuscritoCaza()),
                  () -> assertEquals(suscriptions.isEmpty(), admin.getClientList().get(0).isSuscritoEstacionEspacial()));
    }

    @Test
    void todasLasOpciones() {
        String[] tiposNaves = {"Carguero", "Destructor", "Caza", "EstacionEspacial"};
        // Nos suscribimos a todos los tipos
        suscriptions.addAll(Arrays.asList(tiposNaves));
        int contador = 0;
        // Queremos ver que recorre todos los tipos
        for(String opcion : suscriptions){
            contador+=1;
        }
        // Comprobamos que ha recorrido todas las opciones
        assertEquals(contador, suscriptions.size());
    }

    @Test
    void desuscripcionCorrecta() throws IOException, ClassNotFoundException {
        Collections.addAll(suscriptions, tiposNaves);

        // Nos desuscribimos de todos los tipos
        Admin admin = new Admin();
        // Carguero
        admin.setSuscriptor(suscriptions.get(0), admin.getClientList().get(0), false);
        assertFalse(admin.getClientList().get(0).isSuscritoCarguero());
        // Destructor
        admin.setSuscriptor(suscriptions.get(1), admin.getClientList().get(0), false);
        assertFalse(admin.getClientList().get(0).isSuscritoDestructor());
        // Caza
        admin.setSuscriptor(suscriptions.get(2), admin.getClientList().get(0), false);
        assertFalse(admin.getClientList().get(0).isSuscritoCaza());
        // Estacion Espacial
        admin.setSuscriptor(suscriptions.get(3), admin.getClientList().get(0), false);
        assertFalse(admin.getClientList().get(0).isSuscritoEstacionEspacial());
    }
}
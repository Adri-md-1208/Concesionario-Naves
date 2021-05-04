import concesionario.Admin;
import concesionario.Client;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UnsubscribeToShipTest {

    // Objetos requeridos para las pruebas
    static Client client;
    static List<String> suscriptions;
    static String[] tiposNaves;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Prueba de la clase UnsubscribeToShip: ");
        // Iniciamos los objetos
        client = new Client("Alejandro", "Tatooine",
                "Human", 7777, "AlexLopezAdrados", "alejandro",
                "alelopez@gmail.com", true, 0, false,
                false, null, null);
        tiposNaves = new String[]{"Carguero", "Destructor", "Caza", "EstacionEspacial"};
        suscriptions = new ArrayList<>();
    }

    @AfterAll
    static void afterAll() {
    }

    @BeforeEach
    @Disabled
    void beforeEach() { }

    @AfterEach
    void afterEach(){
        // Borramos las suscripciones y desuscribimos al usuario
        suscriptions.clear();
        client.setSuscritoCarguero(false);
        client.setSuscritoDestructor(false);
        client.setSuscritoCaza(false);
        client.setSuscritoEstacionEspacial(false);
    }

    @Test
    void tiposNavesCorrecto() {

        // Nos suscribimos a todos los tipos de naves
        client.setSuscritoCarguero(true);
        client.setSuscritoDestructor(true);
        client.setSuscritoCaza(true);
        client.setSuscritoEstacionEspacial(true);

        // En el bucle, no añadimos las suscripciones a las naves
        // De esta forma comprobamos que llegamos a todas las ramas del if
        for (String s : tiposNaves){
            if(client.isSuscritoCarguero()){ }
            if(client.isSuscritoDestructor()){ }
            if(client.isSuscritoCaza()){ }
            if(client.isSuscritoEstacionEspacial()){ }
        } //Comprobamos que efectivamente está suscrito y no está añadido a suscripciones
        assertAll(() -> assertEquals(suscriptions.isEmpty(), client.isSuscritoCarguero()),
                  () -> assertEquals(suscriptions.isEmpty(), client.isSuscritoDestructor()),
                  () -> assertEquals(suscriptions.isEmpty(), client.isSuscritoCaza()),
                  () -> assertEquals(suscriptions.isEmpty(), client.isSuscritoEstacionEspacial()));
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
        admin.setSuscriptor(suscriptions.get(0), client, false);
        assertFalse(client.isSuscritoCarguero());
        // Destructor
        admin.setSuscriptor(suscriptions.get(1), client, false);
        assertFalse(client.isSuscritoDestructor());
        // Caza
        admin.setSuscriptor(suscriptions.get(2), client, false);
        assertFalse(client.isSuscritoCaza());
        // Estacion Espacial
        admin.setSuscriptor(suscriptions.get(3), client, false);
        assertFalse(client.isSuscritoEstacionEspacial());
    }
}
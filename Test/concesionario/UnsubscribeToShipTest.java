package concesionario;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UnsubscribeToShipTest {


    @BeforeAll
    static void beforeAll() {
        System.out.println("Prueba de la clase UnsubscribeToShip: ");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Probando método ");
    }

    @Test
    void tiposNavesCorrecto() {
        List<String> suscriptions = new ArrayList<>();
        // Creamos cliente
        Client client = new Client("Alejandro", "Tatooine",
                "Human", 7777, "AlexLopezAdrados", "alejandro",
                "alelopez@gmail.com", true, 0, false,
                false, null, null);
        String[] tiposNaves = {"Carguero", "Destructor", "Caza", "EstacionEspacial"};

        // Nos suscribimos a todos los tipos de naves
        client.setSuscritoCarguero(true);
        client.setSuscritoDestructor(true);
        client.setSuscritoCaza(true);
        client.setSuscritoEstacionEspacial(true);

        // En el bucle, no añadimos las suscripciones a las naves
        for (String s : tiposNaves){
            if(client.isSuscritoCarguero()){

            }
            if(client.isSuscritoDestructor()){

            }
            if(client.isSuscritoCaza()){

            }
            if(client.isSuscritoEstacionEspacial()){
            }
            //Comprobamos que efectivamente está suscrito y no está añadido a suscripciones
            assertEquals(suscriptions.isEmpty(), client.isSuscritoCarguero());
            assertEquals(suscriptions.isEmpty(), client.isSuscritoDestructor());
            assertEquals(suscriptions.isEmpty(), client.isSuscritoCaza());
            assertEquals(suscriptions.isEmpty(), client.isSuscritoEstacionEspacial());
        }
    }

    @Test
    void todasLasOpciones() {
        List<String> suscriptions = new LinkedList<>();
        String[] tiposNaves = {"Carguero", "Destructor", "Caza", "EstacionEspacial"};
        for (String s : tiposNaves){
            suscriptions.add(s); // Nos suscribimos a todos los tipos
        }
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
        Client client = new Client("Alejandro", "Tatooine",
                "Human", 7777, "AlexLopezAdrados", "alejandro",
                "alelopez@gmail.com", true, 0, false,
                false, null, null);
        List<String> suscriptions = new LinkedList<>();
        String[] tiposNaves = {"Carguero", "Destructor", "Caza", "EstacionEspacial"};
        for (String s : tiposNaves){
            suscriptions.add(s); // Nos suscribimos a todos los tipos
        }

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



















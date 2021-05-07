import concesionario.Admin;
import concesionario.FilesCreator;
import concesionario.TestOperation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyRatingsTest {

    @BeforeAll
    static void beforeAll() throws IOException, ClassNotFoundException {
        TestOperation testOperation = new TestOperation();
        //testOperation.reinicioFicheros();
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
        FilesCreator files = new FilesCreator();
        files.ClientCreator();
        files.ShipCreator();
        testOperation.creadorOfertas();
        testOperation.comprarOfertas();
    }

    @Test
    void valoracionCorrectaClient1() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        //Cogemos los comentarios del Cliente 1 y nos quedamos con la valoración por el mismo proceso que se realiza en MyRatings.
        List<String> comentarios = admin.getClientList().get(1).getComments();
        int valoracion = Integer.parseInt(comentarios.get(0).substring(0,1));
        assertEquals(valoracion,3);
    }

    @Test
    void valoracionCorrectaClient5() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        //Cogemos los comentarios del Cliente 5 y nos quedamos con la valoración por el mismo proceso que se realiza en MyRatings.
        List<String> comentarios = admin.getClientList().get(4).getComments();
        int valoracion = Integer.parseInt(comentarios.get(0).substring(0,1));
        assertEquals(valoracion,5);
    }

    @Test
    void comentarioCorrectoClient1() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        //Cogemos los comentarios del Cliente 1 y nos quedamos con el comentario por el mismo proceso que se realiza en MyRatings.
        List<String> comentarios = admin.getClientList().get(1).getComments();
        String comentario = comentarios.get(0).substring(1,comentarios.get(0).length());
        assertEquals(comentario,"Experiencia bastante mejorable");
    }

    @Test
    void comentarioCorrectoClient5() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        //Cogemos los comentarios del Cliente 5 y nos quedamos con el comentario por el mismo proceso que se realiza en MyRatings.
        List<String> comentarios = admin.getClientList().get(4).getComments();
        String comentario = comentarios.get(0).substring(1,comentarios.get(0).length());
        assertEquals(comentario,"Inmejorable, muy recomendable");
    }

    @Test
    void variosComentarios() throws IOException, ClassNotFoundException {
        //Como el Cliente 1 tiene dos comentarios, veamos que la lista de comentarios es del tamaño adecuado.
        Admin admin = new Admin();
        List<String> comentarios = admin.getClientList().get(0).getComments();
        assertEquals(comentarios.size(),2);
    }

    @Test
    void mediaCorrecta() throws IOException, ClassNotFoundException {
        //Como el Cliente 1 tiene dos comentario, veamos que la media se efectúa de manera correcta.
        Admin admin = new Admin();
        List<String> comentarios = admin.getClientList().get(0).getComments();
        //Guardamos las valoraciones (igual forma que en MyRatings).
        List<Integer> valoraciones = new ArrayList<>();
        for(String comentario : admin.getClientList().get(0).getComments()){
            int valoracion = Integer.parseInt(comentario.substring(0,1));
            valoraciones.add(valoracion);
        }
        //Calculamos media (igual forma que en MyRatings).
        double media = 0;
        for (Integer rating : valoraciones) {
            media += rating;
        }
        media = media / valoraciones.size();
        //Vemos si el cálculo es correcto.
        assertEquals(media,4.5,0);
    }

}

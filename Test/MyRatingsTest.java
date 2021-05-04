import concesionario.Admin;
import concesionario.TestOperation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyRatingsTest {

    @BeforeAll
    static void beforeAll() throws IOException, ClassNotFoundException {
        TestOperation testOperation = new TestOperation();
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

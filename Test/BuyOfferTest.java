import concesionario.Admin;
import concesionario.Nave;
import concesionario.Offer;
import concesionario.TestOperation;
import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;

public class BuyOfferTest {

    @BeforeAll
    static void beforeAll() throws IOException, ClassNotFoundException {
        TestOperation testOperation = new TestOperation();
        testOperation.reinicioFicheros();
        testOperation.creadorOfertas();
    }

    @Test
    void mostrarOfertasTipo1SinFiltros() throws IOException, ClassNotFoundException {
        //Tras ejecutarse el método de testOperation correspondiente, ofertasVálidas debe guardar las ofertas con Carguero y que
        //no sean del Client2. Éstas son 1 (ver en el método testOperation.creadorOfertas las ofertas que se crean).
        TestOperation testOperation = new TestOperation();
        System.out.println("Ofertas para búsqueda de Guillermo - Tipo 1 ");
        List<Offer> ofertasVálidas = testOperation.verOfertasTipo1SinFiltros();
        assertEquals(ofertasVálidas.size(),1);
    }

    @Test
    void mostrarOfertasTipo2SinFiltros() throws IOException, ClassNotFoundException {
        //Tras ejecutarse el método de testOperation correspondiente, ofertasVálidas debe guardar las ofertas con Carguero y/o Cazas y que
        //no sean del Client5. Éstas son 3 (ver en el método testOperation.creadorOfertas las ofertas que se crean).
        TestOperation testOperation = new TestOperation();
        System.out.println("Ofertas para búsqueda de Kromi - Tipo 2 ");
        List<Offer> ofertasVálidas = testOperation.verOfertasTipo2SinFiltros();
        assertEquals(ofertasVálidas.size(),3);
    }

    @Test
    void mostrarOfertasTipo2ConFiltros() throws IOException, ClassNotFoundException {
        //Tras ejecutarse el método de testOperation correspondiente, ofertasVálidas debe guardar las ofertas con Carguero y/o Cazas, que
        //no sean del Client5 y que cumplan tener un Carguero en su interior (filtro añadido).
        //Éstas son 2 (ver en el método testOperation.creadorOfertas las ofertas que se crean).
        TestOperation testOperation = new TestOperation();
        System.out.println("Ofertas para búsqueda de Kromi - Tipo 2 - Filtro para Carguero");
        List<Offer> ofertasVálidas = testOperation.verOfertasTipo2ConFiltros();
        assertEquals(ofertasVálidas.size(),2);
    }

    @Test
    void mostrarOfertasTipo3SinFiltros() throws IOException, ClassNotFoundException {
        //Tras ejecutarse el método de testOperation correspondiente, ofertasVálidas debe guardar las ofertas con cualquier nave y que
        //no sean del Client4. Éstas son 3 (ver en el método testOperation.creadorOfertas las ofertas que se crean).
        TestOperation testOperation = new TestOperation();
        System.out.println("Ofertas para búsqueda de Adrián - Tipo 3 ");
        List<Offer> ofertasVálidas = testOperation.verOfertasTipo3SinFiltros();
        assertEquals(ofertasVálidas.size(),3);
    }

    @Test
    void mostrarOfertasTipo3ConFiltros() throws IOException, ClassNotFoundException {
        //Tras ejecutarse el método de testOperation correspondiente, ofertasVálidas debe guardar las ofertas que
        //no sean del Client5 y que cumplan tener un Destructor en su interior (2 filtros añadidos).
        //Éstas son 1 (ver en el método testOperation.creadorOfertas las ofertas que se crean).
        TestOperation testOperation = new TestOperation();
        System.out.println("Ofertas para búsqueda de Adrián - Tipo 3 - Filtro para Destructor");
        List<Offer> ofertasVálidas = testOperation.verOfertasTipo3ConFiltros();
        assertEquals(ofertasVálidas.size(),1);
    }

    @Test
    void naveCambiaDeDueño() throws IOException, ClassNotFoundException {
        //Este test nos servirá para comprobar que la nave, una vez vendida, no sigue perteneciendo al vendedor (Client1).

        //En primer lugar, conseguimos el número de naves del Cliente 1 ántes de la compra.
        TestOperation testOperation = new TestOperation();
        Admin admin = new Admin();
        List<Offer> ofertas = admin.getPublishedOffers();
        List<Nave> navesClient1PreCompra = new ArrayList<>();
        for (Offer oferta : ofertas) {
            List<String> navesOferta = oferta.getNaves();
            for (String numRegNave : navesOferta) {
                Nave nave = admin.searchShip(numRegNave);
                if (nave.getPropietario().equals(admin.getClientList().get(0))) {
                    navesClient1PreCompra.add(nave);
                }
            }
        }

        //En segundo lugar, se realiza la compra.
        testOperation.comprarUnaOferta();

        //En tercer lugar, conseguimos el número de naves del Cliente 1 después de la compra.
        List<Nave> navesClient1PostCompra = new ArrayList<>();
        for (Offer oferta : ofertas) {
            List<String> navesOferta = oferta.getNaves();
            for (String numRegNave : navesOferta) {
                Nave nave = admin.searchShip(numRegNave);
                if (nave.getPropietario().equals(admin.getClientList().get(0))) {
                    navesClient1PostCompra.add(nave);
                }
            }
        }

        //Reinicio de ficheros para no romper el resto de test.
        testOperation.reinicioFicheros();
        testOperation.creadorOfertas();
        //El número de naves del Cliente 1 ántes de la compra menos 1 tiene que ser igual al número de naves del Cliente 1 después de la compra.
        assertTrue(navesClient1PreCompra.size() == navesClient1PostCompra.size());
    }
}

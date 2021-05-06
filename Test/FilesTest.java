import concesionario.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class FilesTest {


    @Test
    void insercionYLecturaDeNaves() throws IOException, ClassNotFoundException {
        TestOperation test = new TestOperation();
        Admin admin = new Admin();
        admin.addShip(test.crearNave1Test());
        admin.addShip(test.crearNave2Test());
        List<Nave> lista= admin.getShipList();
        Boolean correcto1 = false;
        Boolean correcto2 = false;
        for (Nave nave : lista){
            if(nave.getNumeroRegistro().equals("numTest")){
                correcto1 = true;
            }
            if(nave.getNumeroRegistro().equals("Test2")){
                correcto2 = true;
            }
        }
        assertTrue(correcto1&&correcto2);
    }

    @Test
    void modificacionDeNaves() throws IOException, ClassNotFoundException {
        TestOperation test = new TestOperation();
        Admin admin = new Admin();
        admin.addShip(test.crearNave1Test());
        admin.addShip(test.crearNave2Test());
        List<Nave> lista= admin.getShipList();
        Boolean correcto1 = false;
        Boolean correcto2 = false;
        admin.changePropietario(lista.get(0),test.crearCliente1Test());//Cambiar método
        lista = admin.getShipList();
        for (Nave nave : lista){
            if(nave.getNumeroRegistro().equals("numTest")){
                correcto1 = nave.getPropietario().getName().equals("TestName1");
            }
        }
        assertTrue(correcto1);
    }

    @Test
    void insercionYLecturaDeClientes() throws IOException, ClassNotFoundException {
        TestOperation test = new TestOperation();
        Admin admin = new Admin();
        admin.addClient(test.crearCliente1Test());
        admin.addClient(test.crearCliente2Test());
        List <Client> lista = admin.getClientList();
        Boolean correcto1 = false;
        Boolean correcto2 = false;
        for (Client cliente : lista){
            if(cliente.getName().equals("TestName1")){
                correcto1 = true;
            }
            if(cliente.getName().equals("TestName2")){
                correcto2 = true;
            }
        }
        assertTrue(correcto1&&correcto2);
    }

    @Test
    void modificacionDeClientes() throws IOException, ClassNotFoundException {
        TestOperation test = new TestOperation();
        Admin admin = new Admin();
        admin.addClient(test.crearCliente1Test());
        admin.addClient(test.crearCliente2Test());
        List <Client> lista = admin.getClientList();
        Boolean correcto1 = false;
        Boolean correcto2 = false;
        admin.addWarningSanction(test.crearCliente1Test());
        admin.addFraudSanction(test.crearCliente2Test());
        lista = admin.getClientList();
        for (Client cliente : lista){
            if(cliente.getName().equals("TestName1")){
                correcto1 = cliente.getWarnings()==1;
            }
            if(cliente.getName().equals("TestName2")){
                correcto2 = cliente.isFraud();
            }
        }
        assertTrue(correcto1&&correcto2);
    }

    @Test
    void inserciónYLecturaDeOfertas() throws IOException, ClassNotFoundException {
        TestOperation test = new TestOperation();
        Admin admin = new Admin();
        admin.addOffer(test.crearOferta1Test());
        admin.addOffer(test.crearOferta2Test());
        List<Offer> lista= admin.getUnpublishedOffers();
        Boolean correcto1 = false;
        Boolean correcto2 = false;
        for (Offer oferta : lista){
            if(oferta.getDescription().equals("Test1")){
                correcto1 = true;
            }
            if(oferta.getDescription().equals("Test2")){
                correcto2 = true;
            }
        }
        assertTrue(correcto1&&correcto2);
    }

    @Test
    void modificacionDeOfertas() throws IOException, ClassNotFoundException {
        TestOperation test = new TestOperation();
        Admin admin = new Admin();
        admin.addOffer(test.crearOferta1Test());
        admin.addOffer(test.crearOferta2Test());
        List <Offer> lista = admin.getUnpublishedOffers();
        Boolean correcto1 = false;
        Boolean correcto2 = false;
        admin.modifyOfferVisibility(lista.get(0),true);
        lista = admin.getUnpublishedOffers();
        List <Offer> lista2 = admin.getPublishedOffers();
        for (Offer ofertaPublicada : lista2) {
            if (ofertaPublicada.getDescription().equals("Test1")) {
                correcto1 = ofertaPublicada.isPublished();
            }
        }
            for (Offer ofertaSinPublicar : lista){
                if(ofertaSinPublicar.getDescription().equals("Test2")){
                    correcto2 = !ofertaSinPublicar.isPublished();
                }
            }
        assertTrue(correcto1&&correcto2);
    }

    @Test
    void borrarOfertaTest() throws IOException, ClassNotFoundException {
        TestOperation test = new TestOperation();
        Admin admin = new Admin();
        admin.addOffer(test.crearOferta1Test());
        admin.addOffer(test.crearOferta2Test());
        List <Offer> lista = admin.getUnpublishedOffers();
        Boolean correcto1 = false;
        Boolean correcto2 = false;
        admin.modifyOfferVisibility(lista.get(0),true);
        lista = admin.getUnpublishedOffers();
        List <Offer> lista2 = admin.getPublishedOffers();
        for (Offer ofertaPublicada : lista2) {
            if (ofertaPublicada.getDescription().equals("Test1")) {
                correcto1 = ofertaPublicada.isPublished();
            }
        }
        for (Offer ofertaSinPublicar : lista){
            if(ofertaSinPublicar.getDescription().equals("Test2")){
                correcto2 = !ofertaSinPublicar.isPublished();
            }
        }
        admin.deleteOffer(lista.get(0));
        Boolean correcto3 = admin.getUnpublishedOffers().isEmpty();
        Boolean correcto4 = !admin.getPublishedOffers().isEmpty();
        assertTrue(correcto1&&correcto2&&correcto3&&correcto4);
    }

    @Test
    void inserciónYLecturaDeTransaccion() throws IOException, ClassNotFoundException {
        TestOperation test = new TestOperation();
        Admin admin = new Admin();
        admin.addTransaction(test.crearTransaccion1Test());
        List<Transaction> lista= admin.getIndividualTransaction(test.crearCliente1Test());
        Boolean correcto1 = false;
        for (Transaction transaccion : lista){
            if(transaccion.getOffer().getDescription().equals("Test1")){
                correcto1 = true;
            }
        }
        assertTrue(correcto1);
    }

    @BeforeAll
    static void beforeAll() {
        File naves = new File("Ships.dat");
        File  clientes = new File("Clients.dat");
        File transacciones = new File("Transactions.dat");
        File ofertas = new File("Offers.dat");
        naves.delete();
        clientes.delete();
        transacciones.delete();
        ofertas.delete();

    }

    @BeforeEach
    void setUp() {
        File naves = new File("Ships.dat");
        File  clientes = new File("Clients.dat");
        File transacciones = new File("Transactions.dat");
        File ofertas = new File("Offers.dat");
        naves.delete();
        clientes.delete();
        transacciones.delete();
        ofertas.delete();

    }
}

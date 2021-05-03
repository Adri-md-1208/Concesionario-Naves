package concesionario;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TestOperation {

    public void creadorOfertas() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        Calendar calendario = new GregorianCalendar();
        Date fechaPublicacion = new Date(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.HOUR_OF_DAY), calendario.get(Calendar.MINUTE), calendario.get(Calendar.SECOND));

        //Reinicio de ficheros
        File clientsFile = new File("Clients.dat");
        File shipsFile = new File("Ships.dat");
        File offersFile = new File("Offers.dat");
        File transactionsFile = new File("Transactions.dat");
        if (clientsFile.exists()) {
            clientsFile.delete();
            FilesCreator filesCreator = new FilesCreator();
            filesCreator.ClientCreator();
        }
        if (shipsFile.exists()) {
            shipsFile.delete();
            FilesCreator filesCreator = new FilesCreator();
            filesCreator.ShipCreator();
        }
        if (offersFile.exists()) {
            offersFile.delete();
        }
        if (transactionsFile.exists()) {
            transactionsFile.delete();
        }

        //Creamos Oferta 1
        ArrayList<String> navesOferta1 = new ArrayList<>();
        navesOferta1.add(admin.getShipList().get(0).getNumeroRegistro());   //Client1
        navesOferta1.add(admin.getShipList().get(2).getNumeroRegistro());   //Client1
        Date fechaLímite1 = new Date(2025, 3, 25);
        Offer oferta1 = new Offer("Test1", 2500, fechaLímite1, false, false, navesOferta1, fechaPublicacion);
        admin.addOffer(oferta1);

        //Creamos Oferta 2
        ArrayList<String> navesOferta2 = new ArrayList<>();
        navesOferta2.add(admin.getShipList().get(1).getNumeroRegistro());   //Client2
        navesOferta2.add(admin.getShipList().get(4).getNumeroRegistro());   //Client2
        Date fechaLímite2 = new Date(2024, 4, 24);
        Offer oferta2 = new Offer("Test2", 5000, fechaLímite2, false, false, navesOferta2, fechaPublicacion);
        admin.addOffer(oferta2);

        //Creamos Oferta 3
        ArrayList<String> navesOferta3 = new ArrayList<>();
        navesOferta3.add(admin.getShipList().get(6).getNumeroRegistro());   //Client4
        Date fechaLímite3 = new Date(2030, 3, 23);
        Offer oferta3 = new Offer("Test3", 7777, fechaLímite3, false, false, navesOferta3, fechaPublicacion);
        admin.addOffer(oferta3);

        //Creamos Oferta 4
        ArrayList<String> navesOferta4 = new ArrayList<>();
        navesOferta4.add(admin.getShipList().get(3).getNumeroRegistro());   //Client3
        Date fechaLímite4 = new Date(2026, 1, 24);
        Offer oferta4 = new Offer("Test4", 4, fechaLímite2, false, false, navesOferta4, fechaPublicacion);
        admin.addOffer(oferta4);

        //Revisamos las ofertas
        List<Offer> listaOfertas = admin.getUnpublishedOffers();
        admin.modifyOfferVisibility(listaOfertas.get(0), true);
        admin.modifyOfferVisibility(listaOfertas.get(1), true);
        admin.modifyOfferVisibility(listaOfertas.get(2), true);
        admin.modifyOfferVisibility(listaOfertas.get(3), true);
    }

    public void comprarTresOfertas() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        Calendar calendario = new GregorianCalendar();
        Date fechaPublicacion = new Date(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.HOUR_OF_DAY), calendario.get(Calendar.MINUTE), calendario.get(Calendar.SECOND));

        //Compramos Oferta 1
        Transaction transaction1 = new Transaction(admin.getClientList().get(3).getEmail(), admin.getClientList().get(0).getEmail(), admin.getPublishedOffers().get(0), fechaPublicacion);
        admin.addTransaction(transaction1);
        //Comentario y Valoración 1
        String valoracion1 = "4Fue puntual y bastante majo, pero tardaba en responder";
        admin.addComment(admin.getClientList().get(0), valoracion1);

        //Compramos Oferta 2
        Transaction transaction2 = new Transaction(admin.getClientList().get(4).getEmail(), admin.getClientList().get(1).getEmail(), admin.getPublishedOffers().get(1), fechaPublicacion);
        admin.addTransaction(transaction2);
        //Comentario y Valoración 2
        String valoracion2 = "3Experiencia bastante mejorable";
        admin.addComment(admin.getClientList().get(1), valoracion2);

        //Compramos Oferta 3
        Transaction transaction3 = new Transaction(admin.getClientList().get(0).getEmail(), admin.getClientList().get(4).getEmail(), admin.getPublishedOffers().get(2), fechaPublicacion);
        admin.addTransaction(transaction3);
        //Comentario y Valoración 3
        String valoracion3 = "5Inmejorable, muy recomendable";
        admin.addComment(admin.getClientList().get(4), valoracion3);
    }

    public Nave crearNave1Test(){
        List<String> comentarios = new ArrayList<>();
        Client client1 = new Client("TestName", "TestPlanet",
                "TestSpecie", 0001, "TestNick", "testPassword",
                "TestEmail", true, 0, false, false, null,comentarios);
        Propulsion propulsion1 = new Propulsion(Propulsion.TipoPropulsor.Compresor_de_traza, 2000);
        Propulsion[] motor1=new Propulsion[2];
        motor1[0]=propulsion1;
        Defensa defensa1 = new Escudo(1000, 3000);
        Defensa[] defensas1 = new Defensa[1];
        defensas1[0]=defensa1;
        Arma arma1 = new Arma(Arma.TipoArma.Cañón_Blaster, 2500);
        Arma[] armas1 = new Arma[2];
        armas1[0]= arma1;
        Creator destCreator = new DestructorCreator();
        Nave nave1 = destCreator.crearNave("numTest", client1, motor1, 1000, null, 0, defensas1, armas1, 0, null);
        return nave1;
    }

    public Nave crearNave2Test(){
        List<String> comentarios = new ArrayList<>();
        Client client1 = new Client("TestName", "TestPlanet",
                "TestSpecie", 0001, "TestNick", "testPassword",
                "TestEmail", true, 0, false, false, null,comentarios);
        Propulsion propulsion1 = new Propulsion(Propulsion.TipoPropulsor.Compresor_de_traza, 2000);
        Propulsion[] motor1=new Propulsion[2];
        motor1[0]=propulsion1;
        Defensa defensa1 = new Escudo(1000, 3000);
        Defensa[] defensas1 = new Defensa[1];
        defensas1[0]=defensa1;
        Arma arma1 = new Arma(Arma.TipoArma.Cañón_Blaster, 2500);
        Arma[] armas1 = new Arma[2];
        armas1[0]= arma1;
        Creator destCreator = new DestructorCreator();
        Nave nave1 = destCreator.crearNave("Test2", client1, motor1, 1000, null, 0, defensas1, armas1, 0, null);
        return nave1;
    }
    public Client crearCliente1Test(){
        List<String> comentarios = new ArrayList<>();
        Client client1 = new Client("TestName1", "TestPlanet1",
                "TestSpecie1", 0001, "TestNick1", "testPassword1",
                "TestEmail1", true, 0, false, false, null,comentarios);
        return client1;
    }
    public Client crearCliente2Test(){
        List<String> comentarios = new ArrayList<>();
        Client client1 = new Client("TestName2", "TestPlanet2",
                "TestSpecie2", 0002, "TestNick2", "testPassword2",
                "TestEmail2", false, 2, false, false, null,comentarios);
        return client1;
    }
}


package concesionario;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TestOperation {

    public void reinicioFicheros() throws  IOException, ClassNotFoundException {
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
    }

    public void creadorOfertas() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        Calendar calendario = new GregorianCalendar();
        Date fechaPublicacion = new Date(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.HOUR_OF_DAY), calendario.get(Calendar.MINUTE), calendario.get(Calendar.SECOND));

        //Creamos Oferta 1
        ArrayList<String> navesOferta1 = new ArrayList<>();
        navesOferta1.add(admin.getShipList().get(0).getNumeroRegistro());   //Client1 (Alejandro 3) - Destructor
        Date fechaLímite1 = new Date(2025, 3, 25);
        Offer oferta1 = new Offer("Test1", 2500, fechaLímite1, false, false, navesOferta1, fechaPublicacion);
        admin.addOffer(oferta1);

        //Creamos Oferta 2
        ArrayList<String> navesOferta2 = new ArrayList<>();
        navesOferta2.add(admin.getShipList().get(1).getNumeroRegistro());   //Client2 (Guillermo 1) - Carguero
        navesOferta2.add(admin.getShipList().get(4).getNumeroRegistro());   //Client2 (Guillermo 1) - Caza
        Date fechaLímite2 = new Date(2024, 4, 24);
        Offer oferta2 = new Offer("Test2", 5000, fechaLímite2, false, false, navesOferta2, fechaPublicacion);
        admin.addOffer(oferta2);

        //Creamos Oferta 3
        ArrayList<String> navesOferta3 = new ArrayList<>();
        navesOferta3.add(admin.getShipList().get(6).getNumeroRegistro());   //Client4 (Adrián 3) - Carguero
        Date fechaLímite3 = new Date(2030, 3, 23);
        Offer oferta3 = new Offer("Test3", 7777, fechaLímite3, false, false, navesOferta3, fechaPublicacion);
        admin.addOffer(oferta3);

        //Creamos Oferta 4
        ArrayList<String> navesOferta4 = new ArrayList<>();
        navesOferta4.add(admin.getShipList().get(2).getNumeroRegistro());   //Client1 (Alejandro 3) - Caza
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

    public void comprarOfertas() throws IOException, ClassNotFoundException {
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

        //Compramos Oferta 4
        Transaction transaction4 = new Transaction(admin.getClientList().get(1).getEmail(), admin.getClientList().get(0).getEmail(), admin.getPublishedOffers().get(3), fechaPublicacion);
        admin.addTransaction(transaction4);
        //Comentario y Valoración 3
        String valoracion4 = "5Producto en perfecto estado";
        admin.addComment(admin.getClientList().get(0), valoracion4);
    }

    public List<Offer> verOfertasTipo1SinFiltros() throws IOException, ClassNotFoundException {
        //Este método copiado de verOfertas en BuyOffer guardará en ofertasVálidas las ofertas disponibles para el Client2,
        //que es de Tipo 1, sin añadir filtros. El resultado debería ser sólo la Oferta 3 (Carguero de Adrián).
        //Inicializamos variables necesarias
        List<Offer> ofertasVálidas = new ArrayList<>();
        List<Nave> navesTotales = new ArrayList<>();
        boolean ofertaVálida = false;
        boolean navesVálidas = false;
        List<String> validNaves = new ArrayList<>();
        boolean containsFilter = false;
        int contador = 1;
        boolean contadorEscrito = false;
        Admin admin = new Admin();
        List<Offer> listaOfertas = admin.getPublishedOffers();
        List<String> filterList = new ArrayList<>();
        filterList.add("Destructor");
        filterList.add("Carguero");
        filterList.add("Caza");
        filterList.add("EstacionEspacial");
        //Case 2
        for (Offer oferta : listaOfertas) {
            ofertaVálida = false;
            List<String> numRegTotales = oferta.getNaves();
            for (String numReg : numRegTotales) {
                navesTotales.add(admin.searchShip(numReg));
            }
            for (Nave nave : navesTotales) {
                if (nave.getClass().getSimpleName().equals("Caza") || nave.getClass().getSimpleName().equals("Destructor") || nave.getClass().getSimpleName().equals("EstacionEspacial")) {
                    navesVálidas = false;
                }
            }
            if (navesVálidas) {
                if (!navesTotales.get(0).getPropietario().getEmail().equals(admin.getClientList().get(1).getEmail())) {
                    System.out.println("Oferta " + contador + ":");
                    //El forEach solo coge ofertas distintas a null.
                    for (Nave nave : navesTotales) {
                        if (nave.getClass().getSimpleName().equals("Carguero")) {
                            System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                    ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                            ofertaVálida = true;
                        }
                    }
                    if (ofertaVálida) {
                        ofertasVálidas.add(oferta);
                        System.out.println("Precio: " + oferta.getPrize());
                        contador++;
                    }
                }
            }
            navesVálidas = true;
            navesTotales = new ArrayList<>();
        }
        return ofertasVálidas;
    }

    public List<Offer> verOfertasTipo2SinFiltros() throws IOException, ClassNotFoundException {
        //Este método copiado de verOfertas en BuyOffer guardará en ofertasVálidas las ofertas disponibles para el Client5,
        //que es de Tipo 2, sin añadir filtros. El resultado debería ser la Oferta 2, 3 y 4 (La 1 contiene un Destructor).
        //Inicializamos variables necesarias
        List<Offer> ofertasVálidas = new ArrayList<>();
        List<Nave> navesTotales = new ArrayList<>();
        boolean ofertaVálida = false;
        boolean navesVálidas = false;
        List<String> validNaves = new ArrayList<>();
        boolean containsFilter = false;
        int contador = 1;
        boolean contadorEscrito = false;
        Admin admin = new Admin();
        List<Offer> listaOfertas = admin.getPublishedOffers();
        List<String> filterList = new ArrayList<>();
        filterList.add("Destructor");
        filterList.add("Carguero");
        filterList.add("Caza");
        filterList.add("EstacionEspacial");
        //Case 2
        for (Offer oferta : listaOfertas) {
            ofertaVálida = false;
            List<String> numRegTotales = oferta.getNaves();
            for (String numReg : numRegTotales) {
                navesTotales.add(admin.searchShip(numReg));
            }
            for (Nave nave : navesTotales) {
                if (nave.getClass().getSimpleName().equals("Destructor") || nave.getClass().getSimpleName().equals("EstacionEspacial")) {
                    navesVálidas = false;
                }
                if (filterList.contains(nave.getClass().getSimpleName())) {
                    containsFilter = true;
                }
            }
            if (navesVálidas) {
                if (!navesTotales.get(0).getPropietario().getEmail().equals(admin.getClientList().get(4).getEmail())) {
                    //El forEach solo coge ofertas distintas a null.
                    for (Nave nave : navesTotales) {
                        if ((filterList.contains("Carguero") && nave.getClass().getSimpleName().equals("Carguero")) || (containsFilter && nave.getClass().getSimpleName().equals("Carguero"))) {
                            if (!contadorEscrito) {
                                System.out.println("Oferta " + contador + ":");
                                contadorEscrito = true;
                            }
                            System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                    ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                            ofertaVálida = true;
                            containsFilter = true;
                        }
                        if ((filterList.contains("Caza") && (nave.getClass().getSimpleName().equals("Caza"))) || (containsFilter && nave.getClass().getSimpleName().equals("Caza"))) {
                            if (!contadorEscrito) {
                                System.out.println("Oferta " + contador + ":");
                                contadorEscrito = true;
                            }
                            System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                    ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                            ofertaVálida = true;
                            containsFilter = true;
                        }
                    }

                    if (ofertaVálida) {
                        ofertasVálidas.add(oferta);
                        System.out.println("Precio: " + oferta.getPrize());
                        contador++;
                    }
                }
            }
            navesVálidas = true;
            containsFilter = false;
            contadorEscrito = false;
            navesTotales = new ArrayList<>();
        }
        return ofertasVálidas;
    }

    public List<Offer> verOfertasTipo2ConFiltros() throws IOException, ClassNotFoundException {
        //Este método copiado de verOfertas en BuyOffer guardará en ofertasVálidas las ofertas disponibles para el Client5,
        //que es de Tipo 2, añadiendo un filtro para Cargueros.
        //El resultado debería ser la Oferta 2 y la 3 (Tienen un carguero en su interior).
        //Inicializamos variables necesarias
        List<Offer> ofertasVálidas = new ArrayList<>();
        List<Nave> navesTotales = new ArrayList<>();
        boolean ofertaVálida = false;
        boolean navesVálidas = false;
        List<String> validNaves = new ArrayList<>();
        boolean containsFilter = false;
        int contador = 1;
        boolean contadorEscrito = false;
        Admin admin = new Admin();
        List<Offer> listaOfertas = admin.getPublishedOffers();
        List<String> filterList = new ArrayList<>();
        filterList.add("Carguero");
        //Case 2
        for (Offer oferta : listaOfertas) {
            ofertaVálida = false;
            List<String> numRegTotales = oferta.getNaves();
            for (String numReg : numRegTotales) {
                navesTotales.add(admin.searchShip(numReg));
            }
            for (Nave nave : navesTotales) {
                if (nave.getClass().getSimpleName().equals("Destructor") || nave.getClass().getSimpleName().equals("EstacionEspacial")) {
                    navesVálidas = false;
                }
                if (filterList.contains(nave.getClass().getSimpleName())) {
                    containsFilter = true;
                }
            }
            if (navesVálidas) {
                if (!navesTotales.get(0).getPropietario().getEmail().equals(admin.getClientList().get(4).getEmail())) {
                    //El forEach solo coge ofertas distintas a null.
                    for (Nave nave : navesTotales) {
                        if ((filterList.contains("Carguero") && nave.getClass().getSimpleName().equals("Carguero")) || (containsFilter && nave.getClass().getSimpleName().equals("Carguero"))) {
                            if (!contadorEscrito) {
                                System.out.println("Oferta " + contador + ":");
                                contadorEscrito = true;
                            }
                            System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                    ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                            ofertaVálida = true;
                            containsFilter = true;
                        }
                        if ((filterList.contains("Caza") && (nave.getClass().getSimpleName().equals("Caza"))) || (containsFilter && nave.getClass().getSimpleName().equals("Caza"))) {
                            if (!contadorEscrito) {
                                System.out.println("Oferta " + contador + ":");
                                contadorEscrito = true;
                            }
                            System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                    ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                            ofertaVálida = true;
                            containsFilter = true;
                        }
                    }

                    if (ofertaVálida) {
                        ofertasVálidas.add(oferta);
                        System.out.println("Precio: " + oferta.getPrize());
                        contador++;
                    }
                }
            }
            navesVálidas = true;
            containsFilter = false;
            contadorEscrito = false;
            navesTotales = new ArrayList<>();
        }
        return ofertasVálidas;
    }

    public List<Offer> verOfertasTipo3SinFiltros() throws IOException, ClassNotFoundException {
        //Este método copiado de verOfertas en BuyOffer guardará en ofertasVálidas las ofertas disponibles para el Client4,
        //que es de Tipo 3, sin añadir filtros. El resultado debería ser la Oferta 1,2 y 4 (La 3 es de él mismo).
        //Inicializamos variables necesarias
        List<Offer> ofertasVálidas = new ArrayList<>();
        List<Nave> navesTotales = new ArrayList<>();
        boolean ofertaVálida = false;
        boolean navesVálidas = false;
        List<String> validNaves = new ArrayList<>();
        boolean containsFilter = false;
        int contador = 1;
        boolean contadorEscrito = false;
        Admin admin = new Admin();
        List<Offer> listaOfertas = admin.getPublishedOffers();
        List<String> filterList = new ArrayList<>();
        filterList.add("Destructor");
        filterList.add("Carguero");
        filterList.add("Caza");
        filterList.add("EstacionEspacial");
        //Case 3
        for (Offer oferta : listaOfertas) {
            ofertaVálida = false;
            List<String> numRegTotales = oferta.getNaves();
            for (String numReg : numRegTotales) {
                navesTotales.add(admin.searchShip(numReg));
            }
            for (Nave nave : navesTotales) {
                if (filterList.contains(nave.getClass().getSimpleName())) {
                    containsFilter = true;
                }
            }
            if (!navesTotales.get(0).getPropietario().getEmail().equals(admin.getClientList().get(3).getEmail())) {
                //El forEach solo coge ofertas distintas a null.
                for (Nave nave : navesTotales) {
                    if ((filterList.contains("Carguero") && nave.getClass().getSimpleName().equals("Carguero")) || (containsFilter && nave.getClass().getSimpleName().equals("Carguero"))) {
                        if (!contadorEscrito) {
                            System.out.println("Oferta " + contador + ":");
                            contadorEscrito = true;
                        }
                        System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                        ofertaVálida = true;
                        containsFilter = true;
                    }
                    if ((filterList.contains("Caza") && nave.getClass().getSimpleName().equals("Caza")) || (containsFilter && nave.getClass().getSimpleName().equals("Caza"))) {
                        if (!contadorEscrito) {
                            System.out.println("Oferta " + contador + ":");
                            contadorEscrito = true;
                        }
                        System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                        ofertaVálida = true;
                        containsFilter = true;
                    }
                    if ((filterList.contains("Destructor") && nave.getClass().getSimpleName().equals("Destructor")) || (containsFilter && nave.getClass().getSimpleName().equals("Destructor"))) {
                        if (!contadorEscrito) {
                            System.out.println("Oferta " + contador + ":");
                            contadorEscrito = true;
                        }
                        System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                        ofertaVálida = true;
                        containsFilter = true;
                    }
                    if ((filterList.contains("EstacionEspacial") && nave.getClass().getSimpleName().equals("EstacionEspacial")) || (containsFilter && nave.getClass().getSimpleName().equals("EstacionEspacial"))) {
                        if (!contadorEscrito) {
                            System.out.println("Oferta " + contador + ":");
                            contadorEscrito = true;
                        }
                        System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                        ofertaVálida = true;
                        containsFilter = true;
                    }
                }
                if (ofertaVálida) {
                    ofertasVálidas.add(oferta);
                    System.out.println("Precio: " + oferta.getPrize());
                    contador++;
                }
            }
            containsFilter = false;
            contadorEscrito = false;
            navesTotales = new ArrayList<>();
        }
        return ofertasVálidas;
    }

    public List<Offer> verOfertasTipo3ConFiltros() throws IOException, ClassNotFoundException {
        //Este método copiado de verOfertas en BuyOffer guardará en ofertasVálidas las ofertas disponibles para el Client4,
        //que es de Tipo 3, añadiendo un filtro para Destructores.
        //El resultado debería ser la Oferta 1 (Tienen un destructor en su interior).
        //Inicializamos variables necesarias
        List<Offer> ofertasVálidas = new ArrayList<>();
        List<Nave> navesTotales = new ArrayList<>();
        boolean ofertaVálida = false;
        boolean navesVálidas = false;
        List<String> validNaves = new ArrayList<>();
        boolean containsFilter = false;
        int contador = 1;
        boolean contadorEscrito = false;
        Admin admin = new Admin();
        List<Offer> listaOfertas = admin.getPublishedOffers();
        List<String> filterList = new ArrayList<>();
        filterList.add("Destructor");
        //Case 3
        for (Offer oferta : listaOfertas) {
            ofertaVálida = false;
            List<String> numRegTotales = oferta.getNaves();
            for (String numReg : numRegTotales) {
                navesTotales.add(admin.searchShip(numReg));
            }
            for (Nave nave : navesTotales) {
                if (filterList.contains(nave.getClass().getSimpleName())) {
                    containsFilter = true;
                }
            }
            if (!navesTotales.get(0).getPropietario().getEmail().equals(admin.getClientList().get(3).getEmail())) {
                //El forEach solo coge ofertas distintas a null.
                for (Nave nave : navesTotales) {
                    if ((filterList.contains("Carguero") && nave.getClass().getSimpleName().equals("Carguero")) || (containsFilter && nave.getClass().getSimpleName().equals("Carguero"))) {
                        if (!contadorEscrito) {
                            System.out.println("Oferta " + contador + ":");
                            contadorEscrito = true;
                        }
                        System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                        ofertaVálida = true;
                        containsFilter = true;
                    }
                    if ((filterList.contains("Caza") && nave.getClass().getSimpleName().equals("Caza")) || (containsFilter && nave.getClass().getSimpleName().equals("Caza"))) {
                        if (!contadorEscrito) {
                            System.out.println("Oferta " + contador + ":");
                            contadorEscrito = true;
                        }
                        System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                        ofertaVálida = true;
                        containsFilter = true;
                    }
                    if ((filterList.contains("Destructor") && nave.getClass().getSimpleName().equals("Destructor")) || (containsFilter && nave.getClass().getSimpleName().equals("Destructor"))) {
                        if (!contadorEscrito) {
                            System.out.println("Oferta " + contador + ":");
                            contadorEscrito = true;
                        }
                        System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                        ofertaVálida = true;
                        containsFilter = true;
                    }
                    if ((filterList.contains("EstacionEspacial") && nave.getClass().getSimpleName().equals("EstacionEspacial")) || (containsFilter && nave.getClass().getSimpleName().equals("EstacionEspacial"))) {
                        if (!contadorEscrito) {
                            System.out.println("Oferta " + contador + ":");
                            contadorEscrito = true;
                        }
                        System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                        ofertaVálida = true;
                        containsFilter = true;
                    }
                }
                if (ofertaVálida) {
                    ofertasVálidas.add(oferta);
                    System.out.println("Precio: " + oferta.getPrize());
                    contador++;
                }
            }
            containsFilter = false;
            contadorEscrito = false;
            navesTotales = new ArrayList<>();
        }
        return ofertasVálidas;
    }

    public void comprarUnaOferta() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        Calendar calendario = new GregorianCalendar();
        Date fechaPublicacion = new Date(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.HOUR_OF_DAY), calendario.get(Calendar.MINUTE), calendario.get(Calendar.SECOND));

        Transaction transaction = new Transaction(admin.getClientList().get(3).getEmail(), admin.getClientList().get(0).getEmail(), admin.getPublishedOffers().get(0), fechaPublicacion);
        List<String> navesEnOferta = admin.getPublishedOffers().get(0).getNaves();
        Nave navePropietario = admin.searchShip(navesEnOferta.get(0));
        Client seller = navePropietario.getPropietario();
        for (String numReg : navesEnOferta) {
            Nave nave = admin.searchShip(numReg);   //La nave pasa a ser del cliente comprador.
            nave.setPropietario(admin.getClientList().get(3));
            admin.changePropietario(nave, admin.getClientList().get(3));
        }
        admin.deleteOffer(admin.getPublishedOffers().get(0));
    }

    public Nave crearNave1Test() {
        List<String> comentarios = new ArrayList<>();
        Client client1 = new Client("TestName", "TestPlanet",
                "TestSpecie", 0001, "TestNick", "testPassword",
                "TestEmail", true, 0, false, false, null, comentarios);
        Propulsion propulsion1 = new Propulsion(Propulsion.TipoPropulsor.Compresor_de_traza, 2000);
        Propulsion[] motor1 = new Propulsion[2];
        motor1[0] = propulsion1;
        Defensa defensa1 = new Escudo(1000, 3000);
        Defensa[] defensas1 = new Defensa[1];
        defensas1[0] = defensa1;
        Arma arma1 = new Arma(Arma.TipoArma.Cañón_Blaster, 2500);
        Arma[] armas1 = new Arma[2];
        armas1[0] = arma1;
        Creator cazCreator = new CazaCreator();
        Nave nave1 = cazCreator.crearNave("numTest", client1, motor1, 1000, null, 0, defensas1, armas1, 0, null);
        return nave1;
    }

    public Nave crearNave2Test() {
        List<String> comentarios = new ArrayList<>();
        Client client1 = new Client("TestName", "TestPlanet",
                "TestSpecie", 0001, "TestNick", "testPassword",
                "TestEmail", true, 0, false, false, null, comentarios);
        Propulsion propulsion1 = new Propulsion(Propulsion.TipoPropulsor.Compresor_de_traza, 2000);
        Propulsion[] motor1 = new Propulsion[2];
        motor1[0] = propulsion1;
        Defensa defensa1 = new Escudo(1000, 3000);
        Defensa[] defensas1 = new Defensa[1];
        defensas1[0] = defensa1;
        Arma arma1 = new Arma(Arma.TipoArma.Cañón_Blaster, 2500);
        Arma[] armas1 = new Arma[2];
        armas1[0] = arma1;
        Creator destCreator = new DestructorCreator();
        Nave nave1 = destCreator.crearNave("Test2", client1, motor1, 1000, null, 0, defensas1, armas1, 0, null);
        return nave1;
    }

    public Client crearCliente1Test() {
        List<String> comentarios = new ArrayList<>();
        Client client1 = new Client("TestName1", "TestPlanet1",
                "TestSpecie1", 0001, "TestNick1", "testPassword1",
                "TestEmail1", true, 0, false, false, null, comentarios);
        return client1;
    }

    public Client crearCliente2Test() {
        List<String> comentarios = new ArrayList<>();
        Client client1 = new Client("TestName2", "TestPlanet2",
                "TestSpecie2", 0002, "TestNick2", "testPassword2",
                "TestEmail2", false, 2, false, false, null, comentarios);
        return client1;
    }

    public Offer crearOferta1Test() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        Calendar calendario = new GregorianCalendar();
        Date fechaPublicacion = new Date(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.HOUR_OF_DAY), calendario.get(Calendar.MINUTE), calendario.get(Calendar.SECOND));
        ArrayList<String> navesOferta1 = new ArrayList<>();
        navesOferta1.add(crearNave1Test().getNumeroRegistro());
        Date fechaLímite1 = new Date(2025, 3, 25);
        Offer oferta1 = new Offer("Test1", 2500, fechaLímite1, false, false, navesOferta1, fechaPublicacion);
        return oferta1;
    }

    public Offer crearOferta2Test() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        Calendar calendario = new GregorianCalendar();
        Date fechaPublicacion = new Date(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.HOUR_OF_DAY), calendario.get(Calendar.MINUTE), calendario.get(Calendar.SECOND));
        ArrayList<String> navesOferta2 = new ArrayList<>();
        navesOferta2.add(crearNave1Test().getNumeroRegistro());   //Client2
        navesOferta2.add(crearNave2Test().getNumeroRegistro());   //Client2
        Date fechaLímite2 = new Date(2024, 4, 24);
        Offer oferta2 = new Offer("Test2", 5000, fechaLímite2, false, false, navesOferta2, fechaPublicacion);
        return oferta2;
    }

    public Transaction crearTransaccion1Test() throws IOException, ClassNotFoundException {
        Calendar calendario = new GregorianCalendar();
        Date fechaPublicacion = new Date(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.HOUR_OF_DAY), calendario.get(Calendar.MINUTE), calendario.get(Calendar.SECOND));
        Transaction transaction1 = new Transaction(crearCliente1Test().getEmail(), crearCliente2Test().getEmail(), crearOferta1Test(), fechaPublicacion);
        return transaction1;
    }

    public void crearOferta() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        Calendar calendario = new GregorianCalendar();
        Date fechaPublicacion = new Date(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.HOUR_OF_DAY), calendario.get(Calendar.MINUTE), calendario.get(Calendar.SECOND));
        admin.addShip(crearNave1Test());
        admin.addShip(crearNave2Test());
        ArrayList<String> navesOferta2 = new ArrayList<>();
        navesOferta2.add(admin.getShipList().get(0).getNumeroRegistro());
        navesOferta2.add(admin.getShipList().get(1).getNumeroRegistro());
        Date fechaLímite2 = new Date(2024, 4, 24);
        Offer oferta2 = new Offer("Test2", 5000, fechaLímite2, false, false, navesOferta2, fechaPublicacion);
        admin.addOffer(oferta2);
    }
}


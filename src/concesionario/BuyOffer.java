package concesionario;

import java.io.IOException;
import java.util.*;

public class BuyOffer {

    private List<Offer> ofertasVálidas = new ArrayList<>();
    private List<Nave> navesTotales = new ArrayList<>();

    public boolean verOfertas(int TipoUsuario, Client client) throws IOException, ClassNotFoundException {
        boolean ofertaVálida = false;
        boolean navesVálidas = true;
        List<String> validNaves = new ArrayList<>();
        boolean containsFilter = false;
        int contador = 1;
        boolean contadorEscrito = false;
        Admin admin = new Admin();
        List<Offer> listaOfertas = admin.getPublishedOffers();
        List<String> filterList = selectFilters();
        System.out.println("Selecciona la oferta que desee comprar");
        switch (TipoUsuario) {
            case 1: //Piratería espacial (Solo cargueros)
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
                        if (!navesTotales.get(0).getPropietario().getEmail().equals(client.getEmail())) {
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
                break;

            case 2: //Kromagg sin licencia (Solo cargueros y cazas)
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
                        if (!navesTotales.get(0).getPropietario().getEmail().equals(client.getEmail())) {
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
                break;

            case 3: //Kromagg con licencia y el resto (Todas las naves)
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
                    if (!navesTotales.get(0).getPropietario().getEmail().equals(client.getEmail())) {
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
                break;
        }

        Client seller;
        if (ofertasVálidas.size() != 0) {
            //Una vez mostradas, se llama a un método para seleccionar la oferta que se desee comprar.
            Offer chosenOffer = seleccionarOferta();
            if (chosenOffer==null) {
                return true;
            }
            //Conseguimos el cliente vendedor.
            List<String> offerShips = chosenOffer.getNaves();
            String shipString = offerShips.get(0);  //La oferta elegida tiene seguro al menos una nave, por lo que puedo hacer .get(0)
            Nave ship = admin.searchShip(shipString);
            seller = ship.getPropietario();
            //Ya se tiene la oferta deseada, por lo que se creará la transacción con el método comprarOferta().
            Transaction transaction = comprarOferta(chosenOffer, client);
            //Se añade la transacción al registro.
            if (transaction == null) {
                return false;
            }
            admin.addTransaction(transaction);

            //COMENTARIO Y VALORACIÓN
            Scanner sc = new Scanner(System.in);
            System.out.println("¡Pónganos una valoración entre 0 y 5 estrellas sobre su compra!");
            sc = new Scanner(System.in);
            String valoracion = "";
            Integer votacion = 0;
            if (sc.hasNextInt()) {
                boolean validStar = false;
                while (!validStar) {
                    votacion = sc.nextInt();
                    if (votacion >= 0 && votacion <= 5) {
                        validStar = true;
                    }
                }
                valoracion = votacion.toString();
            }
            System.out.println("Por favor, escriba un comentario sobre la experiencia con el vendedor");
            Scanner sc2 = new Scanner(System.in);
            String comentario = sc2.nextLine();
            valoracion = valoracion + comentario;
            admin.addComment(seller, valoracion);
            System.out.println("Gracias por todo, disfrute de su compra");
            return true;
        } else {
            System.out.println("No hay ofertas disponibles\n");
            return true;
        }
    }

    private Offer seleccionarOferta() {
        System.out.println("\nIntroduzca el número de la oferta que desea comprar -- 0 para cancelar");
        Scanner sc = new Scanner(System.in);
        int numOffer = sc.nextInt();
        if (numOffer==0) {
            return null;
        }
        while (numOffer < 1 || numOffer > (ofertasVálidas.size())) {
            numOffer = sc.nextInt();
        }
        System.out.println("Has seleccionado la oferta " + numOffer + " por un precio de " + ofertasVálidas.get(numOffer - 1).getPrize() + " euros\n");
        return ofertasVálidas.get(numOffer - 1);
    }

    private Transaction comprarOferta(Offer oferta, Client purchaser) throws IOException, ClassNotFoundException {
        System.out.println("¿Está seguro de que quiere comprar la oferta '" + oferta.getDescription() + "' por un precio de " + oferta.getPrize() + " euros? -- Pulse S en caso afirmativo y N en caso negativo");
        Scanner sc = new Scanner(System.in);
        if (sc.next().equals("S")) {
            Transaction transaction = new Transaction(null,null,null,null);
            transaction.setPurchaser(purchaser.getEmail());
            List<String> navesEnOferta = oferta.getNaves();
            Admin admin = new Admin();
            Nave navePropietario = admin.searchShip(navesEnOferta.get(0));
            Client seller = navePropietario.getPropietario();
            transaction.setSeller(seller.getEmail());
            for (String numReg : navesEnOferta) {
                Nave nave = admin.searchShip(numReg);   //La nave pasa a ser del cliente comprador.
                nave.setPropietario(purchaser);
                admin.changePropietario(nave, purchaser);
            }
            transaction.setOffer(oferta);
            Date date = new Date(Calendar.YEAR + 120, Calendar.MONTH, Calendar.DAY_OF_MONTH);
            transaction.setTime(date);
            System.out.println("Se ha creado una transacción: Venta de la oferta '" + oferta.getDescription() + "' de " + seller.getNick() + " a "
                    + purchaser.getNick() + " el día " + date.getDay() + " del mes " + date.getMonth() + ".\n");
            admin.deleteOffer(oferta);
            return transaction;
        } else {
            System.out.println("Se ha cancelado la transacción\n");
            return null;
        }
    }

    private List<String> selectFilters() {
        System.out.println("Puedes añadir filtros para tu búsqueda de naves espaciales");
        boolean exit = false;
        List<String> filterList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            System.out.println("Pulsa D para añadir el filtro Destructor, CG para añadir el filtro Carguero, CZ para añadir el filtro caza, E para añadir el filtro Estación Espacial o N para no añadir ninguno");
            sc = new Scanner(System.in);
            String filter = sc.nextLine();
            switch (filter) {
                case "D":
                    filterList.add("Destructor");
                    break;
                case "CG":
                    filterList.add("Carguero");
                    break;
                case "CZ":
                    filterList.add("Caza");
                    break;
                case "E":
                    filterList.add("EstacionEspacial");
                    break;
                default:
                    if (filterList.isEmpty()) {
                        filterList.add("Destructor");
                        filterList.add("Carguero");
                        filterList.add("Caza");
                        filterList.add("EstacionEspacial");
                    }
                    System.out.println("No se seleccionarán más filtros");
                    exit = true;
                    break;
            }
            if (exit == false) {
                System.out.println("¿Desea añadir más filtros? -- S para aceptar");
                sc = new Scanner(System.in);
                String option = sc.next();
                if (!option.equals("S")) {
                    exit = true;
                }
            }
        }
        return filterList;
    }
}

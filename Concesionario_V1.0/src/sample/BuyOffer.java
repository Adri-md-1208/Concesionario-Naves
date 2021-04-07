package sample;

import java.io.IOException;
import java.util.*;

public class BuyOffer {

    private List<Offer> ofertasVálidas = new ArrayList<>();
    private List<Nave> navesTotales = new ArrayList<>();

    public boolean verOfertas(int TipoUsuario, Client client) throws IOException, ClassNotFoundException {
        boolean ofertaVálida = false;
        int contador = 1;
        Admin admin = new Admin();
        List<Offer> listaOfertas = admin.getPublishedOffers();
        System.out.println("Selecciona la oferta que desee comprar");

        switch (TipoUsuario) {
            case 1: //Piratería espacial (Solo cargueros)
                for (Offer oferta : listaOfertas) {
                    ofertaVálida = false;
                    List<String> numRegTotales = oferta.getNaves();
                    for (String numReg : numRegTotales) {
                        navesTotales.add(admin.searchShip(numReg));
                    }
                    if (!navesTotales.get(0).getPropietario().getEmail().equals(client.getEmail())) {
                        System.out.println("Oferta " + contador + ":");
                        //El forEach solo coge ofertas distintas a null.
                        for (Nave nave : navesTotales) {
                            if (nave.getClass().getSimpleName().equals("Carguero")) {
                                System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                        ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                                ofertaVálida = true;
                            } else {
                                System.out.println("Nave no válida para su usuario -- Oferta cancelada");

                            }
                        }
                        if (ofertaVálida) {
                            ofertasVálidas.add(oferta);
                            System.out.println("Precio: " + oferta.getPrize());
                            contador++;
                        }
                    }
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
                    if (!navesTotales.get(0).getPropietario().getEmail().equals(client.getEmail())) {
                        System.out.println("Oferta " + contador + ":");
                        //El forEach solo coge ofertas distintas a null.
                        for (Nave nave : navesTotales) {
                            if (nave.getClass().getSimpleName().equals("Carguero") || (nave.getClass().getSimpleName().equals("Caza"))) {
                                System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                        ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                                ofertaVálida = true;
                            } else {
                                System.out.println("Nave no válida para su usuario -- Oferta cancelada");

                            }
                        }
                        if (ofertaVálida) {
                            ofertasVálidas.add(oferta);
                            System.out.println("Precio: " + oferta.getPrize());
                            contador++;
                        }
                    }
                    navesTotales = new ArrayList<>();
                }
                break;

            case 3: //Kromagg con licencia y el resto (Todas las naves)
                for (Offer oferta : listaOfertas) {
                    List<String> numRegTotales = oferta.getNaves();
                    for (String numReg : numRegTotales) {
                        navesTotales.add(admin.searchShip(numReg));
                    }
                    if (!navesTotales.get(0).getPropietario().getEmail().equals(client.getEmail())) {
                        System.out.println("Oferta " + contador + ":");
                        ofertasVálidas.add(oferta); //El forEach solo coge ofertas distintas a null.
                        for (Nave nave : navesTotales) {
                            System.out.println("Tipo " + nave.getClass().getSimpleName() + ", Núm.Registro: " + nave.getNumeroRegistro() +
                                    ", Propietario: " + nave.getPropietario().getNick() + ", Tripulantes: " + nave.getNumTripulantes());
                        }
                        System.out.println("Precio: " + oferta.getPrize());
                        contador++;
                    }
                    navesTotales = new ArrayList<>();
                }
                break;
        }

        if (ofertasVálidas.size() != 0) {
            //Una vez mostradas, se llama a un método para seleccionar la oferta que se desee comprar.
            Offer chosenOffer = seleccionarOferta();
            //Ya se tiene la oferta deseada, por lo que se creará la transacción con el método comprarOferta().
            Transaction transaction = comprarOferta(chosenOffer, client);
            //Se añade la transacción al registro.
            if (transaction==null) {
                return false;
            }
            admin.addTransaction(transaction);

            //QUEDA COMENTARIO Y VALORACIÓN

            return true;
        } else {
            System.out.println("No hay ofertas disponibles\n");
            return true;
        }
    }


    private Offer seleccionarOferta() {
        System.out.println("\nIntroduzca el número de la oferta que desea comprar");
        Scanner sc = new Scanner(System.in);
        int numOffer = sc.nextInt();
        while (numOffer < 1 || numOffer > (ofertasVálidas.size())) {
            numOffer = sc.nextInt();
        }
        System.out.println("Has seleccionado la oferta " + numOffer + " por un precio de " + ofertasVálidas.get(numOffer - 1).getPrize() + " euros\n");
        return ofertasVálidas.get(numOffer - 1);
    }

    private Transaction comprarOferta(Offer oferta, Client purchaser) throws IOException, ClassNotFoundException {
        Transaction transaction = new Transaction();
        transaction.setPurchaser(purchaser);
        List<String> navesEnOferta = oferta.getNaves();
        Admin admin = new Admin();
        Nave navePropietario = admin.searchShip(navesEnOferta.get(0));
        Client seller = navePropietario.getPropietario();
        transaction.setSeller(seller);
        for (String numReg : navesEnOferta) {
            Nave nave = admin.searchShip(numReg);   //La nave pasa a ser del cliente comprador.
            nave.setPropietario(purchaser);
            //updateProperty(purchaser, nave);
            //updateSoldOffer(oferta); No es necesario porque se borra con el siguiente método
        }
        transaction.setOffer(oferta);
        //admin.deleteOffer(oferta); //La oferta deja de estar a la venta -- No funciona
        Date date = new Date(Calendar.YEAR + 120, Calendar.MONTH, Calendar.DAY_OF_MONTH);
        transaction.setTime(date);
        System.out.println("¿Está seguro de que quiere comprar la oferta '" + oferta.getDescription() + "' por un precio de " + oferta.getPrize() + " euros? -- Pulse S en caso afirmativo y N en caso negativo");
        Scanner sc = new Scanner(System.in);
        if (sc.next().equals("S")) {
            System.out.println("Se ha creado una transacción: Venta de la oferta '" + oferta.getDescription() + "' de " + seller.getNick() + " a "
                    + purchaser.getNick() + " el día " + date.getDay() + " del mes " + date.getMonth() + ".\n");
            return transaction;
        } else {
            System.out.println("Se ha cancelado la transacción");
            return null;
        }
    }

}

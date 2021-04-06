package sample;

import java.io.IOException;
import java.util.*;

public class BuyOffer {

    private int contador = 1;
    private List<Offer> listaOfertas = new ArrayList<>();
    private List<Offer> ofertasVálidas = new ArrayList<>();

    public void verOfertas(int TipoUsuario, Client client) throws IOException, ClassNotFoundException {
        contador = 1;
        Admin admin = new Admin();
        listaOfertas = admin.getPublishedOffers();
        System.out.println("Selecciona la oferta que desee comprar");
        switch (TipoUsuario) {
            case 1: //Piratería espacial (Solo cargueros)
                for (Offer oferta : listaOfertas) {
                    List<Nave> navesTotales = oferta.getNaves();
                    if (!navesTotales.get(0).getPropietario().getEmail().equals(client.getEmail())) {
                        if (!navesTotales.get(0).getPropietario().getEmail().equals(client.getEmail())) {
                            System.out.println("Oferta " + contador + ":");
                            ofertasVálidas.add(oferta); //El forEach solo coge ofertas distintas a null.
                            for (int i = 0; i < navesTotales.size(); i++) {
                                if (navesTotales.get(i) != null) {
                                    if (navesTotales.get(i).getClass().getName().equals("Carguero") || (navesTotales.get(i).getClass().getName().equals("Caza"))) {
                                        System.out.println("Tipo " + navesTotales.get(i).getClass().getSimpleName() + ", Núm.Registro: " + navesTotales.get(i).getNumeroRegistro() +
                                                ", Propietario: " + navesTotales.get(i).getPropietario().getNick() + ", Tripulantes: " + navesTotales.get(i).getNumTripulantes());
                                        System.out.println("Precio: " + oferta.getPrize());
                                    }
                                }
                            }
                            contador++;
                        }
                    }
                }
                break;

            case 2: //Kromagg sin licencia (Solo cargueros y cazas)
                for (Offer oferta : listaOfertas) {
                    List<Nave> navesTotales = oferta.getNaves();
                    if (!navesTotales.get(0).getPropietario().getEmail().equals(client.getEmail())) {
                        System.out.println("Oferta " + contador + ":");
                        ofertasVálidas.add(oferta); //El forEach solo coge ofertas distintas a null.
                        for (int i = 0; i < navesTotales.size(); i++) {
                            if (navesTotales.get(i) != null) {
                                if (navesTotales.get(i).getClass().getName().equals("Carguero") || (navesTotales.get(i).getClass().getName().equals("Caza"))) {
                                    System.out.println("Tipo " + navesTotales.get(i).getClass().getSimpleName() + ", Núm.Registro: " + navesTotales.get(i).getNumeroRegistro() +
                                            ", Propietario: " + navesTotales.get(i).getPropietario().getNick() + ", Tripulantes: " + navesTotales.get(i).getNumTripulantes());
                                    System.out.println("Precio: " + oferta.getPrize());
                                }
                            }
                        }
                        contador++;
                    }
                }
                break;

            case 3: //Kromagg con licencia y el resto (Todas las naves)
                for (Offer oferta : listaOfertas) {
                    List<Nave> navesTotales = oferta.getNaves();
                    if (!navesTotales.get(0).getPropietario().getEmail().equals(client.getEmail())) {
                        System.out.println("Oferta " + contador + ":");
                        ofertasVálidas.add(oferta); //El forEach solo coge ofertas distintas a null.
                        for (int i = 0; i < navesTotales.size(); i++) {
                            if (navesTotales.get(i) != null) {
                                System.out.println("Tipo " + navesTotales.get(i).getClass().getSimpleName() + ", Núm.Registro: " + navesTotales.get(i).getNumeroRegistro() +
                                        ", Propietario: " + navesTotales.get(i).getPropietario().getNick() + ", Tripulantes: " + navesTotales.get(i).getNumTripulantes());
                                System.out.println("Precio: " + oferta.getPrize());
                            }
                        }
                        contador++;
                    }
                }
                break;
        }

        if (ofertasVálidas.size() != 0) {
            //Una vez mostradas, se llama a un método para seleccionar la oferta que se desee comprar.
            Offer chosenOffer = seleccionarOferta();
            //Ya se tiene la oferta deseada, por lo que se creará la transacción con el método comprarOferta().
            Transaction transaction = comprarOferta(chosenOffer, client);
            //Se añade la transacción al registro.
            admin.addTransaction(transaction);
            //QUEDA COMENTARIO Y VALORACIÓN
        } else {
            System.out.println("No hay ofertas disponibles\n");
        }
    }


    private Offer seleccionarOferta() {
        System.out.println("\nIntroduzca el número de la oferta que desea comprar");
        Scanner sc = new Scanner(System.in);
        int numOffer = sc.nextInt();
        while (numOffer < 1 && numOffer > (ofertasVálidas.size())) {
            numOffer = sc.nextInt();
        }
        System.out.println("Has seleccionado la oferta " + numOffer + " por un precio de " + ofertasVálidas.get(numOffer).getPrize() + " euros");
        return ofertasVálidas.get(numOffer);
    }

    private Transaction comprarOferta(Offer oferta, Client purchaser) {
        Transaction transaction = new Transaction();
        transaction.setPurchaser(purchaser);
        List<Nave> navesEnOferta = oferta.getNaves();
        Client seller = navesEnOferta.get(0).getPropietario();
        transaction.setSeller(seller);
        for (Nave nave : navesEnOferta) {
            nave.setPropietario(purchaser);     //La nave pasa a ser del cliente comprador.
        }
        transaction.setOffer(oferta);
        Date date = new Date(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH);
        transaction.setTime(date);
        System.out.println("Se ha creado una transacción: Venta de la oferta " + oferta.getDescription() + " de " + seller.getNick() + " a "
                + purchaser.getNick() + " el día " + date.toString());
        return transaction;
    }

}

package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuyOffer {

    private int contador = 1;
    private List<Offer> listaOfertas = new ArrayList<>();
    private Offer[] ofertasVálidas;

    public void verOfertas(int TipoUsuario, Client client) throws IOException, ClassNotFoundException {
        contador = 1;
        Admin admin = new Admin();
        listaOfertas = admin.getPublishedOffers();
        System.out.println("Selecciona la oferta que desee comprar");
        switch (TipoUsuario) {
            case 1: //Piratería espacial (Solo cargueros)
                for (Offer oferta : listaOfertas) {
                    Nave[] navesTotales = oferta.getNaves();
                    int i = 0;
                    System.out.println("Oferta " + contador + ":");
                    while (i < (navesTotales.length - 1)) {
                        if (navesTotales[i].getClass().getName().equals("Carguero")) {
                            ofertasVálidas[contador] = oferta;
                            System.out.println("Tipo " + navesTotales[i].getClass().getName() + ", Núm.Registro: " + navesTotales[i].getNumeroRegistro() +
                                    ", Propietario: " + navesTotales[i].getPropietario() + ", Tripulantes: " + navesTotales[i].getNumTripulantes());
                            System.out.println("Precio: " + oferta.getPrize());
                        }
                    }
                    contador++;
                }
                break;

            case 2: //Kromagg sin licencia (Solo cargueros y cazas)


            case 3: //Kromagg con licencia y el resto (Todas las naves)

        }

        //Seleccionar la oferta deseada y llamar a comprarOferta()
        //Usar la propiedad navesVálidas para saber cual se desea comprar.


    }

    private Transaction comprarOferta(Offer oferta) {
        return null;
    }

}

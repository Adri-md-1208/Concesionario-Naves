package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    public boolean run() throws IOException, ClassNotFoundException {
        try {
            Admin admin = new Admin();
            List<Offer> unpublishedOfferList = admin.getUnpublishedOffers();
            if (unpublishedOfferList.isEmpty()) {
                System.out.println("No hay ofertas para revisar\n");
                return false;
            }
            int n = 1;
            System.out.println("Seleccione una oferta para revisar:");
            for (Offer offer : unpublishedOfferList) {
                System.out.print(n + ".");
                System.out.println(offer.getDescription());
                n++;
            }
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            System.out.println("Usted ha seleccionado la oferta "+option+ " :");
            System.out.println(unpublishedOfferList.get(option-1).getDescription());
            List <Nave> navesInOffer = new ArrayList<>();
            List<String> numsReferenciaList = unpublishedOfferList.get(option-1).getNaves();
            for(String numReferencia :numsReferenciaList ){
                navesInOffer.add(admin.searchShip(numReferencia));
            }
            for (Nave nave : navesInOffer){
                System.out.println("Tipo: " + nave.getClass().getSimpleName());
                System.out.println("Propietario: "+ nave.getPropietario().getName());
                System.out.println("Precio: " + unpublishedOfferList.get(option-1).getPrize() + "€");
            }
            System.out.println("Pulse 'S' para publicarla, 'N' para borrarla o 'C' para cancelar la operación");
            String decision = input.next();
            switch (decision) {
                case "S":
                    admin.modifyOfferVisibility(unpublishedOfferList.get(option - 1));
                    break;
                case "N":
                    admin.deleteOffer(unpublishedOfferList.get(option - 1));
                    break;
                case "C":
                    return false;
            }
            System.out.println("¿Desea revisar más ofertas?");
            decision = input.next();
            switch (decision) {
                case "S":
                    return true;
                case "N":
                    return false;
            }
            return false;
        } catch (Exception noFile) {
            System.out.println("No hay ofertas para revisar\n");
            return false;
        }
    }
}
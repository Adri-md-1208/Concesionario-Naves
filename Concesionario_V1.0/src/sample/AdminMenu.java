package sample;

import java.io.IOException;
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
            System.out.println("Pulse 'Y' para publicarla o 'N' para borrarla");
            String decision = input.next();
            switch (decision) {
                case "Y":
                    admin.modifyOfferVisibility(unpublishedOfferList.get(option - 1));
                    break;
                case "N":
                    admin.deleteOffer(unpublishedOfferList.get(option - 1));
                    break;
            }
            System.out.println("¿Desea revisar más ofertas?");
            decision = input.next();
            switch (decision) {
                case "Y":
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
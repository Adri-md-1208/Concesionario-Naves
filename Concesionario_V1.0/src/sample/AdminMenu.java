package sample;

import java.io.IOException;
import java.util.Scanner;

public class AdminMenu {

    Admin admin = new Admin();

    public boolean run() throws IOException, ClassNotFoundException {
        int n = 1;
        System.out.println("Seleccione una oferta para revisar:");
        for (Offer offer : admin.getUnpublishedOffers()){
            System.out.print(n+".");
            System.out.println(offer.getDescription());
            n=+1;
        }
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        System.out.println("Pulse 'Y' para publicarla o 'N' para borrarla");
        String decision = input.next();
        switch(decision){
            case "Y" :
                admin.modifyOfferVisibility(admin.getUnpublishedOffers().get(option-1));
                break;
            case "N":
                admin.deleteOffer(admin.getUnpublishedOffers().get(option-1));
                break;
        }
        System.out.println("¿Desea revisar más ofertas?");
        decision = input.next();
        switch(decision){
            case "Y" :
                return true;
            case "N":
                return false;
        }
        return false;
    }

}

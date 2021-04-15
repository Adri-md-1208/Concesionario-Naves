package concesionario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    public boolean reviewOffers() throws IOException, ClassNotFoundException {
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
            int contador = 1;
            for (Nave nave : navesInOffer){
                System.out.println("Tipo " + contador + ": " + nave.getClass().getSimpleName());
                contador++;
            }
            System.out.println("Propietario: "+ navesInOffer.get(0).getPropietario().getName());
            System.out.println("Precio: " + unpublishedOfferList.get(option-1).getPrize() + "€");
            System.out.println("Pulse 'S' para publicarla, 'N' para borrarla o 'C' para cancelar la operación");
            String decision = input.next();
            switch (decision) {
                case "S":
                    admin.modifyOfferVisibility(unpublishedOfferList.get(option - 1),true);
                    break;
                case "N":
                    admin.deleteOffer(unpublishedOfferList.get(option - 1));
                    Client client = admin.searchShip(unpublishedOfferList.get(option-1).getNaves().get(0)).getPropietario();
                    admin.addWarningSanction(client);
                    if(client.getWarnings()==2){
                        //En el momento que un usuario llega a 2 warnings se guarda esta fecha con hora y cuando pasen 5 días se llama a admin.deleteWarningSanction();
                    }
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

    public Client showClients() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        Admin admin = new Admin();
        int cont = 1;
        List<Client> clients = null;
        clients = admin.getClientList();
        for (Client client : clients) {
            System.out.println(cont++ + ". " + client.getNick());
        }
        int n = sc.nextInt();
        return admin.getClientList().get(n-1);
    }

    public boolean printMenu() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Revisar ofertas");
        System.out.println("2.Añadir sanción de piratería");
        System.out.println("3.Eliminar sanción de piratería");
        System.out.println("4.Añadir sanción de fraude");
        System.out.println("5.Eliminar sanción de fraude");
        System.out.println("6. Salir");
        int n = sc.nextInt();
        Admin admin = new Admin();
        switch(n){
            case 1:
                reviewOffers();
                break;
            case 2:
                admin.addPiracySanction(showClients());
                break;
            case 3:
                admin.deletePiracySanction(showClients());
                break;
            case 4:
                admin.addFraudSanction(showClients());
                break;
            case 5:
                admin.deleteFraudSanction(showClients());
                break;
            case 6 :
                return false;
        }
        return true;
    }
}
package sample;

import java.io.IOException;
import java.util.Scanner;

public class OperationMenu {

    boolean validOption = false;
    boolean exit = false;

    public void printMenu(int TipoUsuario, Client client) throws IOException, ClassNotFoundException {
        while (!exit) {
            System.out.println("Bienvenido, elija el número de la opción que desee:");
            System.out.println("1.Comprar Naves");
            System.out.println("2.Vender Naves");
            System.out.println("3.Suscribirse");
            System.out.println("4.Desuscribirse");
            System.out.println("5.Últimas transacciones");
            System.out.println("6.Mis valoraciones");
            System.out.println("7.Salir");

            validOption = false;
            while (validOption == false) {
                int option = selectOption();
                if (option >= 1 && option <= 7) {
                    validOption = true;
                    switch (option) {
                        case 1:
                            BuyOffer buyOffer = new BuyOffer();
                            buyOffer.verOfertas(TipoUsuario, client);
                            break;
                        case 2:
                            CreateOffer createOffer = new CreateOffer();
                            createOffer.crearOferta(client);
                            break;
                        case 3:
                            SubscribeToShip subscribeToShip = new SubscribeToShip();
                            subscribeToShip.subscribe(client);
                            break;
                        case 4:
                            UnsubscribeToShip unsubscribeToOffer = new UnsubscribeToShip();
                            unsubscribeToOffer.unsubscribe(client);
                            break;
                        case 5:
                            LastTransactions lastTransaction = new LastTransactions();
                            lastTransaction.lastTransaction(client);
                            break;
                        case 6:
                            MyRatings valoraciones = new MyRatings();
                            valoraciones.seeRatings(client);
                            break;
                        case 7:
                            exit = true;
                            break;
                    }
                } else {
                    System.out.println("Opción inexistente, vuelva a intentarlo");
                }
            }
        }
    }

    public int selectOption() {
        Scanner sc = new Scanner(System.in);
        int optionSelected = 0;
        if (sc.hasNextInt()) {
            optionSelected = sc.nextInt();
        }
        return optionSelected;
    }
}

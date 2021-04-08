package sample;

import java.io.IOException;
import java.util.List;

public class LastTransactions {

    public void lastTransaction(Client client) throws IOException, ClassNotFoundException {
        int contador = 1;
        Admin admin = new Admin();
        List<Transaction> transactionList = admin.getIndividualTransaction(client);
        System.out.println("Estas son tus últimas transacciones: ");
        List<Client> clientList = admin.getClientList();
        Client purchaser= null;
        Client seller= null;
        if (transactionList.isEmpty()) {
            System.out.println("\nNo hay transacciones disponibles");
        }
        for(Transaction transaction : transactionList) {
            for (Client clientToSearch : clientList) {
                if (clientToSearch.getEmail().equals(transaction.getPurchaser())){
                    purchaser=clientToSearch;

                }
                else if(clientToSearch.getEmail().equals(transaction.getSeller())){
                    seller=clientToSearch;
                }
            }
            System.out.println(contador + ". " + purchaser.getNick() + " ha comprado a " + seller.getNick()
                    + " la oferta de " + transaction.getOffer().getDescription() + " por " + transaction.getOffer().getPrize() + " euros en el día " +
                    transaction.getTime().getDay() + " del mes " + transaction.getTime().getMonth());
            contador++;
            if (contador>=10) {
                break;
            }
        }
        System.out.println("\nVolviendo al menú principal...\n");
    }
}
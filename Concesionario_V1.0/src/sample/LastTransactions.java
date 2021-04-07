package sample;

import java.io.IOException;
import java.util.List;

public class LastTransactions {

    public void lastTransaction(Client client) throws IOException, ClassNotFoundException {
        int contador = 1;
        Admin admin = new Admin();
        List<Transaction> transactionList = admin.getIndividualTransaction(client);
        System.out.println("Estas son tus últimas transacciones: ");
        int i=0;

        for (Transaction transaction : transactionList) {
            System.out.println(contador + ". " + transaction.getPurchaser().getNick() + " ha comprado a " + transaction.getSeller().getNick()
            + " la oferta de " + transaction.getOffer().getDescription() + " por " + transaction.getOffer().getPrize() + " euros en el día " +
            transaction.getTime().getDay() + " del mes " + transaction.getTime().getMonth());
            i++;
            if (i>9) {
                break;
            }
        }

        System.out.println("\nVolviendo al menú principal...\n");
    }

}
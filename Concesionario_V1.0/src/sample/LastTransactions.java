package sample;

import java.io.IOException;
import java.util.List;

public class LastTransactions {

    public void lastTransaction(Client client) throws IOException, ClassNotFoundException {

        Admin admin = new Admin();
        List<Transaction> transactionList = admin.getIndividualTransaction(client);
        System.out.println("Tus últimas cinco transacciones son las siguintes: ");
        for (int i=0; i<5; i++) {
            if (!transactionList.isEmpty()){
                Transaction transaction = transactionList.get(i);
                System.out.println((i+1) + ". " + transaction.getPurchaser().getNick() + " ha comprado a " + transaction.getSeller().getNick()
                        + " la oferta de " + transaction.getOffer().getDescription() + " por " + transaction.getOffer().getPrize() + " euros en el día " +
                        transaction.getTime().toString()); //getTime habría que probarlo.
            }else {
                System.out.println("Usted no tiene mas transacciones");
                i=5;
            }
        }
        System.out.println("\nVolviendo al menú principal...\n");
    }

}
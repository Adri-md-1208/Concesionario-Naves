package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LastTransactions {

    private List<Transaction> listTransaction = new ArrayList<Transaction>();

    public void lastTransaction(Client client) throws IOException, ClassNotFoundException {

        Admin admin = new Admin();
        listTransaction = admin.getIndividualTransaction(client);
        System.out.println("Tus últimas cinco transacciones son las siguintes: ");
        for (int i=0; i<5; i++) {
            if (!listTransaction.isEmpty()){
                Transaction transacción = listTransaction.get(i);
                //System.out.print de todo
            }else {
                System.out.println("Usted no tiene mas transacciones");
                i=5;
            }
        }
        System.out.println("\nVolviendo al menú principal...\n");
    }

}

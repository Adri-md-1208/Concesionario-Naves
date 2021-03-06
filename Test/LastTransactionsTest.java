import concesionario.Admin;
import concesionario.FilesCreator;
import concesionario.TestOperation;
import concesionario.Transaction;
//import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LastTransactionsTest {

    @BeforeAll
    static void beforeAll() throws IOException, ClassNotFoundException {
        TestOperation testOperation = new TestOperation();
        //testOperation.reinicioFicheros();
        File naves = new File("Ships.dat");
        File clientes = new File("Clients.dat");
        File transacciones = new File("Transactions.dat");
        File ofertas = new File("Offers.dat");
        BufferedWriter bwN = new BufferedWriter(new FileWriter(naves));
        bwN.write("");
        bwN.close();
        //naves.delete(); NO FUNCIONA EN WINDOWS.
        BufferedWriter bwC = new BufferedWriter(new FileWriter(clientes));
        bwC.write("");
        bwC.close();
        //clientes.delete();
            BufferedWriter bwT = new BufferedWriter(new FileWriter(transacciones));
        bwT.write("");
        bwT.close();
        //transacciones.delete();
        BufferedWriter bwO = new BufferedWriter(new FileWriter(ofertas));
        bwO.write("");
        bwO.close();
        //ofertas.delete();
        FilesCreator files = new FilesCreator();
        files.ClientCreator();
        files.ShipCreator();
        testOperation.creadorOfertas();
        testOperation.comprarOfertas();
    }

    @Test
    void lastTransactionsClient1() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        List<Transaction> listIndividualTransaction = admin.getIndividualTransaction(admin.getClientList().get(0));
        //Seg??n TestOperation, deber??amos tener 2 transacciones.
        assertEquals(listIndividualTransaction.size(), 3);
    }

    @Test
    void lastTransactionsClient2() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        List<Transaction> listIndividualTransaction = admin.getIndividualTransaction(admin.getClientList().get(1));
        //Seg??n TestOperation, deber??amos tener 1 transacciones.
        assertEquals(listIndividualTransaction.size(), 2);
    }

    @Test
    void lastTransactionsClient3() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        List<Transaction> listIndividualTransaction = admin.getIndividualTransaction(admin.getClientList().get(2));
        //Seg??n TestOperation, deber??amos tener 0 transacciones (usuario bloqueado).
        assertEquals(listIndividualTransaction.size(), 0);
    }

    @Test
    void lastTransactionsClient4() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        List<Transaction> listIndividualTransaction = admin.getIndividualTransaction(admin.getClientList().get(3));
        //Seg??n TestOperation, deber??amos tener 1 transacciones.
        assertEquals(listIndividualTransaction.size(), 1);
    }

    @Test
    void lastTransactionsClient5() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        List<Transaction> listIndividualTransaction = admin.getIndividualTransaction(admin.getClientList().get(4));
        //Seg??n TestOperation, deber??amos tener 2 transacciones.
        assertEquals(listIndividualTransaction.size(), 2);
    }

    @Test
    void correctPurchaser() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        List<Transaction> listIndividualTransaction = admin.getIndividualTransaction(admin.getClientList().get(1));
        //Deber??amos obtener una transacci??n con comprador 'Kromi' y vendedor 'Guille'. Miraremos el comprador.
        assertEquals(listIndividualTransaction.get(0).getPurchaser(), admin.getClientList().get(4).getEmail());
    }

    @Test
    void correctSeller() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();
        List<Transaction> listIndividualTransaction = admin.getIndividualTransaction(admin.getClientList().get(1));
        //Deber??amos obtener una transacci??n con comprador 'Kromi' y vendedor 'Guille'. Miraremos el vendedor.
        assertEquals(listIndividualTransaction.get(0).getSeller(), admin.getClientList().get(1).getEmail());
    }

    /*@BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        File naves = new File("Ships.dat");
        File  clientes = new File("Clients.dat");
        File transacciones = new File("Transactions.dat");
        File ofertas = new File("Offers.dat");
        naves.delete();
        clientes.delete();
        transacciones.delete();
        ofertas.delete();

    }*/
}

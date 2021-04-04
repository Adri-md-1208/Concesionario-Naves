package sample;

import javax.security.auth.login.LoginContext;
import java.io.File;
import java.io.IOException;
import java.io.PipedReader;

public class ClientManager {

    private OperationMenu operationMenu = new OperationMenu();
    private int TipoUsuario;
    private Client client;

    public boolean management() throws IOException, ClassNotFoundException {
        Admin admin = new Admin();

        //Creador de clientes:

        //FilesCreator filesCreator = new FilesCreator();
        //filesCreator.ClientCreator();
        //filesCreator.ShipCreator();

        //System.out.println(admin.getShipList().get(1).getPropietario().getEmail());
        Login login = new Login();
        client  = login.login();
        if (client!=null) {
            TipoUsuario = admin.evaluarTipoUsuario(client);
            System.out.println("\nSe está evaluando ...\n");
            if (TipoUsuario==0) {
                System.out.println("Está usted bloqueado en nuestro sistema.");
                return false;
            }
            operationMenu.printMenu(TipoUsuario, client);
        }
        return true;
    }

    public int getTipoUsuario() {
        return TipoUsuario;
    }
}

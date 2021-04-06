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

        /*FilesCreator filesCreator = new FilesCreator();
        filesCreator.ClientCreator();
        filesCreator.ShipCreator();*/


        Login login = new Login();
        client = login.login();
        try {
            if (client.getId() == 0) {
                AdminMenu adminMenu = new AdminMenu();
                adminMenu.run();
            }

            else if (client != null) {
                TipoUsuario = admin.evaluarTipoUsuario(client);
                System.out.println("\nSe está evaluando su usuario...\n");
                if (TipoUsuario == 0) {
                    System.out.println("Está usted bloqueado en nuestro sistema\n");
                    return false;
                }
                operationMenu.printMenu(TipoUsuario, client);
            }
            return true;
        } catch (Exception noClient) {
            return true;
        }
    }

    public int getTipoUsuario() {
        return TipoUsuario;
    }
}

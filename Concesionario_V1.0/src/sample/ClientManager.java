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

        //Creador de ficheros:
        File clientsFile = new File("Clients.dat");
        File shipsFile = new File("Ships.dat");
        if (!clientsFile.exists()){
            FilesCreator filesCreator = new FilesCreator();
            filesCreator.ClientCreator();
        }
        if (!shipsFile.exists()){
            FilesCreator filesCreator = new FilesCreator();
            filesCreator.ShipCreator();
        }

        Login login = new Login();
        client = login.login();
        try {
            if (client.getId() == 0) {
                AdminMenu adminMenu = new AdminMenu();
                Boolean repeat = true;
                while (repeat){
                    repeat = adminMenu.run();
                };
            }

            else if (client != null) {
                TipoUsuario = admin.evaluarTipoUsuario(client);
                System.out.println("\nSe está evaluando su usuario...\n");
                if (TipoUsuario == 0) {
                    System.out.println("Está usted bloqueado en nuestro sistema\n");
                    return false;
                }

                admin.actualizarUltimoAcceso(client);
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

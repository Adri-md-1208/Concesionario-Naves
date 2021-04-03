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

        Client client1 = new Client("Alejandro", "Tatooine",
             "Human", 7777, "AlexLopezAdrados", "programar",
                    "ala@gmail.com", true, 0, false, false, null);
         admin.addClient(client1);
        Propulsion propulsion1 = new Propulsion("Motor FTL", 2000);
        Propulsion propulsion2 = new Propulsion("Motor de curvatura", 1500);
        Propulsion[] motor=new Propulsion[2];
        motor[0]=propulsion1;
        motor[1]=propulsion2;
        Defensa defensa1 = new Escudo(1000, 3000);
        Defensa[] defensas = new Defensa[2];
        defensas[0]=defensa1;
        Arma arma = new Arma("Cañón Blaster", 1000);
        Arma[] armas = new Arma[1];
        armas[0]= arma;
        Creator destCreator = new DestructorCreator();
        Nave[] naves = new Nave[5];
        naves[0]=destCreator.crearNave("777", client1, motor, 1000, null, 5000, defensas, armas, 0, null);

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

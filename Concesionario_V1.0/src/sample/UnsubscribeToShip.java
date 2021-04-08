package sample;

import java.io.IOException;
import java.util.*;

public class UnsubscribeToShip {

    public void unsubscribe(Client client) throws IOException, ClassNotFoundException {
        System.out.println("Elija la suscripcion de la que darse de baja:");
        List<String> suscrito = new LinkedList<>();
        String[] tiposNaves = {"Estacion espacial", "Destructor", "Carguero", "Caza"};
        for (String tipo : tiposNaves){
            if (SuscriptionManager.getSuscriptors(tipo).contains(client)) suscrito.add(tipo);
        }
        int cont = 1;
        for (String estaSuscrito : suscrito){
            System.out.println(cont++ + ". " + estaSuscrito);
        }
        Scanner sc = new Scanner(System.in);
        String tipo = suscrito.get(Integer.parseInt(sc.nextLine()) - 1);
        SuscriptionManager.removeSuscriptor(tipo, client);
        System.out.println("Ha cancelado su suscripcion a las naves de tipo " + tipo);
    }

}

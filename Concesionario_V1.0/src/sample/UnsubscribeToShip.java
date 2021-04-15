package sample;

import java.io.IOException;
import java.util.*;

public class UnsubscribeToShip {

    public void unsubscribe(Client client) throws IOException, ClassNotFoundException {
        /*
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
        Admin admin = new Admin();
        admin.setSuscriptor(tipo,client,false);
        System.out.println("Ha cancelado su suscripcion a las naves de tipo " + tipo);*/
        System.out.println("Elija la suscripción que quiere darse de baja");
        List <String> suscriptions = new ArrayList<>();
        int contador =0;
        if(client.isSuscritoCarguero()){
            suscriptions.add("Carguero");
            }
        if(client.isSuscritoDestructor()){
            suscriptions.add("Destructor");
        }
        if(client.isSuscritoCaza()){
            suscriptions.add("Caza");
        }
        if(client.isSuscritoEstacionEspacial()){
            suscriptions.add("EstaciónEspacial");
        }
        if(suscriptions.isEmpty()){
            System.out.println("Actualmente no tiene suscripciones activas.");
        }
        for(String opcion : suscriptions){
            contador+=1;
            System.out.println(contador+"-"+opcion);
        }
        Scanner sc = new Scanner(System.in);
        int opcionSeleccionada = 999;
        while (opcionSeleccionada>suscriptions.size()) {
            opcionSeleccionada = sc.nextInt();
        }
            String desuscripcion =suscriptions.get(opcionSeleccionada-1);
            Admin admin = new Admin();
            admin.setSuscriptor(desuscripcion,client,false);
        System.out.println("Se ha anulado su suscripción a "+ desuscripcion);
        }
    }


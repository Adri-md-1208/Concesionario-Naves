package sample;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class SuscriptionManager {

    /*static TreeMap<String, HashSet<Client>> suscripciones = new TreeMap<>();

    public static HashSet<Client> getSuscriptors(String tipoNave){
        if (suscripciones.isEmpty()){
            suscripciones.put("Estacion espacial", new HashSet<>());
            suscripciones.put("Destructor", new HashSet<>());
            suscripciones.put("Carguero", new HashSet<>());
            suscripciones.put("Caza", new HashSet<>());
            return new HashSet<>();
        }
        else {
            return suscripciones.get(tipoNave);
        }
    }

    public static void addSuscriptor(String tipoNave, Client suscriptor){
        if (suscripciones.isEmpty()){
            suscripciones.put("Estacion espacial", new HashSet<>());
            suscripciones.put("Destructor", new HashSet<>());
            suscripciones.put("Carguero", new HashSet<>());
            suscripciones.put("Caza", new HashSet<>());
        }
        suscripciones.get(tipoNave).add(suscriptor);
    }

    public static void removeSuscriptor(String tipoNave, Client suscriptor){
        suscripciones.get(tipoNave).remove(suscriptor);
    }*/
    public void showOffers(Client client)throws IOException, ClassNotFoundException{
        Admin admin = new Admin();
        List<Nave> shipList = admin.getShipList();
        Date ultimoAcceso = client.getUltimoAcceso();
    }






}

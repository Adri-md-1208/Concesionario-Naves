package sample;

import java.util.HashSet;
import java.util.TreeMap;

public class SuscriptionManager {

    static TreeMap<String, HashSet<Client>> suscripciones = new TreeMap<>();

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
    }
}

package sample;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

abstract class Creator implements Serializable {
    public abstract Nave crearNave(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                                   List<String> suscriptores, double carga, Defensa[] defensa, Arma[] armas,
                                   int numMax, List<Nave> hangar);
}
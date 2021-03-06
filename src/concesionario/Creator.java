package concesionario;

import java.io.Serializable;
import java.util.List;

abstract class Creator implements Serializable {
    public abstract Nave crearNave(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                                   List<String> suscriptores, double carga, Defensa[] defensa, Arma[] armas,
                                   int numMax, List<Nave> hangar);
}
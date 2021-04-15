package sample;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

class CargueroCreator extends Creator implements Serializable {

    @Override
    public Nave crearNave(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                          List<String> suscriptores, double carga, Defensa[] defensa, Arma[] armas,
                          int numMax, List<Nave> hangar) {
        return new Carguero(numeroRegistro, propietario, motor, numTripulantes, suscriptores, carga, defensa);
    }
}
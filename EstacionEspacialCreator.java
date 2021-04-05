package sample;

import java.io.Serializable;
import java.util.List;

class EstacionEspacialCreator extends Creator implements Serializable {

    @Override
    public Nave crearNave(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                          List<Client> suscriptores, double carga, Defensa[] defensa, Arma[] armas,
                          int numMax, List<Nave> hangar) {
        return new EstacionEspacial(numeroRegistro, propietario, motor, numTripulantes, suscriptores,
                defensa, numMax, hangar);

    }
}
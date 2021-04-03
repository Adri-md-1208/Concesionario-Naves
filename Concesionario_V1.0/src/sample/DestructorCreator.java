package sample;

import java.util.List;

class DestructorCreator extends Creator{

    @Override
    public Nave crearNave(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                          List<Client> suscriptores, double carga, Defensa[] defensa, Arma[] armas,
                          int numMax, List<Nave> hangar) {
        return new Destructor(numeroRegistro, propietario, motor, numTripulantes, suscriptores, defensa, armas);
    }
}
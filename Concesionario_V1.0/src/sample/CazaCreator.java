package sample;

import java.util.List;

class CazaCreator extends Creator{

    @Override
    public Nave crearNave(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                          List<Client> suscriptores, double carga, Defensa[] defensa, Arma[] armas,
                          int numMax, List<Nave> hangar) {
        return new Caza(numeroRegistro, propietario, motor, numTripulantes, suscriptores, defensa, armas);

    }
}
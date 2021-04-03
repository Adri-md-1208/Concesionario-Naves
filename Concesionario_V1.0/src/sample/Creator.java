package sample;

import java.util.List;

abstract class Creator{
    public abstract Nave crearNave(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                                   List<Client> suscriptores, double carga, Defensa[] defensa, Arma[] armas,
                                   int numMax, List<Nave> hangar);
}
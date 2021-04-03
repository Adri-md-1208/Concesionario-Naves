package sample;

import java.util.List;

class Destructor extends Nave{
    private Arma[] armas;
    private Defensa[] defensa = new Defensa[2];

    public Destructor(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                      List<Client> suscriptores, Defensa[] defensa, Arma[] armas) {
        this.setNumeroRegistro(numeroRegistro);
        this.setPropietario(propietario);
        this.setMotor(motor);
        this.setNumTripulantes(numTripulantes);
        this.setSuscriptores(suscriptores);
        this.defensa = defensa;
        this.armas = armas;
    }
}
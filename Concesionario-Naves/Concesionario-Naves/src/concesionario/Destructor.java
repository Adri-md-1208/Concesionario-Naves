package concesionario;

import java.io.Serializable;
import java.util.List;

class Destructor extends Nave implements Serializable {
    private Arma[] armas;
    private Defensa[] defensa;

    public Destructor(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                      List<String> suscriptores, Defensa[] defensa, Arma[] armas) {
        this.setNumeroRegistro(numeroRegistro);
        this.setPropietario(propietario);
        this.setMotor(motor);
        this.setNumTripulantes(numTripulantes);
        this.setSuscriptores(suscriptores);
        this.defensa = defensa;
        this.armas = armas;
    }
}
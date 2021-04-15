package sample;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

class Caza extends Nave implements Serializable {
    private Arma[] armas = new Arma[2];
    private Defensa[] defensa = new Defensa[2];

    public Caza(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                List<String> suscriptores, Defensa[] defensa, Arma[] armas){
        this.setNumeroRegistro(numeroRegistro);
        this.setPropietario(propietario);
        this.setMotor(motor);
        this.setNumTripulantes(numTripulantes);
        this.setSuscriptores(suscriptores);
        this.defensa = defensa;
        this.armas = armas;
    }
}
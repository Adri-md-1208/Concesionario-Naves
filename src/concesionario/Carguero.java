package concesionario;

import java.io.Serializable;
import java.util.List;

class Carguero extends Nave implements Serializable {
    private double carga;
    private Defensa[] defensa;

    public Carguero(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                    List<String> suscriptores, double carga, Defensa[] defensa){
        this.setNumeroRegistro(numeroRegistro);
        this.setPropietario(propietario);
        this.setMotor(motor);
        this.setNumTripulantes(numTripulantes);
        this.setSuscriptores(suscriptores);
        this.carga = carga;
        this.defensa = defensa;
    }
}
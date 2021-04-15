package concesionario;

import java.io.Serializable;
import java.util.List;

class Nave implements Serializable {

    private String numeroRegistro;
    private Client propietario;
    private Propulsion[] motor = new Propulsion[2];
    private int numTripulantes;
    private List<String> suscriptores;

    public Nave() { }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public Client getPropietario() {
        return propietario;
    }

    public Propulsion[] getMotor() {
        return motor;
    }

    public int getNumTripulantes() {
        return numTripulantes;
    }

    public List<String> getSuscriptores() {
        return suscriptores;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public void setPropietario(Client propietario) {
        this.propietario = propietario;
    }

    public void setMotor(Propulsion[] motor) {
        this.motor = motor;
    }

    public void setNumTripulantes(int numTripulantes) {
        this.numTripulantes = numTripulantes;
    }

    public void setSuscriptores(List<String> suscriptores) {
        this.suscriptores = suscriptores;
    }
}
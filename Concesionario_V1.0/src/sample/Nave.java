package sample;

import java.util.ArrayList;
import java.util.List;

class Nave{

    private String numeroRegistro;
    private Client propietario;
    private Propulsion[] motor = new Propulsion[2];
    private int numTripulantes;
    private List<Client> suscriptores = new ArrayList<>(); //considerar set

    public Nave(){}

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

    public List<Client> getSuscriptores() {
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

    public void setSuscriptores(List<Client> suscriptores) {
        this.suscriptores = suscriptores;
    }

    /*
    public notificar(List<Client> suscriptores){
        for (Client Client : suscriptores){
            Client; // Notificar
        }
    }

    public anadirSuscriptor(Client Client){
        suscriptores.add(Client);
    }

    public eliminarSuscriptor(Client Client){
        suscriptores.remove(Client);
    }

     */
}

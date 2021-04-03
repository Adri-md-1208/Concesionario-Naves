package sample;

import java.util.List;

class Carguero extends Nave{
    private double carga;
    private Defensa[] defensa;

    public Carguero(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                    List<Client> suscriptores, double carga, Defensa[] defensa){
        this.setNumeroRegistro(numeroRegistro);
        this.setPropietario(propietario);
        this.setMotor(motor);
        this.setNumTripulantes(numTripulantes);
        this.setSuscriptores(suscriptores);
        this.carga = carga;
        this.defensa = defensa;
    }
}
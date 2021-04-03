package sample;

import java.util.List;

class EstacionEspacial extends Nave{
    private int numMax;
    private List<Nave> hangar;
    private Defensa[] defensa = new Defensa[3];

     public EstacionEspacial(String numeroRegistro, Client propietario, Propulsion[] motor, int numTripulantes,
                             List<Client> suscriptores, Defensa[] defensa, int numMax, List<Nave> hangar){
        this.setNumeroRegistro(numeroRegistro);
        this.setPropietario(propietario);
        this.setMotor(motor);
        this.setNumTripulantes(numTripulantes);
        this.setSuscriptores(suscriptores);
        this.defensa = defensa;
        this.numMax = numMax;
        this.hangar = hangar;
    }
}
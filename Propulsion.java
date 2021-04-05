package sample;

import java.io.Serializable;

public class Propulsion implements Serializable {
    enum TipoPropulsor implements Serializable {Compresor_de_traza, Motor_FTL, Motor_de_curvatura, Motor_iónico, Velas_solares}

    private TipoPropulsor nombre;
    private double maxVel;

    public Propulsion(TipoPropulsor nombre, double maxVel) {
        this.nombre = nombre;
        this.maxVel = maxVel;
    }

}
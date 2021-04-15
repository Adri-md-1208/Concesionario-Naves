package concesionario;

import java.io.Serializable;

class Escudo implements Defensa, Serializable {
    private double energiaRequerida;
    private double danoAbsorcion;

    public Escudo(double energiaRequerida, double danoAbsorcion) {
        this.energiaRequerida = energiaRequerida;
        this.danoAbsorcion = danoAbsorcion;
    }
}
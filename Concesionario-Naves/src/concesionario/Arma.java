package concesionario;

import java.io.Serializable;

class Arma implements Serializable {
    enum TipoArma implements Serializable{ Cañón_Blaster, Misiles_termonucleares, Rayos_láser, PEM}

    private TipoArma nombre;
    private double potencia;

    public Arma(TipoArma nombre, double potencia) {
        this.nombre = nombre;
        this.potencia = potencia;
    }
}
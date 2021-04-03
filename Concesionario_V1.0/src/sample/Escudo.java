package sample;

class Escudo implements Defensa{
    private double energiaRequerida;
    private double danoAbsorcion;

    public Escudo(double energiaRequerida, double danoAbsorcion) {
        this.energiaRequerida = energiaRequerida;
        this.danoAbsorcion = danoAbsorcion;
    }

}
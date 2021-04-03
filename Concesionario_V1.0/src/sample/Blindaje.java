package sample;

class Blindaje implements Defensa {
    private String material;
    private double peso;
    private double danoAbsorcion;

    public Blindaje(String material, double peso, double danoAbsorcion) {
        this.material = material;
        this.peso = peso;
        this.danoAbsorcion = danoAbsorcion;
    }
}
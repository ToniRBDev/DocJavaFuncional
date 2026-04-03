package modelo;

import java.util.Objects;

public class Coche extends Vehiculo {

    private TipoCoche tipoCoche;
    private int numPuertas;
    private int potencia;
    private int capacidadMaletero;

    public Coche(String numeroBastidor, String matricula, String marca, String modelo, int anioFabricacion,
                 boolean disponible, int kilometros, TipoCombustible tipoCombustible,
                 PrecioAlquiler precio, TipoCoche tipoCoche, int numPuertas,
                 int potencia, int capacidadMaletero) {
        super(numeroBastidor, matricula, marca, modelo, anioFabricacion, disponible, kilometros, tipoCombustible, precio);
        this.tipoCoche = tipoCoche;
        this.numPuertas = numPuertas;
        this.potencia = potencia;
        this.capacidadMaletero = capacidadMaletero;
    }

    public TipoCoche getTipoCoche() {
        return tipoCoche;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getCapacidadMaletero() {
        return capacidadMaletero;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "tipoCoche=" + tipoCoche +
                ", numPuertas=" + numPuertas +
                ", potencia=" + potencia +
                ", capacidadMaletero=" + capacidadMaletero +
                ", numeroBastidor='" + getNumeroBastidor() + '\'' +
                ", matricula='" + getMatricula() + '\'' +
                ", marca='" + getMarca() + '\'' +
                ", modelo='" + getModelo() + '\'' +
                ", disponible=" + isDisponible() +
                ", kilometros=" + getKilometros() +
                ", tipoCombustible=" + getTipoCombustible() +
                ", precio=" + getPrecio() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public boolean validar() {
        return this.getKilometros() < 200000 && this.getAnioFabricacion() > 2016;
    }
}

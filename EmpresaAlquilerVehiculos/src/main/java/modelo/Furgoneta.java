package modelo;

import java.util.Objects;

public class Furgoneta extends Vehiculo {

    private int potencia;
    private int capacidadPasajeros;
    private int volumenCargaM3;

    public Furgoneta(String numeroBastidor, String matricula, String marca, String modelo, int anioFabricacion,
                     boolean disponible, int kilometros, TipoCombustible tipoCombustible,
                     PrecioAlquiler precio, int potencia, int capacidadPasajeros,
                     int volumenCargaM3) {
        super(numeroBastidor, matricula, marca, modelo, anioFabricacion, disponible, kilometros, tipoCombustible, precio);
        this.potencia = potencia;
        this.capacidadPasajeros = capacidadPasajeros;
        this.volumenCargaM3 = volumenCargaM3;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public int getVolumenCargaM3() {
        return volumenCargaM3;
    }

    @Override
    public String toString() {
        return "Furgoneta{" +
                "potencia=" + potencia +
                ", capacidadPasajeros=" + capacidadPasajeros +
                ", volumenCargaM3=" + volumenCargaM3 +
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
            return this.getKilometros() < 400000 && this.getAnioFabricacion() > 2020;
    }
}

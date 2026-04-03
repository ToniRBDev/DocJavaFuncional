package modelo;

import java.util.Objects;

public class Moto extends Vehiculo {

    private TipoMoto tipoMoto;
    private double cilindrada;
    private int numPlazas;
    private boolean tieneBaul;

    public Moto(String numeroBastidor, String matricula, String marca, String modelo, int anioFabricacion,
                boolean disponible, int kilometros, TipoCombustible tipoCombustible,
                PrecioAlquiler precio, TipoMoto tipoMoto, double cilindrada,
                int numPlazas, boolean tieneBaul) {
        super(numeroBastidor, matricula, marca, modelo, anioFabricacion, disponible, kilometros, tipoCombustible, precio);
        this.tipoMoto = tipoMoto;
        this.cilindrada = cilindrada;
        this.numPlazas = numPlazas;
        this.tieneBaul = tieneBaul;
    }

    public TipoMoto getTipoMoto() {
        return tipoMoto;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public boolean isTieneBaul() {
        return tieneBaul;
    }


    @Override
    public String toString() {
        return "Moto{" +
                "tipoMoto=" + tipoMoto +
                ", cilindrada=" + cilindrada +
                ", numPlazas=" + numPlazas +
                ", tieneBaul=" + tieneBaul +
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
        return this.getKilometros() < 100000 && this.getAnioFabricacion() > 2010;
    }
}



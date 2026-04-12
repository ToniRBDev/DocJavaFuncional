package modelo;

import java.util.Objects;

public abstract class Vehiculo implements ValidadorVehiculoI {

    private String numeroBastidor;
    private String matricula;
    private String marca;
    private String modelo;
    private int anioFabricacion;
    private boolean disponible;
    private int kilometros;
    private TipoCombustible tipoCombustible;
    private PrecioAlquiler precio;

    public Vehiculo(String numeroBastidor, String matricula, String marca, String modelo, int anioFabricacion,
                    boolean disponible, int kilometros, TipoCombustible tipoCombustible,
                    PrecioAlquiler precio) {
        this.numeroBastidor = numeroBastidor;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.disponible = disponible;
        this.kilometros = kilometros;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
    }

    public String getNumeroBastidor() {
        return numeroBastidor;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getKilometros() {
        return kilometros;
    }

    public TipoCombustible getTipoCombustible() {
        return tipoCombustible;
    }

    public PrecioAlquiler getPrecio() {
        return precio;
    }


    @Override
    public String toString() {
        return "Vehiculo{" +
                "numeroBastidor='" + numeroBastidor + '\'' +
                ", matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anioFabricacion=" + anioFabricacion +
                ", disponible=" + disponible +
                ", kilometros=" + kilometros +
                ", tipoCombustible=" + tipoCombustible +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehiculo)) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(numeroBastidor, vehiculo.numeroBastidor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroBastidor);
    }
}

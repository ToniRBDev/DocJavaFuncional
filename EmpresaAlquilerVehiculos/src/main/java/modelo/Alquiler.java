package modelo;

import java.util.Objects;

public class Alquiler implements  CalculadoraPreciosI, ValidadorAlquilerI {

    private Vehiculo vehiculo;
    private TiempoAlquiler tiempoAlquiler;
    private double precioTotal;

    public Alquiler(Vehiculo vehiculo, TiempoAlquiler tiempoAlquiler) {
        this.vehiculo = vehiculo;
        this.tiempoAlquiler = tiempoAlquiler;
        this.precioTotal = calcularPrecioAlquiler(vehiculo, tiempoAlquiler);
    }


    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public TiempoAlquiler getTiempoAlquiler() {
        return tiempoAlquiler;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                ", vehiculo=" + vehiculo +
                ", tiempoAlquiler=" + tiempoAlquiler +
                ", precioTotal=" + precioTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alquiler)) return false;
        Alquiler alquiler = (Alquiler) o;
        return Objects.equals(vehiculo, alquiler.vehiculo) &&
                Objects.equals(tiempoAlquiler, alquiler.tiempoAlquiler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehiculo, tiempoAlquiler);
    }


    @Override
    public boolean validarAlquiler() {
        if (vehiculo == null || tiempoAlquiler == null) {
            return false;
        }
        if (tiempoAlquiler.getDias()==0 && tiempoAlquiler.getMeses()==0) return false;
        return true;
    }
}

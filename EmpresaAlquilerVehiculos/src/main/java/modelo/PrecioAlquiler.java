package modelo;

import java.util.Objects;

public class PrecioAlquiler {

    private double precioPorDia;
    private double precioPorMes;

    public PrecioAlquiler(double precioPorDia, double precioPorMes) {
        this.precioPorDia = precioPorDia;
        this.precioPorMes = precioPorMes;
    }

    public double getPrecioPorDia() {
        return precioPorDia;
    }

    public double getPrecioPorMes() {
        return precioPorMes;
    }

    @Override
    public String toString() {
        return "PrecioAlquiler{" +
                "precioPorDia=" + precioPorDia +
                ", precioPorMes=" + precioPorMes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrecioAlquiler)) return false;
        PrecioAlquiler that = (PrecioAlquiler) o;
        return Double.compare(that.precioPorDia, precioPorDia) == 0 &&
                Double.compare(that.precioPorMes, precioPorMes) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(precioPorDia, precioPorMes);
    }
}

package modelo;

import java.util.Objects;

public class TiempoAlquiler {

    private int dias;
    private int meses;

    public TiempoAlquiler(int dias, int meses) {
        this.dias = dias;
        this.meses = meses;
    }

    public int getDias() {
        return dias;
    }

    public int getMeses() {
        return meses;
    }

    @Override
    public String toString() {
        return "TiempoAlquiler{" +
                "dias=" + dias +
                ", meses=" + meses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TiempoAlquiler)) return false;
        TiempoAlquiler that = (TiempoAlquiler) o;
        return dias == that.dias && meses == that.meses;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dias, meses);
    }
}

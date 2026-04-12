package modelo;

import java.util.Objects;

public class Alquiler implements CalculadoraPreciosI, ValidadorAlquilerI {

    // Atributo estático que recuerda el último número usado
    private static int contadorId = 0;

    private String codigoAlquiler;
    private Vehiculo vehiculo;
    private TiempoAlquiler tiempoAlquiler;
    private double precioTotal;
    private boolean activo;

    public Alquiler(Vehiculo vehiculo, TiempoAlquiler tiempoAlquiler) {
        // Generación automática: Incrementa el contador y lo asigna
        contadorId++;
        this.codigoAlquiler = "ALQ-" + contadorId;

        this.vehiculo = vehiculo;
        this.tiempoAlquiler = tiempoAlquiler;

        // Uso del método default de la interfaz
        this.precioTotal = calcularPrecioAlquiler(vehiculo, tiempoAlquiler);
        this.activo = true;
    }

    // Getters y Setters
    public String getCodigoAlquiler() {
        return codigoAlquiler;
    }

    // He eliminado el Setter de codigoAlquiler para proteger la integridad
    // del ID autogenerado, pero puedes añadirlo si lo necesitas.

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public TiempoAlquiler getTiempoAlquiler() {
        return tiempoAlquiler;
    }

    public void setTiempoAlquiler(TiempoAlquiler tiempoAlquiler) {
        this.tiempoAlquiler = tiempoAlquiler;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "codigo='" + codigoAlquiler + '\'' +
                ", activo=" + activo +
                ", precio=" + precioTotal +
                ", vehiculo=" + vehiculo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alquiler)) return false;
        Alquiler alquiler = (Alquiler) o;
        return Objects.equals(codigoAlquiler, alquiler.codigoAlquiler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoAlquiler);
    }

    @Override
    public boolean validarAlquiler() {
        if (vehiculo == null || tiempoAlquiler == null) {
            return false;
        }
        return tiempoAlquiler.getDias() != 0 || tiempoAlquiler.getMeses() != 0;
    }
}
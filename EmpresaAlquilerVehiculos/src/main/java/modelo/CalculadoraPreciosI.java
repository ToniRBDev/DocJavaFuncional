package modelo;

public interface CalculadoraPreciosI {

    // Método por defecto: calcular el precio del alquiler
    default double calcularPrecioAlquiler(Vehiculo vehiculo, TiempoAlquiler tiempoAlquiler ) {
        double tiempoAlquilerPorDias= tiempoAlquiler.getDias()*vehiculo.getPrecio().getPrecioPorDia();
        double tiempoAlquilerPorMeses= tiempoAlquiler.getMeses()*vehiculo.getPrecio().getPrecioPorMes();
        return tiempoAlquilerPorDias+tiempoAlquilerPorMeses;
    }

    // Método estático: utilidad general
    static double aplicarDescuento(Alquiler alquiler, double porcentaje) {
        double precioAlquiler= alquiler.getPrecioTotal();
        return precioAlquiler - (precioAlquiler * porcentaje / 100);
    }
}

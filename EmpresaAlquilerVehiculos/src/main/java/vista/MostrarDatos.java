package vista;

import logica.LogicaEmpresaAlquiler;
import modelo.Alquiler;
import modelo.Cliente;
import modelo.EmpresaAlquilerVehiculos;
import modelo.Vehiculo;

import java.util.List;

public class MostrarDatos {

    public static void mostrarEmpresa(EmpresaAlquilerVehiculos empresa) {

        System.out.println("=================================");
        System.out.println("EMPRESA: " + empresa.getNombre());
        System.out.println("=================================");

        // =====================
        // VEHICULOS
        // =====================
        System.out.println("\n--- VEHICULOS ---");

        for (Vehiculo vehiculo : empresa.getVehiculos()) {
            System.out.println(vehiculo);
        }

        // =====================
        // CLIENTES
        // =====================
        System.out.println("\n--- CLIENTES ---");

        for (Cliente cliente : empresa.getClientes()) {
            System.out.println("Cliente: " + cliente.getNombre() + " (" + cliente.getDni() + ")");
            System.out.println("Edad: " + cliente.getEdad());
            System.out.println("Dirección: " + cliente.getDireccion());
            System.out.println("Cuenta: " + cliente.getNumCuenta());

            List<Alquiler> alquileresCliente = cliente.getAlquileres();

            if (!alquileresCliente.isEmpty()) {
                System.out.println("Alquileres:");
                for (Alquiler alquiler : alquileresCliente) {
                    System.out.println("   - Vehículo: " + alquiler.getVehiculo().getMatricula()
                            + " | Tiempo: " + alquiler.getTiempoAlquiler()
                            + " | Precio: " + alquiler.getPrecioTotal());
                }
            } else {
                System.out.println("   Sin alquileres");
            }

            System.out.println();
        }

        // =====================
        // TODOS LOS ALQUILERES
        // =====================
        System.out.println("\n--- TODOS LOS ALQUILERES ---");

        List<Alquiler> todosAlquileres = empresa.obtenerTodosAlquileresEmpresa();

        if (!todosAlquileres.isEmpty()) {
            for (Alquiler alquiler : todosAlquileres) {
                System.out.println("Vehículo: " + alquiler.getVehiculo().getMatricula()
                        + " | Tiempo: " + alquiler.getTiempoAlquiler()
                        + " | Precio: " + alquiler.getPrecioTotal());
            }
        } else {
            System.out.println("No hay alquileres registrados.");
        }

        System.out.println("\n=================================\n");
    }

    /**
     * Método estático que muestra las marcas de todas las furgonetas ordenadas alfabéticamente
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarTodasLasMarcasDeFurgonetasOrdenadasAlfabéticamente(EmpresaAlquilerVehiculos empresa) {
        System.out.println("Marcas de furgonetas ordenadas alfabéticamente:");
        LogicaEmpresaAlquiler.marcasDeFurgonetas(empresa)
                .stream()
                .sorted()
                .forEach(System.out::println);
    }
}
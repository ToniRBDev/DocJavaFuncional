package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmpresaAlquilerVehiculos {

    private final String nombre;
    private List<Vehiculo> vehiculos;
    private List<Cliente> clientes;


    public EmpresaAlquilerVehiculos(String nombre) {
        this.nombre = nombre;
        vehiculos=new ArrayList<>();
        clientes=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }


    @Override
    public String toString() {
        return "EmpresaAlquilerVehiculos{" +
                "nombre='" + nombre + '\'' +
                ", vehiculos=" + vehiculos +
                ", clientes=" + clientes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmpresaAlquilerVehiculos)) return false;
        EmpresaAlquilerVehiculos that = (EmpresaAlquilerVehiculos) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    /**
     * Añade un cliente a la lista de clientes de la empresa
     * @param cliente de tipo Cliente
     */
    public void addCliente(Cliente cliente){
        clientes.add(cliente);
    }

    /**
     * Añade un vehículo a la lista de vehículos de la empresa
     * @param vehiculo de tipo Vehiculo
     */
    public void addVehiculo(Vehiculo vehiculo){
        vehiculos.add(vehiculo);
    }

    /**
     * Obtiene todos los alquileres de la empresa
     * @return lista de alquileres
     */
    public List<Alquiler> obtenerTodosAlquileresEmpresa(){
        return clientes
                .stream()
                .flatMap(cliente -> cliente.getAlquileres()
                        .stream())
                .toList();
    }

    public List<Alquiler> obtenerTodosAlquileresEmpresa2() {
        return clientes
                .stream()
                .map(Cliente::getAlquileres)
                .flatMap(List::stream)
                .toList();
    }
}

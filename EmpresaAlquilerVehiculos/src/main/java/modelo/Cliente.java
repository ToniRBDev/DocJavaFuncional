package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente {

    private final String dni;
    private String nombre;
    private int edad;
    private String direccion;
    private String numCuenta;
    private List<Alquiler> alquileres;

    public Cliente(String dni, String nombre, int edad, String direccion, String numCuenta) {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.direccion = direccion;
        this.numCuenta = numCuenta;
        this.alquileres=new ArrayList<>();
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public List<Alquiler> getAlquileres() {
        return alquileres;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", direccion='" + direccion + '\'' +
                ", numCuenta='" + numCuenta + '\'' +
                ", alquileres=" + alquileres +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    public void addAlquiler(Alquiler alquiler){
        alquileres.add(alquiler);
    }
}


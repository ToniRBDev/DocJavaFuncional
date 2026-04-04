package logica;
import modelo.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LogicaEmpresaAlquiler {


    /**
     * Metodo estático que filtra los vehiculos disponibles.
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @return lista con los vehiculos disponibles
     */
    public static List<Vehiculo> fitrarVehiculosDisponibles(EmpresaAlquilerVehiculos empresa){
        return empresa.getVehiculos()
                .stream()
                .filter(Vehiculo::isDisponible)
                .toList();
    }

    /**
     * Metodo estático que filtra las distintas marcas de las furgonetas.
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @return lista con las marcas de las furgonetas
     */
    public static List<String> marcasDeFurgonetas(EmpresaAlquilerVehiculos empresa){
        return empresa.getVehiculos()
                .stream()
                .filter(vehiculo -> vehiculo instanceof Furgoneta)
                .map(vehiculo -> (Furgoneta) vehiculo)
                .map(Furgoneta::getMarca)
                .distinct()
                .toList();
    }

    /**
     * Metodo estático que calcula la media de kilometros de los vehiculos de la empresa.
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @return media de kilometros de los vehiculos o 0 si no hay vehiculos
     */
    public static double mediaKilometrosVehiculosEmpresa(EmpresaAlquilerVehiculos empresa){
        return empresa.getVehiculos()
                .stream()
                .mapToInt(Vehiculo::getKilometros)
                .average()
                .orElse(0);
    }

    /**
     * Metodo que ordena los vehiculos por kilometros de menor a mayor utilizando un Comparator
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @return lista de vehiculos ordenados por kilometros de menor a mayor
     */
    public List<Vehiculo> ordenarVehiculosPorKilometros(EmpresaAlquilerVehiculos empresa){
        return empresa.getVehiculos()
                .stream()
                .sorted((vehiculo1, vehiculo2) -> vehiculo1.getKilometros() - vehiculo2.getKilometros())
                .toList();
    }

    /**
     * Metodo estático que ordena los vehiculos por kilometros de menor a mayor utilizando el metodo comparing de Comparator
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @return lista de vehiculos ordenados por kilometros de menor a mayor
     */
    public static List<Vehiculo> ordenarVehiculosPorKilometros2(EmpresaAlquilerVehiculos empresa){
        return empresa.getVehiculos()
                .stream()
                .sorted(Comparator.comparing(Vehiculo::getKilometros))
                .toList();
    }


    /**
     * Método estático que ordena los vehiculos por kilometros de mayor a menor utilizando el metodo comparing de Comparator e invirtiendo el orden con el método reversed()
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @return lista de vehiculos ordenados por kilometros de mayor a menor
     */
    public List<Vehiculo> ordenarVehiculosPorKilometros3(EmpresaAlquilerVehiculos empresa){
        return empresa.getVehiculos()
                .stream()
                .sorted(Comparator.comparing(Vehiculo::getKilometros).reversed())
                .toList();
    }

    /**
     * Método que filtra los vehiculos disponibles y muestra un mensaje por consola para cada vehiculo antes y despues del filtro
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @return lista de vehiculos disponibles
     */
    public static List<Vehiculo> vehiculosDisponiblesDebug(EmpresaAlquilerVehiculos empresa){
        return empresa.getVehiculos()
                .stream()
                .peek(vehiculo -> System.out.println("ANTES filtro: " + vehiculo.getMatricula()))
                .filter(Vehiculo::isDisponible)
                .peek(vehiculo -> System.out.println("DESPUÉS filtro: " + vehiculo.getMatricula()))
                .toList();
    }

    /**
     * Método estático que obtiene los clientes más jóvenes de la empresa
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @param numeroClientesAObtener número de clientes a obtener
     * @return lista de clientes más jóvenes
     */
    public static List<Cliente> obtenerLosClientesMasJovenes( EmpresaAlquilerVehiculos empresa, int numeroClientesAObtener){
        return empresa
                .getClientes()
                .stream()
                .sorted(Comparator.comparingInt(Cliente::getEdad))
                .limit(Math.max(0, numeroClientesAObtener)) //Evitamos que lance una excepción en caso de que se pase un numero negativo
                .toList();
    }


    /**
     * Método estático que permite paginar los vehículos de la empresa
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @param pagina de tipo int
     * @param tamanioPagina de tipo int
     * @return lista de vehículos del tamaño indicado en la página indicada
     */
    public static List<Vehiculo> obtenerVehiculosPaginados(EmpresaAlquilerVehiculos empresa, int pagina, int tamanioPagina) {
        return empresa.getVehiculos()
                .stream()
                .skip((long) (pagina - 1) * tamanioPagina)
                .limit(tamanioPagina)
                .toList();
    }

    /**
     * Método estático que obtiene todos los clientes de 2 empresas, útil para una evaluar posibles fusiones o colaboraciones.
     * @param empresa1 de tipo EmpresaAlquilerVehiculos
     * @param empresa2 de tipo EmpresaAlquilerVehiculos
     * @return lista de clientes de las 2 empresas
     */
    public static List<Cliente> todosLosClientesDe2Empresas(EmpresaAlquilerVehiculos empresa1, EmpresaAlquilerVehiculos empresa2){
        return Stream.concat(empresa1.getClientes().stream(),
                        empresa2.getClientes().stream())
                .distinct()
                .toList();
    }

    public static int[] potenciaDeCadaCoche(EmpresaAlquilerVehiculos empresa) {
       empresa.getVehiculos()
               .stream()
               .map(V)
    }




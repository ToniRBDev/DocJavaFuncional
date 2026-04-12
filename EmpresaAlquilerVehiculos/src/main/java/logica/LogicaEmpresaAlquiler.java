package logica;
import modelo.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicaEmpresaAlquiler {

    /**
     * Método estático que alquila un vehículo a un cliente.
     * <p>
     * El vehículo pasa a no estar disponible y se crea un nuevo alquiler activo
     * asociado al cliente.
     * </p>
     * @param cliente cliente que realiza el alquiler
     * @param vehiculo vehículo que se desea alquilar
     * @param tiempoAlquiler tiempo durante el que se alquila el vehículo
     * @return alquiler creado o null si no se puede realizar el alquiler
     */
    public static Alquiler alquilarVehiculo(
            Cliente cliente,
            Vehiculo vehiculo,
            TiempoAlquiler tiempoAlquiler) {

        if (cliente == null || vehiculo == null || tiempoAlquiler == null) {
            return null;
        }

        if (tiempoAlquiler.getDias() == 0 && tiempoAlquiler.getMeses() == 0) {
            return null;
        }

        if (!vehiculo.isDisponible()) {
            throw new IllegalStateException("El vehículo no está disponible");
        }

        Alquiler alquiler = new Alquiler(vehiculo, tiempoAlquiler);
        vehiculo.setDisponible(false);
        cliente.addAlquiler(alquiler);

        return alquiler;
    }

    /**
     * Método estático que devuelve un vehículo y finaliza el alquiler.
     * @param alquiler de tipo Alquiler
     */
    public static void devolverVehiculo(Alquiler alquiler) {

        if (alquiler == null) {
            throw new IllegalArgumentException("El alquiler no puede ser null");
        }

        if (!alquiler.isActivo()) {
            throw new IllegalStateException("El alquiler ya está finalizado");
        }

        alquiler.setActivo(false);
        alquiler.getVehiculo().setDisponible(true);
    }


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
     * Método estático una lista con los vehiculos paginados
     * @param empresa sobre la cuál paginar los vehículos
     * @param pagina página a obtener
     * @param tamanioPagina que va a tener la paginación
     * @return lista de vehículos paginados
     */
    public static List<Vehiculo> obtenerVehiculosPaginados(
            EmpresaAlquilerVehiculos empresa,
            int pagina,
            int tamanioPagina) {

        int paginaSegura = Math.max(1, pagina);
        int tamanioSeguro = Math.max(0, tamanioPagina);

        return empresa.getVehiculos()
                .stream()
                .sorted(Comparator.comparing(Vehiculo::getMarca))
                .skip((long) (paginaSegura - 1) * tamanioSeguro)
                .limit(tamanioSeguro)
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

    /**
     * Método estático que obtiene un array con la potencia de cada coche de la empresa
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @return array con la potencia de cada coche de la empresa
     */
    public static int[] potenciaDeCadaCoche(EmpresaAlquilerVehiculos empresa) {
        return empresa.getVehiculos()
                .stream()
                .filter(vehiculo -> vehiculo instanceof Coche)
                .map(coche -> (Coche) coche)
                .mapToInt(Coche::getPotencia)
                .toArray();
    }

    /**
     * Método estático que obtiene el cliente con el alquiler más caro
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @return cliente con el alquiler más caro
     */
     public static Cliente encontrarClienteConElAlquilerMasCaro(EmpresaAlquilerVehiculos empresa){
        return empresa.getClientes()
                .stream()
                .max(Comparator.comparing(cliente -> cliente.getAlquileres()
                        .stream()
                        .mapToDouble(Alquiler::getPrecioTotal)
                        .max()
                        .orElse(0)))
                .orElse(null);
     }

     /**
     * Método estático que obtiene el coche más barato de alquilar por día
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @return coche más barato por día
     */
     public static Coche obtenerCocheMasBaratoPorDia(EmpresaAlquilerVehiculos empresa){
         return empresa.getVehiculos()
                 .stream()
                 .filter(vehiculo -> vehiculo instanceof Coche)
                 .map(vehiculo -> (Coche) vehiculo)
                 .min(Comparator.comparing(coche -> coche.getPrecio().getPrecioPorDia()))
                 .orElse(null);
     }

    /**
     * Método estático que cuenta el número de vehículos disponibles en la empresa.
     *
     * <p>El método recorre la lista de vehículos de la empresa y filtra aquellos
     * que están disponibles. Posteriormente, utiliza la operación terminal {@code count}
     * para obtener el número total de vehículos disponibles.</p>
     *
     * @param empresa empresa sobre la cual se realizará el conteo
     * @return número total de vehículos disponibles
     */
    public static long contarTotalVehiculosDisponibles(EmpresaAlquilerVehiculos empresa) {
        return empresa.getVehiculos()
                .stream()
                .filter(Vehiculo::isDisponible)
                .count();
    }


    /**
     * Método estático que comprueba si existe al menos una furgoneta que tenga un número de plazas
     * mayor o igual al indicado.
     * @param empresa empresa sobre la cual se realizará la comprobación
     * @param numeroPlazasIndicadas número mínimo de plazas que debe tener la furgoneta
     * @return {@code true} si existe al menos una furgoneta con ese número de plazas o más,
     *         {@code false} en caso contrario
     */
    public static boolean comprobarSiHayFurgonetaDisponibleConMinimoPlazas(
            EmpresaAlquilerVehiculos empresa, int numeroPlazasIndicadas){
        return empresa.getVehiculos()
                .stream()
                .filter(vehiculo -> vehiculo instanceof Furgoneta)
                .map(vehiculo -> (Furgoneta) vehiculo)
                .anyMatch(furgoneta -> furgoneta.getCapacidadPasajeros() >= numeroPlazasIndicadas
                            && furgoneta.isDisponible());
    }

    /**
     * Método estático que comprueba si todos los clientes de la empresa tienen al menos un alquiler.
     * <p>Si la empresa no tiene clientes, el método devolverá {@code true}
     * @param empresa empresa sobre la cual se realizará la comprobación
     * @return {@code true} si todos los clientes tienen al menos un alquiler,
     *         {@code false} si existe algún cliente sin alquileres
     */
    public static boolean comprobarSiTodosClientesTienenAlquileres(
            EmpresaAlquilerVehiculos empresa) {
        return empresa.getClientes()
                .stream()
                .noneMatch(cliente -> cliente.getAlquileres().isEmpty());
    }

    /**
     * Método estático que comprueba si todos los vehículos de la empresa tienen un número de kilómetros
     * inferior al límite indicado.
     * <p>Si la empresa no tiene vehículos, el método devolverá {@code true}
     * @param empresa empresa sobre la cual se realizará la comprobación
     * @param limiteKilometros límite máximo de kilómetros permitido
     * @return {@code true} si todos los vehículos tienen menos kilómetros que el límite,
     *         {@code false} si al menos uno lo supera
     */
    public static boolean comprobarQueTodosLosVehiculosTenganMenosDeXkilometros(
            EmpresaAlquilerVehiculos empresa, int limiteKilometros) {

        return empresa.getVehiculos()
                .stream()
                .allMatch(vehiculo -> vehiculo.getKilometros() < limiteKilometros);
    }

    /**
     * Método estático que obtiene el coche disponible con el precio por día más barato.
     * @param empresa empresa sobre la cual se realizará la búsqueda
     * @return un {@code Optional<Coche>} con el coche disponible más barato,
     *         o vacío si no existe ninguno
     */
    public static Optional<Coche> obtenerCocheMasBaratoDisponible(
            EmpresaAlquilerVehiculos empresa) {
        return empresa.getVehiculos()
                .stream()
                .filter(vehiculo -> vehiculo instanceof Coche)
                .map(vehiculo -> (Coche) vehiculo)
                .filter(Coche::isDisponible)
                .sorted(Comparator.comparing(coche -> coche.getPrecio().getPrecioPorDia()))
                .findFirst();
    }

    /**
     * Método estático que obtiene una moto de tipo Enduro disponible cualquiera.
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @return un {@code Optional<Moto>} con una moto Enduro disponible,
     *         o vacío si no existe ninguna
     */
    public static Optional<Moto> obtenerMotoEnduroDisponible(EmpresaAlquilerVehiculos empresa){
        return empresa.getVehiculos()
                .stream()
                .filter(vehiculo -> vehiculo instanceof Moto)
                .map(vehiculo -> (Moto) vehiculo)
                .filter(moto -> moto.getTipoMoto() == TipoMoto.ENDURO)
                .filter(Moto::isDisponible)
                .findAny();
    }

    /**
     * Método estático que calcula el importe total generado por todos los alquileres actuales de la empresa.
     * @param empresa empresa sobre la cual se realizará el cálculo
     * @return importe total generado por todos los alquileres
     */
    public static double calcularIngresosTotalesDeLaEmpresa(EmpresaAlquilerVehiculos empresa) {
        return empresa.getClientes()
                .stream()
                .flatMap(cliente -> cliente.getAlquileres().stream())
                .map(Alquiler::getPrecioTotal)
                .reduce(0.0, Double::sum);
    }

    /**
     * Método estático que obtiene el vehículo con más kilómetros recorridos.
     * @param empresa empresa sobre la cual se realizará la búsqueda
     * @return el vehículo con más kilómetros recorridos, o {@code null} si no hay vehículos
     */
    public static Vehiculo obtenerVehiculoConMasKilometros(EmpresaAlquilerVehiculos empresa) {
        return empresa.getVehiculos()
                .stream()
                .reduce((vehiculo1, vehiculo2) ->
                        vehiculo1.getKilometros() > vehiculo2.getKilometros() ? vehiculo1 : vehiculo2)
                .orElse(null);
    }

    /**
     * Método estático que obtiene una lista de las furgonetas disponibles.
     * @param empresa empresa sobre la cual se realizará la búsqueda
     * @return lista de furgonetas disponibles
     */
    public static List<Furgoneta> obtenerFurgonetasDisponibles(EmpresaAlquilerVehiculos empresa) {
        return empresa.getVehiculos()
                .stream()
                .filter(vehiculo -> vehiculo instanceof Furgoneta)
                .map(vehiculo -> (Furgoneta) vehiculo)
                .filter(Furgoneta::isDisponible)
                .collect(Collectors.toList());
    }

    /**
     * Método estático que agrupa los vehículos de la empresa por tipo de combustible.
     * @param empresa empresa sobre la cual se realizará la agrupación
     * @return mapa con los vehículos agrupados por tipo de combustible
     */
    public static Map<TipoCombustible, List<Vehiculo>> agruparVehiculosPorTipoCombustible(
            EmpresaAlquilerVehiculos empresa) {
        return empresa.getVehiculos()
                .stream()
                .collect(Collectors.groupingBy(Vehiculo::getTipoCombustible));
    }


    /**
     * Método estático que obtiene un String con todas las matrículas de los vehículos separadas por coma.
     * @param empresa empresa sobre la cual se realizará la búsqueda
     * @return cadena con las matrículas de los vehículos
     */
    public static String obtenerMatriculasVehiculos(EmpresaAlquilerVehiculos empresa) {
        return empresa.getVehiculos()
                .stream()
                .map(Vehiculo::getMatricula)
                .collect(Collectors.joining(", "));
    }

    /**
     * Método estático que cuenta el número de vehículos por tipo de combustible.
     * @param empresa empresa sobre la cual se realizará el conteo
     * @return mapa con el número de vehículos por tipo de combustible
     */
    public static Map<TipoCombustible, Long> contarVehiculosPorTipoCombustible(
            EmpresaAlquilerVehiculos empresa) {

        return empresa.getVehiculos()
                .stream()
                .collect(Collectors.groupingBy(
                        Vehiculo::getTipoCombustible,  // En vez de devolver una lista como valor devolvemos un contador de elementos por clave
                        Collectors.counting()
                ));
    }

    /**
     * Método estático que divide los vehículos de la empresa en disponibles y no disponibles.
     * <p>
     * Utiliza {@code partitioningBy} para agrupar los vehículos en dos listas:
     * una con los disponibles (true) y otra con los no disponibles (false).
     * </p>
     * @param empresa empresa sobre la cual se realizará la partición
     * @return mapa con dos entradas:
     *         {@code true} -> vehículos disponibles
     *         {@code false} -> vehículos no disponibles
     */
    public static Map<Boolean, List<Vehiculo>> particionarVehiculosPorDisponibilidad(
            EmpresaAlquilerVehiculos empresa) {

        return empresa.getVehiculos()
                .stream()
                .collect(Collectors.partitioningBy(Vehiculo::isDisponible));
    }

    /**
     * Método estático que convierte la lista de vehículos en un mapa usando la matrícula como clave.
     * @param empresa empresa sobre la cual se realizará la conversión
     * @return mapa con la matrícula como clave y el vehículo como valor
     */
    public static Map<String, Vehiculo> mapearVehiculosPorMatricula(
            EmpresaAlquilerVehiculos empresa) {

        return empresa.getVehiculos()
                .stream()
                .collect(Collectors.toMap(
                        Vehiculo::getMatricula,
                        vehiculo -> vehiculo
                ));
    }

    /**
     * Método estático que agrupa las matrículas de los vehículos por tipo de combustible.
     * <p>
     * Utiliza {@code groupingBy} junto con {@code mapping} para transformar
     * los vehículos en sus matrículas dentro de cada grupo.
     * </p>
     * @param empresa empresa sobre la cual se realizará la agrupación
     * @return mapa con el tipo de combustible como clave y una lista de matrículas como valor
     */
    public static Map<TipoCombustible, List<String>> agruparMatriculasPorCombustible(
            EmpresaAlquilerVehiculos empresa) {

        return empresa.getVehiculos()
                .stream()
                .collect(Collectors.groupingBy(
                        Vehiculo::getTipoCombustible,
                        Collectors.mapping(Vehiculo::getMatricula, Collectors.toList())
                ));
    }

    /**
     * Método estático que cuenta el número de vehículos disponibles utilizando un Collector personalizado.
     *
     * <p>
     * Se construye el Collector manualmente utilizando supplier, accumulator,
     * combiner y finisher para comprender su funcionamiento.
     * </p>
     *
     * @param empresa empresa sobre la cual se realizará el conteo
     * @return número de vehículos disponibles
     */
    public static int contarVehiculosDisponiblesCollector(EmpresaAlquilerVehiculos empresa) {

        return empresa.getVehiculos()
                .stream()
                .filter(Vehiculo::isDisponible)
                .collect(Collector.of(
                        () -> new int[1],                  // 1. supplier
                        (contador, v) -> contador[0]++,   // 2. accumulator
                        (c1, c2) -> {                     // 3. combiner
                            c1[0] += c2[0];
                            return c1;
                        },
                        contador -> contador[0]           // 4. finisher
                ));
    }

    /**
     * Método estático que busca un vehículo por su matrícula.
     * @param empresa empresa sobre la cual se realizará la búsqueda
     * @param matricula matrícula del vehículo a buscar
     * @return un {@code Optional<Vehiculo>} con el vehículo encontrado,
     *         o vacío si no existe ninguno con esa matrícula
     */
    public static Optional<Vehiculo> buscarVehiculoPorMatricula(
            EmpresaAlquilerVehiculos empresa, String matricula) {

        Vehiculo vehiculoObtenido = empresa.getVehiculos()
                .stream()
                .filter(vehiculo -> vehiculo.getMatricula().equalsIgnoreCase(matricula))
                .findFirst()
                .orElse(null);

        return Optional.ofNullable(vehiculoObtenido);
    }

    }




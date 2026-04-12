package vista;

import logica.LogicaEmpresaAlquiler;
import modelo.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                    System.out.println("   - Código: " + alquiler.getCodigoAlquiler()
                            + " | Vehículo: " + alquiler.getVehiculo().getMatricula()
                            + " | Tiempo: " + alquiler.getTiempoAlquiler()
                            + " | Precio: " + alquiler.getPrecioTotal()
                            + " | Activo: " + alquiler.isActivo());
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
                System.out.println("Código: " + alquiler.getCodigoAlquiler()
                        + " | Vehículo: " + alquiler.getVehiculo().getMatricula()
                        + " | Tiempo: " + alquiler.getTiempoAlquiler()
                        + " | Precio: " + alquiler.getPrecioTotal()
                        + " | Activo: " + alquiler.isActivo());
            }
        } else {
            System.out.println("No hay alquileres registrados.");
        }}

    /**
     * Método estático que muestra las marcas de todas las furgonetas ordenadas alfabéticamente
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarTodasLasMarcasDeFurgonetasOrdenadasAlfabeticamente(EmpresaAlquilerVehiculos empresa) {
        System.out.println("Marcas de furgonetas ordenadas alfabéticamente:");
        LogicaEmpresaAlquiler.marcasDeFurgonetas(empresa)
                .stream()
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * Método estático que muestra las potencias de todos los coches de la empresa
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarPotenciasCoches(EmpresaAlquilerVehiculos empresa) {
        System.out.println("Actualmente esta es la potencia de todos nuestros coches:");

        Arrays.stream(LogicaEmpresaAlquiler.potenciaDeCadaCoche(empresa))
                .forEach(p -> System.out.println("- " + p + " CV"));
    }

    /**
     * Método estático que muestra los vehículos disponibles actualmente
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarVehiculosDisponibles(EmpresaAlquilerVehiculos empresa) {
        System.out.println("🚗 Vehículos disponibles actualmente:");

        List<Vehiculo> disponibles = LogicaEmpresaAlquiler
                .fitrarVehiculosDisponibles(empresa);

        if (disponibles.isEmpty()) {
            System.out.println("No hay vehículos disponibles.");
        } else {
            disponibles.forEach(v -> System.out.println(
                    "- " + v.getMarca() + " " + v.getModelo()
                            + " | Matrícula: " + v.getMatricula()
                            + " | Km: " + v.getKilometros()
            ));
        }
    }

    /**
     * Método estático que muestra la media de kilómetros de la flota
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarMediaKilometros(EmpresaAlquilerVehiculos empresa) {
        double media = LogicaEmpresaAlquiler
                .mediaKilometrosVehiculosEmpresa(empresa);

        if (media == 0) {
            System.out.println("No hay vehículos registrados en la empresa.");
        } else {
            System.out.println("📊 Media de kilómetros de la flota: "
                    + String.format("%.2f", media) + " km");
        }
    }

    /**
     * Método estático que muestra los vehículos ordenados por kilómetros
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarVehiculosOrdenadosPorKilometros(EmpresaAlquilerVehiculos empresa) {
        System.out.println("🚗 Vehículos ordenados por kilómetros (menor → mayor):");

        List<Vehiculo> vehiculos = LogicaEmpresaAlquiler
                .ordenarVehiculosPorKilometros2(empresa);

        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            vehiculos.forEach(v -> System.out.println(
                    "- " + v.getMarca() + " " + v.getModelo()
                            + " | " + v.getKilometros() + " km"
            ));
        }
    }

    /**
     * Método estático que permite debug sobre la disponibilidad de cada vehiculo
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarVehiculosDisponiblesDebug(EmpresaAlquilerVehiculos empresa) {
        System.out.println("🔍 DEBUG de vehículos disponibles:");

        List<Vehiculo> disponibles = LogicaEmpresaAlquiler
                .vehiculosDisponiblesDebug(empresa);

        System.out.println("\n📋 Resultado final (vehículos disponibles):");

        if (disponibles.isEmpty()) {
            System.out.println("❌ No hay vehículos disponibles.");
        } else {
            disponibles.forEach(v -> System.out.println(
                    "🚗 " + v.getMarca() + " " + v.getModelo()
                            + " | Matrícula: " + v.getMatricula()
            ));
        }
    }

    /**
     * Método estático que muestra los clientes más jóvenes de la empresa
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @param numeroClientesAObtener de tipo int
     */
    public static void mostrarClientesMasJovenes(
            EmpresaAlquilerVehiculos empresa,
            int numeroClientesAObtener){

        System.out.println("👶 Clientes más jóvenes de la empresa:");

        List<Cliente> clientes = LogicaEmpresaAlquiler
                .obtenerLosClientesMasJovenes(empresa, numeroClientesAObtener);

        if (clientes.isEmpty()) {
            System.out.println("❌ No hay clientes disponibles.");
        } else {
            clientes.forEach(c -> System.out.println(
                    "🔹 " + c.getNombre()
                            + " | Edad: " + c.getEdad()
                            + " | DNI: " + c.getDni()
            ));
        }
    }

    /**
     * Método estático que muestra los vehículos paginados
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @param pagina de tipo int
     * @param tamanioPagina de tipo int
     */
    public static void mostrarVehiculosPaginados(
            EmpresaAlquilerVehiculos empresa,
            int pagina,
            int tamanioPagina) {

        System.out.println("📄 Página " + pagina + " de vehículos:");

        List<Vehiculo> vehiculos = LogicaEmpresaAlquiler
                .obtenerVehiculosPaginados(empresa, pagina, tamanioPagina);

        if (vehiculos.isEmpty()) {
            System.out.println("❌ No hay vehículos en esta página.");
        } else {
            vehiculos.forEach(v -> System.out.println(
                    "🚗 " + v.getMarca() + " " + v.getModelo()
                            + " | " + v.getMatricula()
            ));
        }
    }

    /**
     * Método estático que muestra los clientes de 2 empresas
     * @param empresa1 de tipo EmpresaAlquilerVehiculos
     * @param empresa2 de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarClientesDe2Empresas(
            EmpresaAlquilerVehiculos empresa1,
            EmpresaAlquilerVehiculos empresa2){

        System.out.println("🤝 Clientes combinados de ambas empresas:");

        List<Cliente> clientes = LogicaEmpresaAlquiler
                .todosLosClientesDe2Empresas(empresa1, empresa2);

        if (clientes.isEmpty()) {
            System.out.println("❌ No hay clientes.");
        } else {
            clientes.forEach(c -> System.out.println(
                    "🔹 " + c.getNombre()
                            + " | DNI: " + c.getDni()
            ));
        }
    }

    /**
     * Método estático que muestra de que potencia disponen nuestro vehículos
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarPotenciaCoches(EmpresaAlquilerVehiculos empresa) {
        System.out.println("🚗 Potencia de los coches de la empresa:");

        int[] potencias = LogicaEmpresaAlquiler
                .potenciaDeCadaCoche(empresa);

        if (potencias.length == 0) {
            System.out.println("❌ No hay coches en la empresa.");
        } else {
            System.out.println("📊 Total coches: " + potencias.length);

            for (int potencia : potencias) {
                System.out.println("⚙️ " + potencia + " CV");
            }
        }
    }

    /**
     * Método estático que muestra el cliente con el alquiler más caro
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarClienteConElAlquilerMasCaro(EmpresaAlquilerVehiculos empresa) {
        Cliente cliente = LogicaEmpresaAlquiler.encontrarClienteConElAlquilerMasCaro(empresa);

        if (cliente == null) {
            System.out.println("❌ No hay clientes en la empresa.");
        } else {
            System.out.println("💰 Cliente con el alquiler más caro: "
                    + cliente.getNombre() + " | DNI: " + cliente.getDni());
        }
    }

    /**
     * Método estático que muestra el coche más barato por día
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarCocheMasBaratoPorDia(EmpresaAlquilerVehiculos empresa) {
        Coche coche = LogicaEmpresaAlquiler.obtenerCocheMasBaratoPorDia(empresa);

        if (coche == null) {
            System.out.println("❌ No hay coches.");
        } else {
            System.out.println("🚗 Coche más barato por día:");
            System.out.println(coche.getMarca() + " " + coche.getModelo() +
                    " | Precio/día: " + coche.getPrecio().getPrecioPorDia());
        }
    }

    /**
     * Método estático que muestra el número de vehículos disponibles
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarNumeroVehiculosDisponibles(EmpresaAlquilerVehiculos empresa) {
        long total = LogicaEmpresaAlquiler.contarTotalVehiculosDisponibles(empresa);

        System.out.println("🚗 Vehículos disponibles: " + total);
    }

    public static void mostrarSiHayFurgonetasConMinimoPlazas(
            EmpresaAlquilerVehiculos empresa, int numeroPlazasIndicadas) {
        boolean resultado = LogicaEmpresaAlquiler
                .comprobarSiHayFurgonetaDisponibleConMinimoPlazas(empresa, numeroPlazasIndicadas);
        if (resultado) {
            System.out.println("✅ Sí, existe al menos una furgoneta disponible con " + numeroPlazasIndicadas + " plazas.");
        } else {
            System.out.println("❌ No existe ninguna furgoneta disponible con " + numeroPlazasIndicadas + " plazas.");
        }
    }

    /**
     * Método estático que muestra si todos los clientes tienen alquileres
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarSiTodosClientesTienenAlquileres(
            EmpresaAlquilerVehiculos empresa) {
        boolean resultado = LogicaEmpresaAlquiler
                .comprobarSiTodosClientesTienenAlquileres(empresa);
        System.out.println("👥 ¿Todos los clientes tienen alquileres?");
        if (resultado) {
            System.out.println("✅ Sí, todos los clientes tienen al menos un alquiler.");
        } else {
            System.out.println("❌ No, hay clientes sin alquileres.");
        }
    }


    /**
     * Método estático que muestra si todos los vehículos tienen menos kilómetros de los que se indican
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @param limiteKilometros de tipo int
     */
    public static void mostrarSiVehiculosCumplenLimiteKilometros(
            EmpresaAlquilerVehiculos empresa, int limiteKilometros) {

        boolean resultado = LogicaEmpresaAlquiler
                .comprobarQueTodosLosVehiculosTenganMenosDeXkilometros(empresa, limiteKilometros);

        System.out.println("🚗 ¿Todos los vehículos tienen menos de "
                + limiteKilometros + " km?");

        if (resultado) {
            System.out.println("✅ Sí, todos los vehículos cumplen el límite.");
        } else {
            System.out.println("❌ No, hay vehículos que superan el límite.");
        }
    }

    /**
     * Muestra el coche disponible más barato por día
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarCocheMasBaratoDisponible(EmpresaAlquilerVehiculos empresa) {
        Optional<Coche> cocheOpt =
                LogicaEmpresaAlquiler.obtenerCocheMasBaratoDisponible(empresa);
        System.out.println("🚗 Coche disponible más barato:");
        cocheOpt.ifPresentOrElse(
                coche -> System.out.println("✅ " + coche.getMarca() + " " + coche.getModelo()
                        + " | Precio/día: " + coche.getPrecio().getPrecioPorDia()),
                () -> System.out.println("❌ No hay coches disponibles")
        );
    }

    /**
     * Método estático que muestra una moto enduro disponible, si es que la hay
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarMotoEnduroDisponible(EmpresaAlquilerVehiculos empresa) {
        Optional<Moto> motoObtenida =
                LogicaEmpresaAlquiler.obtenerMotoEnduroDisponible(empresa);
        System.out.println("🏍️ Moto Enduro disponible:");

        motoObtenida.ifPresentOrElse(
                moto -> System.out.println("✅ " + moto.getMarca() + " " + moto.getModelo()
                        + " | Matrícula: " + moto.getMatricula()),
                () -> System.out.println("❌ No hay motos Enduro disponibles")
        );
    }

    /**
     * Método estático que muesta los ingresos obtenidos por los alquileres actuales
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarIngresosTotalesDeLaEmpresa(EmpresaAlquilerVehiculos empresa) {
        double total = LogicaEmpresaAlquiler.calcularIngresosTotalesDeLaEmpresa(empresa);
        System.out.println("💰 Ingresos totales de los alquileres actuales:");
        System.out.println("Total: " + total + " €");
    }

    /**
     * Método estático que muestra por consola las furgonetas disponibles de la empresa.
     * @param empresa empresa sobre la cual se realizará la consulta
     */
    public static void mostrarFurgonetasDisponibles(EmpresaAlquilerVehiculos empresa) {
        System.out.println("🚐 Furgonetas disponibles:");
        List<Furgoneta> furgonetas = LogicaEmpresaAlquiler
                .obtenerFurgonetasDisponibles(empresa);
        if (furgonetas.isEmpty()) {
            System.out.println("❌ No hay furgonetas disponibles.");
        } else {
            furgonetas.forEach(f -> System.out.println(
                    "- " + f.getMarca() + " " + f.getModelo()
                            + " | Matrícula: " + f.getMatricula()
                            + " | Plazas: " + f.getCapacidadPasajeros()
                            + " | Km: " + f.getKilometros()
            ));
        }
    }

    /**
     * Método estático que muestra los vehículos agrupados por tipo de combustible.
     * @param empresa empresa sobre la cual se realizará la consulta
     */
    public static void mostrarVehiculosAgrupadosPorTipoCombustible(EmpresaAlquilerVehiculos empresa) {

        System.out.println("⛽ Vehículos agrupados por tipo de combustible:");

        Map<TipoCombustible, List<Vehiculo>> vehiculosAgrupados = LogicaEmpresaAlquiler
                .agruparVehiculosPorTipoCombustible(empresa);

        if (vehiculosAgrupados.isEmpty()) {
            System.out.println("❌ No hay vehículos registrados.");
        } else {
            vehiculosAgrupados.forEach((tipoCombustible, vehiculos) -> {
                System.out.println("\n🔹 " + tipoCombustible + ":");

                vehiculos.forEach(vehiculo -> System.out.println(
                        "- " + vehiculo.getMarca() + " " + vehiculo.getModelo()
                                + " | Matrícula: " + vehiculo.getMatricula()
                                + " | Km: " + vehiculo.getKilometros()
                ));
            });
        }
    }

    /**
     * Método estático que muestra por consola las matrículas de los vehículos.
     * @param empresa empresa sobre la cual se realizará la consulta
     */
    public static void mostrarMatriculasVehiculos(EmpresaAlquilerVehiculos empresa) {
        System.out.println("🚗 Matrículas de los vehículos:");
        String matriculas = LogicaEmpresaAlquiler
                .obtenerMatriculasVehiculos(empresa);
        if (matriculas.isEmpty()) {
            System.out.println("❌ No hay vehículos.");
        } else {
            System.out.println(matriculas);
        }
    }

    /**
     * Método estático que muestra el número de vehículos por tipo de combustible.
     * @param empresa empresa sobre la cual se realizará la consulta
     */
    public static void mostrarConteoVehiculosPorCombustible(EmpresaAlquilerVehiculos empresa) {

        System.out.println("⛽ Número de vehículos por tipo de combustible:");

        Map<TipoCombustible, Long> conteo = LogicaEmpresaAlquiler
                .contarVehiculosPorTipoCombustible(empresa);

        if (conteo.isEmpty()) {
            System.out.println("❌ No hay vehículos.");
        } else {
            conteo.forEach((tipo, cantidad) ->
                    System.out.println("🔹 " + tipo + ": " + cantidad));
        }
    }

    /**
     * Método estático que muestra los vehículos agrupados por disponibilidad.
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarVehiculosPorDisponibilidad(EmpresaAlquilerVehiculos empresa) {

        System.out.println("🚗 Vehículos agrupados por disponibilidad:");

        Map<Boolean, List<Vehiculo>> vehiculosParticionados =
                LogicaEmpresaAlquiler.particionarVehiculosPorDisponibilidad(empresa);

        if (vehiculosParticionados.isEmpty()) {
            System.out.println("❌ No hay vehículos registrados.");
        } else {

            System.out.println("\n✅ Vehículos disponibles:");
            vehiculosParticionados.get(true)
                    .forEach(v -> System.out.println(
                            "- " + v.getMarca() + " " + v.getModelo()
                                    + " | Matrícula: " + v.getMatricula()
                                    + " | Km: " + v.getKilometros()
                    ));

            System.out.println("\n❌ Vehículos no disponibles:");
            vehiculosParticionados.get(false)
                    .forEach(v -> System.out.println(
                            "- " + v.getMarca() + " " + v.getModelo()
                                    + " | Matrícula: " + v.getMatricula()
                                    + " | Km: " + v.getKilometros()
                    ));
        }
    }

    /**
     * Método estático que muestra los vehículos mapeados por matrícula.
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarVehiculosMapeadosPorMatricula(EmpresaAlquilerVehiculos empresa) {

        System.out.println("🚗 Vehículos mapeados por matrícula:");

        Map<String, Vehiculo> mapaVehiculos =
                LogicaEmpresaAlquiler.mapearVehiculosPorMatricula(empresa);

        if (mapaVehiculos.isEmpty()) {
            System.out.println("❌ No hay vehículos registrados.");
        } else {
            mapaVehiculos.forEach((matricula, vehiculo) -> System.out.println(
                    "🔹 " + matricula + " -> "
                            + vehiculo.getMarca() + " " + vehiculo.getModelo()
                            + " | Km: " + vehiculo.getKilometros()
            ));
        }
    }

    /**
     * Método estático que muestra las matrículas de los vehículos agrupadas por tipo de combustible.
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarMatriculasPorCombustible(EmpresaAlquilerVehiculos empresa) {

        System.out.println("⛽ Matrículas agrupadas por tipo de combustible:");

        Map<TipoCombustible, List<String>> mapa =
                LogicaEmpresaAlquiler.agruparMatriculasPorCombustible(empresa);

        if (mapa.isEmpty()) {
            System.out.println("❌ No hay vehículos.");
        } else {
            mapa.forEach((tipo, matriculas) -> {
                System.out.println("\n🔹 " + tipo + ":");
                matriculas.forEach(m -> System.out.println("- " + m));
            });
        }
    }

    /**
     * Método estático que muestra el número de vehículos disponibles usando un Collector personalizado.
     * @param empresa de tipo EmpresaAlquilerVehiculos
     */
    public static void mostrarNumeroVehiculosDisponiblesCollector(EmpresaAlquilerVehiculos empresa) {

        int total = LogicaEmpresaAlquiler
                .contarVehiculosDisponiblesCollector(empresa);

        System.out.println("🚗 Vehículos disponibles (Collector personalizado): " + total);
    }

    /**
     * Método estático que muestra un vehículo buscado por matrícula.
     * @param empresa de tipo EmpresaAlquilerVehiculos
     * @param matricula de tipo String
     */
    public static void mostrarVehiculoPorMatricula(
            EmpresaAlquilerVehiculos empresa, String matricula) {

        System.out.println("🔍 Búsqueda de vehículo por matrícula:");

        Optional<Vehiculo> vehiculoOpt =
                LogicaEmpresaAlquiler.buscarVehiculoPorMatricula(empresa, matricula);

        vehiculoOpt.ifPresentOrElse(
                vehiculo -> System.out.println(
                        "✅ Vehículo encontrado: " +
                                vehiculo.getMarca() + " " + vehiculo.getModelo() +
                                " | Matrícula: " + vehiculo.getMatricula() +
                                " | Km: " + vehiculo.getKilometros()
                ),
                () -> System.out.println("❌ No existe vehículo con matrícula: " + matricula)
        );
    }




}
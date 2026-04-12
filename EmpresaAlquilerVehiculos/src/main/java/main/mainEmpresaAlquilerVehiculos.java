package main;

import logica.LogicaEmpresaAlquiler;
import modelo.*;
import vista.MostrarDatos;

public class mainEmpresaAlquilerVehiculos {

    static EmpresaAlquilerVehiculos empresa = insertarDatos();
    static EmpresaAlquilerVehiculos empresa2 = insertarDatosEmpresa2();

    public static void main(String[] args) {

        // MostrarDatos.mostrarEmpresa(empresa);

        MostrarDatos.mostrarPotenciasCoches(empresa);
        MostrarDatos.mostrarVehiculosDisponibles(empresa);
        MostrarDatos.mostrarTodasLasMarcasDeFurgonetasOrdenadasAlfabeticamente(empresa);
        MostrarDatos.mostrarMediaKilometros(empresa);
        MostrarDatos.mostrarVehiculosOrdenadosPorKilometros(empresa);
        MostrarDatos.mostrarVehiculosDisponiblesDebug(empresa);
        MostrarDatos.mostrarClientesMasJovenes(empresa, 3);
        MostrarDatos.mostrarVehiculosPaginados(empresa, 1, 10);
        MostrarDatos.mostrarClientesDe2Empresas(empresa, empresa2);
        MostrarDatos.mostrarPotenciaCoches(empresa);
        MostrarDatos.mostrarClienteConElAlquilerMasCaro(empresa);
        MostrarDatos.mostrarCocheMasBaratoPorDia(empresa);
        MostrarDatos.mostrarNumeroVehiculosDisponibles(empresa);
        MostrarDatos.mostrarSiHayFurgonetasConMinimoPlazas(empresa, 5);
        MostrarDatos.mostrarSiHayFurgonetasConMinimoPlazas(empresa, 3);
        MostrarDatos.mostrarSiTodosClientesTienenAlquileres(empresa);
        MostrarDatos.mostrarSiVehiculosCumplenLimiteKilometros(empresa, 200_000);
        MostrarDatos.mostrarSiVehiculosCumplenLimiteKilometros(empresa, 100_000);
        MostrarDatos.mostrarCocheMasBaratoDisponible(empresa);
        MostrarDatos.mostrarMotoEnduroDisponible(empresa);
        MostrarDatos.mostrarIngresosTotalesDeLaEmpresa(empresa);
        MostrarDatos.mostrarFurgonetasDisponibles(empresa);
        MostrarDatos.mostrarVehiculosAgrupadosPorTipoCombustible(empresa);
        MostrarDatos.mostrarMatriculasVehiculos(empresa);
        MostrarDatos.mostrarConteoVehiculosPorCombustible(empresa);
        MostrarDatos.mostrarVehiculosPorDisponibilidad(empresa);
        MostrarDatos.mostrarVehiculosMapeadosPorMatricula(empresa);
        MostrarDatos.mostrarMatriculasPorCombustible(empresa);
        MostrarDatos.mostrarNumeroVehiculosDisponiblesCollector(empresa);
        MostrarDatos.mostrarVehiculoPorMatricula(empresa, "1234ABC");
    }

    public static EmpresaAlquilerVehiculos insertarDatos() {

        EmpresaAlquilerVehiculos empresa = new EmpresaAlquilerVehiculos("Rentasturcar");

        // =====================
        // VEHÍCULOS (30)
        // =====================

        Coche coche1 = new Coche("B001", "1234ABC", "Toyota", "Corolla", 2020,
                true, 50000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(50, 900),
                TipoCoche.TURISMO, 5, 120, 450);

        Coche coche2 = new Coche("B002", "5678DEF", "BMW", "X5", 2021,
                true, 30000, TipoCombustible.DIESEL,
                new PrecioAlquiler(90, 1600),
                TipoCoche.TODOTERRENO, 5, 250, 600);

        Coche coche3 = new Coche("B006", "9999GGG", "Audi", "A4", 2019,
                true, 40000, TipoCombustible.DIESEL,
                new PrecioAlquiler(80, 1400),
                TipoCoche.TURISMO, 5, 190, 480);

        Coche coche4 = new Coche("B007", "8888HHH", "Seat", "Leon", 2018,
                true, 60000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(45, 800),
                TipoCoche.TURISMO, 5, 110, 400);

        Coche coche5 = new Coche("B008", "7777III", "Tesla", "Model 3", 2023,
                true, 20000, TipoCombustible.ELECTRICO,
                new PrecioAlquiler(100, 1800),
                TipoCoche.TURISMO, 5, 300, 500);

        Coche coche6 = new Coche("B016", "1111PPP", "Mercedes", "Clase A", 2022,
                true, 18000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(85, 1500),
                TipoCoche.TURISMO, 5, 170, 370);

        Coche coche7 = new Coche("B017", "2222QQQ", "Hyundai", "Tucson", 2021,
                true, 35000, TipoCombustible.HIBRIDO,
                new PrecioAlquiler(75, 1300),
                TipoCoche.TODOCAMINO, 5, 150, 520);

        Coche coche8 = new Coche("B018", "3333RRR", "Kia", "Sportage", 2022,
                true, 28000, TipoCombustible.HIBRIDO,
                new PrecioAlquiler(78, 1350),
                TipoCoche.TODOCAMINO, 5, 160, 540);

        Coche coche9 = new Coche("B019", "4444SSS", "Volkswagen", "Golf", 2020,
                true, 45000, TipoCombustible.DIESEL,
                new PrecioAlquiler(55, 950),
                TipoCoche.TURISMO, 5, 130, 380);

        Coche coche10 = new Coche("B020", "5555TTT", "Renault", "Clio", 2021,
                true, 22000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(42, 760),
                TipoCoche.TURISMO, 5, 100, 320);

        Moto moto1 = new Moto("B003", "1111AAA", "Yamaha", "MT-07", 2022,
                true, 15000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(30, 500),
                TipoMoto.NAKED, 700, 2, false);

        Moto moto2 = new Moto("B004", "2222BBB", "Honda", "CBR", 2021,
                true, 10000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(35, 600),
                TipoMoto.DEPORTIVA, 600, 2, false);

        Moto moto3 = new Moto("B009", "6666JJJ", "Kawasaki", "Ninja", 2020,
                true, 12000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(40, 700),
                TipoMoto.DEPORTIVA, 650, 2, false);

        Moto moto4 = new Moto("B010", "5555KKK", "Vespa", "Primavera", 2024,
                true, 8000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(25, 400),
                TipoMoto.SCOOTER, 125, 2, true);

        Moto moto5 = new Moto("B013", "7777END", "KTM", "EXC 300", 2022,
                true, 9000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(45, 800),
                TipoMoto.ENDURO, 300, 1, false);

        Moto moto6 = new Moto("B014", "8888END", "Honda", "CRF 450", 2021,
                true, 12000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(50, 900),
                TipoMoto.ENDURO, 450, 1, false);

        Moto moto7 = new Moto("B015", "9999END", "Yamaha", "WR 250", 2020,
                true, 15000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(40, 700),
                TipoMoto.ENDURO, 250, 1, false);

        Moto moto8 = new Moto("B021", "1212UUU", "Suzuki", "V-Strom 650", 2022,
                true, 17000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(42, 740),
                TipoMoto.TRAIL, 650, 2, true);

        Moto moto9 = new Moto("B022", "2323VVV", "BMW", "R 1250 GS", 2023,
                true, 9000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(65, 1150),
                TipoMoto.TRAIL, 1250, 2, true);

        Moto moto10 = new Moto("B023", "3434WWW", "Piaggio", "Liberty", 2021,
                true, 7000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(22, 360),
                TipoMoto.SCOOTER, 125, 2, true);

        Furgoneta furgo1 = new Furgoneta("B005", "3333CCC", "Mercedes", "Vito", 2021,
                true, 80000, TipoCombustible.DIESEL,
                new PrecioAlquiler(70, 1200),
                140, 3, 10);

        Furgoneta furgo2 = new Furgoneta("B011", "4444LLL", "Renault", "Trafic", 2022,
                true, 90000, TipoCombustible.DIESEL,
                new PrecioAlquiler(65, 1100),
                130, 3, 12);

        Furgoneta furgo3 = new Furgoneta("B012", "3333MMM", "Ford", "Transit", 2023,
                true, 100000, TipoCombustible.DIESEL,
                new PrecioAlquiler(75, 1300),
                150, 3, 15);

        Furgoneta furgo4 = new Furgoneta("B024", "4545XXX", "Peugeot", "Boxer", 2024,
                true, 25000, TipoCombustible.DIESEL,
                new PrecioAlquiler(80, 1400),
                165, 3, 14);

        Furgoneta furgo5 = new Furgoneta("B025", "5656YYY", "Citroen", "Jumpy", 2022,
                true, 42000, TipoCombustible.DIESEL,
                new PrecioAlquiler(68, 1180),
                145, 5, 11);

        Furgoneta furgo6 = new Furgoneta("B026", "6767ZZZ", "Opel", "Vivaro", 2021,
                true, 51000, TipoCombustible.DIESEL,
                new PrecioAlquiler(69, 1190),
                140, 6, 10);

        Furgoneta furgo7 = new Furgoneta("B027", "7878AAA", "Volkswagen", "Crafter", 2023,
                true, 31000, TipoCombustible.DIESEL,
                new PrecioAlquiler(88, 1550),
                177, 3, 16);

        Furgoneta furgo8 = new Furgoneta("B028", "8989BBB", "Toyota", "Proace City", 2024,
                true, 15000, TipoCombustible.ELECTRICO,
                new PrecioAlquiler(72, 1250),
                136, 5, 9);

        Furgoneta furgo9 = new Furgoneta("B029", "9090CCC", "Nissan", "Primastar", 2022,
                true, 47000, TipoCombustible.DIESEL,
                new PrecioAlquiler(71, 1220),
                150, 3, 13);

        Furgoneta furgo10 = new Furgoneta("B030", "1010DDD", "Fiat", "Ducato", 2021,
                true, 62000, TipoCombustible.DIESEL,
                new PrecioAlquiler(78, 1380),
                160, 3, 15);

        empresa.addVehiculo(coche1);
        empresa.addVehiculo(coche2);
        empresa.addVehiculo(coche3);
        empresa.addVehiculo(coche4);
        empresa.addVehiculo(coche5);
        empresa.addVehiculo(coche6);
        empresa.addVehiculo(coche7);
        empresa.addVehiculo(coche8);
        empresa.addVehiculo(coche9);
        empresa.addVehiculo(coche10);

        empresa.addVehiculo(moto1);
        empresa.addVehiculo(moto2);
        empresa.addVehiculo(moto3);
        empresa.addVehiculo(moto4);
        empresa.addVehiculo(moto5);
        empresa.addVehiculo(moto6);
        empresa.addVehiculo(moto7);
        empresa.addVehiculo(moto8);
        empresa.addVehiculo(moto9);
        empresa.addVehiculo(moto10);

        empresa.addVehiculo(furgo1);
        empresa.addVehiculo(furgo2);
        empresa.addVehiculo(furgo3);
        empresa.addVehiculo(furgo4);
        empresa.addVehiculo(furgo5);
        empresa.addVehiculo(furgo6);
        empresa.addVehiculo(furgo7);
        empresa.addVehiculo(furgo8);
        empresa.addVehiculo(furgo9);
        empresa.addVehiculo(furgo10);

        // =====================
        // CLIENTES (12)
        // =====================

        Cliente cliente1 = new Cliente("111A", "Juan", 30, "Calle Mayor 1", "ES123456789");
        Cliente cliente2 = new Cliente("222B", "Maria", 25, "Avenida Sol 22", "ES234567890");
        Cliente cliente3 = new Cliente("333C", "Pedro", 40, "Calle Luna 15", "ES345678901");
        Cliente cliente4 = new Cliente("444D", "Laura", 35, "Plaza España 3", "ES456789012");
        Cliente cliente5 = new Cliente("555E", "Carlos", 28, "Calle Norte 8", "ES567890123");
        Cliente cliente6 = new Cliente("666F", "Ana", 22, "Calle Sur 12", "ES678901234");
        Cliente cliente7 = new Cliente("777G", "Lucia", 27, "Calle Verde 10", "ES789012345");
        Cliente cliente8 = new Cliente("888H", "Miguel", 45, "Avenida Azul 5", "ES890123456");
        Cliente cliente9 = new Cliente("999I", "Sergio", 31, "Calle Río 7", "ES901234567");
        Cliente cliente10 = new Cliente("000J", "Elena", 29, "Avenida Mar 14", "ES012345678");
        Cliente cliente11 = new Cliente("101K", "Raul", 38, "Calle Bosque 2", "ES112233445");
        Cliente cliente12 = new Cliente("202L", "Paula", 24, "Plaza Nueva 9", "ES223344556");

        empresa.addCliente(cliente1);
        empresa.addCliente(cliente2);
        empresa.addCliente(cliente3);
        empresa.addCliente(cliente4);
        empresa.addCliente(cliente5);
        empresa.addCliente(cliente6);
        empresa.addCliente(cliente7);
        empresa.addCliente(cliente8);
        empresa.addCliente(cliente9);
        empresa.addCliente(cliente10);
        empresa.addCliente(cliente11);
        empresa.addCliente(cliente12);

        // =====================
        // ALQUILERES (26)
        // =====================
        // Se crean mediante lógica para mantener consistencia entre activo/disponible

        Alquiler alquiler1 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente1, coche1, new TiempoAlquiler(5, 0));
        Alquiler alquiler2 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente1, moto1, new TiempoAlquiler(3, 0));

        Alquiler alquiler3 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente2, coche2, new TiempoAlquiler(0, 1));
        Alquiler alquiler4 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente2, moto3, new TiempoAlquiler(5, 0));

        Alquiler alquiler5 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente3, furgo1, new TiempoAlquiler(10, 0));
        Alquiler alquiler6 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente3, coche3, new TiempoAlquiler(3, 0));

        Alquiler alquiler7 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente4, coche4, new TiempoAlquiler(7, 0));
        Alquiler alquiler8 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente4, moto4, new TiempoAlquiler(4, 0));

        Alquiler alquiler9 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente5, coche5, new TiempoAlquiler(0, 1));
        Alquiler alquiler10 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente5, furgo2, new TiempoAlquiler(8, 0));

        Alquiler alquiler11 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente6, moto5, new TiempoAlquiler(2, 0));
        Alquiler alquiler12 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente6, coche6, new TiempoAlquiler(6, 0));

        Alquiler alquiler13 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente7, coche7, new TiempoAlquiler(4, 0));
        Alquiler alquiler14 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente7, moto6, new TiempoAlquiler(3, 0));

        Alquiler alquiler15 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente8, furgo3, new TiempoAlquiler(0, 1));
        Alquiler alquiler16 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente8, coche8, new TiempoAlquiler(5, 0));

        Alquiler alquiler17 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente9, moto2, new TiempoAlquiler(2, 0));
        Alquiler alquiler18 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente9, furgo4, new TiempoAlquiler(9, 0));

        Alquiler alquiler19 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente10, coche9, new TiempoAlquiler(6, 0));
        Alquiler alquiler20 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente10, moto8, new TiempoAlquiler(0, 1));

        Alquiler alquiler21 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente11, furgo5, new TiempoAlquiler(3, 0));
        Alquiler alquiler22 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente11, moto9, new TiempoAlquiler(4, 0));

        Alquiler alquiler23 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente12, coche10, new TiempoAlquiler(7, 0));
        Alquiler alquiler24 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente12, furgo6, new TiempoAlquiler(5, 0));

        // Historial extra: alquilar y devolver para que queden alquileres finalizados
        Alquiler alquiler25 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente6, moto7, new TiempoAlquiler(2, 0));
        Alquiler alquiler26 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente3, moto10, new TiempoAlquiler(3, 0));

        // =====================
        // DEVOLUCIONES
        // =====================
        // Dejamos algunos alquileres históricos ya finalizados
        LogicaEmpresaAlquiler.devolverVehiculo(alquiler2);
        LogicaEmpresaAlquiler.devolverVehiculo(alquiler6);
        LogicaEmpresaAlquiler.devolverVehiculo(alquiler8);
        LogicaEmpresaAlquiler.devolverVehiculo(alquiler11);
        LogicaEmpresaAlquiler.devolverVehiculo(alquiler14);
        LogicaEmpresaAlquiler.devolverVehiculo(alquiler17);
        LogicaEmpresaAlquiler.devolverVehiculo(alquiler21);
        LogicaEmpresaAlquiler.devolverVehiculo(alquiler25);
        LogicaEmpresaAlquiler.devolverVehiculo(alquiler26);

        return empresa;
    }

    public static EmpresaAlquilerVehiculos insertarDatosEmpresa2() {

        EmpresaAlquilerVehiculos empresa = new EmpresaAlquilerVehiculos("AutoRent Plus");

        // =====================
        // VEHÍCULOS (6)
        // =====================

        Coche coche1 = new Coche("B101", "1111XXX", "Ford", "Focus", 2020,
                true, 40000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(55, 950),
                TipoCoche.TURISMO, 5, 130, 400);

        Coche coche2 = new Coche("B102", "2222YYY", "Audi", "Q3", 2022,
                true, 25000, TipoCombustible.DIESEL,
                new PrecioAlquiler(85, 1500),
                TipoCoche.TODOCAMINO, 5, 180, 500);

        Moto moto1 = new Moto("B103", "3333ZZZ", "Honda", "CB500", 2021,
                true, 12000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(35, 600),
                TipoMoto.NAKED, 500, 2, false);

        Moto moto2 = new Moto("B104", "4444WWW", "KTM", "390 Duke", 2023,
                true, 6000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(38, 650),
                TipoMoto.NAKED, 390, 2, false);

        Furgoneta furgo1 = new Furgoneta("B105", "5555VVV", "Peugeot", "Expert", 2024,
                true, 18000, TipoCombustible.DIESEL,
                new PrecioAlquiler(74, 1280),
                145, 5, 11);

        Furgoneta furgo2 = new Furgoneta("B106", "6666UUU", "Toyota", "Proace", 2023,
                true, 22000, TipoCombustible.DIESEL,
                new PrecioAlquiler(76, 1320),
                150, 3, 12);

        empresa.addVehiculo(coche1);
        empresa.addVehiculo(coche2);
        empresa.addVehiculo(moto1);
        empresa.addVehiculo(moto2);
        empresa.addVehiculo(furgo1);
        empresa.addVehiculo(furgo2);

        // =====================
        // CLIENTES (6)
        // =====================

        Cliente cliente1 = new Cliente("777G", "Lucia", 27, "Calle Verde 10", "ES999999999");
        Cliente cliente2 = new Cliente("888H", "Miguel", 45, "Avenida Azul 5", "ES888888888");
        Cliente cliente3 = new Cliente("111A", "Juan", 30, "Calle Mayor 1", "ES123456789"); // duplicado
        Cliente cliente4 = new Cliente("303M", "Andrea", 26, "Calle Plata 11", "ES334455667");
        Cliente cliente5 = new Cliente("404N", "David", 33, "Avenida Centro 4", "ES445566778");
        Cliente cliente6 = new Cliente("505O", "Noelia", 41, "Calle Jardín 18", "ES556677889");

        empresa.addCliente(cliente1);
        empresa.addCliente(cliente2);
        empresa.addCliente(cliente3);
        empresa.addCliente(cliente4);
        empresa.addCliente(cliente5);
        empresa.addCliente(cliente6);

        // =====================
        // ALQUILERES (6)
        // =====================

        Alquiler alquiler1 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente1, coche1, new TiempoAlquiler(3, 0));
        Alquiler alquiler2 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente2, moto1, new TiempoAlquiler(5, 0));
        Alquiler alquiler3 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente3, coche2, new TiempoAlquiler(0, 1));
        Alquiler alquiler4 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente4, furgo1, new TiempoAlquiler(4, 0));
        Alquiler alquiler5 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente5, moto2, new TiempoAlquiler(2, 0));
        Alquiler alquiler6 = LogicaEmpresaAlquiler.alquilarVehiculo(cliente6, furgo2, new TiempoAlquiler(6, 0));

        // Dejamos algunos ya devueltos
        LogicaEmpresaAlquiler.devolverVehiculo(alquiler2);
        LogicaEmpresaAlquiler.devolverVehiculo(alquiler5);

        return empresa;
    }
}
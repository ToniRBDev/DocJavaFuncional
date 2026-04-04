package main;

import modelo.*;
import vista.MostrarDatos;

public class mainEmpresaAlquilerVehiculos {

    static EmpresaAlquilerVehiculos empresa = insertarDatos();

    public static void main(String[] args) {
        MostrarDatos.mostrarEmpresa(empresa);
        MostrarDatos.mostrarTodasLasMarcasDeFurgonetasOrdenadasAlfabéticamente(empresa);
    }

    public static EmpresaAlquilerVehiculos insertarDatos() {

        EmpresaAlquilerVehiculos empresa = new EmpresaAlquilerVehiculos("RentCar");

        // =====================
        // VEHICULOS
        // =====================

        Vehiculo coche1 = new Coche("B001", "1234ABC", "Toyota", "Corolla", 2020,
                true, 50000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(50, 900),
                TipoCoche.TURISMO, 5, 120, 450);

        Vehiculo coche2 = new Coche("B002", "5678DEF", "BMW", "X5", 2021,
                true, 30000, TipoCombustible.DIESEL,
                new PrecioAlquiler(90, 1600),
                TipoCoche.TODOTERRENO, 5, 250, 600);

        Vehiculo coche3 = new Coche("B006", "9999GGG", "Audi", "A4", 2019,
                false, 40000, TipoCombustible.DIESEL,
                new PrecioAlquiler(80, 1400),
                TipoCoche.TURISMO, 5, 190, 480);

        Vehiculo coche4 = new Coche("B007", "8888HHH", "Seat", "Leon", 2018,
                true, 60000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(45, 800),
                TipoCoche.TURISMO, 5, 110, 400);

        Vehiculo coche5 = new Coche("B008", "7777III", "Tesla", "Model 3", 2023,
                true, 20000, TipoCombustible.ELECTRICO,
                new PrecioAlquiler(100, 1800),
                TipoCoche.TURISMO, 5, 300, 500);

        Vehiculo moto1 = new Moto("B003", "1111AAA", "Yamaha", "MT-07", 2022,
                true, 15000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(30, 500),
                TipoMoto.NAKED, 700, 2, false);

        Vehiculo moto2 = new Moto("B004", "2222BBB", "Honda", "CBR", 2021,
                false, 10000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(35, 600),
                TipoMoto.DEPORTIVA, 600, 2, false);

        Vehiculo moto3 = new Moto("B009", "6666JJJ", "Kawasaki", "Ninja", 2020,
                true, 12000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(40, 700),
                TipoMoto.DEPORTIVA, 650, 2, false);

        Vehiculo moto4 = new Moto("B010", "5555KKK", "Vespa", "Primavera", 2024,
                true, 8000, TipoCombustible.GASOLINA,
                new PrecioAlquiler(25, 400),
                TipoMoto.SCOOTER, 125, 2, true);

        Vehiculo furgo1 = new Furgoneta("B005", "3333CCC", "Mercedes", "Vito", 2019,
                true, 80000, TipoCombustible.DIESEL,
                new PrecioAlquiler(70, 1200),
                140, 3, 10);

        Vehiculo furgo2 = new Furgoneta("B011", "4444LLL", "Renault", "Trafic", 2018,
                true, 90000, TipoCombustible.DIESEL,
                new PrecioAlquiler(65, 1100),
                130, 3, 12);

        Vehiculo furgo3 = new Furgoneta("B012", "3333MMM", "Ford", "Transit", 2017,
                false, 100000, TipoCombustible.DIESEL,
                new PrecioAlquiler(75, 1300),
                150, 3, 15);

        empresa.addVehiculo(coche1);
        empresa.addVehiculo(coche2);
        empresa.addVehiculo(coche3);
        empresa.addVehiculo(coche4);
        empresa.addVehiculo(coche5);
        empresa.addVehiculo(moto1);
        empresa.addVehiculo(moto2);
        empresa.addVehiculo(moto3);
        empresa.addVehiculo(moto4);
        empresa.addVehiculo(furgo1);
        empresa.addVehiculo(furgo2);
        empresa.addVehiculo(furgo3);

        // =====================
        // CLIENTES
        // =====================

        Cliente cliente1 = new Cliente("111A", "Juan", 30, "Calle Mayor 1", "ES123456789");
        Cliente cliente2 = new Cliente("222B", "Maria", 25, "Avenida Sol 22", "ES234567890");
        Cliente cliente3 = new Cliente("333C", "Pedro", 40, "Calle Luna 15", "ES345678901");
        Cliente cliente4 = new Cliente("444D", "Laura", 35, "Plaza España 3", "ES456789012");
        Cliente cliente5 = new Cliente("555E", "Carlos", 28, "Calle Norte 8", "ES567890123");
        Cliente cliente6 = new Cliente("666F", "Ana", 22, "Calle Sur 12", "ES678901234");

        empresa.addCliente(cliente1);
        empresa.addCliente(cliente2);
        empresa.addCliente(cliente3);
        empresa.addCliente(cliente4);
        empresa.addCliente(cliente5);
        empresa.addCliente(cliente6);

        // =====================
        // ALQUILERES
        // =====================

        Alquiler alquiler1 = new Alquiler(coche1, new TiempoAlquiler(5, 0));
        Alquiler alquiler2 = new Alquiler(moto1, new TiempoAlquiler(3, 0));
        Alquiler alquiler3 = new Alquiler(coche2, new TiempoAlquiler(0, 1));
        Alquiler alquiler4 = new Alquiler(furgo1, new TiempoAlquiler(10, 0));
        Alquiler alquiler5 = new Alquiler(coche4, new TiempoAlquiler(7, 0));
        Alquiler alquiler6 = new Alquiler(moto3, new TiempoAlquiler(2, 0));
        Alquiler alquiler7 = new Alquiler(coche5, new TiempoAlquiler(0, 1));
        Alquiler alquiler8 = new Alquiler(moto4, new TiempoAlquiler(4, 0));
        Alquiler alquiler9 = new Alquiler(furgo2, new TiempoAlquiler(8, 0));
        Alquiler alquiler10 = new Alquiler(moto3, new TiempoAlquiler(5, 0));
        Alquiler alquiler11 = new Alquiler(coche3, new TiempoAlquiler(3, 0));
        Alquiler alquiler12 = new Alquiler(coche4, new TiempoAlquiler(6, 0));

        // Añadir alquileres a cada cliente
        cliente1.addAlquiler(alquiler1);
        cliente1.addAlquiler(alquiler2);

        cliente2.addAlquiler(alquiler3);
        cliente2.addAlquiler(alquiler10);

        cliente3.addAlquiler(alquiler4);
        cliente3.addAlquiler(alquiler11);

        cliente4.addAlquiler(alquiler5);
        cliente4.addAlquiler(alquiler6);

        cliente5.addAlquiler(alquiler7);
        cliente5.addAlquiler(alquiler8);

        cliente6.addAlquiler(alquiler9);
        cliente6.addAlquiler(alquiler12);

        return empresa;
    }
}
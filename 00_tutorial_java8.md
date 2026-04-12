# 📘 Guía de Programación Funcional en Java

> Proyecto práctico basado en el proyecto de empresa de alquiler de vehículos para aplicar los conceptos de programación funcional en Java 8.

---

# 📑 Índice

1. [Introducción a la programación funcional](#intro-funcional)
2. [Interfaces en Java](#Interfaces-en-java)
3. [Expresiones lambda](#expresiones-lambda)
4. [Referencia a métodos](#referencia-a-metodos)
5. [Interfaces funcionales](#Interfaces-funcionales)
6. [Streams](#Streams)
7. [Reducción](#reduccion)
8. [Optional](#Optional)

---

<a id="intro-funcional"></a>
# 🧠 1. Introducción a la programación funcional

## 📌 Definición

La programación funcional es un paradigma que se basa en el uso de funciones como elementos principales, evitando estados mutables y efectos secundarios.

---

## ⚙️ Características principales

* Inmutabilidad (los datos no cambian)
* Funciones puras (misma entrada → misma salida)
* Código declarativo (qué hacer, no cómo hacerlo)

---

## 🆚 Programación imperativa vs funcional

**Imperativo:**

```java
List<Vehiculo> disponibles = new ArrayList<>();
for (Vehiculo vehiculo : empresa.getVehiculos()) {
    if (vehiculo.isDisponible()) {
        disponibles.add(vehiculo);
    }
}
```

**Funcional:**

```java
List<Vehiculo> disponibles = empresa.getVehiculos()
    .stream()
    .filter(Vehiculo::isDisponible)
    .toList();
```

---

## 💡 Idea clave

👉 La programación funcional reduce código, mejora legibilidad y facilita el procesamiento de colecciones.

---

<a id="Interfaces-en-java"></a>
# 🧩 2. Interfaces en Java

## 📌 Definición

Una interfaz define un contrato que las clases deben implementar.

---

## 🔹 Tipos de métodos en interfaces

### ✔ Métodos abstractos

```java
    // Interfaz con métodos abstracto
    public interface ValidadorAlquilerI {
    boolean validarAlquiler();
}
    // La implementamos en la clase Alquiler e implementamos el método abstracto
        @Override
    public boolean validarAlquiler() {
        if (cliente == null || vehiculo == null || tiempoAlquiler == null) {
            return false;
        }
        if (cliente.getEdad()>18) return false;
        if (tiempoAlquiler.getDias()==0 && tiempoAlquiler.getMeses()==0) return false;
        return true;
    }
    //Uso en el main
    alquiler1.validarAlquiler();
```

---

### ✔ Métodos por defecto (default)

Permiten implementar lógica dentro de la interfaz.

```java
    // Interfac con métodos por defecto y estático
    public interface CalculadoraPreciosI {

    default double calcularPrecioAlquiler(Vehiculo vehiculo, TiempoAlquiler tiempoAlquiler ) {
        double tiempoAlquilerPorDias= tiempoAlquiler.getDias()+vehiculo.getPrecio().getPrecioPorDia();
        double tiempoAlquilerPorMeses= tiempoAlquiler.getMeses()+vehiculo.getPrecio().getPrecioPorMes();
        return tiempoAlquilerPorDias+tiempoAlquilerPorMeses;
    }
}
    // Implementación del método por defecto (En este caso se utilizar en el constructor de la clase Alquiler)
        public Alquiler(Cliente cliente, Vehiculo vehiculo, TiempoAlquiler tiempoAlquiler) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.tiempoAlquiler = tiempoAlquiler;
        this.precioTotal = calcularPrecioAlquiler(vehiculo, tiempoAlquiler);
    }
```

---

### ✔ Métodos estáticos (static)

```java
    static double aplicarDescuento(Alquiler alquiler, double porcentaje) {
        double precioAlquiler= alquiler.getPrecioTotal();
        return precioAlquiler - (precioAlquiler * porcentaje / 100);
    }
    // Uso del método estático en el main (No es necesario implementarlo ni usarlo, es un método de utilidad general)
        CalculadoraPreciosI.aplicarDescuento(alquiler1, 25);
```

---

### ✔ Métodos privados

Uso interno dentro de la interfaz.

---
<a id="expresiones-lambda"></a>
# 3. 🔗 Expresiones Lambda

## 📌 Definición

Una lambda es una función anónima que se puede pasar como parámetro.

Permiten representar instancias de interfaces funcionales de forma concisa y se utilizan ampliamente en situaciones donde se requiere pasar una función como argumento a un método o asignarla a una variable.

---

## ✍️ Sintaxis

```java
(parametros) -> expresion
```

Ejemplo:

```java
(vehiculo) -> vehiculo.isDisponible()
```

---

## 💡 Ejemplos

```java
// Filtrar vehículos disponibles
empresa.getVehiculos()
    .forEach(vehiculo -> System.out.println(vehiculo));
```

```java
// Ordenar por kilómetros
empresa.getVehiculos()
    .sort((vehiculo1, vehiculo2) -> vehiculo1.getKilometros() - vehiculo2.getKilometros());
```

---

## ⚠️ Excepciones en lambdas

Las lambdas pueden lanzar excepciones, pero si son checked, deben estar declaradas en la interfaz funcional o gestionarse con try/catch dentro de la lambda.

---

## 🔒 Inmutabilidad y variables capturadas

Las variables usadas dentro de una lambda deben ser final o efectivamente finales.

* final
* o efectivamente finales

```java
int limite = 50000;

empresa.getVehiculos()
    .stream()
    .filter(v -> v.getKilometros() > limite)
    .toList();
```
- No deben  de cambiar su valor después de ser inicializadas.

---

<a id="referencia-a-metodos"></a>
# 4. 🔗 Referencia a métodos

## 📌 Definición

Una referencia a método es una forma abreviada de escribir una expresión lambda cuando simplemente se llama a un método existente.

👉 Es una sintaxis más limpia y legible.

---

## 🧠 Forma general

```java
Clase::metodo
```

---

## 🔹 Equivalencia con lambda

Lambda:
```java
vehiculo -> vehiculo.isDisponible()
```

Referencia a método:
```java
Vehiculo::isDisponible
```

---

## 📚 Tipos de referencias a métodos

### 🔹 1. Método de instancia de un objeto

```java
System.out::println
```

Equivalente a:
```java
x -> System.out.println(x)
```

---

### 🔹 2. Método de instancia de una clase

```java
Vehiculo::getMatricula
```

Equivalente a:
```java
vehiculo -> vehiculo.getMatricula()
```

---

### 🔹 3. Método estático

```java
Math::max
```

Equivalente a:
```java
(a, b) -> Math.max(a, b)
```

---

### 🔹 4. Referencia a constructor

```java
ArrayList::new
```

Equivalente a:
```java
() -> new ArrayList<>()
```

---

## 💡 Ejemplos con el proyecto

### ✔ Imprimir vehículos

```java
empresa.getVehiculos()
    .forEach(System.out::println);
```

---

### ✔ Obtener matrículas

```java
empresa.getVehiculos()
    .stream()
    .map(Vehiculo::getMatricula)
    .toList();
```

---

### ✔ Comprobar disponibilidad

```java
empresa.getVehiculos()
    .stream()
    .filter(Vehiculo::isDisponible)
    .toList();
```

---

## ⚠️ Cuándo usar referencias a métodos

Usa referencias a métodos cuando:

- La lambda solo llama a un método
- No hay lógica adicional

---

## 💡 Idea clave

👉 Las referencias a métodos hacen el código más limpio, pero no siempre sustituyen a las lambdas.

<a id="Interfaces-funcionales"></a>
# 5. ⚙️ Interfaces funcionales

## 📌 Definición

Una interfaz funcional tiene **un único método abstracto**.

---

## 🧪 @FunctionalInterface

```java
@FunctionalInterface
interface Operacion {
    int aplicar(int a, int b);
}

// Implementación usando lambda
Operacion suma = (a, b) -> a + b;

// Uso
int resultado = suma.aplicar(5, 3);
System.out.println(resultado); // 8

// Otra implementación: Multiplicacióm
Operacion multiplicar = (a, b) -> a * b;
System.out.println(multiplicar.aplicar(4, 6)); // 24
```

---

## 📚 Principales interfaces funcionales de Java

### 🔹 Function<T, R>

Transforma un valor. 

```java
Function<Vehiculo, String> obtenerMarca = vehiculo -> vehiculo.getMarca();
```

---

### 🔹 Consumer <T> 

Consume un valor (no devuelve nada)

```java
Consumer<Vehiculo> imprimir = vehiculo -> System.out.println(vehiculo);
```

---

### 🔹 Supplier <R>

Genera valores sin argumentos

```java
Supplier<String> generarCodigo = () -> "ALQ-" + System.currentTimeMillis();
```

---

### 🔹 Predicate <T>

Representa una función que devuelve un boolean

```java
Predicate<Vehiculo> disponible = vehiculo -> vehiculo.isDisponible();
```

---

### 🔹 BiFunction<T, U, R>

Representa una función de acepta 2 argumentos y devuelve un solo valor.

```java
BiFunction<Integer, Integer, Integer> suma = (a, b) -> a + b;
```

---

### 🔹 UnaryOperator <T, T>

Representa una función que acepta un argumento y devuelve un resultado del mismo tipo

```java
UnaryOperator<Double> aplicarIVA = precio -> precio * 1.21;
```

---

### 🔹 BinaryOperator <T,T,T>

Representa una función que acepta 2 argumentos de tipo T y devuelve un resultado del mismo tipo T

```java
BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
```

### 🔹 Interfaces funcionales especializadas (Mejoran el rendimiento)

- Ejemplo: `IntFunction, IntPredicate, IntSupplier, IntConsumer, IntUnaryOperator, IntBinaryOperator....`
---

## 🛠 Interfaces funcionales personalizadas

- Podemos realizar interfaces funcionales personalizadas. Debemos de asegurarnos que sólo tengan un método abstracto
y debedos de etiquetarlas como `@FunctionalInterface`
- Recordamos el primer ejemplo, es una interfaz funcional personalizada:
```java
@FunctionalInterface
interface Operacion {
    int aplicar(int a, int b);
}

// Implementación usando lambda
Operacion suma = (a, b) -> a + b;

// Uso
int resultado = suma.aplicar(5, 3);
System.out.println(resultado); // 8

// Otra implementación: Multiplicacióm
Operacion multiplicar = (a, b) -> a * b;
System.out.println(multiplicar.aplicar(4, 6)); // 24
```
---

# 🚀 A partir de aquí usamos el proyecto práctico de Empresa de alquiler de vehículos

---

<a id="Streams"></a>
# 6. 🌊 Streams

## 📌 Definición

Un Stream es una secuencia de datos que permite procesarlos de forma declarativa.

---

## 🔄 Flujo de un Stream

1. Fuente (colección)
2. Operaciones intermedias
3. Operación terminal

---

## 🔹 Operaciones intermedias
- Son operaciones que devuelven un Stream resultante de una modificación del actual. Se pueden encadenar varias operaciones intermedias.

### filter(Predicate, <Stream<trueElements>>)

Filtra elementos

```java
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
```

---

### map(Function, <Stream<elemtosResultantesFuncion>>)

Transforma el stream a otro stream con otros elementos como resultado de la modificación de los elementos del stream original.


```java
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
```

---

#### 📦 Boxing y Unboxing

* Boxing: int → Integer
* Unboxing: Integer → int

---

### mapToInt / mapToDouble / mapToLong (Permiten operaciones sobre tipos primitivos y mejoran el rendimiento)

```java
/**
 * Metodo estático que calcula la media de kilometros de los vehiculos de la empresa.
 * @param empresa de tipo EmpresaAlquilerVehiculos
 * @return media de kilometros de los vehiculos o 0 si no hay vehiculos
 */
public static double mediaKilometrosVehiculosEmpresa(EmpresaAlquilerVehiculos empresa){
    return empresa.getVehiculos()
            .stream()
            .mapToInt(Vehiculo::getKilometros)
            .average() //La media no puede ser int, devuelve un Optional<Double>
            .orElse(0);
}
```

---

### flatMap

Aplana estructuras, permite unir streams de diferentes niveles en uno solo

```java
/**
 * Método que obtiene todos los alquileres de la empresa utilizando lambda
 * @return lista de alquileres de la empresa
 */
public List<Alquiler> obtenerTodosAlquileresEmpresa(){
    return clientes
            .stream()
            .flatMap(cliente -> cliente.getAlquileres()
                    .stream())
            .toList();
}

/**
 * Método que obtiene todos los alquileres de la empresa utilizando referencia a metodos
 * @return lista de alquileres de la empresa
 */
public List<Alquiler> obtenerTodosAlquileresEmpresa2() {
    return clientes
            .stream()
            .map(Cliente::getAlquileres)
            .flatMap(List::stream)
            .toList();

```

---

### distinct

Elimina duplicados de un stream. Para funcionar, los objetos deben implementar el método `equals` y `hashCode`.

```java
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
```

---

### sorted (<StreamActual, StreamOrdenado>)
Ordena los elementos del stream. Se puede usar con o sin un comparador. Sin comparador se ordena según el orden natural de la clase o mediante el método `compareTo` de la clase

```java
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
```

---

### peek <Consumer>
No modifica el Stream, pero permite realizar operaciones secundarias con los elementos del Stream. Muy útil para depuración del stream. Permite ver los cambios intermedios.


```java
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
```
---

### limit <StreamActual, StreamRecortadoConPrimerosElementos>
Devuelve solo los primeros n elementos del Stream. 

```java
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
```

---

### skip <StreamActual, StreamRecortadoOmitiendoPrimerosElementos>
Devuelve un Stream que omite los primeros n elementos

Muy útil para implementar paginación. 

```java
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
```

---

### concat `<Stream1, Stream2, StreamFinalFusionado>`
Es un método `estático` que concatena dos streams, no es un método de Stream.
```java
//La siguiente operación puede ser útil para evaluar una posible fusión o colaboración entre 2 negocios
/**
 * Método estático que obtiene todos los clientes de 2 empresas
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
```

---

## 🔹 Operaciones terminales
- Producen un resultado o un efecto secundario
- Finalizan el stream
- Sólo pueden ejecutarse una vez por stream

## 🔹 Principales operaciones terminales:

### forEach `<Consumer<T>>`
Aplica una acción a cada elemento del Stream

```java
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
```

---

### toArray `<Stream, Array>`

```java
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
```

---

### min / max
Encuentra el mínimo y el máximo elemento del Stream según, un comparador, excepto que sea un Stream primitivo.

```java
//-----MAX-----
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

//-----MIN-----
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
```

---

### count
Devuelve el número de elementos del Stream

```java
/**
 * Cuenta el número de vehículos disponibles en la empresa.
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
```

---

### anyMatch / allMatch / noneMatch `<Predicate>`
Devuelven true o false aplicando un predicado a todos los elementos del Stream
- `anYMatch` si algún elmento cumple la condición
- `allMatch` si todos los elementos cumplen la condición
- `noneMatch` si ningún elemento cumple la condición


```java
//----ANYMATCH----
/**
 * Comprueba si existe al menos una furgoneta que tenga un número de plazas
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
//----ALLMATCH----
/**
 * Comprueba si todos los vehículos de la empresa tienen un número de kilómetros
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
//----NONEMATCH----
/**
 * Comprueba si todos los clientes de la empresa tienen al menos un alquiler.
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
```

---

### findFirst / findAny. `<Optional>`
- `findFirst`
```java
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
```

- `findAny`
```java
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
```

---

<a id="reduccion"></a>
## 7. 🔻 Reducción

## 📌 Definición

Proceso de convertir múltiples valores en uno solo.

## Principales operaciones de reducción generales: `reduce()` y `collect()`

---

### reduce `<accumulator, BinaryOperator<T>>`
- Reduce los elementos del Stream a un único valor aplicanto iterativamente un operador binario
#### Usos reales:
- elegir el mejor elemento
- concatenar valores
- transformar estructuras
- acumular múltiples cosas
- crear lógica personalizada

- En este caso lo utilizaremos com acumulador
```java
/**
 * Método estático que calcula el importe total generado por todos los alquileres actuales de la empresa.
 * @param empresa empresa sobre la cual se realizará el cálculo
 * @return importe total generado por todos los alquileres
 */
public static double calcularIngresosTotalesDeAlquileresActuales(EmpresaAlquilerVehiculos empresa) {
    return empresa.getClientes()
            .stream()
            .flatMap(cliente -> cliente.getAlquileres().stream())
            .map(Alquiler::getPrecioTotal)
            .reduce(0.0, Double::sum);
}

// Forma completa para entenderlo mejor:
.reduce(0.0, (total, precio) -> total + precio)
```

- En este caso lo utilizaremos para quedarnos con el mejor valor
```java
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
```

---

### collect `(Collector<T,A,R> collector)`
Se utiliza para transformar los elementos de un stream en una forma diferente utilizando un objeto `Collector`

- Las principales operaciones proporcionadas por la clase colectors son: 

#### toList
Almacena en una lista los elementos del Stream

```java
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
```
- La principal diferecia entre .toList() y .collect(Collectors.toList()) es que la lista creada con collect es mutable.
- En el anterior método, una vez que obtenga la lista, le puedo añadir una nueva furgoneta, si hubiera utilizado .toList() no.

---

#### groupingBy `<Function<T,K>,Map>`
- Agrupa los elementos y devuelve el resultado en un Map con clave el método de agrupación y la lista de valores

```java
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
```
---

#### joining `<StreamString, StringConcatenado>`
- Convierte un Stream de String en un único String
- Puedes indicar por parámetro un elemento de separación
```java
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
```
---

#### counting
Cuenta el número de elementos del Stream
- En el siguiente ejemplo lo utilizamos junto un groupinBy (Es muy utilizado)
```java
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
```

---

#### partitioningBy <Predicate, Map<Boolean, List<T>>>
- Divide el Stream en dos grupos según un predicado, devuelve un Map con clave true y false
- Es útil para dividir los elementos en dos grupos basados en una condición.

```java
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
```

#### toMap <Stream<T>, Map<K,V>>
- Convierte un Stream en un Map, indicando cómo obtener la clave y el valor a partir de los elementos del Stream

```java
/**
 * Método estático que convierte la lista de vehículos en un mapa usando la matrícula como clave.
 *
 * <p>
 * Cada vehículo de la empresa se almacena en el mapa donde la clave es su matrícula
 * y el valor es el propio objeto {@code Vehiculo}.
 * </p>
 *
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
```

---

#### mapping <Function, Collector>
- Permite aplicar una función de mapeo a los elementos antes de realizar una operación de reducción
```java
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
                    Collectors.mapping(Vehiculo::getMatricula, Collectors.toList()) //Mapear cada vehículo a su matrícula antes de agruparlos en la lista final
            ));
}
```
---

### Collector por partes
- Un Collector se puede construir por partes utilizando las siguientes funciones:
- `supplier`: Proporciona una nueva instancia del contenedor de resultados
- `accumulator`: Agrega un elemento al contenedor de resultados
- `combiner`: Combina dos contenedores de resultados (en caso de procesamiento paralelo)
- `finisher`: Transforma el contenedor de resultados en el resultado final (opcional, si el contenedor es del mismo tipo que el resultado, no es necesario)

```java
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
                    () -> new int[1],  // 1. supplier
                    (contador, vehiculo) -> contador[0]++,   // 2. accumulator
                    (contenedor1, contenedor2) -> {          // 3. combiner
                        contenedor1[0] += contenedor2[0];
                        return contenedor1;
                    },
                    contador -> contador[0] // 4. finisher
            ));
}
```
### Explicación del `Collector` por partes

En este ejemplo se construye un `Collector` manualmente para contar cuántos vehículos disponibles hay en la empresa.

La estructura general de un `Collector` es:

1. **supplier** → crea el contenedor inicial  
2. **accumulator** → añade elementos al contenedor  
3. **combiner** → une contenedores parciales  
4. **finisher** → transforma el contenedor en el resultado final  

---

#### 1. `supplier`

```java
() -> new int[1]
```

Se encarga de crear el contenedor inicial donde se irá guardando el resultado.

En este caso se usa un array de enteros de una sola posición:

- `int[1]` permite almacenar un contador
- la posición `0` será donde se irá acumulando el total

👉 Al principio el contador vale `0`.

---

#### 2. `accumulator`

```java
(contador, vehiculo) -> contador[0]++
```

Se ejecuta una vez por cada elemento del stream.

Su función es añadir cada vehículo al resultado acumulado.  
Como en este caso queremos contar vehículos, simplemente incrementamos el contador en 1 por cada elemento que llega.

- `contador` es el array que actúa como contenedor
- `vehiculo` es cada vehículo del stream

👉 Cada vez que entra un vehículo disponible, el contador aumenta en 1.

---

#### 3. `combiner`

```java
(contenedor1, contenedor2) -> {
    contenedor1[0] += contenedor2[0];
    return contenedor1;
}
```

Sirve para combinar dos contenedores parciales en uno solo.

Esto es especialmente importante si el stream se ejecuta en paralelo, porque puede haber varios contadores parciales y después hay que unirlos.

En este caso:

- `contenedor1[0]` contiene un recuento parcial
- `contenedor2[0]` contiene otro recuento parcial
- ambos se suman en `contenedor1`

👉 El resultado final será la suma de todos los contadores parciales.

---

#### 4. `finisher`

```java
contador -> contador[0]
```

Transforma el contenedor interno en el resultado final que devolverá el método.

Durante el proceso interno se usa un `int[]`, pero el método no quiere devolver un array, sino un número entero.

Por eso, en el paso final:

- se toma el array `contador`
- se devuelve su posición `0`

👉 Así el resultado final del `Collector` es un `int`.

---

### Resumen conceptual

Este `Collector` sigue el flujo:

crear contenedor → acumular elementos → combinar resultados → devolver valor final

En este caso concreto:

- el contenedor interno es un `int[]`
- cada vehículo disponible suma 1
- si hay varios contadores parciales, se combinan
- al final se devuelve el total como un `int`

---

### Idea importante

Este ejemplo no es la forma más habitual de contar elementos, ya que para eso normalmente se utilizaría:

```java
.count()
```

Pero construirlo manualmente con `Collector.of(...)` es muy útil para entender cómo funciona un `Collector` internamente.

<a id="Optional"></a>
# 8. 📦 Optional

## 📌 Definición

`Optional` es un contenedor de objetos introducido en Java 8 que permite representar de forma explícita que un valor puede estar presente o ausente.

👉 Su objetivo principal es evitar errores como `NullPointerException` y hacer el código más seguro y legible cuando trabajamos con valores que podrían no existir.

---

## 🧠 Idea clave

En lugar de devolver `null`, un método puede devolver un `Optional<T>` para indicar que:

- puede haber un valor
- o puede no haberlo

Esto obliga a tratar ambos casos de manera más clara.

---

## 1. 🏗️ Creación de un Optional

Se puede crear un `Optional` de varias formas:

### 🔹 `Optional.of(valor)`

Crea un `Optional` con un valor que **no puede ser nulo**.

```java
Optional<String> nombre = Optional.of("Juan");
```

⚠️ Si se le pasa `null`, lanzará una excepción `NullPointerException`.

---

### 🔹 `Optional.ofNullable(valor)`

Crea un `Optional` con un valor que **puede ser nulo**.

```java
Optional<String> nombre = Optional.ofNullable("Juan");
Optional<String> nombreVacio = Optional.ofNullable(null);
```

Si el valor es `null`, el resultado será un `Optional` vacío.

---

### 🔹 `Optional.empty()`

Crea directamente un `Optional` vacío.

```java
Optional<String> vacio = Optional.empty();
```

---

## 2. 🔍 Verificar si un Optional contiene valor

Para comprobar si un `Optional` tiene contenido se pueden usar métodos como:

- `isPresent()`
- `isEmpty()`

```java
Optional<String> optionalValue = Optional.of("Hola, mundo");

boolean tieneValor = optionalValue.isPresent(); // true
boolean estaVacio = optionalValue.isEmpty();    // false
```

---

## 3. 📥 Obtener el valor de un Optional

### ⚠️ `get()`

Permite obtener el valor contenido en el `Optional`.

```java
Optional<String> nombre = Optional.of("Juan");
String valor = nombre.get();
```

⚠️ No es la opción más recomendable, porque si el `Optional` está vacío lanzará una excepción `NoSuchElementException`.

Por eso, en la práctica se suelen usar alternativas más seguras.

---

### 🔹 `orElse(valorPorDefecto)`

Devuelve el valor contenido en el `Optional`, o un valor alternativo si está vacío.

```java
Optional<String> nombre = Optional.ofNullable(null);

String resultado = nombre.orElse("Nombre predeterminado");

System.out.println(resultado);
```

Resultado:

```java
Nombre predeterminado
```

---

### 🔹 `orElseGet(Supplier<? extends T> supplier)`

Devuelve el valor contenido en el `Optional`, o genera uno nuevo usando una función si está vacío.

```java
Optional<String> nombre = Optional.ofNullable(null);

String resultado = nombre.orElseGet(() -> "No hay nombre");

System.out.println(resultado);
```

Resultado:

```java
No hay nombre
```

👉 La diferencia con `orElse()` es que aquí el valor alternativo se calcula de forma **perezosa (lazy)**, solo si hace falta.

---

### 🔹 `orElseThrow(Supplier<? extends X> exceptionSupplier)`

Devuelve el valor si está presente, o lanza una excepción personalizada si no lo está.

```java
Optional<String> nombre = Optional.ofNullable(null);

String resultado = nombre.orElseThrow(() ->
        new IllegalArgumentException("El valor no está presente en el Optional")
);
```

---

### 🔹 `ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction)`

Ejecuta una acción si el valor está presente y otra distinta si está ausente.

```java
Optional<String> nombre = Optional.ofNullable("Juan");

nombre.ifPresentOrElse(
        valor -> System.out.println("El nombre es: " + valor),
        () -> System.out.println("No se proporcionó un nombre")
);
```

---

## 4. ⚙️ Operaciones con Optional

Un `Optional` también permite transformar y filtrar su contenido de forma segura.

---

### 🔹 `map()`

Transforma el valor si está presente.

```java
Optional<String> optionalValue = Optional.of("Hola, mundo");
Optional<Integer> lengthOptional = optionalValue.map(String::length);
```

En este caso:

- el `Optional` original contiene un `String`
- tras aplicar `map`, obtenemos un `Optional<Integer>` con la longitud del texto

---

### 🔹 `filter()`

Permite mantener el valor solo si cumple una condición.

```java
Optional<String> nombre = Optional.of("Juan");

Optional<String> resultado = nombre.filter(n -> n.length() > 3);
```

Si la condición no se cumple, el resultado será un `Optional` vacío.

---

### 🔹 `flatMap()`

Se usa cuando la transformación ya devuelve otro `Optional`, evitando crear un `Optional<Optional<T>>`.

```java
Optional<String> texto = Optional.of("123");

Optional<Integer> numero = texto.flatMap(valor -> {
    try {
        return Optional.of(Integer.parseInt(valor));
    } catch (NumberFormatException e) {
        return Optional.empty();
    }
});
```

---

# 🚀 Ejemplos con el proyecto práctico de empresa de alquiler

En tu proyecto ya tienes métodos que trabajan muy bien con `Optional`, por ejemplo:

- buscar el coche disponible más barato
- obtener una moto Enduro disponible

Esto encaja muy bien con este enfoque, porque en esos casos:

- puede existir un resultado
- o puede no existir ninguno

---

### ✔ Obtener el coche disponible más barato

```java
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
```

👉 Aquí se devuelve un `Optional<Coche>` y lo tratamos correctamente cuando llamamos al método:



### ✔ Obtener una moto Enduro disponible

```java
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
```

👉 En este caso también se utiliza `Optional` porque puede que no exista ninguna moto Enduro disponible en ese momento.

---

### ✔ Obtener un vehículo por su matrícula

```java
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

```

👉 En este caso también se utiliza `Optional` porque puede que no exista ningún coche con esa matrícula
---

## ✔ Uso de `ifPresentOrElse` en el proyecto

Tratamos el resultado del Optional de forma clara, mostrando un mensaje si hay resultado o si no lo hay:

```java
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
                    + " | Precio/día: " + coche.getPrecio().getPrecioPorDia()),  // Si el coche está presente
            () -> System.out.println("❌ No hay coches disponibles") // Si el Optional está vacío
    );
}
```

---

```java
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
```
---

```java
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
```


👉 Este enfoque es muy claro porque trata directamente ambos casos:

- si hay valor, se muestra
- si no lo hay, se informa por consola

---

## 📌 Buenas prácticas con Optional

- Usar `Optional` cuando un método puede no devolver resultado
- Evitar usar `get()` salvo que estemos completamente seguros de que hay valor
- Preferir `orElse`, `orElseGet`, `orElseThrow` o `ifPresentOrElse`
- Utilizar `map`, `filter` y `flatMap` para trabajar de forma segura y expresiva

---

## 📌 Resumen final

`Optional` permite expresar de forma clara que un valor puede estar presente o ausente.

Sus ventajas principales son:

- reduce el riesgo de `NullPointerException`
- mejora la legibilidad del código
- obliga a tratar explícitamente el caso en que no haya resultado
- encaja muy bien con Streams y programación funcional
- 

# 📌 Notas finales

* Streams no modifican la colección original
* Son perezosos (lazy)
* Mejoran la legibilidad pero hay que usarlos con criterio

---

# 📘 Guía de Programación Funcional en Java

> Proyecto práctico basado en gestión de alquiler de vehículos

---

# 📑 Índice

1. Introducción a la programación funcional
2. Interfaces en Java
3. Expresiones lambda
4. Interfaces funcionales
5. Streams

---

# 1. 🧠 Introducción a la programación funcional

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

# 2. 🧩 Interfaces en Java

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
//Útil para mostrar los vehículos por páginas en el frontend. 
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
```

---

### concat <Stream1, Stream2, StreamFinalFusionado>
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

### forEach <Consumer<T>>
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

### toArray <>

```java
Object[] array = empresa.getVehiculos()
    .toArray();
```

---

### min / max

```java
Vehiculo maxKm = empresa.getVehiculos()
    .stream()
    .max(Comparator.comparing(Vehiculo::getKilometros))
    .orElse(null);
```

---

### count

```java
long total = empresa.getVehiculos()
    .stream()
    .count();
```

---

### anyMatch / allMatch / noneMatch

```java
boolean hayDisponibles = empresa.getVehiculos()
    .stream()
    .anyMatch(Vehiculo::isDisponible);
```

---

### findFirst / findAny

```java
Vehiculo primero = empresa.getVehiculos()
    .stream()
    .findFirst()
    .orElse(null);
```

---

## 🔻 Reducción

## 📌 Definición

Proceso de convertir múltiples valores en uno solo.

---

### reduce

```java
int sumaKm = empresa.getVehiculos()
    .stream()
    .map(Vehiculo::getKilometros)
    .reduce(0, Integer::sum);
```

---

### collect (Collector)

#### toList

```java
List<Vehiculo> lista = empresa.getVehiculos()
    .stream()
    .toList();
```

---

#### groupingBy

```java
Map<TipoCombustible, List<Vehiculo>> agrupados =
    empresa.getVehiculos()
        .stream()
        .collect(Collectors.groupingBy(Vehiculo::getTipoCombustible));
```

---

#### counting

```java
long total = empresa.getVehiculos()
    .stream()
    .collect(Collectors.counting());
```

---

#### toMap

```java
Map<String, Vehiculo> mapa =
    empresa.getVehiculos()
        .stream()
        .collect(Collectors.toMap(
            Vehiculo::getMatricula,
            v -> v
        ));
```

---

# 📌 Notas finales

* Streams no modifican la colección original
* Son perezosos (lazy)
* Mejoran la legibilidad pero hay que usarlos con criterio

---

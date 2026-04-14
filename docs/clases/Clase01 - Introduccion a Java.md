# Clase 01 — Introducción a Java
## Programación II

---

## Conceptos fundamentales

Java es un lenguaje **orientado a objetos** con sintaxis similar a C.

| Característica | Significado |
|---------------|-------------|
| **Compilado** | El código se traduce a bytecode **antes** de ejecutarse (no en tiempo real). |
| **Estáticamente tipado** | Cada variable tiene un tipo declarado que **no puede cambiar** en ejecución. |
| **Orientado a objetos** | El código se organiza en **clases y objetos**. |

> **Por qué importa**: al ser estáticamente tipado, los errores de tipo se detectan en compilación, no en producción. Esto te obliga a pensar en los tipos de dato desde el principio.

---

## Tipos de dato

### Primitivos

Son parte del lenguaje. Almacenan **valores directamente** en memoria.
**No pueden ser `null`**.

| Tipo | Descripción | Ejemplo |
|------|-------------|---------|
| `int` | Entero 32-bit | `42` |
| `long` | Entero 64-bit | `42L` |
| `byte` | Entero 8-bit | `127` |
| `short` | Entero 16-bit | `32000` |
| `double` | Decimal 64-bit | `3.14` |
| `float` | Decimal 32-bit | `3.14f` |
| `boolean` | Verdadero/Falso | `true` |
| `char` | Carácter Unicode | `'A'` |

### De referencia

Todos los demás tipos. Almacenan la **dirección en memoria** del objeto (un puntero).
**Pueden ser `null`** (no apuntan a ningún objeto).

Los más comunes: `String`, `Object`, y cualquier clase que definas vos.

### Wrappers (envoltorios)

Cada primitivo tiene su **clase wrapper** de referencia:

| Primitivo | Wrapper |
|-----------|---------|
| `int` | `Integer` |
| `boolean` | `Boolean` |
| `double` | `Double` |
| `char` | `Character` |
| `long` | `Long` |

**¿Para qué sirven?** Las colecciones como `ArrayList` no aceptan primitivos, solo objetos. Entonces se "envuelve" el primitivo en su wrapper (**boxing**). Java puede hacerlo automáticamente (**autoboxing**).

---

## Trampas con tipos de dato

### Comparar referencias con `==`

```java
String a = new String("hola");
String b = new String("hola");
System.out.println(a == b);      // false — compara direcciones de memoria
System.out.println(a.equals(b)); // true  — compara contenido
```

> **Regla**: con primitivos `==` compara valores. Con referencias compara si apuntan al **mismo objeto en memoria**. Para comparar contenido: usá `equals()`.

### NullPointerException con wrappers

```java
Integer x = null;
int y = x; // NullPointerException — intenta "desempaquetar" algo que no existe
```

**Solución**:
```java
if (x != null && x == 5) { ... }
// O también:
Objects.equals(x, 5); // null-safe, requiere import java.util.Objects
```

---

## Variables

### Declaración y asignación

```java
// Declarar
int cantidad;

// Declarar y asignar
int cantidad = 5;

// Múltiples en una línea (mismo tipo)
int a, b, c;
int x = 1, y = 2;
```

### Arrays (arreglos)

Un array es una **colección de tamaño fijo** de elementos del mismo tipo.
Los arrays son objetos de referencia (incluso si guardan primitivos).
`.length` devuelve la cantidad de elementos.

```java
// Declaración
String[] nombres;
int[] numeros;

// Asignación (tamaño fijo, no puede cambiar)
nombres = new String[10];
numeros = new int[5];

// Declaración + asignación + inicialización
int[] nums = {1, 2, 3, 4, 5};

// Acceso
System.out.println(nums[0]); // 1 — índice empieza en 0
nums[2] = 99;
```

> **Importante**: si el array se llena, no se puede agrandar. Hay que crear uno nuevo y copiar los elementos. Esto es exactamente lo que implementa `SimpleArrayList` en el TP03.

### Scope (alcance)

El scope define **dónde existe una variable**. Una variable solo existe dentro del bloque `{}` donde fue creada.

```java
{
    int x = 5;
    // x existe aquí
}
// x NO existe aquí — error de compilación si intentás usarla
```

---

## Operadores

### Aritméticos

| Operador | Operación |
|----------|-----------|
| `+` | Suma |
| `-` | Resta |
| `*` | Multiplicación |
| `/` | División (entera si ambos son int) |
| `%` | Módulo / Resto |
| `++` | Incremento en 1 |
| `--` | Decremento en 1 |

```java
int a = 10 / 3;  // = 3 (división entera)
double b = 10.0 / 3; // = 3.333...
int c = 10 % 3;  // = 1 (resto)
```

### Relacionales

| Operador | Significado |
|----------|-------------|
| `==` | Igual |
| `!=` | Distinto |
| `<` | Menor |
| `>` | Mayor |
| `<=` | Menor o igual |
| `>=` | Mayor o igual |
| `!` | Negación lógica |

---

## Estructuras de control

### Condicionales

```java
// if / else if / else
if (condicion) {
    // ...
} else if (otraCondicion) {
    // ...
} else {
    // ...
}

// switch (ideal para múltiples opciones sobre un mismo valor)
switch (variable) {
    case "opcion1":
        // ...
        break;
    case "opcion2":
        // ...
        break;
    default:
        // ...
        break;
}
```

> **Siempre poner `break`** en cada `case` del switch, salvo que intencionalmente quieras que caiga al siguiente.

### Bucles

```java
// while — mientras se cumpla la condición
while (condicion) {
    // ...
}

// for — cuando sabés cuántas veces iterar
for (int i = 0; i < 10; i++) {
    // ...
}

// for-each — para recorrer colecciones/arrays
for (String elemento : coleccion) {
    // ...
}
```

---

## Métodos

Un método es una **pieza de código reutilizable** que realiza una tarea.
Se escriben en **camelCase**.

```java
// Estructura
<tipoRetorno> <nombre>(<tipo> <param>, ...) {
    // cuerpo
    return valor; // si no es void
}

// Ejemplo
int sumar(int a, int b) {
    return a + b;
}

// Sin retorno
void saludar(String nombre) {
    System.out.println("Hola, " + nombre);
}
```

---

## Modificadores de acceso

Controlan **quién puede ver/usar** una clase, campo o método.

| Modificador | Visibilidad |
|-------------|-------------|
| `public` | Cualquier clase puede acceder |
| `private` | Solo accesible dentro de esa clase |
| `protected` | Accesible en la clase y sus subclases |
| *(sin modificador)* | Accesible dentro del mismo package |

> **Regla de oro**: los campos casi siempre son `private`. Los métodos que forman parte de la "interfaz pública" de la clase son `public`. Los métodos internos son `private`.

---

## Clases

Las clases son la **definición** de un objeto (el "plano"). Un objeto es una **instancia** de una clase.

Una clase se compone de:
- **Campos** (variables): el estado del objeto.
- **Métodos**: el comportamiento del objeto.
- **Constructores**: cómo se crea el objeto.

```java
// Sintaxis básica
public class NombreClase {
    // campos
    private int valor;

    // constructor
    public NombreClase(int valor) {
        this.valor = valor;
    }

    // método
    public int getValor() {
        return valor;
    }
}

// Uso
NombreClase obj = new NombreClase(42);
System.out.println(obj.getValor()); // 42
```

### Herencia e Interfaces

```java
// Una clase puede extender SOLO UNA clase
public class Perro extends Animal {
    // hereda todo lo que Animal tenga como protected/public
}

// Puede implementar MÚLTIPLES interfaces
public class Perro extends Animal implements Domestico, Entrenado {
    // debe implementar todos los métodos de las interfaces
}

// Sintaxis completa
public class Car extends Vehicle implements Repairable {
    @Override
    public void repair() {
        // implementación
    }
}
```

> **Herencia** = "es un tipo de". `Perro` **es un** `Animal`.
> **Interfaz** = "puede hacer". `Perro` **puede ser** `Domestico`.

### Clases abstractas

```java
public abstract class Figura {
    // método abstracto: no tiene cuerpo, DEBEN implementarlo las subclases
    protected abstract double calcularArea();

    // método concreto: tiene implementación, las subclases pueden o no sobreescribirlo
    public void mostrarArea() {
        System.out.println("Área: " + calcularArea());
    }
}
```

- Una clase abstracta **no puede instanciarse** directamente (`new Figura()` da error).
- Obliga a sus subclases a implementar los métodos abstractos.
- Puede tener métodos con y sin implementación.

---

## Resumen visual

```
Tipos de dato
├── Primitivos (int, double, boolean...) → almacenan valor, no pueden ser null
└── Referencia (String, Object, arrays, clases) → almacenan dirección, pueden ser null
    └── Wrappers (Integer, Boolean...) → versión objeto de cada primitivo

Clases
├── Campos (private generalmente)
├── Métodos (public los externos, private los internos)
└── Constructores

Herencia: extends (1 sola clase)
Interfaz:  implements (múltiples)
Abstracta: no instanciable, obliga a implementar métodos en subclases
```

# Clase 02 — Introducción a TDAs
## Programación II

---

## ¿Qué es un TDA?

Un **Tipo de Dato Abstracto (TDA)** es un **modelo conceptual** de una estructura de datos.

- Define **qué comportamientos** y **operaciones** tiene una estructura.
- **No importa cómo** está implementado internamente, solo **qué puede hacer**.
- Son independientes del lenguaje de programación.

> **Analogía**: el TDA List es como el concepto de "lista de compras". No importa si la escribís en papel, en el teléfono o en una pizarra — el comportamiento es el mismo: agregar, tachar, ver los ítems.

### Lo importante: qué, no cómo

| Pregunta | Respuesta del TDA |
|----------|-------------------|
| ¿Qué operaciones tiene? | Agregar, remover, obtener, limpiar... |
| ¿Cómo las implementa? | **No le importa al TDA** — eso es la implementación |

---

## Abstract no es lo mismo que `abstract`

Cuidado con esta confusión:

| Concepto | Significado |
|----------|-------------|
| **TDA abstracto** | El TDA es un modelo conceptual, independiente de la implementación |
| **`abstract` en Java** | Keyword que hace que una clase **no pueda instanciarse** y obliga a sus subclases a implementar ciertos métodos |

Son conceptos relacionados pero distintos. El TDA usa `abstract` en Java para expresar que la clase no es completa por sí sola.

---

## Las dos partes de un TDA

Todo TDA tiene:

### 1. Especificación
- Define **qué** hace la estructura.
- Se expresa en Java con una **interfaz** (`interface`).
- Declara los métodos sin implementación.

```java
public interface SimpleList<E> {
    boolean add(E element);
    E remove(int index);
    E get(int index);
    int size();
    boolean isEmpty();
    // ...
}
```

### 2. Implementación
- Define **cómo** se realizan esos comportamientos.
- Se expresa con **clases** que implementan la interfaz.
- Puede haber múltiples implementaciones del mismo TDA.

```java
public class SimpleArrayList<E> implements SimpleList<E> {
    // implementación con array interno
}

public class SimpleLinkedList<E> implements SimpleList<E> {
    // implementación con nodos enlazados
}
```

---

## Implementaciones: estáticas vs dinámicas

Toda implementación de un TDA es o **estática** o **dinámica**.

### Implementación estática

- Internamente usa un **array**.
- El array tiene un tamaño fijo → cuando se llena, se crea uno nuevo más grande y se copian los datos.
- Acceder por índice es **O(1)** (instantáneo).
- Insertar/remover en el medio requiere **mover elementos** → más lento.

```
[ A | B | C | _ | _ | _ | _ | _ ]
  0   1   2   <- libres ->
```

### Implementación dinámica

- Internamente usa **nodos enlazados**.
- Cada nodo guarda un dato + referencia al nodo anterior y/o siguiente.
- No tiene límite de tamaño (mientras haya memoria).
- Insertar/remover es **rápido** si tenés referencia al nodo.
- Acceder por índice requiere **recorrer los nodos** → más lento.

```
[A] <-> [B] <-> [C] <-> [D]
```

### Comparación

| Operación | Array (estática) | Nodos (dinámica) |
|-----------|-----------------|-----------------|
| Acceder por índice | Rápido O(1) | Lento O(n) |
| Insertar al final | Rápido O(1)* | Rápido O(1) |
| Insertar en el medio | Lento O(n) | Rápido O(1)** |
| Memoria | Continua (eficiente) | Dispersa (overhead por nodo) |

*Amortizado — puede ser O(n) cuando hay que hacer resize.
**Si ya tenés la referencia al nodo. Buscar el nodo puede ser O(n).

---

## El TDA List

El TDA List es el primero que se estudia en la materia y la base para entender los demás.

### Concepto

Una lista es una **colección ordenada** de elementos donde:
- Los elementos tienen un **índice** (posición).
- Puede haber **elementos duplicados**.
- El tamaño **puede crecer o decrecer**.

### Interfaz en Java: `List<E>`

Java ya tiene este TDA implementado. La `E` es un **genérico** — significa que la lista puede guardar cualquier tipo de objeto.

### Operaciones principales

| Operación | Descripción |
|-----------|-------------|
| `add(E element)` | Agrega al final, devuelve `true` |
| `add(int index, E element)` | Inserta en una posición específica |
| `remove(int index)` | Remueve por posición, devuelve el elemento |
| `remove(Object o)` | Remueve por referencia, devuelve `true` si existía |
| `get(int index)` | Devuelve el elemento en esa posición |
| `set(int index, E element)` | Reemplaza el elemento en esa posición |
| `clear()` | Vacía la lista |
| `contains(Object o)` | Devuelve `true` si el elemento existe |
| `size()` | Cantidad de elementos |
| `isEmpty()` | Devuelve `true` si no hay elementos |

### Implementaciones nativas de Java

Java tiene dos implementaciones principales del TDA List:

```java
List<String> lista1 = new ArrayList<>();   // implementación estática (array interno)
List<String> lista2 = new LinkedList<>();  // implementación dinámica (nodos enlazados)
```

> La **variable** es del tipo de la interfaz (`List<String>`). El **objeto** es la implementación concreta. Esto es el patrón correcto — programar hacia la interfaz, no hacia la implementación.

---

## Genéricos (Generics)

Los TDAs usan genéricos (`<E>`, `<T>`) para ser **reutilizables con cualquier tipo**.

```java
// Sin genéricos — solo para String
public class ListaDeStrings { ... }

// Con genéricos — funciona con cualquier tipo
public class SimpleList<E> {
    public boolean add(E element) { ... }
    public E get(int index) { ... }
}

// Uso
SimpleList<String> strings = new SimpleArrayList<>();
SimpleList<Integer> numeros = new SimpleArrayList<>();
```

El compilador reemplaza `E` por el tipo real en compilación. Si intentás agregar un `Integer` a una `SimpleList<String>`, es un error en compilación.

---

## Resumen

```
TDA
├── Especificación → interface en Java
│   └── Define QUÉ puede hacer la estructura
└── Implementación → clases que implementan la interface
    ├── Estática → usa array internamente
    │   └── Pros: acceso rápido por índice
    │   └── Contras: tamaño fijo, copiar al crecer
    └── Dinámica → usa nodos enlazados
        └── Pros: inserción/remoción rápida
        └── Contras: acceso por índice lento, más memoria por nodo

TDA List → colección ordenada con índices
├── ArrayList  → implementación estática
└── LinkedList → implementación dinámica
```

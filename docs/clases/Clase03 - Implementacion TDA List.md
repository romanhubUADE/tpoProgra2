# Clase 03 — Implementación del TDA List
## Programación II

---

## Repaso rápido: TDA List

- Interfaz: `List<E>` / `SimpleList<E>`
- Operaciones: `add`, `remove`, `get`, `set`, `clear`, `contains`, `size`, `isEmpty`
- Dos implementaciones: **estática** (array) y **dinámica** (nodos enlazados)

---

## Implementación estática: `SimpleArrayList<E>`

### Estructura interna

Internamente usa un **array** de tipo genérico y una variable `size` que cuenta los espacios ocupados.

```java
public class SimpleArrayList<E> implements SimpleList<E> {
    private E[] elements;
    private int size;
    static final int DEFAULT_CAPACITY = 4;

    // Constructor sin tamaño inicial
    public SimpleArrayList() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    // Constructor con tamaño inicial
    public SimpleArrayList(int initialCapacity) {
        elements = (E[]) new Object[initialCapacity];
        size = 0;
    }
}
```

> **¿Por qué `(E[]) new Object[]`?** Java no permite crear arrays genéricos directamente (`new E[n]` da error). La solución es crear un `Object[]` y castearlo. Es un cast no seguro pero es el patrón estándar.

### `size` vs `length`

```
[ A | B | C | _ | _ | _ | _ | _ ]
  0   1   2   3   4   5   6   7
  <-- size=3 -->  <- espacios vacíos ->
  <---------- elements.length=8 ---------->
```

- `elements.length` = tamaño total del array (capacidad).
- `size` = cantidad de elementos realmente guardados.
- Los índices válidos son `0` a `size-1`.
- Los índices de `size` en adelante están vacíos (`null`).

### Resize: cómo crecer cuando el array se llena

Cuando `size == elements.length`, hay que agrandar.

```java
private void validateSize(int newSize) {
    if (newSize > elements.length) {
        resize();
    }
}

private void resize() {
    E[] newArray = (E[]) new Object[elements.length * 2]; // doble de tamaño
    for (int i = 0; i < size; i++) {
        newArray[i] = elements[i];                         // copiar elementos
    }
    elements = newArray;                                   // reemplazar referencia
}
```

Llamar `validateSize(size + 1)` antes de cada `add`.

### Validar índices

Siempre validar que el índice esté dentro de rango antes de acceder.

```java
private void validateIndex(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}
```

### Correr elementos al insertar/remover en el medio

**Al insertar** en `index`, todos los elementos desde `index` hasta `size-1` se **mueven a la derecha** (suma 1 al índice):

```
Antes:  [ A | B | C | D | _ ]   insertar X en index=1
         0   1   2   3   4

Proceso: D -> índice 4
         C -> índice 3
         B -> índice 2  (mover de derecha a izquierda para no pisar datos)
Después: [ A | X | B | C | D ]
```

```java
// Mover elementos a la derecha (para add)
for (int i = size; i > index; i--) {
    elements[i] = elements[i - 1];
}
elements[index] = element;
size++;
```

**Al remover** en `index`, todos los elementos desde `index+1` hasta `size-1` se **mueven a la izquierda** (resta 1 al índice):

```
Antes:  [ A | B | C | D | _ ]   remover index=1 (B)
         0   1   2   3   4

Proceso: C -> índice 1
         D -> índice 2  (mover de izquierda a derecha para no pisar datos)
Después: [ A | C | D | _ | _ ]
```

```java
// Mover elementos a la izquierda (para remove)
for (int i = index; i < size - 1; i++) {
    elements[i] = elements[i + 1];
}
elements[size - 1] = null; // nullear el último para limpiar referencia
size--;
```

> **¿Por qué nullear el último?** Para que el Garbage Collector de Java pueda liberar ese objeto. Si no lo nulleás, el array mantiene una referencia al objeto aunque no sea "parte de la lista".

### Implementación completa de los métodos clave

```java
public boolean add(E element) {
    validateSize(size + 1);
    elements[size] = element;
    size++;
    return true;
}

public void add(int index, E element) {
    validateIndex(index); // o validar index <= size si se permite insertar al final
    validateSize(size + 1);
    for (int i = size; i > index; i--) {
        elements[i] = elements[i - 1];
    }
    elements[index] = element;
    size++;
}

public E remove(int index) {
    validateIndex(index);
    E removed = elements[index];
    for (int i = index; i < size - 1; i++) {
        elements[i] = elements[i + 1];
    }
    elements[size - 1] = null;
    size--;
    return removed;
}

public boolean remove(Object object) {
    for (int i = 0; i < size; i++) {
        if (elements[i].equals(object)) {
            remove(i);
            return true;
        }
    }
    return false;
}

public E get(int index) {
    validateIndex(index);
    return elements[index];
}

public E set(int index, E element) {
    validateIndex(index);
    E old = elements[index];
    elements[index] = element;
    return old;
}

public void clear() {
    for (int i = 0; i < size; i++) {
        elements[i] = null; // limpiar referencias
    }
    size = 0;
}

public boolean contains(Object object) {
    for (int i = 0; i < size; i++) {
        if (elements[i].equals(object)) return true;
    }
    return false;
}

public int size() { return size; }
public boolean isEmpty() { return size == 0; }
```

---

## Implementación dinámica: `SimpleLinkedList<E>`

### El nodo: `LinkedNode<E>`

La lista doblemente enlazada se construye con nodos. Cada nodo tiene:

```java
public class LinkedNode<E> {
    public E value;
    public LinkedNode<E> next; // referencia al siguiente
    public LinkedNode<E> prev; // referencia al anterior

    public LinkedNode(E value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}
```

```
[prev=null | value=A | next=●]-->[prev=● | value=B | next=●]-->[prev=● | value=C | next=null]
      first                                                              last
```

### Estructura interna de la lista

```java
public class SimpleLinkedList<E> implements SimpleList<E> {
    private LinkedNode<E> first; // referencia al primer nodo
    private LinkedNode<E> last;  // referencia al último nodo
    private int size;
}
```

### Agregar al final: `add(E element)`

```
Antes:  first=[A]<->[B]<->[C]=last

Nuevo nodo D:
  D.prev = last (C)
  C.next = D
  last = D

Después: first=[A]<->[B]<->[C]<->[D]=last
```

```java
public boolean add(E element) {
    LinkedNode<E> newNode = new LinkedNode<>(element);
    if (isEmpty()) {
        first = newNode;
        last = newNode;
    } else {
        newNode.prev = last;
        last.next = newNode;
        last = newNode;
    }
    size++;
    return true;
}
```

### Buscar nodo por índice: `getNodeByIndex(int index)`

No hay acceso directo. Hay que **recorrer los nodos**. Para optimizar, empezar desde el extremo más cercano:

```java
private LinkedNode<E> getNodeByIndex(int index) {
    validateIndex(index);
    LinkedNode<E> current;
    if (index < size / 2) {
        // más cerca del inicio → ir hacia adelante
        current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
    } else {
        // más cerca del final → ir hacia atrás
        current = last;
        for (int i = size - 1; i > index; i--) {
            current = current.prev;
        }
    }
    return current;
}
```

### Agregar en posición: `add(int index, E element)`

Se busca el nodo en `index` y se inserta el nuevo **delante de él**.

**Orden correcto de reconexión** (crítico — si te equivocás de orden perdés referencias):

```
Antes:   ... <-> [A] <-> [B] <-> ...
                  ^nodoActual (index)
Insertar C antes de B:

1. C.prev = A       (C apunta hacia atrás a A)
2. C.next = B       (C apunta hacia adelante a B)
3. A.next = C       (A ahora apunta a C)
4. B.prev = C       (B ahora apunta atrás a C)

Después: ... <-> [A] <-> [C] <-> [B] <-> ...
```

```java
public void add(int index, E element) {
    if (index == size) {
        add(element); // insertar al final
        return;
    }
    LinkedNode<E> current = getNodeByIndex(index);
    LinkedNode<E> newNode = new LinkedNode<>(element);

    newNode.prev = current.prev;
    newNode.next = current;

    if (current.prev != null) {
        current.prev.next = newNode;
    } else {
        first = newNode; // insertar al principio
    }
    current.prev = newNode;
    size++;
}
```

### Remover un nodo: lógica de desconexión

```java
private void removeAndReconnect(LinkedNode<E> toRemove) {
    if (toRemove == first && toRemove == last) {
        // único nodo
        first = null;
        last = null;
    } else if (toRemove == first) {
        first = toRemove.next;
        first.prev = null;
    } else if (toRemove == last) {
        last = toRemove.prev;
        last.next = null;
    } else {
        // nodo en el medio
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
    }
    size--;
}
```

### Remover por índice y por referencia

```java
public E remove(int index) {
    LinkedNode<E> toRemove = getNodeByIndex(index);
    E value = toRemove.value;
    removeAndReconnect(toRemove);
    return value;
}

public boolean remove(Object object) {
    LinkedNode<E> current = first;
    while (current != null) {           // != null, no .equals — el nodo puede no existir
        if (current.value.equals(object)) {
            removeAndReconnect(current);
            return true;
        }
        current = current.next;
    }
    return false;
}
```

> **¿Por qué `!= null` y no `.equals()`?** Porque `null` no es un objeto — no puede ejecutar ningún método. Si intentás `null.equals(algo)` obtenés un `NullPointerException`.

---

## ArrayList vs LinkedList — ¿Cuándo usar cada una?

| Caso de uso | Mejor opción | Motivo |
|-------------|-------------|--------|
| Acceso frecuente por índice | `ArrayList` | O(1) vs O(n) |
| Iteración simple | `ArrayList` | Memoria contigua, más cache-friendly |
| Insertar/remover al principio o medio con referencia | `LinkedList` | No requiere mover elementos |
| Tamaño desconocido y muy variable | `LinkedList` | No hace resize |

> En la práctica, `ArrayList` se usa la mayoría de las veces.

---

## Gotchas importantes

1. **Orden de reconexión de nodos**: siempre asignar `prev` y `next` del nuevo nodo **antes** de modificar los nodos existentes, para no perder referencias.

2. **Nullear al remover en array**: `elements[size - 1] = null` después de mover elementos hacia la izquierda.

3. **Buscar por objeto usa `!= null`**: para recorrer nodos enlazados, la condición de parada es `current != null`, nunca `current.equals(null)`.

4. **`size` ≠ `length`**: `size` es los elementos en uso, `length` es la capacidad total del array.

5. **Casos borde**: siempre considerar lista vacía, insertar al principio, insertar al final, único elemento.

---

## Resumen visual

```
SimpleArrayList<E>
  ┌──────────────────────────────────┐
  │ elements: [ A | B | C | _ | _ ] │
  │ size: 3                          │
  │ elements.length: 5               │
  └──────────────────────────────────┘
  → resize cuando size == length (duplicar array)
  → correr elementos al insertar/remover en el medio

SimpleLinkedList<E>
  first ──> [prev=null|A|next] <──> [prev|B|next] <──> [prev|C|next=null] <── last
  size: 3
  → getNodeByIndex: recorrer desde el extremo más cercano
  → removeAndReconnect: manejar 4 casos (único, primero, último, medio)
```

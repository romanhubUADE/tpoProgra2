# Clase 04 — TDAs Stack y Queue
## Programación II

---

## FIFO y LIFO

Son **estrategias de acceso a datos** en una colección. Definen el orden en que los datos salen.

| Estrategia | Nombre completo | Analogía |
|------------|----------------|----------|
| **LIFO** | Last In, First Out | Pila de platos — el último que pusiste es el primero en salir |
| **FIFO** | First In, First Out | Cola del banco — el primero en llegar es el primero en ser atendido |

```
LIFO (Stack/Pila):        FIFO (Queue/Cola):
  push A                    enqueue A
  push B                    enqueue B
  push C                    enqueue C

  Estado: [A, B, C]         Estado: [A, B, C]
          ↑ top                      ↑ front

  pop → C (el último)       dequeue → A (el primero)
  pop → B                   dequeue → B
  pop → A                   dequeue → C
```

---

## TDA Stack (Pila)

### Concepto

Una pila aplica la estrategia **LIFO**. Los datos se "apilan" — el último en entrar es el primero en salir. Solo se puede acceder/modificar el **tope** de la pila.

### Casos de uso reales

- Historial de deshacer (Ctrl+Z) — el último cambio es el primero en deshacer.
- Pila de llamadas del sistema (call stack) — la función más reciente termina primero.
- Evaluación de expresiones matemáticas.
- Navegación hacia atrás en el browser.

### Interfaz: `SimpleStack<E>`

```java
public interface SimpleStack<E> {
    void push(E element);   // agrega al tope
    E pop();                // remueve el tope y lo devuelve
    E peek();               // devuelve el tope SIN removerlo
    void clear();           // vacía la pila
    int size();             // cantidad de elementos
    boolean isEmpty();      // true si no hay elementos
}
```

| Método | Descripción | ¿Modifica la pila? |
|--------|-------------|-------------------|
| `push(E)` | Agrega al tope | Sí |
| `pop()` | Devuelve y remueve el tope | Sí |
| `peek()` | Solo mira el tope | No |
| `clear()` | Vacía todo | Sí |

---

## TDA Queue (Cola)

### Concepto

Una cola aplica la estrategia **FIFO**. Los datos "hacen fila" — el primero en entrar es el primero en salir. Se agrega al **final** y se saca del **frente**.

### Casos de uso reales

- Procesamiento de tareas en orden de llegada.
- Cola de impresión.
- Mensajes en un sistema de eventos.
- BFS (Breadth-First Search) en grafos.

### Interfaz: `SimpleQueue<E>`

```java
public interface SimpleQueue<E> {
    void enqueue(E element);  // agrega al final
    E dequeue();              // remueve el frente y lo devuelve
    E peek();                 // devuelve el frente SIN removerlo
    void clear();             // vacía la cola
    int size();               // cantidad de elementos
    boolean isEmpty();        // true si no hay elementos
}
```

| Método | Descripción | ¿Modifica la cola? |
|--------|-------------|-------------------|
| `enqueue(E)` | Agrega al final | Sí |
| `dequeue()` | Devuelve y remueve el frente | Sí |
| `peek()` | Solo mira el frente | No |
| `clear()` | Vacía todo | Sí |

---

## Implementaciones

### En Java nativo

```java
// Stack de Java (legado, no se recomienda en código nuevo)
Stack<String> stack = new Stack<>();
stack.push("A");
stack.pop();
stack.peek();

// Queue en Java (interfaz — se usa con LinkedList)
Queue<String> queue = new LinkedList<>();
queue.offer("A"); // equivalente a enqueue
queue.poll();     // equivalente a dequeue
queue.peek();
```

### Lo que implementamos: `SimpleArrayStack`, `SimpleLinkedStack`, `SimpleArrayQueue`, `SimpleLinkedQueue`

Se implementan "desde cero" sin usar las colecciones de Java.

```
stackModule/
├── SimpleStack<E>         (interfaz)
├── SimpleArrayStack<E>    (implementación estática)
└── SimpleLinkedStack<E>   (implementación dinámica)

queueModule/
├── SimpleQueue<E>         (interfaz)
├── SimpleArrayQueue<E>    (implementación estática)
└── SimpleLinkedQueue<E>   (implementación dinámica)
```

---

## Implementación estática (array)

Igual que en `SimpleArrayList`, vuelven los mismos componentes:

```java
private E[] elements;
private int size;
static final int DEFAULT_CAPACITY = 4;
```

Y los mismos helpers:
```java
private void validateSize(int newSize) { ... } // resize si se llena
private void resize() { ... }                  // duplicar array
```

**La diferencia está en dónde se opera:**

- **Stack**: siempre opera en `elements[size-1]` (el tope = el último del array).
- **Queue**: `enqueue` agrega en `size`, `dequeue` saca desde `elements[0]` y corre todo.

```java
// SimpleArrayStack
public void push(E element) {
    validateSize(size + 1);
    elements[size] = element;
    size++;
}

public E pop() {
    if (isEmpty()) throw new NoSuchElementException();
    E value = elements[size - 1];
    elements[size - 1] = null;
    size--;
    return value;
}

public E peek() {
    if (isEmpty()) throw new NoSuchElementException();
    return elements[size - 1];
}
```

```java
// SimpleArrayQueue
public void enqueue(E element) {
    validateSize(size + 1);
    elements[size] = element;
    size++;
}

public E dequeue() {
    if (isEmpty()) throw new NoSuchElementException();
    E value = elements[0];
    // correr todos los elementos hacia la izquierda
    for (int i = 0; i < size - 1; i++) {
        elements[i] = elements[i + 1];
    }
    elements[size - 1] = null;
    size--;
    return value;
}

public E peek() {
    if (isEmpty()) throw new NoSuchElementException();
    return elements[0];
}
```

---

## Implementación dinámica (nodos enlazados)

Se reutiliza `LinkedNode<E>` del TP03 tal cual está.

```java
// La lista guarda first y last
private LinkedNode<E> first;
private LinkedNode<E> last;   // Stack puede omitir first si solo opera en last
private int size;
```

**La diferencia:**
- **Stack**: solo necesita `last` (el tope). Se puede optimizar con solo nodo anterior (lista simplemente enlazada).
- **Queue**: necesita `first` (para dequeue) y `last` (para enqueue).

```java
// SimpleLinkedStack
public void push(E element) {
    LinkedNode<E> newNode = new LinkedNode<>(element);
    if (isEmpty()) {
        last = newNode;
    } else {
        newNode.prev = last;
        last.next = newNode;
        last = newNode;
    }
    size++;
}

public E pop() {
    if (isEmpty()) throw new NoSuchElementException();
    E value = last.value;
    if (size == 1) {
        last = null;
    } else {
        last = last.prev;
        last.next = null;
    }
    size--;
    return value;
}

public E peek() {
    if (isEmpty()) throw new NoSuchElementException();
    return last.value;
}
```

```java
// SimpleLinkedQueue
public void enqueue(E element) {
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
}

public E dequeue() {
    if (isEmpty()) throw new NoSuchElementException();
    E value = first.value;
    if (size == 1) {
        first = null;
        last = null;
    } else {
        first = first.next;
        first.prev = null;
    }
    size--;
    return value;
}

public E peek() {
    if (isEmpty()) throw new NoSuchElementException();
    return first.value;
}
```

---

## `clear()`, `size()`, `isEmpty()` — comunes a ambos

```java
public void clear() {
    if (isEmpty()) return; // no llamar si ya está vacía
    first = null;          // (o solo last para Stack)
    last = null;
    size = 0;
    // En implementación estática: nullear el array + size = 0
}

public int size() { return size; }
public boolean isEmpty() { return size == 0; }
```

---

## Reglas importantes de la consigna

| Método | Regla |
|--------|-------|
| `pop` / `dequeue` | **Chequear que no esté vacío** antes de operar → `NoSuchElementException` |
| `peek` | **Chequear vacío**. Además, **volver al menú** después (repetirlo mostraría lo mismo) |
| `clear` | **No llamar a clear** de la estructura si ya está vacía |

---

## `NoSuchElementException`

Cuando se intenta operar sobre una estructura vacía donde no hay nada que devolver:

```java
import java.util.NoSuchElementException;

public E pop() {
    if (isEmpty()) throw new NoSuchElementException("Stack is empty");
    // ...
}
```

---

## Comparación Stack vs Queue vs List

| TDA | Estrategia | Agrega en | Saca de | Uso típico |
|-----|-----------|-----------|---------|------------|
| **List** | Cualquier posición | Cualquier lugar | Cualquier lugar | Colección general ordenada |
| **Stack** | LIFO | El tope | El tope | Deshacer, recursión |
| **Queue** | FIFO | El final | El frente | Procesamiento en orden |

---

## No usar List internamente

La consigna es explícita: **no usar una Lista internamente** para implementar Stack o Queue. La implementación debe usar array o nodos directamente.

Si hay código en común entre Stack y Queue (por ejemplo `resize`, `LinkedNode`), se puede **reutilizar la clase `LinkedNode`** tal cual, y duplicar el código que sea necesario.

---

## Resumen

```
LIFO → Stack
  push  → agrega al tope
  pop   → saca del tope
  peek  → mira el tope (sin sacar)

FIFO → Queue
  enqueue → agrega al final
  dequeue → saca del frente
  peek    → mira el frente (sin sacar)

Implementaciones:
  Estática  → array + resize + validateSize
  Dinámica  → LinkedNode + first/last + reconexión de nodos

Siempre lanzar NoSuchElementException si pop/dequeue/peek en estructura vacía.
```

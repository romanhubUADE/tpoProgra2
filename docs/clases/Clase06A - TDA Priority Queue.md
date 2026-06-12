# Clase 06A - TDA Priority Queue

## Concepto

Una **PriorityQueue** es una Queue ordenada por **prioridad**. El primer elemento que sale es siempre el de **mayor prioridad**, independientemente del orden de inserción.

Al encolar un elemento se guardan dos cosas: el **valor** y un **valor de prioridad** (generalmente `int`).

> No se garantiza el orden entre elementos con la misma prioridad.

---

## Interfaz

Prácticamente igual que `SimpleQueue`, con estos cambios:

```java
public interface SimplePriorityQueue<E> {
    // Cambios respecto a Queue:
    boolean enqueue(E element, int priority);  // Se agrega la prioridad
    int getHighestPriority();                  // Devuelve la prioridad del primer elemento

    // Sin cambios:
    E dequeue();
    E peek();
    int size();
    boolean isEmpty();
    void clear();
}
```

---

## Implementación Estática

Se mantiene casi todo igual que en `SimpleArrayQueue`, con estos agregados:

- Se agrega un segundo array `int[] priorities`
- Los arrays `elements` y `priorities` se mantienen **sincronizados**: en el mismo índice viven el elemento y su prioridad

### enqueue

1. Inicializar `insertIndex = size`
2. Iterar `priorities` con `for` de `size` hacia `0`
3. Salir del bucle si `priority >= priorities[i]` (encontramos la posición correcta)
4. A medida que iteramos, **correr todo a la derecha** (en ambos arrays)
5. Al terminar el bucle, asignar `elements[insertIndex]` y `priorities[insertIndex]`
6. Los chequeos de validación y `size++` se mantienen igual

---

## Implementación Dinámica

Se reemplaza el nodo base por un nuevo `PriorityLinkedNode<E>` que agrega:

```java
class PriorityLinkedNode<E> {
    E data;
    int priority;     // campo nuevo
    PriorityLinkedNode<E> next;
    PriorityLinkedNode<E> prev;
}
```

- `getHighestPriority()` devuelve la `priority` del nodo `first`

### enqueue

1. Si la cola está vacía: el nodo es `first` y `last`
2. Si no: bucle con `current = last`
3. Iterar con `while` mientras haya un `prev` y `priority < current.priority`
4. Si la nueva prioridad es mayor a la más alta: el nodo pasa a ser el nuevo `first`
5. Si no, se inserta en la posición encontrada reconectando nodos

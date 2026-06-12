# SimpleLinkedPriorityQueue<E>

## Archivo fuente
- `src/priorityQueueModule/SimpleLinkedPriorityQueue.java`

## Tipo
Implementación dinámica de [[SimplePriorityQueue]] con lista doblemente enlazada ordenada por prioridad.

## Estructura interna
- `PriorityLinkedNode<E> first` (mayor prioridad)
- `PriorityLinkedNode<E> last` (menor prioridad)
- `int size`

## Ideas clave
- `enqueue`: si la nueva prioridad es la más alta → nuevo `first`. Si no, recorre desde `last` hacia atrás reconectando nodos.
- `dequeue` saca `first` (siempre el de mayor prioridad).

## Referencias
- [[SimplePriorityQueue]]
- [[PriorityLinkedNode]]

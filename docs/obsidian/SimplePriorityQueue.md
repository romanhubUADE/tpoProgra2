# SimplePriorityQueue<E>

## Archivo fuente
- `src/priorityQueueModule/SimplePriorityQueue.java`

## Rol
Contrato de cola con prioridad. El primer elemento que sale es el de **mayor prioridad** (menor valor numérico = mayor prioridad).

## Métodos
- `void enqueue(E element, int priority)`
- `E dequeue()`
- `E peek()`
- `int getHighestPriority()`
- `int size()`
- `boolean isEmpty()`
- `void clear()`

## Implementaciones
- [[SimpleArrayPriorityQueue]]
- [[SimpleLinkedPriorityQueue]]

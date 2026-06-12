# SimpleArrayPriorityQueue<E>

## Archivo fuente
- `src/priorityQueueModule/SimpleArrayPriorityQueue.java`

## Tipo
Implementación estática de [[SimplePriorityQueue]] con dos arrays sincronizados (`elements[]` y `priorities[]`).

## Ideas clave
- Array ordenado: posición 0 = mayor prioridad.
- `enqueue` itera de derecha a izquierda corriendo elementos hasta encontrar la posición correcta.
- `dequeue` saca de índice 0 y corre todo hacia la izquierda.

## Referencias
- [[SimplePriorityQueue]]

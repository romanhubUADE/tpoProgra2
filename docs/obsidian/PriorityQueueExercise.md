# PriorityQueueExercise

## Archivo fuente
- `src/priorityQueueModule/PriorityQueueExercise.java`

## Herencia
- Extiende [[Exercise]].

## Dependencias
- [[SimplePriorityQueue]]
- Implementación activa por defecto: [[SimpleArrayPriorityQueue]]
- Alternativa para comparar: [[SimpleLinkedPriorityQueue]]

## Flujo (máquina de estados)
- `0` menú
- `1` enqueue (pide valor + prioridad)
- `2` dequeue
- `3` peek
- `4` highest priority
- `5` clear

## Métodos relevantes
- `exerciseLogic()`
- `menuLogic()`
- `enqueueLogic()`
- `dequeueLogic()`
- `peekLogic()`
- `highestLogic()`
- `clearLogic()`
- `readPriority()`
- `printStatus()`

## Reglas implementadas
- Convención: **menor número = mayor prioridad**.
- `dequeue`, `peek` y `high` validan si está vacía.
- `enqueue` valida que la prioridad sea un entero válido.

## Referencias
- [[SimplePriorityQueue]]
- [[SimpleArrayPriorityQueue]]
- [[SimpleLinkedPriorityQueue]]

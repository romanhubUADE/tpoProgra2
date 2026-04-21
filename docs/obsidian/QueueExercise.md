# QueueExercise

## Archivo fuente
- `src/queueModule/QueueExercise.java`

## Herencia
- Extiende [[Exercise]].

## Dependencias
- [[SimpleQueue]]
- Implementación activa por defecto: [[SimpleArrayQueue]]
- Alternativa para comparar: [[SimpleLinkedQueue]]

## Flujo (máquina de estados)
- `0` menú
- `1` enqueue
- `2` dequeue
- `3` peek
- `4` clear

## Métodos relevantes
- `exerciseLogic()`
- `menuLogic()`
- `enqueueLogic()`
- `dequeueLogic()`
- `peekLogic()`
- `clearLogic()`
- `printStatus()`

## Reglas implementadas
- `dequeue` y `peek` validan si está vacía antes de operar.
- `peek` vuelve directo al menú.

## Referencias
- [[SimpleQueue]]
- [[SimpleArrayQueue]]
- [[SimpleLinkedQueue]]

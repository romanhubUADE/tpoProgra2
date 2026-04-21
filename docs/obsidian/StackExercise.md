# StackExercise

## Archivo fuente
- `src/stackModule/StackExercise.java`

## Herencia
- Extiende [[Exercise]].

## Dependencias
- [[SimpleStack]]
- Implementación activa por defecto: [[SimpleArrayStack]]
- Alternativa para comparar: [[SimpleLinkedStack]]

## Flujo (máquina de estados)
- `0` menú
- `1` push
- `2` pop
- `3` peek
- `4` clear

## Métodos relevantes
- `exerciseLogic()`
- `menuLogic()`
- `pushLogic()`
- `popLogic()`
- `peekLogic()`
- `clearLogic()`
- `printStatus()`

## Reglas implementadas
- `pop` y `peek` validan si está vacía antes de operar.
- `peek` vuelve directo al menú (no sentido repetir sin cambio de estado).

## Referencias
- [[SimpleStack]]
- [[SimpleArrayStack]]
- [[SimpleLinkedStack]]

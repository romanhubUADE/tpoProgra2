# Exercise

## Rol
Clase base abstracta para todos los ejercicios de consola.

## Archivo fuente
- `src/application/Exercise.java`

## Estado compartido
- `protected boolean running` → controla loop del ejercicio.
- `protected Scanner scanner` → entrada de usuario compartida.

## Contrato

### `run()`
- Ejecuta un loop `while (running)`.
- En cada iteración llama a `exerciseLogic()`.
- Al finalizar, resetea `running = true` para futuras ejecuciones.

### `protected abstract void exerciseLogic()`
Cada ejercicio implementa su propia máquina de estados/flujo.

## Implementaciones directas
- [[TestExercise]]
- [[ListExercise]]
- [[StackExercise]]
- [[QueueExercise]]
- [[SetExercise]]

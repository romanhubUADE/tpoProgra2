# MainProgram

## Rol
Punto de entrada de la aplicación. Muestra el menú principal y delega en ejercicios concretos.

## Archivo fuente
- `src/application/MainProgram.java`

## Responsabilidades
- Crear un único `Scanner` compartido.
- Mantener el loop principal (`running`).
- Seleccionar y ejecutar una instancia de [[Exercise]].

## Menú principal actual
- `0` Terminar programa
- `1` [[TestExercise]]
- `2` [[ListExercise]]
- `3` [[StackExercise]]
- `4` [[QueueExercise]]
- `5` [[SetExercise]]

## Métodos

### `main(String[] args)`
Arranca la aplicación creando `MainProgram` y llamando a `run()`.

### `run()`
- Abre `Scanner`.
- Mientras `running` sea `true`, llama `selectExercise(scanner)`.
- Si hay ejercicio seleccionado, ejecuta `exercise.run()`.
- Al terminar, cierra `Scanner`.

### `selectExercise(Scanner scanner)`
- Muestra menú.
- Según input, instancia el ejercicio correspondiente.
- Input inválido: vuelve a pedir opción.

## Referencias
- [[Exercise]]
- [[TestExercise]]
- [[ListExercise]]
- [[StackExercise]]
- [[QueueExercise]]
- [[SetExercise]]

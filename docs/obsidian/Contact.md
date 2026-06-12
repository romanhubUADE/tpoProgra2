# Contact

## Archivo fuente
- `src/treeModule/Contact.java`

## Rol
Entidad de dominio del ejercicio de contactos (TP08/TP09). Implementa `Comparable<Contact>`.

## Campos
- `String name`, `String phone`, `String email` (validados, no vacíos).

## Ideas clave
- `compareTo` ordena por **nombre** (`compareToIgnoreCase`) → es lo que usa el árbol para ubicar/buscar.
- Constructor package-private `Contact(String name)`: contacto "solo nombre", útil para buscar/comparar sin cargar phone/email.
- `toString` formatea la fila de la agenda.

## Referencias
- [[ContactsExercise]]
- [[SimpleBST]]

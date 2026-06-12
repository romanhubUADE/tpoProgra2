# ListExercise

## Archivo fuente
- `src/listModule/ListExercise.java`

## Herencia
- Extiende [[Exercise]].

## Dependencias
- [[SimpleList]]
- Implementación activa por defecto: [[SimpleArrayList]]
- Alternativa para comparar: [[SimpleLinkedList]]

## Flujo (máquina de estados)
- `0` menú
- `1` agregar
- `2` remover por índice
- `3` remover por referencia
- `4` clear

## Métodos relevantes
- `exerciseLogic()`
- `menuLogic()`
- `addLogic()`
- `removeByIndexLogic()`
- `removeByReferenceLogic()`
- `clearLogic()`
- `printList()`
- `printStatus()`

## Notas
- Repite operaciones de add/remove sin volver al menú principal.
- Muestra estado de la estructura entre iteraciones.

## TP
- [[TP02 - TDA List]] (uso con listas de Java)
- [[TP03 - Implementacion TDA List]] (implementación propia)

## Referencias
- [[SimpleList]]
- [[SimpleArrayList]]
- [[SimpleLinkedList]]

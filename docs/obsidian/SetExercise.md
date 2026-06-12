# SetExercise

## Archivo fuente
- `src/setModule/SetExercise.java`

## Herencia
- Extiende [[Exercise]].

## Dependencias
- [[SimpleSet]]
- Implementación activa por defecto: [[SimpleArraySet]] para Set A y Set B.
- Alternativa para comparar: [[SimpleLinkedSet]] (cambio manual en constructor).

## Modelo de trabajo
- Mantiene dos conjuntos: **Set A** y **Set B**.
- Permite operar sobre uno seleccionado y luego comparar A/B.

## Flujo (máquina de estados)
- `0` menú principal de Set
- `1` submenú del set seleccionado
- `2` add
- `3` remove
- `4` union (A ∪ B)
- `5` intersect (A ∩ B)
- `6` difference (A - B)
- `7` difference (B - A)

## Métodos relevantes
- `exerciseLogic()`
- `menuLogic()`
- `setMenuLogic()`
- `addLogic()`
- `removeLogic()`
- `unionLogic()`
- `intersectLogic()`
- `differenceABLogic()`
- `differenceBALogic()`
- `repeatOperationQuestion(String operation)`
- `printSetsStatus()`
- `formatSet(SimpleSet<String> set)`

## TP
- [[TP05 - TDA Set]]

## Referencias
- [[SimpleSet]]
- [[SimpleArraySet]]
- [[SimpleLinkedSet]]

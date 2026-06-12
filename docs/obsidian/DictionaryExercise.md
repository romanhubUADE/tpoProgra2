# DictionaryExercise

## Archivo fuente
- `src/dictionaryModule/DictionaryExercise.java`

## Herencia
- Extiende [[Exercise]].

## Dependencias
- [[SimpleDictionary]]
- Implementación activa por defecto: [[SimpleArrayDictionary]]
- Alternativa para comparar: [[SimpleLinkedDictionary]]

## Flujo (máquina de estados)
- `0` menú
- `1` put (key + value)
- `2` get
- `3` remove
- `4` containsKey
- `5` keys
- `6` values
- `7` clear

## Métodos relevantes
- `exerciseLogic()`
- `menuLogic()`
- `putLogic()`
- `getLogic()`
- `removeLogic()`
- `containsKeyLogic()`
- `keysLogic()`
- `valuesLogic()`
- `clearLogic()`
- `printStatus()`
- `formatArray()`

## Reglas implementadas
- `put` informa si fue alta o reemplazo (mostrando el value anterior).
- `get`/`remove` validan si está vacío.

## TP
- [[TP07 - TDA Dictionary]]

## Referencias
- [[SimpleDictionary]]
- [[SimpleArrayDictionary]]
- [[SimpleLinkedDictionary]]

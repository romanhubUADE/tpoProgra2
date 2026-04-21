# SimpleArrayList<E>

## Archivo fuente
- `src/listModule/SimpleArrayList.java`

## Tipo
Implementación estática de [[SimpleList]] usando array dinámico.

## Estructura interna
- `E[] elements`
- `int size`
- `DEFAULT_CAPACITY`

## Ideas clave
- Crece por `resize()` al llenarse.
- Inserción/remoción por índice requiere corrimientos.

## Referencias
- [[SimpleList]]

# Clase 05A - Complejidad Algorítmica

## ¿Qué es la Complejidad Algorítmica?

Describe los **costos** de un algoritmo en dos dimensiones:
- **Complejidad Temporal**: cuánto tarda en ejecutarse
- **Complejidad Espacial**: cuánta memoria consume

No se mide en segundos ni bytes — lo que importa es **cómo escalan esos costos relativo al tamaño del input**.

---

## Notación Big O

Se expresa como `O(n)` donde `n` es el input. Describe el **orden de crecimiento** del algoritmo.

| Notación   | Nombre        | Descripción                                              | Ejemplo                        |
|------------|---------------|----------------------------------------------------------|--------------------------------|
| O(1)       | Constante     | Siempre cuesta lo mismo, sin importar el input           | Acceder a un índice de array   |
| O(log n)   | Logarítmica   | El problema se divide en cada paso                       | Búsqueda binaria               |
| O(n)       | Lineal        | Crece igual que el tamaño del input                      | Búsqueda lineal                |
| O(n log n) | Log-lineal    | Repetir un proceso O(log n) unas n veces                 | Merge Sort                     |
| O(n²)      | Cuadrática    | Crece rápidamente; típico del doble for                  | Recorrer una matriz            |
| O(Cⁿ)      | Exponencial   | Se vuelve inviable rápido                                | Fork Bomb                      |
| O(n!)      | Factorial     | El peor caso posible                                     | Fuerza bruta (todas las combo) |

---

## Mejor caso, peor caso y caso promedio

- **Mejor caso**: el input ideal. Ej: el elemento buscado es el primero → O(1)
- **Peor caso**: el input más desfavorable. Ej: el elemento es el último → O(n)
- **Caso promedio**: estimación estadística del comportamiento general

---

## Complejidad de TDAs

Cada operación de un TDA tiene su propio costo. Varía según si la implementación es **estática** (array) o **dinámica** (nodos enlazados).

### List

| Operación               | Estática         | Dinámica         |
|-------------------------|------------------|------------------|
| Insertar al principio   | O(n) — corre todo| O(1)             |
| Insertar al final       | O(1)*            | O(1)*            |
| Insertar en el medio    | O(n)             | O(n)             |
| Remover al principio    | O(n) — corre todo| O(1)             |
| Remover al final        | O(1)             | O(n)             |
| Acceder por índice      | O(1)             | O(n)             |
| Búsqueda por referencia | O(n)             | O(n)             |

> **Conclusión**: no hay una ganadora clara. Si accedés aleatoriamente seguido → estática. Si modificás el primer elemento seguido → dinámica.

### Stack

| Operación | Estática | Dinámica |
|-----------|----------|----------|
| push      | O(1)     | O(1)     |
| pop       | O(1)     | O(1)     |
| peek      | O(1)     | O(1)     |

> **Empate**: la estática salta por índice; la dinámica tiene referencia al último nodo.

### Queue

| Operación | Estática         | Dinámica |
|-----------|------------------|----------|
| enqueue   | O(1)             | O(1)     |
| dequeue   | O(n) — corre todo| O(1)     |
| peek      | O(1)             | O(1)     |

> **Dinámica más eficiente**: tiene referencia al primer nodo y no necesita correr elementos. En estática existe el *array circular* como solución (no se ve en esta materia).

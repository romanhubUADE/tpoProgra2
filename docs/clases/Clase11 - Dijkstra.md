# Clase 11 - Dijkstra

> Complemento de [[Clase10 - TDA Graph]]: el grafo es la estructura; Dijkstra es el **algoritmo** que la recorre para encontrar caminos mínimos.

## Concepto

El **Algoritmo de Dijkstra** es un algoritmo de **búsqueda en grafos**. Encuentra la **distancia mínima** desde un nodo origen hacia **todos los demás** vértices del grafo.

Fue desarrollado por **Edsger Dijkstra** en 1956. Desde entonces surgieron muchas variantes con ligeras diferencias (por ejemplo, versiones que buscan el camino más corto solo entre **2 nodos** y cortan antes).

### Restricciones

- Solo funciona en **grafos ponderados** (las aristas tienen peso/costo).
- **No admite pesos negativos**: si los hubiera, el algoritmo no garantiza el resultado correcto.

---

## Qué recibe y qué devuelve

- **Recibe**: un **nodo inicial** (origen) y un **grafo**.
- **Devuelve**: una **"tabla"** con, por cada nodo, su **distancia mínima** al origen y su **nodo previo** en ese camino.
- Internamente trabaja con dos colecciones: nodos **visitados** y nodos **no visitados**.

---

## Los pasos

1. **Asignar valores iniciales.**
2. **Seleccionar** el nodo no visitado más cercano al origen.
3. **Actualizar** los valores de los vecinos del nodo seleccionado.
4. **Marcar** el nodo como visitado.
5. **Repetir** hasta visitarlos todos.

### 1. Asignar valores iniciales
- Para el nodo inicial: **distancia 0** y **previo `null`**.
- Para los demás: **distancia infinita** (o máxima, `Integer.MAX_VALUE`) y **previo `null`**.
- Todos los nodos van a una **Cola de Prioridad** (o Cola/Lista). Lo importante es poder **tomar el nodo más cercano al origen**.

### 2. Seleccionar el nodo no visitado más cercano
- De la cola de no visitados, sacamos el **más cercano al origen**.
- La **Cola de Prioridad** hace esto **automáticamente** (la prioridad es el costo).
- Sin ella, habría que ordenar manualmente por otros medios.

### 3. Actualizar los vecinos
- Sumamos el **costo acumulado del nodo actual** + el **costo de la arista** a cada vecino.
- Si esa suma es **menor** al costo total actual del vecino, lo **actualizamos**:
  - nuevo costo del vecino = esa suma,
  - previo del vecino = nodo actual.

### 4. Marcar como visitado
- Agregamos el nodo a la colección de **visitados**.
- Ya conocemos su **distancia mínima** al origen y su **nodo anterior**: no se vuelve a actualizar.

### 5. Repetir
- Seguimos sacando nodos no visitados hasta verlos todos.
- Eventualmente se completa la tabla.

---

## Reconstrucción de caminos

Cuando termina el proceso, la tabla permite **reconstruir el camino** hacia cualquier nodo:

1. Tomamos el nodo destino.
2. Miramos su **previo**, luego el previo del previo, etc.
3. Seguimos hasta llegar al **origen**.

El camino sale **al revés** (de destino a origen), así que se invierte para leerlo de origen a destino.

---

## Implementación

- El grafo se representa con **Lista de Adyacencia** (más legible que la matriz). Ver [[ListGraph]] y [[Edge]].
- La diapo usa `Character` como ejemplo de nodo, pero es **genérico** (`T`).
- Pasos del código:
  1. Inicializar el diccionario resultado (costo 0 al origen, infinito al resto; previos en `null`).
  2. Encolar el origen en una **`SimpleLinkedPriorityQueue`** (no visitados).
  3. Usar un **`SimpleArraySet`** para los visitados.
  4. Bucle `while`: mientras haya algo en la cola, desencolar un nodo, recorrer sus vecinos sumando costos y encolando, aplicar los pasos 3 y 4.
  5. Cuando se visitaron todos, devolver el resultado.

### Decisión de diseño: `PathInfo` en vez de `Edge`

> La diapo propone devolver `SimpleDictionary<T, Edge<T>>`, reutilizando [[Edge]] para guardar el par (previo, costo).
>
> En esta implementación se usa una clase propia [[PathInfo]] (`SimpleDictionary<T, PathInfo<T>>`). **Por qué:** en `Edge` el campo `destination` significa "vértice destino de una arista"; en Dijkstra ese campo sería "vértice **previo** en el camino", que es un concepto distinto y **no implica una arista directa**. Una clase aparte (`previous` + `cost`) **se lee sola** y evita malentendidos. Es una desviación **intencional** y mejor justificada que el ejemplo de la cátedra.

Ver [[DijkstraSolver]].

---

## Complejidad

- Cada nodo se visita una vez; por cada uno se recorren sus vecinos.
- Las estructuras de apoyo (`SimpleDictionary`, `SimpleArraySet`, `SimpleLinkedPriorityQueue`) son **O(n)** en sus búsquedas/inserciones (no usan heap binario ni hashing).
- En consecuencia, esta implementación queda en torno a **O(V² + V·E)** en el peor caso, peor que la versión clásica con heap (**O((V+E)·log V)**), pero coherente con el resto de los TDAs del curso.

---

## Relacionado
- Clase base del TDA: [[Clase10 - TDA Graph]]
- Trabajo práctico: [[TP10 - Graph y Dijkstra]]
- Código: [[DijkstraSolver]] · [[PathInfo]] · [[ListGraph]] · [[Graph]] · [[Edge]]

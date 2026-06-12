---
tp: 10
tda: Graph + Dijkstra
---

# TP10 - Graph y Dijkstra

## Consigna
- [[TP10.pdf|📄 Consigna TP10 (PDF)]]

## Contenidos
- Implementación del TDA **Graph**.
- Implementación del **Algoritmo de Dijkstra** para *pathfinding*.
- Desarrollo de una **aplicación de consola** sobre estos contenidos.

## Qué pide
Desarrollar una app de consola en Java, a elección entre:
- **GPS** — dado un mapa como grafo ponderado, un origen y un destino, calcula el **camino más corto**.
- **Simulador estratégico** — dado un grafo de acciones con costo y un objetivo, calcula el **plan menos costoso** para lograrlo.

### Observaciones de la consigna
- Incluir un **grafo ya hecho** y cargarlo al iniciar la app (editarlo es opcional y se valora).
- Al inicio (y tras cada edición) **imprimir todo el grafo, arista por arista**:
  ```
  A → B: 3
  A → C: 4
  C → B: 1
  ```
- Contemplar **inputs no esperados** sin que rompan la app.

## Aplicación elegida
Se implementó la variante **GPS**, con dominio de **rutas de vuelo con escalas**: aeropuertos = vértices, vuelos = aristas ponderadas. Dado un origen y un destino, calcula la ruta más barata con Dijkstra. El grafo viene precargado y es **editable** (agregar/quitar aeropuertos y vuelos), lo que la consigna valora positivamente.

## Implementado en
- [[Graph]] — interfaz del TDA grafo (agregado `getNeighbors`).
- [[ListGraph]] — implementación con lista de adyacencia (clave-valor).
- [[Edge]] — arista (destino + peso).
- [[DijkstraSolver]] — ✅ algoritmo de Dijkstra (`dijkstraAllNodes`).
- [[PathInfo]] — ✅ par (previo, costo) para la tabla de Dijkstra.
- [[FlightExercise]] — ✅ la app de consola del TP: CRUD del grafo + búsqueda de ruta (Dijkstra + reconstrucción de escalas).

> El viejo `GraphExercise` (CRUD suelto del grafo, no pedido en la cursada) se **eliminó**: su funcionalidad de edición quedó integrada en [[FlightExercise]].

## Clase relacionada
- [[Clase10 - TDA Graph]] (el TDA)
- [[Clase11 - Dijkstra]] (el algoritmo)

## Navegación
← [[TP09 - TDA AVL Tree]]

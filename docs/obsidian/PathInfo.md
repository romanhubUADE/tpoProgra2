# PathInfo

## Archivo fuente
- `src/graphModule/PathInfo.java`

## Qué es
Clase de datos genérica que guarda, para cada vértice, la información del **camino más corto** conocido desde el origen en [[DijkstraSolver]]:

```java
public class PathInfo<T> {
    public T previous;   // nodo anterior en el camino (null = origen o no alcanzado)
    public int cost;     // distancia acumulada desde el origen
}
```

## Por qué existe (y no se reutiliza `Edge`)
La diapo propone usar [[Edge]] para esto. Acá se prefirió una clase aparte porque:
- En `Edge`, `destination` es el **destino de una arista**; en Dijkstra el campo análogo es el **previo del camino**, otro concepto.
- `previous` **no implica una arista directa** hacia el nodo.
- Una clase con nombres propios (`previous` / `cost`) **se lee sola** y evita confusión.

Detalle completo en [[Clase11 - Dijkstra]].

## Usado por
- [[DijkstraSolver]] (como value del diccionario resultado)

## TP / Clase
- [[TP10 - Graph y Dijkstra]] · [[Clase11 - Dijkstra]]

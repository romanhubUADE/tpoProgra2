# Edge<T>

## Archivo fuente
- `src/graphModule/Edge.java`

## Rol
Arista del grafo. Representa la conexión saliente hacia un vértice destino, con su peso. Es la pieza que guarda [[ListGraph]] en cada lista de vecinos.

## Campos
- `T destination`
- `int weight`

## Ideas clave
- Overridea `equals` comparando **ambos** campos (`destination` y `weight`).
- **NO** implementa [[Graph]]: una arista no es un grafo. El peso vive acá, no en el grafo.

## Referencias
- [[Graph]]
- [[ListGraph]]

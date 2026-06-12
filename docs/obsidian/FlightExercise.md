# FlightExercise

## Archivo fuente
- `src/graphModule/FlightExercise.java`

## Qué es
La **aplicación de consola del TP10**: un planificador de rutas de vuelo (GPS de ciudades). Reúne en una sola app el **TDA Graph** (cargar/editar el mapa) y el **Algoritmo de Dijkstra** (calcular la ruta más barata).

> Reemplaza al viejo `GraphExercise` (que era solo un CRUD del grafo y no se pidió en la cursada). Como el TP10 cubre Graph **y** Dijkstra juntos, esta app los integra.

## Herencia
- Extiende [[Exercise]].

## Modelo
- Ciudades (su **nombre**, `String`) = **vértices**. Se eligió `String` y no una clase `City`: la ciudad se identifica solo por su nombre, una clase sería un wrapper sin valor agregado (YAGNI).
- Vuelos con costo = **aristas ponderadas**. Cada vuelo se carga **ida y vuelta** (dos aristas dirigidas con el mismo costo).
- El grafo es un [[Graph]] implementado con [[ListGraph]] (lista de adyacencia, clave-valor).

## Flujo (máquina de estados)
- `0` menú (imprime el mapa al inicio y tras cada edición)
- `1` `fr` — Find cheapest route (origen → destino)
- `2` `ac` — Add city
- `3` `af` — Add flight
- `4` `rc` — Remove city
- `5` `rf` — Remove flight
- `mm` — vuelve al Main Menu

## Lo importante: `findRouteLogic()`
1. Valida origen y destino (no vacíos, existen, distintos).
2. Llama a [[DijkstraSolver]] `dijkstraAllNodes(graph, origin)`.
3. **Caso no alcanzable** (como `A → E` en la diapo): si el destino quedó con costo `Integer.MAX_VALUE`, no hay ruta → avisa y corta **antes** de reconstruir (si no, al seguir los `previous` daría con `null`).
4. **Reconstruye el camino con un [[SimpleStack]]**: apila cada paso desde el destino hacia el origen siguiendo los `previous` de cada [[PathInfo]]. Como el stack es **LIFO**, al desapilar (`pop`) sale en orden origen → … → destino — la inversión sale gratis.
5. Imprime la ruta, el costo total y las escalas (el `size()` del stack se captura **antes** del bucle de `pop`, porque cada `pop` reduce el tamaño).

## Grafo precargado
Red de 8 ciudades (Buenos Aires, Santiago, Sao Paulo, Bogota, Miami, Nueva York, Madrid, Londres). Ejemplo: `Buenos Aires → Nueva York` no es directo; Dijkstra elige `Buenos Aires → Sao Paulo → Miami → Nueva York` (730) por sobre alternativas más caras.

## Reglas / validaciones (inputs no esperados)
- `trim` en todos los inputs; rechaza vacíos.
- **Búsqueda case-insensitive** vía `resolveCity()`: recorre `graph.vertices()` y compara con `equalsIgnoreCase`, devolviendo el nombre canónico. Así "miami", "MIAMI" y "Miami" matchean la misma ciudad (no se usa `toUpperCase`, que rompería nombres con espacios/mayúsculas).
- Costo parseado como entero (`NumberFormatException` controlada) y **debe ser positivo** (Dijkstra no admite pesos negativos).
- Origen/destino inexistentes o iguales se avisan sin romper.
- Imprime el mapa **arista por arista** (`Ciudad -> Ciudad: peso`), como pide la consigna.

## TP / Clase
- [[TP10 - Graph y Dijkstra]] · [[Clase10 - TDA Graph]] · [[Clase11 - Dijkstra]]

## Referencias
- [[Graph]] · [[ListGraph]] · [[Edge]] · [[DijkstraSolver]] · [[PathInfo]] · [[SimpleStack]]

# SimpleLinkedSet<E>

## Archivo fuente
- `src/setModule/SimpleLinkedSet.java`

## Tipo
Implementación dinámica de [[SimpleSet]] usando nodos enlazados.

## Estructura interna
- `LinkedNode<E> first`
- `LinkedNode<E> last`
- `int size`

## Ideas clave
- No admite repetidos.
- Remoción reconecta punteros según caso borde (primero/último/intermedio).
- `union/intersect/difference` retornan un nuevo Set.

## Referencias
- [[SimpleSet]]
- [[LinkedNode]]

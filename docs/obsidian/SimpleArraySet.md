# SimpleArraySet<E>

## Archivo fuente
- `src/setModule/SimpleArraySet.java`

## Tipo
Implementación estática de [[SimpleSet]] usando array dinámico.

## Ideas clave
- No admite repetidos (`add` valida `contains`).
- Remoción optimizada: reemplaza con el último elemento.
- `union/intersect/difference` retornan un nuevo Set.

## Referencias
- [[SimpleSet]]

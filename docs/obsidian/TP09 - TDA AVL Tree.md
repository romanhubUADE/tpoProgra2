---
tp: 9
tda: AVL Tree
---

# TP09 - TDA AVL Tree

## Consigna
- [[TP09.pdf|📄 Consigna TP09 (PDF)]]

## Contenidos
- Implementación del TDA **AVL Tree** (BST autobalanceado).

## Qué pide
**Modificar el TP08 para que use AVL en vez de BST.** El árbol debe rebalancearse correctamente en inserción y remoción. Hacer override de funciones según necesario, y cambiar funciones de `private` a `protected` donde haga falta.

## Implementado en
- [[SimpleAVL]] — `extends` [[SimpleBST]]; overridea `insertRecursive`/`removeRecursive` para rebalancear con rotaciones (LL/RR/LR/RL).
- [[TreeNode]] — se le agregó el campo `height`.
- [[SimpleBST]] — los métodos recursivos pasaron a `protected`.
- [[ContactsExercise]] — ahora instancia `SimpleAVL` (sigue corriendo igual, ahora balanceado).

## Clase relacionada
- [[Clase09 - Arbol AVL]]

## Navegación
← [[TP08 - TDA Binary Search Tree]] · [[TP10 - Graph y Dijkstra]] →

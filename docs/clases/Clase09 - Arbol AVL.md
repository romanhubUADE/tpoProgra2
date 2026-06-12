# Clase 09 - Árbol AVL

## Factor de Balance (en BST)

**Factor de Balance (FB)** de un nodo = **altura del subárbol izquierdo − altura del subárbol derecho** (no se cuenta el root como referencia externa, se mide por nodo).

Un BST común puede quedar **desbalanceado** al insertar o remover (en el peor caso, degenera en una "lista" y pierde la eficiencia logarítmica).

---

## Altura de un (sub)árbol

Cantidad de aristas hasta la hoja más lejana. Se calcula **recursivamente**:

- **Caso base** (`current == null`) → devolvemos **-1** (por consistencia).
- Un árbol de **1 nodo tiene altura 0**.
- A cada llamado **sumamos 1**, y elegimos la **altura máxima** entre ambos hijos (`Math.max`).

```java
altura(node) = node == null ? -1 : 1 + max(altura(node.left), altura(node.right))
```

---

## AVL Tree

Un **AVL Tree** es un BST que **siempre está balanceado**: el factor de balance de cada nodo **solo puede ser −1, 0 o 1**. Su nombre viene de sus creadores, **Adelson-Velskii y Landis**.

> Como **es-un** BST, hereda inserción, remoción, búsqueda y recorridos. Solo agrega el rebalanceo.

---

## Rotaciones

Cuando un nodo se desbalancea (FB fuera de {−1, 0, 1}), se aplican **rotaciones**: cambiar de lugar dos nodos (y reubicar a sus hijos). Pueden ser hacia la **izquierda** (contrarreloj) o **derecha** (reloj).

### Los 4 casos

| Caso | Condición | Rotación |
|------|-----------|----------|
| **LL** | FB > 1 y FB(hijo izq) ≥ 0 | rotación **derecha** |
| **RR** | FB < −1 y FB(hijo der) ≤ 0 | rotación **izquierda** |
| **LR** | FB > 1 y FB(hijo izq) < 0 | rotación **izquierda** del hijo, luego **derecha** |
| **RL** | FB < −1 y FB(hijo der) > 0 | rotación **derecha** del hijo, luego **izquierda** |

Refinado como jerarquía:
- **Casos Lx** (FB > 1): si FB(izq) ≥ 0 → **LL**; si FB(izq) < 0 → **LR**.
- **Casos Rx** (FB < −1): si FB(der) ≤ 0 → **RR**; si FB(der) > 0 → **RL**.

### Pseudocódigo de rotación derecha

```
rotateRight(Node y):
    Node x  = y.izq
    Node t2 = x.der
    x.der = y
    y.izq = t2
    return x
```

La rotación **respeta todas las condiciones del BST** antes y después: el subárbol que se mueve (`t2` / "B") es mayor que `x` y menor que `y`, así que termina como hijo correcto. Al ser una estructura con nodos, **no hace falta tocar nada más**.

---

## En el proyecto

- [[SimpleAVL]] `extends` [[SimpleBST]]: overridea `insertRecursive` y `removeRecursive` para, tras cada cambio, **actualizar alturas y rebalancear**.
- El [[TreeNode]] guarda un campo `height`.
- En `SimpleBST` se cambiaron los recursivos de `private` a **`protected`** para poder overridearlos (TP09).
- [[ContactsExercise]] pasa a instanciar `SimpleAVL` en vez de `SimpleBST`.

# Clase 08 - BST y Búsquedas

## Árbol Binario

Un **Binary Tree** cumple todas las condiciones de un [[Clase07B - Introduccion a Arboles|árbol]], pero **cada nodo puede tener hasta dos hijos**: **izquierdo** y **derecho**. Lo mismo aplica a los subárboles (izquierdo y derecho).

---

## BST (Binary Search Tree)

En español: **Árbol Binario de Búsqueda** o **ABB**. Sus nodos están **ordenados de izquierda a derecha**:

- Todos los nodos del **subárbol izquierdo** de un nodo son **menores**.
- Todos los nodos del **subárbol derecho** son **mayores**.
- Para cumplir esto, los nodos **se insertan ya ordenados**.

> Si no se usan `<=` o `>=`, el árbol **no admite duplicados**.

---

## Inserción

Es **recursiva**, con un *wrapper* público:

```java
public void insert(E value)                                   // wrapper para el usuario
private TreeNode<E> insertRecursive(TreeNode<E> current, E value)
```

- `insertRecursive` **devuelve el nodo que queda en ese lugar** post inserción. Hay que reasignar:
  - `root = insertRecursive(root, value);`
  - `current.left = insertRecursive(current.left, value);`
- Si el nodo es `null` → crea un nodo nuevo con ese valor y lo devuelve.
- Si el valor es **menor** → va al **hijo izquierdo**; si es **mayor** → al **derecho** (recursivo).
- **Siempre al final devuelve `current`.**

---

## Comparación genérica

Para ordenar y buscar de forma genérica usamos una **comparación** que devuelve un `int`:

- `-1` = menor → izquierda.
- `1` = mayor → derecha.
- `0` = igual → misma ubicación (¡lo encontramos!).

La más común es `int compareTo(T other)`, que implementan las clases que extienden `Comparable<T>`. Al ser genérica, sirve para datos no numéricos (ej: contactos por nombre). **El orden importa**: comparamos el valor buscado contra el del nodo.

---

## Remoción

Coordina tres pasos: **encontrar** el nodo, **eliminar la referencia** en su padre, y **reemplazar** por un hijo si lo hubiera. Una vez encontrado, hay **tres casos**:

1. **Hoja** (sin hijos): devolvemos `null` (se guarda en la referencia del padre).
2. **Un hijo**: devolvemos ese hijo (movemos la referencia de lugar).
3. **Dos hijos**: buscamos un **sucesor** (generalmente el **mínimo del subárbol derecho** — iterar `current = current.left` hasta `null`). Reemplazamos el valor de `current` por el del sucesor, y luego removemos el sucesor con más llamados recursivos. Al final devolvemos `current`.

---

## Búsquedas / Recorridos

### DFS — Depth First Search (en profundidad)

Prioriza profundizar antes de ir a un hermano. Natural de programar **recursivamente**. Cuatro pasos: chequear que el nodo exista, **procesar el actual**, procesar izquierdo, procesar derecho. Según el **orden** de esos pasos:

- **Pre-Order**: actual → izq → der. Respeta la jerarquía ("topológicamente ordenado"). Sirve para copiar un árbol.
- **In-Order**: izq → actual → der. Sobre un BST, **devuelve la colección ordenada**.
- **Post-Order**: izq → der → actual. Sirve para limpiar árboles ("¿puedo borrar este nodo sin afectar a otros?").

### BFS — Breadth First Search (en ancho)

Recorre **todo un nivel antes de bajar** al siguiente (*level-order*). Natural de programar **iterativamente**, usando una **Queue** para respetar el orden.

---

## En el proyecto

- `treeModule`: [[SimpleBST]] (implementación), [[TreeNode]], [[Contact]] (`Comparable`), [[ContactsExercise]].
- `inOrder()` devuelve los contactos **ordenados alfabéticamente**.

# SimpleBST<T extends Comparable<T>>

## Archivo fuente
- `src/treeModule/SimpleBST.java`

## Rol
Árbol Binario de Búsqueda (BST/ABB). Mantiene los elementos **ordenados** (menores a la izquierda, mayores a la derecha) usando `compareTo`. No admite duplicados.

## Métodos públicos
- `void insert(T element)`
- `void remove(T element)`
- `T search(T key)`
- `boolean contains(T element)`
- `Object[] inOrder()` — recorrido in-order → colección ordenada
- `int size()` / `boolean isEmpty()` / `void clear()`

## Métodos protegidos (overridables por AVL)
- `protected TreeNode<T> insertRecursive(TreeNode<T> node, T element)`
- `protected TreeNode<T> removeRecursive(TreeNode<T> node, T element)`
- `protected T findMin(TreeNode<T> node)`

## Ideas clave
- Inserción y remoción **recursivas**: cada llamado devuelve el nodo que queda en ese lugar.
- Remoción con 3 casos: hoja, un hijo, dos hijos (sucesor = mínimo del subárbol derecho).
- Se cambiaron los recursivos a `protected` para que [[SimpleAVL]] los pueda overridear (TP09).

## Subclases / Nodo
- [[SimpleAVL]] (extends)
- [[TreeNode]]

# TreeNode<T>

## Archivo fuente
- `src/treeModule/TreeNode.java`

## Rol
Nodo de [[SimpleBST]] / [[SimpleAVL]]. Árbol binario: dos hijos.

## Campos
- `T value`
- `TreeNode<T> left`
- `TreeNode<T> right`
- `int height` — altura del nodo (hoja = 0, `null` = -1). Lo usa [[SimpleAVL]]; el BST lo ignora.

## Visibilidad
- Clase **package-private** (sin `public`): solo se usa dentro de `treeModule`.

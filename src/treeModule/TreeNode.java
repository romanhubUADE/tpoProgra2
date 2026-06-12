package treeModule;

/**
 * Nodo genérico para los árboles binarios (BST y AVL).
 * Almacena el valor, los hijos izquierdo y derecho, y la altura del subárbol.
 */
class TreeNode<T> {
    T value;
    TreeNode<T> left;
    TreeNode<T> right;
    int height = 0;   // altura del nodo. Una hoja vale 0; null vale -1. Lo usa SimpleAVL.

    TreeNode(T value) {
        this.value = value;
    }
}

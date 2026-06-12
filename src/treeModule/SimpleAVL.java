package treeModule;

/**
 * AVL Tree: un BST que se mantiene siempre balanceado.
 *
 * Hereda toda la lógica de {@link SimpleBST} (insert/remove/search/inOrder) y solo
 * overridea la recursión de inserción y remoción para, después de cada cambio,
 * actualizar alturas y rebalancear con rotaciones. El factor de balance de cada
 * nodo queda siempre en {-1, 0, 1}.
 *
 * Convención de altura (igual que en clase): altura(null) = -1, una hoja = 0.
 */
public class SimpleAVL<T extends Comparable<T>> extends SimpleBST<T> {

    /**
     * Igual que el BST pero, al volver de la recursión (camino de subida),
     * recalcula la altura del nodo y lo rebalancea si hace falta.
     */
    @Override
    protected TreeNode<T> insertRecursive(TreeNode<T> node, T element) {
        if (node == null) return new TreeNode<>(element);

        int cmp = element.compareTo(node.value);
        if (cmp < 0) node.left  = insertRecursive(node.left,  element);
        else         node.right = insertRecursive(node.right, element);

        updateHeight(node);
        return rebalance(node);
    }

    /**
     * Igual que el BST pero, al subir de la recursión, recalcula alturas
     * y rebalancea. Garantiza que el árbol sigue siendo AVL después del borrado.
     */
    @Override
    protected TreeNode<T> removeRecursive(TreeNode<T> node, T element) {
        if (node == null) return null;

        int cmp = element.compareTo(node.value);
        if (cmp < 0) {
            node.left  = removeRecursive(node.left,  element);
        } else if (cmp > 0) {
            node.right = removeRecursive(node.right, element);
        } else {
            if (node.left  == null) return node.right;
            if (node.right == null) return node.left;
            T successor = findMin(node.right);
            node.value  = successor;
            node.right  = removeRecursive(node.right, successor);
        }

        updateHeight(node);
        return rebalance(node);
    }

    // ── Alturas y factor de balance ─────────────────────────────────────────

    /** Devuelve la altura del nodo; si es null devuelve -1 (convenio de clase). */
    private int height(TreeNode<T> node) {
        return node == null ? -1 : node.height;
    }

    /** Recalcula la altura de un nodo a partir de las alturas de sus hijos. */
    private void updateHeight(TreeNode<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    /** Factor de balance: altura del subárbol izquierdo menos la del derecho. */
    private int balanceFactor(TreeNode<T> node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // ── Rebalanceo ──────────────────────────────────────────────────────────

    private TreeNode<T> rebalance(TreeNode<T> node) {
        int balance = balanceFactor(node);

        // Pesado a la izquierda
        if (balance > 1) {
            if (balanceFactor(node.left) < 0) node.left = rotateLeft(node.left); // Caso LR
            return rotateRight(node);                                            // Caso LL
        }
        // Pesado a la derecha
        if (balance < -1) {
            if (balanceFactor(node.right) > 0) node.right = rotateRight(node.right); // Caso RL
            return rotateLeft(node);                                                 // Caso RR
        }
        return node; // ya está balanceado
    }

    // ── Rotaciones ────────────────────────────────────────────────────────────

    /**
     * Rotación simple a la derecha (caso LL: el subárbol izquierdo pesa más).
     *
     * Antes:       y              Después:    x
     *             / \                        / \
     *            x   C                      A   y
     *           / \                            / \
     *          A   t2                         t2   C
     *
     * x sube a la raíz, y baja a la derecha de x.
     * El subárbol t2 (que era hijo derecho de x) pasa a ser hijo izquierdo de y
     * para mantener el orden BST.
     */
    private TreeNode<T> rotateRight(TreeNode<T> y) {
        TreeNode<T> x  = y.left;
        TreeNode<T> t2 = x.right;
        x.right = y;
        y.left  = t2;
        // Actualiza primero y (ahora es hijo) y después x (ahora es la nueva raíz)
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    /**
     * Rotación simple a la izquierda (caso RR: el subárbol derecho pesa más).
     *
     * Antes:   x                  Después:     y
     *         / \                             / \
     *        A   y                           x   C
     *           / \                         / \
     *          t2   C                      A   t2
     *
     * y sube a la raíz, x baja a la izquierda de y.
     * El subárbol t2 (hijo izquierdo de y) pasa a ser hijo derecho de x.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> x) {
        TreeNode<T> y  = x.right;
        TreeNode<T> t2 = y.left;
        y.left  = x;
        x.right = t2;
        // Actualiza primero x (ahora es hijo) y después y (ahora es la nueva raíz)
        updateHeight(x);
        updateHeight(y);
        return y;
    }
}
